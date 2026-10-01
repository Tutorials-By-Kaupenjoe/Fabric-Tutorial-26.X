package net.kaupenjoe.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.WarturtleEntity;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.client.renderer.state.level.CameraRenderState;
import net.minecraft.resources.Identifier;

public class WarturtleRenderer extends MobRenderer<WarturtleEntity, WarturtleRenderState, WarturtleModel> {
    public WarturtleRenderer(EntityRendererProvider.Context context) {
        super(context, new WarturtleModel(context.bakeLayer(ModModelLayerLocations.WARTURTLE)), 0.9f);
    }

    @Override
    public Identifier getTextureLocation(WarturtleRenderState state) {
        return Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/warturtle.png");
    }

    @Override
    public void submit(WarturtleRenderState state, PoseStack poseStack, SubmitNodeCollector submitNodeCollector, CameraRenderState camera) {
        if(state.isBaby) {
            poseStack.scale(0.45f, 0.45f, 0.45f);
        } else {
            poseStack.scale(1f, 1f, 1f);
        }

        super.submit(state, poseStack, submitNodeCollector, camera);
    }

    @Override
    public WarturtleRenderState createRenderState() {
        return new WarturtleRenderState();
    }

    @Override
    public void extractRenderState(WarturtleEntity entity, WarturtleRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);
        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.sittingAnimationState.copyFrom(entity.sittingAnimationState);
        state.hidingAnimationState.copyFrom(entity.sittingTransitionAnimationState);
        state.emergeAnimationState.copyFrom(entity.standingTransitionAnimationState);
    }
}
