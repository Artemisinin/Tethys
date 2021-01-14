package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.client.render.entity.model.WaterStriderEntityModel;
import com.artemis.parallel_world.entity.WaterStriderEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;


public class WaterStriderEntityRenderer extends MobEntityRenderer<WaterStriderEntity, WaterStriderEntityModel<WaterStriderEntity>> {

    public WaterStriderEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new WaterStriderEntityModel(), 0.4F);
    }

    public Identifier getTexture(WaterStriderEntity entity) {
        return new Identifier("parallel_world", "textures/entity/water_strider.png");
    }

    protected void scale(WaterStriderEntity waterStriderEntity, MatrixStack matrixStack, float f) {
        super.scale(waterStriderEntity, matrixStack, f);
        matrixStack.scale(0.4f,0.4f,0.4f);
    }

}
