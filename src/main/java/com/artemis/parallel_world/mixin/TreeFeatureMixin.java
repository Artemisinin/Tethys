package com.artemis.parallel_world.mixin;


import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {

    @Inject(method = "canPlaceTreeOn", at = @At(value = "HEAD"), cancellable = true)
    private static void isMoss(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> cir)
    {
        if (world.testBlockState(pos, blockState -> blockState.isOf(Blocks.MOSS_BLOCK))) {
        cir.setReturnValue(true);
        }
    }
}
