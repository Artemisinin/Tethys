package com.artemis.parallel_world.item;

import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.artemis.parallel_world.block.TethysBlocks.*;

public class TethysItems {

    public static final Item BASSWOOD_LEAVES = new BlockItem(TethysBlocks.BASSWOOD_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item BASSWOOD_LOG = new BlockItem(TethysBlocks.BASSWOOD_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BASSWOOD_PLANKS = new BlockItem(TethysBlocks.BASSWOOD_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BASSWOOD_SAPLING = new BlockItem(TethysBlocks.BASSWOOD_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item BLACKCURRANT_LEAVES = new BlockItem(TethysBlocks.BLACKCURRANT_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item BLACKCURRANT_LOG = new BlockItem(TethysBlocks.BLACKCURRANT_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BLACKCURRANT_PLANKS = new BlockItem(TethysBlocks.BLACKCURRANT_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item BLACKCURRANT_SAPLING = new BlockItem(TethysBlocks.BLACKCURRANT_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item BLUE_CALCITE_LOG = new BlockItem(TethysBlocks.BLUE_CALCITE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CALCITE_LOG = new BlockItem(TethysBlocks.CALCITE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CAVE_GLOWLEAF = new BlockItem(TethysBlocks.CAVE_GLOWLEAF, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item CHERRY_LEAVES = new BlockItem(TethysBlocks.CHERRY_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item CHERRY_LOG = new BlockItem(TethysBlocks.CHERRY_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CHERRY_PLANKS = new BlockItem(TethysBlocks.CHERRY_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item CHERRY_SAPLING = new BlockItem(TethysBlocks.CHERRY_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item DOGWOOD_LEAVES = new BlockItem(TethysBlocks.DOGWOOD_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item DOGWOOD_LOG = new BlockItem(TethysBlocks.DOGWOOD_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item DOGWOOD_PLANKS = new BlockItem(TethysBlocks.DOGWOOD_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item DOGWOOD_SAPLING = new BlockItem(TethysBlocks.DOGWOOD_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item ELDERBERRY_LEAVES = new BlockItem(TethysBlocks.ELDERBERRY_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item ELDERBERRY_LOG = new BlockItem(TethysBlocks.ELDERBERRY_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item ELDERBERRY_PLANKS = new BlockItem(TethysBlocks.ELDERBERRY_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item ELDERBERRY_SAPLING = new BlockItem(TethysBlocks.ELDERBERRY_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item GINKGO_LEAVES = new BlockItem(TethysBlocks.GINKGO_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item GINKGO_LOG = new BlockItem(TethysBlocks.GINKGO_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item GINKGO_PLANKS = new BlockItem(TethysBlocks.GINKGO_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item GINKGO_SAPLING = new BlockItem(TethysBlocks.GINKGO_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item GLOW_LICHEN_BALL = new GlowLichenBallItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));
    public static final Item GLOWFRUIT = new BlockItem(TethysBlocks.GLOWFRUIT, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item PINK_DIAMOND = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item PINK_MUSHROOM_BLOCK = new BlockItem(TethysBlocks.PINK_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item PORIFERAN_CHUNK = new BlockItem(TethysBlocks.PORIFERAN_CHUNK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PORIFERAN_CHUNK_GLOW = new BlockItem(TethysBlocks.PORIFERAN_CHUNK_GLOW, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item PORIFERAN_STEM = new BlockItem(TethysBlocks.PORIFERAN_STEM, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SWEETGUM_LEAVES = new BlockItem(TethysBlocks.SWEETGUM_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item SWEETGUM_LOG = new BlockItem(TethysBlocks.SWEETGUM_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SWEETGUM_PLANKS = new BlockItem(TethysBlocks.SWEETGUM_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS));
    public static final Item SWEETGUM_SAPLING = new BlockItem(TethysBlocks.SWEETGUM_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS));
    public static final Item WHITE_MUSHROOM_BLOCK = new BlockItem(TethysBlocks.WHITE_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS));

    public static void registerItems() {

        // Calcite logs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "calcite_log"), CALCITE_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blue_calcite_log"), BLUE_CALCITE_LOG);

        // Cave glowleaf
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cave_glowleaf"), CAVE_GLOWLEAF);

        // Glowfruit
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "glowfruit"), GLOWFRUIT);

        // Glow lichen ball
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "glow_lichen_ball"), GLOW_LICHEN_BALL);

        // Mushrooms
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_mushroom_block"), PINK_MUSHROOM_BLOCK);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "white_mushroom_block"), WHITE_MUSHROOM_BLOCK);

        // Ores
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "copper_ore_andesite"), new BlockItem(COPPER_ORE_ANDESITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "copper_ore_diorite"), new BlockItem(COPPER_ORE_DIORITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "copper_ore_granite"), new BlockItem(COPPER_ORE_GRANITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "gold_ore_andesite"), new BlockItem(GOLD_ORE_ANDESITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "gold_ore_diorite"), new BlockItem(GOLD_ORE_DIORITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "gold_ore_granite"), new BlockItem(GOLD_ORE_GRANITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "iron_ore_andesite"), new BlockItem(IRON_ORE_ANDESITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "iron_ore_diorite"), new BlockItem(IRON_ORE_DIORITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "iron_ore_granite"), new BlockItem(IRON_ORE_GRANITE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // Poriferans
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_chunk"), PORIFERAN_CHUNK);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_chunk_glow"), PORIFERAN_CHUNK_GLOW);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_stem"), PORIFERAN_STEM);

        // Tethys turtle eggs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "tethys_turtle_egg"), new BlockItem(TETHYS_TURTLE_EGG, new Item.Settings().group(ItemGroup.MISC)));

        // Trees
            // Basswood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_log"), BASSWOOD_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_planks"), BASSWOOD_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_leaves"), BASSWOOD_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_sapling"), BASSWOOD_SAPLING);

            // Blackcurrant trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_log"), BLACKCURRANT_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_planks"), BLACKCURRANT_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_leaves"), BLACKCURRANT_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_sapling"), BLACKCURRANT_SAPLING);

            // Cherry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_log"), CHERRY_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_planks"), CHERRY_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_leaves"), CHERRY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_sapling"), CHERRY_SAPLING);

            // Dogwood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_log"), DOGWOOD_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_planks"), DOGWOOD_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_leaves"), DOGWOOD_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_sapling"), DOGWOOD_SAPLING);

            // Elderberry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_log"), ELDERBERRY_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_planks"), ELDERBERRY_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_leaves"), ELDERBERRY_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_sapling"), ELDERBERRY_SAPLING);

            // Ginkgo trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_log"), GINKGO_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_planks"), GINKGO_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_leaves"), GINKGO_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_sapling"), GINKGO_SAPLING);

            // Sweetgum trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_log"), SWEETGUM_LOG);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_planks"), SWEETGUM_PLANKS);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_leaves"), SWEETGUM_LEAVES);
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_sapling"), SWEETGUM_SAPLING);

        // Pink diamonds!
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond_block"), new BlockItem(PINK_DIAMOND_BLOCK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond_ore"), new BlockItem(PINK_DIAMOND_ORE, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond"), PINK_DIAMOND);

        // Spawn eggs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "flying_cat_spawn_egg"), new SpawnEggItem(TethysEntities.FLYING_CAT, 16769262, 4668761, (new Item.Settings()).group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "tethys_turtle_spawn_egg"), new SpawnEggItem(TethysEntities.TETHYS_TURTLE, 4750419, 4599642, (new Item.Settings()).group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "water_strider_spawn_egg"), new SpawnEggItem(TethysEntities.WATER_STRIDER, 5263602, 987033, (new Item.Settings()).group(ItemGroup.MISC)));

    }
}
