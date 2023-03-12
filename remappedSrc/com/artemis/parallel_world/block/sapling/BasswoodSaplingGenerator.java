package com.artemis.parallel_world.block.sapling;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.util.math.random.Random;

public class BasswoodSaplingGenerator extends SaplingGenerator {
    public BasswoodSaplingGenerator() {
    }

    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? TethysConfiguredFeatures.BASSWOOD_TREE_BEES : TethysConfiguredFeatures.BASSWOOD_TREE;
    }
}

