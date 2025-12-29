package com.artemis.parallel_world.entity.ai;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.Nullable;

public class WaterStriderPathNodeMaker extends LandPathNodeMaker {

    public WaterStriderPathNodeMaker() {
        super();
    }

    @Override
    public PathNode getStart() {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int i = this.entity.getBlockY();
        BlockState blockState = this.context.getBlockState(mutable.set(this.entity.getX(), (double)i, this.entity.getZ()));
        if (this.entity.canWalkOnFluid(blockState.getFluidState())) {
            while (this.entity.canWalkOnFluid(blockState.getFluidState())) {
                blockState = this.context.getBlockState(mutable.set(this.entity.getX(), (double)(++i), this.entity.getZ()));
            }
            i--;
        }
        BlockPos blockPos = this.entity.getBlockPos();
        if (!this.canPathThrough(mutable.set(blockPos.getX(), i, blockPos.getZ()))) {
            Box box = this.entity.getBoundingBox();
            if (this.canPathThrough(mutable.set(box.minX, (double)i, box.minZ))
                    || this.canPathThrough(mutable.set(box.minX, (double)i, box.maxZ))
                    || this.canPathThrough(mutable.set(box.maxX, (double)i, box.minZ))
                    || this.canPathThrough(mutable.set(box.maxX, (double)i, box.maxZ))) {
                return this.getStart(mutable);
            }
        }
        return this.getStart(new BlockPos(blockPos.getX(), i, blockPos.getZ()));
    }

    @Override
    @Nullable
    protected PathNode getPathNode(int x, int y, int z, int maxYStep, double prevFeetY, Direction direction, PathNodeType nodeType) {
        PathNode pathNode = null;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        double d = this.getFeetY(mutable.set(x, y, z));
        if (d - prevFeetY > this.getStepHeight()) {
            return null;
        } else {
            PathNodeType pathNodeType = this.getNodeType(x, y, z);
            float f = this.entity.getPathfindingPenalty(pathNodeType);
            // This should make water A-okay, as well as other allowable blocks.
            if (f >= 0.0F) {
                pathNode = this.getNodeWith(x, y, z, pathNodeType, f);
            }
            // Land blocks have penalty either 0 or 8 (should be on water).
            // This should make it not walk on land when it's set to a penalty of 8.
            if (pathNode != null && pathNodeType == PathNodeType.WALKABLE && pathNode.penalty == 8.0F) {
                pathNode = null;
            }
            // Same for water border, default value is 8 so I reset to 0 if the strider is on land.
            if (pathNode != null && pathNodeType == PathNodeType.WATER_BORDER && pathNode.penalty == 8.0F) {
                pathNode = null;
            }
            if (pathNode != null && isNodeTypeBlocked(nodeType) && pathNode.penalty >= 0.0F && !this.isBlocked(pathNode)) {
                pathNode = null;
            }
            // I think I can't just make the penalty for land -1 because it looks like
            // this will still make it walk on it.
            if ((pathNode == null || pathNode.penalty < 0.0F)
                    && maxYStep > 0
                    && pathNodeType != PathNodeType.FENCE
                    && pathNodeType != PathNodeType.UNPASSABLE_RAIL
                    && pathNodeType != PathNodeType.TRAPDOOR
                    && pathNodeType != PathNodeType.POWDER_SNOW) {
                pathNode = this.getJumpOnTopNode(x, y, z, maxYStep, prevFeetY, direction, nodeType, mutable);
            }
            if (pathNodeType == PathNodeType.OPEN) {
                pathNode = this.getOpenNode(x, y, z);
            }
            if (pathNode == null && isNodeTypeBlocked(pathNodeType)) {
                pathNode = this.getNodeWith(x, y, z, pathNodeType);
            }
        }
        return pathNode;
    }

    private boolean isNodeTypeBlocked(PathNodeType nodeType) {
            return nodeType == PathNodeType.FENCE || nodeType == PathNodeType.DOOR_WOOD_CLOSED || nodeType == PathNodeType.DOOR_IRON_CLOSED;
    }
}
