package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.Dimension;
import com.mojang.serialization.Codec;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FreezeShadedWaterFeature extends Feature<DefaultFeatureConfig> {
    public FreezeShadedWaterFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for (int i = 0; i < 16; ++i) {
            for (int j = 0; j < 16; ++j) {
                int x = blockPos.getX() + i;
                int y = Dimension.TETHYS_SEA_LEVEL -1;
                int z = blockPos.getZ() + j;
                mutable.set(x, y, z);
                Biome biome = structureWorldAccess.getBiome(mutable).value();
                if (structureWorldAccess.getFluidState(mutable).isOf(Fluids.WATER) &&
                        biome.canSetIce(structureWorldAccess, mutable, false)) {
                    int random = Random.create().nextBetween(0,5);
                    if (random > 1) {
                        structureWorldAccess.setBlockState(mutable, Blocks.ICE.getDefaultState(), 2);
                    }
                }
            }
        }
        return true;
    }
}
