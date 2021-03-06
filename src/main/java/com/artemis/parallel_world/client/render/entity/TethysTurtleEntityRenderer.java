package com.artemis.parallel_world.client.render.entity;

import com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers;
import com.artemis.parallel_world.client.render.entity.model.TethysTurtleEntityModel;
import com.artemis.parallel_world.entity.TethysTurtleEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory.Context;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.render.entity.model.EntityModelLayers;
import net.minecraft.client.render.entity.model.TurtleEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)

public class TethysTurtleEntityRenderer extends MobEntityRenderer<TethysTurtleEntity,TethysTurtleEntityModel<TethysTurtleEntity>> {
    private static final Identifier TEXTURE = new Identifier("parallel_world:textures/entity/tethys_turtle.png");

    public TethysTurtleEntityRenderer(Context context) {
        super(context, new TethysTurtleEntityModel(context.getPart(TethysEntityModelLayers.TETHYS_TURTLE_MODEL_LAYER)), 1.0F);
    }

    public void render(TethysTurtleEntity tethysTurtleEntity, float f, float g, MatrixStack matrixStack, VertexConsumerProvider vertexConsumerProvider, int i) {
        if (tethysTurtleEntity.isBaby()) {
            this.shadowRadius *= 0.5F;
        }
        super.render(tethysTurtleEntity, f, g, matrixStack, vertexConsumerProvider, i);
    }

    public Identifier getTexture(TethysTurtleEntity tethysTurtleEntity) {
        return TEXTURE;
    }
}