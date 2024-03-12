package com.artemis.parallel_world.mixin.saplingblocks;

import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.sapling.MangroveSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(MangroveSaplingGenerator.class)
public class MangrovePropaguleMixin {
    @Final
    @Shadow
    private float tallChance;

    protected RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees, ServerWorld world, BlockPos pos) {
        if (world.getBiome(pos).isIn(TethysBiomes.IS_TETHYS)) {
            return TethysConfiguredFeatures.SMALL_MANGROVE;
        }
        if (random.nextFloat() < this.tallChance) {
            return TreeConfiguredFeatures.TALL_MANGROVE;
        }
        return TreeConfiguredFeatures.MANGROVE;
    }
}
