package com.artemis.parallel_world.mixin;

import net.minecraft.registry.RegistryKeys;
import net.minecraft.structure.StructurePiece;
import net.minecraft.structure.StructurePiecesList;
import net.minecraft.structure.StructureStart;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.BlockBox;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.StructureWorldAccess;
import net.minecraft.world.gen.StructureAccessor;
import net.minecraft.world.gen.chunk.ChunkGenerator;
import net.minecraft.world.gen.feature.FeatureConfig;
import net.minecraft.world.gen.structure.JigsawStructure;
import net.minecraft.world.gen.structure.Structure;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;


import java.util.Iterator;
import java.util.List;


@Mixin(StructureStart.class)
public final class StructureStartMixin<C extends FeatureConfig> {

    @Mutable
    @Final
    @Shadow
    private final StructurePiecesList children;

    @Shadow
    @Final
    private Structure structure;

    public StructureStartMixin(StructurePiecesList children) {
        this.children = children;
    }

    @Inject(method = "place", at = @At(value = "INVOKE", target = "Ljava/util/List;iterator()Ljava/util/Iterator;"))
    private void checkAltitude(StructureWorldAccess world, StructureAccessor structureAccessor, ChunkGenerator chunkGenerator, net.minecraft.util.math.random.Random random, BlockBox chunkBox, ChunkPos chunkPos, CallbackInfo ci) {
        List<StructurePiece> list = this.children.pieces();
        Iterator children = list.iterator();
        if (world.getRegistryManager().get(RegistryKeys.DIMENSION_TYPE).getId(world.getDimension()).equals(new Identifier("parallel_world", "tethys")) &&
                this.structure instanceof JigsawStructure) {
                while (children.hasNext()) {
                    StructurePiece structurePiece = (StructurePiece)children.next();
                    if (structurePiece.getBoundingBox().getMinY() < 30) {
                        children.remove();
                    }
                }
            }
            else children = list.iterator();
    }
}

