package com.artemis.parallel_world.world.gen.carver;


import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.world.gen.ProbabilityConfig;
import net.minecraft.world.gen.carver.Carver;
import net.minecraft.world.gen.carver.CarverConfig;
import net.minecraft.world.gen.carver.ConfiguredCarver;

public class TethysConfiguredCarvers {

    public static final ConfiguredCarver<ProbabilityConfig> GULLY;

    private static <WC extends CarverConfig> ConfiguredCarver<WC> register(String id, ConfiguredCarver<WC> configuredCarver) {
        return (ConfiguredCarver) BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_CARVER, id, configuredCarver);
    }

    static {
        GULLY = register("gully", Carver.CANYON.configure(new ProbabilityConfig(0.02F)));

    }

}
