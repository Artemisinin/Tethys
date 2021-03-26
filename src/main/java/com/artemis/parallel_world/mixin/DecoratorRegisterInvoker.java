package com.artemis.parallel_world.mixin;

import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.decorator.DecoratorConfig;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(Decorator.class)
public interface DecoratorRegisterInvoker {
    @Invoker("register")
    static  <T extends DecoratorConfig, G extends Decorator<T>> G invokeRegister(String registryName, G decorator) {
        throw new AssertionError();
    }
}
