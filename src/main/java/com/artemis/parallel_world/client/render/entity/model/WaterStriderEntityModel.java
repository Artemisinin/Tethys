package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.WaterStriderEntity;
import com.google.common.collect.ImmutableList;
import net.minecraft.client.model.*;
import net.minecraft.client.render.entity.model.AnimalModel;
import net.minecraft.client.util.math.Dilation;
import org.lwjgl.system.CallbackI;

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
		this.rightAntenna = root.getChild("right_antenna");
		this.leftAntenna = root.getChild("left_antenna");
		this.rightLegUpper1 = root.getChild("right_leg_upper_1");
		this.rightLegUpper2 = root.getChild("right_leg_upper_2");
		this.rightLegUpper3 = root.getChild("right_leg_upper_3");
		this.rightLegUpper4 = root.getChild("right_leg_upper_4");
		this.rightLegLower1 = root.getChild("right_leg_lower_1");
		this.rightLegLower2 = root.getChild("right_leg_lower_2");
		this.rightLegLower3 = root.getChild("right_leg_lower_3");
		this.rightLegLower4 = root.getChild("right_leg_lower_4");
		this.leftLegUpper1 = root.getChild("left_leg_upper_1");
		this.leftLegUpper2 = root.getChild("left_leg_upper_2");
		this.leftLegUpper3 = root.getChild("left_leg_upper_3");
		this.leftLegUpper4 = root.getChild("left_leg_upper_4");
		this.leftLegLower1 = root.getChild("left_leg_lower_1");
		this.leftLegLower2 = root.getChild("left_leg_lower_2");
		this.leftLegLower3 = root.getChild("left_leg_lower_3");
		this.leftLegLower4 = root.getChild("left_leg_lower_4");
	}

//		textureWidth = 32;
//		textureHeight = 16;
		float upperLegRoll = 1.85F;
		float lowerLegRoll = -1.85F;

	public static ModelData getModelData() {
		ModelData modelData = new ModelData();
		ModelPartData modelPartData = modelData.getRoot();
		Dilation headPartDilation = new Dilation(-0.2F);
		modelPartData.addChild("head", ModelPartBuilder.create().
				cuboid("main", -1.0F, -0.5F, -2.0F, 2,1,2, 21, 10).
				cuboid("proboscis", 0.5F, -0.125F, -2.5F, 1,3,1, headPartDilation, 21, 4),
				ModelTransform.pivot(0.0F, 19.0F, -6.5F));
		ModelPartBuilder antenna = ModelPartBuilder.create().cuboid(0.0F, -2.5F, -0.5F, 1,3,1,headPartDilation, 21,0);
		modelPartData.addChild("right_antenna", antenna, ModelTransform.pivot(-0.75F, -1.0F, -1.0F));
		modelPartData.addChild("left_antenna", antenna, ModelTransform.pivot(0.25F, -1.0F, -1.0F));
		modelPartData.addChild("body", ModelPartBuilder.create().
				cuboid("main", -2.0F, -1.0F, -4.0F, 4, 2, 11, 0, 3).
				cuboid("spike1", -0.5F, -2.0F, -2.0F, 1, 1, 1, 4, 0).
				cuboid("spike2", -0.5F, -3.0F, 0.0F, 1, 2, 1, 4, 0).
				cuboid("spike3", -0.5F, -3.0F, 0.0F, 1, 2, 1, 4, 0).
				cuboid("spike4", -0.5F, -2.0F, 0.0F, 1, 1, 1, 4, 0),
		ModelTransform.pivot(0.0F, 19.0F, -3.0F));

		ModelPartBuilder upper_leg = ModelPartBuilder.create().cuboid(0.0F, 0.0F, 1.0F, 3, 1, 0).uv(0,0);
		ModelPartBuilder lower_leg = ModelPartBuilder.create().cuboid(0.0F, -0.5F, 0.0F, 1, 4, 1).uv(0,0);

		// Right leg
		modelPartData.addChild("right_leg_upper_1", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -5.5F));
		modelPartData.addChild("right_leg_lower_1", lower_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -3.0F));
		modelPartData.addChild("right_leg_upper_2", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -3.0F));
		modelPartData.addChild("right_leg_lower_2", lower_leg,
				ModelTransform.pivot(0.25F, 3.0F, 0.5F));
		modelPartData.addChild("right_leg_upper_3", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, -0.5F));
		modelPartData.addChild("right_leg_lower_3", lower_leg,
				ModelTransform.pivot(0.25F, 3.0F, 0.5F));
		modelPartData.addChild("right_leg_upper_4", upper_leg,
				ModelTransform.pivot(-1.5F, 19.0F, 2.0F));
		modelPartData.addChild("right_leg_lower_4", lower_leg,
				ModelTransform.pivot(0.25F, 3.0F, 0.5F));

		// Left leg
		modelPartData.addChild("left_leg_upper_1", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, -5.5F));
		modelPartData.addChild("left_leg_lower_1", lower_leg,
				ModelTransform.pivot(0.5F, 3.0F, 0.5F));
		modelPartData.addChild("left_leg_upper_2", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, -3.0F));
		modelPartData.addChild("left_leg_lower_2", lower_leg,
				ModelTransform.pivot(0.5F, 3.0F, 0.5F));
		modelPartData.addChild("left_leg_upper_3", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, 0.5F));
		modelPartData.addChild("left_leg_lower_3", lower_leg,
				ModelTransform.pivot(0.5F, 3.0F, 0.5F));
		modelPartData.addChild("left_leg_upper_4", upper_leg,
				ModelTransform.pivot(1.5F, 19.0F, 2.0F));
		modelPartData.addChild("left_leg_lower_4", lower_leg,
				ModelTransform.pivot(0.5F, 3.0F, 0.5F));

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
				this.rightLegLower1,
				this.rightLegLower2,
				this.rightLegLower3,
				this.rightLegLower4,
				this.leftLegUpper1,
				this.leftLegUpper2,
				this.leftLegUpper3,
				this.leftLegUpper4,
				this.leftLegLower1,
				this.leftLegLower2,
				this.leftLegLower3,
				this.leftLegLower4
		);
	}

	public void setAngles(T waterStriderEntity, float limbAngle, float limbDistance, float animationProgress, float headYaw, float headPitch) {
	this.head.pitch = 0F;
	this.head.yaw = 0F;
	this.rightAntenna.roll = 0.4F;
	this.leftAntenna.roll = 0.4F;
	this.rightLegUpper1.roll = this.rightLegUpper2.roll = this.rightLegUpper3.roll = this.rightLegUpper4.roll = upperLegRoll;
	this.rightLegLower1.roll = this.rightLegLower2.roll = this.rightLegLower3.roll = this.rightLegLower4.roll = lowerLegRoll;

		//this.rightLegUpper1.roll = 1.83F;
	}
}