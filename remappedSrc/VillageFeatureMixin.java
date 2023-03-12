/*

//I think this needs to be changed to mixing into Structures and changing each village, if editing the jigsaw placement doesn't work.

package com.artemis.parallel_world.mixin;

import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.heightprovider.ConstantHeightProvider;
import net.minecraft.world.gen.structure.JigsawStructure;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArg;


@Mixin(JigsawStructure.class)
public abstract class VillageFeatureMixin {

    static @ModifyArg(method = "<init>", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/feature/JigsawFeature;<init>(Lcom/mojang/serialization/Codec;IZZ)V"), index = 1)
    private int overrideStructureStartY(int old) {
        return ConstantHeightProvider.create(YOffset.fixed(33)};
    }
}


*/
