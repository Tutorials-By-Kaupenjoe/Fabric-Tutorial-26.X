package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.CapybaraEntity;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;

public class CapybaraRenderer extends MobRenderer<CapybaraEntity, CapybaraRenderState, CapybaraModel> {
    public CapybaraRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel(context.bakeLayer(ModModelLayerLocations.CAPYBARA)), 0.45f);
    }

    @Override
    public Identifier getTextureLocation(CapybaraRenderState state) {
        return Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/capybara/capybara.png");
    }

    @Override
    public CapybaraRenderState createRenderState() {
        return new CapybaraRenderState();
    }

    @Override
    public void extractRenderState(CapybaraEntity entity, CapybaraRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
    }
}
