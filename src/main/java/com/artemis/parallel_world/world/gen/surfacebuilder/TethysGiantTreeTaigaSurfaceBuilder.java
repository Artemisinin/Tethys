package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;

public class TethysGiantTreeTaigaSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    public TethysGiantTreeTaigaSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    public void generate(Random random, Chunk chunk, Biome biome, int i, int j, int k, double d, BlockState blockState, BlockState blockState2, int l, long m, TernarySurfaceConfig ternarySurfaceConfig) {
        if (d > 1.75D) {
            TethysSurfaceBuilder.TETHYS_DEFAULT.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, l, m, SurfaceBuilder.COARSE_DIRT_CONFIG);
        } else if (d > -0.95D) {
            TethysSurfaceBuilder.TETHYS_DEFAULT.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, l, m, SurfaceBuilder.PODZOL_CONFIG);
        } else {
            TethysSurfaceBuilder.TETHYS_DEFAULT.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, l, m, SurfaceBuilder.GRASS_CONFIG);
        }
    }
}