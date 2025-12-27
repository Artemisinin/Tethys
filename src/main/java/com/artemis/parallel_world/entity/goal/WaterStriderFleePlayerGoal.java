package com.artemis.parallel_world.entity.goal;

import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.TargetPredicate;
import net.minecraft.entity.ai.goal.FleeEntityGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.fluid.Fluids;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

public class WaterStriderFleePlayerGoal extends FleeEntityGoal<PlayerEntity> {

    private final TargetPredicate withinRangePredicate = TargetPredicate.createAttackable().setBaseMaxDistance(this.fleeDistance).setPredicate(inclusionSelector.and(extraInclusionSelector));

    public WaterStriderFleePlayerGoal(PathAwareEntity mob, Class<PlayerEntity> fleeFromType, float distance, double slowSpeed, double fastSpeed) {
        super(mob, fleeFromType, distance, slowSpeed, fastSpeed);
    }

    @Override
    public boolean canStart() {
        this.targetEntity = this.mob.getWorld().getClosestEntity(this.mob.getWorld().getEntitiesByClass
                        (this.classToFleeFrom, this.mob.getBoundingBox().expand(this.fleeDistance, 3.0, this.fleeDistance), livingEntity -> true),
                this.withinRangePredicate, this.mob, this.mob.getX(), this.mob.getY(), this.mob.getZ());
        if (this.targetEntity == null || !this.mob.getWorld().getFluidState(this.mob.getBlockPos().down()).isIn(FluidTags.WATER)) {
            return false;
        } else {
            Vec3d vec3d = NoPenaltyTargeting.findFrom(this.mob, 16, 0, this.targetEntity.getPos());
            if (vec3d == null) {
                return false;
            }
            BlockPos target = new BlockPos((int) vec3d.getX(), (int) vec3d.getY(), (int) vec3d.getZ());
            if (this.mob.getWorld().getFluidState(target.down()).isOf(Fluids.WATER)) {
                if (this.targetEntity.squaredDistanceTo(vec3d.x, vec3d.y, vec3d.z) < this.targetEntity.squaredDistanceTo(this.mob)) {
                    return false;
                } else {
                    this.fleePath = this.fleeingEntityNavigation.findPathTo(vec3d.x, vec3d.y, vec3d.z, 0);
                    return this.fleePath != null;
                }
            }
            return false;
        }
    }
}
