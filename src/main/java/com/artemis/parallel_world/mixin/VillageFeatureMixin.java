package com.artemis.parallel_world.mixin;

import net.minecraft.world.gen.feature.VillageFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(VillageFeature.class)
public abstract class VillageFeatureMixin {

    static @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/JigsawFeature;<init>(Lcom/mojang/serialization/Codec;IZZ)V"), index = 1)
    private int overrideStructureStartY(int old) {
        return 30;
    }
}


