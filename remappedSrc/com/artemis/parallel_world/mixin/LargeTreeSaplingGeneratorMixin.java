package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.LargeTreeSaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyVariable;


@Mixin(LargeTreeSaplingGenerator.class)
public class LargeTreeSaplingGeneratorMixin {
/*
I can't be bothered to fix this right now.
    @ModifyVariable(method = "generateLargeTree", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/registry/RegistryEntry;value()Ljava/lang/Object;"))
    private RegistryKey<? extends ConfiguredFeature<?, ?>> modifyFeature(RegistryKey<? extends ConfiguredFeature<?, ?>> originalValue, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        BlockPos ground = pos.down();
        BlockState groundState = world.getBlockState(ground);
        if (groundState.isOf(Blocks.MOSS_BLOCK)) {
            // Dark oak shrubs
            if (originalValue.equals(TreeConfiguredFeatures.DARK_OAK)) {
                if (random.nextInt(10) >= 5) {
                    return (TethysConfiguredFeatures.DARK_OAK_SHRUB_SHORT);
                } else {
                    return (TethysConfiguredFeatures.DARK_OAK_SHRUB_TALL);
                }
            }
            // Jungle shrub
            if (originalValue.equals(TreeConfiguredFeatures.MEGA_JUNGLE_TREE)) {
                return TethysConfiguredFeatures.JUNGLE_SHRUB;
            }
            // Spruce shrubs
            if (originalValue.equals(TreeConfiguredFeatures.MEGA_SPRUCE) || originalValue.equals(TreeConfiguredFeatures.MEGA_PINE)) {
                if (random.nextInt(10) >= 5) {
                    return TethysConfiguredFeatures.SPRUCE_SHRUB_SHORT;
                } else {
                    return TethysConfiguredFeatures.SPRUCE_SHRUB_TALL;
                }
            }
        }
        return originalValue;
    }

 */
}