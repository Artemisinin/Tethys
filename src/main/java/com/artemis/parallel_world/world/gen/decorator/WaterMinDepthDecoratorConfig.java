package com.artemis.parallel_world.world.gen.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.decorator.DecoratorConfig;

public class WaterMinDepthDecoratorConfig implements DecoratorConfig {
    public final int minSubmersion;

    public static final Codec<WaterMinDepthDecoratorConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("min_submersion").forGetter((waterMinDepthDecoratorConfig) -> {
            return waterMinDepthDecoratorConfig.minSubmersion;
        })).apply(instance, WaterMinDepthDecoratorConfig::new);
    });

    public WaterMinDepthDecoratorConfig(int minSubmersion) { this.minSubmersion = minSubmersion; }
}
