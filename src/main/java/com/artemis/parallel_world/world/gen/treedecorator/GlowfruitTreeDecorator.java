package com.artemis.parallel_world.world.gen.treedecorator;


import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.mixin.TreeDecoratorTypeRegisterInvoker;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.LeavesBlock;
import net.minecraft.fluid.FluidState;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


public class GlowfruitTreeDecorator extends TreeDecorator {

    public static TreeDecoratorType<GlowfruitTreeDecorator> GLOWFRUIT;

    public static final GlowfruitTreeDecorator INSTANCE = new GlowfruitTreeDecorator();

    public static final MapCodec<GlowfruitTreeDecorator> CODEC = MapCodec.unit(() -> INSTANCE);

    public GlowfruitTreeDecorator() {
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return GlowfruitTreeDecorator.GLOWFRUIT;
    }

    public void generate(Generator generator) {
        Random random = generator.getRandom();
        generator.getLeavesPositions().forEach((pos) -> {
                    if (random.nextInt(12) == 0 && generator.getLeavesPositions().contains(pos.up())) {
                        if (generator.getWorld().testFluidState(pos, FluidState::isEmpty)) {
                            generator.replace(pos, TethysBlocks.GLOWFRUIT.getStateManager().getDefaultState().with(LeavesBlock.DISTANCE, 1).with(LeavesBlock.PERSISTENT, false).with(Properties.WATERLOGGED, false));
                        } else {
                            generator.replace(pos, TethysBlocks.GLOWFRUIT.getStateManager().getDefaultState().with(LeavesBlock.DISTANCE, 1).with(LeavesBlock.PERSISTENT, false).with(Properties.WATERLOGGED, true));

                        }
                    }
                }
        );
    }

    public static void registerGlowfruitTreeDecorator() {
        GLOWFRUIT = TreeDecoratorTypeRegisterInvoker.invokeRegister("parallel_world:glowfruit", GlowfruitTreeDecorator.CODEC);
    }
}
