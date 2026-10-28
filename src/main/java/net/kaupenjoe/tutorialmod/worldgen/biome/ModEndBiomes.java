package net.kaupenjoe.tutorialmod.worldgen.biome;

import net.minecraft.core.HolderGetter;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.data.worldgen.placement.EndPlacements;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.attribute.AmbientParticle;
import net.minecraft.world.attribute.EnvironmentAttributes;
import net.minecraft.world.entity.EntityTypes;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.BiomeGenerationSettings;
import net.minecraft.world.level.biome.BiomeSpecialEffects;
import net.minecraft.world.level.biome.MobSpawnSettings;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.carver.WorldCarver;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

public class ModEndBiomes {
    public static Biome endRot(HolderGetter<PlacedFeature> placedFeatures, HolderGetter<WorldCarver> worldCarvers) {
        MobSpawnSettings.Builder mobBuilder = new MobSpawnSettings.Builder();
        mobBuilder.addSpawn(EntityTypes.ENDERMAN, 15, UniformInt.of(1, 3));

        BiomeGenerationSettings.Builder builder = new BiomeGenerationSettings.Builder(placedFeatures, worldCarvers)
                .addFeature(GenerationStep.Decoration.SURFACE_STRUCTURES, EndPlacements.END_SPIKE);

        return new Biome.BiomeBuilder()
                .hasPrecipitation(false).temperature(3f).downfall(0f)
                .mobSpawnSettings(mobBuilder.build())
                .generationSettings(builder.build())
                .specialEffects(new BiomeSpecialEffects.Builder().waterColor(0xabab11).build())
                .setAttribute(EnvironmentAttributes.AMBIENT_PARTICLES, AmbientParticle.of(ParticleTypes.END_ROD, 0.005f))
                .build();
    }
}
