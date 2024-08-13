package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.ConstantIntProvider;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.Stream;

import static com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers.BELOW_HEIGHTMAP_PLACEMENT_MODIFIER;

public class BelowHeightmapPlacementModifier extends PlacementModifier {

    public static final MapCodec<BelowHeightmapPlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.mapCodec((instance) -> instance.group(
                    Heightmap.Type.CODEC.fieldOf("heightmap").forGetter((belowHeightmapPlacementModifier) -> belowHeightmapPlacementModifier.heightmap),
                    IntProvider.createValidatingCodec(0, 256).fieldOf("count").forGetter((belowHeightmapPlacementModifier) -> belowHeightmapPlacementModifier.count),
                    Codec.BOOL.fieldOf("include_water").orElse(false).forGetter(config -> config.includeWater)).
            apply(instance, BelowHeightmapPlacementModifier::new));

    private final Heightmap.Type heightmap;
    private final IntProvider count;
    private final boolean includeWater;

    private BelowHeightmapPlacementModifier(Heightmap.Type heightmap, IntProvider count, boolean includeWater) {
        this.heightmap = heightmap;
        this.count = count;
        this.includeWater = includeWater;
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, IntProvider count, boolean includeWater) {
        return new BelowHeightmapPlacementModifier(heightmap, count, includeWater);
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, int count, boolean includeWater) {
        return new BelowHeightmapPlacementModifier(heightmap, ConstantIntProvider.create(count), includeWater);
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, IntProvider count) {
        return new BelowHeightmapPlacementModifier(heightmap, count, false);
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, int count) {
        return new BelowHeightmapPlacementModifier(heightmap, ConstantIntProvider.create(count), false);
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        Stream.Builder<BlockPos> builder = Stream.builder();
        int targetY = 0;
        boolean done;
        do {
            done = false;
            for(int tries = 0; tries < this.count.get(random); ++tries) {
                int newX = random.nextInt(16) + pos.getX();
                int newZ = random.nextInt(16) + pos.getZ();
                int maxY = context.getTopY(heightmap, newX, newZ) - 1;
                int newY = findPos(context, newX, maxY, newZ, targetY, includeWater);
                if (newY != 2147483647) {
                    builder.add(new BlockPos(newX, newY, newZ));
                    done = true;
                }
            }
            ++targetY;
        } while(done);
        return builder.build();
    }

    @Override
    public PlacementModifierType<?> getType() {
        return BELOW_HEIGHTMAP_PLACEMENT_MODIFIER;
    }

    private static int findPos(FeaturePlacementContext context, int x, int maxY, int z, int targetY, boolean includeWater) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, maxY, z);
        int i = 0;

        // This is the blockstate at the position we are currently testing to see if we can put something on it.
        BlockState placementState = context.getBlockState(mutable);

        for(int testY = maxY; testY >= context.getBottomY() + 1; --testY) {
            // Get the blockstate underneath the position we are currently testing.
            mutable.setY(testY - 1);
            BlockState floorState = context.getBlockState(mutable);

            if (!includeWater) {
                // If the block position below the test block position is not air, water, lava, or bedrock
                // AND the test block position is air, water, or lava,
                // return the test block position as a potential placement site.
                //
                // This will allow placing things under lava and water.

                if (!blocksSpawn(floorState) && !floorState.isOf(Blocks.BEDROCK) && blocksSpawn(placementState)) {
                    if (i == targetY) {
                        return mutable.getY() + 1;
                    }
                    ++i;
                }
            }
            // This will allow placing things on top of water, if you really want to.
            else if (!isAirOrLava(floorState) && !floorState.isOf(Blocks.BEDROCK)) {
                if (i == targetY) {
                    return mutable.getY() + 1;
                }
                ++i;
            }
            // We're going down a step in the next loop, so move the position we're examining down.
            placementState = floorState;
        }
        // lol why
        // Wikipedia:  "The number 2,147,483,647 (of hexadecimal 7FFFFFFF16) is the maximum
        // positive value for a 32-bit signed binary integer in computing."
        return 2147483647;
    }

    private static boolean blocksSpawn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) || state.isOf(Blocks.LAVA);
    }

    private static boolean isAirOrLava(BlockState state) {
        return state.isAir() || state.isOf(Blocks.LAVA);
    }
}
