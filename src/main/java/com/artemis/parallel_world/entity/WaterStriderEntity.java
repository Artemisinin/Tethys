package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.entity.ai.WaterStriderPathNodeMaker;
import com.artemis.parallel_world.entity.goal.JoinOtherWaterStriderGoal;
import com.artemis.parallel_world.entity.goal.WaterStriderSeekWater;
import com.artemis.parallel_world.entity.goal.WaterStriderWanderWater;
import net.minecraft.block.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkSectionPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import net.minecraft.world.chunk.WorldChunk;


public class WaterStriderEntity extends PathAwareEntity {

    private static final TrackedData<Integer> STATUS = DataTracker.registerData(WaterStriderEntity.class, TrackedDataHandlerRegistry.INTEGER);

    protected WaterStriderEntity(EntityType<? extends WaterStriderEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new MoveControl(this);
        this.navigation = new WaterStriderNavigation(this, this.getWorld());
    }

    @Override
    protected void initDataTracker(DataTracker.Builder builder) {
        super.initDataTracker(builder);
        // Status of 1 means it is at rest.
        // Status of 2 means on the water and moving.
        // Status of 3 means it is on land and moving.
        builder.add(STATUS, 1);
    }

    public static DefaultAttributeContainer.Builder createWaterStriderAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.6D);
    }

    public void setAtRest() {
        this.dataTracker.set(STATUS, 1);
    }

    public void setGliding() {
        this.dataTracker.set(STATUS, 2);
    }

    public void setWalkingOnLand() {
        this.dataTracker.set(STATUS, 3);
    }

    public Integer getStatus() {
        return this.dataTracker.get(STATUS);
    }

    @Override
    public boolean canWalkOnFluid(FluidState state) {
        return state.isIn(FluidTags.WATER);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(1, new WaterStriderSeekWater(this, 0.3D));
        this.goalSelector.add(2, new WaterStriderWanderWater(this, 0.7D));
        this.goalSelector.add(2, new JoinOtherWaterStriderGoal(this, 0.7D, 4, 6));
        //this.goalSelector.add(3, new WaterStriderFleePlayerGoal(this, PlayerEntity.class, 8.0F, 1.0D, 1.0D));
    }

    @Override
    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
        FluidState fluidStateDown = this.getWorld().getFluidState(getBlockPos().down());
        if (this.getVelocity().horizontalLength() > 1.0E-7D && fluidStateDown.isOf(Fluids.WATER)) {
            this.setGliding();
        } else if (this.getVelocity().horizontalLength() > 1.0E-7D && !fluidStateDown.isOf(Fluids.WATER)) {
            this.setWalkingOnLand();
        } else setAtRest();
    }

    @Override
    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return 0;
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
        if (this.getWorld().isTopSolid(this.getBlockPos().down(), this)
        && !this.isInsideWaterOrBubbleColumn()) {
            setPathfindingPenalty(PathNodeType.WATER_BORDER, 0);
        }
        if (!this.firstUpdate && (this.getFluidHeight(FluidTags.WATER) > 0 || this.isSubmergedInWater())) {
            this.setVelocity(this.getVelocity().add(0.0D, Math.abs(this.getFinalGravity() / 2), 0.0D));
        } else {
            ShapeContext striderShape = ShapeContext.of(this);
            if (striderShape.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), false)
                    && this.getWorld().getFluidState(this.getBlockPos()).isOf(Fluids.WATER)) {
                this.setVelocity(this.getVelocity().x, 0.0D, getVelocity().z);
                this.touchingWater = true;
                this.setOnGround(true);
                this.setPathfindingPenalty(PathNodeType.WATER_BORDER, -1.0F);
            }
        }
    }

    @Override
    protected void fall(double heightDifference, boolean onGround, BlockState landedState, BlockPos landedPosition) {
        this.checkBlockCollision();
        if (this.getWorld().getFluidState(landedPosition).isOf(Fluids.WATER)) {
            this.fallDistance = 0.0F;
        } else {
            super.fall(heightDifference, onGround, landedState, landedPosition);
        }
    }

    @Override
    public void onLanding() {
        super.onLanding();
        updateFloating();
    }

    @Override
    protected void onSwimmingStart() {
    }

    static class WaterStriderNavigation extends MobNavigation {
        WaterStriderNavigation(WaterStriderEntity entity, World world) {
            super(entity, world);
        }

        @Override
        protected PathNodeNavigator createPathNodeNavigator(int range) {
            // LandPathNodemaker should work, but it checks for water and then
            // doesn't check if you could walk on water and retargets the river bed.
            this.nodeMaker = new WaterStriderPathNodeMaker();
            this.nodeMaker.setCanEnterOpenDoors(true);
            return new PathNodeNavigator(this.nodeMaker, range);
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
            return this.world.getBlockState(down).isOf(Blocks.WATER)
                    || super.isValidPosition(pos);
        }

        @Override
        public void setCanSwim(boolean canSwim) {
        }
    }
}
