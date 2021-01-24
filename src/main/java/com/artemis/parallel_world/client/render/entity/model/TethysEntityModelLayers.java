package com.artemis.parallel_world.client.render.entity.model;

import com.google.common.collect.Sets;
import net.minecraft.client.render.entity.model.EntityModelLayer;
import net.minecraft.util.Identifier;

import java.util.Set;

public class TethysEntityModelLayers {

    public static EntityModelLayer TETHYS_TURTLE;
    public static EntityModelLayer WATER_STRIDER;
    public static EntityModelLayer FLYING_CAT;

    public static Set<EntityModelLayer> TETHYS_LAYERS = Sets.newHashSet();


    private static EntityModelLayer registerMain(String id) {
        return register(id, "main");
    }

    private static EntityModelLayer register(String id, String layer) {
        EntityModelLayer entityModelLayer = create(id, layer);
        if (!TETHYS_LAYERS.add(entityModelLayer)) {
            throw new IllegalStateException("Duplicate registration for " + entityModelLayer);
        } else {
            return entityModelLayer;
        }
    }

    private static EntityModelLayer create(String id, String layer) {
        return new EntityModelLayer(new Identifier("parallel_world", id), layer);
    }

    public static void registerModelLayers()
    {
        FLYING_CAT = registerMain("flying_cat");
        TETHYS_TURTLE = registerMain("tethys_turtle");
        WATER_STRIDER = registerMain("water_strider");
    }
}
