package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.trunk.ScaleTreeTrunkPlacer;
import com.google.common.collect.ImmutableList;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.decorator.*;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.treedecorator.LeavesVineTreeDecorator;
import net.minecraft.world.gen.trunk.ForkingTrunkPlacer;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


import java.util.List;
import java.util.OptionalInt;


public class TethysConfiguredFeatures extends ConfiguredFeatures {

    // Decorators
    private static BeehiveTreeDecorator BEES_005;

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
    public static ConfiguredFeature<?,?> GHOST_TREE;
    public static ConfiguredFeature<?,?> PORIFERAN;
    public static ConfiguredFeature<?,?> SWAMP_OAK_SHRUB;
    public static ConfiguredFeature<?,?> SWAMP_OAK_SHRUBS;

    // Placed features
    public static PlacedFeature CAVE_SCATTERED_GHOST_TREES;
    public static PlacedFeature TEST_LYCOPOD;
    public static PlacedFeature SCATTERED_PORIFERANS;

    // Tethys tree builders

        private static TreeFeatureConfig.Builder basswood() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.BASSWOOD_LOG.getDefaultState()),
                    new LargeOakTrunkPlacer(3, 11, 0),
                    BlockStateProvider.of(TethysBlocks.BASSWOOD_LEAVES.getDefaultState()),
                    new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder blackcurrant() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.BLACKCURRANT_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.of(TethysBlocks.BLACKCURRANT_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder cherry() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.CHERRY_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 2, 0),
                    BlockStateProvider.of(TethysBlocks.CHERRY_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder dogwood() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.DOGWOOD_LOG.getDefaultState()),
                    new StraightTrunkPlacer(3, 2, 0),
                    BlockStateProvider.of(TethysBlocks.DOGWOOD_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder elderberry() {
            return  (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.ELDERBERRY_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 3, 0),
                    BlockStateProvider.of(TethysBlocks.ELDERBERRY_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder ginkgo() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.GINKGO_LOG.getDefaultState()),
                    new StraightTrunkPlacer(4, 2, 1),
                    BlockStateProvider.of(TethysBlocks.GINKGO_LEAVES.getDefaultState()),
                    new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                    new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
        }

        private static TreeFeatureConfig.Builder sweetgum() {
            return (new TreeFeatureConfig.Builder(
                    BlockStateProvider.of(TethysBlocks.SWEETGUM_LOG.getDefaultState()),
                    new LargeOakTrunkPlacer(3, 11, 0),
                    BlockStateProvider.of(TethysBlocks.SWEETGUM_LEAVES.getDefaultState()),
                    new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                    new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
        }

    // Placement modifier stuffs

    private static List<PlacementModifier> modifiers(PlacementModifier countModifier, PlacementModifier heightModifier) {
        return List.of(countModifier, SquarePlacementModifier.of(), heightModifier, BiomePlacementModifier.of());
    }

    private static List<PlacementModifier> modifiersWithCount(int count, PlacementModifier heightModifier) {
        return modifiers(CountPlacementModifier.of(count), heightModifier);
    }

    private static List<PlacementModifier> modifiersWithCountWaterDepth(int count, PlacementModifier heightModifier, PlacementModifier waterDepth) {
        return modifiers(CountPlacementModifier.of(count), heightModifier, waterDepth);
    }


    public static void registerConfiguredFeatures() {

        // Decorators

        BEES_005 = new BeehiveTreeDecorator(0.05F);

        // Heath shrubs
        BIRCH_SHRUB = ConfiguredFeatures.register("parallel_world:birch_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(1, 0, 1),                
                BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        DARK_OAK_SHRUB_SHORT = ConfiguredFeatures.register("parallel_world:dark_oak_shrub_short", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        DARK_OAK_SHRUB_TALL = ConfiguredFeatures.register("parallel_world:dark_oak_shrub_tall", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        JUNGLE_SHRUB = ConfiguredFeatures.register("parallel_world:jungle_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.JUNGLE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.JUNGLE_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        OAK_SHRUB = ConfiguredFeatures.register("parallel_world:oak_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        SPRUCE_SHRUB_SHORT = ConfiguredFeatures.register("parallel_world:spruce_shrub_short", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.SPRUCE_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).build()));
        SPRUCE_SHRUB_TALL = ConfiguredFeatures.register("parallel_world:spruce_shrub_tall", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                BlockStateProvider.of(Blocks.SPRUCE_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).build()));

        // Rainbow trees

        BASSWOOD_TREE = ConfiguredFeatures.register("parallel_world:basswood_tree", Feature.TREE.configure(basswood().build()));
        BASSWOOD_TREE_BEES = ConfiguredFeatures.register("parallel_world:basswood_tree_bees", Feature.TREE.configure(basswood().
                decorators(ImmutableList.of(BEES_005)).build()));
        BLACKCURRANT_TREE = ConfiguredFeatures.register("parallel_world:blackcurrant_tree", Feature.TREE.configure(blackcurrant().build()));
        BLACKCURRANT_TREE_BEES = ConfiguredFeatures.register("parallel_world:blackcurrant_tree_bees", Feature.TREE.configure(blackcurrant().
                decorators(ImmutableList.of(BEES_005)).build()));
        CHERRY_TREE = ConfiguredFeatures.register("parallel_world:cherry_tree", Feature.TREE.configure(cherry().build()));
        CHERRY_TREE_BEES = ConfiguredFeatures.register("parallel_world:cherry_tree_bees", Feature.TREE.configure(cherry().
                decorators(ImmutableList.of(BEES_005)).build()));
        DOGWOOD_TREE = ConfiguredFeatures.register("parallel_world:dogwood_tree", Feature.TREE.configure(dogwood().build()));
        DOGWOOD_TREE_BEES = ConfiguredFeatures.register("parallel_world:dogwood_tree_bees", Feature.TREE.configure(dogwood().
                decorators(ImmutableList.of(BEES_005)).build()));
        ELDERBERRY_TREE = ConfiguredFeatures.register("parallel_world:elderberry_tree", Feature.TREE.configure(elderberry().build()));
        ELDERBERRY_TREE_BEES = ConfiguredFeatures.register("parallel_world:elderberry_tree_bees", Feature.TREE.configure(elderberry().
                decorators(ImmutableList.of(BEES_005)).build()));
        GINKGO_TREE = ConfiguredFeatures.register("parallel_world:ginkgo_tree", Feature.TREE.configure(ginkgo().build()));
        GINKGO_TREE_BEES = ConfiguredFeatures.register("parallel_world:ginkgo_tree_bees", Feature.TREE.configure(ginkgo().
                decorators(ImmutableList.of(BEES_005)).build()));
        SWEETGUM_TREE = ConfiguredFeatures.register("parallel_world:sweetgum_tree", Feature.TREE.configure(sweetgum().build()));
        SWEETGUM_TREE_BEES = ConfiguredFeatures.register("parallel_world:sweetgum_tree_bees", Feature.TREE.configure(sweetgum().
                decorators(ImmutableList.of(BEES_005)).build()));

        // Misc
        BURNED_TREE = ConfiguredFeatures.register ("parallel_world:burned_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG.getDefaultState()),
                new LargeOakTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(Blocks.AIR.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).build()));
        GHOST_TREE = ConfiguredFeatures.register("parallel_world:ghost_tree", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.POLISHED_BASALT.getDefaultState()),
                new ForkingTrunkPlacer(2,4,4),
                BlockStateProvider.of(TethysBlocks.CAVE_GLOWLEAF.getDefaultState()),
                 //new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1,0,2))).build()));
        CAVE_SCATTERED_GHOST_TREES = PlacedFeatures.register("parallel_world:cave_scattered_ghost_trees", GHOST_TREE.withPlacement(
                modifiersWithCount(20, HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(40)))));

        PORIFERAN = ConfiguredFeatures.register("parallel_world:poriferan",  Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TethysBlocks.PORIFERAN_STEM.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(TethysBlocks.PORIFERAN_CHUNK.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0),ConstantIntProvider.create(4)),
                new TwoLayersFeatureSize(1, 1, 1))).build()));
        SCATTERED_PORIFERANS = PlacedFeatures.register("parallel_world:scattered_poriferans", PORIFERAN.withPlacement(
                SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                CountPlacementModifier.of(20), BiomePlacementModifier.of()));
        SWAMP_OAK_SHRUB = ConfiguredFeatures.register("parallel_world:swamp_oak_shrub", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG.getDefaultState()),
                new StraightTrunkPlacer(2, 1, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(1, 0, 1))).decorators(ImmutableList.of(LeavesVineTreeDecorator.INSTANCE)).build()));
        SWAMP_OAK_SHRUBS = ConfiguredFeatures.register("parallel_world:swamp_oak_shrubs", SWAMP_OAK_SHRUB.decorate(ConfiguredFeatures.Decorators.HEIGHTMAP_OCEAN_FLOOR).
                decorate(TethysDecorators.WATER_MIN_DEPTH_DECORATOR.configure(new WaterMinDepthDecoratorConfig(1))).
                decorate(TethysDecorators.WATER_MAX_DEPTH_DECORATOR.configure(new WaterMaxDepthDecoratorConfig(3))).
                repeat(40));

        TEST_LYCOPOD = PlacedFeatures.register("parallel_world:test_lycopod", Feature.TREE.configure((new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG.getDefaultState()),
                new ScaleTreeTrunkPlacer(6, 3, 3),
                BlockStateProvider.of(Blocks.JUNGLE_LEAVES.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(3)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines().build()).
                withPlacement(SquarePlacementModifier.of(), PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP, CountPlacementModifier.of(20), BiomePlacementModifier.of()));
    }
}
