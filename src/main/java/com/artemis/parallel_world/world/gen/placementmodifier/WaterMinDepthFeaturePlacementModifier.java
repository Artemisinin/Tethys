package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;


import java.util.stream.Stream;

import static com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers.WATER_MIN_DEPTH_DECORATOR;

public class WaterMinDepthFeaturePlacementModifier extends PlacementModifier {
    private final int minSubmersion;

    public static final MapCodec<WaterMinDepthFeaturePlacementModifier> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Codec.INT.fieldOf("min_submersion").forGetter((waterMinDepthFeaturePlacementModifier) ->
                    waterMinDepthFeaturePlacementModifier.minSubmersion)).apply(instance, WaterMinDepthFeaturePlacementModifier::new));

    public WaterMinDepthFeaturePlacementModifier(int minSubmersion) { this.minSubmersion = minSubmersion; }

    public static WaterMinDepthFeaturePlacementModifier of(int minSubmersion) {
        return new WaterMinDepthFeaturePlacementModifier(minSubmersion);
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        int blocksUp = 1;
        BlockPos.Mutable mutable = pos.mutableCopy();
        while (blocksUp <= minSubmersion) {
            BlockPos mutableUp = mutable.offset(Direction.Axis.Y, blocksUp);
            // Check to see if the offset block is water.
            if (context.getWorld().testBlockState(mutableUp, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                // Check to see if we've reached the required minimum.
                if (blocksUp == minSubmersion) {
                    // If yes, we have hit minimum required depth, send back the block position tested.
                    return Stream.of(pos);
                } else {
                    // This position might still be valid because there is water but we need to check more.
                    // Offset the block again and check the next.
                    blocksUp++;
                }
            } else {
                // We are below the minimum required depth and encountered a block that is not water, return empty stream for this position.
                return Stream.of();
            }
        }
        return Stream.of();
    }


    @Override
    public PlacementModifierType<?> getType() {
        return WATER_MIN_DEPTH_DECORATOR;
    }
}
