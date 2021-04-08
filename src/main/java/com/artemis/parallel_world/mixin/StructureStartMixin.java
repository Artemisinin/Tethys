package com.artemis.parallel_world.mixin;

import com.google.common.collect.Lists;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.feature.StructureFeature;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.LocalCapture;


import java.util.Iterator;
import java.util.List;
import java.util.Random;

@Mixin(StructureStart.class)
public final class StructureStartMixin<C extends FeatureConfig> {

    @Final
    @Shadow
    protected final List<StructurePiece> children = Lists.newArrayList();

    @Shadow
    @Final
    private StructureFeature<C> feature;

    @Inject(method = "generateStructure", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private void checkAltitude(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, Random random, BlockBox box, ChunkPos chunkPos, CallbackInfo ci) {
//        Iterator iterator = this.children.iterator();
//        if (world.getRegistryManager().get(Registry.DIMENSION_TYPE_KEY).getId(world.getDimension()).equals(new Identifier("parallel_world", "tethys")) &&
//                this.feature.equals(StructureFeature.VILLAGE)) {
//            while (iterator.hasNext()) {
//                if (this.children.iterator().next().getBoundingBox().getMinY() < 30) {
//                    iterator.remove();
//                }
//            }
//            if (this.children.isEmpty()) {
//                return;
//            } else iterator = this.children.iterator();
//        } else iterator = this.children.iterator();
    }
}

