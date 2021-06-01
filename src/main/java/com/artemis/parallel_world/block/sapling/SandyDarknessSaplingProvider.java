package com.artemis.parallel_world.block.sapling;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class SandyDarknessSaplingProvider extends Block {
    public SandyDarknessSaplingProvider(Settings settings) {
        super(settings);
    }

    public boolean canPlaceAt(BlockState state, WorldView world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        return world.getBlockState(blockPos).isIn(BlockTags.SAND) && !world.isSkyVisibleAllowingSea(blockPos);
    }

}
