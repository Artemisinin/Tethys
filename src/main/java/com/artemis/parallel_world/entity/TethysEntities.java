package com.artemis.parallel_world.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.Heightmap;

public class TethysEntities {

    public static EntityType<FlyingCatEntity> FLYING_CAT;
    public static EntityType<GlowLichenBallEntity> GLOW_LICHEN_BALL;
    public static EntityType<TethysTurtleEntity> TETHYS_TURTLE;
    public static EntityType<WaterStriderEntity> WATER_STRIDER;

    public static void registerEntities()

    {
        FLYING_CAT = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "flying_cat"),
                FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FlyingCatEntity::new).dimensions(EntityDimensions.fixed(0.6f, 0.7f)).build());
            FabricDefaultAttributeRegistry.register(FLYING_CAT, FlyingCatEntity.createFlyingCatAttributes());
        GLOW_LICHEN_BALL = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "glow_lichen_ball"),
                FabricEntityTypeBuilder.<GlowLichenBallEntity>create(SpawnGroup.MISC, GlowLichenBallEntity::new).dimensions(EntityDimensions.fixed(0.25F, 0.25F)).trackRangeBlocks(4).trackedUpdateRate(10).build());
        TETHYS_TURTLE = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "tethys_turtle"),
                FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, TethysTurtleEntity::new).dimensions(EntityDimensions.fixed(1.2f, 0.4f)).build());
            FabricDefaultAttributeRegistry.register(TETHYS_TURTLE, TethysTurtleEntity.createTurtleAttributes());
        WATER_STRIDER = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "water_strider"),
                FabricEntityTypeBuilder.create(SpawnGroup.AMBIENT, WaterStriderEntity::new).dimensions(EntityDimensions.fixed(0.3f, 0.3f)).build());
            FabricDefaultAttributeRegistry.register(WATER_STRIDER, WaterStriderEntity.createWaterStriderAttributes());
    }
}
