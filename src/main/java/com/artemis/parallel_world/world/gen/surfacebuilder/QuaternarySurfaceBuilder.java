package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;


import java.util.Random;

public class QuaternarySurfaceBuilder extends SurfaceBuilder<QuaternarySurfaceConfig> implements SurfaceConfig {

    public QuaternarySurfaceBuilder() {
        super(QuaternarySurfaceConfig.CODEC);
    }

    public void generate(Random random, Chunk chunk, Biome biome, int i, int j, int k, double d, BlockState defaultBlock, BlockState fluidBlock, int l, long m, QuaternarySurfaceConfig quaternarySurfaceConfig) {
        this.generate(random, chunk, biome, i, j, k, d, quaternarySurfaceConfig.getDefaultMaterial(), fluidBlock, quaternarySurfaceConfig.getTopMaterial(), quaternarySurfaceConfig.getUnderMaterial(), quaternarySurfaceConfig.getUnderwaterMaterial(), l);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState fluidBlock, BlockState topBlock, BlockState underBlock, BlockState underwaterBlock, int seaLevel) {
        BlockState blockState = topBlock;
        BlockState blockState2 = underBlock;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = -1;
        int j = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int k = x & 15;
        int l = z & 15;

        for(int m = height; m >= 0; --m) {
            mutable.set(k, m, l);
            BlockState blockState3 = chunk.getBlockState(mutable);
            if (blockState3.isAir()) {
                i = -1;
            } else if (blockState3.isOf(defaultBlock.getBlock())) {
                if (i == -1) {
                    if (j <= 0) {
                        blockState = Blocks.AIR.getDefaultState();
                        blockState2 = defaultBlock;
                    } else if (m >= seaLevel - 4 && m <= seaLevel + 1) {
                        blockState = topBlock;
                        blockState2 = underBlock;
                    }

                    if (m < seaLevel && (blockState == null || blockState.isAir())) {
                        blockState = fluidBlock;

                        mutable.set(k, m, l);
                    }

                    i = j;
                    if (m >= seaLevel - 1) {
                        chunk.setBlockState(mutable, blockState, false);
                    } else if (m < seaLevel - 7 - j) {
                        blockState = Blocks.AIR.getDefaultState();
                        blockState2 = defaultBlock;
                        chunk.setBlockState(mutable, underwaterBlock, false);
                    } else {
                        chunk.setBlockState(mutable, blockState2, false);
                    }
                } else if (i > 0) {
                    --i;
                    chunk.setBlockState(mutable, blockState2, false);
                    if (i == 0 && blockState2.isOf(Blocks.SAND) && j > 1) {
                        i = random.nextInt(4) + Math.max(0, m - 63);
                        blockState2 = blockState2.isOf(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                    }
                }
            }
        }
    }

    @Override
    public BlockState getTopMaterial() {
        return null;
    }

    @Override
    public BlockState getUnderMaterial() {
        return null;
    }
}