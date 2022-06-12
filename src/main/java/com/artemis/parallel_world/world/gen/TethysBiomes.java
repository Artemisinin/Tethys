package com.artemis.parallel_world.world.gen;

import net.minecraft.util.Identifier;
import net.minecraft.util.registry.BuiltinRegistries;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryKey;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.OverworldBiomeCreator;

public class TethysBiomes {

    // Adding a json biome to the registry will allow it to be added to vanilla dimensions.

    public static final RegistryKey<Biome> AMAZON_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "amazon"));
    public static final RegistryKey<Biome> ANCIENT_OAK_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "ancient_oak_forest"));
    public static final RegistryKey<Biome> BAMBOO_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "bamboo_forest"));
    public static final RegistryKey<Biome> CRYSTAL_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "crystal_forest"));
    public static final RegistryKey<Biome> END_DESERT_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "end_desert"));
    public static final RegistryKey<Biome> FAIRY_DARK_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "fairy_dark_forest"));
    public static final RegistryKey<Biome> FAIRY_FLOWER_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "fairy_flower_forest"));
    public static final RegistryKey<Biome> FUNGAL_JUNGLE_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "fungal_jungle"));
    public static final RegistryKey<Biome> HEATH_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "heath"));
    public static final RegistryKey<Biome> ICE_BERGS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "ice_bergs"));
    public static final RegistryKey<Biome> ICE_SPIRES_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "ice_spires"));
    public static final RegistryKey<Biome> JADE_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "jade_forest"));
    public static final RegistryKey<Biome> MYCELIUM_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "mycelium"));
    public static final RegistryKey<Biome> RAINBOW_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "rainbow_forest"));
    public static final RegistryKey<Biome> REEF_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "reef"));
    public static final RegistryKey<Biome> SEQUOIA_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "sequoia_forest"));
    public static final RegistryKey<Biome> SNOW_FOREST_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "snow_forest"));
    public static final RegistryKey<Biome> TRAPS_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "traps"));
    public static final RegistryKey<Biome> WASTELAND_KEY = RegistryKey.of(Registry.BIOME_KEY, new Identifier("parallel_world", "wasteland"));

    public static void registerBiomes() {
        // Create dummy registry entry
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "amazon"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "ancient_oak_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "bamboo_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "crystal_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "end_desert"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "fairy_dark_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "fairy_flower_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "fungal_jungle"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "heath"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "ice_bergs"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "ice_spires"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "jade_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "mycelium"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "rainbow_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "reef"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "sequoia_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "snow_forest"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "traps"), OverworldBiomeCreator.createTheVoid());
        Registry.register(BuiltinRegistries.BIOME, new Identifier("parallel_world", "wasteland"), OverworldBiomeCreator.createTheVoid());

        // Add biome to BuiltinBiomes list
/*        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(AMAZON_KEY)), AMAZON_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(ANCIENT_OAK_FOREST_KEY)), ANCIENT_OAK_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(BAMBOO_FOREST_KEY)), BAMBOO_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(CRYSTAL_FOREST_KEY)), CRYSTAL_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(END_DESERT_KEY)), END_DESERT_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(FAIRY_DARK_FOREST_KEY)), FAIRY_DARK_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(FAIRY_FLOWER_FOREST_KEY)), FAIRY_FLOWER_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(FUNGAL_JUNGLE_KEY)), FUNGAL_JUNGLE_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(HEATH_KEY)), HEATH_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(ICE_BERGS_KEY)), ICE_BERGS_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(ICE_SPIRES_KEY)), ICE_SPIRES_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(JADE_FOREST_KEY)), JADE_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(MYCELIUM_KEY)), MYCELIUM_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(RAINBOW_FOREST_KEY)), RAINBOW_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(REEF_KEY)), REEF_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(SEQUOIA_FOREST_KEY)), SEQUOIA_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(SNOW_FOREST_KEY)), SNOW_FOREST_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(TRAPS_KEY)), TRAPS_KEY);
        BuiltinBiomesAccessor.getBY_RAW_ID().put(BuiltinRegistries.BIOME.getRawId(BuiltinRegistries.BIOME.get(WASTELAND_KEY)), WASTELAND_KEY);*/
    }
}
