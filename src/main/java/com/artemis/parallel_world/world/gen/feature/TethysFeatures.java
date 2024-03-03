package com.artemis.parallel_world.world.gen.feature;

import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.gen.CountConfig;
import net.minecraft.world.gen.feature.*;

public class TethysFeatures {

    public static Feature<DomeDiskFeatureConfig> DOME_DISK_FEATURE;
    public static Feature<HugeMushroomFeatureConfig> HUGE_HEATH_FLAT_MUSHROOM_FEATURE;
    public static Feature<HugeMushroomFeatureConfig> HUGE_HEATH_UMBRELLA_MUSHROOM_FEATURE;
    public static Feature<DefaultFeatureConfig> SHORTENED_ICE_SPIKE;
    public static Feature<DefaultFeatureConfig> SKY_ISLAND_GLOWSTONE_FEATURE;
    public static Feature<DefaultFeatureConfig> SWAMP_WATER_FEATURE;
    public static Feature<CountConfig> TETHYS_SEA_PICKLE_FEATURE;
    public static Feature<FillLayerFeatureConfig> WATER_REPLACING_FILL_LAYER_FEATURE;

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registries.FEATURE, name, feature);
    }

    public static void registerFeatures() {
        DOME_DISK_FEATURE = register("parallel_world:dome_disk", new DomeDiskFeature(DomeDiskFeatureConfig.CODEC));
        HUGE_HEATH_FLAT_MUSHROOM_FEATURE = register("parallel_world:flat_mushroom", new HugeHeathFlatMushroomFeature(HugeMushroomFeatureConfig.CODEC));
        HUGE_HEATH_UMBRELLA_MUSHROOM_FEATURE = register("parallel_world:umbrella_mushroom", new HugeHeathUmbrellaMushroomFeature(HugeMushroomFeatureConfig.CODEC));
        SHORTENED_ICE_SPIKE = register("parallel_world:shortened_ice_spike", new IceSpikeShortenedFeature(DefaultFeatureConfig.CODEC));
        SKY_ISLAND_GLOWSTONE_FEATURE = register("parallel_world:sky_island_glowstone", new SkyIslandGlowstoneFeature(DefaultFeatureConfig.CODEC));
        SWAMP_WATER_FEATURE = register("parallel_world:swamp_water", new SwampWaterFeature(DefaultFeatureConfig.CODEC));
        TETHYS_SEA_PICKLE_FEATURE = register("parallel_world:tethys_sea_pickle", new TethysSeaPickleFeature(CountConfig.CODEC));
        WATER_REPLACING_FILL_LAYER_FEATURE = register("parallel_world:water_replacing_fill_layer", new WaterReplacingFillLayerFeature(FillLayerFeatureConfig.CODEC));
    }
}
