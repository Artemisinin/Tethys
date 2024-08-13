package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class FreezeAreaFeature extends Feature<DefaultFeatureConfig> {
    public FreezeAreaFeature(Codec<DefaultFeatureConfig> configCodec) {
        super(configCodec);
    }

    @Override
    public boolean generate(FeatureContext<DefaultFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        Random random = context.getRandom();
        boolean placed = false;
        int deltaX = random.nextBetween(2, 5);
        //int deltaY = random.nextBetween(2, 5);
        int deltaY = 0;
        int deltaZ = random.nextBetween(2, 5);
        // This feels really ugly.
        int max = Math.max(deltaX, Math.max(deltaY, deltaZ));
        // This thing doesn't work at all like i thought it did I guess, so we're going
        // to pitch the idea of scanning the Y axis around the area for the moment.
        // That just makes weird ice pyramids.
        // This should at least get better ice cover.
        Iterable<BlockPos> candidatePositions = BlockPos.iterateOutwards(blockPos, deltaX, deltaY, deltaZ);
        for (BlockPos placementPos : candidatePositions) {
            BlockState placementPosState = structureWorldAccess.getBlockState(placementPos);
            if (placementPos.getManhattanDistance(blockPos) > max) {
                break;
            }
            // Check to see if placement position is air and below it is not air.
            // Currently NOT placing snow on ice.
            if (placementPosState.isAir() && structureWorldAccess.getBlockState(placementPos.down()).isSolid()
                    && !structureWorldAccess.getBlockState(placementPos.down()).isOf(Blocks.ICE)
                    && random.nextBetween(0, 10) > 4) {
                // Check for solid surface, place snow.
                structureWorldAccess.setBlockState(placementPos, Blocks.SNOW.getDefaultState(), Block.NOTIFY_LISTENERS);
                placed = true;
            }
            // Check for water, place ice.
            if (placementPosState.isAir() && structureWorldAccess.testFluidState(blockPos.down(), (fluidState) ->
                fluidState.isEqualAndStill(Fluids.WATER)) && random.nextBetween(0, 10) > 4) {
                structureWorldAccess.setBlockState(placementPos.down(), Blocks.ICE.getDefaultState(), Block.NOTIFY_LISTENERS);
                placed = true;
            }
        }
        return placed;
    }
}

// The below tries to do something really illegal related to chunk loading.
        /*
        StructureWorldAccess structureWorldAccess = context.getWorld();
        BlockPos blockPos = context.getOrigin();
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        BlockPos.Mutable mutableDown = new BlockPos.Mutable();
        for (int deltaX = 0; deltaX < 16; deltaX++) {
            for (int deltaY = 0; deltaY < 16; deltaY++) {
                for (int deltaZ = 0; deltaZ < 16; deltaZ++) {
                    int newX = blockPos.getX() + deltaX;
                    int newY = Math.max((blockPos.getY() + deltaY), structureWorldAccess.getSeaLevel());
                    int newZ = blockPos.getY() + deltaZ;
                    int random;
                    mutable.set(newX, newY, newZ);
                    mutableDown.set(newX, newY - 1, newZ);
                    {
                        if (structureWorldAccess.isAir(mutable) && !structureWorldAccess.isAir(mutableDown)) {
                            if (structureWorldAccess.testBlockState(mutableDown, (blockState) ->
                                    blockState.isSolidBlock(structureWorldAccess, mutableDown))) {
                                random = Random.create().nextBetween(0, 10);
                                if (random > 4) {
                                    structureWorldAccess.setBlockState(mutable, Blocks.SNOW.getDefaultState(), 2);
                                }
                            }
                            if (structureWorldAccess.testBlockState(mutableDown, (blockState) ->
                                    blockState.getFluidState().isEqualAndStill(Fluids.WATER))) {
                                random = Random.create().nextBetween(0, 10);
                                if (random > 4) {
                                    structureWorldAccess.setBlockState(mutableDown, Blocks.ICE.getDefaultState(), 2);
                                }
                            }
                        }
                    }
                }
            }
        }
        return true;

         */
