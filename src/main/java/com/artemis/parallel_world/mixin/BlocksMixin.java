package com.artemis.parallel_world.mixin;


import net.minecraft.block.*;
import net.minecraft.sound.BlockSoundGroup;
import org.objectweb.asm.Opcodes;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyArg;
import org.spongepowered.asm.mixin.injection.Slice;


@Mixin(Blocks.class)
public class BlocksMixin {

    @ModifyArg(
            method = "<clinit>",
            slice = @Slice(
                    from = @At(value = "CONSTANT", args = "stringValue=moss_block")),
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/Blocks;register(Ljava/lang/String;Lnet/minecraft/block/Block;)Lnet/minecraft/block/Block;", ordinal = 0), index = 1)
    private static Block setMossSolidOrganic(Block block) {
        return new MossBlock(AbstractBlock.Settings.of(Material.SOLID_ORGANIC, MapColor.GREEN).strength(0.1F).sounds(BlockSoundGroup.MOSS));
    }
}
