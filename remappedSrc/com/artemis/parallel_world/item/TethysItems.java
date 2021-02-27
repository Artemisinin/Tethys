package com.artemis.parallel_world.item;

import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.EntityType;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.SpawnEggItem;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

import static com.artemis.parallel_world.block.TethysBlocks.*;

public class TethysItems {

    public static final Item PINK_DIAMOND = new Item(new FabricItemSettings().group(ItemGroup.MISC));

    public static void registerItems() {

        // Glowfruit
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "glowfruit"), new BlockItem(GLOWFRUIT, new Item.Settings().group(ItemGroup.MISC)));

        // Calcite logs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "calcite_log"), new BlockItem(CALCITE_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blue_calcite_log"), new BlockItem(BLUE_CALCITE_LOG, new Item.Settings().group(ItemGroup.MISC)));

        // Tethys turtle eggs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "tethys_turtle_egg"), new BlockItem(TETHYS_TURTLE_EGG, new Item.Settings().group(ItemGroup.MISC)));

        // Trees
            // Basswood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_log"), new BlockItem(BASSWOOD_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_planks"), new BlockItem(BASSWOOD_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_leaves"), new BlockItem(BASSWOOD_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_sapling"), new BlockItem(BASSWOOD_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Blackcurrant trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_log"), new BlockItem(BLACKCURRANT_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_planks"), new BlockItem(BLACKCURRANT_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_leaves"), new BlockItem(BLACKCURRANT_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_sapling"), new BlockItem(BLACKCURRANT_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Cherry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_log"), new BlockItem(CHERRY_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_planks"), new BlockItem(CHERRY_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_leaves"), new BlockItem(CHERRY_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_sapling"), new BlockItem(CHERRY_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Dogwood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_log"), new BlockItem(DOGWOOD_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_planks"), new BlockItem(DOGWOOD_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_leaves"), new BlockItem(DOGWOOD_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_sapling"), new BlockItem(DOGWOOD_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Elderberry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_log"), new BlockItem(ELDERBERRY_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_planks"), new BlockItem(ELDERBERRY_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_leaves"), new BlockItem(ELDERBERRY_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_sapling"), new BlockItem(ELDERBERRY_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Ginkgo trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_log"), new BlockItem(GINKGO_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_planks"), new BlockItem(GINKGO_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_leaves"), new BlockItem(GINKGO_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_sapling"), new BlockItem(GINKGO_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

            // Sweetgum trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_log"), new BlockItem(SWEETGUM_LOG, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_planks"), new BlockItem(SWEETGUM_PLANKS, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_leaves"), new BlockItem(SWEETGUM_LEAVES, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_sapling"), new BlockItem(SWEETGUM_SAPLING, new Item.Settings().group(ItemGroup.MISC)));

        // Pink diamonds!
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond_block"), new BlockItem(PINK_DIAMOND_BLOCK, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond_ore"), new BlockItem(PINK_DIAMOND_ORE, new Item.Settings().group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_diamond"), PINK_DIAMOND);

        // Spawn eggs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "flying_cat_spawn_egg"), new SpawnEggItem(TethysEntities.FLYING_CAT, 16769262, 4668761, (new Item.Settings()).group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "tethys_turtle_spawn_egg"), new SpawnEggItem(TethysEntities.TETHYS_TURTLE, 4750419, 4599642, (new Item.Settings()).group(ItemGroup.MISC)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "water_strider_spawn_egg"), new SpawnEggItem(TethysEntities.WATER_STRIDER, 5263602, 987033, (new Item.Settings()).group(ItemGroup.MISC)));

    }
}
