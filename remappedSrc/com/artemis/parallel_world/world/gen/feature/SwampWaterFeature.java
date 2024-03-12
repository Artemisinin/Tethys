package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.block.TethysBlocks;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class SwampWaterFeature extends Feature<DefaultFeatureConfig> {

    public SwampWaterFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        BlockPos blockPos = context.getOrigin();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        if (canPlace(structureWorldAccess, blockPos)) {
            this.setBlockState(structureWorldAccess, blockPos.down(), Blocks.WATER.getDefaultState());
            this.setBlockState(structureWorldAccess, blockPos.down().down(), Blocks.MUD.getDefaultState());
            return true;
        }
        return false;
    }

    private boolean canPlace(WorldAccess world, BlockPos pos) {
        BlockPos blockPos = pos.down();
        BlockState blockstate = world.getBlockState(blockPos);
        BlockState down = world.getBlockState(blockPos.down());
        BlockState north = world.getBlockState(blockPos.north());
        BlockState south = world.getBlockState(blockPos.south());
        BlockState west = world.getBlockState(blockPos.west());
        BlockState east = world.getBlockState(blockPos.east());
        return world.getBlockState(pos).isOf(Blocks.AIR) &&
                blockstate.isIn(TethysBlocks.DIRT_OR_STONE) &&
                down.isIn(TethysBlocks.DIRT_OR_STONE) &&
                (north.isIn(TethysBlocks.DIRT_OR_STONE) || north.getFluidState().isIn(FluidTags.WATER)) &&
                (south.isIn(TethysBlocks.DIRT_OR_STONE) || south.getFluidState().isIn(FluidTags.WATER)) &&
                (west.isIn(TethysBlocks.DIRT_OR_STONE) || west.getFluidState().isIn(FluidTags.WATER)) &&
                (east.isIn(TethysBlocks.DIRT_OR_STONE) || east.getFluidState().isIn(FluidTags.WATER));
    }
}
