package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.carver.TethysConfiguredCarvers;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.tree.GlowfruitTreeDecorator;
import com.artemis.parallel_world.world.gen.trunk.TethysTrunkPlacers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.util.Identifier;
import net.minecraft.world.dimension.DimensionOptions;
import net.minecraft.world.gen.GenerationStep;


import static net.minecraft.world.biome.BiomeKeys.*;


public class Dimension implements ModInitializer {

    // If sea level is updated in the noise file this needs to be updated.
    public static final int TETHYS_SEA_LEVEL = 20;

    @Override
    public void onInitialize() {


        // Add portal
        // Custom portal not working in snapshot at the moment.
        //CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register entities
        TethysEntities.registerEntities();

        // Register blocks and items
        TethysBlocks.registerBlocks();
        TethysBlocks.registerFlammability();
        TethysBlocks.registerBlockTags();
        TethysItems.registerItems();
        TethysItems.addItemsToGroups();
        TethysItems.registerCompostableItems();

        // Register worldgen elements
        //TethysBiomes.registerBiomes();
        //TethysConfiguredCarvers.registerCarvers();
        GlowfruitTreeDecorator.registerGlowfruitTreeDecorator();
        TethysTrunkPlacers.registerTrunkPlacers();
        TethysPlacementModifiers.registerDecorators();
        TethysFeatures.registerFeatures();
        //TethysConfiguredFeatures.registerConfiguredFeatures();
        //TethysConfiguredFeatures.registerPlacedFeatures();

        // Add configured features to overworld biomes
        //BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "cave_scattered_ghost_trees")));
        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "scattered_poriferans")));
        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "swamp_oak_shrubs")));
    }
}