package com.artemis.parallel_world.mixin;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.GeodeFeature;
import net.minecraft.world.gen.feature.GeodeFeatureConfig;
import net.minecraft.world.gen.feature.util.FeatureContext;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(GeodeFeature.class)
public abstract class GeodeFeatureMixin {

    @Inject(method = "generate", at = @At("HEAD"), cancellable = true)
    private void onGenerate(FeatureContext<GeodeFeatureConfig> featureContext, CallbackInfoReturnable<Boolean> cir) {
        GeodeFeatureConfig geodeFeatureConfig = featureContext.getConfig();
        BlockPos blockPos = featureContext.getPos();
        StructureWorldAccess structureWorldAccess = featureContext.getWorld();
        int j = geodeFeatureConfig.maxGenOffset;
        if(structureWorldAccess.getBlockState(blockPos.add(0, j/3, 0)).isAir()) {
            cir.setReturnValue(false);
        }
    }
}
