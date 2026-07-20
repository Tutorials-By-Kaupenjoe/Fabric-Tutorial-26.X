package net.kaupenjoe.tutorialmod.compat.custom;

import me.shedaniel.math.Point;
import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.gui.Renderer;
import me.shedaniel.rei.api.client.gui.widgets.Widget;
import me.shedaniel.rei.api.client.gui.widgets.Widgets;
import me.shedaniel.rei.api.client.registry.display.DisplayCategory;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.compat.TutorialModREICommon;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

import java.util.LinkedList;
import java.util.List;

public class CrystallizerCategory implements DisplayCategory<Display> {
    public static final Identifier TEXTURE = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
            "textures/gui/crystallizer/crystallizer_gui.png");

    @Override
    public CategoryIdentifier<? extends Display> getCategoryIdentifier() {
        return TutorialModREICommon.CRYSTALLIZER;
    }

    @Override
    public Component getTitle() {
        return Component.translatable("block.tutorialmod.crystallizer");
    }

    @Override
    public Renderer getIcon() {
        return EntryStacks.of(ModBlocks.CRYSTALLIZER.asItem().getDefaultInstance());
    }

    @Override
    public List<Widget> setupDisplay(Display display, Rectangle bounds) {
        List<Widget> widgets = new LinkedList<>();
        Point startPoint = new Point(bounds.getCenterX() - 87, bounds.getCenterY() - 35);

        widgets.add(Widgets.createTexturedWidget(TEXTURE,
                new Rectangle(startPoint.x, startPoint.y, 175, 82)));

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 54, startPoint.y + 34))
                .entries(display.getInputEntries().getFirst()).markInput());

        widgets.add(Widgets.createSlot(new Point(startPoint.x + 104, startPoint.y + 34))
                .entries(display.getOutputEntries().getFirst()).markOutput());

        return widgets;
    }

    @Override
    public int getDisplayHeight() {
        return 90;
    }
}
