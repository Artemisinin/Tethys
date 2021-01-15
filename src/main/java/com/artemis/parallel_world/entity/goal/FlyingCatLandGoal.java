package com.artemis.parallel_world.entity.goal;

import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.ai.pathing.NavigationType;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class FlyingCatLandGoal extends MoveToTargetPosGoal {
    protected final PathAwareEntity flyingCat;
    protected int cooldown;
    private boolean reached;

    public FlyingCatLandGoal(PathAwareEntity flyingCat, double speed, int range) {
        super(flyingCat, speed, range);
        this.flyingCat = flyingCat;
    }

    @Override
    public boolean canStart() {
        if (this.flyingCat.isOnGround()) {
            return false;
        } else if (this.cooldown > 0) {
            --this.cooldown;
            return false;
        } else {
            this.cooldown = this.getInterval(this.mob);
            return this.findTargetPos();
        }
    }

    protected boolean hasReached() {
        return this.reached;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos.up()).canPathfindThrough(world, pos, NavigationType.LAND);
    }

    @Override
    public void tick() {
        BlockPos blockPos = this.getTargetPos();
        if (!blockPos.isWithinDistance(this.mob.getPos(), this.getDesiredSquaredDistanceToTarget())) {
            this.reached = false;
            ++this.tryingTime;
            if (this.shouldResetPath()) {
                this.mob.getNavigation().startMovingTo((double)((float)blockPos.getX()) + 0.5D, (double)blockPos.getY(), (double)((float)blockPos.getZ()) + 0.5D, this.speed);
            }
        } else {
            this.reached = true;
            --this.tryingTime;
            flyingCat.setNoGravity(false);
        }
    }

}
