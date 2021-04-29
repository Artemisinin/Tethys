package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.item.TethysItems;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.projectile.thrown.ThrownItemEntity;
import net.minecraft.fluid.*;
import net.minecraft.item.Item;
import net.minecraft.network.Packet;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;

import java.util.Map;


public class GlowLichenBallEntity extends ThrownItemEntity {
    public GlowLichenBallEntity(EntityType<? extends ThrownItemEntity> entityType, World world) {
        super(entityType, world);
    }

    public GlowLichenBallEntity(World world, LivingEntity owner) {
        super(TethysEntities.GLOW_LICHEN_BALL, owner, world);
    }

    public GlowLichenBallEntity(World world, double x, double y, double z) {
        super(TethysEntities.GLOW_LICHEN_BALL, x, y, z, world);
    }

    public static final Identifier GLOW_LICHEN_BALL_SPAWN_PACKET = new Identifier("parallel_world", "glow_lichen_ball_spawn_packet");

    @Override
    public Packet createSpawnPacket() {
        return EntitySpawnPacket.create(this, GLOW_LICHEN_BALL_SPAWN_PACKET);
    }

    @Override
    protected Item getDefaultItem() {
        return TethysItems.GLOW_LICHEN_BALL;
    }

    protected void onBlockHit(BlockHitResult blockHitResult) {
        // Properties
        Map<Direction, BooleanProperty> FACING_PROPERTIES = ConnectingBlock.FACING_PROPERTIES;
        BooleanProperty WATERLOGGED = Properties.WATERLOGGED;
        // BlockStates and BlockPositions
        BlockState hitPosState = this.world.getBlockState(blockHitResult.getBlockPos());
        Direction sideHit = blockHitResult.getSide();
        BlockPos placementPos = blockHitResult.getBlockPos().offset(sideHit);
        BlockState placementPosState = this.world.getBlockState(blockHitResult.getBlockPos().offset(sideHit));
        //BlockState glowLichenPlacementState = Blocks.GLOW_LICHEN.getDefaultState().with(FACING_PROPERTIES.get(sideHit.getOpposite()), true);
        BlockState glowLichenPlacementState = AbstractLichenBlock.withNoDirections(Blocks.GLOW_LICHEN).with(FACING_PROPERTIES.get(sideHit.getOpposite()), true);
        hitPosState.onProjectileHit(this.world, hitPosState, blockHitResult, this);

        // Projectile gets "eaten" by certain blocks--grass, ferns, torches, snow (layers).  Check for these first and drop the item if found.
        // Disallowed placing on magma because that's just weird.
        if (placementPosState.getMaterial() == Material.PLANT || placementPosState.getBlock() instanceof TorchBlock || placementPosState.isOf(Blocks.SNOW) || placementPosState.isOf(Blocks.MAGMA_BLOCK) ) {
            this.dropItem(TethysItems.GLOW_LICHEN_BALL);
            this.remove(RemovalReason.DISCARDED);
        } else if (((AbstractLichenBlock) Blocks.GLOW_LICHEN).canPlaceAt(glowLichenPlacementState, world, placementPos)) {
            if (placementPosState.getFluidState().isEqualAndStill(Fluids.WATER)) {
                world.setBlockState(placementPos, glowLichenPlacementState.with(WATERLOGGED, true));
            } else {
                world.setBlockState(placementPos, glowLichenPlacementState);
            }
            this.remove(RemovalReason.DISCARDED);
        } else {
            this.dropItem(TethysItems.GLOW_LICHEN_BALL);
            this.remove(RemovalReason.DISCARDED);
        }
    }
}