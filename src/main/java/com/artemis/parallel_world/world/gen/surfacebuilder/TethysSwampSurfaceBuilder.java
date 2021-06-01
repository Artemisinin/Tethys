package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;

public class TethysSwampSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    public TethysSwampSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int i, long l, TernarySurfaceConfig surfaceConfig) {

        double e = Biome.FOLIAGE_NOISE.sample((double)x * 0.25D, (double)z * 0.25D, false);
        if (e > 0.0D) {
            int o = x & 15;
            int p = z & 15;
            BlockPos.Mutable mutable = new BlockPos.Mutable();

            for(int q = height; q >= i; --q) {
                mutable.set(o, q, p);
                if (!chunk.getBlockState(mutable).isAir()) {
                    if (!chunk.getBlockState(mutable).isOf(defaultFluid.getBlock()) &&
                            !chunk.getBlockState(mutable.offset(Direction.NORTH)).isAir() &&
                            !chunk.getBlockState(mutable.offset(Direction.SOUTH)).isAir() &&
                            !chunk.getBlockState(mutable.offset(Direction.EAST)).isAir() &&
                            !chunk.getBlockState(mutable.offset(Direction.WEST)).isAir() &&
                            !chunk.getBlockState(mutable.offset(Direction.DOWN)).isAir())
                    {
                        chunk.setBlockState(mutable, defaultFluid, false);
                    }
                    break;
                }
            }
        }

        SurfaceBuilder.DEFAULT.generate(random, chunk, biome, x, z, height, noise, defaultBlock, defaultFluid, seaLevel, i, l, surfaceConfig);
    }
}
