package com.artemis.parallel_world.block.sapling;

import java.util.Random;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

public class CherrySaplingGenerator extends SaplingGenerator {
    public CherrySaplingGenerator() {
    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bees) {
        return bees ? (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.CHERRY_TREE : (ConfiguredFeature<TreeFeatureConfig, ?>) TethysConfiguredFeatures.CHERRY_TREE;
    }
}