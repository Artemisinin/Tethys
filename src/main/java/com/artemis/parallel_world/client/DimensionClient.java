package com.artemis.parallel_world.client;

import com.artemis.parallel_world.client.render.FlyingCatEntityRenderer;
import com.artemis.parallel_world.client.render.TethysTurtleEntityRenderer;
import com.artemis.parallel_world.client.render.WaterStriderEntityRenderer;
import com.artemis.parallel_world.entity.TethysEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendereregistry.v1.EntityRendererRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.render.RenderLayer;

import java.awt.*;

import static com.artemis.parallel_world.block.TethysBlocks.GLOWFRUIT;
import static com.artemis.parallel_world.block.TethysBlocks.BASSWOOD_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.BLACKCURRANT_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.CHERRY_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.DOGWOOD_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.ELDERBERRY_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.GINKGO_SAPLING;
import static com.artemis.parallel_world.block.TethysBlocks.SWEETGUM_SAPLING;


@Environment(EnvType.CLIENT)
public class DimensionClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(GLOWFRUIT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BASSWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BLACKCURRANT_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CHERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DOGWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ELDERBERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SWEETGUM_SAPLING, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
            BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.DARK_OAK_LEAVES);
            return provider == null ? -1 : provider.getColor(block, world, pos, layer);
        }, GLOWFRUIT);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x48B518, GLOWFRUIT);

        EntityRendererRegistry.INSTANCE.register(TethysEntities.FLYING_CAT, (entityRenderDispatcher, context) -> new FlyingCatEntityRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(TethysEntities.TETHYS_TURTLE, (entityRenderDispatcher, context) -> new TethysTurtleEntityRenderer(entityRenderDispatcher));
        EntityRendererRegistry.INSTANCE.register(TethysEntities.WATER_STRIDER, (entityRenderDispatcher, context) -> new WaterStriderEntityRenderer(entityRenderDispatcher));
    }
}
