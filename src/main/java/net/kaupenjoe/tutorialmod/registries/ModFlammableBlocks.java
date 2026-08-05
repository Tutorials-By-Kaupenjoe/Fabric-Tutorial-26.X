package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FlammableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;

public class ModFlammableBlocks {
    public static void registerFlammableBlocks() {
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_WOOD, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BALSA_LOG, 5, 5);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.STRIPPED_BALSA_WOOD, 5, 5);

        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_PLANKS, 5, 20);
        FlammableBlockRegistry.getDefaultInstance().add(ModBlocks.BALSA_LEAVES, 30, 60);
    }
}
