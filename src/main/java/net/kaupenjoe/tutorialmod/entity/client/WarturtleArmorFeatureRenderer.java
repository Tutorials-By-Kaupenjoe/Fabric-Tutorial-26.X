package net.kaupenjoe.tutorialmod.entity.client;

import com.mojang.blaze3d.vertex.PoseStack;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.client.model.geom.EntityModelSet;
import net.minecraft.client.renderer.SubmitNodeCollector;
import net.minecraft.client.renderer.entity.RenderLayerParent;
import net.minecraft.client.renderer.entity.layers.RenderLayer;
import net.minecraft.client.renderer.rendertype.RenderTypes;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.Map;

public class WarturtleArmorFeatureRenderer extends RenderLayer<WarturtleRenderState, WarturtleModel> {
    private final WarturtleModel model;
    private final Map<Item, Identifier> ARMOR_MAP = Map.of(
            ModItems.IRON_WARTURTLE_ARMOR, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/iron_warturtle.png"),
            ModItems.GOLD_WARTURTLE_ARMOR, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/gold_warturtle.png"),
            ModItems.DIAMOND_WARTURTLE_ARMOR, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/diamond_warturtle.png"),
            ModItems.NETHERITE_WARTURTLE_ARMOR, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/netherite_warturtle.png"),
            ModItems.FLUORITE_WARTURTLE_ARMOR, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/fluorite_warturtle.png")
    );

    public WarturtleArmorFeatureRenderer(RenderLayerParent<WarturtleRenderState, WarturtleModel> renderer, EntityModelSet modelSet) {
        super(renderer);
        model = new WarturtleModel(modelSet.bakeLayer(ModModelLayerLocations.WARTURTLE_ARMOR));
    }

    @Override
    public void submit(PoseStack poseStack, SubmitNodeCollector submitNodeCollector, int lightCoords, WarturtleRenderState state, float yRot, float xRot) {
        if(state.bodyArmorItem != ItemStack.EMPTY) {
            ItemStack stack = state.bodyArmorItem;

            this.model.setupAnim(state);
            submitNodeCollector.order(1).submitModel(this.model, state, poseStack, RenderTypes.entityCutout(ARMOR_MAP.get(stack.getItem())),
                    lightCoords, OverlayTexture.NO_OVERLAY, -1, null, state.outlineColor);
        }
    }
}
