package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.WarturtleEntity;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.inventory.InventoryScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class WarturtleScreen extends AbstractContainerScreen<WarturtleMenu> {
    private static final Identifier GUI_TEXTURE_T0 =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier0.png");
    private static final Identifier GUI_TEXTURE_T1 =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier1.png");
    private static final Identifier GUI_TEXTURE_T2 =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier2.png");
    private static final Identifier GUI_TEXTURE_T3 =
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,"textures/entity/warturtle/gui/warturtle_gui_tier3.png");
    private final WarturtleEntity warturtle;

    public WarturtleScreen(WarturtleMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
        this.warturtle = menu.warturtle;
    }

    @Override
    protected void init() {
        super.init();
        titleLabelX = 72;
        titleLabelY = 12;

        inventoryLabelX = 1000;
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float partialTick) {
        super.extractBackground(graphics, mouseX, mouseY, partialTick);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        if(!warturtle.hasTier1Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T0, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        } else if(warturtle.hasTier1Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T1, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }

        if(warturtle.hasTier2Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T2, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }
        if(warturtle.hasTier3Chest()) {
            graphics.blit(RenderPipelines.GUI_TEXTURED, GUI_TEXTURE_T3, x, y, 0, 0, imageWidth, imageHeight, 256, 256);
        }

        InventoryScreen.extractEntityInInventoryFollowsMouse(graphics, x + 8, y + 9, x + 60, y + 58, 20, 0.05F,
                mouseX, mouseY, this.warturtle);
    }
}
