package com.artemis.parallel_world.entity.ai;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;

public class LongDistanceFuzzyPositions {

    public static BlockPos localFuzz(Random random, int minHorizontalRange, int maxHorizontalRange, int verticalRange) {
        int i = random.nextBoolean() ?
                minHorizontalRange + random.nextBetween(0, maxHorizontalRange - minHorizontalRange)
                : -(minHorizontalRange + random.nextBetween(0, maxHorizontalRange - minHorizontalRange));
        int j = random.nextInt(2 * verticalRange + 1) - verticalRange;
        int k = random.nextBoolean() ?
                minHorizontalRange + random.nextBetween(0, maxHorizontalRange - minHorizontalRange)
                : -(minHorizontalRange + random.nextBetween(0, maxHorizontalRange - minHorizontalRange));
        return new BlockPos(i, j, k);
    }
}
