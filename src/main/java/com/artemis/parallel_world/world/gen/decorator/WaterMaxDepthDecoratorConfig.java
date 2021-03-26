package com.artemis.parallel_world.world.gen.decorator;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.world.gen.decorator.DecoratorConfig;

public class WaterMaxDepthDecoratorConfig implements DecoratorConfig {
    public static final Codec<WaterMaxDepthDecoratorConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(Codec.INT.fieldOf("max_submersion").forGetter((waterMaxDepthDecoratorConfig) -> {
            return waterMaxDepthDecoratorConfig.maxSubmersion;
        })).apply(instance, WaterMaxDepthDecoratorConfig::new);
    });
    public final int maxSubmersion;

    public WaterMaxDepthDecoratorConfig(int maxSubmersion) { this.maxSubmersion = maxSubmersion; }
}
