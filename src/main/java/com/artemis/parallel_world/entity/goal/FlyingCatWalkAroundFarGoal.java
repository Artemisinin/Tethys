package com.artemis.parallel_world.entity.goal;

import net.minecraft.entity.ai.TargetFinder;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class FlyingCatWalkAroundFarGoal extends FlyingCatWalkAroundGoal {

    protected final float probability;

    public FlyingCatWalkAroundFarGoal(PathAwareEntity flyingCat, double speed, float probability) {
        super(flyingCat, speed);
        this.probability = probability;
    }


    @Nullable
    protected Vec3d getWanderTarget() {
        if (this.mob.isInsideWaterOrBubbleColumn()) {
            Vec3d vec3d = TargetFinder.findGroundTarget(this.mob, 15, 7);
            return vec3d == null ? super.getWanderTarget() : vec3d;
        } else {
            if (!this.mob.isOnGround()) {
                Vec3d vec3d = TargetFinder.findGroundTarget(this.mob, 10, 30);

            }
            return this.mob.getRandom().nextFloat() >= this.probability ? TargetFinder.findGroundTarget(this.mob, 10, 7) : super.getWanderTarget();
        }
    }
}
