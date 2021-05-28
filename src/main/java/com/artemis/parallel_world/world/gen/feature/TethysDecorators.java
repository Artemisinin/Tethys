package com.artemis.parallel_world.world.gen.feature;


import com.artemis.parallel_world.mixin.DecoratorRegisterInvoker;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecoratorConfig;
import net.minecraft.world.gen.decorator.Decorator;

public class TethysDecorators {

    public static Decorator<WaterMaxDepthDecoratorConfig> WATER_MAX_DEPTH_DECORATOR;
    public static Decorator<WaterMinDepthDecoratorConfig> WATER_MIN_DEPTH_DECORATOR;

    public static void registerDecorators() {
        WATER_MAX_DEPTH_DECORATOR = DecoratorRegisterInvoker.invokeRegister("parallel_world:water_max_depth_decorator", new WaterMaxDepthDecorator(WaterMaxDepthDecoratorConfig.CODEC));
        WATER_MIN_DEPTH_DECORATOR = DecoratorRegisterInvoker.invokeRegister("parallel_world:water_min_depth_decorator", new WaterMinDepthDecorator(WaterMinDepthDecoratorConfig.CODEC));
    }
}
