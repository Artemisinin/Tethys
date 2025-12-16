package com.artemis.parallel_world.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(EntityType.Builder.class)
public class EntityTypeBuilderMixin {

    @Mutable
    @Shadow
    private boolean spawnableFarFromPlayer;

    @Inject(method = "<init>", at = @At("TAIL"))
    private void modifyBuilder(EntityType.EntityFactory factory, SpawnGroup spawnGroup, CallbackInfo ci){
        spawnableFarFromPlayer = spawnGroup == SpawnGroup.CREATURE || spawnGroup == SpawnGroup.MISC || spawnGroup.getName().matches("tethys_creatures");
    }
}
