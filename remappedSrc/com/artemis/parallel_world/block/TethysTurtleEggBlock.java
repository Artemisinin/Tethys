package com.artemis.parallel_world.block;

import com.artemis.parallel_world.entity.TethysEntities;
import com.artemis.parallel_world.entity.TethysTurtleEntity;
import net.minecraft.block.*;
import net.minecraft.fluid.FluidState;
import net.minecraft.fluid.Fluids;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.BooleanProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.jetbrains.annotations.Nullable;

public class TethysTurtleEggBlock extends TurtleEggBlock implements Waterloggable {

    // Currently these turtle eggs are not crushable and will not be targeted by zombies.

    public static final BooleanProperty WATERLOGGED = Properties.WATERLOGGED;

    public TethysTurtleEggBlock(Settings settings) {
        super(settings);
        this.setDefaultState(this.stateManager.getDefaultState().with(HATCH, 0).with(EGGS, 1).with(WATERLOGGED, false));
    }

    @Override
    protected void appendProperties(StateManager.Builder<Block, BlockState> builder) {
         builder.add(WATERLOGGED, HATCH, EGGS);
    }

    @Nullable
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        BlockState blockState = ctx.getWorld().getBlockState(ctx.getBlockPos());
        return blockState.isOf(this) ? blockState.with(EGGS, Math.min(4, blockState.get(EGGS) + 1)) : super.getPlacementState(ctx);
    }

    public FluidState getFluidState(BlockState state) {
        return state.get(WATERLOGGED) ? Fluids.WATER.getStill(false) : super.getFluidState(state);
    }

    private boolean shouldHatchProgress(World world) {
        float f = world.getSkyAngle(1.0F);
        if ((double)f < 0.69D && (double)f > 0.65D) {
            return true;
        } else {
            return world.random.nextInt(500) == 0;
        }
    }

    @Override
    public void randomTick(BlockState state, ServerWorld world, BlockPos pos, Random random) {
        if (this.shouldHatchProgress(world) && isSand(world, pos)) {
            int i = state.get(HATCH);
            if (i < 2) {
                world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_CRACK, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.setBlockState(pos, state.with(HATCH, i + 1), 2);
            } else {
                world.playSound(null, pos, SoundEvents.ENTITY_TURTLE_EGG_HATCH, SoundCategory.BLOCKS, 0.7F, 0.9F + random.nextFloat() * 0.2F);
                world.removeBlock(pos, false);

                for(int j = 0; j < state.get(EGGS); ++j) {
                    world.syncWorldEvent(2001, pos, Block.getRawIdFromState(state));
                    TethysTurtleEntity tethysTurtleEntity = TethysEntities.TETHYS_TURTLE.create(world);
                    tethysTurtleEntity.setBreedingAge(-24000);
                    tethysTurtleEntity.setHomePos(pos);
                    tethysTurtleEntity.refreshPositionAndAngles((double)pos.getX() + 0.3D + (double)j * 0.2D, (double)pos.getY(), (double)pos.getZ() + 0.3D, 0.0F, 0.0F);
                    world.spawnEntity(tethysTurtleEntity);
                }
            }
        }
    }

    public BlockState getStateForNeighborUpdate(BlockState state, Direction direction, BlockState newState, WorldAccess world, BlockPos pos, BlockPos posFrom) {
        if (state.get(WATERLOGGED)) {
            world.scheduleFluidTick(pos, Fluids.WATER, Fluids.WATER.getTickRate(world));
        }
        return super.getStateForNeighborUpdate(state, direction, newState, world, pos, posFrom);
    }
}