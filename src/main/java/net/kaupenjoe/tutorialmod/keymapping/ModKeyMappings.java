package net.kaupenjoe.tutorialmod.keymapping;

import com.mojang.blaze3d.platform.InputConstants;
import net.fabricmc.fabric.api.client.keymapping.v1.KeyMappingHelper;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.client.KeyMapping;
import org.lwjgl.glfw.GLFW;

public class ModKeyMappings {
    public static final KeyMapping KAUPEN_KEYMAPPING = KeyMappingHelper.registerKeyMapping(
            new KeyMapping("key.tutorialmod.kaupen_key",
                    InputConstants.Type.KEYSYM,
                    GLFW.GLFW_KEY_K, KeyMapping.Category.MISC));


    public static void register() {
        TutorialMod.LOGGER.info("Registering ModKeyMappings for " + TutorialMod.MOD_ID);
    }
}
