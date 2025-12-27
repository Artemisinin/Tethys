package com.artemis.parallel_world.entity.ai;

import net.minecraft.entity.ai.pathing.LandPathNodeMaker;
import net.minecraft.entity.ai.pathing.PathNode;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.util.math.*;
import org.jetbrains.annotations.Nullable;

public class WaterStriderPathNodeMaker extends LandPathNodeMaker {

    @Override
    @Nullable
    protected PathNode getPathNode(int x, int y, int z, int maxYStep, double prevFeetY, Direction direction, PathNodeType nodeType) {
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        PathNodeType pathNodeType = this.getNodeType(x, y, z);
        if (pathNodeType == PathNodeType.WATER) {
            return this.getNode(x, y, z);
        }
        return super.getPathNode(x, y, z, maxYStep, prevFeetY, direction, nodeType);
    }
}
