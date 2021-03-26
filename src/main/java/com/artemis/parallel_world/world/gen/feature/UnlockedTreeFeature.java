package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.mixin.GetTopPositionInvoker;
import com.artemis.parallel_world.mixin.PlaceLogsAndLeavesInvoker;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mojang.serialization.Codec;

import java.util.*;

import net.minecraft.structure.Structure;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3i;
import net.minecraft.util.shape.VoxelSet;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import static com.artemis.parallel_world.Dimension.VALID_GROUND_BLOCKS;


public class UnlockedTreeFeature extends TreeFeature {

    public UnlockedTreeFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    // Allows this to be placed just about anywhere.  Edit the json to configure allowable blocks.
    private boolean canPlaceTreeOn(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> state.isIn(VALID_GROUND_BLOCKS));
    }

    private boolean generate(StructureWorldAccess world, Random random, BlockPos pos, Set<BlockPos> logPositions, Set<BlockPos> leavesPositions, BlockBox box, TreeFeatureConfig config) {
        int i = config.trunkPlacer.getHeight(random);
        int j = config.foliagePlacer.getRandomHeight(random, i, config);
        int k = i - j;
        int l = config.foliagePlacer.getRandomRadius(random, k);
        if (pos.getY() >= world.getBottomY() + 1 && pos.getY() + i + 1 <= world.getTopY()) {
            if (!canPlaceTreeOn(world, pos.down())) {
                return false;
            } else {
                OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
                int m = ((GetTopPositionInvoker) this).invokeGetTopPosition(world, i, pos, config);
                if (m >= i || optionalInt.isPresent() && m >= optionalInt.getAsInt()) {
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, random, m, pos, logPositions, box, config);
                    list.forEach((node) -> config.foliagePlacer.generate(world, random, config, m, node, j, l, leavesPositions, box));
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }

    @Override
    public boolean generate(FeatureContext<TreeFeatureConfig> context) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        Random random = context.getRandom();
        BlockPos placementPos = context.getOrigin();
        TreeFeatureConfig treeFeatureConfig = context.getConfig();
        Set<BlockPos> logs = Sets.newHashSet();
        Set<BlockPos> decorators = Sets.newHashSet();
        Set<BlockPos> leaves = Sets.newHashSet();
        BlockBox treeBlocksBox = BlockBox.empty();
        // Check to see if tree can be placed at this position.
        boolean canGenerate = this.generate(structureWorldAccess, random, placementPos, logs, decorators, treeBlocksBox, treeFeatureConfig);
        if (treeBlocksBox.minX <= treeBlocksBox.maxX && canGenerate && !logs.isEmpty()) {
            if (!treeFeatureConfig.decorators.isEmpty()) {
                List<BlockPos> logList = Lists.newArrayList(logs);
                List<BlockPos> decoratorsList = Lists.newArrayList(decorators);
                logList.sort(Comparator.comparingInt(Vec3i::getY));
                decoratorsList.sort(Comparator.comparingInt(Vec3i::getY));
                // Looks like this is where decorators get set, so if decorators are waterloggable this will have to be edited.
                treeFeatureConfig.decorators.forEach((decorator) -> decorator.generate(structureWorldAccess, random, logList, decoratorsList, leaves, treeBlocksBox));
            }

            VoxelSet treeBlocksSet = ((PlaceLogsAndLeavesInvoker) this).invokePlaceLogsAndLeaves(structureWorldAccess, treeBlocksBox, logs, leaves);
            Structure.updateCorner(structureWorldAccess, 3, treeBlocksSet, treeBlocksBox.minX, treeBlocksBox.minY, treeBlocksBox.minZ);
            return true;
        } else {
            return false;
        }
    }
}