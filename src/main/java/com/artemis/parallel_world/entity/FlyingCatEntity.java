package com.artemis.parallel_world.entity;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.MobNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.ServerWorldAccess;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import net.minecraft.world.WorldView;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.ai.goal.TemptGoal;

import java.util.Random;


public class FlyingCatEntity extends TameableEntity {

    private static final Ingredient TAMING_INGREDIENT = Ingredient.ofItems(Items.COOKIE);
    private int locomotionToggle;

    public FlyingCatEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlightMoveControl(this, 5, false);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 16.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0F);
    }

    public static DefaultAttributeContainer.Builder createFlyingCatAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanPathThroughDoors(true);
        birdNavigation.setCanEnterOpenDoors(true);
        return birdNavigation;
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.COOKIE), false));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        // Add this later modelled on cat.
        //this.goalSelector.add(5, new FleeEntityGoal<>());
        this.goalSelector.add(6, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(7, new AttackGoal(this));
        this.goalSelector.add(8, new AnimalMateGoal(this, 0.8D));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.8D, 1.0E-5F));
        this.goalSelector.add(10, new FlyOntoTreeGoal(this, 1.0D));
        this.goalSelector.add(11, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
    }

    public void tick() {
        super.tick();
        // Why is my math not working.
//        if (this.locomotionToggle < 900) {
//            int increment = random.nextInt(10);
//            locomotionToggle += increment;
        if (this.locomotionToggle <600) {
            locomotionToggle++;
        } else if (this.flying(true)) {
            if(this.isOnGround()) {
                setFlying(false);
                MoveControl walkingControl = new MoveControl(this);
                this.moveControl = walkingControl;
                MobNavigation walkingNav = new MobNavigation(this, world);
                walkingNav.setCanPathThroughDoors(true);
                walkingNav.setCanPathThroughDoors(true);
                this.navigation = walkingNav;
                this.locomotionToggle = 0;
            }
        } else if (this.flying(false)) {
            setFlying(true);
            FlightMoveControl flyingControl = new FlightMoveControl(this,5, false);
            this.moveControl = flyingControl;
            BirdNavigation flyingNav = new BirdNavigation(this, world);
            flyingNav.setCanEnterOpenDoors(true);
            flyingNav.setCanPathThroughDoors(true);
            flyingNav.setCanSwim(true);
            this.navigation = flyingNav;
            this.locomotionToggle = 0;
        }
    }

     public boolean canFly() {
        return true;
    }

    public void setFlying(boolean status) {
        this.flying(status);
    }

    private boolean flying(boolean status) {
        return status;
    }

    public boolean isBreedingItem(ItemStack stack) {
        return TAMING_INGREDIENT.test(stack);
    }

    protected void eat(PlayerEntity player, Hand hand, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
        }
        super.eat(player, hand, stack);
    }

    private float getAttackDamage() {
        return (float)this.getAttributeValue(EntityAttributes.GENERIC_ATTACK_DAMAGE);
    }

    public boolean tryAttack(Entity target) {
        return target.damage(DamageSource.mob(this), this.getAttackDamage());
    }

    @Nullable
    protected SoundEvent getAmbientSound() {
        if (this.isTamed()) {
            if (this.isInLove()) {
                return SoundEvents.ENTITY_CAT_PURR;
            } else {
                return this.random.nextInt(4) == 0 ? SoundEvents.ENTITY_CAT_PURREOW : SoundEvents.ENTITY_CAT_AMBIENT;
            }
        } else {
            return SoundEvents.ENTITY_CAT_STRAY_AMBIENT;
        }
    }

    public boolean handleFallDamage(float fallDistance, float damageMultiplier, DamageSource damageSource) {
        return false;
    }

    public int getMinAmbientSoundDelay() {
        return 120;
    }

    public void hiss() {
        this.playSound(SoundEvents.ENTITY_CAT_HISS, this.getSoundVolume(), this.getSoundPitch());
    }

    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_CAT_HURT;
    }

    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_CAT_DEATH;
    }

    public FlyingCatEntity createChild(ServerWorld serverWorld, PassiveEntity passiveEntity) {
        FlyingCatEntity flyingCatEntity = TethysEntities.FLYING_CAT.create(serverWorld);
        if (this.isTamed()) {
            flyingCatEntity.setOwnerUuid(this.getOwnerUuid());
            flyingCatEntity.setTamed(true);
        }
        return flyingCatEntity;
    }

    protected float getActiveEyeHeight(EntityPose pose, EntityDimensions dimensions) {
        return dimensions.height * 0.5F;
    }

    public static boolean canSpawn(EntityType<? extends FlyingCatEntity> type, WorldAccess world, SpawnReason spawnReason, BlockPos pos, Random random) {
        if (pos.getY() < 35) {
            return false;
        }
        BlockState blockState = world.getBlockState(pos.down());
        return blockState.isOf(Blocks.GRASS_BLOCK) || blockState.isIn(BlockTags.LEAVES);
    }

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isTamed() && this.age > 2400;
    }


}
