package com.artemis.parallel_world.client.render.entity.model;

import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;


public class TethysEntityModelLayers {

    public static EntityModelLayer TETHYS_TURTLE_MODEL_LAYER;
    public static EntityModelLayer WATER_STRIDER_MODEL_LAYER;
    public static EntityModelLayer FLYING_CAT_MODEL_LAYER;

public static void registerModelLayers() {
    TETHYS_TURTLE_MODEL_LAYER = new EntityModelLayer(new Identifier("parallel_world", "tethys_turtle"), "main");
    WATER_STRIDER_MODEL_LAYER = new EntityModelLayer(new Identifier("parallel_world", "water_strider"), "main");
    FLYING_CAT_MODEL_LAYER = new EntityModelLayer(new Identifier("parallel_world", "flying_cat"), "main");
    }
}