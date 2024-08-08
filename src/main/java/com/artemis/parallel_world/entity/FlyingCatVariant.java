package com.artemis.parallel_world.entity;

import com.mojang.serialization.Codec;
import net.minecraft.util.StringIdentifiable;
import net.minecraft.util.function.ValueLists;

import java.util.function.IntFunction;

public enum FlyingCatVariant implements StringIdentifiable {
    TABBY(0, "flying_cat_tabby"),
    BLACK(1, "flying_cat_black"),
    RED(2, "flying_cat_red"),
    SIAMESE(3, "flying_cat_siamese"),
    BRITISH_SHORTHAIR(4, "flying_cat_british_shorthair"),
    CALICO(5, "flying_cat_calico"),
    PERSIAN(6, "flying_cat_persian"),
    RAGDOLL(7, "flying_cat_ragdoll"),
    WHITE(8, "flying_cat_white"),
    JELLIE(9, "flying_cat_jellie"),
    ALL_BLACK(10, "flying_cat_all_black");

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
