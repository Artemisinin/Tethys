package com.artemis.parallel_world.client;


import com.artemis.parallel_world.client.render.entity.TethysEntityRenderers;
import com.artemis.parallel_world.client.render.entity.model.TethysEntityModelLayers;
import com.artemis.parallel_world.client.render.entity.model.TethysEntityModels;
import com.artemis.parallel_world.client.render.entity.model.TethysTexturedModelData;
import com.artemis.parallel_world.entity.EntitySpawnPacket;
import com.artemis.parallel_world.entity.GlowLichenBallEntity;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.blockrenderlayer.v1.BlockRenderLayerMap;
import net.fabricmc.fabric.api.client.rendering.v1.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.render.RenderLayer;


import static com.artemis.parallel_world.block.TethysBlocks.*;


@Environment(EnvType.CLIENT)
public class DimensionClient implements ClientModInitializer {

    /*public void receiveEntityPacket() {
        ClientSidePacketRegistry.INSTANCE.register(GlowLichenBallEntity.GLOW_LICHEN_BALL_SPAWN_PACKET, (ctx, byteBuf) -> {
            EntityType<?> entityType = Registry.ENTITY_TYPE.get(byteBuf
                    readVarInt());
            UUID uuid = byteBuf.readUuid();
            int entityId = byteBuf.readVarInt();
            Vec3d pos = EntitySpawnPacket.PacketBufUtil.readVec3d(byteBuf);
            AtomicReference<Float> pitch = new AtomicReference<>(EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf));
            AtomicReference<Float> yaw = new AtomicReference<>(EntitySpawnPacket.PacketBufUtil.readAngle(byteBuf));
            ctx.getTaskQueue().execute(() -> {
                if (MinecraftClient.getInstance().world == null)
                    throw new IllegalStateException("Tried to spawn entity in a null world!");
                Entity entity = entityType.create(MinecraftClient.getInstance().world);
                if (entity == null)
                    throw new IllegalStateException("Failed to create instance of entity \"" + Registry.ENTITY_TYPE.getId(entityType) + "\"!");
                entity.updateTrackedPosition(pos);
                entity.setPos(pos.x, pos.y, pos.z);
                pitch.set(entity.getPitch());
                yaw.set(entity.getYaw());
                entity.setEntityId(entityId);
                entity.setUuid(uuid);
                MinecraftClient.getInstance().world.addEntity(entityId, entity);
            });
        });
    }*/

    @Override
    public void onInitializeClient() {
        BlockRenderLayerMap.INSTANCE.putBlock(BASSWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(BLACKCURRANT_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CAVE_GLOWLEAF, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(CHERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(DOGWOOD_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(ELDERBERRY_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GINKGO_SAPLING, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLOW_FLOWER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(GLOWFRUIT, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(HEATHER, RenderLayer.getCutout());
        BlockRenderLayerMap.INSTANCE.putBlock(SWEETGUM_SAPLING, RenderLayer.getCutout());

        ColorProviderRegistry.BLOCK.register((block, world, pos, layer) -> {
            BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.DARK_OAK_LEAVES);
            return provider == null ? -1 : provider.getColor(block, world, pos, layer);
        }, GLOWFRUIT);

        ColorProviderRegistry.ITEM.register((stack, tintIndex) -> 0x48B518, GLOWFRUIT);

        TethysEntityModelLayers.registerModelLayers();
        TethysTexturedModelData.registerTexturedModelData();
        TethysEntityModels.registerEntityModels();
        TethysEntityRenderers.registerEntityRenderers();
        //receiveEntityPacket();
    }
}
