package com.artemis.parallel_world.block.sapling;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;

import org.jetbrains.annotations.Nullable;


public class DogwoodSaplingGenerator extends SaplingGenerator {
    public DogwoodSaplingGenerator() {
    }

    @Nullable
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? TethysConfiguredFeatures.DOGWOOD_TREE_BEES : TethysConfiguredFeatures.DOGWOOD_TREE;
    }
}