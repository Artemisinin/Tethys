package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.client.render.entity.model.FlyingCatEntityModel;
import com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers;
import com.artemis.parallel_world.entity.FlyingCatEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;


@Environment(EnvType.CLIENT)
public class FlyingCatEntityRenderer extends MobEntityRenderer<FlyingCatEntity, FlyingCatEntityModel<FlyingCatEntity>> {

    public FlyingCatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FlyingCatEntityModel(context.getPart(TethysEntityModelLayers.FLYING_CAT_MODEL_LAYER)), 0.4F);
    }

    public Identifier getTexture(FlyingCatEntity entity) {
        return new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_calico.png");
    }

    protected void scale(FlyingCatEntity flyingCatEntity, MatrixStack matrixStack, float f) {
        super.scale(flyingCatEntity, matrixStack, f);
        matrixStack.scale(0.8F, 0.8F, 0.8F);
    }
}