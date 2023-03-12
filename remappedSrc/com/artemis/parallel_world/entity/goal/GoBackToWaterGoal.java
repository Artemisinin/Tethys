package com.artemis.parallel_world.entity.goal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class GoBackToWaterGoal extends MoveToTargetPosGoal {
    private final PathAwareEntity mob;

    public GoBackToWaterGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
        this.mob = mob;
    }

    public BlockPos getTargetPos() {
        return this.targetPos;
    }

    public double getDesiredSquaredDistanceToTarget() {
        return 1.0D;
    }

    public boolean shouldContinue() {
//        if (this.isTargetPos(this.mob.world, this.targetPos)) {
//            return this.mob.canWalkOnFluid(Fluids.WATER) ? !this.mob.world.getFluidState(this.mob.getBlockPos().down()).isIn(FluidTags.WATER) : !this.mob.isSubmergedInWater();
//        }
//        else { return false; }
        return !this.mob.isTouchingWater();
    }

    public boolean canStart() {
        //return (this.mob.canWalkOnFluid(Fluids.WATER) ? !this.mob.world.getFluidState(this.mob.getBlockPos().down()).isIn(FluidTags.WATER) : !this.mob.isSubmergedInWater()) && super.canStart();
        return !this.mob.isTouchingWater();
    }

    protected void startMovingToTarget() {
        this.mob.getNavigation().startMovingTo(this.targetPos.getX(), this.targetPos.getY(), this.targetPos.getZ(), this.speed);
    }

    public boolean shouldResetPath() {
        return this.tryingTime % 20 == 0;
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.WATER) && world.getBlockState(pos.up()).isAir();
    }
}

