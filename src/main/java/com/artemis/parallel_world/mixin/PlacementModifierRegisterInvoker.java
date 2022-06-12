package com.artemis.parallel_world.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.decorator.PlacementModifier;
import net.minecraft.world.gen.decorator.PlacementModifierType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(PlacementModifierType.class)
public interface PlacementModifierRegisterInvoker {
    @Invoker("register")
    static  <P extends PlacementModifier> PlacementModifierType<P> register(String registryName, Codec<P> codec) {
        throw new AssertionError();
    }
}
