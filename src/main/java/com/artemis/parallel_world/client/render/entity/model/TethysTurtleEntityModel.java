package com.artemis.parallel_world.client.render.entity.model;

import com.artemis.parallel_world.entity.TethysTurtleEntity;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Iterables;
import net.minecraft.client.model.*;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.entity.model.QuadrupedEntityModel;
import net.minecraft.client.render.entity.model.TurtleEntityModel;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.passive.TurtleEntity;
import net.minecraft.util.math.MathHelper;


public class TethysTurtleEntityModel<T extends TethysTurtleEntity> extends QuadrupedEntityModel<T> {

    private final ModelPart plastron;

    public TethysTurtleEntityModel(ModelPart root) {
        super(root, true, 120.0F, 0.0F, 9.0F, 6.0F, 120);
        this.plastron = root.getChild("egg_belly");
    }

    public static TexturedModelData getTexturedModelData() {
        ModelData modelData = new ModelData();
        ModelPartData modelPartData = modelData.getRoot();
        modelPartData.addChild("head", ModelPartBuilder.create().uv(3, 0).cuboid(-3.0F, -1.0F, -3.0F, 6.0F, 5.0F, 6.0F), ModelTransform.pivot(0.0F, 19.0F, -10.0F));
        modelPartData.addChild("body", ModelPartBuilder.create().uv(7, 37).cuboid("shell", -9.5F, 3.0F, -10.0F, 19.0F, 20.0F, 6.0F).uv(31, 1).cuboid("belly", -5.5F, 3.0F, -13.0F, 11.0F, 18.0F, 3.0F), ModelTransform.of(0.0F, 11.0F, -10.0F, 1.5707964F, 0.0F, 0.0F));
        modelPartData.addChild("egg_belly", ModelPartBuilder.create().uv(70, 33).cuboid(-4.5F, 3.0F, -14.0F, 9.0F, 18.0F, 1.0F), ModelTransform.of(0.0F, 11.0F, -10.0F, 1.5707964F, 0.0F, 0.0F));
        boolean i = true;
        modelPartData.addChild("right_hind_leg", ModelPartBuilder.create().uv(1, 23).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F), ModelTransform.pivot(-3.5F, 22.0F, 11.0F));
        modelPartData.addChild("left_hind_leg", ModelPartBuilder.create().uv(1, 12).cuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F), ModelTransform.pivot(3.5F, 22.0F, 11.0F));
        modelPartData.addChild("right_front_leg", ModelPartBuilder.create().uv(27, 30).cuboid(-13.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F), ModelTransform.pivot(-5.0F, 21.0F, -4.0F));
        modelPartData.addChild("left_front_leg", ModelPartBuilder.create().uv(27, 24).cuboid(0.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F), ModelTransform.pivot(5.0F, 21.0F, -4.0F));
        return TexturedModelData.of(modelData, 128, 64);
    }

    protected Iterable<ModelPart> getBodyParts() {
        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.plastron));
    }

    public void setAngles(T turtleEntity, float f, float g, float h, float i, float j) {
        super.setAngles(turtleEntity, f, g, h, i, j);
        this.rightHindLeg.pitch = MathHelper.cos(f * 0.666F * 0.6F) * 0.5F * g;
        this.leftHindLeg.pitch = MathHelper.cos(f * 0.666F * 0.6F + 3.14F) * 0.5F * g;
        this.rightFrontLeg.roll = MathHelper.cos(f * 0.666F * 0.6F + 3.14F) * 0.5F * g;
        this.leftFrontLeg.roll = MathHelper.cos(f * 0.666F * 0.6F) * 0.5F * g;
        this.rightFrontLeg.pitch = 0.0F;
        this.leftFrontLeg.pitch = 0.0F;
        this.rightFrontLeg.yaw = 0.0F;
        this.leftFrontLeg.yaw = 0.0F;
        this.rightHindLeg.yaw = 0.0F;
        this.leftHindLeg.yaw = 0.0F;
        if (!turtleEntity.isTouchingWater() && turtleEntity.isOnGround()) {
            float k = turtleEntity.isDiggingSand() ? 4.0F : 1.0F;
            float l = turtleEntity.isDiggingSand() ? 2.0F : 1.0F;
            float m = 5.0F;
            this.rightFrontLeg.yaw = MathHelper.cos(k * f * 5.0F + 3.14F) * 8.0F * g * l;
            this.rightFrontLeg.roll = 0.0F;
            this.leftFrontLeg.yaw = MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
            this.leftFrontLeg.roll = 0.0F;
            this.rightHindLeg.yaw = MathHelper.cos(f * 5.0F + 3.14F) * 3.0F * g;
            this.rightHindLeg.pitch = 0.0F;
            this.leftHindLeg.yaw = MathHelper.cos(f * 5.0F) * 3.0F * g;
            this.leftHindLeg.pitch = 0.0F;
        }

        this.plastron.visible = !this.child && turtleEntity.hasEgg();
    }

    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
        boolean bl = this.plastron.visible;
        if (bl) {
            matrices.push();
            matrices.translate(0.0D, -0.08D, 0.0D);
        }

        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
        if (bl) {
            matrices.pop();
        }

    }
}

