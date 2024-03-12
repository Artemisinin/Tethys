package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.Dimension;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SpawnReason;
import net.minecraft.entity.passive.GlowSquidEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.ServerWorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(GlowSquidEntity.class)
public class GlowSquidMixin {
    @Inject(method = "canSpawn", at = @At(value = "RETURN"), cancellable = true)
    private static void canSpawnTethys(EntityType<? extends LivingEntity> type, ServerWorldAccess world, SpawnReason reason, BlockPos pos, Random random, CallbackInfoReturnable<Boolean> cir) {
       if (world.getSeaLevel() == Dimension.TETHYS_SEA_LEVEL &&
               pos.getY() < Dimension.TETHYS_SEA_LEVEL &&
               world.getBaseLightLevel(pos, 0) == 0 &&
               world.getBlockState(pos).isOf(Blocks.WATER)) {
           cir.setReturnValue(true);
       }
       cir.cancel();
    }
}
