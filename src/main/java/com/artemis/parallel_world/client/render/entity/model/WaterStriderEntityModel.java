package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.WaterStriderEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.AnimalModel;

public class WaterStriderEntityModel<T extends WaterStriderEntity> extends AnimalModel<T> {
	private final ModelPart head = new ModelPart(this);
	private final ModelPart rightAntenna;
	private final ModelPart leftAntenna;
	private final ModelPart body;
	private final ModelPart rightLegUpper1;
	private final ModelPart rightLegUpper2;
	private final ModelPart rightLegUpper3;
	private final ModelPart rightLegUpper4;
	private final ModelPart rightLegLower1;
	private final ModelPart rightLegLower2;
	private final ModelPart rightLegLower3;
	private final ModelPart rightLegLower4;
	private final ModelPart leftLegUpper1;
	private final ModelPart leftLegUpper2;
	private final ModelPart leftLegUpper3;
	private final ModelPart leftLegUpper4;
	private final ModelPart leftLegLower1;
	private final ModelPart leftLegLower2;
	private final ModelPart leftLegLower3;
	private final ModelPart leftLegLower4;


	public WaterStriderEntityModel() {
		super(true, 0F, 0F, 2.0F, 2.0F, 16F);
		textureWidth = 32;
		textureHeight = 16;
		float upperLegRoll = 1.85F;
		float lowerLegRoll = -1.85F;

		this.head.addCuboid("main", -1.0F, -0.5F, -2.0F, 2, 1, 2, 0.0F, 21, 10);
		this.head.addCuboid("proboscis", -0.5F, -0.125F, -2.5F, 1, 3, 1, -0.2F, 21, 4);
		this.head.setPivot(0.0F, 19.0F, -6.5F);

		rightAntenna = new ModelPart(this, 21, 0);
		this.rightAntenna.addCuboid(0.0F, -2.5F, -0.5F, 1, 3, 1, (float) -0.2);
		this.rightAntenna.setPivot(-0.75F, -1.0F, -1.0F);
		this.head.addChild(rightAntenna);
		this.rightAntenna.roll = -0.4F;

		leftAntenna = new ModelPart(this, 21, 0);
		this.leftAntenna.addCuboid(0.0F, -2.5F, -0.5F, 1, 3, 1, (float) -0.2);
		this.leftAntenna.setPivot(0.25F, -1.0F, -1.0F);
		this.head.addChild(leftAntenna);
		this.leftAntenna.roll = 0.4F;

		body = new ModelPart(this);
		this.body.addCuboid("main", -2.0F, -1.0F, -4.0F, 4, 2, 11, 0, 0, 3);
		this.body.addCuboid("spike1", -0.5F, -2.0F, -2.0F, 1, 1, 1, 0.0F, 4, 0);
		this.body.addCuboid("spike2", -0.5F, -3F, 0.0F, 1, 2, 1, 0.0F, 4, 0);
		this.body.addCuboid("spike3", -0.5F, -3F, 2.0F, 1, 2, 1, 0.0F, 4, 0);
		this.body.addCuboid("spike4", -0.5F, -2F, 4.0F, 1, 1, 1, 0.0F, 4, 0);
		this.body.setPivot(0.0F, 19F, -3.0F);

		rightLegUpper1 = new ModelPart(this, 0, 0);
		rightLegUpper1.addCuboid(0.0F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		rightLegUpper1.setPivot(-1.5F, 19.0F, -5.5F);
		rightLegUpper1.roll = upperLegRoll;

		rightLegUpper2 = new ModelPart(this, 0, 0);
		rightLegUpper2.addCuboid(0.0F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		rightLegUpper2.setPivot(-1.5F, 19.0F, -3.5F);
		rightLegUpper2.roll = upperLegRoll;

		rightLegUpper3 = new ModelPart(this, 0, 0);
		rightLegUpper3.addCuboid(0.0F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		rightLegUpper3.setPivot(-1.5F, 19.0F, 0.5F);
		rightLegUpper3.roll = upperLegRoll;

		rightLegUpper4 = new ModelPart(this, 0,0);
		rightLegUpper4.addCuboid(0.0F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		rightLegUpper4.setPivot(-1.5F, 19.0F, 2.0F);
		rightLegUpper4.roll = upperLegRoll;

		leftLegUpper1 = new ModelPart(this, 0, 0);
		leftLegUpper1.addCuboid(-0.5F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		leftLegUpper1.setPivot(1.5F, 19.0F, -5.5F);
		leftLegUpper1.roll = -upperLegRoll;

		leftLegUpper2 = new ModelPart(this, 0,0);
		leftLegUpper2.addCuboid(-0.5F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		leftLegUpper2.setPivot(1.5F, 19.0F, -3.5F);
		leftLegUpper2.roll = -upperLegRoll;

		leftLegUpper3 = new ModelPart(this, 0,0);
		leftLegUpper3.addCuboid(-0.5F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		leftLegUpper3.setPivot(1.5F, 19.0F, 0.5F);
		leftLegUpper3.roll = -upperLegRoll;

		leftLegUpper4 = new ModelPart(this,0,0);
		leftLegUpper4.addCuboid(-0.5F, 0.0F, 0.5F, 1.0F, 3.0F, 1.0F, 0.0F);
		leftLegUpper4.setPivot(1.5F, 19.0F, 2.0F);
		leftLegUpper4.roll = -upperLegRoll;

		rightLegLower1 = new ModelPart(this, 0, 0);
		rightLegLower1.setPivot(0.25F, 3.0F, 0.5F);
		rightLegLower1.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		rightLegLower1.roll = lowerLegRoll;
		rightLegUpper1.addChild(rightLegLower1);

		rightLegLower2 = new ModelPart(this, 0, 0);
		rightLegLower2.setPivot(0.25F, 3.0F, 0.5F);
		rightLegLower2.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		rightLegLower2.roll = lowerLegRoll;
		rightLegUpper2.addChild(rightLegLower2);

		rightLegLower3 = new ModelPart(this, 0, 0);
		rightLegLower3.setPivot(0.25F, 3.0F, 0.5F);
		rightLegLower3.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		rightLegLower3.roll = lowerLegRoll;
		rightLegUpper3.addChild(rightLegLower3);

		rightLegLower4 = new ModelPart(this, 0,0);
		rightLegLower4.setPivot(0.25F, 3.0F, 0.5F);
		rightLegLower4.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		rightLegLower4.roll = lowerLegRoll;
		rightLegUpper4.addChild(rightLegLower4);

		leftLegLower1 = new ModelPart(this, 0, 0);
		leftLegLower1.setPivot(0.5F, 3.0F, 0.5F);
		leftLegLower1.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		leftLegLower1.roll = -lowerLegRoll;
		leftLegUpper1.addChild(leftLegLower1);

		leftLegLower2 = new ModelPart(this, 0,0);
		leftLegLower2.setPivot(0.5F, 3.0F, 0.5F);
		leftLegLower2.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		leftLegLower2.roll = -lowerLegRoll;
		leftLegUpper2.addChild(leftLegLower2);

		leftLegLower3 = new ModelPart(this,0,0);
		leftLegLower3.setPivot(0.5F, 3.0F, 0.5F);
		leftLegLower3.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		leftLegLower3.roll = -lowerLegRoll;
		leftLegUpper3.addChild(leftLegLower3);

		leftLegLower4 = new ModelPart(this,0,0);
		leftLegLower4.setPivot(0.5F, 3.0F, 0.5F);
		leftLegLower4.addCuboid(0.0F, -0.5F, 0.0F, 1.0F, 4.0F, 1.0F, 0.0F);
		leftLegLower4.roll = -lowerLegRoll;
		leftLegUpper4.addChild(leftLegLower4);
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(this.body, this.rightLegUpper1, this.rightLegUpper2, this.rightLegUpper3, this.rightLegUpper4, this.leftLegUpper1, this.leftLegUpper2, this.leftLegUpper3, this.leftLegUpper4);
	}

	public void setAngles(T waterStriderEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	this.head.pitch = 0F;
	this.head.yaw = 0F;
	//this.rightLegUpper1.roll = 1.83F;
	}
}