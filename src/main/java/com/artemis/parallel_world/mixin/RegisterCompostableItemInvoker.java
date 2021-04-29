package com.artemis.parallel_world.mixin;

import net.minecraft.block.ComposterBlock;
import net.minecraft.item.ItemConvertible;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;


@Mixin(ComposterBlock.class)
public interface RegisterCompostableItemInvoker {
    @Invoker("registerCompostableItem")
    static void invokeRegisterCompostableItem(float levelIncreaseChance, ItemConvertible item) {
        throw new AssertionError();
    }
}