package net.kaupenjoe.tutorialmod.worldgen.biome;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.worldgen.biome.region.NetherRegion;
import net.kaupenjoe.tutorialmod.worldgen.biome.region.OverworldRegion;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import terrablender.api.EndBiomeRegistry;
import terrablender.api.Regions;

public class ModBiomes {
    public static final ResourceKey<Biome> KAUPEN_VALLEY = registerBiomeKey("kaupen_valley");
    public static final ResourceKey<Biome> GLOWSTONE_PLAINS = registerBiomeKey("glowstone_plains");
    public static final ResourceKey<Biome> END_ROT = registerBiomeKey("end_rot");

    public static void registerBiomes() {
        Regions.register(new OverworldRegion(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "tutorial_overworld"), 20));
        Regions.register(new NetherRegion(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "tutorial_nether"), 20));

        EndBiomeRegistry.registerHighlandsBiome(END_ROT, 20);
    }

    public static void bootstrap(BootstrapContext<Biome> context) {
        var carvers = context.lookup(Registries.CARVER);
        var placedFeatures = context.lookup(Registries.PLACED_FEATURE);

        register(context, KAUPEN_VALLEY, ModOverworldBiomes.kaupenValley(placedFeatures, carvers));
        register(context, GLOWSTONE_PLAINS, ModNetherBiomes.glowstonePlains(placedFeatures, carvers));
        register(context, END_ROT, ModEndBiomes.endRot(placedFeatures, carvers));
    }

    private static void register(BootstrapContext<Biome> context, ResourceKey<Biome> key, Biome biome) {
        context.register(key, biome);
    }

    private static ResourceKey<Biome> registerBiomeKey(String name) {
        return ResourceKey.create(Registries.BIOME, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
