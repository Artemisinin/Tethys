package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.fluid.Fluids;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.placementmodifier.AbstractConditionalPlacementModifier;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;


import static com.artemis.parallel_world.world.gen.feature.TethysPlacementModifiers.WATER_MAX_DEPTH_DECORATOR;

public class WaterMaxDepthFeaturePlacementModifier extends AbstractConditionalPlacementModifier {
    private final int maxSubmersion;

    public static final MapCodec<WaterMaxDepthFeaturePlacementModifier> CODEC = RecordCodecBuilder.mapCodec((instance) ->
            instance.group(Codec.INT.fieldOf("max_submersion").forGetter((waterMaxDepthFeaturePlacementModifier) ->
                    waterMaxDepthFeaturePlacementModifier.maxSubmersion)).apply(instance, WaterMaxDepthFeaturePlacementModifier::new));

    public WaterMaxDepthFeaturePlacementModifier(int maxSubmersion) { this.maxSubmersion = maxSubmersion; }

    public static WaterMaxDepthFeaturePlacementModifier of(int maxSubmersion) {
        return new WaterMaxDepthFeaturePlacementModifier(maxSubmersion);
    }

    protected boolean shouldPlace(FeaturePlacementContext context, Random random, BlockPos blockPos) {
        int blocksUp = 1;
        while (blocksUp <= maxSubmersion + 1) {
            BlockPos pos = blockPos.offset(Direction.Axis.Y, blocksUp);
            // If a non-water block is encountered, return block position in stream since it is not submerged. Currently allows *flowing* water.
            if (!context.getWorld().testBlockState(pos, (state) -> state.getFluidState().isEqualAndStill(Fluids.WATER))) {
                return true;
                // Otherwise it was a water block, so increment to check the next block.
            } else {
                blocksUp++;
            }
        }
        return false;
    }

    @Override
    public PlacementModifierType<?> getType() {
        return WATER_MAX_DEPTH_DECORATOR;
    }

}
