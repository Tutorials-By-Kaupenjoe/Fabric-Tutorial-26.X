package net.kaupenjoe.tutorialmod.compat;

import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.DisplaySerializerRegistry;
import me.shedaniel.rei.api.common.plugins.REICommonPlugin;
import me.shedaniel.rei.api.common.registry.display.ServerDisplayRegistry;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.compat.custom.CrystallizerDisplay;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;

public class TutorialModREICommon implements REICommonPlugin {
    public static final CategoryIdentifier<CrystallizerDisplay> CRYSTALLIZER =
            CategoryIdentifier.of(TutorialMod.MOD_ID, "crystallizer");

    @Override
    public void registerDisplaySerializer(DisplaySerializerRegistry registry) {
        registry.register(CRYSTALLIZER.getIdentifier(), CrystallizerDisplay.SERIALIZER);
    }

    @Override
    public void registerDisplays(ServerDisplayRegistry registry) {
        registry.beginRecipeFiller(CrystallizerRecipe.class).fill(CrystallizerDisplay::new);
    }
}
