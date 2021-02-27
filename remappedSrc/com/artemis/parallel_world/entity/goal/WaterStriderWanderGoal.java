package com.artemis.parallel_world.entity.goal;

import net.minecraft.class_5532;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

import java.util.EnumSet;

public class WaterStriderWanderGoal extends Goal {
        protected final PathAwareEntity strider;
        protected double targetX;
        protected double targetY;
        protected double targetZ;
        protected final double speed;
        protected int chance;
        protected boolean ignoringChance;
        private boolean field_24463;

        public WaterStriderWanderGoal(PathAwareEntity mob, double speed) {
            this(mob, speed, 120);
        }

        public WaterStriderWanderGoal(PathAwareEntity mob, double speed, int chance) {
            this(mob, speed, chance, true);
        }

        public WaterStriderWanderGoal(PathAwareEntity pathAwareEntity, double d, int i, boolean bl) {
            this.strider = pathAwareEntity;
            this.speed = d;
            this.chance = i;
            this.field_24463 = bl;
            this.setControls(EnumSet.of(Goal.Control.MOVE));
        }

        public boolean canStart() {
            if (!this.ignoringChance) {
                if (this.field_24463 && this.strider.getDespawnCounter() >= 100) {
                    return false;
                }

                if (this.strider.getRandom().nextInt(this.chance) != 0) {
                    return false;
                }
            }

            Vec3d vec3d = this.getWanderTarget();
            if (vec3d == null) {
                return false;
            } else {
                this.targetX = vec3d.x;
                this.targetY = vec3d.y;
                this.targetZ = vec3d.z;
                this.ignoringChance = false;
                return true;
            }
        }

        @Nullable
        //This is pretty much definitely the wrong method, but they're all gibberish right now.
        protected Vec3d getWanderTarget() {
            return class_5532.method_31510(strider, 12,0);
        }

        public boolean shouldContinue() {
            return !this.strider.getNavigation().isIdle();
        }

        public void start() {
            this.strider.getNavigation().startMovingTo(this.targetX, this.targetY, this.targetZ, this.speed);
        }

        public void stop() {
            this.strider.getNavigation().stop();
            super.stop();
        }

        public void ignoreChanceOnce() {
            this.ignoringChance = true;
        }

        public void setChance(int chance) {
            this.chance = chance;
        }
    }

