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
    public WaterMinDepthDecorator(Codec<WaterMinDepthDecoratorConfig> codec) {super(codec);}

    public Stream<BlockPos> getPositions(DecoratorContext decoratorContext, Random random, WaterMinDepthDecoratorConfig waterMinDepthDecoratorConfig, BlockPos blockPos) {

        int blocksUp = 0;
        while (blocksUp < waterMinDepthDecoratorConfig.minSubmersion) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // If the block is not water, return empty stream.
            if (!decoratorContext.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                return Stream.of();
            // Otherwise increment to check next.
            } else {
                blocksUp++;
            }
        }
        // We ran through the minimum height required and all were water blocks, so this would be valid placement.
        return Stream.of(blockPos);
    }
}
