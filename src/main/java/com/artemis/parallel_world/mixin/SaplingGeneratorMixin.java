package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
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

@Mixin(SaplingGenerator.class)
public class SaplingGeneratorMixin {

    @ModifyVariable(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Block;getDefaultState()Lnet/minecraft/block/BlockState;"))
    private ConfiguredFeature<TreeFeatureConfig, ?> modifyFeature(ConfiguredFeature<TreeFeatureConfig, ?> originalValue, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        BlockPos ground = pos.down();
        BlockState groundState = world.getBlockState(ground);
        if (groundState.isOf(Blocks.MOSS_BLOCK)) {
        // Vanilla trees
            // Acacia doesn't grow
            if (originalValue == ConfiguredFeatures.ACACIA) {
                return null;
            }
            // Birch shrub
            if (originalValue == ConfiguredFeatures.BIRCH_BEES_005 || originalValue == ConfiguredFeatures.BIRCH) {
                return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.BIRCH_SHRUB;
            }
            // Jungle shrub
            if (originalValue == ConfiguredFeatures.JUNGLE_TREE_NO_VINE) {
                return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.JUNGLE_SHRUB;
            }
            // Oak shrubs
            if (originalValue == ConfiguredFeatures.FANCY_OAK_BEES_005 || originalValue == ConfiguredFeatures.FANCY_OAK || originalValue == ConfiguredFeatures.OAK_BEES_005 || originalValue == ConfiguredFeatures.OAK) {
                return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.OAK_SHRUB;
            }
            // Spruce shrubs
            if (originalValue == ConfiguredFeatures.SPRUCE) {
                if (random.nextInt(10) >= 5) {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.SPRUCE_SHRUB_SHORT;
                } else {
                    return (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.SPRUCE_SHRUB_TALL;
                }
            }
        // Tethys trees -- all null for now
            if (originalValue == TethysConfiguredFeatures.BASSWOOD_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.BLACKCURRANT_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.CHERRY_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.DOGWOOD_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.ELDERBERRY_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.GINKGO_TREE) {
                return null;
            }
            if (originalValue == TethysConfiguredFeatures.SWEETGUM_TREE) {
                return null;
            }
        }
        return originalValue;
    }
}
