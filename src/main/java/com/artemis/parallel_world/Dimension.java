package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.surfacebuilder.ConfiguredSurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.SurfaceBuilder;
import net.minecraft.world.gen.surfacebuilder.TernarySurfaceConfig;


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

    }
}