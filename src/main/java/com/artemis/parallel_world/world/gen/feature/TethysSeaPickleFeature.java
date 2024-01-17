package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SeaPickleBlock;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.SeaPickleFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;

public class TethysSeaPickleFeature extends SeaPickleFeature {


    public TethysSeaPickleFeature(Codec<CountConfig> codec) {
        super(codec);
    }

    @Override
    public boolean generate(FeatureContext<CountConfig> context) {
        int placements = 0;
        Random random = context.getRandom();
        StructureWorldAccess world = context.getWorld();
        BlockPos origin = context.getOrigin();
        int count = context.getConfig().getCount().get(random);
        BlockState pickles = Blocks.SEA_PICKLE.getDefaultState().with(SeaPickleBlock.PICKLES, random.nextInt(4) + 1);
        for (int tries = 0; tries < count; ++tries) {
            int xIncrement = random.nextInt(8) - random.nextInt(8);
            int zIncrement = random.nextInt(8) - random.nextInt(8);
            int y = 5;
            BlockPos newPos = new BlockPos(origin.getX() + xIncrement, y, origin.getZ() + zIncrement);
            while ((y <= 20) && !world.getBlockState(newPos).isOf(Blocks.WATER) && !world.getBlockState(newPos.down()).isIn(BlockTags.SAND)) {
                newPos = newPos.add(0, 1, 0);
                ++y;
            }
            if (pickles.canPlaceAt(world, newPos)) {
                world.setBlockState(newPos, pickles, Block.NOTIFY_LISTENERS);
                ++placements;
            }
        }
        return placements > 0;
    }
}
