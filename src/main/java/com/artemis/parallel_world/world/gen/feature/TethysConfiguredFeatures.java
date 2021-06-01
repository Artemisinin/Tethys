package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.Dimension;
import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.trunk.HugeTreeTrunkPlacer;
import com.artemis.parallel_world.world.gen.trunk.ScaleTreeTrunkPlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.VerticalSurfaceType;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.CaveSurfaceDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.ThreeLayersFeatureSize;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


import java.util.OptionalInt;
import java.util.Random;

public class TethysConfiguredFeatures extends ConfiguredFeatures {

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<?, ?> configuredFeature)
    {
        return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    // Heath shrubs
    public static ConfiguredFeature<?,?> BIRCH_SHRUB;
    public static ConfiguredFeature<?,?> DARK_OAK_SHRUB_SHORT;
    public static ConfiguredFeature<?,?> DARK_OAK_SHRUB_TALL;
    public static ConfiguredFeature<?,?> JUNGLE_SHRUB;
    public static ConfiguredFeature<?,?> OAK_SHRUB;
    public static ConfiguredFeature<?,?> SPRUCE_SHRUB_SHORT;
    public static ConfiguredFeature<?,?> SPRUCE_SHRUB_TALL;

    // Rainbow trees
    public static ConfiguredFeature<?,?> BASSWOOD_TREE;
    public static ConfiguredFeature<?,?> BASSWOOD_TREE_BEES;
    public static ConfiguredFeature<?,?> BLACKCURRANT_TREE;
    public static ConfiguredFeature<?,?> BLACKCURRANT_TREE_BEES;
    public static ConfiguredFeature<?,?> CHERRY_TREE;
    public static ConfiguredFeature<?,?> CHERRY_TREE_BEES;
    public static ConfiguredFeature<?,?> DOGWOOD_TREE;
    public static ConfiguredFeature<?,?> DOGWOOD_TREE_BEES;
    public static ConfiguredFeature<?,?> ELDERBERRY_TREE;
    public static ConfiguredFeature<?,?> ELDERBERRY_TREE_BEES;
    public static ConfiguredFeature<?,?> GINKGO_TREE;
    public static ConfiguredFeature<?,?> GINKGO_TREE_BEES;
    public static ConfiguredFeature<?,?> SWEETGUM_TREE;
    public static ConfiguredFeature<?,?> SWEETGUM_TREE_BEES;

    // Misc
    public static ConfiguredFeature<?,?> BURNED_TREE;
    public static ConfiguredFeature<?,?> CAVE_SCATTERED_GHOST_TREES;
    public static ConfiguredFeature<?,?> GHOST_TREE;
    public static ConfiguredFeature<?,?> PORIFERAN;
    public static ConfiguredFeature<?,?> SCATTERED_PORIFERANS;
    public static ConfiguredFeature<?,?> SWAMP_OAK_SHRUB;
    public static ConfiguredFeature<?,?> SWAMP_OAK_SHRUBS;

    public static ConfiguredFeature<?,?> TEST_LYCOPOD;


    public static void registerConfiguredFeatures() {

        // Heath shrubs
        BIRCH_SHRUB = register("parallel_world:birch_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.BIRCH_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                new SimpleBlockStateProvider(Blocks.BIRCH_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.BIRCH_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        DARK_OAK_SHRUB_SHORT = register("parallel_world:dark_oak_shrub_short", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.OAK_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        DARK_OAK_SHRUB_TALL = register("parallel_world:dark_oak_shrub_tall", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                new SimpleBlockStateProvider(Blocks.DARK_OAK_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.DARK_OAK_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        JUNGLE_SHRUB = register("parallel_world:jungle_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.JUNGLE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                new SimpleBlockStateProvider(Blocks.JUNGLE_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.JUNGLE_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        OAK_SHRUB = register("parallel_world:oak_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.OAK_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        SPRUCE_SHRUB_SHORT = register("parallel_world:spruce_shrub_short", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.SPRUCE_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        SPRUCE_SHRUB_TALL = register("parallel_world:spruce_shrub_tall", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.SPRUCE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                new SimpleBlockStateProvider(Blocks.SPRUCE_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.SPRUCE_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));

        // Rainbow trees
        BASSWOOD_TREE = register("parallel_world:basswood_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.BASSWOOD_LOG.getDefaultState()),
                new LargeOakTrunkPlacer(3, 11, 0),
                new SimpleBlockStateProvider(TethysBlocks.BASSWOOD_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.BASSWOOD_SAPLING.getDefaultState()),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build()));
        BASSWOOD_TREE_BEES = register("parallel_world:basswood_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)BASSWOOD_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        BLACKCURRANT_TREE = register("parallel_world:blackcurrant_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.BLACKCURRANT_LOG.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 0),
                new SimpleBlockStateProvider(TethysBlocks.BLACKCURRANT_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.BLACKCURRANT_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));
        BLACKCURRANT_TREE_BEES = register("parallel_world:blackcurrant_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)BLACKCURRANT_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        CHERRY_TREE = register("parallel_world:cherry_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.CHERRY_LOG.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 0),
                new SimpleBlockStateProvider(TethysBlocks.CHERRY_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.CHERRY_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));
        CHERRY_TREE_BEES = register("parallel_world:cherry_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)CHERRY_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        DOGWOOD_TREE = register("parallel_world:dogwood_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.DOGWOOD_LOG.getDefaultState()),
                new StraightTrunkPlacer(3, 2, 0),
                new SimpleBlockStateProvider(TethysBlocks.DOGWOOD_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.DOGWOOD_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));
        DOGWOOD_TREE_BEES = register("parallel_world:dogwood_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)DOGWOOD_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        ELDERBERRY_TREE = register("parallel_world:elderberry_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.ELDERBERRY_LOG.getDefaultState()),
                new StraightTrunkPlacer(4, 3, 0),
                new SimpleBlockStateProvider(TethysBlocks.ELDERBERRY_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.ELDERBERRY_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));
        ELDERBERRY_TREE_BEES = register("parallel_world:elderberry_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)ELDERBERRY_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        GINKGO_TREE = register("parallel_world:ginkgo_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.GINKGO_LOG.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 1),
                new SimpleBlockStateProvider(TethysBlocks.GINKGO_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.GINKGO_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));
        GINKGO_TREE_BEES = register("parallel_world:ginkgo_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)GINKGO_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));
        SWEETGUM_TREE = register("parallel_world:sweetgum_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.SWEETGUM_LOG.getDefaultState()),
                new LargeOakTrunkPlacer(3, 11, 0),
                new SimpleBlockStateProvider(TethysBlocks.SWEETGUM_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.SWEETGUM_SAPLING.getDefaultState()),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().build()));
        SWEETGUM_TREE_BEES = register("parallel_world:sweetgum_tree_bees", Feature.TREE.configure(((TreeFeatureConfig)SWEETGUM_TREE.getConfig()).
                setTreeDecorators(ImmutableList.of(ConfiguredFeatures.Decorators.REGULAR_BEEHIVES_TREES))));

        // Misc
        BURNED_TREE = register ("parallel_world:burned_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.DARK_OAK_LOG.getDefaultState()),
                new LargeOakTrunkPlacer(3, 11, 0),
                new SimpleBlockStateProvider(Blocks.AIR.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.OAK_SAPLING.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).build()));
        GHOST_TREE = register("parallel_world:ghost_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.POLISHED_BASALT.getDefaultState()),
                new ForkingTrunkPlacer(2,4,4),
                new SimpleBlockStateProvider(TethysBlocks.CAVE_GLOWLEAF.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.STONY_GROUND_SAPLING_PROVIDER.getDefaultState()),
                //new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1,0,2))).build()));
        CAVE_SCATTERED_GHOST_TREES = register("parallel_world:cave_scattered_ghost_trees", GHOST_TREE.decorate(Decorator.CAVE_SURFACE.configure(
                new CaveSurfaceDecoratorConfig(VerticalSurfaceType.FLOOR, 12))).
                range(new RangeDecoratorConfig(UniformHeightProvider.create(YOffset.getBottom(), YOffset.fixed(40)))).
                decorate(TethysDecorators.WATER_MAX_DEPTH_DECORATOR.configure(new WaterMaxDepthDecoratorConfig(0))).spreadHorizontally().repeat(20));
        PORIFERAN = register("parallel_world:poriferan",  Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(TethysBlocks.PORIFERAN_STEM.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 0),
                new SimpleBlockStateProvider(TethysBlocks.PORIFERAN_CHUNK.getDefaultState()),
                new SimpleBlockStateProvider(TethysBlocks.GRAVEL_GROUND_SAPLING_PROVIDER.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0),ConstantIntProvider.create(4)),
                new TwoLayersFeatureSize(1, 1, 1))).build()));
        SCATTERED_PORIFERANS = register("parallel_world:scattered_poriferans", PORIFERAN.decorate(ConfiguredFeatures.Decorators.SQUARE_TOP_SOLID_HEIGHTMAP).
                decorate(TethysDecorators.WATER_MIN_DEPTH_DECORATOR.configure(new WaterMinDepthDecoratorConfig(10))).
                decorate(TethysDecorators.WATER_MAX_DEPTH_DECORATOR.configure(new WaterMaxDepthDecoratorConfig(50))).repeat(120));
        SWAMP_OAK_SHRUB = register("parallel_world:swamp_oak_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(2, 1, 0),
                new SimpleBlockStateProvider(Blocks.OAK_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.OAK_SAPLING.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(1, 0, 1))).decorators(ImmutableList.of(LeavesVineTreeDecorator.INSTANCE)).build()));
        SWAMP_OAK_SHRUBS = register("parallel_world:swamp_oak_shrubs", SWAMP_OAK_SHRUB.decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_OCEAN_FLOOR).
                decorate(TethysDecorators.WATER_MIN_DEPTH_DECORATOR.configure(new WaterMinDepthDecoratorConfig(1))).
                decorate(TethysDecorators.WATER_MAX_DEPTH_DECORATOR.configure(new WaterMaxDepthDecoratorConfig(3))).
                repeat(40));

        TEST_LYCOPOD = register("parallel_world:test_lycopod", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                new SimpleBlockStateProvider(Blocks.ACACIA_LOG.getDefaultState()),
                new ScaleTreeTrunkPlacer(6, 3, 3),
                new SimpleBlockStateProvider(Blocks.JUNGLE_LEAVES.getDefaultState()),
                new SimpleBlockStateProvider(Blocks.OAK_SAPLING.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(3)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()).
                decorate(ConfiguredFeatures.Decorators.SQUARE_HEIGHTMAP_OCEAN_FLOOR_NO_WATER));
    }
}
