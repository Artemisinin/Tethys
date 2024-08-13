package com.artemis.parallel_world.world.gen.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.Mutable;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class WeepingFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<WeepingFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return fillFoliagePlacerFields(instance).apply(instance, WeepingFoliagePlacer::new);
    });

    public WeepingFoliagePlacer(IntProvider radius, IntProvider offset) {
        super(radius, offset);
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TethysFoliagePlacers.WEEPING_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        BlockPos blockPos = treeNode.getCenter().down(offset);
        this.generateSquare(world, placer, random, config, blockPos, radius + 2, -1, false);
        this.generateSquare(world, placer, random, config, blockPos, radius + 2, 0, false);
        this.generateSquare(world, placer, random, config, blockPos, radius + 1, 1, false);
        Mutable mutable = new Mutable();
        int lowestRadius = radius + 2;
/*        for (int x = - radius; x <= radius; ++x) {
            for (int z = -radius -2; z <= radius +2; ++z) {
                mutable.set(blockPos.getX() + x, blockPos.getY() - 2, blockPos.getZ() + z);
                float chance = random.nextFloat() * 10;
                if (chance >= 9) {
                    placeFoliageBlock(world, placer, random, config, mutable);
                }
            }
        }*/

        int i = 0;
        while (i < 10) {
            float chance = random.nextFloat() * 10;
            if (chance < 7) {
                continue;
            }
            // I am CERTAIN there is a better way to do this.
            int side = random.nextBetween(1,8);
            switch (side)
            {
                // x fixed positive and z positive and variable
                case 1 -> mutable = new Mutable(blockPos.getX() + lowestRadius, blockPos.getY() - 2, blockPos.getZ() + random.nextBetween(1, lowestRadius - 1));
                // x fixed positive and z negative and variable
                case 2 -> mutable = new Mutable(blockPos.getX() + lowestRadius, blockPos.getY() - 2, blockPos.getZ() - random.nextBetween(1, lowestRadius - 1));
                // x fixed negative and z positive and variable
                case 3 -> mutable = new Mutable(blockPos.getX() - lowestRadius, blockPos.getY() - 2, blockPos.getZ() + random.nextBetween(1, lowestRadius - 1));
                // x fixed negative and z negative and variable
                case 4 -> mutable = new Mutable(blockPos.getX() - lowestRadius, blockPos.getY() - 2, blockPos.getZ() - random.nextBetween(1, lowestRadius - 1));
                // x positive and variable and z fixed positive
                case 5 -> mutable = new Mutable(blockPos.getX() + random.nextBetween(1, lowestRadius -1), blockPos.getY() - 2, blockPos.getZ() + lowestRadius);
                // x negative and variable and z fixed positive
                case 6 -> mutable = new Mutable(blockPos.getX() - random.nextBetween(1, lowestRadius - 1), blockPos.getY() - 2, blockPos.getZ() + lowestRadius);
                // x positive and variable and z fixed negative
                case 7 -> mutable = new Mutable(blockPos.getX() + random.nextBetween(1, lowestRadius - 1), blockPos.getY() - 2, blockPos.getZ() - lowestRadius);
                // x negative and variable and z fixed negative
                case 8 -> mutable = new Mutable(blockPos.getX() - random.nextBetween(1, lowestRadius - 1), blockPos.getY() - 2, blockPos.getZ() - lowestRadius);
            }
            placeFoliageBlock(world, placer, random, config, mutable);
            ++i;
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return 3;
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        if (y == 0) {
            return dx + dz > radius * 2 - 2;
        }
        if (y == -1) {
            if (dx == 1 && (dz == 1 || dz == 0)) {
                return true;
            }
            if (dz == 1 && (dx == 1 || dx == 0)) {
                return true;
            }
            return dx + dz > radius * 2 - 1;
        }
        if (y == -2) {
            return dx + dz > radius * 2 - 1;
        }
        else {
            return false;
        }
    }
}
