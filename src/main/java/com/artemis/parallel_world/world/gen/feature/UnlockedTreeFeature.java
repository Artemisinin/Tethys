package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.mixin.GetTopPositionInvoker;
import com.mojang.serialization.Codec;
import java.util.*;
import java.util.function.BiConsumer;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import static com.artemis.parallel_world.block.TethysBlocks.VALID_GROUND_BLOCKS;


public class UnlockedTreeFeature extends TreeFeature {

    public UnlockedTreeFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    // Allows this to be placed just about anywhere.  Edit the json to configure allowable blocks.
    private boolean canPlaceTreeOn(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> state.isIn(VALID_GROUND_BLOCKS));
    }

    @Override
    protected boolean generate(StructureWorldAccess world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> logsBiConsumer, BiConsumer<BlockPos, BlockState> leavesBiConsumer, TreeFeatureConfig config) {
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
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, logsBiConsumer, random, m, pos, config);
                    list.forEach((node) -> config.foliagePlacer.generate(world, leavesBiConsumer, random, config, m, node, j, l));
                    return true;
                } else {
                    return false;
                }
            }
        } else {
            return false;
        }
    }
}