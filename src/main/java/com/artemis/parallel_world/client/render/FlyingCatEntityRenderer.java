package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.client.render.entity.model.FlyingCatEntityModel;
import com.artemis.parallel_world.entity.FlyingCatEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;

public class FlyingCatEntityRenderer extends MobEntityRenderer<FlyingCatEntity, FlyingCatEntityModel<FlyingCatEntity>> {

    public FlyingCatEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new FlyingCatEntityModel<>(0.0F), 0.4F);
    }

    public Identifier getTexture(FlyingCatEntity entity) {
        return new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_calico.png");
    }
}