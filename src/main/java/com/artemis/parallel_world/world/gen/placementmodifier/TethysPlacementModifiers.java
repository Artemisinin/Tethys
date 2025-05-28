package com.artemis.parallel_world.world.gen.placementmodifier;


import com.artemis.parallel_world.mixin.PlacementModifierRegisterInvoker;
import net.minecraft.world.gen.placementmodifier.HeightRangePlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

public class TethysPlacementModifiers {

    public static PlacementModifierType<BelowHeightmapPlacementModifier> BELOW_HEIGHTMAP_PLACEMENT_MODIFIER;
    public static PlacementModifierType<HeightRangeUndergroundPlacementModifier> HEIGHT_RANGE_UNDERGROUND;
    public static PlacementModifierType<WaterMaxDepthFeaturePlacementModifier> WATER_MAX_DEPTH_DECORATOR;
    public static PlacementModifierType<WaterMinDepthFeaturePlacementModifier> WATER_MIN_DEPTH_DECORATOR;

    public static void registerDecorators() {
        BELOW_HEIGHTMAP_PLACEMENT_MODIFIER = PlacementModifierRegisterInvoker.register("parallel_world:below_heightmap_placement_modifier", BelowHeightmapPlacementModifier.MODIFIER_CODEC);
        HEIGHT_RANGE_UNDERGROUND = PlacementModifierRegisterInvoker.register("parallel_world:height_range_underground", HeightRangeUndergroundPlacementModifier.MODIFIER_CODEC);
        WATER_MAX_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_max_depth_decorator", WaterMaxDepthFeaturePlacementModifier.CODEC);
        WATER_MIN_DEPTH_DECORATOR = PlacementModifierRegisterInvoker.register("parallel_world:water_min_depth_decorator", WaterMinDepthFeaturePlacementModifier.CODEC);
    }
}
