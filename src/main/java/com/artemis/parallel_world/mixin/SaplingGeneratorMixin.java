package com.artemis.parallel_world.mixin;

import com.artemis.parallel_world.block.GetVariableConfiguredTreeFeature;
import com.artemis.parallel_world.world.gen.TethysBiomes;
import com.artemis.parallel_world.world.gen.feature.TethysConfiguredFeatures;
import com.llamalad7.mixinextras.sugar.Local;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.SaplingGenerator;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.gen.feature.ConfiguredFeature;
import net.minecraft.world.gen.feature.TreeConfiguredFeatures;
import org.apache.logging.log4j.core.jmx.Server;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

import java.util.Iterator;
import java.util.Optional;

@Mixin(SaplingGenerator.class)
public abstract class SaplingGeneratorMixin implements GetVariableConfiguredTreeFeature {

    @Shadow @Final private String id;
    @Shadow @Final private float rareChance;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> beesVariant;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> rareBeesVariant;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> megaVariant;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> rareMegaVariant;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> regularVariant;
    @Shadow @Final private Optional<RegistryKey<ConfiguredFeature<?, ?>>> rareRegularVariant;


    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/SaplingGenerator;getSmallTreeFeature(Lnet/minecraft/util/math/random/Random;Z)Lnet/minecraft/registry/RegistryKey;"))
    private RegistryKey<ConfiguredFeature<?,?>> correctSmallTreeFeature(SaplingGenerator saplingGenerator, Random random, boolean flowersNearby, @Local ServerWorld world, @Local BlockPos pos) {
        return getSmallTreeFeature(random, this.areFlowersNearby(world, pos), world, pos);
    }

    @Redirect(method = "generate", at = @At(value = "INVOKE", target = "Lnet/minecraft/block/SaplingGenerator;getMegaTreeFeature(Lnet/minecraft/util/math/random/Random;)Lnet/minecraft/registry/RegistryKey;"))
    private RegistryKey<ConfiguredFeature<?,?>> correctMegaTreeFeature(SaplingGenerator saplingGenerator, Random random,  @Local ServerWorld world, @Local BlockPos pos) {
        return getMegaTreeFeature(random, world, pos);
    }

    @Override
    @Nullable
    public RegistryKey<ConfiguredFeature<?, ?>> getSmallTreeFeature(Random random, boolean flowersNearby, ServerWorld world, BlockPos pos) {
        BlockState groundState = world.getBlockState(pos.down());
        RegistryKey<ConfiguredFeature<?,?>> tree = null;
        if (groundState.isOf(Blocks.MOSS_BLOCK)) {
            switch (id) {
                case "birch" -> tree = TethysConfiguredFeatures.BIRCH_SHRUB;
                case "dark_oak" -> tree = random.nextBoolean() ? TethysConfiguredFeatures.DARK_OAK_SHRUB_SHORT : TethysConfiguredFeatures.DARK_OAK_SHRUB_TALL;
                case "jungle" -> tree = TethysConfiguredFeatures.JUNGLE_SHRUB;
                case "oak" -> tree = TethysConfiguredFeatures.OAK_SHRUB;
                case "spruce" -> tree = random.nextBoolean() ? TethysConfiguredFeatures.SPRUCE_SHRUB_SHORT : TethysConfiguredFeatures.SPRUCE_SHRUB_TALL;
            }
            if (tree != null) {
                return tree;
            }
        }
        if (world.getBiome(pos).isIn(TethysBiomes.GROWS_SHORT_MANGROVE) && (id.equals("mangrove"))) {
            return TethysConfiguredFeatures.SMALL_MANGROVE;
        }
        if (random.nextFloat() < this.rareChance) {
            if (flowersNearby && this.rareBeesVariant.isPresent()) {
                return this.rareBeesVariant.get();
            }
            if (this.rareRegularVariant.isPresent()) {
                return this.rareRegularVariant.get();
            }
        }
        if (flowersNearby && this.beesVariant.isPresent()) {
            return this.beesVariant.get();
        }
        return this.regularVariant.orElse(null);
    }

    @Override
    @Nullable
    public RegistryKey<ConfiguredFeature<?, ?>> getMegaTreeFeature(Random random, ServerWorld world, BlockPos pos) {
        if (id.equals("dark_oak")) {
            if (world.getBiome(pos).matchesId(new Identifier("parallel_world:fairy_dark_forest"))) {
                return TethysConfiguredFeatures.DARK_OAK_GLOWFRUIT;
            }
            if (world.getBiome(pos).isIn(TethysBiomes.IS_TETHYS)) {
                return random.nextBoolean() ? TethysConfiguredFeatures.DARK_OAK_GLOWFRUIT : TreeConfiguredFeatures.DARK_OAK;
            }
        }
        if (this.rareMegaVariant.isPresent() && random.nextFloat() < this.rareChance) {
            return this.rareMegaVariant.get();
        }
        return this.megaVariant.orElse(null);
    }

    private boolean areFlowersNearby(WorldAccess world, BlockPos pos) {
        Iterator<BlockPos> mutableIterator = BlockPos.Mutable.iterate(pos.down().north(2).west(2), pos.up().south(2).east(2)).iterator();

        BlockPos blockPos;
        do {
            if (!mutableIterator.hasNext()) {
                return false;
            }

            blockPos = mutableIterator.next();
        } while(!world.getBlockState(blockPos).isIn(BlockTags.FLOWERS));

        return true;
    }
}
