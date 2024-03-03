package com.artemis.parallel_world.mixin.saplingblocks;

import com.artemis.parallel_world.block.sapling.GetVariableConfiguredTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(LargeTreeSaplingGenerator.class)
public class LargeTreeSaplingGeneratorMixin implements GetVariableConfiguredTreeFeature {

    @ModifyVariable(method = "generateLargeTree", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/sapling/LargeTreeSaplingGenerator;getLargeTreeFeature(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/registry/RegistryKey;"))
    public RegistryKey<ConfiguredFeature<?,?>> overrideRegistryKey(RegistryKey<ConfiguredFeature<?,?>> registryKey, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState blockState, Random random, int x, int z) {
        registryKey = this.getLargeTreeFeature(random, world, pos);
        return registryKey;
    }
}
