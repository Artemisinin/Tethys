package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.block.TethysBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.MushroomBlock;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.Heightmap;
import net.minecraft.world.gen.UniformIntDistribution;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.RangeDecoratorConfig;
import net.minecraft.world.gen.feature.*;
import net.minecraft.world.gen.feature.size.TwoLayersFeatureSize;
import net.minecraft.world.gen.foliage.BlobFoliagePlacer;
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

    // Trees
    public static ConfiguredFeature<?,?> BASSWOOD_TREE;
    public static ConfiguredFeature<?,?> BLACKCURRANT_TREE;
    public static ConfiguredFeature<?,?> CHERRY_TREE;
    public static ConfiguredFeature<?,?> DOGWOOD_TREE;
    public static ConfiguredFeature<?,?> ELDERBERRY_TREE;
    public static ConfiguredFeature<?,?> GINKGO_TREE;
    public static ConfiguredFeature<?,?> SWEETGUM_TREE;

    public static ConfiguredFeature<?,?> PINK_DIAMOND_ORE;

    public static ConfiguredFeature<?,?> TEST_CHERT;

    //public static ConfiguredFeature<?, ?> HUGE_HEATH_MUSHROOM;

    public static void registerConfiguredFeatures() {

        TEST_CHERT = register("chert_block", Feature.FLOWER.configure((new RandomPatchFeatureConfig.Builder(new SimpleBlockStateProvider(TethysBlocks.PINK_DIAMOND_ORE.getDefaultState()),
                SimpleBlockPlacer.INSTANCE)).tries(64).build()));

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

        PINK_DIAMOND_ORE = register("parallel_world:pink_diamond_ore", Feature.ORE.configure(
                new OreFeatureConfig(OreFeatureConfig.Rules.BASE_STONE_OVERWORLD, TethysBlocks.PINK_DIAMOND_ORE.getDefaultState(), 5)).
                decorate(Decorator.RANGE.configure(new RangeDecoratorConfig(0,30, 50))).spreadHorizontally().repeat(2));

        // The mushroom right now is configured in json.
        //HUGE_HEATH_MUSHROOM = register("parallel_world:huge_heath_mushroom", TethysFeatures.HUGE_HEATH_MUSHROOM_FEATURE.configure(new HugeMushroomFeatureConfig(new SimpleBlockStateProvider(BROWN_MUSHROOM_BLOCK), new SimpleBlockStateProvider(MUSHROOM_STEM), 3)));
    }

    public static final class States {

        // Blockstates for construction
        protected static final BlockState BASSWOOD_LEAVES = TethysBlocks.BASSWOOD_LEAVES.getDefaultState();
        protected static final BlockState BASSWOOD_LOG = TethysBlocks.BASSWOOD_LOG.getDefaultState();
        protected static final BlockState BLACKCURRANT_LEAVES = TethysBlocks.BLACKCURRANT_LEAVES.getDefaultState();
        protected static final BlockState BLACKCURRANT_LOG = TethysBlocks.BLACKCURRANT_LOG.getDefaultState();
        protected static final BlockState CHERRY_LEAVES = TethysBlocks.CHERRY_LEAVES.getDefaultState();
        protected static final BlockState CHERRY_LOG = TethysBlocks.CHERRY_LOG.getDefaultState();
        protected static final BlockState DOGWOOD_LEAVES = TethysBlocks.DOGWOOD_LEAVES.getDefaultState();
        protected static final BlockState DOGWOOD_LOG = TethysBlocks.DOGWOOD_LOG.getDefaultState();
        protected static final BlockState ELDERBERRY_LEAVES = TethysBlocks.ELDERBERRY_LEAVES.getDefaultState();
        protected static final BlockState ELDERBERRY_LOG = TethysBlocks.ELDERBERRY_LOG.getDefaultState();
        protected static final BlockState GINKGO_LEAVES = TethysBlocks.GINKGO_LEAVES.getDefaultState();
        protected static final BlockState GINKGO_LOG = TethysBlocks.GINKGO_LOG.getDefaultState();
        protected static final BlockState SWEETGUM_LEAVES = TethysBlocks.SWEETGUM_LEAVES.getDefaultState();
        protected static final BlockState SWEETGUM_LOG = TethysBlocks.SWEETGUM_LOG.getDefaultState();

        //protected static final BlockState BROWN_MUSHROOM_BLOCK = Blocks.BROWN_MUSHROOM_BLOCK.getDefaultState().with(MushroomBlock.UP, true).with(MushroomBlock.DOWN, false);
        //protected static final BlockState MUSHROOM_STEM = Blocks.MUSHROOM_STEM.getDefaultState().with(MushroomBlock.UP, false).with(MushroomBlock.DOWN, false);
    }
}
