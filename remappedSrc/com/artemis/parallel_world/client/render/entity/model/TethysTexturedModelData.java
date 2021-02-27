package com.artemis.parallel_world.client.render.entity.model;

import net.minecraft.client.model.TexturedModelData;


public class TethysTexturedModelData {

    public static TexturedModelData FLYING_CAT_MODEL_DATA;
    public static TexturedModelData WATER_STRIDER_MODEL_DATA;
    public static TexturedModelData TETHYS_TURTLE_MODEL_DATA;

    public static void registerTexturedModelData ()
    {
        FLYING_CAT_MODEL_DATA = TexturedModelData.of(FlyingCatEntityModel.getModelData(null), 64, 32);
        WATER_STRIDER_MODEL_DATA = TexturedModelData.of(WaterStriderEntityModel.getModelData(), 32, 16);
        TETHYS_TURTLE_MODEL_DATA = TethysTurtleEntityModel.getTexturedModelData();
    }
}
