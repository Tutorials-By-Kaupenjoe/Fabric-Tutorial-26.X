package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.CompostableRegistry;
import net.kaupenjoe.tutorialmod.item.ModItems;

public class ModCompostables {
    public static void registerCompostables() {
        CompostableRegistry.INSTANCE.add(ModItems.STRAWBERRY, 0.5f);
        CompostableRegistry.INSTANCE.add(ModItems.STRAWBERRY_SEEDS, 0.35f);
    }
}
