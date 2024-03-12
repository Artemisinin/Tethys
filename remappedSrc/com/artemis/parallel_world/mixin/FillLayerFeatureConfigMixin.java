package com.artemis.parallel_world.mixin;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.dimension.DimensionType;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.FillLayerFeatureConfig;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(FillLayerFeatureConfig.class)
public class FillLayerFeatureConfigMixin implements FeatureConfig {
    @Final
    @Shadow
    public static final Codec<FillLayerFeatureConfig> CODEC = RecordCodecBuilder.create(instance -> instance.group((Codec.intRange(DimensionType.MIN_HEIGHT, DimensionType.MAX_HEIGHT).fieldOf("height")).forGetter(config -> config.height), (BlockState.CODEC.fieldOf("state")).forGetter(config -> config.state)).apply(instance, FillLayerFeatureConfig::new));
    @Final
    @Shadow
    public
    int height;
    @Final
    @Shadow
    public
    BlockState state;
}