//    /**
//     * The belly side of the turtle's shell.
//     */
//    private ModelPart plastron;
//
//    public TethysTurtleEntityModel(float scale) {
//        super(12, scale, true, 120.0F, 0.0F, 9.0F, 6.0F, 120);
//        this.textureWidth = 128;
//        this.textureHeight = 64;
//        this.head = new ModelPart(this, 3, 0);
//        this.head.addCuboid(-3.0F, -1.0F, -3.0F, 6.0F, 5.0F, 6.0F, 0.0F);
//        this.head.setPivot(0.0F, 19.0F, -10.0F);
//        this.torso = new ModelPart(this);
//        this.torso.setTextureOffset(7, 37).addCuboid(-9.5F, 3.0F, -10.0F, 19.0F, 20.0F, 6.0F, 0.0F);
//        this.torso.setTextureOffset(31, 1).addCuboid(-5.5F, 3.0F, -13.0F, 11.0F, 18.0F, 3.0F, 0.0F);
//        this.torso.setPivot(0.0F, 11.0F, -10.0F);
//        this.plastron = new ModelPart(this);
//        this.plastron.setTextureOffset(70, 33).addCuboid(-4.5F, 3.0F, -14.0F, 9.0F, 18.0F, 1.0F, 0.0F);
//        this.plastron.setPivot(0.0F, 11.0F, -10.0F);
//        boolean i = true;
//        this.backRightLeg = new ModelPart(this, 1, 23);
//        this.backRightLeg.addCuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
//        this.backRightLeg.setPivot(-3.5F, 22.0F, 11.0F);
//        this.backLeftLeg = new ModelPart(this, 1, 12);
//        this.backLeftLeg.addCuboid(-2.0F, 0.0F, 0.0F, 4.0F, 1.0F, 10.0F, 0.0F);
//        this.backLeftLeg.setPivot(3.5F, 22.0F, 11.0F);
//        this.frontRightLeg = new ModelPart(this, 27, 30);
//        this.frontRightLeg.addCuboid(-13.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
//        this.frontRightLeg.setPivot(-5.0F, 21.0F, -4.0F);
//        this.frontLeftLeg = new ModelPart(this, 27, 24);
//        this.frontLeftLeg.addCuboid(0.0F, 0.0F, -2.0F, 13.0F, 1.0F, 5.0F, 0.0F);
//        this.frontLeftLeg.setPivot(5.0F, 21.0F, -4.0F);
//    }
//
//    public TethysTurtleEntityModel(int legHeight, float scale, boolean headScaled, float childHeadYOffset, float childHeadZOffset, float invertedChildHeadScale, float invertedChildBodyScale, int childBodyYOffset) {
//        super(legHeight, scale, headScaled, childHeadYOffset, childHeadZOffset, invertedChildHeadScale, invertedChildBodyScale, childBodyYOffset);
//    }
//
//    protected Iterable<ModelPart> getBodyParts() {
//        return Iterables.concat(super.getBodyParts(), ImmutableList.of(this.plastron));
//    }
//
//    public void setAngles(T tethysTurtleEntity, float f, float g, float h, float i, float j) {
//        super.setAngles(tethysTurtleEntity, f, g, h, i, j);
//        this.backRightLeg.pitch = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
//        this.backLeftLeg.pitch = MathHelper.cos(f * 0.6662F * 0.6F + 3.1415927F) * 0.5F * g;
//        this.frontRightLeg.roll = MathHelper.cos(f * 0.6662F * 0.6F + 3.1415927F) * 0.5F * g;
//        this.frontLeftLeg.roll = MathHelper.cos(f * 0.6662F * 0.6F) * 0.5F * g;
//        this.frontRightLeg.pitch = 0.0F;
//        this.frontLeftLeg.pitch = 0.0F;
//        this.frontRightLeg.yaw = 0.0F;
//        this.frontLeftLeg.yaw = 0.0F;
//        this.backRightLeg.yaw = 0.0F;
//        this.backLeftLeg.yaw = 0.0F;
//        this.plastron.pitch = 1.5707964F;
//        if (!tethysTurtleEntity.isTouchingWater() && tethysTurtleEntity.isOnGround()) {
//            float k = tethysTurtleEntity.isDiggingSand() ? 4.0F : 1.0F;
//            float l = tethysTurtleEntity.isDiggingSand() ? 2.0F : 1.0F;
//            float m = 5.0F;
//            this.frontRightLeg.yaw = MathHelper.cos(k * f * 5.0F + 3.1415927F) * 8.0F * g * l;
//            this.frontRightLeg.roll = 0.0F;
//            this.frontLeftLeg.yaw = MathHelper.cos(k * f * 5.0F) * 8.0F * g * l;
//            this.frontLeftLeg.roll = 0.0F;
//            this.backRightLeg.yaw = MathHelper.cos(f * 5.0F + 3.1415927F) * 3.0F * g;
//            this.backRightLeg.pitch = 0.0F;
//            this.backLeftLeg.yaw = MathHelper.cos(f * 5.0F) * 3.0F * g;
//            this.backLeftLeg.pitch = 0.0F;
//        }
//
//        this.plastron.visible = !this.child && tethysTurtleEntity.hasEgg();
//    }
//
//    public void render(MatrixStack matrices, VertexConsumer vertices, int light, int overlay, float red, float green, float blue, float alpha) {
//        boolean bl = this.plastron.visible;
//        if (bl) {
//            matrices.push();
//            matrices.translate(0.0D, -0.07999999821186066D, 0.0D);
//        }
//
//        super.render(matrices, vertices, light, overlay, red, green, blue, alpha);
//        if (bl) {
//            matrices.pop();
//        }
//
//    }
//}
