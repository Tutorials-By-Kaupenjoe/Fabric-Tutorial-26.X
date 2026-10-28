package net.kaupenjoe.tutorialmod.worldgen.biome;

import net.minecraft.core.RegistryAccess;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.material.MaterialRules;
import net.minecraft.world.level.levelgen.material.condition.MaterialCondition;
import net.minecraft.world.level.levelgen.material.rule.MaterialRule;
import net.minecraft.world.level.levelgen.placement.CaveSurface;

public class ModMaterialRules {
    private static final MaterialRule DIRT = makeStateRule(Blocks.DIRT);
    private static final MaterialRule GRASS_BLOCK = makeStateRule(Blocks.GRASS_BLOCK);
    private static final MaterialRule RED_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.red());
    private static final MaterialRule BLUE_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.blue());
    private static final MaterialRule GREEN_TERRACOTTA = makeStateRule(Blocks.DYED_TERRACOTTA.green());

    private static final MaterialRule OBSIDIAN = makeStateRule(Blocks.OBSIDIAN);
    private static final MaterialRule END_STONE = makeStateRule(Blocks.END_STONE);

    private static final MaterialRule GLOWSTONE = makeStateRule(Blocks.GLOWSTONE);
    private static final MaterialRule NETHERRACK = makeStateRule(Blocks.NETHERRACK);
    private static final MaterialRule BEDROCK = makeStateRule(Blocks.BEDROCK);

    private static final MaterialCondition ON_FLOOR = MaterialRules.stoneDepthCheck(0, false, CaveSurface.FLOOR);
    private static final MaterialCondition UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, CaveSurface.FLOOR);
    private static final MaterialCondition DEEP_UNDER_FLOOR = MaterialRules.stoneDepthCheck(0, true, 6, CaveSurface.FLOOR);
    private static final MaterialCondition UNDER_CEILING = MaterialRules.stoneDepthCheck(0, true, CaveSurface.CEILING);



    public static MaterialRule makeModOverworldRules(RegistryAccess registryAccess) {
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.isBiome(registryAccess.lookupOrThrow(Registries.BIOME), ModBiomes.KAUPEN_VALLEY),
                        MaterialRules.sequence(MaterialRules.ifTrue(UNDER_FLOOR, RED_TERRACOTTA), BLUE_TERRACOTTA)),
                // Default to green terracotta
                MaterialRules.ifTrue(ON_FLOOR, GREEN_TERRACOTTA)
        );
    }

    public static MaterialRule makeModNetherRules(RegistryAccess registryAccess) {
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.verticalGradient("bedrock_floor", VerticalAnchor.bottom(), VerticalAnchor.aboveBottom(5)), BEDROCK),
                MaterialRules.ifTrue(MaterialRules.not(MaterialRules.verticalGradient("bedrock_roof", VerticalAnchor.belowTop(5), VerticalAnchor.top())), BEDROCK),

                // Then apply biome-specific rules
                MaterialRules.ifTrue(
                        MaterialRules.isBiome(registryAccess.lookupOrThrow(Registries.BIOME), ModBiomes.GLOWSTONE_PLAINS),
                        MaterialRules.sequence(
                                // Obsidian on the undersides of ceilings
                                MaterialRules.ifTrue(UNDER_CEILING, OBSIDIAN),
                                // Obsidian on the undersides of floors (though less common in Nether caves)
                                MaterialRules.ifTrue(UNDER_FLOOR, GLOWSTONE),
                                MaterialRules.ifTrue(DEEP_UNDER_FLOOR, OBSIDIAN),
                                // Default to glowstone if not under a ceiling or floor
                                NETHERRACK))
        );
    }

    public static MaterialRule makeModEndRules(RegistryAccess registryAccess) {
        return MaterialRules.sequence(
                MaterialRules.ifTrue(MaterialRules.isBiome(registryAccess.lookupOrThrow(Registries.BIOME), ModBiomes.END_ROT), OBSIDIAN),
                // Default to end stone
                MaterialRules.ifTrue(ON_FLOOR, END_STONE)
        );
    }


    private static MaterialRule makeStateRule(Block block) {
        return MaterialRules.state(block.defaultBlockState());
    }
}
