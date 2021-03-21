package com.artemis.parallel_world.world.gen.tree;


import com.artemis.parallel_world.Dimension;
import com.artemis.parallel_world.block.TethysBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.LeavesBlock;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


import java.util.List;
import java.util.Random;
import java.util.Set;

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

    public void generate(StructureWorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> placedStates, BlockBox box) {
        leavesPositions.forEach((pos) -> {
            if (random.nextInt(12) == 0 && leavesPositions.contains(pos.up())) {
                this.setBlockStateAndEncompassPosition(world, pos, TethysBlocks.GLOWFRUIT.getStateManager().getDefaultState().with(LeavesBlock.DISTANCE, 1).with(LeavesBlock.PERSISTENT, false).with(Properties.WATERLOGGED, false), placedStates, box);
            }
        });
    }

}
