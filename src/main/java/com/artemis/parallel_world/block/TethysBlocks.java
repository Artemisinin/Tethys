package com.artemis.parallel_world.block;

import com.artemis.parallel_world.block.sapling.*;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.minecraft.block.*;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;


public class TethysBlocks {

    public static final Block CALCITE_LOG = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(2.0f));
    public static final Block CAVE_GLOWLEAF = new Block(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().luminance(6));
    public static final Block BLUE_CALCITE_LOG = new PillarBlock(FabricBlockSettings.of(Material.STONE).hardness(2.0f));
    public static final Block GLOWFRUIT = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque().luminance(9).hardness(0.2f).sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block GLOW_FLOWER = new FlowerBlock(StatusEffects.NIGHT_VISION, 5, AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().nonOpaque().luminance(state -> 9).sounds(BlockSoundGroup.GRASS));
    public static final Block HEATHER = new FlowerBlock(StatusEffects.LUCK, 7, AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().nonOpaque().sounds(BlockSoundGroup.GRASS));
    public static final Block MANGROVE_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).nonOpaque().hardness(0.2f).sounds(BlockSoundGroup.GRASS).ticksRandomly());

    // Marsh grass blocks
    public static final Block MARSH_GRASS_RED = new TallPlantBlock(AbstractBlock.Settings.of(Material.REPLACEABLE_PLANT).noCollision().breakInstantly().sounds(BlockSoundGroup.GRASS).offsetType(AbstractBlock.OffsetType.XZ));

    // Mushroom blocks
    public static final Block PINK_MUSHROOM_BLOCK = new MushroomBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.DULL_PINK).strength(0.2F).sounds(BlockSoundGroup.WOOD));
    public static final Block WHITE_MUSHROOM_BLOCK = new MushroomBlock(AbstractBlock.Settings.of(Material.WOOD, MapColor.OFF_WHITE).strength(0.2F).sounds(BlockSoundGroup.WOOD));

    // Ores
    public static final Block COPPER_ORE_ANDESITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE).strength(3.0F, 3.0F));
    public static final Block COPPER_ORE_DIORITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE).strength(3.0F, 3.0F));
    public static final Block COPPER_ORE_GRANITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.COPPER_ORE).strength(3.0F, 3.0F));
    public static final Block GOLD_ORE_ANDESITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE).strength(3.0F, 3.0F));
    public static final Block GOLD_ORE_DIORITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE).requiresTool().strength(3.0F, 3.0F));
    public static final Block GOLD_ORE_GRANITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.GOLD_ORE).requiresTool().strength(3.0F, 3.0F));
    public static final Block IRON_ORE_ANDESITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE).strength(3.0F, 3.0F));
    public static final Block IRON_ORE_DIORITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE).strength(3.0F, 3.0F));
    public static final Block IRON_ORE_GRANITE = new ExperienceDroppingBlock(AbstractBlock.Settings.copy(Blocks.IRON_ORE).strength(3.0F, 3.0F));

    // Pink diamonds!
    public static final Block PINK_DIAMOND_BLOCK = new Block(FabricBlockSettings.of(Material.METAL).hardness(5.0f));
    public static final Block PINK_DIAMOND_ORE = new ExperienceDroppingBlock(AbstractBlock.Settings.of(Material.METAL).requiresTool().strength(3.0F, 3.0F), UniformIntProvider.create(3, 7));

    // Poriferans
    public static final Block PORIFERAN_CHUNK = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.5f).requiresTool().strength(0.8F));
    public static final Block PORIFERAN_CHUNK_GLOW = new Block(FabricBlockSettings.of(Material.STONE).hardness(0.5f).requiresTool().luminance(5).strength(0.8F));
    public static final Block PORIFERAN_STEM = new PillarBlock(FabricBlockSettings.of(Material.WOOD).strength(0.2F).sounds(BlockSoundGroup.WOOD));

    // Tethys turtle eggs
    public static final Block TETHYS_TURTLE_EGG = new TethysTurtleEggBlock((AbstractBlock.Settings.of(Material.EGG).strength(0.5F).sounds(BlockSoundGroup.METAL).ticksRandomly().nonOpaque()));

    // Trees
        // Basswood trees
    public static final Block BASSWOOD_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block BASSWOOD_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block BASSWOOD_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock BASSWOOD_SAPLING = new SaplingBlock(new BasswoodSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Blackcurrant trees
    public static final Block BLACKCURRANT_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block BLACKCURRANT_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block BLACKCURRANT_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock BLACKCURRANT_SAPLING = new SaplingBlock(new BlackcurrantSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Cherry trees
    public static final Block CHERRY_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block CHERRY_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block CHERRY_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock CHERRY_SAPLING = new SaplingBlock(new CherrySaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Dogwood trees
    public static final Block DOGWOOD_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block DOGWOOD_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block DOGWOOD_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock DOGWOOD_SAPLING = new SaplingBlock(new DogwoodSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Elderberry trees
    public static final Block ELDERBERRY_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block ELDERBERRY_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block ELDERBERRY_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock ELDERBERRY_SAPLING = new SaplingBlock(new ElderberrySaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Ginkgo trees
    public static final Block GINKGO_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block GINKGO_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block GINKGO_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock GINKGO_SAPLING = new SaplingBlock(new GinkgoSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());

        // Sweetgum trees
    public static final Block SWEETGUM_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final Block SWEETGUM_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).hardness(0.2f).nonOpaque().sounds(BlockSoundGroup.GRASS).ticksRandomly());
    public static final Block SWEETGUM_LOG = new PillarBlock(FabricBlockSettings.of(Material.WOOD).hardness(2.0f));
    public static final SaplingBlock SWEETGUM_SAPLING = new SaplingBlock(new SweetgumSaplingGenerator(), AbstractBlock.Settings.of(Material.PLANT).ticksRandomly().breakInstantly().sounds(BlockSoundGroup.GRASS).nonOpaque().noCollision());


    // Block Tags
    public static TagKey<Block> CORAL_BLOCKS;
    public static TagKey<Block> DIRT_OR_SAND;
    public static TagKey<Block> DIRT_OR_STONE;

    public static void registerBlocks() {

        // Cave glowleaf
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "cave_glowleaf"), CAVE_GLOWLEAF);

        // Crystal forest calcite logs
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "calcite_log"), CALCITE_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "blue_calcite_log"), BLUE_CALCITE_LOG);

        // Glowfruit
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "glowfruit"), GLOWFRUIT);

        // Glow Flower
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "glow_flower"), GLOW_FLOWER);

        // Heather
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "heather"), HEATHER);

        // Mangrove leaves
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "mangrove_leaves"), MANGROVE_LEAVES);

        // Marsh grass blocks
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "marsh_grass_red"), MARSH_GRASS_RED);

        // Mushroom blocks
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "pink_mushroom_block"), PINK_MUSHROOM_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "white_mushroom_block"), WHITE_MUSHROOM_BLOCK);

        // Ores
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "copper_ore_andesite"), COPPER_ORE_ANDESITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "copper_ore_diorite"), COPPER_ORE_DIORITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "copper_ore_granite"), COPPER_ORE_GRANITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "gold_ore_andesite"), GOLD_ORE_ANDESITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "gold_ore_diorite"), GOLD_ORE_DIORITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "gold_ore_granite"), GOLD_ORE_GRANITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "iron_ore_andesite"), IRON_ORE_ANDESITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "iron_ore_diorite"), IRON_ORE_DIORITE);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "iron_ore_granite"), IRON_ORE_GRANITE);

        // Pink diamonds!
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "pink_diamond_block"), PINK_DIAMOND_BLOCK);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "pink_diamond_ore"), PINK_DIAMOND_ORE);

        // Poriferans
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "poriferan_chunk"), PORIFERAN_CHUNK);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "poriferan_chunk_glow"), PORIFERAN_CHUNK_GLOW);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "poriferan_stem"), PORIFERAN_STEM);

        // Tethys turtle eggs
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "tethys_turtle_egg"), TETHYS_TURTLE_EGG);

        // Tethys trees
            // Basswood trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "basswood_log"), BASSWOOD_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "basswood_planks"), BASSWOOD_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "basswood_leaves"), BASSWOOD_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "basswood_sapling"), BASSWOOD_SAPLING);

            // Blackcurrant trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "blackcurrant_log"), BLACKCURRANT_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "blackcurrant_planks"), BLACKCURRANT_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "blackcurrant_leaves"), BLACKCURRANT_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "blackcurrant_sapling"), BLACKCURRANT_SAPLING);

            // Cherry trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "cherry_log"), CHERRY_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "cherry_planks"), CHERRY_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "cherry_leaves"), CHERRY_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "cherry_sapling"), CHERRY_SAPLING);

            // Dogwood trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "dogwood_log"), DOGWOOD_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "dogwood_planks"), DOGWOOD_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "dogwood_leaves"), DOGWOOD_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "dogwood_sapling"), DOGWOOD_SAPLING);

            // Elderberry trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "elderberry_log"), ELDERBERRY_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "elderberry_planks"), ELDERBERRY_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "elderberry_leaves"), ELDERBERRY_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "elderberry_sapling"), ELDERBERRY_SAPLING);

            // Ginkgo trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "ginkgo_log"), GINKGO_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "ginkgo_planks"), GINKGO_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "ginkgo_leaves"), GINKGO_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "ginkgo_sapling"), GINKGO_SAPLING);

            // Sweetgum trees
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "sweetgum_log"), SWEETGUM_LOG);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "sweetgum_planks"), SWEETGUM_PLANKS);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "sweetgum_leaves"), SWEETGUM_LEAVES);
        Registry.register(Registries.BLOCK, new Identifier("parallel_world", "sweetgum_sapling"), SWEETGUM_SAPLING);
    }

    public static void registerFlammability(){

        FlammableBlockRegistry.getDefaultInstance().add(GLOWFRUIT, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(MANGROVE_LEAVES, 60, 30);
        // Basswood trees
        FlammableBlockRegistry.getDefaultInstance().add(BASSWOOD_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(BASSWOOD_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(BASSWOOD_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(BASSWOOD_SAPLING, 100, 60);
        // Blackcurrant trees
        FlammableBlockRegistry.getDefaultInstance().add(BLACKCURRANT_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(BLACKCURRANT_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(BLACKCURRANT_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(BLACKCURRANT_SAPLING, 100, 60);
        // Cherry trees
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(CHERRY_SAPLING, 100, 60);
        // Dogwood trees
        FlammableBlockRegistry.getDefaultInstance().add(DOGWOOD_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(DOGWOOD_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(DOGWOOD_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(DOGWOOD_SAPLING, 100, 60);
        // Elderberry trees
        FlammableBlockRegistry.getDefaultInstance().add(ELDERBERRY_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERBERRY_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERBERRY_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ELDERBERRY_SAPLING, 100, 60);
        // Ginkgo trees
        FlammableBlockRegistry.getDefaultInstance().add(GINKGO_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(GINKGO_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(GINKGO_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(GINKGO_SAPLING, 100, 60);
        // Sweetgum trees
        FlammableBlockRegistry.getDefaultInstance().add(SWEETGUM_LOG, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(SWEETGUM_LEAVES, 60, 30);
        FlammableBlockRegistry.getDefaultInstance().add(SWEETGUM_PLANKS, 20, 5);
        FlammableBlockRegistry.getDefaultInstance().add(SWEETGUM_SAPLING, 100, 60);

    }

    public static void registerBlockTags() {

        CORAL_BLOCKS = TagKey.of(RegistryKeys.BLOCK, new Identifier("parallel_world", "coral_blocks"));
        DIRT_OR_SAND = TagKey.of(RegistryKeys.BLOCK, new Identifier("parallel_world", "dirt_or_sand"));
        DIRT_OR_STONE = TagKey.of(RegistryKeys.BLOCK, new Identifier("parallel_world", "dirt_or_stone"));
    }
}
