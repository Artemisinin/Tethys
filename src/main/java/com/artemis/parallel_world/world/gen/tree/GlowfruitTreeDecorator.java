package com.artemis.parallel_world.world.gen.tree;


import com.artemis.parallel_world.Dimension;
import com.artemis.parallel_world.block.TethysBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.function.BiConsumer;

public class GlowfruitTreeDecorator extends TreeDecorator {
    public static final GlowfruitTreeDecorator INSTANCE = new GlowfruitTreeDecorator();

    public static final Codec<GlowfruitTreeDecorator> CODEC = Codec.unit(() -> {
        return INSTANCE;
    });

    public GlowfruitTreeDecorator() {
    }

    @Override
    protected TreeDecoratorType<?> getType() {
        return Dimension.GLOWFRUIT;
    }

    public void generate(TestableWorld world, BiConsumer<BlockPos, BlockState> biConsumer, Random random, List<BlockPos> leavesPositions, List<BlockPos> logPositions) {
        leavesPositions.forEach((pos) -> {
            if (random.nextInt(12) == 0 && leavesPositions.contains(pos.up())) {
                biConsumer.accept(pos, TethysBlocks.GLOWFRUIT.getStateManager().getDefaultState().with(LeavesBlock.DISTANCE, 1).with(LeavesBlock.PERSISTENT, false).with(Properties.WATERLOGGED, false));
            }
        });
    }

}
