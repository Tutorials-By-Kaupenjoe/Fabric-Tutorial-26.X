package net.kaupenjoe.tutorialmod.worldgen.biome;

import net.kaupenjoe.tutorialmod.worldgen.ModPlacedFeatures;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.BiomeDefaultFeatures;
import net.minecraft.data.worldgen.placement.AquaticPlacements;
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

public class ModOverworldBiomes {
    public static Biome kaupenValley(HolderGetter<PlacedFeature> placedFeatureGetter, HolderGetter<WorldCarver> carverGetter) {
        // Mob spawns
        MobSpawnSettings.Builder spawnBuilder = new MobSpawnSettings.Builder();
        BiomeDefaultFeatures.commonSpawns(spawnBuilder, 30);
        spawnBuilder.addSpawn(EntityTypes.ARMADILLO, 20, UniformInt.of(2, 3));
        spawnBuilder.addSpawn(EntityTypes.CAMEL, 20, UniformInt.of(1, 2));

        // Biome features
        BiomeGenerationSettings.Builder biomeBuilder = new BiomeGenerationSettings.Builder(placedFeatureGetter, carverGetter);
        BiomeDefaultFeatures.addDefaultCarversAndLakes(biomeBuilder);
        BiomeDefaultFeatures.addDefaultCrystalFormations(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMonsterRoom(biomeBuilder);
        BiomeDefaultFeatures.addDefaultUndergroundVariety(biomeBuilder);
        BiomeDefaultFeatures.addDefaultOres(biomeBuilder);
        BiomeDefaultFeatures.addDefaultMushrooms(biomeBuilder);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, VegetationPlacements.PATCH_SUGAR_CANE);
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, AquaticPlacements.SEAGRASS_SWAMP);

        // Custom Placed Feature
        biomeBuilder.addFeature(GenerationStep.Decoration.VEGETAL_DECORATION, ModPlacedFeatures.BALSA_PLACED_KEY);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(4.0F).downfall(0.0F)
                .specialEffects((new BiomeSpecialEffects.Builder().waterColor(0xbdb133)).build())
                .mobSpawnSettings(spawnBuilder.build()).generationSettings(biomeBuilder.build())
                .setAttribute(EnvironmentAttributes.FOG_COLOR, ARGB.vector3fFromRGB24(-13432824))
                .setAttribute(EnvironmentAttributes.BACKGROUND_MUSIC, new BackgroundMusic(SoundEvents.AMBIENT_NETHER_WASTES_LOOP))
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.GLOW, 0.00625F))
                .setAttribute(EnvironmentAttributes.AMBIENT_SOUNDS, new AmbientSounds(
                        Optional.of(SoundEvents.AMBIENT_NETHER_WASTES_LOOP),
                        Optional.of(new AmbientMoodSettings(SoundEvents.AMBIENT_BASALT_DELTAS_MOOD, 90000, 8, 2.0D)),
                        List.of(new AmbientAdditionsSettings(SoundEvents.AMBIENT_SOUL_SAND_VALLEY_LOOP, 0.0111))))
                .build();
    }
}
