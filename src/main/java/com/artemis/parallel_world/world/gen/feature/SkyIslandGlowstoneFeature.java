package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;

import java.util.Random;

public class SkyIslandGlowstoneFeature extends Feature<DefaultFeatureConfig> {
    public SkyIslandGlowstoneFeature(Codec<DefaultFeatureConfig> codec) {
        super(codec);
    }

    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        if (!structureWorldAccess.isAir(blockPos)) {
            return false;
        } else {
            BlockState blockState = structureWorldAccess.getBlockState(blockPos.up());
            if (!blockState.isOf(Blocks.STONE)) {
                return false;
            } else {
                structureWorldAccess.setBlockState(blockPos, Blocks.GLOWSTONE.getDefaultState(), 2);

                for(int i = 0; i < 1500; ++i) {
                    BlockPos blockPos2 = blockPos.add(random.nextInt(8) - random.nextInt(8), -random.nextInt(12), random.nextInt(8) - random.nextInt(8));
                    if (structureWorldAccess.getBlockState(blockPos2).isAir()) {
                        int j = 0;
                        Direction[] var9 = Direction.values();
                        int var10 = var9.length;

                        for(int var11 = 0; var11 < var10; ++var11) {
                            Direction direction = var9[var11];
                            if (structureWorldAccess.getBlockState(blockPos2.offset(direction)).isOf(Blocks.GLOWSTONE)) {
                                ++j;
                            }

                            if (j > 1) {
                                break;
                            }
                        }

                        if (j == 1) {
                            structureWorldAccess.setBlockState(blockPos2, Blocks.GLOWSTONE.getDefaultState(), 2);
                        }
                    }
                }

                return true;
            }
        }
    }
}
