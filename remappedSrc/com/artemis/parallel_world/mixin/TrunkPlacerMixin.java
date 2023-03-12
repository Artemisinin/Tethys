package com.artemis.parallel_world.mixin;

import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.TestableWorld;
import net.minecraft.world.gen.trunk.TrunkPlacer;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Random;

@Mixin(TrunkPlacer.class)
public class TrunkPlacerMixin {

    @Shadow @Final protected int baseHeight;
    @Shadow @Final protected int firstRandomHeight;
    @Shadow @Final protected int secondRandomHeight;
//    public int instanceFirstRandomHeight;
//    public int instanceSecondRandomHeight;

//    @Inject(method = "getHeight", at = @At(value = "HEAD"), cancellable = true)
//    public void improvedGetHeight(Random random, CallbackInfoReturnable<Integer> cir) {
//        instanceFirstRandomHeight = random.nextInt(this.firstRandomHeight + 1);
//        instanceSecondRandomHeight = random.nextInt(this.secondRandomHeight + 1);
//        cir.setReturnValue(this.baseHeight + instanceFirstRandomHeight + instanceSecondRandomHeight);
//    }

//    @Redirect(method = "setToDirt", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/gen/trunk/TrunkPlacer;canGenerate(Lnet/minecraft/world/TestableWorld;Lnet/minecraft/util/math/BlockPos;)Z"))
//    private static boolean notTurnToDirt(TestableWorld world, BlockPos pos) {
//        return !world.testBlockState(pos, (state) -> state.isOf(Blocks.GRASS_BLOCK)) && !world.testBlockState(pos, (state) -> state.isOf(Blocks.MYCELIUM));
//    }
}