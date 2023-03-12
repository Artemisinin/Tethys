package com.artemis.parallel_world.world.gen.feature;


import com.artemis.parallel_world.mixin.PlacementModifierRegisterInvoker;
import com.artemis.parallel_world.world.gen.placementmodifier.WaterMaxDepthFeaturePlacementModifier;
import com.artemis.parallel_world.world.gen.placementmodifier.WaterMinDepthFeaturePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class TethysPlacementModifiers {

    public static PlacementModifierType<WaterMaxDepthFeaturePlacementModifier> WATER_MAX_DEPTH_DECORATOR;
    public static PlacementModifierType<WaterMinDepthFeaturePlacementModifier> WATER_MIN_DEPTH_DECORATOR;

    public static void registerDecorators() {
        WATER_MAX_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_max_depth_decorator", WaterMaxDepthFeaturePlacementModifier.CODEC);
        WATER_MIN_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_min_depth_decorator", WaterMinDepthFeaturePlacementModifier.CODEC);
    }
}
