package com.artemis.parallel_world.entity.goal;

import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class GoBackToWaterGoal extends MoveToTargetPosGoal {
    private final PathAwareEntity mob;
    private boolean onWater;

    public GoBackToWaterGoal(PathAwareEntity mob, double speed, int range, int maxYDifference) {
        super(mob, speed, range, maxYDifference);
        this.mob = mob;
    }

    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos).isOf(Blocks.WATER) && world.getBlockState(pos.up()).isAir();
    }

    // If left as super the strider goes to the edge of the water and then stops.
    @Override
    protected BlockPos getTargetPos() {
        return this.targetPos;
    }

    @Override
    public boolean canStart() {
        BlockPos down = this.mob.getBlockPos().down();
        return !this.mob.world.getFluidState(down).isIn(FluidTags.WATER) && super.canStart();
    }

    // Since it can never actually reach the target block because it's not allowed
    // to go down into the water, you have to stop moving when over water.
    @Override
    public boolean shouldContinue() {
        BlockPos down = this.mob.getBlockPos().down();
        if (this.mob.world.getFluidState(down).isIn(FluidTags.WATER)) {
            return false;
        }
        else return super.shouldContinue();
    }

    @Override
    public boolean shouldResetPath() {
        return this.tryingTime % 20 == 0;
    }
}

