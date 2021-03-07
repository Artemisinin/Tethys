package com.artemis.parallel_world.mixin;

import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LeavesBlock.class)
public abstract class LeavesBlockMixin extends Block implements Waterloggable {

    @Shadow
    private static BlockState updateDistanceFromLogs(BlockState state, WorldAccess world, BlockPos pos) {
        return null;
    }

    @Shadow @Final public static BooleanProperty PERSISTENT;
    private static BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public LeavesBlockMixin(Settings settings) {
        super(settings);
    }

    @Inject(method = "appendProperties", at = @At(value = "TAIL"))
    private void addWaterloggableProperty(StateManager.Builder<Block, BlockState> builder, CallbackInfo ci)
    {
        builder.add(WATERLOGGED);
    }

    @Inject(method = "<init>", at = @At(value = "TAIL"))
    private void addDefaultStateWaterloggable(AbstractBlock.Settings settings, CallbackInfo ci)
    {
        this.setDefaultState(this.getDefaultState().with(WATERLOGGED, false));
    }

    @Inject(method = "getStateForNeighborUpdate", at = @At(value = "TAIL"))
    private void wateryNeighborUpdate(BlockState state, Direction direction, BlockState neighborState, WorldAccess world, BlockPos pos, BlockPos neighborPos, CallbackInfoReturnable<BlockState> cir)
    {
        if (state.get(WATERLOGGED)) {
            world.getFluidTickScheduler().schedule(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
    }

    @Override
    public FluidState getFluidState(BlockState state)
    {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    @Inject(method = "getPlacementState", at = @At(value = "TAIL"), cancellable = true)
    private void getPlacementState(ItemPlacementContext ctx, CallbackInfoReturnable<BlockState> cir) {
        FluidState fluidState = ctx.getWorld().getFluidState(ctx.getBlockPos());
        cir.setReturnValue(updateDistanceFromLogs(this.getDefaultState().with(PERSISTENT, true).with(WATERLOGGED, fluidState.isEqualAndStill(Fluids.WATER)), ctx.getWorld(), ctx.getBlockPos()));
    }
}
