package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.entity.player.Inventory;

public class PedestalScreen extends AbstractContainerScreen<PedestalMenu> {
    private static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
            "textures/gui/pedestal/pedestal_gui.png");

    public PedestalScreen(PedestalMenu menu, Inventory inventory, Component title) {
        super(menu, inventory, title);
    }

    @Override
    public void extractBackground(GuiGraphicsExtractor graphics, int mouseX, int mouseY, float a) {
        super.extractBackground(graphics, mouseX, mouseY, a);
        int x = (width - imageWidth) / 2;
        int y = (height - imageHeight) / 2;

        graphics.blit(RenderPipelines.GUI_TEXTURED, TEXTURE, x, y, 0, 0,
                imageWidth, imageHeight, 256, 256);
    }
}
