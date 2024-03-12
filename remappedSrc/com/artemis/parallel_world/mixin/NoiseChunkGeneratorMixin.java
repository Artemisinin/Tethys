package com.artemis.parallel_world.mixin;

import net.minecraft.world.gen.chunk.AquiferSampler;
import net.minecraft.world.gen.chunk.ChunkGeneratorSettings;
import net.minecraft.world.gen.chunk.NoiseChunkGenerator;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(NoiseChunkGenerator.class)
public class NoiseChunkGeneratorMixin {

    // Get rid of the lava at the bottom of the world when in the alternate dimension.
    @Inject(method = "createFluidLevelSampler(Lnet/minecraft/world/gen/chunk/ChunkGeneratorSettings;)Lnet/minecraft/world/gen/chunk/AquiferSampler$FluidLevelSampler;", at = @At(value = "RETURN"), cancellable = true)
    private static void removeLava(ChunkGeneratorSettings settings, CallbackInfoReturnable<AquiferSampler.FluidLevelSampler> cir) {
        AquiferSampler.FluidLevel fluidLevel;
        if (settings.seaLevel() == 0) {
            fluidLevel = new AquiferSampler.FluidLevel(0, settings.defaultFluid());
            AquiferSampler.FluidLevelSampler fluidLevelSampler = (x,y,z) -> fluidLevel;
            cir.setReturnValue(fluidLevelSampler);
        }
        cir.cancel();
    }
}
