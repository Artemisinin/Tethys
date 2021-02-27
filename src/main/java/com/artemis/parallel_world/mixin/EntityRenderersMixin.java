package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.client.render.FlyingCatEntityRenderer;
import com.artemis.parallel_world.entity.TethysEntities;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.EntityRenderers;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.Map;

@Mixin(EntityRenderers.class)
public abstract class EntityRenderersMixin {

    @Shadow @Final
    static Map<EntityType<?>, EntityRendererFactory<?>> rendererFactories;

    @Shadow
    static <T extends Entity> void register(EntityType<? extends T> type, EntityRendererFactory<T> factory) {
        rendererFactories.put(type, factory);
    }

    @Inject(method = "<clinit>", at = @At("HEAD"))
    private static void addEntities(CallbackInfo ci) {
//        register(TethysEntities.FLYING_CAT, FlyingCatEntityRenderer::new);
    }
}