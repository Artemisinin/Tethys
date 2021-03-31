package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.entity.goal.WaterStriderWanderGoal;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluid;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import java.util.Random;

public class WaterStriderEntity extends PathAwareEntity {
    protected WaterStriderEntity(EntityType<? extends WaterStriderEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new MoveControl(this);
    }

    public static DefaultAttributeContainer.Builder createWaterStriderAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6D);
    }

    protected void initGoals() {
        this.goalSelector.add(1, new WaterStriderEntity.GoBackToWaterGoal(this, 0.3D));
        this.goalSelector.add(2, new FleePlayerGoal(this, PlayerEntity.class, 8.0F, 1.0D, 1.0D));
        this.goalSelector.add(2, new WaterStriderWanderGoal(this, 0.8D, 60));
    }

    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
    }

    protected EntityNavigation createNavigation(World world) {
        return new WaterStriderNavigation(this, world);
    }

    public boolean canWalkOnFluid(Fluid fluid) {
        return fluid.isIn(FluidTags.WATER);
    }

    protected void onSwimmingStart() {
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).isAir() && world.getBlockState(pos.down()).getFluidState().isIn(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return this.isSubmergedInWater() ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }

    static class WaterStriderNavigation extends MobNavigation {
        WaterStriderNavigation(WaterStriderEntity entity, World world) {
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
            BlockPos blockPos = pos.down();
            return this.world.getBlockState(blockPos).isOf(Blocks.WATER) || super.isValidPosition(pos);
        }
    }

    public static boolean canSpawn(EntityType<WaterStriderEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        BlockPos.Mutable mutable = pos.mutableCopy();
        do {
            mutable.move(Direction.UP);
        } while (world.getFluidState(mutable).isIn(FluidTags.WATER));
        return world.getBlockState(mutable).isAir();
    }

    private void updateFloating() {
        if (world.getBlockState(this.getBlockPos().down()).getFluidState().isIn(FluidTags.WATER) && world.getBlockState(this.getBlockPos()).isAir()) {
            this.onGround = true;
            this.setVelocity(getVelocity().add(0.0D, 0.05D, 0.0D));
        }
        else if (this.getBlockState().getFluidState().isIn(FluidTags.WATER)) {
            this.onGround = false;
            this.setVelocity(getVelocity().add(0.0D, 0.08D, 0.0D));
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

    static class GoBackToWaterGoal extends MoveToTargetPosGoal {
        private final WaterStriderEntity waterStrider;

        private GoBackToWaterGoal(WaterStriderEntity waterStrider, double speed) {
            super(waterStrider, speed, 12, 4);
            this.waterStrider = waterStrider;
        }

        public BlockPos getTargetPos() {
            return this.targetPos;
        }

        public boolean shouldContinue() {
            return !this.waterStrider.isInsideWaterOrBubbleColumn() && this.isTargetPos(this.waterStrider.world, this.targetPos);
        }

        public boolean canStart() {
            return !this.waterStrider.isInsideWaterOrBubbleColumn() && super.canStart();
        }

        public boolean shouldResetPath() {
            return this.tryingTime % 20 == 0;
        }

        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER) && world.getBlockState(pos.up()).canPathfindThrough(world, pos, NavigationType.LAND);
        }
    }

    static class FleePlayerGoal extends FleeEntityGoal<PlayerEntity> {

        private TargetPredicate withinRangePredicate = (new TargetPredicate()).setBaseMaxDistance(this.fleeDistance).setPredicate(inclusionSelector.and(extraInclusionSelector));

        public FleePlayerGoal(PathAwareEntity mob, Class<PlayerEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
        }

        public boolean canStart() {
            this.targetEntity = this.mob.world.getClosestEntity(this.classToFleeFrom, this.withinRangePredicate, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().expand((double) this.fleeDistance, 3.0D, (double) this.fleeDistance));
            if (this.targetEntity == null) {
                return false;
            } else {
                Vec3d vec3d = NoPenaltyTargeting.find(this.mob, 16, 0, this.targetEntity.getPos());
                if (vec3d == null) {
                    return false;
                } else if (this.targetEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < this.targetEntity.squaredDistanceTo(this.mob)) {
                    return false;
                } else {
                    this.fleePath = this.fleeingEntityNavigation.findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                    return this.fleePath != null;
                }
            }
        }
    }
}
