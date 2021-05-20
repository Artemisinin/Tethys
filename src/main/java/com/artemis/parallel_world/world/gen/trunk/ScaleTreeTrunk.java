package com.artemis.parallel_world.world.gen.trunk;

import com.google.common.collect.Lists;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

import java.util.List;
import java.util.Random;
import java.util.function.BiConsumer;

public class ScaleTreeTrunk extends TrunkPlacer {

    public ScaleTreeTrunk(int baseHeight, int firstRandomHeight, int secondRandomHeight) {
        super(baseHeight, firstRandomHeight, secondRandomHeight);
    }

    @Override
    protected TrunkPlacerType<?> getType() {
        return null;
    }

    @Override
    public List<FoliagePlacer.TreeNode> generate(TestableWorld world, BiConsumer<BlockPos, BlockState> replacer, Random random, int topPosition, BlockPos startPos, TreeFeatureConfig config) {
        setToDirt(world, replacer, random, startPos.down(), config);
        List<FoliagePlacer.TreeNode> treeNodes = Lists.newArrayList();
        Direction direction = Direction.Type.HORIZONTAL.random(random);
        int i = topPosition - random.nextInt(4) - 1;
        int j = 3 - random.nextInt(3);
        BlockPos.Mutable mutable = new BlockPos.Mutable();
        int plantedX = startPos.getX();
        int plantedZ = startPos.getZ();
        int m = 0;

        int trunkHeight;
        for(int n = 0; n < topPosition; ++n) {
            trunkHeight = startPos.getY() + n;
            if (n >= i && j > 0) {
                plantedX += direction.getOffsetX();
                plantedZ += direction.getOffsetZ();
                --j;
            }

            if (getAndSetState(world, replacer, random, mutable.set(plantedX, trunkHeight, plantedZ), config)) {
                m = trunkHeight + 1;
            }
        }

        treeNodes.add(new FoliagePlacer.TreeNode(new BlockPos(plantedX, m, plantedZ), 1, false));
        plantedX = startPos.getX();
        plantedZ = startPos.getZ();
        Direction direction2 = Direction.Type.HORIZONTAL.random(random);
        if (direction2 != direction) {
            trunkHeight = i - random.nextInt(2) - 1;
            int q = 1 + random.nextInt(3);
            m = 0;

            for(int r = trunkHeight; r < topPosition && q > 0; --q) {
                if (r >= 1) {
                    int s = startPos.getY() + r;
                    plantedX += direction2.getOffsetX();
                    plantedZ += direction2.getOffsetZ();
                    if (getAndSetState(world, replacer, random, mutable.set(plantedX, s, plantedZ), config)) {
                        m = s + 1;
                    }
                }

                ++r;
            }

            if (m > 1) {
                treeNodes.add(new FoliagePlacer.TreeNode(new BlockPos(plantedX, m, plantedZ), 0, false));
            }
        }

        return treeNodes;
    }
}
