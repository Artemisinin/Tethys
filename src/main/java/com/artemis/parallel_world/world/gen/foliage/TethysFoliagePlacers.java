package com.artemis.parallel_world.world.gen.foliage;

import com.artemis.parallel_world.mixin.FoliagePlacerTypeRegisterInvoker;
import net.minecraft.world.gen.foliage.FoliagePlacerType;

public class TethysFoliagePlacers {
    public static FoliagePlacerType<ConeFoliagePlacer> CONE_FOLIAGE_PLACER;
    public static FoliagePlacerType<WeepingFoliagePlacer> WEEPING_FOLIAGE_PLACER;

    public static void registerFoliagePlacers () {
        CONE_FOLIAGE_PLACER = FoliagePlacerTypeRegisterInvoker.invokeRegister("parallel_world:cone_foliage_placer", ConeFoliagePlacer.CODEC);
        WEEPING_FOLIAGE_PLACER = FoliagePlacerTypeRegisterInvoker.invokeRegister("parallel_world:weeping_foliage_placer", WeepingFoliagePlacer.CODEC);
    }
}
