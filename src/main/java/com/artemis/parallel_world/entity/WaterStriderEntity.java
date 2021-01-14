package com.artemis.parallel_world.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;

import java.util.Random;

public class WaterStriderEntity extends AnimalEntity {
    protected WaterStriderEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createWaterStriderAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6D);
    }

    public static boolean canSpawn(EntityType<WaterStriderEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        do {
            mutable.move(Direction.UP);
        } while(world.getFluidState(mutable).isIn(FluidTags.WATER));
        return world.getBlockState(mutable).isAir();
    }

    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.6D));
        this.goalSelector.add(4, new WaterStriderEntity.GoBackToWaterGoal(this, 0.3D));
        this.goalSelector.add(4, new WaterStriderEntity.FleeGoal(this, PlayerEntity.class, 8.0F, 0.6D, 0.8D));
        this.goalSelector.add(7, new WanderAroundFarGoal(this, 0.6D, 60));
    }

    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
    }

    private void updateFloating() {
        if (this.isSubmergedInWater()) {
            ShapeContext shapeContext = ShapeContext.of(this);
            if (shapeContext.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), true) && !this.world.getFluidState(this.getBlockPos().up()).isIn(FluidTags.WATER)) {
                this.onGround = true;
            } else {
                this.setVelocity(this.getVelocity().multiply(0.5D).add(0.0D, 0.05D, 0.0D));
            }
        }
    }

    protected EntityNavigation createNavigation(World world) {
        return new WaterStriderEntity.Navigation(this, world);
    }

    public boolean canWalkOnFluid(Fluid fluid) {
        return fluid.isIn(FluidTags.WATER);
    }

    static class GoBackToWaterGoal extends MoveToTargetPosGoal {
        private final WaterStriderEntity waterStrider;

        private GoBackToWaterGoal(WaterStriderEntity waterStrider, double speed) {
            super(waterStrider, speed, 8, 2);
            this.waterStrider = waterStrider;
        }

        public BlockPos getTargetPos() {
            return this.targetPos;
        }

        public boolean shouldContinue() {
//            return !this.waterStrider.isSubmergedInWater() && this.isTargetPos(this.waterStrider.world, this.targetPos);
            return this.isTargetPos(this.waterStrider.world, this.targetPos);
        }

        public boolean canStart() {
//            return !this.waterStrider.isSubmergedInWater() && super.canStart();
            return super.canStart();
        }

        public boolean shouldResetPath() {
            return this.tryingTime % 20 == 0;
        }

        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER) && world.getBlockState(pos.up()).canPathfindThrough(world, pos, NavigationType.LAND);
        }
    }

    static class FleeGoal<T extends LivingEntity> extends FleeEntityGoal<T> {
        private final WaterStriderEntity waterStriderEntity;

        public FleeGoal(WaterStriderEntity waterStriderEntity, Class<T> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(waterStriderEntity, fleeFromType, distance, slowSpeed, fastSpeed);
            this.waterStriderEntity = waterStriderEntity;
        }

        public boolean canStart() {
            return super.canStart();
        }
    }

    static class Navigation extends MobNavigation {
        Navigation(WaterStriderEntity entity, World world) {
            super(entity, world);
        }
        protected PathNodeNavigator createPathNodeNavigator(int range) {
            this.nodeMaker = new LandPathNodeMaker();
            return new PathNodeNavigator(this.nodeMaker, range);
        }
        protected boolean canWalkOnPath(PathNodeType pathType) {
            return pathType == PathNodeType.WATER || super.canWalkOnPath(pathType);
        }
        public boolean isValidPosition(BlockPos pos) {
            return this.world.getBlockState(pos).isOf(Blocks.WATER) || super.isValidPosition(pos);
        }
    }

    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        this.checkBlockCollision();
        if (this.isInsideWaterOrBubbleColumn()) {
            this.fallDistance = 0.0F;
        } else {
            super.fall(heightDifference, onGround, landedState, landedPosition);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }
}
