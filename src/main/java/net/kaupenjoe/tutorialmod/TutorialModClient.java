package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.kaupenjoe.tutorialmod.keymapping.ModKeyMappings;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.register();

        ClientTickEvents.END_CLIENT_TICK.register(TutorialModClient::onEndTick);
    }

    public static void onEndTick(Minecraft client) {
        // We are on the CLIENT here
        while(ModKeyMappings.KAUPEN_KEYMAPPING.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
        }
    }
}
