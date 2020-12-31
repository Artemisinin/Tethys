package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.client.render.entity.model.TethysTurtleEntityModel;
import com.artemis.parallel_world.entity.TethysTurtleEntity;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.Identifier;

@Environment(EnvType.CLIENT)

public class TethysTurtleEntityRenderer extends MobEntityRenderer<TethysTurtleEntity, TethysTurtleEntityModel<TethysTurtleEntity>> {
    private static final Identifier TEXTURE = new Identifier("parallel_world:textures/entity/tethys_turtle.png");

    // Should make turtles bigger than vanilla.
    public TethysTurtleEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new TethysTurtleEntityModel(0.5F), 0.7F);
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