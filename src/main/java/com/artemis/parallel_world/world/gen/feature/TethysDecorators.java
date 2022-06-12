package com.artemis.parallel_world.world.gen.feature;


import com.artemis.parallel_world.mixin.PlacementModifierRegisterInvoker;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecorator;
import net.minecraft.world.gen.decorator.PlacementModifierType;

public class TethysDecorators {

    public static PlacementModifierType<WaterMaxDepthDecorator> WATER_MAX_DEPTH_DECORATOR;
    public static PlacementModifierType<WaterMinDepthDecorator> WATER_MIN_DEPTH_DECORATOR;

    public static void registerDecorators() {
        WATER_MAX_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_max_depth_decorator", WaterMaxDepthDecorator.CODEC);
        WATER_MIN_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_min_depth_decorator", WaterMinDepthDecorator.CODEC);
    }
}
