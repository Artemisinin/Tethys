package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.block.TethysBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
import net.minecraft.world.gen.foliage.BushFoliagePlacer;
import net.minecraft.world.gen.foliage.LargeOakFoliagePlacer;
import net.minecraft.world.gen.placer.SimpleBlockPlacer;
import net.minecraft.world.gen.stateprovider.SimpleBlockStateProvider;
import net.minecraft.world.gen.trunk.LargeOakTrunkPlacer;
import net.minecraft.world.gen.trunk.StraightTrunkPlacer;


import java.util.OptionalInt;

public class TethysConfiguredFeatures {

    private static <FC extends FeatureConfig> ConfiguredFeature<FC, ?> register(String id, ConfiguredFeature<?, ?> configuredFeature)
    {
        return (ConfiguredFeature)Registry.register(BuiltinRegistries.CONFIGURED_FEATURE, id, configuredFeature);
    }

    // Shrubs for vanilla trees
    public static ConfiguredFeature<?,?> BIRCH_SHRUB;
    public static ConfiguredFeature<?,?> DARK_OAK_SHRUB_SHORT;
    public static ConfiguredFeature<?,?> DARK_OAK_SHRUB_TALL;
    public static ConfiguredFeature<?,?> JUNGLE_SHRUB;
    public static ConfiguredFeature<?,?> OAK_SHRUB;
    public static ConfiguredFeature<?,?> SPRUCE_SHRUB_SHORT;
    public static ConfiguredFeature<?,?> SPRUCE_SHRUB_TALL;

    // Trees
    public static ConfiguredFeature<?,?> BASSWOOD_TREE;
    public static ConfiguredFeature<?,?> BLACKCURRANT_TREE;
    public static ConfiguredFeature<?,?> CHERRY_TREE;
    public static ConfiguredFeature<?,?> DOGWOOD_TREE;
    public static ConfiguredFeature<?,?> ELDERBERRY_TREE;
    public static ConfiguredFeature<?,?> GINKGO_TREE;
    public static ConfiguredFeature<?,?> SWEETGUM_TREE;

    public static ConfiguredFeature<?,?> PATCH_BERRY_BUSH_HEATH;
    public static ConfiguredFeature<?,?> PINK_DIAMOND_ORE;

    //public static ConfiguredFeature<?, ?> HUGE_HEATH_MUSHROOM;

