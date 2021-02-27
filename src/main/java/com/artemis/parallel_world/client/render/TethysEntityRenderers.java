package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;


public class TethysEntityRenderers {

    public static void registerEntityRenderers() {
        EntityRendererRegistry.INSTANCE.register(TethysEntities.FLYING_CAT, FlyingCatEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.GLOW_LICHEN_BALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.TETHYS_TURTLE, TethysTurtleEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.WATER_STRIDER, WaterStriderEntityRenderer::new);
    }
}