package com.artemis.parallel_world.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.FluidBlock;
import net.minecraft.block.ShapeContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.FuzzyTargeting;
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
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;


public class WaterStriderEntity extends PathAwareEntity {
    protected WaterStriderEntity(EntityType<? extends WaterStriderEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new MoveControl(this);
    }

    public static DefaultAttributeContainer.Builder createWaterStriderAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6D);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new FleePlayerGoal(this, PlayerEntity.class, 8.0F, 1.0D, 1.0D));
        this.goalSelector.add(2, new WaterStriderWanderWater(this, 0.8D));
        this.goalSelector.add(2, new SeekWater(this, 0.3D));
    }

    @Override
    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
    }

    @Override
    protected EntityNavigation createNavigation(World world) {
        return new WaterStriderNavigation(this, world);
    }

    public boolean canWalkOnFluid(Fluid fluid) {
        return fluid.isIn(FluidTags.WATER);
    }

    @Override
    protected void onSwimmingStart() {
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return 0;
        }
    }

    static class WaterStriderNavigation extends MobNavigation {
        WaterStriderNavigation(WaterStriderEntity entity, World world) {
            super(entity, world);
        }

        @Override
        protected boolean canWalkOnPath(PathNodeType pathType) {
            if (pathType == PathNodeType.WATER) {
                return true;
            }
            return super.canWalkOnPath(pathType);
        }

        @Override
        public boolean isValidPosition(BlockPos pos) {
            BlockPos down = pos.down();
            return this.world.getBlockState(down).isOf(Blocks.WATER) || super.isValidPosition(pos);
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
        if (!this.firstUpdate && this.getFluidHeight(FluidTags.WATER) > 0) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.03D, 0.0D));
        }
        else {
            ShapeContext shapeContext = ShapeContext.of(this);
            if (shapeContext.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), true) && this.world.getFluidState(this.getBlockPos()).isOf(Fluids.WATER)) {
                this.setVelocity(this.getVelocity().x, 0, getVelocity().z);
                this.touchingWater = true;
                this.onGround = true;
            }
        }
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        this.checkBlockCollision();
        if (this.isInsideWaterOrBubbleColumn()) {
            this.fallDistance = 0.0F;
        } else {
            super.fall(heightDifference, onGround, landedState, landedPosition);
        }
    }

    // Goals

    static class FleePlayerGoal extends FleeEntityGoal<PlayerEntity> {

        private final TargetPredicate withinRangePredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.fleeDistance).setPredicate(inclusionSelector.and(extraInclusionSelector));

        public FleePlayerGoal(PathAwareEntity mob, Class<PlayerEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
            super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
        }

        @Override
        public boolean canStart() {
            //this.targetEntity = this.mob.world.getClosestEntity(this.classToFleeFrom, this.withinRangePredicate, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ(), this.mob.getBoundingBox().expand((double) this.fleeDistance, 3.0D, (double) this.fleeDistance));
            this.targetEntity = this.mob.world.getClosestEntity(this.mob.world.getEntitiesByClass(this.classToFleeFrom, this.mob.getBoundingBox().expand(this.fleeDistance, 3.0, this.fleeDistance), livingEntity -> true), this.withinRangePredicate, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
            //if (this.targetEntity == null || !this.mob.world.getFluidState(this.mob.getBlockPos().down()).isIn(FluidTags.WATER)) {
            if (this.targetEntity == null || !this.mob.world.getFluidState(this.mob.getBlockPos().down()).isIn(FluidTags.WATER)) {
                return false;
            } else {
                Vec3d vec3d = NoPenaltyTargeting.findFrom(this.mob, 16, 0, this.targetEntity.getPos());
                if (vec3d == null) {
                    return false;
                }
                BlockPos target = new BlockPos(vec3d.x, vec3d.y, vec3d.z);
                if (this.mob.world.getFluidState(target.down()).isOf(Fluids.WATER)) {
                    if (this.targetEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < this.targetEntity.squaredDistanceTo(this.mob)) {
                        return false;
                    } else {
                        this.fleePath = this.fleeingEntityNavigation.findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                        return this.fleePath != null;
                    }
                }
                return false;
            }
        }
    }

    static class WaterStriderWanderWater extends WanderAroundGoal {

        public WaterStriderWanderWater(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }

        BlockPos down;

        @Override
        public boolean canStart() {
            down = mob.getBlockPos().down();
            return (mob.world.getFluidState(down).isOf(Fluids.WATER)) && super.canStart();
        }

        @Override
        public boolean shouldContinue() {
            down = mob.getBlockPos().down();
            if (!mob.world.getFluidState(down).isOf(Fluids.WATER)) {
                return false;
            }
            // Not sure if like return to water where it needs go to the actual water block to target correctly.
            if (targetY == down.getY()) {
                return false;
            }
            else return !this.mob.getNavigation().isIdle();
        }

        @Override
        protected Vec3d getWanderTarget() {
            return NoPenaltyTargeting.find(this.mob, 10, 0);
        }

        @Override
        public void start() {
            this.mob.getNavigation().startMovingTo(this.targetX, this.targetY - 0.5, this.targetZ, this.speed);
        }
    }

    static class SeekWater extends WanderAroundFarGoal {

        public SeekWater(PathAwareEntity mob, double speed) {
            super(mob, speed);
        }

        protected Vec3d findWaterPos() {
            int maxHorizontal = 15;
            int maxVertical = 1;
            BlockPos origin = this.mob.getBlockPos();
            BlockPos.Mutable mutable = new BlockPos.Mutable();
            int k = 0;
            while (k <= maxVertical) {
                for (int l = 0; l < maxHorizontal; ++l) {
                    int m = 0;
                    while (m <= l) {
                        int n = ((m < l) && (m > -l)) ? l : 0;
                        while (n <= l) {
                            mutable.set(origin, m, k - 1, n);
                            if (this.mob.isInWalkTargetRange(mutable) && this.mob.world.getFluidState(mutable).isOf(Fluids.WATER)) {
                                return mutable.toCenterPos();
                            }
                            n = n > 0 ? -n : 1 - n;
                        }
                        m = m > 0 ? -m : 1 - m;
                    }
                }
                k = k > 0 ? -k : 1 - k;
            }
            return null;
        }

        @Override
        protected Vec3d getWanderTarget() {
            Vec3d target = findWaterPos();
            if (target != null) {
                return findWaterPos();
            }
            else if (this.mob.getRandom().nextFloat() >= this.probability) {
                return FuzzyTargeting.find(this.mob, 10, 7);
            }
            else return super.getWanderTarget();
        }

        @Override
        public boolean canStart() {
            BlockPos down = mob.getBlockPos().down();
            return (!mob.world.getFluidState(down).isOf(Fluids.WATER) && super.canStart());
            //return (!mob.isTouchingWater() && super.canStart());
        }

        @Override
        public boolean shouldContinue() {
            BlockPos down = mob.getBlockPos().down();
            if (mob.world.getFluidState(down).isOf(Fluids.WATER)) {
                return false;
            }
            else return !this.mob.getNavigation().isIdle();
        }
    }
}
