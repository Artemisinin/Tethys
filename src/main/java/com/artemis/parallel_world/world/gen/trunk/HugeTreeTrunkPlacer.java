package com.artemis.parallel_world.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer.TreeNode;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;


public class HugeTreeTrunkPlacer extends TrunkPlacer {

    public HugeTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    public static final Codec<HugeTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> TrunkPlacer.fillTrunkPlacerFields(instance).apply(instance, HugeTreeTrunkPlacer::new));

    @Override
    protected TrunkPlacerType<?> getType() {
        return TethysTrunkPlacers.HUGE_TREE_TRUNK_PLACER;
    }

    @Override
    public List<TreeNode> generate(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, int trunkHeight, BlockPos placementPos, TreeFeatureConfig treeFeatureConfig) {
        // This whole shebang is supposed to set dirt, place trunks, and send a list of nodes to place foliage at to the foliage placer.

        // Place dirt if needed.
        BlockPos substratePos = placementPos.down();
        setToDirt(testableWorld, biConsumer, random, substratePos, treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.east(), treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.south(), treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.south().east(), treeFeatureConfig);

        // Build the trunk kinda tapering at the top.
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        // Pick randomly which column gets generated at the top of the trunk in the loop below.
        int columnX;
        int columnZ;
        if (random.nextBoolean()) {
            columnX = 1;
            columnZ = 0;
        } else {
            columnX = 0;
            columnZ = 1;
        }
        for(int trunkY = 0; trunkY <= trunkHeight; ++trunkY) {
            // Sets a full column at the top left corner of the 4x4 grid.
            setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 0);
            // Fills in additional columns in the 4x4 grid depending on height.
            if (trunkY <= trunkHeight/3) {
            /*
            _____________________
            |x+0, z+0 | x+1, z+0|
            |x+0, z+1 | x+1, z+1|
            ---------------------
             */
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 0);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 1);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 1);
            }
            if (trunkY > trunkHeight/3 && trunkY <= trunkHeight/2) {
            /*
            ______________________
            |x+0, z+0 | x+1, z+0 |
            |x+0, z+1 |
            -----------
            */
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 0);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 1);
            }
            if (trunkY > trunkHeight/2 && trunkY <= trunkHeight * 3/4) {
            /*
                ______________________ or   ___________
                |x+0, z+0 | x+1, z+0 |      |x+0, z+0 |
                ----------------------      |x+0, z+1 |
                                            -----------
            */
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, columnX, trunkY, columnZ);
            }
        }
        // Add foliage nodes at 1/2, 3/4, and top of trunk.
        List<TreeNode> treeNodes = Lists.newArrayList();
        treeNodes.add(new TreeNode(placementPos.add(0, trunkHeight - 1,0),0, false));
        treeNodes.add(new TreeNode(placementPos.add(0, trunkHeight*3/4, 0), 0, true));
        treeNodes.add(new TreeNode(placementPos.add(0, trunkHeight/2, 0), 0, true));

        int minBranchBaseHeight = MathHelper.floor((double)trunkHeight * 0.33);

        // This is supposed to find the point where a branch comes out of the trunk. That should then get passed on
        // to select the positions to be turned into logs.
        // Goal is to decide where to start each branch. How many branches? Which height? Which direction?
        int branchTopHeight = trunkHeight - random.nextBetween(3, 6);
        double placementAngle = 0;
        for(int branchPlacementHeight = branchTopHeight;
            branchPlacementHeight >= minBranchBaseHeight;
            branchPlacementHeight = branchPlacementHeight-2) {
            int branchX;
            int branchZ;
            // Set X and Z value and accommodate for 4x4 trunk.
            // ** The Z axis runs THE OPPOSITE WAY from what you would think (see above diagrams). **
            // The additional trunk blocks are placed at x+1 and z+1.
            if (Math.sin(placementAngle) >= 0.707) {
                //Branch should grow from north side of trunk northward.
                branchZ = 0;
                if (branchPlacementHeight < trunkHeight/2) {
                    // Randomly add 1 to x to shift from left to right of trunk.
                    branchX = random.nextBoolean() ? 1 : 0;
                } else branchX = 0;
            } else
            if (Math.sin(placementAngle) <= -0.707) {
                // Branch should grow from south side of trunk southward.
                branchZ = 1;
                if (branchPlacementHeight < trunkHeight/3) {
                    // Randomly add +1 to x to shift from left to right of trunk.
                    branchX = random.nextBoolean() ? 1 : 0;
                } else branchX = 0;

            } else
            if (Math.cos(placementAngle) < 0) {
                // Branch should grow from west side of trunk westward.
                branchX = 0;
                if (branchPlacementHeight < trunkHeight/2) {
                    // Randomly add +1 to x to shift from left to right of trunk.
                    branchZ = random.nextBoolean() ? 1 : 0;
                } else branchZ = 0;
            }
            else {
                // Branch should grow from east side of trunk eastward.
                branchX = 1;
                if (branchPlacementHeight < trunkHeight/3) {
                    // Randomly add +1 to x to shift from left to right of trunk.
                    branchZ = random.nextBoolean() ? 1 : 0;
                } else branchZ = 0;
            }
            BlockPos branchPos = placementPos.add(branchX, branchPlacementHeight, branchZ);
            // Check positions and add to the list of branchPositions, but don't make them yet.
            List<BlockPos> singleBranchLogPositions = this.checkBranch(testableWorld, branchPos, placementAngle, random, true);
            int length = singleBranchLogPositions.size();
            // Add foliage placers along the branch.
            treeNodes.add(new TreeNode(singleBranchLogPositions.get((int) Math.floor(length/3)).down(), 0, false));
            treeNodes.add(new TreeNode(singleBranchLogPositions.get((int) Math.floor(length * 2/3)).down(), 0, false));
            treeNodes.add(new TreeNode(singleBranchLogPositions.get(length - 1).down(), 0, false));
            // Make the branch.
            buildBranch(singleBranchLogPositions, testableWorld, biConsumer,random,treeFeatureConfig, branchPos);
            // If the branch is more than 4 blocks long, add a side branch at the midpoint.
            if (length > 4) {
                int index = (int) Math.floor(length/2);
                List<BlockPos> sideBranchLogPositions;
                while (index <= (length - 1)) {
                    double rotationAngle = random.nextBoolean() ? Math.PI / 2 : Math.PI / 2 * -1;
                    BlockPos blockPos = singleBranchLogPositions.get(index);
                    double sideBranchAngle = placementAngle + rotationAngle;
                    sideBranchLogPositions = this.checkBranch(testableWorld, blockPos, sideBranchAngle, random, false);
                    treeNodes.add(new TreeNode(sideBranchLogPositions.get(sideBranchLogPositions.size() - 1), 0, false));
                    int sideBranchLength = sideBranchLogPositions.size();
                    if (sideBranchLength > 4) {
                        int midpoint = (int) Math.floor(sideBranchLength / 2);
                        treeNodes.add(new TreeNode(sideBranchLogPositions.get(midpoint), 0, false));
                    }
                    buildBranch(sideBranchLogPositions, testableWorld, biConsumer, random, treeFeatureConfig, blockPos);
                    index = Math.min(index + 2, length);
                    sideBranchLogPositions.clear();
                }
            }
            placementAngle = placementAngle + (Math.PI/2);
        }
        return treeNodes;
    }

    private List<BlockPos> checkBranch(TestableWorld testableWorld, BlockPos branchPos, Double placementAngle, Random random, Boolean mainBranch) {

        List<BlockPos> branchPositions = Lists.newArrayList();
        branchPositions.add(branchPos);
        int xFactor;
        int zFactor;
        // Multiply by the change in x or z axis to make sure branches also build along -x and -z.
        if (Math.sin(placementAngle) >= 0.707) {
            xFactor = 0;
            zFactor = -1;
        } else
        if (Math.sin(placementAngle) <= -0.707) {
            xFactor = 0;
            zFactor = 1;
        } else
        if (Math.cos(placementAngle) < 0) {
            xFactor = -1;
            zFactor = 0;
        }
        else {
            xFactor = 1;
            zFactor = 0;
        }
        // If this is a main branch from the trunk, it will be longer than side branches.
        int length = mainBranch ? random.nextBetween(4, 7) : random.nextBetween(2, 4);
        int delta = 0;
        int deltaX = 0;
        int deltaY = 0;
        int deltaZ = 0;

        for (int logNumber = 0; logNumber <= length; ++logNumber) {
            // The first 2 blocks should go directly out from the trunk.
            if (logNumber < 3) {
                ++delta;
                deltaY = 0;
            } else {
                double proportion = (double) logNumber / length;
                int chance = proportion >= 0.5 ? 2 : 4;
                if (random.nextBetween(1, chance) != 1) {
                    ++delta;
                    deltaY = random.nextBoolean() ? ++deltaY : deltaY;
                } else {
                    ++deltaY;
                }
            }
            if (xFactor != 0) {
                deltaX = delta;
            } else {
                deltaZ = delta;
            }
            BlockPos branchBlockPos = branchPos.add(deltaX*xFactor, deltaY, deltaZ*zFactor);
            if (TreeFeature.canReplace(testableWorld, branchBlockPos)) {
                branchPositions.add(branchBlockPos);
            }
            else break;
        }
        return branchPositions;
    }

    private void buildBranch(List<BlockPos> branchLogPositions, TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, TreeFeatureConfig treeFeatureConfig, BlockPos branchPos) {
        if (!branchLogPositions.isEmpty()) {
            for (BlockPos logPosition : branchLogPositions) {
                this.getAndSetState(testableWorld, biConsumer, random, logPosition, treeFeatureConfig,
                        state -> state.withIfExists(PillarBlock.AXIS, this.getLogAxis(branchPos, logPosition)));
            }
        }
    }

    private Direction.Axis getLogAxis(BlockPos branchStart, BlockPos branchEnd) {
        // Set default axis.
        Direction.Axis axis = Direction.Axis.Y;
        int deltaY = Math.abs(branchEnd.getY() - branchStart.getY());
        int deltaX = Math.abs(branchEnd.getX() - branchStart.getX());
        int deltaZ = Math.abs(branchEnd.getZ() - branchStart.getZ());
        int deltaMaxHorizontal = Math.max(deltaX, deltaZ);
        // I feel like there's a better way than setting it to Y again.
        // Moved below if to the next.
/*        if (deltaY > deltaMaxHorizontal + 3) {
            //axis = Direction.Axis.Y;
            return axis;
        }*/
        if (deltaMaxHorizontal > 0 && deltaY < deltaMaxHorizontal + 3) {
            if (deltaX == deltaMaxHorizontal) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }
        return axis;
    }

    private void setLog(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, BlockPos.Mutable mutable, TreeFeatureConfig treeFeatureConfig, BlockPos blockPos, int x, int y, int z) {
        mutable.set(blockPos, x, y, z);
        this.trySetState(testableWorld, biConsumer, random, mutable, treeFeatureConfig);
    }
}