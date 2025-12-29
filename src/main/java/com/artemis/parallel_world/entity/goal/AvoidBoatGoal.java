package com.artemis.parallel_world.entity.goal;

import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.ai.pathing.Path;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.vehicle.BoatEntity;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.Vec3d;


import java.util.List;

public class AvoidBoatGoal extends Goal {
    private int updateCountdownTicks;
    private final PathAwareEntity mob;
    private final float distance;
    private final double speed;
    private BoatEntity boatEntity;
    private Path avoidPath;


    public AvoidBoatGoal (PathAwareEntity mob, float distance, double speed) {
        this.mob = mob;
        this.distance = distance;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        List<BoatEntity> boatList = this.mob.getWorld().getNonSpectatingEntities(BoatEntity.class, this.mob.getBoundingBox().expand(this.distance, 0, this.distance));
        for (BoatEntity boatEntity : boatList) {
            if (boatEntity.getControllingPassenger() == null) {
                this.boatEntity = boatEntity;
                break;
            }
        }
        if (this.boatEntity == null) {
            return false;
        } else {
            Vec3d vec3d = NoPenaltyTargeting.findFrom(this.mob, 10, 0, this.boatEntity.getPos());
            if (vec3d == null) {
                return false;
            } else if (this.boatEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < this.boatEntity.squaredDistanceTo(this.mob)) {
                return false;
            } else {
                this.avoidPath = this.mob.getNavigation().findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
            }
        }
        return this.avoidPath != null && this.mob.getWorld().getFluidState(this.mob.getBlockPos()).isIn(FluidTags.WATER);
    }

    @Override
    public void start() {
        this.mob.getNavigation().startMovingAlong(this.avoidPath,this.speed);
    }

    @Override
    public boolean shouldContinue() {
        return !this.mob.getNavigation().isIdle();
    }

    @Override
    public void stop() {
        this.boatEntity = null;
    }
}
