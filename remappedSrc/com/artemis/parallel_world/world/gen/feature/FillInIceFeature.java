package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FillInIceFeature extends Feature<DefaultFeatureConfig> {
    public FillInIceFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable north = new BlockPos.Mutable();
        BlockPos.Mutable south = new BlockPos.Mutable();
        BlockPos.Mutable east = new BlockPos.Mutable();
        BlockPos.Mutable west = new BlockPos.Mutable();
        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int x = blockPos.getX() + i;
                int z = blockPos.getZ() + j;
                int m = structureWorldAccess.getTopY(Heightmap.Type.MOTION_BLOCKING, x, z) - 1;
                mutable.set(x, m, z);
                if (structureWorldAccess.getFluidState(mutable).isOf(Fluids.WATER)) {
                    north.set(mutable).move(Direction.NORTH, 1);
                    south.set(mutable).move(Direction.SOUTH, 1);
                    east.set(mutable).move(Direction.EAST, 1);
                    west.set(mutable).move(Direction.WEST, 1);
                    if ((structureWorldAccess.getBiome(north).value().isCold(north) ||
                            structureWorldAccess.getBiome(south).value().isCold(south) ||
                            structureWorldAccess.getBiome(east).value().isCold(east) ||
                            structureWorldAccess.getBiome(west).value().isCold(west))) {
                        structureWorldAccess.setBlockState(mutable, Blocks.ICE.getDefaultState(), 2);
                        return true;
                    }
                }
            }
        }
        return true;
    }
}
