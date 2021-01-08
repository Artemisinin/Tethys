package com.artemis.parallel_world.client.render;

import com.artemis.parallel_world.client.render.entity.model.FlyingCatEntityModel;
import com.artemis.parallel_world.entity.FlyingCatEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.util.math.Vector3f;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.MathHelper;

import java.util.Iterator;
import java.util.List;

public class FlyingCatEntityRenderer extends MobEntityRenderer<FlyingCatEntity, FlyingCatEntityModel<FlyingCatEntity>> {

    public FlyingCatEntityRenderer(EntityRenderDispatcher entityRenderDispatcher) {
        super(entityRenderDispatcher, new FlyingCatEntityModel<>(0.0F), 0.4F);
    }

    public Identifier getTexture(FlyingCatEntity entity) {
        return new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_calico.png");
    }

    protected void scale(FlyingCatEntity flyingCatEntity, MatrixStack matrixStack, float f) {
        super.scale(flyingCatEntity, matrixStack, f);
        matrixStack.scale(0.8F, 0.8F, 0.8F);
    }

    protected void setupTransforms(FlyingCatEntity flyingCatEntity, MatrixStack matrixStack, float f, float g, float h) {
        super.setupTransforms(flyingCatEntity, matrixStack, f, g, h);
        float i = flyingCatEntity.getSleepAnimation(h);
        if (i > 0.0F) {
            matrixStack.translate(0.4F * i, 0.15F * i, 0.1F * i);
            matrixStack.multiply(Vector3f.POSITIVE_Z.getDegreesQuaternion(MathHelper.lerpAngleDegrees(i, 0.0F, 90.0F)));
            BlockPos blockPos = flyingCatEntity.getBlockPos();
            List<PlayerEntity> list = flyingCatEntity.world.getNonSpectatingEntities(PlayerEntity.class, (new Box(blockPos)).expand(2.0D, 2.0D, 2.0D));
            Iterator var9 = list.iterator();

            while(var9.hasNext()) {
                PlayerEntity playerEntity = (PlayerEntity)var9.next();
                if (playerEntity.isSleeping()) {
                    matrixStack.translate(0.15F * i, 0.0D, 0.0D);
                    break;
                }
            }
        }
    }
}