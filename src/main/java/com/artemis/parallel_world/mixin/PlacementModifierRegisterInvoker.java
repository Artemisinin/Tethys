package com.artemis.parallel_world.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlacementModifierType.class)
public interface PlacementModifierRegisterInvoker {
    @Invoker("register")
    static  <P extends PlacementModifier> PlacementModifierType<P> register(String registryName, MapCodec<P> codec) {
        throw new AssertionError();
    }
}
