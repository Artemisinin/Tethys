package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.ConfiguredFeatures;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

import java.util.Random;

@Mixin(LargeTreeSaplingGenerator.class)
public class LargeTreeSaplingGeneratorMixin {

    @ModifyVariable(method = "generateLargeTree", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"))
    private ConfiguredFeature<TreeFeatureConfig, ?> modifyFeature(ConfiguredFeature<TreeFeatureConfig, ?> originalValue, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        BlockPos ground = pos.down();
        BlockState groundState = world.getBlockState(ground);
        if (groundState.isOf(Blocks.MOSS_BLOCK)) {
            // Dark oak shrubs
            if (originalValue == ConfiguredFeatures.DARK_OAK) {
                if (random.nextInt(10) >= 5) {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.DARK_OAK_SHRUB_SHORT;
                } else {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.DARK_OAK_SHRUB_TALL;
                }
            }
            // Jungle shrub
            if (originalValue == ConfiguredFeatures.MEGA_JUNGLE_TREE) {
                return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.JUNGLE_SHRUB;
            }
            // Spruce shrubs
            if (originalValue == ConfiguredFeatures.MEGA_SPRUCE || originalValue == ConfiguredFeatures.MEGA_PINE) {
                if (random.nextInt(10) >= 5) {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.SPRUCE_SHRUB_SHORT;
                } else {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.SPRUCE_SHRUB_TALL;
                }
            }
        }
        return originalValue;
    }
}