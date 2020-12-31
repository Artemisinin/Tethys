package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.FlyingCatEntity;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.model.ModelUtil;
import net.minecraft.client.render.entity.model.CatEntityModel;
import net.minecraft.entity.passive.CatEntity;


public class FlyingCatEntityModel<FlyingCatEntity extends CatEntity> extends CatEntityModel<FlyingCatEntity> {
	private float sleepAnimation;
	private float tailCurlAnimation;
	private float headDownAnimation;

	private final ModelPart torso;
	private final ModelPart head = new ModelPart(this);
	private final ModelPart upperTail;
	private final ModelPart lowerTail;
	private final ModelPart leftBackLeg;
	private final ModelPart rightBackLeg;
	private final ModelPart leftFrontLeg;
	private final ModelPart rightFrontLeg;
	private final ModelPart rightWingProx;
//	private final ModelPart rightWingDistal;
//	private final ModelPart rightdistal5_r1;
//	private final ModelPart rightdistal4_r1;
//	private final ModelPart rightdistal3_r1;
//	private final ModelPart rightdistal2_r1;
//	private final ModelPart rightdistal1_r1;
//	private final ModelPart leftwing;
//	private final ModelPart leftwingprox3_r1;
//	private final ModelPart leftwingprox1_r1;
//	private final ModelPart leftwingdistal;
//	private final ModelPart leftdistal5_r1;
//	private final ModelPart leftdistal4_r1;
//	private final ModelPart leftdistal3_r1;
//	private final ModelPart leftdistal2_r1;
//	private final ModelPart leftdistal1_r1;

	public FlyingCatEntityModel(float scale) {
		super(0.4F);

		this.head.addCuboid("main", -2.5F, -2.0F, -3.0F, 5, 4, 5, 0, 0, 0);
		this.head.addCuboid("nose", -1.5F, 0.0F, -4.0F, 3, 2, 2, 0, 0, 24);
		this.head.addCuboid("ear1", -2.0F, -3.0F, 0.0F, 1, 1, 2, 0, 0, 10);
		this.head.addCuboid("ear2", 1.0F, -3.0F, 0.0F, 1, 1, 2, 0, 6, 10);
		head.setPivot(0.0F, -2.0F, -10.0F);

		this.torso = new ModelPart(this, 20, 0);
		this.torso.addCuboid(-2.0F,-8.0F, 3.0F,4.0F, 16.0F,6.0F, 0.0F, false);
		torso.setPivot(0.0F, 17.0F, 1.0F);

		this.upperTail = new ModelPart(this, 0, 15);
		this.upperTail.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, scale);
		this.upperTail.pitch = 0.9F;
		this.upperTail.setPivot(0.0F, 15.0F, 8.0F);
		//setRotationAngle(upperTail, 0.7854F, 0.0F, 0.0F);

		this.lowerTail = new ModelPart(this, 4, 15);
		this.lowerTail.addCuboid(-0.5F, 0.0F, 0.0F, 1.0F, 8.0F, 1.0F, scale);
		this.lowerTail.setPivot(0.0F, 20.0F, 14.0F);
		//setRotationAngle(lowerTail, 0.7854F, 0.0F, 0.0F);

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

		this.rightWingProx = new ModelPart(this);
		this.rightWingProx.addCuboid("rightWingProx1", -0.6112F, -10.7771F, -0.407F, 1, 11, 1, 0.1F, 41, 23);
		this.rightWingProx.addCuboid("rightWingProx2", -0.7201F, 5.1272F, -1.5185F, 2, 3, 2, -0.1F, 55, 15);
		this.rightWingProx.addCuboid("rightWingProx3", -0.6665F, -0.6073F, -1.7416F, 2, 6, 3, 0, 41, 23);
		this.rightWingProx.setPivot(1.0F, -2.0F, -5.0F);
		setRotationAngle(rightWingProx, -0.8727F, -0.0436F, 0.48F);
		torso.addChild(rightWingProx);

