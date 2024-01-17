package com.artemis.parallel_world.mixin.saplingblocks;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.OakSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(OakSaplingGenerator.class)
public class OakSaplingMixin {

    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees, ServerWorld world, BlockPos pos) {
        BlockState groundState = world.getBlockState(pos.down());
        if (groundState.isOf(Blocks.MOSS_BLOCK)) {
            return TethysConfiguredFeatures.OAK_SHRUB;
        }
        if (random.nextInt(10) == 0) {
            return bees ? TreeConfiguredFeatures.FANCY_OAK_BEES_005 : TreeConfiguredFeatures.FANCY_OAK;
        }
        return bees ? TreeConfiguredFeatures.OAK_BEES_005 : TreeConfiguredFeatures.OAK;
    }
}
