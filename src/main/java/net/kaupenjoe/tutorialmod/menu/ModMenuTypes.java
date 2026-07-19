package net.kaupenjoe.tutorialmod.menu;

import net.fabricmc.fabric.api.menu.v1.ExtendedMenuType;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.menu.custom.PedestalMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.inventory.MenuType;

public class ModMenuTypes {
    public static final MenuType<PedestalMenu> PEDESTAL_MENU =
            Registry.register(BuiltInRegistries.MENU, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "pedestal_menu"),
                    new ExtendedMenuType<>(PedestalMenu::new, BlockPos.STREAM_CODEC));


    public static void registerModMenuTypes() {
        TutorialMod.LOGGER.info("Registering ModMenuTypes for " + TutorialMod.MOD_ID);
    }
}
