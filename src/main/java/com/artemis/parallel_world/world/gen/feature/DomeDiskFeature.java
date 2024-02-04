package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class DomeDiskFeature extends Feature<DomeDiskFeatureConfig> {

    public DomeDiskFeature(Codec<DomeDiskFeatureConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<DomeDiskFeatureConfig> context) {
        DomeDiskFeatureConfig domeDiskFeatureConfig = context.getConfig();
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos startPos = context.getOrigin();
        Random random = context.getRandom();
        BlockState placedBlockState = domeDiskFeatureConfig.placedBlock();

        int bottomY = startPos.getY();
        int topY = bottomY + domeDiskFeatureConfig.height().get(random);
        int layerY = bottomY;
        int radiusX = domeDiskFeatureConfig.radius().get(random);
        int radiusZ = domeDiskFeatureConfig.radius().get(random);
        int height = domeDiskFeatureConfig.height().get(random);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int placedBlocks = 0;

        while ((layerY <= topY) || (radiusX > 1 && radiusZ > 1)) {
            for (BlockPos currentPos : BlockPos.iterateOutwards(startPos, radiusX, 0, radiusZ)) {
                // If the current position is not over a solid block, stop.
                if (!structureWorldAccess.testBlockState(currentPos.down(), state -> {
                            Material material = state.getMaterial();
                            return material.isSolid();
                        })) break;
                // If the current position is not a target block or is not the placed block, continue to next position.
                if (!domeDiskFeatureConfig.target().test(structureWorldAccess, currentPos) ||
                        !structureWorldAccess.getBlockState(currentPos.down()).isOf(placedBlockState.getBlock())) continue;
                // If the position is too far from the center, stop.
                if (currentPos.getManhattanDistance(startPos) > (Math.max(radiusX, radiusZ))) break;
                this.setBlockState(structureWorldAccess, mutable.set(currentPos), placedBlockState);
                this.markBlocksAboveForPostProcessing(structureWorldAccess, currentPos);
                ++placedBlocks;
            }
            startPos = startPos.add(0, 1, 0);
            int randomRadiusDecrease = new java.util.Random().nextInt(1,6);
            radiusX = radiusX - randomRadiusDecrease;
            randomRadiusDecrease = new java.util.Random().nextInt(1,6);
            radiusZ = radiusZ - randomRadiusDecrease;
            ++layerY;
        }
        return placedBlocks > 0;
    }
}


/*
        // Issue is this iterates in a cube and I really want half a cube. That may be why they are too tall.
        for (BlockPos currentPos : BlockPos.iterateOutwards(startPos, radiusX, height, radiusZ)) {
            if (currentPos.getY() < startPos.getY()) continue;
            if (currentPos.getY() > (startPos.getY() + height)) continue;
            if (domeDiskFeatureConfig.target().test(structureWorldAccess, currentPos) &&
                    structureWorldAccess.testBlockState(currentPos.down(), state -> {
                        Material material = state.getMaterial();
                        return material.isSolid();
                    })) {
                if (currentPos.getManhattanDistance(startPos) > (Math.max(radiusX, radiusZ)) ||
                        // not sure about Manhattan distance below since I allow radius to vary now,
                        // might have to find whether it's moved along the X or Z axis.
                        currentPos.getManhattanDistance(startPos) > (Math.max(radiusX, height))) break;
                this.setBlockState(structureWorldAccess, mutable.set(currentPos), placedBlockState);
                this.markBlocksAboveForPostProcessing(structureWorldAccess, currentPos);
            }
            ++placedBlocks;
        }
        return (placedBlocks > 0);
    }
}
 */