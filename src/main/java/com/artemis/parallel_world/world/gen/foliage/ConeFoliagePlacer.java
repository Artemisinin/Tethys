package com.artemis.parallel_world.world.gen.foliage;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class ConeFoliagePlacer extends FoliagePlacer {

    public static final MapCodec<ConeFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec((instance) -> {
        return fillFoliagePlacerFields(instance).and
                (IntProvider.createValidatingCodec(0, 24).fieldOf("height").
                        forGetter((placer) -> {
                            return placer.height;
                        })).apply(instance, ConeFoliagePlacer::new);
    });

    private final IntProvider height;

    public ConeFoliagePlacer(IntProvider radius, IntProvider offset, IntProvider height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> getType() {
        return TethysFoliagePlacers.CONE_FOLIAGE_PLACER;
    }

    @Override
    protected void generate(TestableWorld world, BlockPlacer placer, Random random, TreeFeatureConfig config, int trunkHeight, TreeNode treeNode, int foliageHeight, int radius, int offset) {
        // I feel like this is much longer than it should be but whatever.
        BlockPos blockPos = treeNode.getCenter().down();
        int layerRadius = 1;
        int countAtRadius = 0;
        // Build layers starting from the bottom and widening.
        for(int y = -offset; y < foliageHeight - 2; ++y) {
            this.generateSquare(world, placer, random, config, blockPos, layerRadius, y, treeNode.isGiantTrunk());
            layerRadius = Math.min(++layerRadius, radius);
        }
        // From here want to narrow. The issue is that I want the last 2 layers to narrow, but not below a radius of 2.
        // So it needs to 1. narrow but not fall below minimum, 2. not stall at the minimum radius,
        // and 3. in the last layer potentially drop below minimum radius, but not below 2.
        int topRadius = Math.min(layerRadius, radius);
        countAtRadius = (topRadius == radius) ? ++countAtRadius : countAtRadius;
        for (int y = foliageHeight - 2; y <= foliageHeight; ++y) {
            if (y == foliageHeight - 2) {
                if (topRadius < radius) {
                    ++topRadius;
                }
                countAtRadius = (topRadius == radius) ? ++countAtRadius : countAtRadius;
                this.generateSquare(world, placer, random, config, blockPos, topRadius, y, treeNode.isGiantTrunk());
            }
            if (y == foliageHeight - 1) {
                if (countAtRadius == 0) {
                    ++topRadius;
                } else --topRadius;
                countAtRadius = (topRadius == radius) ? ++countAtRadius : countAtRadius;
                this.generateSquare(world, placer, random, config, blockPos, topRadius, y, treeNode.isGiantTrunk());
            }
            if (y == foliageHeight) {
                if (countAtRadius > 0) {
                    break;
                }
                if (topRadius - 1 < 2) {
                    break;
                } else --topRadius;
                this.generateSquare(world, placer, random, config, blockPos, topRadius, y, treeNode.isGiantTrunk());
            }
        }
    }

    @Override
    public int getRandomHeight(Random random, int trunkHeight, TreeFeatureConfig config) {
        return this.height.get(random);
    }

    @Override
    protected boolean isInvalidForLeaves(Random random, int dx, int y, int dz, int radius, boolean giantTrunk) {
        return dx == radius && dz == radius && radius > 0;
    }
}
