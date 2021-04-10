package com.artemis.parallel_world.world.gen.surfacebuilder;

import com.artemis.parallel_world.world.gen.surfacebuilder.SkyIslandSurfaceBuilder;
import com.mojang.serialization.Codec;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.gen.surfacebuilder.*;

import java.util.Random;

public abstract class TethysSurfaceBuilder <C extends SurfaceConfig> extends SurfaceBuilder {

    public static SurfaceBuilder<TernarySurfaceConfig> TETHYS_DEFAULT;
    public static SurfaceBuilder<TernarySurfaceConfig> TETHYS_GIANT_TREE_TAIGA;

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
        TETHYS_DEFAULT = register("parallel_world:tethys_default", new SkyIslandSurfaceBuilder(TernarySurfaceConfig.CODEC));
        TETHYS_GIANT_TREE_TAIGA = register("parallel_world:giant_tree_taiga", new TethysGiantTreeTaigaSurfaceBuilder(TernarySurfaceConfig.CODEC));
    }
}
