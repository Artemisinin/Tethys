package com.artemis.parallel_world.client.render.entity.model;

import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;

import static com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers.*;
import static com.artemis.parallel_world.client.render.entity.model.TethysTexturedModelData.*;


public class TethysEntityModels {

    public static void registerEntityModels() {
        EntityModelLayerRegistry.registerModelLayer(FLYING_CAT_MODEL_LAYER, () -> FLYING_CAT_MODEL_DATA);
        EntityModelLayerRegistry.registerModelLayer(TETHYS_TURTLE_MODEL_LAYER, () -> TETHYS_TURTLE_MODEL_DATA);
        EntityModelLayerRegistry.registerModelLayer(WATER_STRIDER_MODEL_LAYER, () -> WATER_STRIDER_MODEL_DATA);
    }
}