package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;

import java.util.Random;

public class SkyIslandSurfaceBuilder extends SurfaceBuilder<TernarySurfaceConfig> {
    public SkyIslandSurfaceBuilder(Codec<TernarySurfaceConfig> codec) {
        super(codec);
    }

    public void generate(Random random, Chunk chunk, Biome biome, int i, int j, int k, double d, BlockState blockState, BlockState blockState2, int l, long m, TernarySurfaceConfig ternarySurfaceConfig) {
        this.generate(random, chunk, biome, i, j, k, d, blockState, blockState2, ternarySurfaceConfig.getTopMaterial(), ternarySurfaceConfig.getUnderMaterial(), ternarySurfaceConfig.getUnderwaterMaterial(), l);
    }

    protected void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState fluidBlock, BlockState topBlock, BlockState underBlock, BlockState underwaterBlock, int seaLevel) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = (int)(noise / 3.0D + 3.0D + random.nextDouble() * 0.25D);
        int j;
        BlockState blockState5;
        if (i == 0) {
            boolean bl = false;

            for(j = height; j >= 19; --j) {
                mutable.set(x, j, z);
                BlockState blockState = chunk.getBlockState(mutable);
                if (blockState.isAir()) {
                    bl = false;
                } else if (blockState.isOf(defaultBlock.getBlock())) {
                    if (!bl) {
                        if (j >= seaLevel) {
                            blockState5 = Blocks.AIR.getDefaultState();
                        } else if (j == seaLevel - 1) {
                            blockState5 = biome.getTemperature(mutable) < 0.15F ? Blocks.ICE.getDefaultState() : fluidBlock;
                        } else if (j >= seaLevel - (7 + i)) {
                            blockState5 = defaultBlock;
                        } else {
                            blockState5 = underwaterBlock;
                        }

                        chunk.setBlockState(mutable, blockState5, false);
                    }

                    bl = true;
                }
            }
        } else {
            BlockState blockState6 = underBlock;
            j = -1;

            for(int l = height; l >= 19; --l) {
                mutable.set(x, l, z);
                blockState5 = chunk.getBlockState(mutable);
                if (blockState5.isAir()) {
                    j = -1;
                } else if (blockState5.isOf(defaultBlock.getBlock())) {
                    if (j == -1) {
                        j = i;
                        BlockState blockState12;
                        if (l >= seaLevel + 2) {
                            blockState12 = topBlock;
                        } else if (l >= seaLevel - 1) {
                            blockState6 = underBlock;
                            blockState12 = topBlock;
                        } else if (l >= seaLevel - 4) {
                            blockState6 = underBlock;
                            blockState12 = underBlock;
                        } else if (l >= seaLevel - (7 + i)) {
                            blockState12 = blockState6;
                        } else {
                            blockState6 = defaultBlock;
                            blockState12 = underwaterBlock;
                        }

                        chunk.setBlockState(mutable, blockState12, false);
                    } else if (j > 0) {
                        --j;
                        chunk.setBlockState(mutable, blockState6, false);
                        if (j == 0 && blockState6.isOf(Blocks.SAND) && i > 1) {
                            j = random.nextInt(4) + Math.max(0, l - seaLevel);
                            blockState6 = blockState6.isOf(Blocks.RED_SAND) ? Blocks.RED_SANDSTONE.getDefaultState() : Blocks.SANDSTONE.getDefaultState();
                        }
                    }
                }
            }
        }

    }
}
