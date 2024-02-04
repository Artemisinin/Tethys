package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.Dimension;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;
import net.minecraft.world.gen.feature.IceSpikeFeature;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(IceSpikeFeature.class)
public class IceSpikeFeatureMixin {
/*    @Inject(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;down()Lnet/minecraft/util/math/BlockPos;", ordinal = 1), cancellable = true)
    private void truncateSpire(FeatureContext<DefaultFeatureConfig> context, CallbackInfoReturnable<Boolean> cir) {
        if (context.getWorld().getTopY() == Dimension.TETHYS_TOP_Y)
            // If in alternate dimension, if y is above 160 continue as usual, otherwise return a value <50 to stop.
            ;
        else
            // Same as vanilla.
            return vec3i.getY();
    }*/
}
