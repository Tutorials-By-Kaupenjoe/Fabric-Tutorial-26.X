package net.kaupenjoe.tutorialmod.entity.client;

import com.google.common.collect.Maps;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.CapybaraEntity;
import net.kaupenjoe.tutorialmod.entity.variant.CapybaraVariant;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.Identifier;
import net.minecraft.util.Util;

import java.util.Map;

public class CapybaraRenderer extends MobRenderer<CapybaraEntity, CapybaraRenderState, CapybaraModel> {
    private static final Map<CapybaraVariant, Identifier> VARIANT_TO_TEXTURE =
            Util.make(Maps.newEnumMap(CapybaraVariant.class), map -> {
                map.put(CapybaraVariant.BROWN, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/capybara/capybara.png"));
                map.put(CapybaraVariant.SNOW, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/capybara/capybara_snow.png"));
            });

    public CapybaraRenderer(EntityRendererProvider.Context context) {
        super(context, new CapybaraModel(context.bakeLayer(ModModelLayerLocations.CAPYBARA)), 0.45f);
    }

    @Override
    public Identifier getTextureLocation(CapybaraRenderState state) {
        return VARIANT_TO_TEXTURE.get(state.variant); // Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/capybara/capybara.png");
    }

    @Override
    public CapybaraRenderState createRenderState() {
        return new CapybaraRenderState();
    }

    @Override
    public void extractRenderState(CapybaraEntity entity, CapybaraRenderState state, float partialTicks) {
        super.extractRenderState(entity, state, partialTicks);

        state.idleAnimationState.copyFrom(entity.idleAnimationState);
        state.variant = entity.getVariant();
    }
}
