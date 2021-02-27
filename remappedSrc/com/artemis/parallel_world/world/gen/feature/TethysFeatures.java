package com.artemis.parallel_world.world.gen.feature;

import net.minecraft.util.registry.Registry;
import net.minecraft.world.gen.feature.*;


public class TethysFeatures {

    public static Feature<HugeMushroomFeatureConfig> HUGE_HEATH_MUSHROOM_FEATURE;

    private static <C extends FeatureConfig, F extends Feature<C>> F register(String name, F feature) {
        return Registry.register(Registry.FEATURE, name, feature);
    }

    public static void registerFeatures() {
        HUGE_HEATH_MUSHROOM_FEATURE = register("parallel_world:huge_heath_mushroom", new HugeHeathMushroomFeature(HugeMushroomFeatureConfig.CODEC));
    }
}
