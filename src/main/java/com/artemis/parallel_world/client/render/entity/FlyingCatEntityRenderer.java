package com.artemis.parallel_world.client.render.entity;

import com.artemis.parallel_world.client.render.entity.model.FlyingCatEntityModel;
import com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers;
import com.artemis.parallel_world.entity.FlyingCatEntity;
import com.artemis.parallel_world.entity.FlyingCatVariant;
import com.google.common.collect.Maps;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.feature.CatCollarFeatureRenderer;
import net.minecraft.client.render.entity.feature.FeatureRendererContext;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;


@Environment(EnvType.CLIENT)
public class FlyingCatEntityRenderer extends MobEntityRenderer<FlyingCatEntity, FlyingCatEntityModel<FlyingCatEntity>> {
    public FlyingCatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FlyingCatEntityModel<>(context.getPart(TethysEntityModelLayers.FLYING_CAT_MODEL_LAYER)), 0.4F);

        // Need to add a new class for this apparently.
        //this.addFeature(new CatCollarFeatureRenderer(this, context.getModelLoader()));
    }

    @Override
    public Identifier getTexture(FlyingCatEntity entity) {
        return entity.getTexture();
    }

    protected void scale(FlyingCatEntity flyingCatEntity, MatrixStack matrixStack, float f) {
        super.scale(flyingCatEntity, matrixStack, f);
        matrixStack.scale(0.8F, 0.8F, 0.8F);
    }
}