package com.artemis.parallel_world.entity;

import com.artemis.parallel_world.entity.ai.control.FlyingCatMoveControl;
import com.artemis.parallel_world.entity.goal.FlyingCatWalkAroundFarGoal;
import com.artemis.parallel_world.entity.goal.FlyingCatWalkAroundGoal;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.control.FlightMoveControl;
import net.minecraft.entity.ai.control.MoveControl;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.ai.pathing.BirdNavigation;
import net.minecraft.entity.ai.pathing.EntityNavigation;
import net.minecraft.entity.ai.pathing.PathNodeType;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.*;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import net.minecraft.entity.ai.goal.TemptGoal;


public class FlyingCatEntity extends TameableEntity {

    private static final Ingredient TAMING_INGREDIENT = Ingredient.ofItems(Items.COOKIE);
//    private static final TrackedData<? super Boolean> NO_GRAVITY;

    public FlyingCatEntity(EntityType<? extends TameableEntity> entityType, World world) {
        super(entityType, world);
        this.moveControl = new FlyingCatMoveControl(this, 5);
        this.setPathfindingPenalty(PathNodeType.DANGER_FIRE, 16.0F);
        this.setPathfindingPenalty(PathNodeType.DAMAGE_FIRE, -1.0F);

    }

    public static DefaultAttributeContainer.Builder createFlyingCatAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MAX_HEALTH, 10.0D).add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 0.3D).add(EntityAttributes.GENERIC_FLYING_SPEED, 0.6D).add(EntityAttributes.GENERIC_ATTACK_DAMAGE, 3.0D);
    }

    public boolean canFly() {
        return true;
    }

    protected EntityNavigation createNavigation(World world) {
        BirdNavigation birdNavigation = new BirdNavigation(this, world);
        birdNavigation.setCanPathThroughDoors(false);
        birdNavigation.setCanSwim(true);
        birdNavigation.setCanEnterOpenDoors(false);
        return birdNavigation;
    }

    public void travel(Vec3d movementInput) {
        if (this.isTouchingWater()) {
            this.updateVelocity(0.02F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.8D));
        } else if (this.isInLava()) {
            this.updateVelocity(0.02F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply(0.5D));
        } else {
            float f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.91F;
            }

            float g = 0.163F / (f * f * f);
            f = 0.91F;
            if (this.onGround) {
                f = this.world.getBlockState(new BlockPos(this.getX(), this.getY() - 1.0D, this.getZ())).getBlock().getSlipperiness() * 0.91F;
            }

            this.updateVelocity(this.onGround ? 0.1F * g : 0.02F, movementInput);
            this.move(MovementType.SELF, this.getVelocity());
            this.setVelocity(this.getVelocity().multiply((double)f));
        }

        this.method_29242(this, false);
    }

    public boolean isClimbing() {
        return false;
    }

    protected void initGoals() {
        this.goalSelector.add(1, new SwimGoal(this));
        this.goalSelector.add(1, new SitGoal(this));
        this.goalSelector.add(3, new TemptGoal(this, 1.25D, Ingredient.ofItems(Items.COOKIE), false));
        this.goalSelector.add(4, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        this.goalSelector.add(5, new PounceAtTargetGoal(this, 0.3F));
        this.goalSelector.add(6, new AttackGoal(this));
        this.goalSelector.add(7, new AnimalMateGoal(this, 0.8D));
        this.goalSelector.add(8, new FlyingCatWalkAroundFarGoal(this, 0.8D, 1.0E-5F));
        this.goalSelector.add(9, new WanderAroundFarGoal(this, 0.8D, 1.0E-5F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, PlayerEntity.class, 10.0F));
    }

    public void tick() {
        super.tick();
//        if (this.temptGoal != null && this.temptGoal.isActive() && !this.isTamed() && this.age % 100 == 0) {
//            this.playSound(SoundEvents.ENTITY_CAT_BEG_FOR_FOOD, 1.0F, 1.0F);
//        }
    }

@Override
    public MoveControl getMoveControl() {
        return new FlyingCatMoveControl(this, 5);
    }

    public void mobTick() {
        if (!this.isOnGround()) {
            this.setPose(EntityPose.FALL_FLYING);
        }
        else if (this.getMoveControl().isMoving()) {
            double d = this.getMoveControl().getSpeed();
            if (d == 0.6D) {
                this.setPose(EntityPose.CROUCHING);
                this.setSprinting(false);
            } else if (d == 1.33D) {
                this.setPose(EntityPose.STANDING);
                this.setSprinting(true);
            } else {
                this.setPose(EntityPose.STANDING);
                this.setSprinting(false);
            }
        } else {
            this.setPose(EntityPose.STANDING);
            this.setSprinting(false);
        }
    }


    public boolean isBreedingItem(ItemStack stack) {
        return TAMING_INGREDIENT.test(stack);
    }

    protected void eat(PlayerEntity player, ItemStack stack) {
        if (this.isBreedingItem(stack)) {
            this.playSound(SoundEvents.ENTITY_CAT_EAT, 1.0F, 1.0F);
        }
        super.eat(player, stack);
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

    public boolean handleFallDamage(float fallDistance, float damageMultiplier) {
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

    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        ActionResult actionResult;

        if (this.world.isClient) {
            if (this.isTamed() && this.isOwner(player)) {
                return ActionResult.SUCCESS;
            } else {
                return !this.isBreedingItem(itemStack) || this.getHealth() >= this.getMaxHealth() && this.isTamed() ? ActionResult.PASS : ActionResult.SUCCESS;
            }
        } else {
            if (this.isTamed()) {
                if (this.isOwner(player)) {
                    if (item.isFood() && this.isBreedingItem(itemStack) && this.getHealth() < this.getMaxHealth()) {
                        this.eat(player, itemStack);
                        this.heal((float)item.getFoodComponent().getHunger());
                        return ActionResult.CONSUME;
                    }
                    actionResult = super.interactMob(player, hand);
                    if (!actionResult.isAccepted() || this.isBaby()) {
                        this.setSitting(!this.isSitting());
                    }
                    return actionResult;
                }
            } else if (this.isBreedingItem(itemStack)) {
                this.eat(player, itemStack);
                if (this.random.nextInt(3) == 0) {
                    this.setOwner(player);
                    if (this.isOnGround()) {
                        this.setSitting(true);
                    }
                    this.world.sendEntityStatus(this, (byte)7);
                } else {
                    this.world.sendEntityStatus(this, (byte)6);
                }
                this.setPersistent();
                return ActionResult.CONSUME;
            }
            actionResult = super.interactMob(player, hand);
            if (actionResult.isAccepted()) {
                this.setPersistent();
            }
            return actionResult;
        }
    }


    public boolean canBreedWith(AnimalEntity other) {
        if (!this.isTamed()) {
            return false;
        } else if (!(other instanceof FlyingCatEntity)) {
            return false;
        } else {
            FlyingCatEntity flyingCatEntity = (FlyingCatEntity) other;
            return flyingCatEntity.isTamed() && super.canBreedWith(other);
        }
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

    public boolean canImmediatelyDespawn(double distanceSquared) {
        return !this.isTamed() && this.age > 2400;
    }

    protected void onTamedChanged() {
        //I mean nothing?
    }
}
