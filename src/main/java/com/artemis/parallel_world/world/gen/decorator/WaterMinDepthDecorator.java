package com.artemis.parallel_world.world.gen.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.decorator.AbstractConditionalPlacementModifier;
import net.minecraft.world.gen.decorator.DecoratorContext;
import net.minecraft.world.gen.decorator.PlacementModifierType;

import java.util.Random;

import static com.artemis.parallel_world.world.gen.feature.TethysDecorators.WATER_MIN_DEPTH_DECORATOR;

public class WaterMinDepthDecorator extends AbstractConditionalPlacementModifier {
    public int minSubmersion;

    public static final Codec<WaterMinDepthDecorator> CODEC = RecordCodecBuilder.create((instance) ->
            instance.group(Codec.INT.fieldOf("min_submersion").forGetter((waterMinDepthDecorator) ->
                    waterMinDepthDecorator.minSubmersion)).apply(instance, WaterMinDepthDecorator::new));

    public WaterMinDepthDecorator(int minSubmersion) { this.minSubmersion = minSubmersion; }

    public static WaterMinDepthDecorator of(int minSubmersion) {
        return new WaterMinDepthDecorator(minSubmersion);
    }

    @Override
    protected boolean shouldPlace(DecoratorContext context, Random random, BlockPos blockPos) {
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