    public static void registerConfiguredFeatures() {

        // Heath Shrubs
        BIRCH_SHRUB = register("parallel_world:birch_shrub", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BIRCH_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BIRCH_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 1),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        DARK_OAK_SHRUB_SHORT = register("parallel_world:dark_oak_shrub_short", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DARK_OAK_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DARK_OAK_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 1),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        DARK_OAK_SHRUB_TALL = register("parallel_world:dark_oak_shrub_tall", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DARK_OAK_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DARK_OAK_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 1),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        JUNGLE_SHRUB = register("parallel_world:jungle_shrub", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.JUNGLE_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.JUNGLE_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        OAK_SHRUB = register("parallel_world:oak_shrub", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.OAK_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.OAK_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        SPRUCE_SHRUB_SHORT = register("parallel_world:spruce_shrub_short", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SPRUCE_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SPRUCE_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 1),
                        new StraightTrunkPlacer(1, 0, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        SPRUCE_SHRUB_TALL = register("parallel_world:spruce_shrub_tall", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SPRUCE_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SPRUCE_LEAVES),
                        new BushFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(1), 2),
                        new StraightTrunkPlacer(1, 0, 1),
                        new TwoLayersFeatureSize(0, 0, 0))).heightmap(Heightmap.Type.MOTION_BLOCKING_NO_LEAVES).build()));

        // Trees
        BASSWOOD_TREE = register("parallel_world:basswood_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BASSWOOD_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BASSWOOD_LEAVES),
                        new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4),
                        new LargeOakTrunkPlacer(3, 11, 0),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

        BLACKCURRANT_TREE = register("parallel_world:blackcurrant_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BLACKCURRANT_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.BLACKCURRANT_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(4, 2, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));

        CHERRY_TREE = register("parallel_world:cherry_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.CHERRY_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.CHERRY_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(4, 2, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));

        DOGWOOD_TREE = register("parallel_world:dogwood_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DOGWOOD_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.DOGWOOD_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(3, 0, 5),
                        new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));

        ELDERBERRY_TREE = register("parallel_world:elderberry_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.ELDERBERRY_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.ELDERBERRY_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(4, 2, 0),
                        new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));

        GINKGO_TREE = register("parallel_world:ginkgo_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.GINKGO_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.GINKGO_LEAVES),
                        new BlobFoliagePlacer(UniformIntDistribution.of(3), UniformIntDistribution.of(0), 3),
                        new StraightTrunkPlacer(4, 2, 1),
                        new TwoLayersFeatureSize(0, 0, 0))).ignoreVines().build()));

        SWEETGUM_TREE = register("parallel_world:sweetgum_tree", Feature.TREE.configure(
                (new TreeFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SWEETGUM_LOG),
                        new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SWEETGUM_LEAVES),
                        new LargeOakFoliagePlacer(UniformIntDistribution.of(2), UniformIntDistribution.of(4), 4),
                        new LargeOakTrunkPlacer(3, 11, 0),
                        new TwoLayersFeatureSize(0, 0, 0, OptionalInt.of(4)))).ignoreVines().heightmap(Heightmap.Type.MOTION_BLOCKING).build()));

        PATCH_BERRY_BUSH_HEATH = register("parallel_world:patch_berry_bush_heath", Feature.RANDOM_PATCH.configure(
                (new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(TethysConfiguredFeatures.States.SWEET_BERRY_BUSH), SimpleBlockPlacer.INSTANCE)).cannotProject().build()));

        PINK_DIAMOND_ORE = register("parallel_world:pink_diamond_ore", Feature.ORE.configure(
                new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, TethysBlocks.PINK_DIAMOND_ORE.getDefaultState(), 5)).
                averageDepth(YOffset.getBottom(), 80)).spreadHorizontally().repeat(4);

        // The mushroom right now is configured in json.
        //HUGE_HEATH_MUSHROOM = register("parallel_world:huge_heath_mushroom", TethysFeatures.HUGE_HEATH_MUSHROOM_FEATURE.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(BROWN_MUSHROOM_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 3)));
    }

    public static final class States {

        // Blockstates for construction
        protected static final BlockState BASSWOOD_LEAVES = TethysBlocks.BASSWOOD_LEAVES.getDefaultState();
        protected static final BlockState BASSWOOD_LOG = TethysBlocks.BASSWOOD_LOG.getDefaultState();
        protected static final BlockState BIRCH_LOG = Blocks.BIRCH_LOG.getDefaultState();
        protected static final BlockState BIRCH_LEAVES = Blocks.BIRCH_LEAVES.getDefaultState();
        protected static final BlockState BLACKCURRANT_LEAVES = TethysBlocks.BLACKCURRANT_LEAVES.getDefaultState();
        protected static final BlockState BLACKCURRANT_LOG = TethysBlocks.BLACKCURRANT_LOG.getDefaultState();
        protected static final BlockState CHERRY_LEAVES = TethysBlocks.CHERRY_LEAVES.getDefaultState();
        protected static final BlockState CHERRY_LOG = TethysBlocks.CHERRY_LOG.getDefaultState();
        protected static final BlockState DARK_OAK_LOG = Blocks.DARK_OAK_LOG.getDefaultState();
        protected static final BlockState DARK_OAK_LEAVES = Blocks.DARK_OAK_LEAVES.getDefaultState();
        protected static final BlockState DOGWOOD_LEAVES = TethysBlocks.DOGWOOD_LEAVES.getDefaultState();
        protected static final BlockState DOGWOOD_LOG = TethysBlocks.DOGWOOD_LOG.getDefaultState();
        protected static final BlockState ELDERBERRY_LEAVES = TethysBlocks.ELDERBERRY_LEAVES.getDefaultState();
        protected static final BlockState ELDERBERRY_LOG = TethysBlocks.ELDERBERRY_LOG.getDefaultState();
        protected static final BlockState GINKGO_LEAVES = TethysBlocks.GINKGO_LEAVES.getDefaultState();
        protected static final BlockState GINKGO_LOG = TethysBlocks.GINKGO_LOG.getDefaultState();
        protected static final BlockState GRASS_BLOCK = Blocks.GRASS_BLOCK.getDefaultState();
        protected static final BlockState JUNGLE_LEAVES = Blocks.JUNGLE_LEAVES.getDefaultState();
        protected static final BlockState JUNGLE_LOG = Blocks.JUNGLE_LOG.getDefaultState();
        protected static final BlockState MOSS_BLOCK = Blocks.MOSS_BLOCK.getDefaultState();
        protected static final BlockState OAK_LOG = Blocks.OAK_LOG.getDefaultState();
        protected static final BlockState OAK_LEAVES = Blocks.OAK_LEAVES.getDefaultState();
        protected static final BlockState SPRUCE_LOG = Blocks.SPRUCE_LOG.getDefaultState();
        protected static final BlockState SPRUCE_LEAVES = Blocks.SPRUCE_LEAVES.getDefaultState();
        protected static final BlockState SWEET_BERRY_BUSH = Blocks.SWEET_BERRY_BUSH.getDefaultState();
        protected static final BlockState SWEETGUM_LEAVES = TethysBlocks.SWEETGUM_LEAVES.getDefaultState();
        protected static final BlockState SWEETGUM_LOG = TethysBlocks.SWEETGUM_LOG.getDefaultState();

        //protected static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
        //protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);
    }
}
