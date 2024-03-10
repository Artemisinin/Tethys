package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.WaterStriderEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;

public class WaterStriderEntityModel<T extends WaterStriderEntity> extends AnimalModel<T> {
	private final ModelPart head;
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


	public WaterStriderEntityModel(ModelPart root) {
		super(true, 0F, 0F, 2.0F, 2.0F, 16F);
		this.head = root.getChild("head");
		this.body = root.getChild("body");
		this.rightAntenna = this.head.getChild("right_antenna");
		this.leftAntenna = this.head.getChild("left_antenna");
		this.rightLegUpper1 = root.getChild("right_leg_upper_1");
		this.rightLegUpper2 = root.getChild("right_leg_upper_2");
		this.rightLegUpper3 = root.getChild("right_leg_upper_3");
		this.rightLegUpper4 = root.getChild("right_leg_upper_4");
		this.rightLegLower1 = this.rightLegUpper1.getChild("right_leg_lower_1");
		this.rightLegLower2 = this.rightLegUpper2.getChild("right_leg_lower_2");
		this.rightLegLower3 = this.rightLegUpper3.getChild("right_leg_lower_3");
		this.rightLegLower4 = this.rightLegUpper4.getChild("right_leg_lower_4");
		this.leftLegUpper1 = root.getChild("left_leg_upper_1");
		this.leftLegUpper2 = root.getChild("left_leg_upper_2");
		this.leftLegUpper3 = root.getChild("left_leg_upper_3");
		this.leftLegUpper4 = root.getChild("left_leg_upper_4");
		this.leftLegLower1 = this.leftLegUpper1.getChild("left_leg_lower_1");
		this.leftLegLower2 = this.leftLegUpper2.getChild("left_leg_lower_2");
		this.leftLegLower3 = this.leftLegUpper3.getChild("left_leg_lower_3");
		this.leftLegLower4 = this.leftLegUpper4.getChild("left_leg_lower_4");
	}

		float upperLegRoll = 1.85F;
		float lowerLegRoll = -1.0F;

