package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.entity.FlyingCatEntity;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.entity.TethysTurtleEntity;
import com.artemis.parallel_world.entity.WaterStriderEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.world.Heightmap;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;


@Mixin(SpawnRestriction.class)
public abstract class SpawnRestrictionMixin {

    @Shadow private static <T extends MobEntity> void register(EntityType<T> type, SpawnRestriction.Location location, Heightmap.Type heightmapType, SpawnRestriction.SpawnPredicate<T> predicate) {
    }

    @Inject(method = "<clinit>", at = @At(value = "TAIL"))
    private void registerTethysSpawnRestriction() {
        register(TethysEntities.FLYING_CAT, SpawnRestriction.Location.ON_GROUND, Heightmap.Type.MOTION_BLOCKING_NO_LEAVES, FlyingCatEntity::canSpawn);
        register(TethysEntities.TETHYS_TURTLE, SpawnRestriction.Location.IN_WATER, Heightmap.Type.OCEAN_FLOOR, TethysTurtleEntity::isValidNaturalSpawn);
        register(TethysEntities.WATER_STRIDER, SpawnRestriction.Location.IN_WATER, Heightmap.Type.WORLD_SURFACE, WaterStriderEntity::canSpawn);
    }
}
