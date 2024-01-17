package com.artemis.parallel_world.item;

import com.artemis.parallel_world.block.TethysBlocks;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.mixin.RegisterCompostableItemInvoker;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class TethysItems {

    public static final Item BASSWOOD_LEAVES = new BlockItem(TethysBlocks.BASSWOOD_LEAVES, new Item.Settings());
    public static final Item BASSWOOD_LOG = new BlockItem(TethysBlocks.BASSWOOD_LOG, new Item.Settings());
    public static final Item BASSWOOD_PLANKS = new BlockItem(TethysBlocks.BASSWOOD_PLANKS, new Item.Settings());
    public static final Item BASSWOOD_SAPLING = new BlockItem(TethysBlocks.BASSWOOD_SAPLING, new Item.Settings());
    public static final Item BLACKCURRANT_LEAVES = new BlockItem(TethysBlocks.BLACKCURRANT_LEAVES, new Item.Settings());
    public static final Item BLACKCURRANT_LOG = new BlockItem(TethysBlocks.BLACKCURRANT_LOG, new Item.Settings());
    public static final Item BLACKCURRANT_PLANKS = new BlockItem(TethysBlocks.BLACKCURRANT_PLANKS, new Item.Settings());
    public static final Item BLACKCURRANT_SAPLING = new BlockItem(TethysBlocks.BLACKCURRANT_SAPLING, new Item.Settings());
    public static final Item BLUE_CALCITE_LOG = new BlockItem(TethysBlocks.BLUE_CALCITE_LOG, new Item.Settings());
    public static final Item CALCITE_LOG = new BlockItem(TethysBlocks.CALCITE_LOG, new Item.Settings());
    public static final Item CAVE_GLOWLEAF = new BlockItem(TethysBlocks.CAVE_GLOWLEAF, new Item.Settings());
    public static final Item CHERRY_LEAVES = new BlockItem(TethysBlocks.CHERRY_LEAVES, new Item.Settings());
    public static final Item CHERRY_LOG = new BlockItem(TethysBlocks.CHERRY_LOG, new Item.Settings());
    public static final Item CHERRY_PLANKS = new BlockItem(TethysBlocks.CHERRY_PLANKS, new Item.Settings());
    public static final Item CHERRY_SAPLING = new BlockItem(TethysBlocks.CHERRY_SAPLING, new Item.Settings());
    public static final Item COPPER_ORE_ANDESITE = new BlockItem(TethysBlocks.COPPER_ORE_ANDESITE, new Item.Settings());
    public static final Item COPPER_ORE_DIORITE = new BlockItem(TethysBlocks.COPPER_ORE_DIORITE, new Item.Settings());
    public static final Item COPPER_ORE_GRANITE = new BlockItem(TethysBlocks.COPPER_ORE_GRANITE, new Item.Settings());
    public static final Item DOGWOOD_LEAVES = new BlockItem(TethysBlocks.DOGWOOD_LEAVES, new Item.Settings());
    public static final Item DOGWOOD_LOG = new BlockItem(TethysBlocks.DOGWOOD_LOG, new Item.Settings());
    public static final Item DOGWOOD_PLANKS = new BlockItem(TethysBlocks.DOGWOOD_PLANKS, new Item.Settings());
    public static final Item DOGWOOD_SAPLING = new BlockItem(TethysBlocks.DOGWOOD_SAPLING, new Item.Settings());
    public static final Item ELDERBERRY_LEAVES = new BlockItem(TethysBlocks.ELDERBERRY_LEAVES, new Item.Settings());
    public static final Item ELDERBERRY_LOG = new BlockItem(TethysBlocks.ELDERBERRY_LOG, new Item.Settings());
    public static final Item ELDERBERRY_PLANKS = new BlockItem(TethysBlocks.ELDERBERRY_PLANKS, new Item.Settings());
    public static final Item ELDERBERRY_SAPLING = new BlockItem(TethysBlocks.ELDERBERRY_SAPLING, new Item.Settings());
    public static final Item FLYING_CAT_SPAWN_EGG = new SpawnEggItem(TethysEntities.FLYING_CAT, 16769262, 4668761, (new Item.Settings()));
    public static final Item GINKGO_LEAVES = new BlockItem(TethysBlocks.GINKGO_LEAVES, new Item.Settings());
    public static final Item GINKGO_LOG = new BlockItem(TethysBlocks.GINKGO_LOG, new Item.Settings());
    public static final Item GINKGO_PLANKS = new BlockItem(TethysBlocks.GINKGO_PLANKS, new Item.Settings());
    public static final Item GINKGO_SAPLING = new BlockItem(TethysBlocks.GINKGO_SAPLING, new Item.Settings());
    public static final Item GLOW_FLOWER = new BlockItem(TethysBlocks.GLOW_FLOWER, new Item.Settings());
    public static final Item GLOW_LICHEN_BALL = new GlowLichenBallItem(new FabricItemSettings().maxCount(16));
    public static final Item GLOWFRUIT = new BlockItem(TethysBlocks.GLOWFRUIT, new Item.Settings());
    public static final Item GOLD_ORE_ANDESITE = new BlockItem(TethysBlocks.GOLD_ORE_ANDESITE, new Item.Settings());
    public static final Item GOLD_ORE_DIORITE = new BlockItem(TethysBlocks.GOLD_ORE_DIORITE, new Item.Settings());
    public static final Item GOLD_ORE_GRANITE = new BlockItem(TethysBlocks.GOLD_ORE_GRANITE, new Item.Settings());
    public static final Item HEATHER = new BlockItem(TethysBlocks.HEATHER, new Item.Settings());
    public static final Item IRON_ORE_ANDESITE = new BlockItem(TethysBlocks.IRON_ORE_ANDESITE, new Item.Settings());
    public static final Item IRON_ORE_DIORITE = new BlockItem(TethysBlocks.IRON_ORE_DIORITE, new Item.Settings());
    public static final Item IRON_ORE_GRANITE = new BlockItem(TethysBlocks.IRON_ORE_GRANITE, new Item.Settings());
    public static final Item PINK_DIAMOND = new Item(new FabricItemSettings());
    public static final Item PINK_DIAMOND_BLOCK = new BlockItem(TethysBlocks.PINK_DIAMOND_BLOCK, new Item.Settings());
    public static final Item PINK_DIAMOND_ORE = new BlockItem(TethysBlocks.PINK_DIAMOND_ORE, new Item.Settings());
    public static final Item PINK_MUSHROOM_BLOCK = new BlockItem(TethysBlocks.PINK_MUSHROOM_BLOCK, new Item.Settings());
    public static final Item PORIFERAN_CHUNK = new BlockItem(TethysBlocks.PORIFERAN_CHUNK, new Item.Settings());
    public static final Item PORIFERAN_CHUNK_GLOW = new BlockItem(TethysBlocks.PORIFERAN_CHUNK_GLOW, new Item.Settings());
    public static final Item PORIFERAN_STEM = new BlockItem(TethysBlocks.PORIFERAN_STEM, new Item.Settings());
    public static final Item SWEETGUM_LEAVES = new BlockItem(TethysBlocks.SWEETGUM_LEAVES, new Item.Settings());
    public static final Item SWEETGUM_LOG = new BlockItem(TethysBlocks.SWEETGUM_LOG, new Item.Settings());
    public static final Item SWEETGUM_PLANKS = new BlockItem(TethysBlocks.SWEETGUM_PLANKS, new Item.Settings());
    public static final Item SWEETGUM_SAPLING = new BlockItem(TethysBlocks.SWEETGUM_SAPLING, new Item.Settings());
    public static final Item TETHYS_TURTLE_EGG = new BlockItem(TethysBlocks.TETHYS_TURTLE_EGG, new Item.Settings());
    public static final Item TETHYS_TURTLE_SPAWN_EGG = new SpawnEggItem(TethysEntities.TETHYS_TURTLE, 4750419, 4599642, (new Item.Settings()));
    public static final Item WATER_STRIDER_SPAWN_EGG = new SpawnEggItem(TethysEntities.WATER_STRIDER, 5263602, 987033, (new Item.Settings()));
    public static final Item WHITE_MUSHROOM_BLOCK = new BlockItem(TethysBlocks.WHITE_MUSHROOM_BLOCK, new Item.Settings());

    public static void registerItems() {

        // Calcite logs
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "calcite_log"), CALCITE_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "blue_calcite_log"), BLUE_CALCITE_LOG);

        // Cave glowleaf
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "cave_glowleaf"), CAVE_GLOWLEAF);

        // Glowfruit
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "glowfruit"), GLOWFRUIT);

        // Glow Flower
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "glow_flower"), GLOW_FLOWER);

        // Glow lichen ball
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "glow_lichen_ball"), GLOW_LICHEN_BALL);

        // Mushrooms
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "pink_mushroom_block"), PINK_MUSHROOM_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "white_mushroom_block"), WHITE_MUSHROOM_BLOCK);

        // Ores
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "copper_ore_andesite"), COPPER_ORE_ANDESITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "copper_ore_diorite"), COPPER_ORE_DIORITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "copper_ore_granite"), COPPER_ORE_GRANITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "gold_ore_andesite"), GOLD_ORE_ANDESITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "gold_ore_diorite"), GOLD_ORE_DIORITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "gold_ore_granite"), GOLD_ORE_GRANITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "iron_ore_andesite"), IRON_ORE_ANDESITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "iron_ore_diorite"), IRON_ORE_DIORITE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "iron_ore_granite"), IRON_ORE_GRANITE);

        // Poriferans
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "poriferan_chunk"), PORIFERAN_CHUNK);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "poriferan_chunk_glow"), PORIFERAN_CHUNK_GLOW);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "poriferan_stem"), PORIFERAN_STEM);

        // Tethys turtle eggs
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "tethys_turtle_egg"), TETHYS_TURTLE_EGG);

        // Trees
            // Basswood trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "basswood_log"), BASSWOOD_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "basswood_planks"), BASSWOOD_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "basswood_leaves"), BASSWOOD_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "basswood_sapling"), BASSWOOD_SAPLING);

            // Blackcurrant trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "blackcurrant_log"), BLACKCURRANT_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "blackcurrant_planks"), BLACKCURRANT_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "blackcurrant_leaves"), BLACKCURRANT_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "blackcurrant_sapling"), BLACKCURRANT_SAPLING);

            // Cherry trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "cherry_log"), CHERRY_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "cherry_planks"), CHERRY_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "cherry_leaves"), CHERRY_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "cherry_sapling"), CHERRY_SAPLING);

            // Dogwood trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "dogwood_log"), DOGWOOD_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "dogwood_planks"), DOGWOOD_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "dogwood_leaves"), DOGWOOD_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "dogwood_sapling"), DOGWOOD_SAPLING);

            // Elderberry trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "elderberry_log"), ELDERBERRY_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "elderberry_planks"), ELDERBERRY_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "elderberry_leaves"), ELDERBERRY_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "elderberry_sapling"), ELDERBERRY_SAPLING);

            // Ginkgo trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "ginkgo_log"), GINKGO_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "ginkgo_planks"), GINKGO_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "ginkgo_leaves"), GINKGO_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "ginkgo_sapling"), GINKGO_SAPLING);

            // Sweetgum trees
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "sweetgum_log"), SWEETGUM_LOG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "sweetgum_planks"), SWEETGUM_PLANKS);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "sweetgum_leaves"), SWEETGUM_LEAVES);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "sweetgum_sapling"), SWEETGUM_SAPLING);

        // Other Vegetation
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "heather"), HEATHER);

        // Pink diamonds!
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "pink_diamond_block"), PINK_DIAMOND_BLOCK);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "pink_diamond_ore"), PINK_DIAMOND_ORE);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "pink_diamond"), PINK_DIAMOND);

        // Spawn eggs
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "flying_cat_spawn_egg"), FLYING_CAT_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "tethys_turtle_spawn_egg"), TETHYS_TURTLE_SPAWN_EGG);
        Registry.register(Registries.ITEM, new Identifier("parallel_world", "water_strider_spawn_egg"), WATER_STRIDER_SPAWN_EGG);

    }

    public static void registerCompostableItems() {
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.BASSWOOD_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.BASSWOOD_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.BLACKCURRANT_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.BLACKCURRANT_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.CAVE_GLOWLEAF);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.CHERRY_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.CHERRY_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.DOGWOOD_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.DOGWOOD_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.ELDERBERRY_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.ELDERBERRY_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.GINKGO_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.GINKGO_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.GLOW_FLOWER);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.85F, TethysItems.GLOWFRUIT);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.GLOW_LICHEN_BALL);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.HEATHER);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.PINK_MUSHROOM_BLOCK);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.PORIFERAN_CHUNK);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.PORIFERAN_CHUNK_GLOW);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.PORIFERAN_STEM);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.SWEETGUM_LEAVES);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.3F, TethysItems.SWEETGUM_SAPLING);
        RegisterCompostableItemInvoker.invokeRegisterCompostableItem(0.5F, TethysItems.WHITE_MUSHROOM_BLOCK);
    }

    public static void addItemsToGroups()

    {
        ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
            entries.add(BASSWOOD_LOG);
            entries.add(BASSWOOD_PLANKS);
            entries.add(BLACKCURRANT_LOG);
            entries.add(BLACKCURRANT_PLANKS);
            entries.add(BLUE_CALCITE_LOG);
            entries.add(CALCITE_LOG);
            entries.add(CHERRY_LOG);
            entries.add(CHERRY_PLANKS);
            entries.add(DOGWOOD_LOG);
            entries.add(DOGWOOD_PLANKS);
            entries.add(ELDERBERRY_LOG);
            entries.add(ELDERBERRY_PLANKS);
            entries.add(GINKGO_LOG);
            entries.add(GINKGO_PLANKS);
            entries.add(PINK_DIAMOND_BLOCK);
            entries.add(SWEETGUM_LOG);
            entries.add(SWEETGUM_PLANKS);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> entries.add(GLOW_LICHEN_BALL));

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
            entries.add(BASSWOOD_LEAVES);
            entries.add(BASSWOOD_LOG);
            entries.add(BASSWOOD_PLANKS);
            entries.add(BLACKCURRANT_LEAVES);
            entries.add(BLACKCURRANT_LOG);
            entries.add(BLACKCURRANT_PLANKS);
            entries.add(BLACKCURRANT_SAPLING);
            entries.add(BLUE_CALCITE_LOG);
            entries.add(CALCITE_LOG);
            entries.add(CAVE_GLOWLEAF);
            entries.add(CHERRY_LEAVES);
            entries.add(CHERRY_LOG);
            entries.add(CHERRY_PLANKS);
            entries.add(CHERRY_SAPLING);
            entries.add(COPPER_ORE_ANDESITE);
            entries.add(COPPER_ORE_GRANITE);
            entries.add(COPPER_ORE_DIORITE);
            entries.add(DOGWOOD_LEAVES);
            entries.add(DOGWOOD_LOG);
            entries.add(DOGWOOD_PLANKS);
            entries.add(DOGWOOD_SAPLING);
            entries.add(ELDERBERRY_LEAVES);
            entries.add(ELDERBERRY_LOG);
            entries.add(ELDERBERRY_PLANKS);
            entries.add(ELDERBERRY_SAPLING);
            entries.add(GINKGO_LEAVES);
            entries.add(GINKGO_LOG);
            entries.add(GINKGO_PLANKS);
            entries.add(GINKGO_SAPLING);
            entries.add(GLOW_FLOWER);
            entries.add(GLOWFRUIT);
            entries.add(GOLD_ORE_ANDESITE);
            entries.add(GOLD_ORE_GRANITE);
            entries.add(GOLD_ORE_DIORITE);
            entries.add(HEATHER);
            entries.add(IRON_ORE_ANDESITE);
            entries.add(IRON_ORE_GRANITE);
            entries.add(IRON_ORE_DIORITE);
            entries.add(PINK_DIAMOND);
            entries.add(PINK_DIAMOND_ORE);
            entries.add(PINK_MUSHROOM_BLOCK);
            entries.add(PORIFERAN_CHUNK);
            entries.add(PORIFERAN_CHUNK_GLOW);
            entries.add(PORIFERAN_STEM);
            entries.add(SWEETGUM_LEAVES);
            entries.add(SWEETGUM_LOG);
            entries.add(SWEETGUM_PLANKS);
            entries.add(SWEETGUM_SAPLING);
            entries.add(TETHYS_TURTLE_EGG);
            entries.add(WHITE_MUSHROOM_BLOCK);
        });

        ItemGroupEvents.modifyEntriesEvent(ItemGroups.SPAWN_EGGS).register(entries -> {
            entries.add(FLYING_CAT_SPAWN_EGG);
            entries.add(TETHYS_TURTLE_SPAWN_EGG);
            entries.add(WATER_STRIDER_SPAWN_EGG);
        });
    }
}