		//rightwingprox3_r1.setPivot(0.6854F, -10.6735F, 0.8477F);
		//setRotationAngle(rightwingprox3_r1, -0.0873F, 0.6981F, -0.0436F);
		//rightwingprox1_r1.setPivot(0.5F, -0.5F, -0.5F);
		//setRotationAngle(rightwingprox1_r1, -0.0873F, -0.0436F, 0.0436F);

//		this.rightWingDistal = new ModelPart(this);
//		this.rightWingDistal.addCuboid("rightWingDistal1", -0.6271F, -0.503F, -8.9136F, 1, 1, 9, 0, 20, 22);
//
//		//setRotationAngle(rightWingDistal1, -0.1745F, -2.4871F, 0.0F);
//
//		this.rightWingDistal.setPivot(0.2299F, -10.9822F, -0.2017F);
//		setRotationAngle(rightwingdistal1, 0.3054F, 0.0873F, 0.0873F);
//
//		rightdistal5_r1 = new ModelPart(this);
//		rightdistal5_r1.setPivot(0.8969F, 3.1183F, 0.3522F);
//		rightwingdistal.addChild(rightdistal5_r1);
//		setRotationAngle(rightdistal5_r1, 0.9163F, -2.4871F, 0.0F);
//		rightdistal5_r1.setTextureOffset(20, 22).addCuboid(-0.5864F, -0.7835F, -9.801F, 1.0F, 1.0F, 9.0F, 0.0F, false);
//
//		rightdistal4_r1 = new ModelPart(this);
//		rightdistal4_r1.setPivot(0.9548F, 2.0065F, 0.2087F);
//		rightwingdistal.addChild(rightdistal4_r1);
//		setRotationAngle(rightdistal4_r1, 0.6545F, -2.4871F, 0.0F);
//		rightdistal4_r1.setTextureOffset(20, 22).addCuboid(-0.2376F, -0.5528F, -9.1462F, 1.0F, 1.0F, 9.0F, 0.0F, false);
//
//		rightdistal3_r1 = new ModelPart(this);
//		rightdistal3_r1.setPivot(0.7838F, 1.3446F, 0.459F);
//		rightwingdistal.addChild(rightdistal3_r1);
//		setRotationAngle(rightdistal3_r1, 0.3927F, -2.4871F, 0.0F);
//		rightdistal3_r1.setTextureOffset(20, 22).addCuboid(-0.4649F, -0.4847F, -8.8903F, 1.0F, 1.0F, 9.0F, 0.0F, false);
//
//		rightdistal2_r1 = new ModelPart(this);
//		rightdistal2_r1.setPivot(0.567F, 0.6142F, 0.1773F);
//		rightwingdistal.addChild(rightdistal2_r1);
//		setRotationAngle(rightdistal2_r1, 0.1309F, -2.4871F, 0.0F);
//		rightdistal2_r1.setTextureOffset(20, 22).addCuboid(-0.4878F, -0.4827F, -8.9598F, 1.0F, 1.0F, 9.0F, 0.0F, false);
//
//		rightdistal1_r1 = new ModelPart(this);
//		rightdistal1_r1.setPivot(-0.108F, 0.3447F, -0.2372F);
//		rightwingdistal.addChild(rightdistal1_r1);
//
//
//		leftwing = new ModelPart(this);
//		leftwing.setPivot(-1.0F, -2.0F, -5.0F);
//		torso.addChild(leftwing);
//		setRotationAngle(leftwing, -0.9599F, 0.0436F, -0.3491F);
//
//		leftwingprox3_r1 = new ModelPart(this);
//		leftwingprox3_r1.setPivot(-0.6854F, -10.6735F, 0.8477F);
//		leftwing.addChild(leftwingprox3_r1);
//		setRotationAngle(leftwingprox3_r1, -0.0873F, -0.6981F, 0.0436F);
//		leftwingprox3_r1.setTextureOffset(55, 15).addCuboid(-1.2799F, 5.1272F, -1.5185F, 2.0F, 3.0F, 2.0F, -0.1F, false);
//		leftwingprox3_r1.setTextureOffset(41, 23).addCuboid(-1.3335F, -0.6073F, -1.7416F, 2.0F, 6.0F, 3.0F, 0.0F, false);
//
//		leftwingprox1_r1 = new ModelPart(this);
//		leftwingprox1_r1.setPivot(-0.5F, -0.5F, -0.5F);
//		leftwing.addChild(leftwingprox1_r1);
//		setRotationAngle(leftwingprox1_r1, -0.0873F, 0.0436F, -0.0436F);
//		leftwingprox1_r1.setTextureOffset(60, 0).addCuboid(-0.3888F, -10.7771F, -0.407F, 1.0F, 11.0F, 1.0F, 0.1F, false);
//
//		leftwingdistal = new ModelPart(this);
//		leftwingdistal.setPivot(-0.2299F, -10.9822F, -0.2017F);
//		leftwing.addChild(leftwingdistal);
//		setRotationAngle(leftwingdistal, 0.0873F, -0.0873F, -0.0873F);
//
//		leftdistal5_r1 = new ModelPart(this);
//		leftdistal5_r1.setPivot(-0.8969F, 3.1183F, 0.3522F);
//		leftwingdistal.addChild(leftdistal5_r1);
//		setRotationAngle(leftdistal5_r1, 0.9163F, 2.4871F, 0.0F);
//		leftdistal5_r1.setTextureOffset(20, 22).addCuboid(-0.4136F, -0.7835F, -9.801F, 1.0F, 1.0F, 9.0F, 0.0F, true);
//
//		leftdistal4_r1 = new ModelPart(this);
//		leftdistal4_r1.setPivot(-0.9548F, 2.0065F, 0.2087F);
//		leftwingdistal.addChild(leftdistal4_r1);
//		setRotationAngle(leftdistal4_r1, 0.6545F, 2.4871F, 0.0F);
//		leftdistal4_r1.setTextureOffset(20, 22).addCuboid(-0.7624F, -0.5528F, -9.1462F, 1.0F, 1.0F, 9.0F, 0.0F, true);
//
//		leftdistal3_r1 = new ModelPart(this);
//		leftdistal3_r1.setPivot(-0.7838F, 1.3446F, 0.459F);
//		leftwingdistal.addChild(leftdistal3_r1);
//		setRotationAngle(leftdistal3_r1, 0.3927F, 2.4871F, 0.0F);
//		leftdistal3_r1.setTextureOffset(20, 22).addCuboid(-0.5351F, -0.4847F, -8.8903F, 1.0F, 1.0F, 9.0F, 0.0F, true);
//
//		leftdistal2_r1 = new ModelPart(this);
//		leftdistal2_r1.setPivot(-0.567F, 0.6142F, 0.1773F);
//		leftwingdistal.addChild(leftdistal2_r1);
//		setRotationAngle(leftdistal2_r1, 0.1309F, 2.4871F, 0.0F);
//		leftdistal2_r1.setTextureOffset(20, 22).addCuboid(-0.5122F, -0.4827F, -8.9598F, 1.0F, 1.0F, 9.0F, 0.0F, true);
//
//		leftdistal1_r1 = new ModelPart(this);
//		leftdistal1_r1.setPivot(0.108F, 0.3447F, -0.2372F);
//		leftwingdistal.addChild(leftdistal1_r1);
//		setRotationAngle(leftdistal1_r1, -0.1745F, 2.4871F, 0.0F);
//		leftdistal1_r1.setTextureOffset(20, 22).addCuboid(-0.3729F, -0.503F, -8.9136F, 1.0F, 1.0F, 9.0F, 0.0F, true);
//
//		textureWidth = 64;
//		textureHeight = 32;
//
	}

	public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
		modelRenderer.pivotX = x;
		modelRenderer.pivotY = y;
		modelRenderer.pivotZ = z;
	}

	@Override
	public void setAngles(FlyingCatEntity entity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
			super.setAngles(entity, limbAngle, limbDistance, animationProgress, headYaw, headPitch);
			if (this.sleepAnimation > 0.0F) {
				this.head.roll = ModelUtil.interpolateAngle(this.head.roll, -1.2707963F, this.sleepAnimation);
				this.head.yaw = ModelUtil.interpolateAngle(this.head.yaw, 1.2707963F, this.sleepAnimation);
				this.leftFrontLeg.pitch = -1.2707963F;
				this.rightFrontLeg.pitch = -0.47079635F;
				this.rightFrontLeg.roll = -0.2F;
				this.rightFrontLeg.pivotX = -0.2F;
				this.leftBackLeg.pitch = -0.4F;
				this.rightBackLeg.pitch = 0.5F;
				this.rightBackLeg.roll = -0.5F;
				this.rightBackLeg.pivotX = -0.3F;
				this.rightBackLeg.pivotY = 20.0F;
				this.upperTail.pitch = ModelUtil.interpolateAngle(this.upperTail.pitch, 0.8F, this.tailCurlAnimation);
				this.lowerTail.pitch = ModelUtil.interpolateAngle(this.lowerTail.pitch, -0.4F, this.tailCurlAnimation);
			}

			if (this.headDownAnimation > 0.0F) {
				this.head.pitch = ModelUtil.interpolateAngle(this.head.pitch, -0.58177644F, this.headDownAnimation);
		}
	}
}