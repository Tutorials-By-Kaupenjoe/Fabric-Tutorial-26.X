package net.kaupenjoe.tutorialmod.worldgen.tree;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

public class ModTrunkPlacerTypes {
    public static final TrunkPlacerType<SpiralTrunkPlacer> SPIRAL_TRUNK_PLACER =
            Registry.register(BuiltInRegistries.TRUNK_PLACER_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID,
                    "spiral_trunk_placer"), new TrunkPlacerType<>(SpiralTrunkPlacer.CODEC));

    public static void registerModTrunkPlacerTypes() {
        TutorialMod.LOGGER.info("Registering ModTrunkPlacerTypes for " + TutorialMod.MOD_ID);
    }
}
