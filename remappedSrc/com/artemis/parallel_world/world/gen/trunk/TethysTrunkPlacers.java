package com.artemis.parallel_world.world.gen.trunk;

import com.artemis.parallel_world.mixin.TrunkPlacerTypeRegisterInvoker;
import net.minecraft.world.gen.trunk.TrunkPlacerType;

public class TethysTrunkPlacers {

    public static TrunkPlacerType<HugeTreeTrunkPlacer> HUGE_TREE_TRUNK_PLACER;
    public static TrunkPlacerType<ScaleTreeTrunkPlacer> SCALE_TREE_TRUNK_PLACER;

    public static void registerTrunkPlacers() {
        HUGE_TREE_TRUNK_PLACER = TrunkPlacerTypeRegisterInvoker.invokeRegister("parallel_world:huge_tree_trunk_placer", HugeTreeTrunkPlacer.CODEC);
        SCALE_TREE_TRUNK_PLACER = TrunkPlacerTypeRegisterInvoker.invokeRegister("parallel_world:scale_tree_trunk_placer", ScaleTreeTrunkPlacer.CODEC);
    }
}
