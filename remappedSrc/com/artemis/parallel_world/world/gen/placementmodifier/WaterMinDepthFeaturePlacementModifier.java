package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;


import static com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers.WATER_MIN_DEPTH_DECORATOR;

public class WaterMinDepthFeaturePlacementModifier extends AbstractConditionalPlacementModifier {
    public int minSubmersion;

    public static final Codec<WaterMinDepthFeaturePlacementModifier> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(Codec.INT.fieldOf("min_submersion").forGetter((waterMinDepthFeaturePlacementModifier) ->
                    waterMinDepthFeaturePlacementModifier.minSubmersion)).apply(instance, WaterMinDepthFeaturePlacementModifier::new));

    public WaterMinDepthFeaturePlacementModifier(int minSubmersion) { this.minSubmersion = minSubmersion; }

    public static WaterMinDepthFeaturePlacementModifier of(int minSubmersion) {
        return new WaterMinDepthFeaturePlacementModifier(minSubmersion);
    }

    @Override
    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos blockPos) {
        int blocksUp = 1;
        while (blocksUp <= minSubmersion) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // Check to see if the offset block is water.
            if (context.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                // Check to see if we've reached the required minimum.
                if (blocksUp == minSubmersion) {
                    // If yes, we have hit minimum required depth, send back the block position tested.
                    return true;
                } else {
                    // This position might still be valid because there is water but we need to check more.
                    // Offset the block again and check the next.
                    blocksUp++;
                }
            } else {
                // We are below the minimum required depth and encountered a block that is not water, return empty stream for this position.
                return false;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return WATER_MIN_DEPTH_DECORATOR;
    }
}
