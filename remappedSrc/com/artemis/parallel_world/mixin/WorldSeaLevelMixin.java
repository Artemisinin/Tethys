package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.Dimension;
import net.minecraft.registry.RegistryKey;
import net.minecraft.util.Identifier;
import net.minecraft.world.World;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(World.class)
public abstract class WorldSeaLevelMixin {

    @Shadow public abstract RegistryKey<DimensionType> getDimensionKey();

    @Inject(method = "getSeaLevel", at = @At(value = "RETURN"), cancellable = true)
    private void correctSeaLevel(CallbackInfoReturnable<Integer> cir) {
        if (this.getDimensionKey().getValue().equals(new Identifier("parallel_world:tethys"))) {
            cir.setReturnValue(Dimension.TETHYS_SEA_LEVEL);
        }
    }
}
