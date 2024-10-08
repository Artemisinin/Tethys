package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.Dimension;
import com.artemis.parallel_world.block.TethysTurtleEggBlock;
import net.minecraft.advancement.criterion.Criteria;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.TurtleEggBlock;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.NoPenaltyTargeting;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.*;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.data.DataTracker.Builder;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.registry.tag.FluidTags;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.*;
import org.jetbrains.annotations.Nullable;
import com.artemis.parallel_world.block.*;



public class TethysTurtleEntity extends AnimalEntity {

    public TethysTurtleEntity(EntityType<? extends TethysTurtleEntity> entityType, World world) {
        super(entityType, world);
        this.setPathfindingPenalty(PathNodeType.WATER, 0.0F);
        this.moveControl = new TethysTurtleEntity.TurtleMoveControl(this);
    }

    private static final TrackedData<BlockPos> HOME_POS = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Boolean> HAS_EGG  = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> DIGGING_SAND = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<BlockPos> TRAVEL_POS = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BLOCK_POS);
    private static final TrackedData<Boolean> LAND_BOUND = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> ACTIVELY_TRAVELLING = DataTracker.registerData(TethysTurtleEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    public static final Ingredient BREEDING_ITEM = Ingredient.ofItems(Blocks.SEAGRASS.asItem());

    private int sandDiggingCounter;

    public void setHomePos(BlockPos pos) {
        this.dataTracker.set(HOME_POS, pos);
    }

    private BlockPos getHomePos() {
        return this.dataTracker.get(HOME_POS);
    }

    private void setTravelPos(BlockPos pos) {
        this.dataTracker.set(TRAVEL_POS, pos);
    }

    private BlockPos getTravelPos() {
        return this.dataTracker.get(TRAVEL_POS);
    }

    public boolean hasEgg() {
        return this.dataTracker.get(HAS_EGG);
    }

    private void setHasEgg(boolean hasEgg) {
        this.dataTracker.set(HAS_EGG, hasEgg);
    }

    public boolean isDiggingSand() {
        return this.dataTracker.get(DIGGING_SAND);
    }

    private void setDiggingSand(boolean diggingSand) {
        this.sandDiggingCounter = diggingSand ? 1 : 0;
        this.dataTracker.set(DIGGING_SAND, diggingSand);
    }

    private boolean isLandBound() {
        return this.dataTracker.get(LAND_BOUND);
    }

    private void setLandBound(boolean landBound) {
        this.dataTracker.set(LAND_BOUND, landBound);
    }

    private boolean isActivelyTravelling() {
        return this.dataTracker.get(ACTIVELY_TRAVELLING);
    }

    private void setActivelyTravelling(boolean travelling) {
        this.dataTracker.set(ACTIVELY_TRAVELLING, travelling);
    }

    protected void initDataTracker(Builder builder) {
        super.initDataTracker(builder);
        builder.add(HOME_POS, BlockPos.ORIGIN);
        builder.add(HAS_EGG, false);
        builder.add(TRAVEL_POS, BlockPos.ORIGIN);
        builder.add(LAND_BOUND, false);
        builder.add(ACTIVELY_TRAVELLING, false);
        builder.add(DIGGING_SAND, false);
    }

    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putInt("HomePosX", this.getHomePos().getX());
        nbt.putInt("HomePosY", this.getHomePos().getY());
        nbt.putInt("HomePosZ", this.getHomePos().getZ());
        nbt.putBoolean("HasEgg", this.hasEgg());
        nbt.putInt("TravelPosX", this.getTravelPos().getX());
        nbt.putInt("TravelPosY", this.getTravelPos().getY());
        nbt.putInt("TravelPosZ", this.getTravelPos().getZ());
    }

    public void readCustomDataFromNbt(NbtCompound nbt) {
        int i = nbt.getInt("HomePosX");
        int j = nbt.getInt("HomePosY");
        int k = nbt.getInt("HomePosZ");
        this.setHomePos(new BlockPos(i, j, k));
        super.readCustomDataFromNbt(nbt);
        this.setHasEgg(nbt.getBoolean("HasEgg"));
        int l = nbt.getInt("TravelPosX");
        int m = nbt.getInt("TravelPosY");
        int n = nbt.getInt("TravelPosZ");
        this.setTravelPos(new BlockPos(l, m, n));
    }

    @Nullable
    public EntityData initialize(ServerWorldAccess world, LocalDifficulty difficulty, SpawnReason spawnReason, @Nullable EntityData entityData) {
        this.setHomePos(this.getBlockPos());
        this.setTravelPos(BlockPos.ORIGIN);
        return super.initialize(world, difficulty, spawnReason, entityData);
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return TethysEntities.TETHYS_TURTLE.create(world);
    }

    public static boolean canSpawn(EntityType<TethysTurtleEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        return pos.getY() < (Dimension.TETHYS_SEA_LEVEL + 4) && world.getBlockState(pos.down()).isIn(BlockTags.SAND) && world.getBaseLightLevel(pos, 0) > 8;
    }

    protected void initGoals() {
        this.goalSelector.add(0, new TethysTurtleEntity.TurtleEscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(1, new TethysTurtleEntity.MateGoal(this, 1.0D));
        this.goalSelector.add(1, new TethysTurtleEntity.LayEggGoal(this, 1.0D));
        this.goalSelector.add(2, new TemptGoal(this, 1.1D, BREEDING_ITEM, false));
        this.goalSelector.add(3, new TethysTurtleEntity.WanderInWaterGoal(this, 1.0D));
        this.goalSelector.add(4, new TethysTurtleEntity.GoHomeGoal(this, 1.0D));
        this.goalSelector.add(7, new TethysTurtleEntity.TravelGoal(this, 1.0D));
        this.goalSelector.add(8, new LookAtEntityGoal(this, PlayerEntity.class, 8.0F));
        this.goalSelector.add(9, new TethysTurtleEntity.WanderOnLandGoal(this, 1.0D, 100));
    }

    public static net.minecraft.entity.attribute.DefaultAttributeContainer.Builder createTurtleAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 30.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.25D);
    }
    // Add to tag group
    //public boolean canBreatheInWater() {
    //    return true;
    //}

    // Apparently this got nuked in 20.5 or 20.6
    /*
    public EntityGroup getGroup() {
        return EntityGroup.AQUATIC;
    }*/

    public int getMinAmbientSoundDelay() {
        return 200;
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        return !this.isTouchingWater() && this.isOnGround() && !this.isBaby() ? SoundEvents.ENTITY_TURTLE_AMBIENT_LAND : super.getAmbientSound();
    }

    protected void playSwimSound(float volume) {
        super.playSwimSound(volume * 1.5F);
    }

    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_TURTLE_SWIM;
    }

    @Nullable
    protected SoundEvent getHurtSound(DamageSource source) {
        return this.isBaby() ? SoundEvents.ENTITY_TURTLE_HURT_BABY : SoundEvents.ENTITY_TURTLE_HURT;
    }

    @Nullable
    protected SoundEvent getDeathSound() {
        return this.isBaby() ? SoundEvents.ENTITY_TURTLE_DEATH_BABY : SoundEvents.ENTITY_TURTLE_DEATH;
    }

    protected void playStepSound(BlockPos pos, BlockState state) {
        SoundEvent soundEvent = this.isBaby() ? SoundEvents.ENTITY_TURTLE_SHAMBLE_BABY : SoundEvents.ENTITY_TURTLE_SHAMBLE;
        this.playSound(soundEvent, 0.15F, 1.0F);
    }

    public boolean canEat() {
        return super.canEat() && !this.hasEgg();
    }

    protected float calculateNextStepSoundDistance() {
        return this.distanceTraveled + 0.15F;
    }

    public float getScaleFactor() {
        return this.isBaby() ? 0.3F : 1.0F;
    }

    protected EntityNavigation createNavigation(World world) {
        return new TethysTurtleEntity.TurtleSwimNavigation(this, world);
    }

    public boolean isBreedingItem(ItemStack stack) {
        return stack.getItem() == Blocks.SEAGRASS.asItem();
    }

    public float getPathfindingFavor(BlockPos pos, WorldView world) {
        if (world.getBlockState(pos).getFluidState().isIn(FluidTags.WATER)) {
            return 10.0F;
        } else {
            return this.getWorld().getFluidState(this.getBlockPos()).isIn(FluidTags.WATER) ? Float.NEGATIVE_INFINITY : 0.0F;
        }
    }

    public void tickMovement() {
        super.tickMovement();
        if (this.isAlive() && this.isDiggingSand() && this.sandDiggingCounter >= 1 && this.sandDiggingCounter % 5 == 0) {
            BlockPos blockPos = this.getBlockPos();
            if (TethysTurtleEggBlock.isSand(this.getWorld(), blockPos)) {
                this.getWorld().syncWorldEvent(2001, blockPos, Block.getRawIdFromState(Blocks.SAND.getDefaultState()));
            }
        }
    }

    protected void onGrowUp() {
        super.onGrowUp();
        if (!this.isBaby() && this.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
            this.dropItem(Items.TURTLE_SCUTE, 1);
        }
    }

    public void travel(Vec3d movementInput) {
        if (this.canMoveVoluntarily() && this.isTouchingWater()) {
            this.updateVelocity(0.1F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.9D));
            if (this.getTarget() == null && (!this.isLandBound() || !this.getHomePos().isWithinDistance(this.getPos(), 20.0D))) {
                this.setVelocity(this.getVelocity().add(0.0D, -0.005D, 0.0D));
            }
        } else {
            super.travel(movementInput);
        }
    }

    @Override
    public boolean canBeLeashedBy(PlayerEntity player) {
        return false;
    }

    @Override
    public void onStruckByLightning(ServerWorld world, LightningEntity lightning) {
        this.damage(this.getDamageSources().lightningBolt(), 3.4f);
    }

    static class TurtleSwimNavigation extends SwimNavigation {
        TurtleSwimNavigation(TethysTurtleEntity owner, World world) {
            super(owner, world);
        }

        @Override
        protected boolean isAtValidPosition() {
            return true;
        }

        @Override
        protected PathNodeNavigator createPathNodeNavigator(int range) {
            // This true/false thing seems to penalize swimming far below sea level.
            this.nodeMaker = new AmphibiousPathNodeMaker(false);
            return new PathNodeNavigator(this.nodeMaker, range);
        }

        @Override
        public boolean isValidPosition(BlockPos pos) {
            if (this.entity instanceof TethysTurtleEntity tethysTurtleEntity) {
                if (tethysTurtleEntity.isActivelyTravelling()) {
                    return this.world.getBlockState(pos).isOf(Blocks.WATER);
                }
            }
            return !this.world.getBlockState(pos.down()).isAir();
        }
    }

    static class TurtleMoveControl extends MoveControl {
        private final TethysTurtleEntity turtle;

        TurtleMoveControl(TethysTurtleEntity turtle) {
            super(turtle);
            this.turtle = turtle;
        }

        private void updateVelocity() {
            if (this.turtle.isTouchingWater()) {
                this.turtle.setVelocity(this.turtle.getVelocity().add(0.0D, 0.005D, 0.0D));
                if (!this.turtle.getHomePos().isWithinDistance(this.turtle.getPos(), 16.0D)) {
                    this.turtle.setMovementSpeed(Math.max(this.turtle.getMovementSpeed() / 2.0F, 0.08F));
                }
                if (this.turtle.isBaby()) {
                    this.turtle.setMovementSpeed(Math.max(this.turtle.getMovementSpeed() / 3.0F, 0.06F));
                }
            } else if (this.turtle.isOnGround()) {
                this.turtle.setMovementSpeed(Math.max(this.turtle.getMovementSpeed() / 2.0F, 0.06F));
            }
        }

        public void tick() {
            this.updateVelocity();
            if (this.state == State.MOVE_TO && !this.turtle.getNavigation().isIdle()) {
                double d = this.targetX - this.turtle.getX();
                double e = this.targetY - this.turtle.getY();
                double f = this.targetZ - this.turtle.getZ();
                double g = MathHelper.sqrt((float) (d * d + e * e + f * f));
                e /= g;
                float h = (float)(MathHelper.atan2(f, d) * 57.2957763671875D) - 90.0F;
                this.turtle.setYaw(this.wrapDegrees(this.turtle.getYaw(), h, 90.0F));
                this.turtle.bodyYaw = this.turtle.getYaw();
                float i = (float)(this.speed * this.turtle.getAttributeValue(EntityAttributes.GENERIC_MOVEMENT_SPEED));
                this.turtle.setMovementSpeed(MathHelper.lerp(0.125F, this.turtle.getMovementSpeed(), i));
                this.turtle.setVelocity(this.turtle.getVelocity().add(0.0D, (double)this.turtle.getMovementSpeed() * e * 0.1D, 0.0D));
            } else {
                this.turtle.setMovementSpeed(0.0F);
            }
        }
    }

    static class WanderInWaterGoal extends MoveToTargetPosGoal {
        private final TethysTurtleEntity turtle;

        private WanderInWaterGoal(TethysTurtleEntity turtle, double speed) {
            super(turtle, turtle.isBaby() ? 2.0D : speed, 24);
            this.turtle = turtle;
            this.lowestY = -1;
        }

        public boolean shouldContinue() {
            return !this.turtle.isTouchingWater() && this.tryingTime <= 1200 && this.isTargetPos(this.turtle.getWorld(), this.targetPos);
        }

        public boolean canStart() {
            if (this.turtle.isBaby() && !this.turtle.isTouchingWater()) {
                return super.canStart();
            } else {
                return !this.turtle.isLandBound() && !this.turtle.isTouchingWater() && !this.turtle.hasEgg() && super.canStart();
            }
        }

        public boolean shouldResetPath() {
            return this.tryingTime % 160 == 0;
        }

        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return world.getBlockState(pos).isOf(Blocks.WATER);
        }
    }

    static class WanderOnLandGoal extends WanderAroundGoal {
        private final TethysTurtleEntity turtle;

        private WanderOnLandGoal(TethysTurtleEntity turtle, double speed, int chance) {
            super(turtle, speed, chance);
            this.turtle = turtle;
        }

        public boolean canStart() {
            return !this.mob.isTouchingWater() && !this.turtle.isLandBound() && !this.turtle.hasEgg() && super.canStart();
        }
    }

    static class LayEggGoal extends MoveToTargetPosGoal {
        private final TethysTurtleEntity turtle;
        LayEggGoal(TethysTurtleEntity turtle, double speed) {
            super(turtle, speed, 16);
            this.turtle = turtle;
        }

        public boolean canStart() {
            return this.turtle.hasEgg() && this.turtle.getHomePos().isWithinDistance(this.turtle.getPos(), 9.0D) && super.canStart();
        }

        public boolean shouldContinue() {
            return super.shouldContinue() && this.turtle.hasEgg() && this.turtle.getHomePos().isWithinDistance(this.turtle.getPos(), 9.0D);
        }

        public void tick() {
            super.tick();
            BlockPos turtlePos = this.turtle.getBlockPos();
            BlockPos belowTurtlePos = turtlePos.down();
            BlockState belowTurtleBlockState = this.turtle.getWorld().getBlockState(belowTurtlePos);
            // Block belowTurtleBlock = belowTurtleBlockState.getBlock();
            if (this.hasReached()) {
                if (belowTurtleBlockState.isIn(BlockTags.SAND)) {
                    // Attempt to stick turtle to bottom of ocean.
                    this.turtle.setVelocity(0.0D, -0.1D, 0.0D);
                    if (this.turtle.sandDiggingCounter < 1) {
                        this.turtle.setDiggingSand(true);
                    } else if (this.turtle.sandDiggingCounter > 100) {
                        World world = this.turtle.getWorld();
                        world.playSound(null, turtlePos, SoundEvents.ENTITY_TURTLE_LAY_EGG, SoundCategory.BLOCKS, 0.3F, 0.9F + world.random.nextFloat() * 0.2F);
                        world.setBlockState(this.targetPos.up(), TethysBlocks.TETHYS_TURTLE_EGG.getDefaultState().with(TethysTurtleEggBlock.EGGS, this.turtle.random.nextInt(4) + 1), 3);
                        this.turtle.setHasEgg(false);
                        this.turtle.setDiggingSand(false);
                        this.turtle.setLoveTicks(600);
                    }
                }
                if (this.turtle.isDiggingSand()) {
                    this.turtle.sandDiggingCounter++;
                }
            }
        }

        // Goes to the vanilla turtle egg block to see if the block below is sand, but no longer checks to make sure it has air above it.
        protected boolean isTargetPos(WorldView world, BlockPos pos) {
            return TurtleEggBlock.isSandBelow(world, pos);
        }
    }

    static class MateGoal extends AnimalMateGoal {
        private final TethysTurtleEntity turtle;

        MateGoal(TethysTurtleEntity turtle, double speed) {
            super(turtle, speed);
            this.turtle = turtle;
        }

        public boolean canStart() {
            return super.canStart() && !this.turtle.hasEgg();
        }

        protected void breed() {
            ServerPlayerEntity serverPlayerEntity = this.animal.getLovingPlayer();
            if (serverPlayerEntity == null) {
                assert this.mate != null;
                if (this.mate.getLovingPlayer() != null) {
                    serverPlayerEntity = this.mate.getLovingPlayer();
                }
            }
            // It said the below could produce a null pointer exception and recommended asserting not null.
            /*
            if (serverPlayerEntity == null && this.mate.getLovingPlayer() != null) {
                serverPlayerEntity = this.mate.getLovingPlayer();
            }
            */

            if (serverPlayerEntity != null) {
                serverPlayerEntity.incrementStat(Stats.ANIMALS_BRED);
                Criteria.BRED_ANIMALS.trigger(serverPlayerEntity, this.animal, this.mate, null);
            }

            this.turtle.setHasEgg(true);
            this.animal.resetLoveTicks();
            assert this.mate != null;
            this.mate.resetLoveTicks();
            Random random = this.animal.getRandom();
            if (this.world.getGameRules().getBoolean(GameRules.DO_MOB_LOOT)) {
                this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.animal.getX(), this.animal.getY(), this.animal.getZ(), random.nextInt(7) + 1));
            }
        }
    }

    static class GoHomeGoal extends Goal {
        private final TethysTurtleEntity turtle;
        private final double speed;
        private boolean noPath;
        private int homeReachingTryTicks;

        GoHomeGoal(TethysTurtleEntity turtle, double speed) {
            this.turtle = turtle;
            this.speed = speed;
        }

        public boolean canStart() {
            if (this.turtle.isBaby()) {
                return false;
            } else if (this.turtle.hasEgg()) {
                return true;
            } else if (this.turtle.getRandom().nextInt(700) != 0) {
                return false;
            } else {
                return !this.turtle.getHomePos().isWithinDistance(this.turtle.getPos(), 64.0D);
            }
        }

        public void start() {
            this.turtle.setLandBound(true);
            this.noPath = false;
            this.homeReachingTryTicks = 0;
        }

        public void stop() {
            this.turtle.setLandBound(false);
        }

        public boolean shouldContinue() {
            return !this.turtle.getHomePos().isWithinDistance(this.turtle.getPos(), 7.0D) && !this.noPath && this.homeReachingTryTicks <= 600;
        }

        public void tick() {
            BlockPos blockPos = this.turtle.getHomePos();
            boolean bl = blockPos.isWithinDistance(this.turtle.getPos(), 16.0D);
            if (bl) {
                ++this.homeReachingTryTicks;
            }

            if (this.turtle.getNavigation().isIdle()) {
                Vec3d current = Vec3d.ofBottomCenter(blockPos);
                Vec3d target = NoPenaltyTargeting.findTo(this.turtle, 16, 3, current, 0.3141592741012573D);
                if (target == null) {
                    target = NoPenaltyTargeting.findTo(this.turtle, 8, 7, current, 1.57D);
                }

                if (target != null && !bl && !this.turtle.getWorld().getBlockState(new BlockPos((int) target.getX(), (int) target.getY(), (int) target.getZ())).isOf(Blocks.WATER)) {
                    target = NoPenaltyTargeting.findTo(this.turtle, 16, 5, current, 1.57D);
                }

                if (target == null) {
                    this.noPath = true;
                    return;
                }

                this.turtle.getNavigation().startMovingTo(target.x, target.y, target.z, this.speed);
            }

        }
    }

    static class TravelGoal extends Goal {
        private final TethysTurtleEntity turtle;
        private final double speed;
        private boolean noPath;

        TravelGoal(TethysTurtleEntity turtle, double speed) {
            this.turtle = turtle;
            this.speed = speed;
        }

        public boolean canStart() {
            return !this.turtle.isLandBound() && !this.turtle.hasEgg() && this.turtle.isTouchingWater();
        }

        public void start() {
            Random random = this.turtle.random;
            int k = random.nextInt(1025) - 512;
            int l = random.nextInt(9) - 4;
            int m = random.nextInt(1025) - 512;
            if (l + this.turtle.getY() > (this.turtle.getWorld().getSeaLevel() - 1)) {
                l = 0;
            }

            BlockPos blockPos = new BlockPos((k + (int) this.turtle.getX()), l + (int) this.turtle.getY(), m + (int) this.turtle.getZ());
            this.turtle.setTravelPos(blockPos);
            this.turtle.setActivelyTravelling(true);
            this.noPath = false;
        }

        public void tick() {
            if (this.turtle.getNavigation().isIdle()) {
                Vec3d turtleBottomCenter = Vec3d.ofBottomCenter(this.turtle.getTravelPos());
                Vec3d route = NoPenaltyTargeting.findTo(this.turtle, 16, 3, turtleBottomCenter, 0.314D);
                if (route == null) {
                    route = NoPenaltyTargeting.findTo(this.turtle, 8, 7, turtleBottomCenter, 1.57D);
                }

                if (route != null) {
                    int i = MathHelper.floor(route.x);
                    int j = MathHelper.floor(route.z);
                    // what is this mystery variable that does not do a thing
                    //boolean k = true;
                    if (!this.turtle.getWorld().isRegionLoaded(i - 34, 0, j - 34, i + 34, 0, j + 34)) {
                        route = null;
                    }
                }

                if (route == null) {
                    this.noPath = true;
                    return;
                }

                this.turtle.getNavigation().startMovingTo(route.x, route.y, route.z, this.speed);
            }

        }

        public boolean shouldContinue() {
            return !this.turtle.getNavigation().isIdle() && !this.noPath && !this.turtle.isLandBound() && !this.turtle.isInLove() && !this.turtle.hasEgg();
        }

        public void stop() {
            this.turtle.setActivelyTravelling(false);
            super.stop();
        }
    }

    static class TurtleEscapeDangerGoal extends EscapeDangerGoal {
        TurtleEscapeDangerGoal(TethysTurtleEntity turtle, double speed) {
            super(turtle, speed);
        }

        public boolean canStart() {
            if (this.mob.getAttacker() == null && !this.mob.isOnFire()) {
                return false;
            } else {
                BlockPos blockPos = this.locateClosestWater(this.mob.getWorld(), this.mob, 7);
                if (blockPos != null) {
                    this.targetX = blockPos.getX();
                    this.targetY = blockPos.getY();
                    this.targetZ = blockPos.getZ();
                    return true;
                } else {
                    return this.findTarget();
                }
            }
        }
    }
}
