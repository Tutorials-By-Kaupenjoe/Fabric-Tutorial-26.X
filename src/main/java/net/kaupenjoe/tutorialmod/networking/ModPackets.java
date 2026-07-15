package net.kaupenjoe.tutorialmod.networking;

import net.fabricmc.fabric.api.networking.v1.PayloadTypeRegistry;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.network.RegistryFriendlyByteBuf;

public class ModPackets {
    private static void registerClientbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        // These Payloads are sent from Server to Client (S2C) --> CLIENTBOUND

    }

    private static void registerServerbound(PayloadTypeRegistry<RegistryFriendlyByteBuf> registry) {
        // These Payloads are sent from Client to Server (C2S) --> SERVERBOUND
        registry.register(TestPayloadC2S.TYPE, TestPayloadC2S.STREAM_CODEC);

        ServerPlayNetworking.registerGlobalReceiver(TestPayloadC2S.TYPE, ServerboundPackets::handleTestPayload);
    }

    public static void registerPackets() {
        registerClientbound(PayloadTypeRegistry.clientboundPlay());
        registerServerbound(PayloadTypeRegistry.serverboundPlay());
    }
}
