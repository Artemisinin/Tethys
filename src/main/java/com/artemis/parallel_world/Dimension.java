package com.artemis.parallel_world;

import com.artemis.parallel_world.block.*;
import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.item.TethysItems;
import com.artemis.parallel_world.mixin.DecoratorRegisterInvoker;
import com.artemis.parallel_world.mixin.TreeDecoratorTypeRegisterInvoker;
import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMaxDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecorator;
import com.artemis.parallel_world.world.gen.decorator.WaterMinDepthDecoratorConfig;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.artemis.parallel_world.world.gen.feature.TethysFeatures;
import com.artemis.parallel_world.world.gen.surfacebuilder.TethysSurfaceBuilder;
import com.artemis.parallel_world.world.gen.tree.GlowfruitTreeDecorator;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.block.Block;
import net.minecraft.tag.Tag;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.decorator.Decorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;


import static net.minecraft.world.biome.BiomeKeys.*;


public class Dimension implements ModInitializer {

    public static TreeDecoratorType<GlowfruitTreeDecorator> GLOWFRUIT;
    public static Tag.Identified<Block> SOIL_BLOCKS;
    public static Tag.Identified<Block> VALID_GROUND_BLOCKS;
    public static Decorator<WaterMaxDepthDecoratorConfig> WATER_MAX_DEPTH_DECORATOR;
    public static Decorator<WaterMinDepthDecoratorConfig> WATER_MIN_DEPTH_DECORATOR;

    @Override
    public void onInitialize() {

        // Add portal
        // Custom portal not working in snapshot at the moment.
        //CustomPortalApiRegistry.addPortal(Blocks.POLISHED_BLACKSTONE, PortalIgnitionSource.WATER, new Identifier("parallel_world", "tethys"), 23, 140, 176);

        // Register many things
        TethysBiomes.registerBiomes();
        TethysBlocks.registerBlocks();
        TethysItems.registerItems();
        TethysBlocks.registerFlammability();
        WATER_MAX_DEPTH_DECORATOR = DecoratorRegisterInvoker.invokeRegister("parallel_world:water_max_depth_decorator", new WaterMaxDepthDecorator(WaterMaxDepthDecoratorConfig.CODEC));
        WATER_MIN_DEPTH_DECORATOR = DecoratorRegisterInvoker.invokeRegister("parallel_world:water_min_depth_decorator", new WaterMinDepthDecorator(WaterMinDepthDecoratorConfig.CODEC));
        GLOWFRUIT = TreeDecoratorTypeRegisterInvoker.invokeRegister("parallel_world:glowfruit", GlowfruitTreeDecorator.CODEC);
        TethysFeatures.registerFeatures();
        TethysConfiguredFeatures.registerConfiguredFeatures();
        TethysEntities.registerEntities();
        TethysSurfaceBuilder.registerSurfaceBuilders();
        TethysSurfaceBuilder.registerConfiguredSurfaceBuilders();

        // Register tags
        SOIL_BLOCKS = (Tag.Identified<Block>) TagRegistry.block(new Identifier("parallel_world", "soil_blocks"));
        VALID_GROUND_BLOCKS = (Tag.Identified<Block>) TagRegistry.block(new Identifier("parallel_world", "valid_ground_blocks"));

        // Add configured features to overworld biomes
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "cave_scattered_ghost_trees")));
        BiomeModifications.addFeature(BiomeSelectors.includeByKey(OCEAN), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "scattered_poriferans")));
        BiomeModifications.addFeature(BiomeSelectors.categories(Biome.Category.SWAMP), GenerationStep.Feature.VEGETAL_DECORATION, RegistryKey.of(Registry.CONFIGURED_FEATURE_KEY, new Identifier("parallel_world", "swamp_oak_shrubs")));
    }
}