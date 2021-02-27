package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.FlyingCatEntity;
import com.google.common.collect.ImmutableList;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.Dilation;
import net.minecraft.util.math.MathHelper;

@Environment(EnvType.CLIENT)
public class FlyingCatEntityModel<T extends FlyingCatEntity> extends AnimalModel<T> {

	protected final ModelPart leftHindLeg;
	protected final ModelPart rightHindLeg;
	protected final ModelPart leftFrontLeg;
	protected final ModelPart rightFrontLeg;
	protected final ModelPart upperTail;
	protected final ModelPart lowerTail;
	protected final ModelPart head;
	protected final ModelPart body;
	protected int animationState = 1;

	public FlyingCatEntityModel(ModelPart root) {
		super(true, 10.0F, 4.0F);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.upperTail = root.getChild("tail1");
		this.lowerTail = root.getChild("tail2");
		this.leftHindLeg = root.getChild("left_hind_leg");
		this.rightHindLeg = root.getChild("right_hind_leg");
		this.leftFrontLeg = root.getChild("left_front_leg");
		this.rightFrontLeg = root.getChild("right_front_leg");
	}

	public static ModelData getModelData(net.minecraft.client.model.Dilation dilation) {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		modelPartData.addChild("head", ModelPartBuilder.create().cuboid("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, dilation).cuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, dilation, 0, 24).cuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 0, 10).cuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 6, 10), ModelTransform.pivot(0.0F, 15.0F, -9.0F));
		modelPartData.addChild("body", ModelPartBuilder.create().uv(20, 0).cuboid(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, dilation), ModelTransform.of(0.0F, 12.0F, -10.0F, 1.5707964F, 0.0F, 0.0F));
		modelPartData.addChild("tail1", ModelPartBuilder.create().uv(0, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation), ModelTransform.of(0.0F, 15.0F, 8.0F, 0.9F, 0.0F, 0.0F));
		modelPartData.addChild("tail2", ModelPartBuilder.create().uv(4, 15).cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation), ModelTransform.pivot(0.0F, 20.0F, 14.0F));
		ModelPartBuilder modelPartBuilder = ModelPartBuilder.create().uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, dilation);
		modelPartData.addChild("left_hind_leg", modelPartBuilder, ModelTransform.pivot(1.1F, 18.0F, 5.0F));
		modelPartData.addChild("right_hind_leg", modelPartBuilder, ModelTransform.pivot(-1.1F, 18.0F, 5.0F));
		ModelPartBuilder modelPartBuilder2 = ModelPartBuilder.create().uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, dilation);
		modelPartData.addChild("left_front_leg", modelPartBuilder2, ModelTransform.pivot(1.2F, 14.1F, -5.0F));
		modelPartData.addChild("right_front_leg", modelPartBuilder2, ModelTransform.pivot(-1.2F, 14.1F, -5.0F));
		return modelData;
	}

	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.leftHindLeg, this.rightHindLeg, this.leftFrontLeg, this.rightFrontLeg, this.upperTail, this.lowerTail);
	}

	public void setAngles(T entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
		this.head.pitch = headPitch * 0.017453292F;
		this.head.yaw = headYaw * 0.017453292F;
		if (this.animationState != 3) {
			this.body.pitch = 1.5707964F;
			if (this.animationState == 2) {
				this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
				this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 0.3F) * limbDistance;
				this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F + 0.3F) * limbDistance;
				this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
				this.lowerTail.pitch = 1.7278761F + 0.31415927F * MathHelper.cos(limbAngle) * limbDistance;
			} else {
				this.leftHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
				this.rightHindLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
				this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.1415927F) * limbDistance;
				this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
				if (this.animationState == 1) {
					this.lowerTail.pitch = 1.7278761F + 0.7853982F * MathHelper.cos(limbAngle) * limbDistance;
				} else {
					this.lowerTail.pitch = 1.7278761F + 0.47123894F * MathHelper.cos(limbAngle) * limbDistance;
				}
			}
		}

	}

	public void animateModel(T entity, float limbAngle, float limbDistance, float tickDelta) {
		this.body.pivotY = 12.0F;
		this.body.pivotZ = -10.0F;
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
		this.leftHindLeg.pivotY = 18.0F;
		this.leftHindLeg.pivotZ = 5.0F;
		this.rightHindLeg.pivotY = 18.0F;
		this.rightHindLeg.pivotZ = 5.0F;
		this.upperTail.pitch = 0.9F;
		ModelPart var10000;
		if (entity.isInSneakingPose()) {
			++this.body.pivotY;
			var10000 = this.head;
			var10000.pivotY += 2.0F;
			++this.upperTail.pivotY;
			var10000 = this.lowerTail;
			var10000.pivotY += -4.0F;
			var10000 = this.lowerTail;
			var10000.pivotZ += 2.0F;
			this.upperTail.pitch = 1.5707964F;
			this.lowerTail.pitch = 1.5707964F;
			this.animationState = 0;
		} else if (entity.isSprinting()) {
			this.lowerTail.pivotY = this.upperTail.pivotY;
			var10000 = this.lowerTail;
			var10000.pivotZ += 2.0F;
			this.upperTail.pitch = 1.5707964F;
			this.lowerTail.pitch = 1.5707964F;
			this.animationState = 2;
		} else {
			this.animationState = 1;
		}

	}


