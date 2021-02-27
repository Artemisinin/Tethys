package com.artemis.parallel_world.client.render.entity;

import com.artemis.parallel_world.entity.EntitySpawnPacket;
import com.artemis.parallel_world.entity.GlowLichenBallEntity;
import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.network.ClientSidePacketRegistry;
import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.entity.FlyingItemEntityRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.registry.Registry;

import java.util.UUID;

public class TethysEntityRenderers {

    public static void registerEntityRenderers() {
        EntityRendererRegistry.INSTANCE.register(TethysEntities.FLYING_CAT, FlyingCatEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.GLOW_LICHEN_BALL, FlyingItemEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.TETHYS_TURTLE, TethysTurtleEntityRenderer::new);
        EntityRendererRegistry.INSTANCE.register(TethysEntities.WATER_STRIDER, WaterStriderEntityRenderer::new);
    }
}
