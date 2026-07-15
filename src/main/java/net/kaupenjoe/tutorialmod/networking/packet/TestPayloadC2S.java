package net.kaupenjoe.tutorialmod.networking.packet;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.network.protocol.common.custom.CustomPacketPayload;
import net.minecraft.resources.Identifier;

public record TestPayloadC2S(String name, int value) implements CustomPacketPayload {
    public static final Type<TestPayloadC2S> TYPE = new Type<>(
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "test_payload"));
    // CODEC ---> Codec & StreamCodec
    // Codec ==> Create a Java Object Instance from JSON File
    // And write to JSON

    // StreamCodec
    // Java Object ==> Turn it into Bytes for Network traffic
    // Turn Bytes into a new Object again!
    public static final StreamCodec<RegistryFriendlyByteBuf, TestPayloadC2S> STREAM_CODEC = StreamCodec.composite(
            ByteBufCodecs.STRING_UTF8,
            TestPayloadC2S::name,

            ByteBufCodecs.VAR_INT,
            TestPayloadC2S::value,

            TestPayloadC2S::new);


    @Override
    public Type<? extends CustomPacketPayload> type() {
        return TYPE;
    }
}
