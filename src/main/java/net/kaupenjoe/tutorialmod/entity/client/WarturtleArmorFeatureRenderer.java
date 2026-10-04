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
import net.minecraft.world.item.DyeColor;
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

    private static final Identifier[] DYE_LOCATION = new Identifier[] {
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/white.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/orange.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/magenta.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/light_blue.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/yellow.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/lime.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/pink.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/gray.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/light_gray.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/cyan.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/purple.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/blue.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/brown.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/green.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/red.png"),
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "textures/entity/warturtle/armor/blankies/black.png")
    };

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

            if(state.dyeColor != null) {
                DyeColor dyecolor = state.dyeColor;
                Identifier identifier = DYE_LOCATION[dyecolor.getId()];
                submitNodeCollector.order(2).submitModel(this.model, state, poseStack, RenderTypes.entityCutout(identifier),
                        lightCoords, OverlayTexture.NO_OVERLAY, 0);
            }
        }
    }
}
