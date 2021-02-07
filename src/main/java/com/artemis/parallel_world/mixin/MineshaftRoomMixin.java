package com.artemis.parallel_world.mixin;


import net.minecraft.structure.MineshaftGenerator.MineshaftRoom;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;


@Mixin(MineshaftRoom.class)
public abstract class MineshaftRoomMixin {

    @Redirect(method = "generate",
            at = @At(value = "INVOKE", target = "net/minecraft/structure/MineshaftGenerator$MineshaftRoom.isTouchingLiquid(Lnet/minecraft/world/BlockView;Lnet/minecraft/util/math/BlockBox;)Z"))

    private boolean checkAirAndWater(MineshaftRoom structure, BlockView world, BlockBox pos) {

        int i = Math.max(structure.getBoundingBox().minX - 1, pos.minX);
        int j = Math.max(structure.getBoundingBox().minY - 1, pos.minY);
        int k = Math.max(structure.getBoundingBox().minZ - 1, pos.minZ);
        int l = Math.min(structure.getBoundingBox().maxX + 1, pos.maxX);
        int m = Math.min(structure.getBoundingBox().maxY + 1, pos.maxY);
        int n = Math.min(structure.getBoundingBox().maxZ + 1, pos.maxZ);
        BlockPos.Mutable mutable = new BlockPos.Mutable();

        int s;
        int t;
        for(s = i; s <= l; ++s) {
            for(t = k; t <= n; ++t) {
                if (world.getBlockState(mutable.set(s, j, t)).getMaterial().isLiquid() || world.getBlockState(mutable.set(s, j, t)).isAir()) {
                    return true;
                }
                if (world.getBlockState(mutable.set(s, m, t)).getMaterial().isLiquid() || world.getBlockState(mutable.set(s, m, t)).isAir()) {
                    return true;
                }
            }
        }
        for(s = i; s <= l; ++s) {
            for(t = j; t <= m; ++t) {
                if (world.getBlockState(mutable.set(s, t, k)).getMaterial().isLiquid() || world.getBlockState(mutable.set(s, t, k)).isAir()) {
                    return true;
                }
                if (world.getBlockState(mutable.set(s, t, n)).getMaterial().isLiquid() || world.getBlockState(mutable.set(s, t, n)).isAir()) {
                    return true;
                }
            }
        }
        for(s = k; s <= n; ++s) {
            for(t = j; t <= m; ++t) {
                if (world.getBlockState(mutable.set(i, t, s)).getMaterial().isLiquid() || world.getBlockState(mutable.set(i, t, s)).isAir()) {
                    return true;
                }
                if (world.getBlockState(mutable.set(l, t, s)).getMaterial().isLiquid() || world.getBlockState(mutable.set(l, t, s)).isAir()) {
                    return true;
                }
            }
        }
        return false;
    }
}
