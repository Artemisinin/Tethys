package com.artemis.parallel_world.item;

import com.artemis.parallel_world.entity.GlowLichenBallEntity;
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

    public static final Item PINK_DIAMOND = new Item(new FabricItemSettings().group(ItemGroup.MISC));
    public static final Item GLOW_LICHEN_BALL = new GlowLichenBallItem(new FabricItemSettings().group(ItemGroup.MISC).maxCount(16));

    public static void registerItems() {

        // Cave glowleaf
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cave_glowleaf"), new BlockItem(CAVE_GLOWLEAF, new Item.Settings().group(ItemGroup.DECORATIONS)));
        // Glowfruit
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "glowfruit"), new BlockItem(GLOWFRUIT, new Item.Settings().group(ItemGroup.DECORATIONS)));

        // Glow lichen ball
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "glow_lichen_ball"), GLOW_LICHEN_BALL);

        // Calcite logs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "calcite_log"), new BlockItem(CALCITE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blue_calcite_log"), new BlockItem(BLUE_CALCITE_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // Mushrooms
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "pink_mushroom_block"), new BlockItem(PINK_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "white_mushroom_block"), new BlockItem(WHITE_MUSHROOM_BLOCK, new Item.Settings().group(ItemGroup.DECORATIONS)));

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
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_chunk"), new BlockItem(PORIFERAN_CHUNK, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_chunk_glow"), new BlockItem(PORIFERAN_CHUNK_GLOW, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "poriferan_stem"), new BlockItem(PORIFERAN_STEM, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));

        // Tethys turtle eggs
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "tethys_turtle_egg"), new BlockItem(TETHYS_TURTLE_EGG, new Item.Settings().group(ItemGroup.MISC)));

        // Trees
            // Basswood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_log"), new BlockItem(BASSWOOD_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_planks"), new BlockItem(BASSWOOD_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_leaves"), new BlockItem(BASSWOOD_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "basswood_sapling"), new BlockItem(BASSWOOD_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Blackcurrant trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_log"), new BlockItem(BLACKCURRANT_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_planks"), new BlockItem(BLACKCURRANT_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_leaves"), new BlockItem(BLACKCURRANT_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "blackcurrant_sapling"), new BlockItem(BLACKCURRANT_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Cherry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_log"), new BlockItem(CHERRY_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_planks"), new BlockItem(CHERRY_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_leaves"), new BlockItem(CHERRY_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "cherry_sapling"), new BlockItem(CHERRY_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Dogwood trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_log"), new BlockItem(DOGWOOD_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_planks"), new BlockItem(DOGWOOD_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_leaves"), new BlockItem(DOGWOOD_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "dogwood_sapling"), new BlockItem(DOGWOOD_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Elderberry trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_log"), new BlockItem(ELDERBERRY_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_planks"), new BlockItem(ELDERBERRY_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_leaves"), new BlockItem(ELDERBERRY_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "elderberry_sapling"), new BlockItem(ELDERBERRY_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Ginkgo trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_log"), new BlockItem(GINKGO_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_planks"), new BlockItem(GINKGO_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_leaves"), new BlockItem(GINKGO_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "ginkgo_sapling"), new BlockItem(GINKGO_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

            // Sweetgum trees
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_log"), new BlockItem(SWEETGUM_LOG, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_planks"), new BlockItem(SWEETGUM_PLANKS, new Item.Settings().group(ItemGroup.BUILDING_BLOCKS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_leaves"), new BlockItem(SWEETGUM_LEAVES, new Item.Settings().group(ItemGroup.DECORATIONS)));
        Registry.register(Registry.ITEM, new Identifier("parallel_world", "sweetgum_sapling"), new BlockItem(SWEETGUM_SAPLING, new Item.Settings().group(ItemGroup.DECORATIONS)));

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
