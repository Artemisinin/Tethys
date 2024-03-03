package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.world.gen.placementmodifier.WaterMaxDepthFeaturePlacementModifier;
import com.artemis.parallel_world.world.gen.placementmodifier.WaterMinDepthFeaturePlacementModifier;
import com.artemis.parallel_world.world.gen.trunk.ScaleTreeTrunkPlacer;
import net.minecraft.registry.Registerable;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.math.intprovider.ConstantIntProvider;

import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.*;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.BeehiveTreeDecorator;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;

import java.util.OptionalInt;


public class TethysConfiguredFeatures extends ConfiguredFeatures {

    // Decorators
    //private static BeehiveTreeDecorator BEES_005;

    // Tethys shrubs
    public static RegistryKey<ConfiguredFeature<?, ?>> BIRCH_SHRUB = ConfiguredFeatures.of("parallel_world:shrub/shrub_birch");
    public static RegistryKey<ConfiguredFeature<?, ?>> DARK_OAK_SHRUB_SHORT = ConfiguredFeatures.of("parallel_world:shrub/shrub_dark_oak_short");
    public static RegistryKey<ConfiguredFeature<?, ?>> DARK_OAK_SHRUB_TALL = ConfiguredFeatures.of("parallel_world:shrub/shrub_dark_oak_tall");
    public static RegistryKey<ConfiguredFeature<?, ?>> JUNGLE_SHRUB = ConfiguredFeatures.of("parallel_world:shrub/shrub_jungle");
    public static RegistryKey<ConfiguredFeature<?, ?>> OAK_SHRUB = ConfiguredFeatures.of("parallel_world:shrub/shrub_oak");
    public static RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_SHRUB_SHORT = ConfiguredFeatures.of("parallel_world:shrub/shrub_spruce_short");
    public static RegistryKey<ConfiguredFeature<?, ?>> SPRUCE_SHRUB_TALL = ConfiguredFeatures.of("parallel_world:shrub/shrub_spruce_tall");
    //public static RegistryKey<ConfiguredFeature<?, ?>> SWAMP_OAK_SHRUB = ConfiguredFeatures.of("parallel_world:shrub/shrub_swamp_oak");

