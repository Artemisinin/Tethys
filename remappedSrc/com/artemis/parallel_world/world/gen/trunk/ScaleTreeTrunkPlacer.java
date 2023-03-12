package com.artemis.parallel_world.world.gen.trunk;

import com.google.common.collect.Lists;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class ScaleTreeTrunkPlacer extends TrunkPlacer {

    public ScaleTreeTrunkPlacer(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    public static final Codec<ScaleTreeTrunkPlacer> CODEC = RecordCodecBuilder.create((instance) -> {
        return TrunkPlacer.fillTrunkPlacerFields(instance).apply(instance, ScaleTreeTrunkPlacer::new);
    });

    @Override
    protected TrunkPlacerType<?> getType() {
        return TethysTrunkPlacers.SCALE_TREE_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int topPosition, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> treeNodes = Lists.newArrayList();

        //Adding first fork.
        Direction firstForkDirection = Direction.Type.HORIZONTAL.random(random);
        int forkHeight = MathHelper.floor(topPosition * 0.5) + 2;
        int forkLength = random.nextFloat() > 0.5 ? 2 : 3;
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int newX = startPos.getX();
        int newZ = startPos.getZ();
        int treeNodeY;
        int trunkY;

        for(int height = 0; height < topPosition; ++height) {
            trunkY = startPos.getY() + height;
            if (height >= forkHeight && forkLength > 0) {
                newX += firstForkDirection.getOffsetX();
                newZ += firstForkDirection.getOffsetZ();
                --forkLength;
            }
            // Do not add a node at the first two blocks away from the trunk.
            if (getAndSetState(world, replacer, random, mutable.set(newX, trunkY, newZ), config) && (height > (forkHeight + 1)) &&
                    (MathHelper.abs(newX - startPos.getX()) > 2 || MathHelper.abs(newZ - startPos.getZ()) > 2 ||  height > MathHelper.floor(topPosition * 0.5)))
            {
                treeNodeY = trunkY + 1;
                treeNodes.add(new FoliagePlacer.TreeNode(new BlockPos(newX, treeNodeY, newZ), 1, false));
            }

        }

        // Adding second fork.
        // Slight randomization to height.
        forkHeight = (random.nextInt(2) == 1) ? MathHelper.floor(topPosition * 0.5) + 1 : MathHelper.floor(topPosition * 0.5);
        forkLength = random.nextFloat() > 0.5 ? 2 : 3;
        newX = startPos.getX();
        newZ = startPos.getZ();
        Direction secondForkDirection = firstForkDirection.getOpposite();
        int topPositionSecondFork = (random.nextInt(3) == 1) ? topPosition : topPosition - random.nextInt(2);
        for(int height = 0; height < topPositionSecondFork; ++height) {
            trunkY = startPos.getY() + height;
            if (height >= forkHeight && forkLength > 0) {
                newX += secondForkDirection.getOffsetX();
                newZ += secondForkDirection.getOffsetZ();
                --forkLength;
            }
            // Do not add a node at the first two blocks away from the trunk.
            if (getAndSetState(world, replacer, random, mutable.set(newX, trunkY, newZ), config) && (height > (forkHeight + 1)))

            {
                treeNodeY = trunkY + 1;
                treeNodes.add(new FoliagePlacer.TreeNode(new BlockPos(newX, treeNodeY, newZ), 1, false));
            }
        }

        return treeNodes;
    }
}
