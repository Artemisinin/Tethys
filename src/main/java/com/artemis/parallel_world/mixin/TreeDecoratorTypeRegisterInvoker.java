package com.artemis.parallel_world.mixin;

import com.mojang.serialization.MapCodec;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;

@Mixin(TreeDecoratorType.class)
public interface TreeDecoratorTypeRegisterInvoker {
    @Invoker("register")
    static <P extends TreeDecorator> TreeDecoratorType<P> invokeRegister(String id, MapCodec<P> codec) {
        throw new AssertionError();
    }
}
