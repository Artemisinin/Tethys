package com.artemis.parallel_world.mixin;


import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.IcebergFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;

import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(IcebergFeature.class)
public abstract class IcebergFeatureMixin {

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/chunk/ChunkGenerator;getSeaLevel()I"))
    private int overrideY(ChunkGenerator chunkGenerator) {

        if(chunkGenerator.getSeaLevel() == 4) {
            return 20;
        } else {
            return (chunkGenerator.getSeaLevel());
        }
    }
}


    //Generates NPE
//    ChunkGenerator chunkGenerator;
//    @ModifyArg(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/util/math/BlockPos;<init>(III)V"), index = 1)
//    private int overrideY(int old) {
//        if (chunkGenerator.getSeaLevel() == 4) {
//            return 20;
//        } else {
//            return chunkGenerator.getSeaLevel();
//        }
//    }
//}

//    @Inject(method = "generate", at = @At(value = "FIELD", target = "Lnet/minecraft/world/gen/feature/IcebergFeature;bl:V", opcode = Opcodes.GETFIELD, ordinal = 0), cancellable = true)
//    protected void raiseSeaLevel(StructureWorldAccess structureWorldAccess, ChunkGenerator chunkGenerator, Random random, BlockPos blockPos, SingleStateFeatureConfig singleStateFeatureConfig, CallbackInfoReturnable<Boolean> cir) {
//        if (chunkGenerator.getSeaLevel() == 4) {
//            blockPos.add(0, 20, 0);
//        }
//        else cir.cancel();
//    }
//}

