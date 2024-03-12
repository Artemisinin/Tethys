package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.foliage.TethysFoliagePlacers;
import com.artemis.parallel_world.world.gen.treedecorator.AlterGroundPlusSandTreeDecorator;
import com.artemis.parallel_world.world.gen.treedecorator.GlowfruitTreeDecorator;
import com.artemis.parallel_world.world.gen.trunk.TethysTrunkPlacers;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.api.CustomPortalBuilder;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.Identifier;

public class Dimension implements ModInitializer {

    // If these are updated in the json files they to be updated here.
    public static final int TETHYS_SEA_LEVEL = 0;
    public static final int TETHYS_TOP_Y = 208;

    @Override
    public void onInitialize() {

        // Add portal
        CustomPortalBuilder.beginPortal().frameBlock(Blocks.LAPIS_BLOCK).lightWithFluid(Fluids.WATER).destDimID(new Identifier("parallel_world", "tethys")).tintColor(23,140,176).registerPortal();
        // Register entities
        TethysEntities.registerEntities();

        // Register blocks and items
        TethysBlocks.TethysSaplingGenerators.registerSaplingGenerators();
        TethysBlocks.registerBlocks();
        TethysBlocks.registerFlammability();
        TethysBlocks.registerBlockTags();
        TethysItems.registerItems();
        TethysItems.addItemsToGroups();
        TethysItems.registerCompostableItems();

        // Register worldgen elements
        //TethysBiomes.registerBiomes();
        //TethysConfiguredCarvers.registerCarvers();
        AlterGroundPlusSandTreeDecorator.registerAlterGroundPlusSandTreeDecorator();
        GlowfruitTreeDecorator.registerGlowfruitTreeDecorator();
        TethysTrunkPlacers.registerTrunkPlacers();
        TethysFoliagePlacers.registerFoliagePlacers();
        TethysPlacementModifiers.registerDecorators();
        TethysFeatures.registerFeatures();

        // Add configured features to overworld biomes
        //BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "cave_scattered_ghost_trees")));
        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "scattered_poriferans")));
        //BiomeModifications.addFeature(BiomeSelectors.includeByKey(SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("parallel_world", "swamp_oak_shrubs")));
    }
}