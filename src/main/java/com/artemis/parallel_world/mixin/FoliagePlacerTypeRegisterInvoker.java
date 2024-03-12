package com.artemis.parallel_world.mixin;

import com.mojang.serialization.Codec;
import net.minecraft.world.gen.foliage.FoliagePlacer;
import net.minecraft.world.gen.foliage.FoliagePlacerType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(FoliagePlacerType.class)
public interface FoliagePlacerTypeRegisterInvoker {
    @Invoker("register")
    static <P extends FoliagePlacer> FoliagePlacerType<P> invokeRegister(String id, Codec<P> codec) {
        throw new AssertionError();
    }
}