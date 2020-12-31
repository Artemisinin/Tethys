package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import net.fabricmc.api.ModInitializer;
import net.kyrptonaught.customportalapi.CustomPortalApiRegistry;
import net.kyrptonaught.customportalapi.portal.PortalIgnitionSource;
import net.minecraft.block.*;
import net.minecraft.util.Identifier;


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

    }
}
