package net.kaupenjoe.tutorialmod.entity.client;

import net.minecraft.client.animation.KeyframeAnimation;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.util.Mth;

public class WarturtleModel extends EntityModel<WarturtleRenderState> {
    private final ModelPart body;
    private final ModelPart torso;
    private final ModelPart tier1;
    private final ModelPart tier2;
    private final ModelPart tier3;
    private final ModelPart head;

    private final KeyframeAnimation walkingAnimation;
    private final KeyframeAnimation idlingAnimation;
    private final KeyframeAnimation hidingAnimation;
    private final KeyframeAnimation emergeAnimation;
    private final KeyframeAnimation sittingAnimation;


    public WarturtleModel(ModelPart root) {
        super(root);

        this.body = root.getChild("body");
        this.torso = this.body.getChild("torso");
        this.head = this.torso.getChild("head");

        this.tier1 = this.torso.getChild("chests").getChild("tier1");
        this.tier2 = this.torso.getChild("chests").getChild("tier2");
        this.tier3 = this.torso.getChild("chests").getChild("tier3");

        this.walkingAnimation = WarturtleAnimations.WALK.bake(root);
        this.idlingAnimation = WarturtleAnimations.IDLE.bake(root);

        this.hidingAnimation = WarturtleAnimations.HIDE.bake(root);
        this.emergeAnimation = WarturtleAnimations.EMERGE.bake(root);
        this.sittingAnimation = WarturtleAnimations.SITTING.bake(root);
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition body = partdefinition.addOrReplaceChild("body", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition torso = body.addOrReplaceChild("torso", CubeListBuilder.create().texOffs(0, 31).addBox(-11.0F, -23.0F, -12.0F, 22.0F, 17.0F, 28.0F, new CubeDeformation(0.0F))
                .texOffs(3, 99).addBox(-9.0F, -26.0F, -8.0F, 18.0F, 3.0F, 21.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition blanky = torso.addOrReplaceChild("blanky", CubeListBuilder.create(), PartPose.offset(0.0F, -23.0F, 13.0F));

        PartDefinition blanky_r1 = blanky.addOrReplaceChild("blanky_r1", CubeListBuilder.create().texOffs(43, 97).addBox(-10.0F, -3.0F, -3.0F, 20.0F, 6.0F, 6.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, -2.0F, 2.0F, 0.0F, 3.1416F, 0.0F));

        PartDefinition chests = torso.addOrReplaceChild("chests", CubeListBuilder.create(), PartPose.offset(12.0F, -25.0F, 2.0F));

        PartDefinition tier1 = chests.addOrReplaceChild("tier1", CubeListBuilder.create(), PartPose.offset(-12.0F, 13.0F, -1.0F));

        PartDefinition chest_r1 = tier1.addOrReplaceChild("chest_r1", CubeListBuilder.create().texOffs(2, 27).mirror().addBox(-4.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-13.0F, 0.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition chest_r2 = tier1.addOrReplaceChild("chest_r2", CubeListBuilder.create().texOffs(2, 27).addBox(-2.0F, -3.0F, 0.0F, 6.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(13.0F, 0.0F, -1.0F, 0.0F, -1.5708F, 0.0F));

        PartDefinition tier2 = chests.addOrReplaceChild("tier2", CubeListBuilder.create(), PartPose.offset(-12.0F, 7.0F, 7.0F));

        PartDefinition chest_r3 = tier2.addOrReplaceChild("chest_r3", CubeListBuilder.create().texOffs(3, 47).mirror().addBox(-5.0F, -4.0F, 1.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.0F, 1.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition chest_r4 = tier2.addOrReplaceChild("chest_r4", CubeListBuilder.create().texOffs(3, 47).mirror().addBox(-5.0F, -4.0F, 1.0F, 8.0F, 8.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.0F, 1.0F, -1.0F, 0.0F, 1.5708F, 0.0F));

        PartDefinition tier3 = chests.addOrReplaceChild("tier3", CubeListBuilder.create(), PartPose.offset(-12.0F, 6.0F, -7.5F));

        PartDefinition chest_r5 = tier3.addOrReplaceChild("chest_r5", CubeListBuilder.create().texOffs(3, 37).mirror().addBox(-4.0F, -4.0F, 1.0F, 7.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(-15.0F, 1.0F, -0.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition chest_r6 = tier3.addOrReplaceChild("chest_r6", CubeListBuilder.create().texOffs(3, 37).mirror().addBox(-4.0F, -4.0F, 1.0F, 7.0F, 6.0F, 3.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(10.0F, 1.0F, -0.5F, 0.0F, 1.5708F, 0.0F));

        PartDefinition head = torso.addOrReplaceChild("head", CubeListBuilder.create().texOffs(22, 8).addBox(-4.5F, -6.5F, -12.0F, 9.0F, 10.0F, 13.0F, new CubeDeformation(0.0F))
                .texOffs(72, 31).addBox(-4.5F, -0.5F, -12.0F, 9.0F, 4.0F, 9.0F, new CubeDeformation(-0.001F)), PartPose.offset(0.0F, -12.5F, -12.0F));

        PartDefinition helmet = head.addOrReplaceChild("helmet", CubeListBuilder.create().texOffs(91, 67).addBox(-4.5F, 1.0F, -8.5F, 9.0F, 4.0F, 9.0F, new CubeDeformation(0.3F))
                .texOffs(72, 35).addBox(-0.5F, -1.6F, -5.5F, 1.0F, 2.0F, 3.0F, new CubeDeformation(0.3F))
                .texOffs(78, 79).addBox(4.8F, 5.3F, -9.2F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F))
                .texOffs(78, 79).mirror().addBox(-4.8F, 5.3F, -9.2F, 0.0F, 7.0F, 10.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(0.0F, -9.5F, -1.5F));

        PartDefinition frill_r1 = helmet.addOrReplaceChild("frill_r1", CubeListBuilder.create().texOffs(62, -13).mirror().addBox(0.0F, -11.5F, -12.0F, 0.0F, 12.0F, 21.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offsetAndRotation(0.05F, -2.45F, -3.8F, -0.1745F, 0.0F, 0.0F));

        PartDefinition frillsprike_r1 = helmet.addOrReplaceChild("frillsprike_r1", CubeListBuilder.create().texOffs(42, 116).addBox(-10.5F, -0.5F, -7.0F, 12.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(5.55F, -2.9F, -9.8F, -1.5708F, 1.3963F, -1.5708F));

        PartDefinition nosecap_r1 = helmet.addOrReplaceChild("nosecap_r1", CubeListBuilder.create().texOffs(104, 71).addBox(-4.5F, 0.0F, -3.75F, 9.0F, 0.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offsetAndRotation(0.0F, 2.751F, -8.75F, 0.0873F, 0.0F, 0.0F));

        PartDefinition mouth = head.addOrReplaceChild("mouth", CubeListBuilder.create().texOffs(67, 20).addBox(-4.5F, -3.0F, -7.0F, 9.0F, 4.0F, 7.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 2.5F, -5.0F));

        PartDefinition chests2 = torso.addOrReplaceChild("chests2", CubeListBuilder.create(), PartPose.offset(-12.0F, -25.0F, 2.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create(), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition legFL = legs.addOrReplaceChild("legFL", CubeListBuilder.create().texOffs(46, 76).mirror().addBox(-4.0F, -2.0F, -5.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(10.0F, -10.0F, -10.0F));

        PartDefinition legFR = legs.addOrReplaceChild("legFR", CubeListBuilder.create().texOffs(46, 76).addBox(-4.0F, -2.0F, -5.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(-10.0F, -10.0F, -10.0F));

        PartDefinition legBL = legs.addOrReplaceChild("legBL", CubeListBuilder.create().texOffs(0, 76).addBox(-5.0F, -2.0F, -2.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)), PartPose.offset(11.0F, -10.0F, 13.0F));

        PartDefinition legBR = legs.addOrReplaceChild("legBR", CubeListBuilder.create().texOffs(0, 76).mirror().addBox(-3.0F, -2.0F, -2.0F, 8.0F, 12.0F, 8.0F, new CubeDeformation(0.0F)).mirror(false), PartPose.offset(-11.0F, -10.0F, 13.0F));

        return LayerDefinition.create(meshdefinition, 128, 128);
    }

    @Override
    public void setupAnim(WarturtleRenderState state) {
        this.root().getAllParts().forEach(ModelPart::resetPose);
        this.applyHeadRotation(state.yRot, state.xRot);

        this.walkingAnimation.applyWalk(state.walkAnimationPos, state.walkAnimationSpeed, 2f, 2.5f);
        this.idlingAnimation.apply(state.idleAnimationState, state.ageInTicks, 1f);

        hidingAnimation.apply(state.hidingAnimationState, state.ageInTicks);
        emergeAnimation.apply(state.emergeAnimationState, state.ageInTicks);
        sittingAnimation.apply(state.sittingAnimationState, state.ageInTicks);

        tier1.visible = state.hasChestTier1;
        tier2.visible = state.hasChestTier2;
        tier3.visible = state.hasChestTier3;
    }

    private void applyHeadRotation(float headYaw, float headPitch) {
        headYaw = Mth.clamp(headYaw, -30f, 30f);
        headPitch = Mth.clamp(headPitch, -25f, 45);

        this.head.yRot = headYaw * ((float)Math.PI / 180f);
        this.head.xRot = headPitch *  ((float)Math.PI / 180f);
    }
}