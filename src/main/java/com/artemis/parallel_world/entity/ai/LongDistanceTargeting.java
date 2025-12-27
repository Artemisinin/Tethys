package com.artemis.parallel_world.entity.ai;

import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class LongDistanceTargeting {

    // Barbarically basic code that looks for pathfinding favor >0, which means
    // it ignores a lot of perfectly traversable paths.
    // This is because I am using it for the water striders and only want them to walk
    // on water blocks for the goal this is attached to.
    @Nullable
    public static Vec3d find(PathAwareEntity entity, int maxHorizontalRange, int minHorizontalRange, int verticalRange) {
        Vec3d vec3d;
        BlockPos relativePos = LongDistanceFuzzyPositions.localFuzz(entity.getRandom(), minHorizontalRange, maxHorizontalRange, verticalRange);
        BlockPos pos = entity.getBlockPos().add(relativePos.getX(), relativePos.getY(), relativePos.getZ());
        if (!NavigationConditions.isInvalidPosition(entity.getNavigation(), pos)
                && !NavigationConditions.hasPathfindingPenalty(entity, pos)) {
            vec3d = entity.getPathfindingFavor(pos) > 0 ? pos.toCenterPos() : null;
            return vec3d;
        }
        else return null;
    }

}
