package com.artemis.parallel_world.world.gen.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;

public class TethysFeatures {

    public static Feature<HugeMushroomFeatureConfig> HUGE_HEATH_FLAT_MUSHROOM_FEATURE;
    public static Feature<HugeMushroomFeatureConfig> HUGE_HEATH_UMBRELLA_MUSHROOM_FEATURE;
    public static Feature<DefaultFeatureConfig> SKY_ISLAND_GLOWSTONE_FEATURE;
    public static Feature<FillLayerFeatureConfig> WATER_REPLACING_FILL_LAYER_FEATURE;

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }

    public static void registerFeatures() {
        HUGE_HEATH_FLAT_MUSHROOM_FEATURE = register("parallel_world:flat_mushroom", new HugeHeathFlatMushroomFeature(HugeMushroomFeatureConfig.CODEC));
        HUGE_HEATH_UMBRELLA_MUSHROOM_FEATURE = register("parallel_world:umbrella_mushroom", new HugeHeathUmbrellaMushroomFeature(HugeMushroomFeatureConfig.CODEC));
        SKY_ISLAND_GLOWSTONE_FEATURE = register("parallel_world:sky_island_glowstone", new SkyIslandGlowstoneFeature(DefaultFeatureConfig.CODEC));
        WATER_REPLACING_FILL_LAYER_FEATURE = register("parallel_world:water_replacing_fill_layer", new WaterReplacingFillLayerFeature(FillLayerFeatureConfig.CODEC));
    }
}
