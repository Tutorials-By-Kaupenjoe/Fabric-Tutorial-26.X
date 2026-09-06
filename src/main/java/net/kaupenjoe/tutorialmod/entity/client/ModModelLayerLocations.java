package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.resources.Identifier;

public class ModModelLayerLocations {
    public static final ModelLayerLocation CAPYBARA =
            new ModelLayerLocation(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "capybara"), "main");
}
