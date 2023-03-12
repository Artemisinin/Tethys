package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldAccess;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.HugeMushroomFeature;
import net.minecraft.world.gen.feature.HugeMushroomFeatureConfig;

public class HugeHeathFlatMushroomFeature extends HugeMushroomFeature {
    public HugeHeathFlatMushroomFeature(Codec<HugeMushroomFeatureConfig> codec) {
        super(codec);
    }

    protected int getCapSize(int i, int j, int capSize, int y) {
        return y <= 3 ? 0 : capSize;
    }

    @Override
    protected void generateCap(WorldAccess world, Random random, BlockPos start, int y, BlockPos.Mutable mutable, HugeMushroomFeatureConfig config) {
        int i = config.foliageRadius;

        for(int j = -i; j <= i; ++j) {
            for(int k = -i; k <= i; ++k) {
                boolean bl = j == -i;
                boolean bl2 = j == i;
                boolean bl3 = k == -i;
                boolean bl4 = k == i;
                boolean bl5 = bl || bl2;
                boolean bl6 = bl3 || bl4;
                if (!bl5 || !bl6) {
                    mutable.set(start, j, y, k);
                    if (!world.getBlockState(mutable).isOpaqueFullCube(world, mutable)) {
                        boolean bl7 = bl || bl6 && j == 1 - i;
                        boolean bl8 = bl2 || bl6 && j == i - 1;
                        boolean bl9 = bl3 || bl5 && k == 1 - i;
                        boolean bl10 = bl4 || bl5 && k == i - 1;
                        this.setBlockState(world, mutable, config.capProvider.get(random, start).with(MushroomBlock.WEST, bl7).with(MushroomBlock.EAST, bl8).with(MushroomBlock.NORTH, bl9).with(MushroomBlock.SOUTH, bl10));
                    }
                }
            }
        }
    }
}