    // Tethys trees
    public static RegistryKey<ConfiguredFeature<?, ?>> BASSWOOD_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/basswood_tree");
    public static RegistryKey<ConfiguredFeature<?, ?>> BASSWOOD_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/basswood_tree_bees");
    public static RegistryKey<ConfiguredFeature<?,?>> BLACKCURRANT_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/blackcurrant_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> BLACKCURRANT_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/blackcurrant_tree_bees");
    public static RegistryKey<ConfiguredFeature<?,?>> CHERRY_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/cherry_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> CHERRY_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/cherry_tree_bees");
    public static RegistryKey<ConfiguredFeature<?,?>> DOGWOOD_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/dogwood_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> DOGWOOD_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/dogwood_tree_bees");
    public static RegistryKey<ConfiguredFeature<?,?>> ELDERBERRY_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/elderberry_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> ELDERBERRY_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/elderberry_tree_bees");
    public static RegistryKey<ConfiguredFeature<?,?>> GINKGO_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/ginkgo_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> GINKGO_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/ginkgo_tree_bees");
    //public static RegistryKey<ConfiguredFeature<TreeFeatureConfig,?>> LYCOPOD;
    public static RegistryKey<ConfiguredFeature<?,?>> SWEETGUM_TREE = ConfiguredFeatures.of("parallel_world:rainbow_tree/sweetgum_tree");
    public static RegistryKey<ConfiguredFeature<?,?>> SWEETGUM_TREE_BEES = ConfiguredFeatures.of("parallel_world:rainbow_tree/sweetgum_tree_bees");

/*    // Misc
    public static RegistryKey<ConfiguredFeature<TreeFeatureConfig,?>> BURNED_TREE;
    public static RegistryKey<ConfiguredFeature<TreeFeatureConfig,?>> GHOST_TREE;
    public static RegistryKey<ConfiguredFeature<TreeFeatureConfig,?>> PORIFERAN;
    public static RegistryKey<PlacedFeature> SWAMP_OAK_SHRUBS;

    // Placed features
    public static RegistryKey<PlacedFeature> CAVE_SCATTERED_GHOST_TREES;
    public static RegistryKey<PlacedFeature> TEST_LYCOPOD;
    public static RegistryKey<PlacedFeature> SCATTERED_PORIFERANS;
*/

/*
    // Tethys treedecorator builders
    // Shrubs
     private static TreeFeatureConfig.Builder shrub_birch() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.BIRCH_LOG),
                new StraightTrunkPlacer(1, 0, 1),
                BlockStateProvider.of(Blocks.BIRCH_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_dark_oak_short() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_dark_oak_tall() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                new StraightTrunkPlacer(1, 0, 1),
                BlockStateProvider.of(Blocks.DARK_OAK_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_jungle() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.JUNGLE_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.JUNGLE_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_oak() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

   private static TreeFeatureConfig.Builder shrub_spruce_short() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.SPRUCE_LOG),
                new StraightTrunkPlacer(1, 0, 0),
                BlockStateProvider.of(Blocks.SPRUCE_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 1),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_spruce_tall() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.SPRUCE_LOG.getDefaultState()),
                new StraightTrunkPlacer(1, 0, 1),
                BlockStateProvider.of(Blocks.SPRUCE_LEAVES.getDefaultState()),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder shrub_swamp_oak_() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.OAK_LOG),
                new StraightTrunkPlacer(2, 1, 0),
                BlockStateProvider.of(Blocks.OAK_LEAVES),
                new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(1), 2),
                new TwoLayersFeatureSize(1, 0, 1))).decorators(ImmutableList.of(new LeavesVineTreeDecorator(0.25F)));
    }

    // Trees
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

   private static TreeFeatureConfig.Builder burned_tree() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.DARK_OAK_LOG),
                new LargeOakTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(Blocks.AIR),
                new BlobFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4))));
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
/*
    private static TreeFeatureConfig.Builder ghost_tree() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.POLISHED_BASALT),
                new ForkingTrunkPlacer(2,4,4),
                BlockStateProvider.of(TethysBlocks.CAVE_GLOWLEAF.getDefaultState()),
                //new BushFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0), 3),
                new AcaciaFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(0)),
                new TwoLayersFeatureSize(1,0,2)));
    }

    private static TreeFeatureConfig.Builder ginkgo() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TethysBlocks.GINKGO_LOG.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 1),
                BlockStateProvider.of(TethysBlocks.GINKGO_LEAVES.getDefaultState()),
                new BlobFoliagePlacer(ConstantIntProvider.create(3), ConstantIntProvider.create(0), 3),
                new TwoLayersFeatureSize(0, 0, 0))).ignoreVines();
    }

    private static TreeFeatureConfig.Builder lycopod() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(Blocks.ACACIA_LOG.getDefaultState()),
                new ScaleTreeTrunkPlacer(6, 3, 3),
                BlockStateProvider.of(Blocks.JUNGLE_LEAVES.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0), ConstantIntProvider.create(3)), new TwoLayersFeatureSize(1, 0, 2))).ignoreVines();
    }

   private static TreeFeatureConfig.Builder sweetgum() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TethysBlocks.SWEETGUM_LOG.getDefaultState()),
                new LargeOakTrunkPlacer(3, 11, 0),
                BlockStateProvider.of(TethysBlocks.SWEETGUM_LEAVES.getDefaultState()),
                new LargeOakFoliagePlacer(ConstantIntProvider.create(2), ConstantIntProvider.create(4), 4),
                new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines();
    }
    // Misc


    private static TreeFeatureConfig.Builder poriferan() {
        return (new TreeFeatureConfig.Builder(
                BlockStateProvider.of(TethysBlocks.PORIFERAN_STEM.getDefaultState()),
                new StraightTrunkPlacer(4, 2, 0),
                BlockStateProvider.of(TethysBlocks.PORIFERAN_CHUNK.getDefaultState()),
                new PineFoliagePlacer(ConstantIntProvider.create(1), ConstantIntProvider.create(0),ConstantIntProvider.create(4)),
                new TwoLayersFeatureSize(1, 1, 1)));
    }
*/

