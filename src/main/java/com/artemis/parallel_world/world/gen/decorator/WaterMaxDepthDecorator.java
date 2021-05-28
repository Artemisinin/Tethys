package com.artemis.parallel_world.world.gen.decorator;

import com.mojang.serialization.Codec;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorContext;

import java.util.Random;
import java.util.stream.Stream;

public class WaterMaxDepthDecorator extends Decorator<WaterMaxDepthDecoratorConfig> {
    public WaterMaxDepthDecorator(Codec<WaterMaxDepthDecoratorConfig> codec) {super(codec);}

    public Stream<BlockPos> getPositions(DecoratorContext decoratorContext, Random random, WaterMaxDepthDecoratorConfig waterMaxDepthDecoratorConfig, BlockPos blockPos) {

        int blocksUp = 1;
        while (blocksUp <= waterMaxDepthDecoratorConfig.maxSubmersion + 1) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // If a non-water block is encountered, return block position in stream since it is not submerged. Currently allows *flowing* water.
            if (!decoratorContext.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                return Stream.of(blockPos);
            // Otherwise it was a water block, so increment to check the next block.
            } else {
                blocksUp++;
            }
        }
        return Stream.of();
    }
}
