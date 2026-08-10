package net.kaupenjoe.tutorialmod.worldgen.tree;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class ModFoliagePlacerTypes {
    public static final FoliagePlacerType<InvertedPyramidFoliagePlacer> INVERTED_PYRAMID_FOLIAGE_PLACER =
            Registry.register(BuiltInRegistries.FOLIAGE_PLACER_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
                    "inverted_pyramid_foliage_placer"), new FoliagePlacerType<>(InvertedPyramidFoliagePlacer.CODEC));

    public static void registerModFoliagePlacerTypes() {
        TutorialMod.LOGGER.info("Registering ModFoliagePlacerTypes for " + TutorialMod.MOD_ID);
    }
}
