package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.entity.*;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnLocation;
import net.minecraft.entity.SpawnLocationTypes;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


@Mixin(SpawnRestriction.class)
public abstract class SpawnRestrictionMixin {

    @Shadow
    public static <T extends MobEntity> void register(EntityType<T> type, SpawnLocation location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
    }

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private static void registerTethysSpawnRestriction(CallbackInfo ci) {
        register(TethysEntities.FLYING_CAT, SpawnLocationTypes.UNRESTRICTED, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingCatEntity::canSpawn);
        register(TethysEntities.TETHYS_TURTLE, SpawnLocationTypes.IN_WATER, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, TethysTurtleEntity::canSpawn);
        register(TethysEntities.WATER_STRIDER, SpawnLocationTypes.UNRESTRICTED, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, WaterStriderEntity::canSpawn);
    }
}
