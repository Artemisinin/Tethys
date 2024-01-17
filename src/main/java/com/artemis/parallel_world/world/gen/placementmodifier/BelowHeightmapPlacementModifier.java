package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
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

    public static final Codec<BelowHeightmapPlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(
                Heightmap.Type.CODEC.fieldOf("heightmap").forGetter((belowHeightmapPlacementModifier) -> {
            return belowHeightmapPlacementModifier.heightmap;
        }),
                IntProvider.createValidatingCodec(0, 256).fieldOf("count").forGetter((belowHeightmapPlacementModifier) -> {
            return belowHeightmapPlacementModifier.count;
        })).apply(instance, BelowHeightmapPlacementModifier::new);
    });

    private final Heightmap.Type heightmap;
    private final IntProvider count;

    private BelowHeightmapPlacementModifier(Heightmap.Type heightmap, IntProvider count) {
        this.heightmap = heightmap;
        this.count = count;
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, IntProvider count) {
        return new BelowHeightmapPlacementModifier(heightmap, count);
    }

    public BelowHeightmapPlacementModifier of (Heightmap.Type heightmap, int count) {
        return new BelowHeightmapPlacementModifier(heightmap, ConstantIntProvider.create(count));
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
                int newY = findPos(context, newX, maxY, newZ, targetY);
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

    private static int findPos(FeaturePlacementContext context, int x, int maxY, int z, int targetY) {
        BlockPos.Mutable mutable = new BlockPos.Mutable(x, maxY, z);
        int i = 0;
        BlockState placementState = context.getBlockState(mutable);

        for(int testY = maxY; testY >= context.getBottomY() + 1; --testY) {
            mutable.setY(testY - 1);
            BlockState floorState = context.getBlockState(mutable);

            // If the block position below the test block position is not air, water, lava, or bedrock
            // AND the test block position is air, water, or lava,
            // return the test block position as a potential placement site.

            if (!blocksSpawn(floorState) && !floorState.isOf(Blocks.BEDROCK) && blocksSpawn(placementState) ) {
                if (i == targetY) {
                    return mutable.getY() + 1;
                }
                ++i;
            }
            placementState = floorState;
        }
        return 2147483647;
    }

    private static boolean blocksSpawn(BlockState state) {
        return state.isAir() || state.isOf(Blocks.WATER) || state.isOf(Blocks.LAVA);
    }
}
