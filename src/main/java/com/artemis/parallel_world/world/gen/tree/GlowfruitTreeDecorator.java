package com.artemis.parallel_world.world.gen.tree;


import com.artemis.parallel_world.block.TethysBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;


import java.util.List;
import java.util.Random;
import java.util.Set;

public class GlowfruitTreeDecorator {
    public static final GlowfruitTreeDecorator INSTANCE = new GlowfruitTreeDecorator();

//    public static final Codec<GlowfruitTreeDecorator> CODEC = Codec.unit(() -> {
//        return INSTANCE;
//    });
//
//    public GlowfruitTreeDecorator() {
//    }
//
//    @Override
//    protected TreeDecoratorType<?> getType() {
//        return null;
//    }
//
////    protected TreeDecoratorType<?> getType() {
////        return Dimension.GLOWFRUIT;
////    }
//
//    public void generate(StructureWorldAccess world, Random random, List<BlockPos> logPositions, List<BlockPos> leavesPositions, Set<BlockPos> placedStates, BlockBox box) {
//        leavesPositions.forEach((pos) -> {
//            if (random.nextInt(10) == 0) {
//                this.setBlockStateAndEncompassPosition(world, pos, TethysBlocks.GLOWFRUIT.getDefaultState(), placedStates, box);
//            }
//        });
//    }

}
