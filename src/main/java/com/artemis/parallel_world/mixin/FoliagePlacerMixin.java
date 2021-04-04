package com.artemis.parallel_world.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.fluid.Fluids;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ModifiableTestableWorld;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Random;
import java.util.function.BiConsumer;


@Mixin(FoliagePlacer.class)
public class FoliagePlacerMixin {

    @Redirect(
            method = "placeFoliageBlock",
            at = @At(value = "INVOKE",
                    target = "Lnet/minecraft/world/gen/stateprovider/BlockStateProvider;getBlockState(Ljava/util/Random;Lnet/minecraft/util/math/BlockPos;)Lnet/minecraft/block/BlockState;"))
    private static BlockState applyWaterlogging(BlockStateProvider blockStateProvider, Random random, BlockPos blockPos, TestableWorld world, BiConsumer<BlockPos, BlockState> biConsumer, Random random2, TreeFeatureConfig config, BlockPos pos2) {
        if (world.testBlockState(blockPos, (testState) -> testState.getFluidState().isEqualAndStill(Fluids.WATER)) && config.foliageProvider.getBlockState(random, blockPos).contains(Properties.WATERLOGGED)) {
            return config.foliageProvider.getBlockState(random, blockPos).with(Properties.WATERLOGGED, true);
        } else return config.foliageProvider.getBlockState(random, blockPos);
    }
}