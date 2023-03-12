package com.artemis.parallel_world.world.gen.carver;

import net.minecraft.world.gen.carver.*;

public class TethysConfiguredCarvers {

    public static ConfiguredCarver<RavineCarverConfig> WASTELAND_CRACK;

    //private static <WC extends CarverConfig> ConfiguredCarver<WC> register(String id, ConfiguredCarver<WC> configuredCarver) {
    //    return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_CARVER, id, configuredCarver);
   // }

    public static void registerCarvers() {
//        WASTELAND_CRACK = register("parallel_world:wasteland_crack", Carver.RAVINE.configure(new RavineCarverConfig(0.0125F,
//                UniformHeightProvider.create(YOffset.fixed(40), YOffset.fixed(80)), UniformFloatProvider.create(6.0F, 8.0F),
//                YOffset.aboveBottom(40), false, CarverDebugConfig.create(false, Blocks.OAK_BUTTON.getDefaultState()), UniformFloatProvider.create(-0.125F, 0.125F),
//                new RavineCarverConfig.Shape(UniformFloatProvider.create(0.5F, 1.0F), UniformFloatProvider.create(0.0F, 1.0F), 6, UniformFloatProvider.create(0.25F, 1.0F),
//                        0.0F, 3.0F))));
    }
}
