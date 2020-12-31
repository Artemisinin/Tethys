package com.artemis.parallel_world.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.CatEntity;
import net.minecraft.world.World;


public class FlyingCatEntity extends CatEntity {
    public FlyingCatEntity(EntityType<? extends CatEntity> entityType, World world) {
            super(entityType, world);
        }
}
