package com.artemis.parallel_world.world.gen.treedecorator;

import com.artemis.parallel_world.mixin.TreeDecoratorTypeRegisterInvoker;
import com.mojang.serialization.MapCodec;
import net.minecraft.block.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.gen.feature.Feature;
import net.minecraft.world.gen.stateprovider.BlockStateProvider;
import net.minecraft.world.gen.treedecorator.AlterGroundTreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecorator;
import net.minecraft.world.gen.treedecorator.TreeDecoratorType;

public class AlterGroundPlusSandTreeDecorator extends AlterGroundTreeDecorator {

    public static TreeDecoratorType<AlterGroundPlusSandTreeDecorator> ALTERGROUNDPLUSSAND;

    public static final MapCodec<AlterGroundPlusSandTreeDecorator> CODEC = BlockStateProvider.TYPE_CODEC.fieldOf("provider").
            xmap(AlterGroundPlusSandTreeDecorator::new, decorator -> decorator.provider);

    private final BlockStateProvider provider;

    public AlterGroundPlusSandTreeDecorator(BlockStateProvider provider) {
        super(provider);
        this.provider = provider;
    }

    @Override
    protected void setColumn(TreeDecorator.Generator generator, BlockPos origin) {
        for (int i = 2; i >= -3; --i) {
            BlockPos blockPos = origin.up(i);
            if (Feature.isSoil(generator.getWorld(), blockPos) ||
                    generator.getWorld().testBlockState(blockPos, state -> state.isOf(Blocks.SAND))) {
                generator.replace(blockPos, this.provider.get(generator.getRandom(), origin));
                break;
            }
            if (!generator.isAir(blockPos) && i < 0) break;
        }
    }

    public static void registerAlterGroundPlusSandTreeDecorator() {
        ALTERGROUNDPLUSSAND = TreeDecoratorTypeRegisterInvoker.invokeRegister("parallel_world:alter_ground_plus_sand", AlterGroundPlusSandTreeDecorator.CODEC);
    }
}
