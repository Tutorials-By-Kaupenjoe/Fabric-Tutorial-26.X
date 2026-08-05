package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.StrippableBlockRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;

public class ModStrippableBlocks {
    public static void registerStrippableBlocks() {
        StrippableBlockRegistry.register(ModBlocks.BALSA_LOG, ModBlocks.STRIPPED_BALSA_LOG);
        StrippableBlockRegistry.register(ModBlocks.BALSA_WOOD, ModBlocks.STRIPPED_BALSA_WOOD);
    }
}
