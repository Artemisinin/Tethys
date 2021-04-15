package com.artemis.parallel_world.world.gen.decorator;

import com.mojang.serialization.Codec;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;

import java.util.Random;
import java.util.stream.Stream;

public class WaterMinDepthDecorator extends Decorator<WaterMinDepthDecoratorConfig> {
    public WaterMinDepthDecorator(Codec<WaterMinDepthDecoratorConfig> codec) {
        super(codec);
    }

    public Stream<BlockPos> getPositions(DecoratorContext decoratorContext, Random random, WaterMinDepthDecoratorConfig waterMinDepthDecoratorConfig, BlockPos blockPos) {

        int blocksUp = 1;
        while (blocksUp <= waterMinDepthDecoratorConfig.minSubmersion) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // Check to see if the offset block is water.
            if (decoratorContext.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                // Check to see if we've reached the required minimum.
                if (blocksUp == waterMinDepthDecoratorConfig.minSubmersion) {
                    // If yes, we have hit minimum required depth, send back the block position tested.
                    return Stream.of(blockPos);
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
}
