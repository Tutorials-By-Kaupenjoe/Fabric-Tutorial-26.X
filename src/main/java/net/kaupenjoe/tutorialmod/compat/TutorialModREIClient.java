package net.kaupenjoe.tutorialmod.compat;

import me.shedaniel.math.Rectangle;
import me.shedaniel.rei.api.client.plugins.REIClientPlugin;
import me.shedaniel.rei.api.client.registry.category.CategoryRegistry;
import me.shedaniel.rei.api.client.registry.screen.ScreenRegistry;
import me.shedaniel.rei.api.common.util.EntryStacks;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.compat.custom.CrystallizerCategory;
import net.kaupenjoe.tutorialmod.menu.custom.CrystallizerScreen;

public class TutorialModREIClient implements REIClientPlugin {
    @Override
    public void registerCategories(CategoryRegistry registry) {
        registry.add(new CrystallizerCategory());

        registry.addWorkstations(TutorialModREICommon.CRYSTALLIZER, EntryStacks.of(ModBlocks.CRYSTALLIZER));
    }

    @Override
    public void registerScreens(ScreenRegistry registry) {
        registry.registerClickArea(screen -> new Rectangle(((screen.width - 176) / 2) + 78,
                        ((screen.height - 166) / 2) + 30, 20, 25),
                CrystallizerScreen.class, TutorialModREICommon.CRYSTALLIZER);
    }
}
