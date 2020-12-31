package com.artemis.parallel_world.block;

import java.util.Random;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import org.jetbrains.annotations.Nullable;

public class CustomSaplingGenerator extends SaplingGenerator {
    private RegistryKey<ConfiguredFeature<?, ?>> KEY;
    public CustomSaplingGenerator(RegistryKey<ConfiguredFeature<?, ?>> FEATURE_KEY) {
        KEY =  FEATURE_KEY;
    }

    @Nullable
    protected ConfiguredFeature<TreeFeatureConfig, ?> createTreeFeature(Random random, boolean bl) {
        return (ConfiguredFeature<TreeFeatureConfig, ?>) BuiltinRegistries.CONFIGURED_FEATURE.get(KEY);
    }
}
