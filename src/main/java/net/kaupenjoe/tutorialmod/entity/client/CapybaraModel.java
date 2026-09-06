package net.kaupenjoe.tutorialmod.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class CapybaraModel extends EntityModel<CapybaraRenderState> {
    private final ModelPart Body;
    private final ModelPart Head;
    private final ModelPart EarL;
    private final ModelPart EarR;
    private final ModelPart LegBR;
    private final ModelPart LegBL;
    private final ModelPart LegFL;
    private final ModelPart LegFR;
    private final ModelPart Fur;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;

    public CapybaraModel(ModelPart root) {
        super(root);

        this.Body = root.getChild("Body");
        this.Head = this.Body.getChild("Head");
        this.EarL = this.Head.getChild("EarL");
        this.EarR = this.Head.getChild("EarR");
        this.LegBR = this.Body.getChild("LegBR");
        this.LegBL = this.Body.getChild("LegBL");
        this.LegFL = this.Body.getChild("LegFL");
        this.LegFR = this.Body.getChild("LegFR");
        this.Fur = this.Body.getChild("Fur");

        this.walkingAnimation = CapybaraAnimations.WALK.bake(root);
        this.idlingAnimation = CapybaraAnimations.IDLE.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition Body = partdefinition.addOrReplaceChild("Body", CubeListBuilder.create().texOffs(0, 0).addBox(-3.0F, -2.5F, -4.5F, 5.0F, 5.0F, 9.0F, new CubeDeformation(0.0F)), PartPose.offset(0.5F, 17.95F, -0.75F));

        PartDefinition Head = Body.addOrReplaceChild("Head", CubeListBuilder.create().texOffs(0, 14).addBox(-1.5F, -1.5F, -4.75F, 3.0F, 3.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.5F, -1.45F, -4.0F));

        PartDefinition EarL = Head.addOrReplaceChild("EarL", CubeListBuilder.create(), PartPose.offset(1.35F, -1.45F, -1.25F));

        PartDefinition EarL_r1 = EarL.addOrReplaceChild("EarL_r1", CubeListBuilder.create().texOffs(0, 1).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.25F, -0.35F, 0.0F, 0.0369F, -0.2062F, -0.1784F));

        PartDefinition EarR = Head.addOrReplaceChild("EarR", CubeListBuilder.create(), PartPose.offset(-1.35F, -1.45F, -1.25F));

        PartDefinition EarR_r1 = EarR.addOrReplaceChild("EarR_r1", CubeListBuilder.create().texOffs(0, 0).addBox(-0.5F, -0.5F, 0.0F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.25F, -0.35F, 0.0F, 0.0369F, 0.2062F, 0.1784F));

        PartDefinition LegBR = Body.addOrReplaceChild("LegBR", CubeListBuilder.create().texOffs(16, 14).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 2.55F, 2.95F));

        PartDefinition LegBL = Body.addOrReplaceChild("LegBL", CubeListBuilder.create().texOffs(0, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9F, 2.55F, 2.95F));

        PartDefinition LegFL = Body.addOrReplaceChild("LegFL", CubeListBuilder.create().texOffs(14, 20).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.9F, 2.55F, -2.35F));

        PartDefinition LegFR = Body.addOrReplaceChild("LegFR", CubeListBuilder.create().texOffs(19, 0).addBox(-1.0F, -0.5F, -1.0F, 2.0F, 4.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-1.9F, 2.55F, -2.35F));

        PartDefinition Fur = Body.addOrReplaceChild("Fur", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.2286F, -3.3268F, -3.0286F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-0.3714F, 0.7768F, -1.5214F));

        PartDefinition cube_r1 = Fur.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(12, 20).mirror().addBox(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.7286F, 3.3732F, 1.2714F, 0.0F, 0.0F, 0.0524F));

        PartDefinition cube_r2 = Fur.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(12, 20).mirror().addBox(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0286F, 2.9732F, 1.0714F, 0.0F, 0.0F, 0.3316F));

        PartDefinition cube_r3 = Fur.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(12, 20).mirror().addBox(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-2.0286F, 2.5732F, 1.8714F, 0.0F, 0.0F, 0.2269F));

        PartDefinition cube_r4 = Fur.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(12, 20).addBox(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7714F, 2.9732F, 1.2714F, 0.0F, 0.0F, -0.3316F));

        PartDefinition cube_r5 = Fur.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(12, 20).mirror().addBox(-1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-1.7286F, 3.7732F, 1.2714F, 0.0F, 0.0F, 0.1571F));

        PartDefinition cube_r6 = Fur.addOrReplaceChild("cube_r6", CubeListBuilder.create().texOffs(12, 20).addBox(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.7714F, 2.5732F, 1.7714F, 0.0F, 0.0F, -0.2269F));

        PartDefinition cube_r7 = Fur.addOrReplaceChild("cube_r7", CubeListBuilder.create().texOffs(12, 20).addBox(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4714F, 3.3732F, 1.2714F, 0.0F, 0.0F, -0.0524F));

        PartDefinition cube_r8 = Fur.addOrReplaceChild("cube_r8", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(-0.2286F, -3.3268F, -1.5286F, 0.0873F, 0.0F, 0.0F));

        PartDefinition cube_r9 = Fur.addOrReplaceChild("cube_r9", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5714F, -3.3268F, -1.5286F, 0.2618F, 0.0F, 0.0F));

        PartDefinition cube_r10 = Fur.addOrReplaceChild("cube_r10", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1714F, -3.3268F, -2.2286F, 0.2356F, 0.0F, 0.0F));

        PartDefinition cube_r11 = Fur.addOrReplaceChild("cube_r11", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.5714F, -3.3268F, -2.0286F, 0.1745F, 0.0F, 0.0F));

        PartDefinition cube_r12 = Fur.addOrReplaceChild("cube_r12", CubeListBuilder.create().texOffs(-2, 28).addBox(-2.0F, 0.0F, -1.0F, 4.0F, 0.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.1714F, -3.3268F, -1.7286F, 0.4102F, 0.0F, 0.0F));

        PartDefinition cube_r13 = Fur.addOrReplaceChild("cube_r13", CubeListBuilder.create().texOffs(12, 20).addBox(1.0F, -2.0F, -4.0F, 0.0F, 3.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(1.4714F, 3.7732F, 1.2714F, 0.0F, 0.0F, -0.1571F));

        return LayerDefinition.create(meshdefinition, 32, 32);
    }

    @Override
    public void setupAnim(CapybaraRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.Head.yRot = headYaw * ((float)Math.PI / 180f);
        this.Head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}
