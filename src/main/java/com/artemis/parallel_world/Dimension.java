package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.surfacebuilder.TethysSurfaceBuilder;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.gen.GenerationStep;
import org.lwjgl.system.CallbackI;


public class Dimension implements ModInitializer {



    @Override
    public void onInitialize() {

        // Add portal
        // Custom portal not working in snapshot at the moment.
        //CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register blocks, features, and items and make sure stuff will BURN
        TethysBlocks.registerBlocks();
        TethysItems.registerItems();
        TethysBlocks.registerFlammability();
        TethysFeatures.registerFeatures();
        TethysConfiguredFeatures.registerConfiguredFeatures();
        TethysEntities.registerEntities();

        TethysSurfaceBuilder.registerSurfaceBuilders();

        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "cave_forest")));
    }
}