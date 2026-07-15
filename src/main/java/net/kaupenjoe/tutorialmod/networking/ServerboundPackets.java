package net.kaupenjoe.tutorialmod.networking;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.kaupenjoe.tutorialmod.networking.packet.TestPayloadC2S;
import net.minecraft.world.entity.EntitySpawnReason;
import net.minecraft.world.entity.EntityTypes;

// ANYTHING IN THERE WE ARE ON THE SERVER
public class ServerboundPackets {
    public static void handleTestPayload(TestPayloadC2S testPayloadC2S, ServerPlayNetworking.Context context) {
        EntityTypes.COW.spawn(context.player().level(), context.player().getOnPos(), EntitySpawnReason.TRIGGERED);

    }
}
