package com.artemis.parallel_world.mixin;

import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.feature.TreeFeature;
import net.minecraft.world.gen.feature.TreeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(TreeFeature.class)
public class TreeFeatureMixin {
    @Inject(method = "canReplace", at = @At(value = "RETURN"), cancellable = true)

    // Thank you K.jpg for pointing out snow layers are not air!
    private static void snowReplaceable(TestableWorld world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.testBlockState(pos, (blockState -> blockState.isIn(BlockTags.SNOW)))) {
            cir.setReturnValue(true);
            cir.cancel();
        }
        cir.cancel();
    }
}
