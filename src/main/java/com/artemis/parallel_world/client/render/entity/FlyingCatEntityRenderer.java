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
import net.minecraft.client.util.math.MatrixStack;

import net.minecraft.util.Identifier;
import net.minecraft.util.Util;

import java.util.Map;


@Environment(EnvType.CLIENT)
public class FlyingCatEntityRenderer extends MobEntityRenderer<FlyingCatEntity, FlyingCatEntityModel<FlyingCatEntity>> {

    private static final Map<FlyingCatVariant, Identifier> TEXTURES = Util.make(Maps.newEnumMap(FlyingCatVariant.class), (map) -> {
        map.put(FlyingCatVariant.TABBY, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_tabby.png"));
        map.put(FlyingCatVariant.BLACK, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_black.png"));
        map.put(FlyingCatVariant.RED, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_red.png"));
        map.put(FlyingCatVariant.SIAMESE, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_siamese.png"));
        map.put(FlyingCatVariant.BRITISH_SHORTHAIR, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_british_shorthair.png"));
        map.put(FlyingCatVariant.CALICO, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_calico.png"));
        map.put(FlyingCatVariant.PERSIAN, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_persian.png"));
        map.put(FlyingCatVariant.RAGDOLL, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_ragdoll.png"));
        map.put(FlyingCatVariant.WHITE, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_white.png"));
        map.put(FlyingCatVariant.JELLIE, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_jellie.png"));
        map.put(FlyingCatVariant.ALL_BLACK, new Identifier("parallel_world", "textures/entity/flyingcat/flying_cat_all_black.png"));
    });

    public FlyingCatEntityRenderer(EntityRendererFactory.Context context) {
        super(context, new FlyingCatEntityModel(context.getPart(TethysEntityModelLayers.FLYING_CAT_MODEL_LAYER)), 0.4F);
    }

    public Identifier getTexture(FlyingCatEntity entity) {
        return TEXTURES.get(entity.getVariant());
    }

    protected void scale(FlyingCatEntity flyingCatEntity, MatrixStack matrixStack, float f) {
        super.scale(flyingCatEntity, matrixStack, f);
        matrixStack.scale(0.8F, 0.8F, 0.8F);
    }
}