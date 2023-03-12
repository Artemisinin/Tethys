package com.artemis.parallel_world.block.sapling;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;

public class BlackcurrantSaplingGenerator extends SaplingGenerator {
    public BlackcurrantSaplingGenerator() {
    }

    @Nullable
    @Override
    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees) {
        return bees ? TethysConfiguredFeatures.BLACKCURRANT_TREE_BEES : TethysConfiguredFeatures.BLACKCURRANT_TREE;
    }
}