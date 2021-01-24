package com.artemis.parallel_world.client.render.entity.model;


import com.google.common.collect.ImmutableMap;
import net.minecraft.client.model.TexturedModelData;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.client.render.entity.model.EntityModelLayers;

import net.minecraft.client.render.entity.model.EntityModels;
import net.minecraft.client.util.math.Dilation;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TethysEntityModels {

    public static Map<EntityModelLayer, TexturedModelData> getTethysModels() {
        ImmutableMap.Builder<EntityModelLayer, TexturedModelData> builder = ImmutableMap.builder();

        builder.put(TethysEntityModelLayers.FLYING_CAT, TethysTexturedModelData.FLYING_CAT_MODEL_DATA);
        builder.put(TethysEntityModelLayers.WATER_STRIDER, TethysTexturedModelData.WATER_STRIDER_MODEL_DATA);
        builder.put(TethysEntityModelLayers.TETHYS_TURTLE, TethysTexturedModelData.TETHYS_TURTLE_MODEL_DATA);

        ImmutableMap<EntityModelLayer, TexturedModelData> immutableMapTethys = builder.build();
        List<EntityModelLayer> list = (List) EntityModelLayers.getLayers().filter((entityModelLayer) -> {
            return !immutableMapTethys.containsKey(entityModelLayer);
        }).collect(Collectors.toList());
        if (!list.isEmpty()) {
            throw new IllegalStateException("Missing layer definitions: " + list);
        } else {
            return immutableMapTethys;
        }
    }

}