    // Configured Features
    public static void registerConfiguredFeatures(Registerable<ConfiguredFeature<?, ?>> featureRegisterable) {
/*
        // Decorators

        BEES_005 = new BeehiveTreeDecorator(0.05F);


        // Tethys shrubs
        BIRCH_SHRUB = ConfiguredFeatures.register("parallel_world:birch_shrub", Feature.TREE, shrub_birch().build());
        DARK_OAK_SHRUB_SHORT = ConfiguredFeatures.register("parallel_world:dark_oak_shrub_short", Feature.TREE, shrub_dark_oak_short().build());
        DARK_OAK_SHRUB_TALL = ConfiguredFeatures.register("parallel_world:dark_oak_shrub_tall", Feature.TREE, shrub_dark_oak_tall().build());
        JUNGLE_SHRUB = ConfiguredFeatures.register("parallel_world:jungle_shrub", Feature.TREE, shrub_jungle().build());
        OAK_SHRUB = ConfiguredFeatures.register("parallel_world:oak_shrub", Feature.TREE, shrub_oak().build());
        SPRUCE_SHRUB_SHORT = ConfiguredFeatures.register("parallel_world:spruce_shrub_short", Feature.TREE, shrub_spruce_short().build());
        SPRUCE_SHRUB_TALL = ConfiguredFeatures.register("parallel_world:spruce_shrub_tall", Feature.TREE, shrub_spruce_tall().build());
        SWAMP_OAK_SHRUB = ConfiguredFeatures.register("parallel_world:swamp_oak_shrub", Feature.TREE, shrub_swamp_oak_().build());


       // Tethys trees
        BASSWOOD_TREE = ConfiguredFeatures.register("parallel_world:basswood_tree", Feature.TREE, basswood().build());
        BASSWOOD_TREE_BEES = ConfiguredFeatures.register("parallel_world:basswood_tree_bees", Feature.TREE, basswood().decorators(List.of(BEES_005)).build());
        BLACKCURRANT_TREE = ConfiguredFeatures.register("parallel_world:blackcurrant_tree", Feature.TREE, blackcurrant().build());
        BLACKCURRANT_TREE_BEES = ConfiguredFeatures.register("parallel_world:blackcurrant_tree_bees", Feature.TREE, blackcurrant().decorators(ImmutableList.of(BEES_005)).build());
        CHERRY_TREE = ConfiguredFeatures.register("parallel_world:cherry_tree", Feature.TREE, cherry().build());
        CHERRY_TREE_BEES = ConfiguredFeatures.register("parallel_world:cherry_tree_bees", Feature.TREE, cherry().decorators(ImmutableList.of(BEES_005)).build());
        DOGWOOD_TREE = ConfiguredFeatures.register("parallel_world:dogwood_tree", Feature.TREE, dogwood().build());
        DOGWOOD_TREE_BEES = ConfiguredFeatures.register("parallel_world:dogwood_tree_bees", Feature.TREE, dogwood().decorators(ImmutableList.of(BEES_005)).build());
        ELDERBERRY_TREE = ConfiguredFeatures.register("parallel_world:elderberry_tree", Feature.TREE, elderberry().build());
        ELDERBERRY_TREE_BEES = ConfiguredFeatures.register("parallel_world:elderberry_tree_bees", Feature.TREE, elderberry().decorators(ImmutableList.of(BEES_005)).build());
        GINKGO_TREE = ConfiguredFeatures.register("parallel_world:ginkgo_tree", Feature.TREE, ginkgo().build());
        GINKGO_TREE_BEES = ConfiguredFeatures.register("parallel_world:ginkgo_tree_bees", Feature.TREE, ginkgo().decorators(ImmutableList.of(BEES_005)).build());
        SWEETGUM_TREE = ConfiguredFeatures.register("parallel_world:sweetgum_tree", Feature.TREE, sweetgum().build());
        SWEETGUM_TREE_BEES = ConfiguredFeatures.register("parallel_world:sweetgum_tree_bees", Feature.TREE, sweetgum().decorators(ImmutableList.of(BEES_005)).build());

/*
        // Misc
        BURNED_TREE = ConfiguredFeatures.register("parallel_world:burned_tree", Feature.TREE, burned_tree().build());
        GHOST_TREE = ConfiguredFeatures.register("parallel_world:ghost_tree", Feature.TREE, ghost_tree().build());
        LYCOPOD = ConfiguredFeatures.register("parallel_world:test_lycopod", Feature.TREE, lycopod().build());
        PORIFERAN = ConfiguredFeatures.register("parallel_world:poriferan", Feature.TREE, poriferan().build());
*/
    }

    // Placed Features
    public static void registerPlacedFeatures() {
/*        Not added yet to json, just getting rid of errors.

CAVE_SCATTERED_GHOST_TREES = PlacedFeatures.register("parallel_world:cave_scattered_ghost_trees", GHOST_TREE,
                CountPlacementModifier.of(20),
                HeightRangePlacementModifier.uniform(YOffset.getBottom(), YOffset.fixed(40)));
        SCATTERED_PORIFERANS = PlacedFeatures.register("parallel_world:scattered_poriferans", PORIFERAN,
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                SquarePlacementModifier.of(),
                CountPlacementModifier.of(20),
                BiomePlacementModifier.of());
        SWAMP_OAK_SHRUBS = PlacedFeatures.register("parallel_world:swamp_oak_shrubs", SWAMP_OAK_SHRUB,
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                new WaterMinDepthFeaturePlacementModifier(1),
                new WaterMaxDepthFeaturePlacementModifier(3),
                CountPlacementModifier.of(40));
        TEST_LYCOPOD = PlacedFeatures.register("parallel_world:test_lycopod", LYCOPOD,
                SquarePlacementModifier.of(),
                PlacedFeatures.OCEAN_FLOOR_HEIGHTMAP,
                CountPlacementModifier.of(20),
                BiomePlacementModifier.of());*/
    }
}