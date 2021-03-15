package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.block.BlockState;
import net.minecraft.world.gen.surfacebuilder.SurfaceConfig;


public class QuaternarySurfaceConfig implements SurfaceConfig {
    public static final Codec<QuaternarySurfaceConfig> CODEC = RecordCodecBuilder.create((instance) -> {
        return instance.group(BlockState.CODEC.fieldOf("top_material").forGetter((config) -> {
            return config.topMaterial;
        }), BlockState.CODEC.fieldOf("under_material").forGetter((config) -> {
            return config.underMaterial;
        }), BlockState.CODEC.fieldOf("underwater_material").forGetter((config) -> {
            return config.underwaterMaterial;
        }), BlockState.CODEC.fieldOf("default_material").forGetter((config) -> {
            return config.defaultMaterial;
        })).apply(instance, QuaternarySurfaceConfig::new);
    });
    private final BlockState topMaterial;
    private final BlockState underMaterial;
    private final BlockState underwaterMaterial;
    private final BlockState defaultMaterial;

    public QuaternarySurfaceConfig(BlockState topMaterial, BlockState underMaterial, BlockState underwaterMaterial, BlockState defaultMaterial) {
        this.topMaterial = topMaterial;
        this.underMaterial = underMaterial;
        this.underwaterMaterial = underwaterMaterial;
        this.defaultMaterial = defaultMaterial;
    }

    public BlockState getTopMaterial() {
        return this.topMaterial;
    }

    public BlockState getUnderMaterial() {
        return this.underMaterial;
    }

    public BlockState getUnderwaterMaterial() {
        return this.underwaterMaterial;
    }

    public BlockState getDefaultMaterial() {
        return this.defaultMaterial;
    }
}
