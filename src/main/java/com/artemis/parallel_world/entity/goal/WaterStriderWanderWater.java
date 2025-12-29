package com.artemis.parallel_world.entity.goal;

import com.artemis.parallel_world.entity.ai.LongDistanceTargeting;
import net.minecraft.entity.ai.goal.WanderAroundGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.Vec3d;

public class WaterStriderWanderWater extends WanderAroundGoal {

    public WaterStriderWanderWater(PathAwareEntity mob, double speed) {
        super(mob, speed);
    }

    @Override
    public boolean canStart() {
        // It's confusing, sometimes we look at the block the strider is in and sometimes on.
        // This one is looking at the block it is on.
        return (mob.getWorld().getFluidState(mob.getBlockPos()).isOf(Fluids.WATER))
                && mob.isOnGround()
                && super.canStart();
    }

    @Override
    public boolean shouldContinue() {
        return mob.getWorld().getFluidState(mob.getBlockPos()).isOf(Fluids.WATER) && !mob.getNavigation().isIdle();
    }

    @Override
    protected Vec3d getWanderTarget() {
        return LongDistanceTargeting.find(this.mob, 10, 6, 0);
    }

    public boolean shouldRunEveryTick() {
        return true;
    }
}
