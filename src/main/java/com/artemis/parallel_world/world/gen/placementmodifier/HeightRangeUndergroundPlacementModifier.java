package com.artemis.parallel_world.world.gen.placementmodifier;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.Heightmap;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.YOffset;
import net.minecraft.world.gen.feature.FeaturePlacementContext;
import net.minecraft.world.gen.heightprovider.HeightProvider;
import net.minecraft.world.gen.heightprovider.TrapezoidHeightProvider;
import net.minecraft.world.gen.heightprovider.UniformHeightProvider;
import net.minecraft.world.gen.placementmodifier.PlacementModifier;
import net.minecraft.world.gen.placementmodifier.PlacementModifierType;

import java.util.stream.Stream;

public class HeightRangeUndergroundPlacementModifier extends PlacementModifier {

    public static final MapCodec<HeightRangeUndergroundPlacementModifier> MODIFIER_CODEC = RecordCodecBuilder.mapCodec(
            instance -> instance.group(HeightProvider.CODEC.fieldOf("height").forGetter(heightRangeUndergroundPlacementModifier -> heightRangeUndergroundPlacementModifier.height))
                    .apply(instance, HeightRangeUndergroundPlacementModifier::new)
    );
    private final HeightProvider height;

    private HeightRangeUndergroundPlacementModifier(HeightProvider height) {
        this.height = height;
    }

    public static HeightRangeUndergroundPlacementModifier of(HeightProvider height) {
        return new HeightRangeUndergroundPlacementModifier(height);
    }

    public static HeightRangeUndergroundPlacementModifier uniform(YOffset minOffset, YOffset maxOffset) {
        return of(UniformHeightProvider.create(minOffset, maxOffset));
    }

    public static HeightRangeUndergroundPlacementModifier trapezoid(YOffset minOffset, YOffset maxOffset) {
        return of(TrapezoidHeightProvider.create(minOffset, maxOffset));
    }

    @Override
    public Stream<BlockPos> getPositions(FeaturePlacementContext context, Random random, BlockPos pos) {
        StructureWorldAccess structureWorldAccess = context.getWorld();
        int randomY = this.height.get(random, context);
        int surface = structureWorldAccess.getTopY(Heightmap.Type.OCEAN_FLOOR_WG, pos.getX(), pos.getZ());
        if (randomY < surface) {
            return Stream.of(pos.withY(randomY));
        }
        return Stream.of();
    }

    @Override
    public PlacementModifierType<?> getType() {
        return PlacementModifierType.HEIGHT_RANGE;
    }
}