//
//	private final ModelPart torso;
//	private final ModelPart head;
//	private final ModelPart upperTail;
//	private final ModelPart lowerTail;
//	private final ModelPart leftBackLeg;
//	private final ModelPart rightBackLeg;
//	private final ModelPart leftFrontLeg;
//	private final ModelPart rightFrontLeg;
//	private final ModelPart rightWingProx;
//	private final ModelPart rightWingDistal1;
//	private final ModelPart rightWingDistal2;
//	private final ModelPart rightWingDistal3;
//	private final ModelPart rightWingDistal4;
//	private final ModelPart leftWingProx;
//	private final ModelPart leftWingDistal1;
//	private final ModelPart leftWingDistal2;
//	private final ModelPart leftWingDistal3;
//	private final ModelPart leftWingDistal4;
//	private final float proxWingPitchRest = 2.2F;
//	private final float proxWingRollRest = -0.15F;
//	private final float proxWingYawRest = -0.20F;
//	private final float distalWingYawRest = 0.2F;
//
//	private final float proxWingPitchFlight = 1.5F;
//	//0 is flat to the sides, -0.4 is folded back, 0.5F crossed over the back (duh), -1.0 a smidge back, -2.0 tilted forward.
//	private final float proxWingYawFlight = -1.5F;
//	private final float proxWingRollFlight = 0.0F;
//	private final float distalWingYawFlight = -1.5F;
//
//	protected  int animationState = 1;
//
//	public FlyingCatEntityModel(ModelPart root) {
//		super(true, 10.0F, 4.0F);
//		this.head = root.getChild("head");
//		this.torso = root.getChild("body");
//		this.upperTail = root.getChild("tail1");
//		this.lowerTail = root.getChild("tail2");
//		this.leftBackLeg = root.getChild("left_hind_leg");
//		this.rightBackLeg = root.getChild("right_hind_leg");
//		this.leftFrontLeg = root.getChild("left_front_leg");
//		this.rightFrontLeg = root.getChild("right_front_leg");
//		this.rightWingProx = root.getChild("right_wing_prox");
//		this.rightWingDistal1 = root.getChild("right_wing_distal1");
//		this.rightWingDistal2 = root.getChild("right_wing_distal2");
//		this.rightWingDistal3 = root.getChild("right_wing_distal3");
//		this.rightWingDistal4 = root.getChild("right_wing_distal4");
//		this.leftWingProx = root.getChild("left_wing_prox");
//		this.leftWingDistal1 = root.getChild("left_wing_distal1");
//		this.leftWingDistal2 = root.getChild("left_wing_distal2");
//		this.leftWingDistal3 = root.getChild("left_wing_distal3");
//		this.leftWingDistal4 = root.getChild("left_wing_distal4");
//	}
//
//	public static ModelData getModelData(Dilation dilation) {
//		ModelData modelData = new ModelData();
//		ModelPartData modelPartData = modelData.getRoot();
//		modelPartData.addChild("head", ModelPartBuilder.create().
//				cuboid("main", -2.5F, -2.0F, -3.0F, 5.0F, 4.0F, 5.0F, dilation).
//				cuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, dilation, 0, 24).
//				cuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 0, 10).
//				cuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, dilation, 6, 10),
//				ModelTransform.pivot(0.0F, 15.0F, -9.0F));
//		modelPartData.addChild("torso", ModelPartBuilder.create().
//				uv(20, 0).
//				cuboid(-2.0F, 3.0F, -8.0F, 4.0F, 16.0F, 6.0F, dilation),
//				// 1.57 rad = 1/2*pi; a quarter of a circle, 90 degrees
//				ModelTransform.of(0.0F, 12.0F, -10.0F, 1.57F, 0.0F, 0.0F));
//		modelPartData.addChild("upperTail", ModelPartBuilder.create().
//				uv(0, 15).
//				cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation),
//				ModelTransform.of(0.0F, 15.0F, 8.0F, 0.9F, 0.0F, 0.0F));
//		modelPartData.addChild("lowerTail", ModelPartBuilder.create().
//				uv(4, 15).
//				cuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, dilation),
//				ModelTransform.pivot(0.0F, 20.0F, 14.0F));
//		ModelPartBuilder hind_leg = ModelPartBuilder.create().
//				uv(8, 13).cuboid(-1.0F, 0.0F, 1.0F, 2.0F, 6.0F, 2.0F, dilation);
//		modelPartData.addChild("left_hind_leg", hind_leg, ModelTransform.pivot(1.1F, 18.0F, 5.0F));
//		modelPartData.addChild("right_hind_leg", hind_leg, ModelTransform.pivot(-1.1F, 18.0F, 5.0F));
//		ModelPartBuilder front_leg = ModelPartBuilder.create().
//				uv(40, 0).cuboid(-1.0F, 0.0F, 0.0F, 2.0F, 10.0F, 2.0F, dilation);
//		modelPartData.addChild("left_front_leg", front_leg, ModelTransform.pivot(1.2F, 14.1F, -5.0F));
//		modelPartData.addChild("right_front_leg", front_leg, ModelTransform.pivot(-1.2F, 14.1F, -5.0F));
//
//		// Proximal wings
//		ModelPartBuilder prox_wing = ModelPartBuilder.create().
//				uv(41,23).
//				cuboid("main", -0.5F, 0.0F, 0.0F, 1, 11, 1, dilation).
//				cuboid("mid", -0.5F, 0.0F, -0.5F, 2, 3, 2, dilation).
//				cuboid("end", -0.5F, -3.0F, -0.5F, 3, 6, 2, dilation);
//		modelPartData.addChild("right_wing_prox", prox_wing, ModelTransform.pivot(-1.5F, 15.0F, -5.0F));
//		modelPartData.addChild("left_wing_prox", prox_wing, ModelTransform.pivot(1.5F, 15.0F, -5.0F));
//
//		// Distal wings
//		ModelPartBuilder leading_edge = ModelPartBuilder.create().
//				uv(29, 22).
//				cuboid(-0.5F, 0.0F, -0.5F, 1, 9, 1,dilation);
//		modelPartData.addChild("right_wing_distal1", leading_edge, ModelTransform.pivot(0.0F, 1.5F, 0.0F));
//		modelPartData.addChild("left_wing_distal1", leading_edge, ModelTransform.pivot(0.0F, 1.5F, 0.0F));
//		ModelPartBuilder feather = ModelPartBuilder.create().
//				uv(30,22).
//				cuboid(-0.5F, 0.0F, -0.5F, 1,9,1, dilation);
//		modelPartData.addChild("right_wing_distal2", feather,	ModelTransform.pivot(0.0F, 1.0F, 0.0F));
//		modelPartData.addChild("right_wing_distal3", feather, ModelTransform.pivot(0.0F, 0.5F, 0.0F));
//		modelPartData.addChild("right_wing_distal4", feather,	ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//		modelPartData.addChild("left_wing_distal2", feather,	ModelTransform.pivot(0.0F, 1.0F, 0.0F));
//		modelPartData.addChild("left_wing_distal3", feather, ModelTransform.pivot(0.0F, 0.5F, 0.0F));
//		modelPartData.addChild("left_wing_distal4", feather,	ModelTransform.pivot(0.0F, 0.0F, 0.0F));
//
//		return modelData;
//	}
//
//	protected Iterable<ModelPart> getHeadParts() {
//		return ImmutableList.of(this.head);
//	}
//
//	protected Iterable<ModelPart> getBodyParts() {
//		return ImmutableList.of(
//				this.torso,
//				this.leftBackLeg,
//				this.rightBackLeg,
//				this.leftFrontLeg,
//				this.rightFrontLeg,
//				this.upperTail,
//				this.lowerTail,
//				this.rightWingProx,
//				this.leftWingProx,
//				this.rightWingDistal1,
//				this.rightWingDistal2,
//				this.rightWingDistal3,
//				this.rightWingDistal4,
//				this.leftWingDistal1,
//				this.leftWingDistal2,
//				this.leftWingDistal3,
//				this.leftWingDistal4
//				);
//	}
//
//	public void animateModel(T flyingCatEntity, float limbAngle, float limbDistance, float tickDelta) {
//
//		super.animateModel(flyingCatEntity, limbAngle, limbDistance, tickDelta);
//		this.torso.pivotY = 12.0F;
//		this.torso.pivotZ = -10.0F;
//		this.head.pivotY = 15.0F;
//		this.head.pivotZ = -9.0F;
//		this.upperTail.pivotY = 15.0F;
//		this.upperTail.pivotZ = 8.0F;
//		this.lowerTail.pivotY = 20.0F;
//		this.lowerTail.pivotZ = 14.0F;
//		this.leftFrontLeg.pivotY = 14.1F;
//		this.leftFrontLeg.pivotZ = -5.0F;
//		this.rightFrontLeg.pivotY = 14.1F;
//		this.rightFrontLeg.pivotZ = -5.0F;
//		this.leftBackLeg.pivotY = 18.0F;
//		this.leftBackLeg.pivotZ = 5.0F;
//		this.rightBackLeg.pivotY = 18.0F;
//		this.rightBackLeg.pivotZ = 5.0F;
//		this.rightFrontLeg.pitch = 0.0F;
//		this.leftFrontLeg.pitch = 0.0F;
//		this.rightBackLeg.pitch = 0.0F;
//		this.leftBackLeg.pitch = 0.0F;
//		this.upperTail.pitch = 0.9F;
//
//		this.rightWingProx.pitch = proxWingPitchRest;
//		this.leftWingProx.pitch = proxWingPitchRest;
//		this.rightWingProx.yaw = proxWingYawRest;
//		this.leftWingProx.yaw = -proxWingYawRest;
//		this.rightWingDistal1.yaw = this.rightWingDistal2.yaw = this.rightWingDistal3.yaw = this.rightWingDistal4.yaw = distalWingYawRest;
//		this.leftWingDistal1.yaw = this.leftWingDistal2.yaw = this.leftWingDistal3.yaw = this.leftWingDistal4.yaw = -distalWingYawRest;
//		this.rightWingDistal1.pitch = this.leftWingDistal1.pitch = -1.30F;
//		this.rightWingDistal2.pitch = this.leftWingDistal2.pitch = -1.45F;
//		this.rightWingDistal3.pitch = this.leftWingDistal3.pitch = -1.60F;
//		this.rightWingDistal4.pitch = this.leftWingDistal4.pitch = -1.75F;
//
//		boolean flying = !flyingCatEntity.isOnGround();
//
//		 if (flyingCatEntity.isInSittingPose()) {
//			this.animationState = 3;
//			// Torso
//			this.torso.pitch = 0.8F;
//			this.torso.pivotY += -4.0F;
//			this.torso.pivotZ += 5.0F;
//			// Head
//			this.head.pivotY += -3.3F;
//			this.head.pivotZ += 1.0F;
//			// Front legs
//			this.leftFrontLeg.pitch = -0.157F;
//			this.leftFrontLeg.pivotY = 16.1F;
//			this.leftFrontLeg.pivotZ = -7.0F;
//			this.rightFrontLeg.pitch = -0.157F;
//			this.rightFrontLeg.pivotY = 16.1F;
//			this.rightFrontLeg.pivotZ = -7.0F;
//			// Back legs
//			this.leftBackLeg.pitch = -1.57F;
//			this.leftBackLeg.pivotY = 21.0F;
//			this.leftBackLeg.pivotZ = 1.0F;
//			this.rightBackLeg.pitch = -1.57F;
//			this.rightBackLeg.pivotY = 21.0F;
//			this.rightBackLeg.pivotZ = 1.0F;
//			// Tail
//			this.upperTail.pivotY += 8.0F;
//			this.upperTail.pivotZ += -2.0F;
//			this.lowerTail.pivotY += 2.0F;
//			this.lowerTail.pivotZ += -0.8F;
//			this.upperTail.pitch = 1.7F;
//			this.lowerTail.pitch = 2.7F;
//			}
//		else if (flying){
//			this.animationState = 4;
//			if (Entity.squaredHorizontalLength(flyingCatEntity.getVelocity()) < 1.0E-7D) {
//				this.rightFrontLeg.pitch += 0.0F;
//				this.leftFrontLeg.pitch += 0.0F;
//				this.rightBackLeg.pitch += 0.0F;
//				this.leftBackLeg.pitch += 0.0F;
//			} else {
//				this.rightFrontLeg.pitch += 0.2F;
//				this.leftFrontLeg.pitch += 0.2F;
//				this.rightBackLeg.pitch += 0.2F;
//				this.leftBackLeg.pitch += 0.2F;
//				this.upperTail.pitch += 0.5F;
//			}
//			this.rightWingProx.pitch = proxWingPitchFlight;
//			this.leftWingProx.pitch = proxWingPitchFlight;
//			this.rightWingProx.yaw = proxWingYawFlight;
//			this.leftWingProx.yaw = -proxWingYawFlight;
//			this.rightWingDistal1.yaw = this.rightWingDistal2.yaw = this.rightWingDistal3.yaw = this.rightWingDistal4.yaw = distalWingYawFlight;
//			this.leftWingDistal1.yaw = this.leftWingDistal2.yaw = this.leftWingDistal3.yaw = this.leftWingDistal4.yaw = -distalWingYawFlight;
//			this.rightWingDistal1.pitch = this.leftWingDistal1.pitch = -0.7F;
//			this.rightWingDistal2.pitch = this.leftWingDistal2.pitch = -0.95F;
//			this.rightWingDistal3.pitch = this.leftWingDistal3.pitch = -1.10F;
//			this.rightWingDistal4.pitch = this.leftWingDistal4.pitch = -1.25F;
//			}
//		else {
//			this.animationState = 1;
//		}
//	}
//
//	public void setAngles(T flyingCatEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
//		this.head.pitch = headPitch * 0.0175F;
//		this.head.yaw = headYaw * 0.0175F;
//
//		if (this.animationState != 3) {
//			// If not sneaking set torso pitch.
//			this.torso.pitch = 1.57F;
//			if (this.animationState == 4) {
//				this.torso.pitch = 1.47F;
//				// The speed is dictated by the multiplier of the animationProgress ticker.
//				this.rightWingProx.roll = MathHelper.cos(animationProgress * 0.8F) * 3.14F * 0.15F;
//				this.leftWingProx.roll = -this.rightWingProx.roll;
//			// If sneaking, walking, standing...
//			} else {
//				this.rightWingProx.pitch = this.leftWingProx.pitch = proxWingPitchRest;
//				this.rightWingProx.roll = proxWingRollRest;
//				this.rightWingProx.yaw = proxWingYawRest;
//				this.leftWingProx.roll = -proxWingRollRest;
//				this.leftWingProx.yaw = -proxWingYawRest;
//				this.rightWingDistal1.pitch = this.leftWingDistal1.pitch = -1.1F;
//				this.rightWingDistal2.pitch = this.leftWingDistal2.pitch = -1.25F;
//				this.rightWingDistal3.pitch = this.leftWingDistal3.pitch = -1.40F;
//				this.rightWingDistal4.pitch = this.leftWingDistal4.pitch = -1.55F;
//				this.leftBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
//				this.rightBackLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.14F) * limbDistance;
//				this.leftFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F + 3.14F) * limbDistance;
//				this.rightFrontLeg.pitch = MathHelper.cos(limbAngle * 0.6662F) * limbDistance;
//				if (this.animationState == 1) {
//					this.lowerTail.pitch = 1.73F + 0.785F * MathHelper.cos(limbAngle) * limbDistance;
//				} else {
//					this.lowerTail.pitch = 1.73F + 0.471F * MathHelper.cos(limbAngle) * limbDistance;
//				}
//			}
//		}
//	}
}