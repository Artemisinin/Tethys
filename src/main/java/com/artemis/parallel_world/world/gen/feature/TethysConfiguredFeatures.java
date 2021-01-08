package com.artemis.parallel_world.world.gen.feature;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;

public class TethysConfiguredFeatures {

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<HugeMushroomFeatureConfig, ?> configuredFeature) {
        return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    public static ConfiguredFeature<?, ?> HUGE_HEATH_MUSHROOM;

    // JSON Features
    // Trees
    public static final RegistryKey<ConfiguredFeature<?, ?>> BASSWOOD_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "basswood_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> BLACKCURRANT_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "blackcurrant_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> CHERRY_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "cherry_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> DOGWOOD_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "dogwood_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> ELDERBERRY_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "elderberry_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> GINKGO_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "ginkgo_tree"));
    public static final RegistryKey<ConfiguredFeature<?, ?>> SWEETGUM_TREE_KEY = RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "sweetgum_tree"));

    // Blockstates for construction
    protected static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
    protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);

    public static void registerConfiguredFeatures() {

        //HUGE_HEATH_MUSHROOM = register("parallel_world:huge_heath_mushroom", TethysFeatures.HUGE_HEATH_MUSHROOM_FEATURE.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(BROWN_MUSHROOM_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 3)));

    }

}
