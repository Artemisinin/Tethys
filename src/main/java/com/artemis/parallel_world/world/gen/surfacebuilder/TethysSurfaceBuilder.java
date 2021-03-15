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

    public static SurfaceBuilder<TernarySurfaceConfig>  TETHYS_DEFAULT;
    public static SurfaceBuilder<TernarySurfaceConfig> TETHYS_GIANT_TREE_TAIGA;
    //public static SurfaceBuilder<QuaternarySurfaceBuilder> TEST_QUATERNARY;

    public static ConfiguredSurfaceBuilder<TernarySurfaceConfig> TETHYS_HEATH;

    public static TernarySurfaceConfig TETHYS_HEATH_CONFIG = new TernarySurfaceConfig(Blocks.MOSS_BLOCK.getDefaultState(),Blocks.DIRT.getDefaultState(), Blocks.GRAVEL.getDefaultState());

    public TethysSurfaceBuilder(Codec codec) {
        super(codec);
    }

    // Register SurfaceBuilders
    private static <C extends SurfaceConfig, F extends net.minecraft.world.gen.surfacebuilder.SurfaceBuilder<C>> F register(String id, F surfaceBuilder) {
            return Registry.register(Registry.SURFACE_BUILDER, id, surfaceBuilder);
        }

    // Register ConfiguredSurfaceBuilders
    private static <SC extends SurfaceConfig> ConfiguredSurfaceBuilder<SC> register(String id, ConfiguredSurfaceBuilder<SC> configuredSurfaceBuilder) {
        return BuiltinRegistries.add(BuiltinRegistries.CONFIGURED_SURFACE_BUILDER, id, configuredSurfaceBuilder);
    }


    @Override
    public void generate(Random random, Chunk chunk, Biome biome, int x, int z, int height, double noise, BlockState defaultBlock, BlockState defaultFluid, int seaLevel, long seed, SurfaceConfig surfaceBlocks) {
    }

    public static void registerSurfaceBuilders()
    {
        TETHYS_DEFAULT = register("parallel_world:tethys_default", new SkyIslandSurfaceBuilder(TernarySurfaceConfig.CODEC));
        TETHYS_GIANT_TREE_TAIGA = register("parallel_world:giant_tree_taiga", new TethysGiantTreeTaigaSurfaceBuilder(TernarySurfaceConfig.CODEC));
        //TEST_QUATERNARY = register("parallel_world:test_quaternary", new QuaternarySurfaceBuilder(QuaternarySurfaceConfig.CODEC));
    }

    public static void registerConfiguredSurfaceBuilders()
    {
        TETHYS_HEATH = register("parallel_world:tethys_heath", SurfaceBuilder.DEFAULT.withConfig(TETHYS_HEATH_CONFIG));
    }

}
