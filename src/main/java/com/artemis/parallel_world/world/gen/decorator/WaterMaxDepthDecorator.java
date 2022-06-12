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

import static com.artemis.parallel_world.world.gen.feature.TethysDecorators.WATER_MAX_DEPTH_DECORATOR;

public class WaterMaxDepthDecorator extends AbstractConditionalPlacementModifier {
    private int maxSubmersion;

    public static final Codec<WaterMaxDepthDecorator> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("max_submersion").forGetter((waterMaxDepthDecorator) -> {
            return waterMaxDepthDecorator.maxSubmersion;
        })).apply(instance, WaterMaxDepthDecorator::new);
    });

    public  WaterMaxDepthDecorator(int maxSubmersion) { this.maxSubmersion = maxSubmersion; }

    public static WaterMaxDepthDecorator of(int maxSubmersion) {
        return new WaterMaxDepthDecorator(maxSubmersion);
    }

    protected boolean shouldPlace(DecoratorContext context, Random random, BlockPos blockPos) {
        int blocksUp = 1;
        while (blocksUp <= maxSubmersion + 1) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // If a non-water block is encountered, return block position in stream since it is not submerged. Currently allows *flowing* water.
            if (!context.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                return true;
                // Otherwise it was a water block, so increment to check the next block.
            } else {
                blocksUp++;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return WATER_MAX_DEPTH_DECORATOR;
    }
}
