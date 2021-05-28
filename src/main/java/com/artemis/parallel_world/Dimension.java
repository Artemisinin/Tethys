package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.mixin.DecoratorRegisterInvoker;
import com.artemis.parallel_world.mixin.TreeDecoratorTypeRegisterInvoker;
import com.artemis.parallel_world.mixin.TrunkPlacerTypeRegisterInvoker;
import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.carver.TethysConfiguredCarvers;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysDecorators;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.surfacebuilder.TethysSurfaceBuilder;
import com.artemis.parallel_world.world.gen.tree.GlowfruitTreeDecorator;
import com.artemis.parallel_world.world.gen.trunk.HugeTreeTrunkPlacer;
import com.artemis.parallel_world.world.gen.trunk.TethysTrunkPlacers;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import net.minecraft.world.gen.trunk.TrunkPlacerType;


import static net.minecraft.world.biome.BiomeKeys.*;


public class Dimension implements ModInitializer {




    @Override
    public void onInitialize() {

        // Add portal
        // Custom portal not working in snapshot at the moment.
        //CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register blocks and items
        TethysBlocks.registerBlocks();
        TethysBlocks.registerFlammability();
        TethysBlocks.registerBlockTags();
        TethysItems.registerItems();
        TethysItems.registerCompostableItems();

        // Register worldgen elements
        TethysBiomes.registerBiomes();
        TethysSurfaceBuilder.registerSurfaceBuilders();
        TethysConfiguredCarvers.registerCarvers();
        GlowfruitTreeDecorator.registerGlowfruitTreeDecorator();
        TethysTrunkPlacers.registerTrunkPlacers();
        TethysDecorators.registerDecorators();
        TethysFeatures.registerFeatures();
        TethysConfiguredFeatures.registerConfiguredFeatures();

        // Register entities
        TethysEntities.registerEntities();

        // Add configured features to overworld biomes
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "cave_scattered_ghost_trees")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "scattered_poriferans")));
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "swamp_oak_shrubs")));
    }
}