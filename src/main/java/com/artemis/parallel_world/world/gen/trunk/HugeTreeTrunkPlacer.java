package com.artemis.parallel_world.world.gen.trunk;

import com.artemis.parallel_world.Dimension;
import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.block.PillarBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import java.util.function.BiConsumer;


public class HugeTreeTrunkPlacer extends TrunkPlacer {

    public HugeTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    public static final Codec<HugeTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return TrunkPlacer.fillTrunkPlacerFields(instance).apply(instance, HugeTreeTrunkPlacer::new);
    });

    @Override
    protected TrunkPlacerType<?> getType() {
        return Dimension.HUGE_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, int topPosition, BlockPos placementPos, TreeFeatureConfig treeFeatureConfig) {

        BlockPos substratePos = placementPos.down();
        setToDirt(testableWorld, biConsumer, random, substratePos, treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.east(), treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.south(), treeFeatureConfig);
        setToDirt(testableWorld, biConsumer, random, substratePos.south().east(), treeFeatureConfig);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        for(int trunkY = 0; trunkY < topPosition; ++trunkY) {
            setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 0);
            if (trunkY <= this.baseHeight) {
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 0);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 1);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 1);
            }
            if (trunkY > this.baseHeight && trunkY <= (this.baseHeight + this.firstRandomHeight)) {
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 0);
                setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 1);
            }
            if (trunkY > (this.baseHeight + this.firstRandomHeight) && trunkY < (this.baseHeight + this.firstRandomHeight + this.secondRandomHeight)) {
                if (random.nextInt(5) <= 2) {
                    setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 1, trunkY, 0);
                }
                else {
                    setLog(testableWorld, biConsumer, random, mutable, treeFeatureConfig, placementPos, 0, trunkY, 1);
                }
            }
        }

        int maxBranchTipY = topPosition + 10;
        int l = MathHelper.floor((double)maxBranchTipY * 0.618D);
        // Math.pow here squares the value.
        int m = Math.min(1, MathHelper.floor(1.382D + Math.pow((double) maxBranchTipY / 13.0D, 2.0D)));
        int minBranchY = placementPos.getY() + l;
        // Sorry about this...
        int branchPlacementHeight = maxBranchTipY;
        List<HugeTreeTrunkPlacer.BranchPosition> branchPositions = Lists.newArrayList();
        // check changes to width
        branchPositions.add(new HugeTreeTrunkPlacer.BranchPosition(placementPos.up(branchPlacementHeight), minBranchY + 10));

        for(; branchPlacementHeight >= 0; --branchPlacementHeight) {
            float f = shouldGenerateBranch(maxBranchTipY, branchPlacementHeight);
            if (!(f < 0.0F)) {
                for(int p = 0; p < m; ++p) {
                    double g = 1.0D * (double)f * ((double)random.nextFloat() + 0.328D);
                    double placementAngle = (double)(random.nextFloat() * 2.0F) * 3.14D;
                    double branchX = g * Math.sin(placementAngle) + 0.5D;
                    double branchZ = g * Math.cos(placementAngle) + 0.5D;
                    // accommodate for 4x4 trunk
                    if (Math.sin(placementAngle) >= 0) {
                        branchX++;
                    }
                    if (Math.cos(placementAngle) >= 0) {
                        branchZ++;
                    }
                    BlockPos branchPos = placementPos.add(branchX, (branchPlacementHeight - 1), branchZ);
                    // This adjusts the angle of the branches--small numbers they spread out flatter, large numbers they angle up sharply.
                    BlockPos blockPos3 = branchPos.up(4);
                    if (this.makeOrCheckBranch(testableWorld, biConsumer, random, branchPos, blockPos3, false, treeFeatureConfig)) {
                        int deltaX = placementPos.getX() - branchPos.getX();
                        int deltaZ = placementPos.getZ() - branchPos.getZ();
                        double u = (double)branchPos.getY() - Math.sqrt((deltaX * deltaX + deltaZ * deltaZ)) * 0.381D;
                        int v = u > (double)minBranchY ? minBranchY : (int)u;
                        BlockPos blockPos4 = new BlockPos(placementPos.getX(), v, placementPos.getZ());
                        if (this.makeOrCheckBranch(testableWorld, biConsumer, random, blockPos4, branchPos, false, treeFeatureConfig)) {
                            branchPositions.add(new HugeTreeTrunkPlacer.BranchPosition(branchPos, blockPos4.getY()));
                        }
                    }
                }
            }
        }
        this.makeOrCheckBranch(testableWorld, biConsumer, random, placementPos, placementPos.up(l), true, treeFeatureConfig);
        this.makeBranches(testableWorld, biConsumer, random, maxBranchTipY, placementPos, branchPositions, treeFeatureConfig);
        List<FoliagePlacer.TreeNode> treeNodes = Lists.newArrayList();
        Iterator branchPositionIterator = branchPositions.iterator();

        while(branchPositionIterator.hasNext()) {
            HugeTreeTrunkPlacer.BranchPosition branchPosition = (HugeTreeTrunkPlacer.BranchPosition)branchPositionIterator.next();
            if (this.isHighEnough(maxBranchTipY, branchPosition.getEndY() - placementPos.getY())) {
                treeNodes.add(branchPosition.node);
            }
        }
        return treeNodes;
    }

    private static void setLog(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, BlockPos.Mutable mutable, TreeFeatureConfig treeFeatureConfig, BlockPos blockPos, int i, int j, int k) {
        mutable.set(blockPos, i, j, k);
        trySetState(testableWorld, biConsumer, random, mutable, treeFeatureConfig);
    }

    private boolean makeOrCheckBranch(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, BlockPos blockPos, BlockPos blockPos2, boolean bl, TreeFeatureConfig treeFeatureConfig) {
        if (!bl && Objects.equals(blockPos, blockPos2)) {
            return true;
        } else {
            BlockPos blockPos3 = blockPos2.add(-blockPos.getX(), -blockPos.getY(), -blockPos.getZ());
            int i = this.getLongestSide(blockPos3);
            float f = (float)blockPos3.getX() / (float)i;
            float g = (float)blockPos3.getY() / (float)i;
            float h = (float)blockPos3.getZ() / (float)i;

            for(int j = 0; j <= i; ++j) {
                BlockPos blockPos4 = blockPos.add((0.5F + (float)j * f), (0.5F + (float)j * g), (0.5F + (float)j * h));
                if (bl) {
                    TrunkPlacer.getAndSetState(testableWorld, biConsumer, random, blockPos4, treeFeatureConfig, (blockState) -> blockState.with(PillarBlock.AXIS, this.getLogAxis(blockPos, blockPos4)));
                } else if (!TreeFeature.canTreeReplace(testableWorld, blockPos4)) {
                    return false;
                }
            }
            return true;
        }
    }

    private int getLongestSide(BlockPos offset) {
        int i = MathHelper.abs(offset.getX());
        int j = MathHelper.abs(offset.getY());
        int k = MathHelper.abs(offset.getZ());
        return Math.max(i, Math.max(j, k));
    }

    private Direction.Axis getLogAxis(BlockPos branchStart, BlockPos branchEnd) {
        Direction.Axis axis = Direction.Axis.Y;
        int i = Math.abs(branchEnd.getX() - branchStart.getX());
        int j = Math.abs(branchEnd.getZ() - branchStart.getZ());
        int k = Math.max(i, j);
        if (k > 0) {
            if (i == k) {
                axis = Direction.Axis.X;
            } else {
                axis = Direction.Axis.Z;
            }
        }

        return axis;
    }

    private boolean isHighEnough(int treeHeight, int height) {
        return (double)height >= (double)treeHeight * 0.2D;
    }

    private void makeBranches(TestableWorld testableWorld, BiConsumer<BlockPos, BlockState> biConsumer, Random random, int i, BlockPos blockPos, List<HugeTreeTrunkPlacer.BranchPosition> list, TreeFeatureConfig treeFeatureConfig) {
        Iterator var8 = list.iterator();

        while(var8.hasNext()) {
            HugeTreeTrunkPlacer.BranchPosition branchPosition = (HugeTreeTrunkPlacer.BranchPosition)var8.next();
            int j = branchPosition.getEndY();
            BlockPos blockPos2 = new BlockPos(blockPos.getX(), j, blockPos.getZ());
            if (!blockPos2.equals(branchPosition.node.getCenter()) && this.isHighEnough(i, j - blockPos.getY())) {
                this.makeOrCheckBranch(testableWorld, biConsumer, random, blockPos2, branchPosition.node.getCenter(), true, treeFeatureConfig);
            }
        }
    }

    private static float shouldGenerateBranch(int i, int j) {
        if ((float)j < (float)i * 0.3F) {
            return -1.0F;
        } else {
            float f = (float)i / 2.0F;
            float g = f - (float)j;
            float h = MathHelper.sqrt(f * f - g * g);
            if (g == 0.0F) {
                h = f;
            } else if (Math.abs(g) >= f) {
                return 0.0F;
            }

            return h * 0.5F;
        }
    }

    static class BranchPosition {
        private final FoliagePlacer.TreeNode node;
        private final int endY;

        public BranchPosition(BlockPos pos, int width) {
            this.node = new FoliagePlacer.TreeNode(pos, 0, false);
            this.endY = width;
        }

        public int getEndY() {
            return this.endY;
        }
    }
}
