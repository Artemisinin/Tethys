package com.artemis.parallel_world.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;


public enum FlyingCatVariant implements StringIdentifiable {
    ALL_BLACK(0, "flying_cat_all_black"),
    BLACK(1, "flying_cat_black"),
    BRITISH_SHORTHAIR(2, "flying_cat_british_shorthair"),
    CALICO(3, "flying_cat_calico"),
    JELLIE(4, "flying_cat_jellie"),
    OCELOT(5, "flying_cat_ocelot"),
    PERSIAN(6, "flying_cat_persian"),
    RAGDOLL(7, "flying_cat_ragdoll"),
    RED(8, "flying_cat_red"),
    SIAMESE(9, "flying_cat_siamese"),
    TABBY(10, "flying_cat_tabby"),
    WHITE(11, "flying_cat_white");

    public static final Codec<FlyingCatVariant> CODEC = StringIdentifiable.createCodec(FlyingCatVariant::values);
    private static final IntFunction<FlyingCatVariant> BY_ID = ValueLists.createIdToValueFunction(FlyingCatVariant::getId, values(), ValueLists.OutOfBoundsHandling.WRAP);
    private final int id;
    private final String name;

    FlyingCatVariant(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return this.id;
    }

    public static FlyingCatVariant byId(int id) {
        return BY_ID.apply(id);
    }

    public String asString() {
        return this.name;
    }
}
