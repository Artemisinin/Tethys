package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.FlyingCatEntity;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.entity.TethysTurtleEntity;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.carver.GullyCarver;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.impl.biome.modification.BiomeModificationImpl;
import net.fabricmc.fabric.impl.biome.modification.BiomeSelectionContextImpl;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.*;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;


public class Dimension implements ModInitializer {



    @Override
    public void onInitialize() {

        // Add portal
        CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register blocks, features, and items and make sure stuff will BURN
        TethysItems.registerItems();
        TethysBlocks.registerBlocks();
        TethysBlocks.registerFlammability();
        TethysFeatures.registerFeatures();
        TethysEntities.registerEntities();
        //TethysConfiguredFeatures.registerConfiguredFeatures();


    }
}
