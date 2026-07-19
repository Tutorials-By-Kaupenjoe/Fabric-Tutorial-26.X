package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.block.entity.renderer.PedestalBlockEntityRenderer;
import net.kaupenjoe.tutorialmod.keymapping.ModKeyMappings;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.kaupenjoe.tutorialmod.menu.custom.PedestalScreen;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.blockentity.BlockEntityRenderers;
import net.minecraft.network.chat.Component;

public class TutorialModClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        ModKeyMappings.register();

        BlockEntityRenderers.register(ModBlockEntities.PEDESTAL_BE, PedestalBlockEntityRenderer::new);

        ClientTickEvents.END_CLIENT_TICK.register(TutorialModClient::onEndTick);

        MenuScreens.register(ModMenuTypes.PEDESTAL_MENU, PedestalScreen::new);
    }

    public static void onEndTick(Minecraft client) {
        // We are on the CLIENT here
        while(ModKeyMappings.KAUPEN_KEYMAPPING.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
            ClientPlayNetworking.send(new TestPayloadC2S("Kaupenjoe", 42));
        }
    }
}
