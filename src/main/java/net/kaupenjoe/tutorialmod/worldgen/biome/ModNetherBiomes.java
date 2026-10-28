package net.kaupenjoe.tutorialmod.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.NetherPlacements;
import net.minecraft.data.worldgen.placement.OrePlacements;
import net.minecraft.data.worldgen.placement.VegetationPlacements;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.util.ARGB;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.*;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

import java.util.List;
import java.util.Optional;

public class ModNetherBiomes {
    public static Biome glowstonePlains(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<WorldCarver> carverGetter) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        spawnBuilder.addSpawn(EntityTypes.GHAST, 10, UniformInt.of(1, 1));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE_EXTRA)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, NetherPlacements.GLOWSTONE)
                .addFeature(GenerationStep.Decoration.UNDERGROUND_DECORATION, OrePlacements.ORE_MAGMA)
                .addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.RED_MUSHROOM_NETHER);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0xbab111)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, ARGB.vector3fFromRGB24(-1262841))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.AMBIENT_NETHER_WASTES_LOOP))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.GLOW, 0.0065f))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                        Optional.of(SoundEvents.AMBIENT_NETHER_WASTES_LOOP),
                        Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 75000, 7, 2.0D)),
                        List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP, 0.015))))
                .build();
    }
}
