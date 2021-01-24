package com.artemis.parallel_world.mixin;

import java.util.Map;

import com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers;
import com.artemis.parallel_world.client.render.entity.model.TethysTexturedModelData;
import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModels;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


@Mixin(EntityModels.class)
abstract class EntityModelsMixin {
    @Inject(method = "getModels", at = @At(value = "INVOKE",
            target = "Lcom/google/common/collect/ImmutableMap$Builder;build()Lcom/google/common/collect/ImmutableMap;"),
            locals = LocalCapture.CAPTURE_FAILEXCEPTION)
    private static void registerExtraModelData(CallbackInfoReturnable<Map<EntityModelLayer, TexturedModelData>> info,
                                               ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder) {
        builder.put(TethysEntityModelLayers.FLYING_CAT, TethysTexturedModelData.FLYING_CAT_MODEL_DATA);
        builder.put(TethysEntityModelLayers.WATER_STRIDER, TethysTexturedModelData.WATER_STRIDER_MODEL_DATA);
        builder.put(TethysEntityModelLayers.TETHYS_TURTLE, TethysTexturedModelData.TETHYS_TURTLE_MODEL_DATA);
    }
}

