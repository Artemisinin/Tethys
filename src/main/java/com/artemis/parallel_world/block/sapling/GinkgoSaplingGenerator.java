package com.artemis.parallel_world.block.sapling;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class GinkgoSaplingGenerator extends SaplingGenerator {
    public GinkgoSaplingGenerator() {
    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bees) {
        return bees ? (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.GINKGO_TREE_BEES : (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.GINKGO_TREE;
    }
}