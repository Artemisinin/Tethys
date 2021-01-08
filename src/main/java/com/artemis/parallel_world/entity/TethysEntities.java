package com.artemis.parallel_world.entity;

import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import org.lwjgl.system.CallbackI;

public class TethysEntities {

    public static EntityType<TethysTurtleEntity> TETHYS_TURTLE;
    public static EntityType<FlyingCatEntity> FLYING_CAT;

    public static void registerEntities()

    {
        // Add turtles
        TETHYS_TURTLE = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "tethys_turtle"),
            FabricEntityTypeBuilder.create(SpawnGroup.WATER_CREATURE, TethysTurtleEntity::new).dimensions(EntityDimensions.fixed(1.2f, 0.4f)).build());
        FabricDefaultAttributeRegistry.register(TETHYS_TURTLE, TethysTurtleEntity.createTurtleAttributes());

        // Add flying cats
        FLYING_CAT = Registry.register(Registry.ENTITY_TYPE, new Identifier("parallel_world", "flying_cat"),
            FabricEntityTypeBuilder.create(SpawnGroup.CREATURE, FlyingCatEntity::new).dimensions(EntityDimensions.fixed(0.6f, 0.7f)).build());
        FabricDefaultAttributeRegistry.register(FLYING_CAT, FlyingCatEntity.createFlyingCatAttributes());
        // This before was CatEntity.createCatAttributes()
    }





}
