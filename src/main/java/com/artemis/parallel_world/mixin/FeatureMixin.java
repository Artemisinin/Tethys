package com.artemis.parallel_world.mixin;

import net.minecraft.block.BlockState;
import net.minecraft.world.gen.feature.Feature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import static com.artemis.parallel_world.Dimension.SOIL_BLOCKS;

@Mixin(Feature.class)
public class FeatureMixin {

    @Inject(method = "isSoil(Lnet/minecraft/block/BlockState;)Z", at = @At(value = "HEAD"), cancellable = true)
    private static void hasSoilTag(BlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        cir.setReturnValue(state.isIn(SOIL_BLOCKS));
    }
}
