package com.artemis.parallel_world.client.render.entity;

import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;

public class TethysEntityRenderers {

    public static void registerEntityRenderers() {
        EntityRendererRegistry.register(TethysEntities.FLYING_CAT, FlyingCatEntityRenderer::new);
        EntityRendererRegistry.register(TethysEntities.GLOW_LICHEN_BALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.register(TethysEntities.TETHYS_TURTLE, TethysTurtleEntityRenderer::new);
        EntityRendererRegistry.register(TethysEntities.WATER_STRIDER, WaterStriderEntityRenderer::new);
    }
}
