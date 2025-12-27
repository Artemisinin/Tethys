package com.artemis.parallel_world.entity.goal;

import com.artemis.parallel_world.entity.ai.LongDistanceTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class WaterStriderWanderWater extends WanderAroundGoal {

    public WaterStriderWanderWater(PathAwareEntity mob, double speed) {
        super(mob, speed);
    }

    BlockPos down;

    @Override
    public boolean canStart() {
        down = mob.getBlockPos().down();
        return (mob.getWorld().getFluidState(down).isOf(Fluids.WATER)) &&
                mob.isOnGround() &&
                super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle();
    }

    @Override
    protected Vec3d getWanderTarget() {
        return LongDistanceTargeting.find(this.mob, 12, 6, 0);
    }

    public boolean shouldRunEveryTick() {
        return true;
    }
}
