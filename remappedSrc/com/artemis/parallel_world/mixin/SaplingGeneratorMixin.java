package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.sapling.SaplingGenerator;
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


@Mixin(SaplingGenerator.class)
public class SaplingGeneratorMixin {

    @ModifyVariable(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/fluid/FluidState;getBlockState()Lnet/minecraft/block/BlockState;"))
    private RegistryKey<? extends ConfiguredFeature<?, ?>> modifyFeature(RegistryKey<? extends ConfiguredFeature<?, ?>> originalValue, ServerWorld world, ChunkGenerator chunkGenerator, BlockPos pos, BlockState state, Random random) {
        BlockPos ground = pos.down();
        BlockState groundState = world.getBlockState(ground);
        // Check if sapling is planted on moss and is located in the heath biome.
        if (groundState.isOf(Blocks.MOSS_BLOCK) && world.getBiome(pos).matchesKey(TethysBiomes.HEATH_KEY)) {
        // Vanilla trees
            // Acacia doesn't grow
            if (originalValue.equals(TreeConfiguredFeatures.ACACIA)) {
                return null;
            }
            // Birch shrub
            if (originalValue.equals(TreeConfiguredFeatures.BIRCH_BEES_005) || originalValue.equals(TreeConfiguredFeatures.BIRCH)) {
                return TethysConfiguredFeatures.BIRCH_SHRUB;
            }
            // Jungle shrub
            if (originalValue.equals(TreeConfiguredFeatures.JUNGLE_TREE_NO_VINE)) {
                return TethysConfiguredFeatures.JUNGLE_SHRUB;
            }
            // Oak shrubs
            if (originalValue.equals(TreeConfiguredFeatures.FANCY_OAK_BEES_005) || originalValue.equals(TreeConfiguredFeatures.FANCY_OAK) || originalValue.equals(TreeConfiguredFeatures.OAK_BEES_005) || originalValue.equals(TreeConfiguredFeatures.OAK)) {
                return TethysConfiguredFeatures.OAK_SHRUB;
            }
            // Spruce shrubs
            if (originalValue.equals(TreeConfiguredFeatures.SPRUCE)) {
                if (random.nextInt(10) >= 5) {
                    return TethysConfiguredFeatures.SPRUCE_SHRUB_SHORT;
                } else {
                    return TethysConfiguredFeatures.SPRUCE_SHRUB_TALL;
                }
            }
        // Tethys trees -- all null for now
            if (originalValue.equals(TethysConfiguredFeatures.BASSWOOD_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.BLACKCURRANT_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.CHERRY_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.DOGWOOD_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.ELDERBERRY_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.GINKGO_TREE)) {
                return null;
            }
            if (originalValue.equals(TethysConfiguredFeatures.SWEETGUM_TREE)) {
                return null;
            }
        }
        return originalValue;
    }
}
