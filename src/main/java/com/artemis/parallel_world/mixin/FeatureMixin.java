package com.artemis.parallel_world.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(Feature.class)
public class FeatureMixin {

    @Inject(method = "isSoil(Lnet/minecraft/block/BlockState;)Z", at = @At(value = "HEAD"), cancellable = true)
    private static void isMoss(BlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        if (state.isOf(Blocks.MOSS_BLOCK)) {
            cir.setReturnValue(true);
        }
    }
}
