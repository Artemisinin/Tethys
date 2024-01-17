package com.artemis.parallel_world.world.gen.feature;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.util.math.intprovider.IntProvider;
import net.minecraft.world.gen.blockpredicate.BlockPredicate;
import net.minecraft.world.gen.feature.FeatureConfig;

public record DomeDiskFeatureConfig(BlockState placedBlock, BlockPredicate target, IntProvider radius, IntProvider height) implements FeatureConfig {
    public static final Codec<DomeDiskFeatureConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group
                (BlockState.CODEC.fieldOf("placed_block").forGetter(DomeDiskFeatureConfig::placedBlock),
                        BlockPredicate.BASE_CODEC.fieldOf("target").forGetter(DomeDiskFeatureConfig::target),
                        IntProvider.createValidatingCodec(4, 10).fieldOf("radius").forGetter(DomeDiskFeatureConfig::radius),
                        IntProvider.createValidatingCodec(2, 6).fieldOf("height").forGetter(DomeDiskFeatureConfig::height)).
                apply(instance, DomeDiskFeatureConfig::new);
    });


    public DomeDiskFeatureConfig(BlockState placedBlock, BlockPredicate target, IntProvider radius, IntProvider height) {
        this.placedBlock = placedBlock;
        this.target = target;
        this.radius = radius;
        this.height = height;
    }

    public BlockState placedBlock() {
        return this.placedBlock;
    }
//    public PredicatedStateProvider stateProvider() {
//        return this.stateProvider;
//    }

    public BlockPredicate target() {
        return this.target;
    }

    public IntProvider radius() {
        return this.radius;
    }

    public IntProvider height() {
        return this.height;
    }

}
