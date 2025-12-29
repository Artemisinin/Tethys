package com.artemis.parallel_world.entity.ai;

import net.minecraft.entity.ai.FuzzyPositions;
import net.minecraft.entity.ai.NavigationConditions;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import org.jetbrains.annotations.Nullable;

public class LongDistanceTargeting {

    // Is supposed to first look for a path with high favor,
    // and if not available return an average favor path.
    @Nullable
    public static Vec3d find(PathAwareEntity entity, int maxHorizontalRange, int minHorizontalRange, int verticalRange) {
        Vec3d vec3d;
        BlockPos relativePos = LongDistanceFuzzyPositions.localFuzz(entity.getRandom(), minHorizontalRange, maxHorizontalRange, verticalRange);
        BlockPos pos = entity.getBlockPos().add(relativePos.getX(), relativePos.getY(), relativePos.getZ());
        if (!NavigationConditions.isInvalidPosition(entity.getNavigation(), pos)
                && !NavigationConditions.hasPathfindingPenalty(entity, pos)) {
            vec3d = entity.getPathfindingFavor(pos) > 0 ? pos.toCenterPos() : null;
            if (vec3d == null){
                vec3d = entity.getPathfindingFavor(pos) >=0 ?pos.toCenterPos() : null;
            }
            return vec3d;
        }
        else return null;
    }

    @Nullable
    public static Vec3d findFrom(PathAwareEntity entity, int horizontalRange, int verticalRange, Vec3d start) {
        Vec3d vec3d = entity.getPos().subtract(start);
        boolean bl = NavigationConditions.isPositionTargetInRange(entity, horizontalRange);
        return FuzzyPositions.guessBestPathTarget(entity, () -> {
            BlockPos blockPos = FuzzyPositions.localFuzz(entity.getRandom(), horizontalRange, verticalRange, 0, vec3d.x, vec3d.z, (float) (Math.PI / 2));
            return blockPos == null ? null : tryMake(entity, horizontalRange, bl, blockPos);
        });
    }

    private static BlockPos tryMake(PathAwareEntity entity, int horizontalRange, boolean posTargetInRange, BlockPos fuzz) {
        BlockPos blockPos = FuzzyPositions.towardTarget(entity, horizontalRange, entity.getRandom(), fuzz);
        return !NavigationConditions.isHeightInvalid(blockPos, entity)
                && !NavigationConditions.isPositionTargetOutOfWalkRange(posTargetInRange, entity, blockPos)
                && !NavigationConditions.isInvalidPosition(entity.getNavigation(), blockPos)
                && !NavigationConditions.hasPathfindingPenalty(entity, blockPos)
                ? blockPos
                : null;
    }



}
