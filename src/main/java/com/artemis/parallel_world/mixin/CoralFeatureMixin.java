package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.block.TethysBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.CoralFeature;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(CoralFeature.class)
public class CoralFeatureMixin {

    @Inject(method = "generateCoralPiece", at = @At(value = "HEAD"), cancellable = true)
    public void improvedGenerateCoralPiece(WorldAccess world, Random random, BlockPos pos, BlockState state, CallbackInfoReturnable<Boolean> cir)
    {
        BlockPos ground = pos.down();
        BlockState groundState = world.getBlockState(ground);
        // Check to see if we're not trying to put coral on sand
        // or that there's not a coral block of that type underneath, to the side, or diagonal down.
        // If not, don't generate.
        if (!groundState.isOf(Blocks.SAND) &&
                !(groundState == state ||
                        world.getBlockState(pos.north()) == state ||
                        world.getBlockState(pos.south()) == state  ||
                        world.getBlockState(pos.east()) == state  ||
                        world.getBlockState(pos.west()) == state  ||
                        world.getBlockState(ground.north()) == state  ||
                        world.getBlockState(ground.south()) == state  ||
                        world.getBlockState(ground.east()) == state  ||
                        world.getBlockState(ground.west()) == state ))
        {
            cir.setReturnValue(false);
        }
    }
}
