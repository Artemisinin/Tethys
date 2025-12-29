package com.artemis.parallel_world.entity.goal;

import com.artemis.parallel_world.entity.WaterStriderEntity;
import net.minecraft.entity.ai.control.LookControl;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import org.jetbrains.annotations.Nullable;


import java.util.EnumSet;
import java.util.List;
import java.util.function.Predicate;

public class JoinOtherWaterStriderGoal extends Goal {

    private final WaterStriderEntity strider;
    private final Predicate<MobEntity> targetPredicate;
    @Nullable
    private MobEntity target;
    private final double speed;
    private final EntityNavigation navigation;
    private int updateCountdownTicks;
    private final float minDistance;
    private final float maxDistance;
    private final Random random;

    public JoinOtherWaterStriderGoal (WaterStriderEntity strider, double speed, float minDistance, float maxDistance, Random random) {
        this.strider = strider;
        this.targetPredicate = target -> target != null && strider.getClass() == target.getClass();
        this.speed = speed;
        this.navigation = strider.getNavigation();
        this.minDistance = minDistance;
        this.maxDistance = maxDistance;
        this.random = random;
        this.setControls(EnumSet.of(Goal.Control.MOVE, Goal.Control.LOOK));
    }

    @Override
    public boolean canStart() {
        BlockPos down = strider.getBlockPos().down();
        if (!strider.getWorld().getFluidState(down).isOf(Fluids.WATER) || !strider.isOnGround()) {
            return false;
        }
        List<MobEntity> list = this.strider.getWorld().
                getEntitiesByClass(MobEntity.class,
                        this.strider.getBoundingBox().expand(this.maxDistance), this.targetPredicate);
        if (!list.isEmpty()) {
            for (MobEntity mobEntity : list) {
                this.target = mobEntity;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean shouldContinue() {
        return this.target != null && !this.navigation.isIdle() && this.strider.squaredDistanceTo(this.target) > (double)(this.minDistance * this.minDistance);
    }

    @Override
    public void start() {
        this.updateCountdownTicks = 0;
    }

    @Override
    public void stop() {
        this.target = null;
        this.navigation.stop();
    }

    @Override
    public void tick() {
        if (this.target != null) {
            this.strider.getLookControl().lookAt(this.target, 10.0F, (float)this.strider.getMaxLookPitchChange());
            if (--this.updateCountdownTicks <= 0) {
                this.updateCountdownTicks = this.getTickCount(5);
                double d = this.strider.getX() - this.target.getX();
                double e = this.strider.getY() - this.target.getY();
                double f = this.strider.getZ() - this.target.getZ();
                double g = d * d + e * e + f * f;
                if (!(g <= (double)(this.minDistance * this.minDistance))) {
                    int xFuzz = random.nextBetween(1, 4);
                    int zFuzz = random.nextBetween(1, 4);
                    BlockPos fuzzTarget = this.target.getBlockPos().add(random.nextBoolean() ? xFuzz : -xFuzz, 0, random.nextBoolean() ? zFuzz : -zFuzz);
                    strider.getNavigation().startMovingTo(fuzzTarget.getX(), fuzzTarget.getY(), fuzzTarget.getZ(), this.speed);
                } else {
                    this.navigation.stop();
                    LookControl lookControl = this.target.getLookControl();
                    if (g <= (double)this.minDistance
                            || lookControl.getLookX() == this.strider.getX() && lookControl.getLookY() == this.strider.getY() && lookControl.getLookZ() == this.strider.getZ()) {
                        double h = this.target.getX() - this.strider.getX();
                        double i = this.target.getZ() - this.strider.getZ();
                        this.navigation.startMovingTo(this.strider.getX() - h, this.strider.getY(), this.strider.getZ() - i, this.speed);
                    }
                }
            }
        }
    }
}
