package net.kaupenjoe.tutorialmod.worldgen.biome.region;

import com.mojang.datafixers.util.Pair;
import net.kaupenjoe.tutorialmod.worldgen.biome.ModBiomes;
import net.minecraft.core.Registry;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.biome.Climate;
import terrablender.api.Region;
import terrablender.api.RegionType;

import java.util.function.Consumer;

public class NetherRegion extends Region {
    public NetherRegion(Identifier name, int weight) {
        super(name, RegionType.NETHER, weight);
    }

    @Override
    public void addBiomes(Registry<Biome> registry, Consumer<Pair<Climate.ParameterPoint, ResourceKey<Biome>>> mapper) {
        this.addBiome(mapper,
                Climate.Parameter.point(0f),
                Climate.Parameter.point(-0.25f),
                Climate.Parameter.point(0f),
                Climate.Parameter.point(0f),
                Climate.Parameter.point(0f),
                Climate.Parameter.point(0f),
                0f, ModBiomes.GLOWSTONE_PLAINS);
    }
}
