package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.entity.ai.WaterStriderPathNodeMaker;
import com.artemis.parallel_world.entity.goal.*;
import net.minecraft.block.*;
import net.minecraft.entity.Entity;
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
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.*;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;

import net.minecraft.entity.vehicle.BoatEntity;

import java.util.List;

public class WaterStriderEntity extends PathAwareEntity {

    private static final TrackedData<Integer> STATUS = DataTracker.registerData(WaterStriderEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private boolean surfacing;

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
        this.goalSelector.add(2, new WaterStriderFleePlayerGoal(this, PlayerEntity.class, 5.0F, 0.7D, 0.9D));
        this.goalSelector.add(2, new AvoidBoatGoal(this, 8.0F, 0.7D));
        this.goalSelector.add(3, new WaterStriderWanderWater(this, 0.7D));
        this.goalSelector.add(3, new JoinOtherWaterStriderGoal(this, 0.7D, 4, 10, random));
    }

    @Override
    public void tick() {
        super.tick();
        this.updateFloating();
        this.checkBlockCollision();
        this.getOffTheDamnBoat();
        FluidState fluidState = this.getWorld().getFluidState(getBlockPos());
        if (this.getVelocity().horizontalLength() > 1.0E-7D && fluidState.isOf(Fluids.WATER)) {
            this.setGliding();
        } else if (this.getVelocity().horizontalLength() > 1.0E-7D && !fluidState.isOf(Fluids.WATER)) {
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
        if (this.getWorld().isTopSolid(this.getBlockPos(), this)
                && !this.isInsideWaterOrBubbleColumn()) {
            setPathfindingPenalty(PathNodeType.WALKABLE, 0);
            setPathfindingPenalty(PathNodeType.WATER_BORDER, 0);
        }
        // If this doesn't check if it's already surfacing the velocity will be additive, resulting in them
        // not surfacing if the depth is too low and rocketing out of the water if the depth is high.
        // Before updating fall(), they would hit the riverbed and rocket right back up.
        if (!this.firstUpdate && !surfacing && (this.getFluidHeight(FluidTags.WATER) > 0 || this.isSubmergedInWater())) {
            this.setVelocity(this.getVelocity().add(0.0D, 0.35, 0.0D));
            surfacing = true;
        } else {
            ShapeContext striderShape = ShapeContext.of(this);
            if (striderShape.isAbove(FluidBlock.COLLISION_SHAPE, this.getBlockPos(), false)
                    && this.getWorld().getFluidState(this.getBlockPos()).isOf(Fluids.WATER)) {
                this.setVelocity(this.getVelocity().x, 0.0D, getVelocity().z);
                this.touchingWater = true;
                this.setOnGround(true);
                surfacing = false;
                this.setPathfindingPenalty(PathNodeType.WALKABLE, 8.0F);
                this.setPathfindingPenalty(PathNodeType.WATER_BORDER, 8.0F);
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

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    protected void getOffTheDamnBoat() {
        List<BoatEntity> boatList = this.getWorld().getNonSpectatingEntities(BoatEntity.class, this.getBoundingBox().expand(0, 1, 0));
        for (BoatEntity boatEntity : boatList) {
            if (boatEntity.getControllingPassenger() == null
            && boatEntity.getPos().getY() < this.getPos().getY()) {
                double newX = this.random.nextBoolean() ? -0.05 : 0.05;
                double newZ = this.random.nextBoolean() ? -0.05 : 0.05;
                this.addVelocity(newX, 0.0025, newZ);
                break;
            }
        }
    }

    @Override
    public void pushAwayFrom(Entity entity) {
        if (!entity.noClip && !this.noClip) {
            double d = entity.getX() - this.getX();
            double e = entity.getZ() - this.getZ();
            double f = MathHelper.absMax(d, e);
            if (f >= 0.01F) {
                f = Math.sqrt(f);
                d /= f;
                e /= f;
                double g = 1.0 / f;
                if (g > 1.0) {
                    g = 1.0;
                }
                d *= g;
                e *= g;
                d *= 0.05F;
                e *= 0.05F;
                this.addVelocity(-d, 0.0, -e);
            }
        }
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
