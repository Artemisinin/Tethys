package com.artemis.parallel_world.mixin.saplingblocks;

import com.artemis.parallel_world.block.sapling.GetVariableConfiguredTreeFeature;
import net.minecraft.block.BlockState;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;

@Mixin(SaplingGenerator.class)
public abstract class SaplingGeneratorMixin implements GetVariableConfiguredTreeFeature {

    @Shadow protected abstract boolean areFlowersNearby(WorldAccess world, BlockPos pos);

    @ModifyVariable(method = "generate", at = @At(value = "INVOKE_ASSIGN", target = "Lnet/minecraft/block/sapling/SaplingGenerator;getTreeFeature(Lnet/minecraft/util/math/random/Random;Z)Lnet/minecraft/registry/RegistryKey;"))
    public RegistryKey<ConfiguredFeature<?,?>> overrideRegistryKey(RegistryKey<ConfiguredFeature<?, ?>> registryKey, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        registryKey = this.getTreeFeature(random, this.areFlowersNearby(world, pos), world, pos);
        return registryKey;
    }

}
