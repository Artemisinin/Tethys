package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.FlyingCatEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.entity.Entity;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class FlyingCatEntityModel<T extends FlyingCatEntity> extends AnimalModel<T> {

//	private float sleepAnimation;
//	private float tailCurlAnimation;
//	private float headDownAnimation;

	protected  int animationState = 1;

	private final ModelPart torso;
	private final ModelPart head = new ModelPart(this);
	private final ModelPart upperTail;
	private final ModelPart lowerTail;
	private final ModelPart leftBackLeg;
	private final ModelPart rightBackLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightWingProx;
	private final ModelPart leftWingProx;
	private final ModelPart rightWingDistal1;
	private final ModelPart rightWingDistal2;
	private final ModelPart rightWingDistal3;
	private final ModelPart rightWingDistal4;
	private final ModelPart leftWingDistal1;
	private final ModelPart leftWingDistal2;
	private final ModelPart leftWingDistal3;
	private final ModelPart leftWingDistal4;

	private final float proxWingPitchRest = 2.2F;
	private final float proxWingRollRest = -0.15F;
	private final float proxWingYawRest = -0.20F;
	private final float distalWingRollRest = 0.2F;
	private final float distalWingYawRest = 0.2F;

	private final float proxWingPitchFlight = 1.5F;
	private final float proxWingYawFlight = -0.5F;
	private final float proxWingRollFlight = -0.5F;


	public FlyingCatEntityModel(float scale) {
		super(true, 10.0F, 4.0F);

		this.head.addCuboid("main", -2.5F, -2.0F, -3.0F, 5, 4, 5, 0, 0, 0);
		this.head.addCuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, 0, 0, 24);
		this.head.addCuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, 0, 0, 10);
		this.head.addCuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, 0, 6, 10);
		head.setPivot(0.0F, 15.0F, -9.0F);
		this.torso = new ModelPart(this, 20, 0);
		this.torso.addCuboid(-2.0F,3.0F, -8.0F,4.0F, 16.0F,6.0F, 0.0F);
		this.torso.pitch = 1.57F;
		torso.setPivot(0.0F, 12.0F, -10.0F);
		this.upperTail = new ModelPart(this, 0, 15);
		this.upperTail.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, scale);
		this.upperTail.pitch = 0.9F;
		this.upperTail.setPivot(0.0F, 15.0F, 8.0F);
		this.lowerTail = new ModelPart(this, 4, 15);
		this.lowerTail.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, scale);
		this.lowerTail.setPivot(0.0F, 20.0F, 14.0F);
		this.leftBackLeg = new ModelPart(this, 8, 13);
		this.leftBackLeg.addCuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, scale);
		this.leftBackLeg.setPivot(1.1F, 18.0F, 5.0F);
		this.rightBackLeg = new ModelPart(this, 8, 13);
		this.rightBackLeg.addCuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, scale);
		this.rightBackLeg.setPivot(-1.1F, 18.0F, 5.0F);
		this.leftFrontLeg = new ModelPart(this, 40, 0);
		this.leftFrontLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, scale);
		this.leftFrontLeg.setPivot(1.2F, 14.1F, -5.0F);
		this.rightFrontLeg = new ModelPart(this, 40, 0);
		this.rightFrontLeg.addCuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, scale);
		this.rightFrontLeg.setPivot(-1.2F, 14.1F, -5.0F);

		// Right Wing
		this.rightWingProx = new ModelPart(this);
		this.rightWingProx.addCuboid("rightWingProx1", -0.5F,0.0F,0.0F, 1, 11, 1, -0.1F, 41, 23);
		this.rightWingProx.addCuboid("rightWingProx2", -1.0F, 3.0F, -1.0F, 2, 3, 2, 0.0F, 55, 15);
		this.rightWingProx.addCuboid("rightWingProx3", -1.0F, 5.0F, -2.0F, 2, 6,3, 0.1F, 41, 23);
		this.rightWingProx.setPivot(-1.5F,13.5F,-5.0F);

		this.rightWingDistal1 = new ModelPart(this, 29, 22);
		this.rightWingProx.addChild(rightWingDistal1);
		rightWingDistal1.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		rightWingDistal1.setPivot(0.0F, 10.0F, 0.0F);

		this.rightWingDistal2 = new ModelPart(this, 30, 22);
		this.rightWingProx.addChild(rightWingDistal2);
		rightWingDistal2.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		rightWingDistal2.setPivot(0.0F, 9.5F, 0.0F);

		this.rightWingDistal3 = new ModelPart(this, 30, 22);
		this.rightWingProx.addChild(rightWingDistal3);
		rightWingDistal3.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		rightWingDistal3.setPivot(0.0F, 9.0F, 0.0F);

		this.rightWingDistal4 = new ModelPart(this, 30, 22);
		this.rightWingProx.addChild(rightWingDistal4);
		rightWingDistal4.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		rightWingDistal4.setPivot(0.0F, 8.5F, 0.0F);

		// Left wing.
		this.leftWingProx = new ModelPart(this);
		this.leftWingProx.addCuboid("leftWingProx1", -0.5F,0.0F,0.0F, 1, 11, 1, -0.1F, 41, 23);
		this.leftWingProx.addCuboid("leftWingProx2", -1.0F, 3.0F, -1.0F, 2, 3, 2, 0.0F, 55, 15);
		this.leftWingProx.addCuboid("leftWingProx3", -1.0F, 5.0F, -2.0F, 2, 6,3, 0.1F, 41, 23);
		this.leftWingProx.setPivot(1.5F,13.5F,-5.0F);

		this.leftWingDistal1 = new ModelPart(this, 29, 22);
		this.leftWingProx.addChild(leftWingDistal1);
		leftWingDistal1.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		leftWingDistal1.setPivot(0.0F, 10.0F, 0.0F);

		this.leftWingDistal2 = new ModelPart(this, 30, 22);
		this.leftWingProx.addChild(leftWingDistal2);
		leftWingDistal2.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		leftWingDistal2.setPivot(0.0F, 9.5F, 0.0F);

		this.leftWingDistal3 = new ModelPart(this, 30, 22);
		this.leftWingProx.addChild(leftWingDistal3);
		leftWingDistal3.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		leftWingDistal3.setPivot(0.0F, 9.0F, 0.0F);

		this.leftWingDistal4 = new ModelPart(this, 30, 22);
		this.leftWingProx.addChild(leftWingDistal4);
		leftWingDistal4.addCuboid(-0.5F, 0.0F, 0.0F, 1, 9, 1);
		leftWingDistal4.setPivot(0.0F, 8.5F, 0.0F);

		textureWidth = 64;
		textureHeight = 32;
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.torso, this.leftBackLeg, this.rightBackLeg, this.leftFrontLeg, this.rightFrontLeg, this.upperTail, this.lowerTail, this.rightWingProx, this.leftWingProx);
	}

	public void animateModel(T flyingCatEntity, float limbAngle, float limbDistance, float tickDelta) {
//		this.sleepAnimation = flyingCatEntity.getSleepAnimation(tickDelta);
//		this.tailCurlAnimation = flyingCatEntity.getTailCurlAnimation(tickDelta);
//		this.headDownAnimation = flyingCatEntity.getHeadDownAnimation(tickDelta);
//
//		if (this.sleepAnimation <= 0.0F) {
//			this.head.pitch = 0.0F;
//			this.head.roll = 0.0F;
//			this.leftFrontLeg.pitch = 0.0F;
//			this.leftFrontLeg.roll = 0.0F;
//			this.rightFrontLeg.pitch = 0.0F;
//			this.rightFrontLeg.roll = 0.0F;
//			this.rightFrontLeg.pivotX = -1.2F;
//			this.leftBackLeg.pitch = 0.0F;
//			this.rightBackLeg.pitch = 0.0F;
//			this.rightBackLeg.roll = 0.0F;
//			this.rightBackLeg.pivotX = -1.1F;
//			this.rightBackLeg.pivotY = 18.0F;
//		}
		super.animateModel(flyingCatEntity, limbAngle, limbDistance, tickDelta);
		this.torso.pivotY = 12.0F;
		this.torso.pivotZ = -10.0F;
		this.head.pivotY = 15.0F;
		this.head.pivotZ = -9.0F;
		this.upperTail.pivotY = 15.0F;
		this.upperTail.pivotZ = 8.0F;
		this.lowerTail.pivotY = 20.0F;
		this.lowerTail.pivotZ = 14.0F;
		this.leftFrontLeg.pivotY = 14.1F;
		this.leftFrontLeg.pivotZ = -5.0F;
		this.rightFrontLeg.pivotY = 14.1F;
		this.rightFrontLeg.pivotZ = -5.0F;
		this.leftBackLeg.pivotY = 18.0F;
		this.leftBackLeg.pivotZ = 5.0F;
		this.rightBackLeg.pivotY = 18.0F;
		this.rightBackLeg.pivotZ = 5.0F;
		this.rightFrontLeg.pitch = 0.0F;
		this.leftFrontLeg.pitch = 0.0F;
		this.rightBackLeg.pitch = 0.0F;
		this.leftBackLeg.pitch = 0.0F;
		this.upperTail.pitch = 0.9F;

		this.rightWingProx.pitch = proxWingPitchRest;
		this.leftWingProx.pitch = proxWingPitchRest;
		this.rightWingProx.yaw = proxWingYawRest;
		this.leftWingProx.yaw = -proxWingYawRest;


//		if (flyingCatEntity.isInSneakingPose()) {
//			this.animationState = 0;
//			this.torso.pivotY += 1.0F;
//			this.head.pivotY += 2.0F;
//			this.upperTail.pivotY += 1.0F;
//			this.lowerTail.pivotY += -4.0F;
//			this.lowerTail.pivotZ += 2.0F;
//			this.upperTail.pitch = 1.57F;
//			this.lowerTail.pitch = 1.57F;
//			}
//		else if (flyingCatEntity.isSprinting()) {
//			this.animationState = 2;
//			this.lowerTail.pivotY = this.upperTail.pivotY;
//			this.lowerTail.pivotZ += 2.0F;
//			this.upperTail.pitch = 1.57F;
//			this.lowerTail.pitch = 1.57F;
//			}
		 if (flyingCatEntity.isInSittingPose()) {
			this.animationState = 3;
			// Torso
			this.torso.pitch = 0.8F;
			this.torso.pivotY += -4.0F;
			this.torso.pivotZ += 5.0F;
			// Head
			this.head.pivotY += -3.3F;
			this.head.pivotZ += 1.0F;
			// Front legs
			this.leftFrontLeg.pitch = -0.157F;
			this.leftFrontLeg.pivotY = 16.1F;
			this.leftFrontLeg.pivotZ = -7.0F;
			this.rightFrontLeg.pitch = -0.157F;
			this.rightFrontLeg.pivotY = 16.1F;
			this.rightFrontLeg.pivotZ = -7.0F;
			// Back legs
			this.leftBackLeg.pitch = -1.57F;
			this.leftBackLeg.pivotY = 21.0F;
			this.leftBackLeg.pivotZ = 1.0F;
			this.rightBackLeg.pitch = -1.57F;
			this.rightBackLeg.pivotY = 21.0F;
			this.rightBackLeg.pivotZ = 1.0F;
			// Tail
			this.upperTail.pivotY += 8.0F;
			this.upperTail.pivotZ += -2.0F;
			this.lowerTail.pivotY += 2.0F;
			this.lowerTail.pivotZ += -0.8F;
			this.upperTail.pitch = 1.7F;
			this.lowerTail.pitch = 2.7F;
			}
		else if (!flyingCatEntity.isOnGround()){
			this.animationState = 4;
			this.rightFrontLeg.pitch += 0.2F;
			this.leftFrontLeg.pitch += 0.2F;
			this.rightBackLeg.pitch += 0.2F;
			this.leftBackLeg.pitch += 0.2F;
			this.rightWingProx.pitch = proxWingPitchFlight;
			this.leftWingProx.pitch = proxWingPitchFlight;
			this.rightWingProx.yaw = -proxWingYawFlight;
			this.leftWingProx.yaw = proxWingYawFlight;
			}
		else {
			this.animationState = 1;
		}
	}

	public void setAngles(T flyingCatEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.0175F;
		this.head.yaw = headYaw * 0.0175F;

		if (this.animationState != 3) {
			// If not sneaking set torso pitch.
			this.torso.pitch = 1.57F;
			if (this.animationState == 4) {
				this.rightWingProx.roll += proxWingRollFlight + 0.15 * MathHelper.cos(animationProgress * 0.3F);
				this.leftWingProx.roll += -proxWingRollFlight + -0.15 * MathHelper.cos(animationProgress * 0.3F);
				// Set tail movement with speed later?
				this.lowerTail.pitch = 1.73F + 0.31415927F * MathHelper.cos(limbAngle) * limbDistance;
			// If sneaking, walking, standing...
			} else {
				this.rightWingProx.pitch = this.leftWingProx.pitch = proxWingPitchRest;
				this.rightWingProx.roll = proxWingRollRest;
				this.rightWingProx.yaw = proxWingYawRest;
				this.leftWingProx.roll = -proxWingRollRest;
				this.leftWingProx.yaw = -proxWingYawRest;
				this.rightWingDistal1.pitch = this.leftWingDistal1.pitch = -1.1F;
				this.rightWingDistal2.pitch = this.leftWingDistal2.pitch = -1.25F;
				this.rightWingDistal3.pitch = this.leftWingDistal3.pitch = -1.40F;
				this.rightWingDistal4.pitch = this.leftWingDistal4.pitch = -1.55F;
				this.leftBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
				this.rightBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
				this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
				this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
				if (this.animationState == 1) {
					this.lowerTail.pitch = 1.73F + 0.785F * MathHelper.cos(limbAngle) * limbDistance;
				} else {
					this.lowerTail.pitch = 1.73F + 0.471F * MathHelper.cos(limbAngle) * limbDistance;
				}
			}
		}
	}
}