	public static ModelData getModelData() {
		ModelData modelData = new ModelData();
		ModelPartData core = modelData.getRoot();
		Dilation headPartDilation = new Dilation(-0.2F);
		Dilation upperLegDilation = new Dilation(0.1F);

		// Head
		ModelPartData head = core.addChild("head", ModelPartBuilder.create().
				cuboid("main", -1.0F, -0.5F, -1.0F, 2,1,2, 21, 10).
				cuboid("proboscis", -0.5F, -0.125F, -1.5F, 1,3,1, headPartDilation, 21, 4),
				ModelTransform.pivot(0.0F, 19.0F, -7.5F));
		ModelPartBuilder antenna = ModelPartBuilder.create().cuboid(-0.5F, -0.5F, -0.5F, 1,3,1,headPartDilation, 21,0);
		head.addChild("right_antenna", antenna, ModelTransform.pivot(-0.75F, -2.0F, -0.5F));
		head.addChild("left_antenna", antenna, ModelTransform.pivot(0.75F, -2.0F, -0.5F));

		// Body
		core.addChild("body", ModelPartBuilder.create().
				cuboid("main", -2.0F, -1.0F, -3.0F, 4, 2, 11, 0, 3).
				cuboid("spike1", -0.5F, -2.0F, -1.5F, 1, 1, 1, 4, 0).
				cuboid("spike2", -0.5F, -3.0F, 0.5F, 1, 2, 1, 4, 0).
				cuboid("spike3", -0.5F, -3.0F, 2.5F, 1, 2, 1, 4, 0).
				cuboid("spike4", -0.5F, -2.0F, 4.5F, 1, 1, 1, 4, 0),
		ModelTransform.pivot(0.0F, 19.0F, -3.0F));

		// Legs
		ModelPartBuilder upper_leg = ModelPartBuilder.create().cuboid(-0.5F, -0.5F, -0.5F, 1, 6, 1, upperLegDilation).uv(0,0);
		ModelPartBuilder lower_leg = ModelPartBuilder.create().cuboid(-0.5F, -0.5F, -0.5F, 1, 10, 1).uv(0,0);

		// Right leg
		ModelPartData rightLegUpper1 = core.addChild("right_leg_upper_1", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -5.25F));
		rightLegUpper1.addChild("right_leg_lower_1", lower_leg,
				ModelTransform.pivot(0.25F, 6.0F, 0.0F));
		ModelPartData rightLegUpper2 = core.addChild("right_leg_upper_2", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -2.75F));
		rightLegUpper2.addChild("right_leg_lower_2", lower_leg,
				ModelTransform.pivot(0.25F, 6.0F, 0.0F));
		ModelPartData rightLegUpper3 = core.addChild("right_leg_upper_3", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -0.25F));
		rightLegUpper3.addChild("right_leg_lower_3", lower_leg,
				ModelTransform.pivot(0.25F, 6.0F, 0.0F));
		ModelPartData rightLegUpper4 = core.addChild("right_leg_upper_4", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, 2.25F));
		rightLegUpper4.addChild("right_leg_lower_4", lower_leg,
				ModelTransform.pivot(0.25F, 6.0F, 0.0F));

		// Left leg
		ModelPartData leftLegUpper1 = core.addChild("left_leg_upper_1", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, -5.25F));
		leftLegUpper1.addChild("left_leg_lower_1", lower_leg,
				ModelTransform.pivot(-0.25F, 6.0F, 0.0F));
		ModelPartData leftLegUpper2 = core.addChild("left_leg_upper_2", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, -2.75F));
		leftLegUpper2.addChild("left_leg_lower_2", lower_leg,
				ModelTransform.pivot(-0.25F, 6.0F, 0.0F));
		ModelPartData leftLegUpper3 = core.addChild("left_leg_upper_3", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, -0.25F));
		leftLegUpper3.addChild("left_leg_lower_3", lower_leg,
				ModelTransform.pivot(-0.25F, 6.0F, 0.0F));
		ModelPartData leftLegUpper4 = core.addChild("left_leg_upper_4", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, 2.25F));
		leftLegUpper4.addChild("left_leg_lower_4", lower_leg,
				ModelTransform.pivot(-0.25F, 6.0F, 0.0F));

		return modelData;
	}

	@Override
	protected Iterable<ModelPart> getHeadParts() {
		return ImmutableList.of(this.head);
	}

	@Override
	protected Iterable<ModelPart> getBodyParts() {
		return ImmutableList.of(
				this.body,
				this.rightLegUpper1,
				this.rightLegUpper2,
				this.rightLegUpper3,
				this.rightLegUpper4,
				this.leftLegUpper1,
				this.leftLegUpper2,
				this.leftLegUpper3,
				this.leftLegUpper4
		);
	}

	public void setAngles(T waterStriderEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	this.head.pitch = 0F;
	this.head.yaw = 0F;
	this.rightAntenna.roll = -0.4F;
	this.leftAntenna.roll = 0.4F;
	this.rightLegUpper1.roll = this.rightLegUpper2.roll = this.rightLegUpper3.roll = this.rightLegUpper4.roll = upperLegRoll;
	this.rightLegLower1.roll = this.rightLegLower2.roll = this.rightLegLower3.roll = this.rightLegLower4.roll = lowerLegRoll;
	this.leftLegUpper1.roll = this.leftLegUpper2.roll = this.leftLegUpper3.roll = this.leftLegUpper4.roll = -upperLegRoll;
	this.leftLegLower1.roll = this.leftLegLower2.roll = this.leftLegLower3.roll = this.leftLegLower4.roll = -lowerLegRoll;
	// This is the spread of the legs.
	this.rightLegUpper1.pitch = this.leftLegUpper1.pitch = -0.85F;
	this.rightLegUpper2.pitch = this.leftLegUpper2.pitch = -0.3F;
	this.rightLegUpper3.pitch = this.leftLegUpper3.pitch = 0.3F;
	this.rightLegUpper4.pitch = this.leftLegUpper4.pitch = 0.7F;
	}
}