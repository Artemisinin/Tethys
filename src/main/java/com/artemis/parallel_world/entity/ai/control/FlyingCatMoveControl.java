package com.artemis.parallel_world.entity.ai.control;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;


public class FlyingCatMoveControl extends MoveControl {
    protected double targetX;
    protected double targetY;
    protected double targetZ;
    protected double speed;
    protected float forwardMovement;
    protected float sidewaysMovement;
    private final int maxPitchChange;
    protected FlyingCatMoveControl.State state;

    public FlyingCatMoveControl(MobEntity entity, int maxPitchChange) {
        super(entity);
        this.maxPitchChange = maxPitchChange;
    }

    @Override
    public void tick() {
        if (this.state == State.MOVE_TO && entity.hasNoGravity()) {
            this.state = State.WAIT;
            double deltaX = this.targetX - this.entity.getX();
            double deltaY = this.targetY - this.entity.getY();
            double deltaZ = this.targetZ - this.entity.getZ();
            // This is basically the equation for a sphere, and g is the radius squared.
            double g = deltaX * deltaX + deltaY * deltaY + deltaZ * deltaZ;
            if (g < 2.5E-7D) {
                this.entity.setUpwardSpeed(0.0F);
                this.entity.setForwardSpeed(0.0F);
                return;
            }

            // The *57.3 converts radians to degrees.
            float h = (float) (MathHelper.atan2(deltaZ, deltaX) * 57.3D) - 90.0F;
            this.entity.yaw = this.changeAngle(this.entity.yaw, h, 90.0F);
            float speed;
            if (this.entity.isOnGround()) {
                speed = (float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
            } else {
                speed = (float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_FLYING_SPEED));
            }

            this.entity.setMovementSpeed(speed);
            // k is the hypotenuse, the shortest straight line distance between the entity and target.
            double k = MathHelper.sqrt(deltaX * deltaX + deltaZ * deltaZ);
            float l = (float) (-(MathHelper.atan2(deltaY, k) * 57.3D));
            this.entity.pitch = this.changeAngle(this.entity.pitch, l, (float) this.maxPitchChange);
            this.entity.setUpwardSpeed(deltaY > 0.0D ? speed : -speed);

        } else if (this.state == State.MOVE_TO && !entity.hasNoGravity()) {
            if (this.state == State.WAIT) {
                float f = (float) this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED);
                float g = (float) this.speed * f;
                float h = this.forwardMovement;
                float i = this.sidewaysMovement;
                float j = MathHelper.sqrt(h * h + i * i);
                if (j < 1.0F) {
                    j = 1.0F;
                }

                j = g / j;
                h *= j;
                i *= j;
                float k = MathHelper.sin(this.entity.yaw * 0.017453292F);
                float l = MathHelper.cos(this.entity.yaw * 0.017453292F);
                float m = h * l - i * k;
                float q = i * l + h * k;
                if (!this.checkWalkable(m, q)) {
                    this.forwardMovement = 1.0F;
                    this.sidewaysMovement = 0.0F;
                }

                this.entity.setMovementSpeed(g);
                this.entity.setForwardSpeed(this.forwardMovement);
                this.entity.setSidewaysSpeed(this.sidewaysMovement);
                this.state = FlyingCatMoveControl.State.WAIT;
            } else if (this.state == FlyingCatMoveControl.State.MOVE_TO) {
                this.state = FlyingCatMoveControl.State.WAIT;
                double d = this.targetX - this.entity.getX();
                double e = this.targetZ - this.entity.getZ();
                double o = this.targetY - this.entity.getY();
                double p = d * d + o * o + e * e;
                if (p < 2.500000277905201E-7D) {
                    this.entity.setForwardSpeed(0.0F);
                    return;
                }

                float q = (float) (MathHelper.atan2(e, d) * 57.3D) - 90.0F;
                this.entity.yaw = this.changeAngle(this.entity.yaw, q, 90.0F);
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                BlockPos blockPos = this.entity.getBlockPos();
                BlockState blockState = this.entity.world.getBlockState(blockPos);
                Block block = blockState.getBlock();
                VoxelShape voxelShape = blockState.getCollisionShape(this.entity.world, blockPos);
                if (o > (double) this.entity.stepHeight && d * d + e * e < (double) Math.max(1.0F, this.entity.getWidth()) || !voxelShape.isEmpty() && this.entity.getY() < voxelShape.getMax(Direction.Axis.Y) + (double) blockPos.getY() && !block.isIn(BlockTags.DOORS) && !block.isIn(BlockTags.FENCES)) {
                    this.entity.getJumpControl().setActive();
                    this.state = FlyingCatMoveControl.State.JUMPING;
                }
            } else if (this.state == FlyingCatMoveControl.State.JUMPING) {
                this.entity.setMovementSpeed((float) (this.speed * this.entity.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED)));
                if (this.entity.isOnGround()) {
                    this.state = FlyingCatMoveControl.State.WAIT;
                }
            } else {
                this.entity.setUpwardSpeed(0.0F);
                this.entity.setForwardSpeed(0.0F);
            }
        }
    }

    private boolean checkWalkable(float f, float g) {
        EntityNavigation entityNavigation = this.entity.getNavigation();
        if (entityNavigation != null) {
            PathNodeMaker pathNodeMaker = entityNavigation.getNodeMaker();
            if (pathNodeMaker != null && pathNodeMaker.getDefaultNodeType(
                        this.entity.world,
                        MathHelper.floor(this.entity.getX() + (double)f),
                        MathHelper.floor(this.entity.getY()),
                        MathHelper.floor(this.entity.getZ() + (double)g))
                    != PathNodeType.WALKABLE)
                {
                return false;
            }
        }
        return true;
    }
}