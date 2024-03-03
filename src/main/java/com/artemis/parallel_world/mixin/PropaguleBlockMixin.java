package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.block.TethysBlocks;
import net.minecraft.block.BlockState;
import net.minecraft.block.PropaguleBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PropaguleBlock.class)
public class PropaguleBlockMixin {
    @Inject(method = "canPlaceAt", at = @At(value = "RETURN"), cancellable = true)
    private void makeLeavesValid(BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (world.getBlockState(pos.up()).isOf(TethysBlocks.MANGROVE_LEAVES)) {
            cir.setReturnValue(true);
            cir.cancel();
        }
    }

}
