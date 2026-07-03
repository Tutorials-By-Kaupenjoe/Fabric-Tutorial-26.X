package net.kaupenjoe.tutorialmod.sound;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.sounds.SoundEvent;

public class ModSounds {
    public static final SoundEvent CHISEL_USE = registerSoundEvent("chisel_use");



    private static SoundEvent registerSoundEvent(String name) {
        Identifier id = Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name);
        return Registry.register(BuiltInRegistries.SOUND_EVENT, id, SoundEvent.createVariableRangeEvent(id));
    }

    public static void registerSounds() {
        TutorialMod.LOGGER.info("Registering sounds for " + TutorialMod.MOD_ID);
    }
}
