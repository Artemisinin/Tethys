package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.entity.WaterStriderEntity;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Entity.class)
public abstract class EntityMixin {

    Entity entity = (Entity)(Object)this;

    @Inject(method = "onSwimmingStart", at = @At("HEAD"), cancellable = true)
    private void onOnSwimmingStart(CallbackInfo ci) {
        if (entity instanceof WaterStriderEntity) {
            ci.cancel();
        }
    }
}
