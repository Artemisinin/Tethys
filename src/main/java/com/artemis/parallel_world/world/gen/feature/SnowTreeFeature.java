package com.artemis.parallel_world.world.gen.feature;

import com.artemis.parallel_world.mixin.GetTopPositionInvoker;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;

import java.util.List;
import java.util.OptionalInt;
import java.util.Random;
import java.util.function.BiConsumer;

public class SnowTreeFeature extends TreeFeature {

    public SnowTreeFeature(Codec<TreeFeatureConfig> codec) {
        super(codec);
    }

    private boolean canPlaceTreeOn(TestableWorld world, BlockPos pos) {
        return world.testBlockState(pos, (state) -> state.isOf(Blocks.SNOW_BLOCK));
    }

    @Override
    protected boolean generate(StructureWorldAccess world, Random random, BlockPos pos, BiConsumer<BlockPos, BlockState> logsBiConsumer, BiConsumer<BlockPos, BlockState> leavesBiConsumer, TreeFeatureConfig config) {
        int height = config.trunkPlacer.getHeight(random);
        int j = config.foliagePlacer.getRandomHeight(random, height, config);
        int k = height - j;
        int l = config.foliagePlacer.getRandomRadius(random, k);
        int r;

        if (pos.getY() >= world.getBottomY() + 1 && pos.getY() + height + 1 <= world.getTopY()) {
            // Check to make sure there are blocks overhead.
            if (!this.canPlaceTreeOn(world, pos.down())) {
                return false;
            } else {
                OptionalInt optionalInt = config.minimumSize.getMinClippedHeight();
                r = ((GetTopPositionInvoker) this).invokeGetTopPosition(world, height, pos, config);
                if (r >= height || optionalInt.isPresent() && r >= optionalInt.getAsInt()) {
                    List<FoliagePlacer.TreeNode> list = config.trunkPlacer.generate(world, logsBiConsumer, random, r, pos, config);
                    list.forEach((node) -> config.foliagePlacer.generate(world, leavesBiConsumer, random, config, r, node, j, l));
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
