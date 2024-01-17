package com.artemis.parallel_world.block.sapling;

import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.jetbrains.annotations.Nullable;

public interface GetVariableConfiguredTreeFeature {

    @Nullable
    default RegistryKey<ConfiguredFeature<?, ?>> getTreeFeature(Random random, boolean bees, ServerWorld world, BlockPos pos) {
        return null;
    }

}
