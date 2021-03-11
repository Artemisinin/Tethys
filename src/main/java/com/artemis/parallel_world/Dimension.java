package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.surfacebuilder.TethysSurfaceBuilder;
import com.artemis.parallel_world.world.gen.tree.GlowfruitTreeDecorator;
import com.sun.corba.se.spi.ior.IdentifiableFactory;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.biome.v1.OverworldBiomes;
import net.fabricmc.fabric.api.biome.v1.OverworldClimate;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeKeys;
import net.minecraft.world.biome.BuiltinBiomes;
import net.minecraft.world.biome.DefaultBiomeCreator;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.tree.TreeDecoratorType;
import org.lwjgl.system.CallbackI;

import static net.minecraft.world.biome.BiomeKeys.*;


public class Dimension implements ModInitializer {

    public static Tag.Identified<Block> SOIL_BLOCKS;
    public static Tag.Identified<Block> VALID_GROUND_BLOCKS;
    //public static final TreeDecoratorType<GlowfruitTreeDecorator> GLOWFRUIT;

    @Override
    public void onInitialize() {

        // Add portal
        // Custom portal not working in snapshot at the moment.
        //CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register blocks, features, and items and make sure stuff will BURN
        TethysBlocks.registerBlocks();
        TethysItems.registerItems();
        TethysBlocks.registerFlammability();
        TethysFeatures.registerFeatures();
        TethysConfiguredFeatures.registerConfiguredFeatures();
        TethysEntities.registerEntities();
        TethysSurfaceBuilder.registerSurfaceBuilders();
        TethysSurfaceBuilder.registerConfiguredSurfaceBuilders();
        //GLOWFRUIT = Registry.register(Registry.TREE_DECORATOR_TYPE, new Identifier("parallel_world", "glowfruit"),GlowfruitTreeDecorator.CODEC);

        // Register tags
        SOIL_BLOCKS = (Tag.Identified<Block>) TagRegistry.block(new Identifier("parallel_world", "soil_blocks"));
        VALID_GROUND_BLOCKS = (Tag.Identified<Block>) TagRegistry.block(new Identifier("parallel_world", "valid_ground_blocks"));

        // Add configured features to overworld biomes.
        BiomeModifications.addFeature(BiomeSelectors.all(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "cave_scattered_ghost_trees")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(LUKEWARM_OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "scattered_poriferans")));
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_WORLDGEN, new Identifier("parallel_world", "swamp_oak_shrubs")));
    }
}