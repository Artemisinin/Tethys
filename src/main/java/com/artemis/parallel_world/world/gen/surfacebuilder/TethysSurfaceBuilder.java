package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.*;

import java.util.Random;

public abstract class TethysSurfaceBuilder <C extends SurfaceConfig> extends SurfaceBuilder {

    public static SurfaceBuilder<TernarySurfaceConfig> TETHYS_SWAMP;

    public TethysSurfaceBuilder(Codec codec) {
        super(codec);
    }

    private static <C extends SurfaceConfig, F extends net.minecraft.world.gen.surfacebuilder.SurfaceBuilder<C>> F register(String id, F surfaceBuilder) {
            return Registry.register(Registry.SURFACE_BUILDER, id, surfaceBuilder);
        }

    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, int i, long seed, SurfaceConfig surfaceBlocks) {
    }

    public static void registerSurfaceBuilders()
    {
        TETHYS_SWAMP = register("parallel_world:tethys_swamp", new TethysSwampSurfaceBuilder(TernarySurfaceConfig.CODEC));
    }
}
