package com.artemis.parallel_world.entity.goal;

import net.minecraft.entity.ai.FuzzyTargeting;
import net.minecraft.entity.ai.goal.WanderAroundFarGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class WaterStriderSeekWater extends WanderAroundFarGoal {

    public WaterStriderSeekWater(PathAwareEntity mob, double speed) {
        super(mob, speed);
    }

    protected Vec3d findWaterPos() {
        int maxHorizontal = 15;
        int maxVertical = 5;
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
                        if (this.mob.isInWalkTargetRange(mutable) &&
                                this.mob.getWorld().getFluidState(mutable).isOf(Fluids.WATER)) {
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
            return target;
        }
        else if (this.mob.getRandom().nextFloat() >= this.probability) {
            return FuzzyTargeting.find(this.mob, 10, 7);
        }
        else return super.getWanderTarget();
    }

    @Override
    public boolean canStart() {
        return (!mob.getWorld().getFluidState(mob.getBlockPos()).isOf(Fluids.WATER))
                && super.canStart();
    }

    public boolean shouldRunEveryTick() {
        return true;
    }
}
