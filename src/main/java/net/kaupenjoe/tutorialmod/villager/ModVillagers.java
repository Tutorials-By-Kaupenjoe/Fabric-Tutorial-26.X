package net.kaupenjoe.tutorialmod.villager;

import com.google.common.collect.ImmutableSet;
import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
import net.fabricmc.fabric.api.object.builder.v1.world.poi.PoiHelper;
import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.datagen.villager.ModTradeSets;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.entity.ai.village.poi.PoiType;
import net.minecraft.world.entity.npc.villager.VillagerProfession;
import net.minecraft.world.item.trading.TradeSet;

public class ModVillagers {
    public static final ResourceKey<PoiType> KAUPEN_POI_KEY = ResourceKey.create(Registries.POINT_OF_INTEREST_TYPE,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "kaupen_poi"));
    public static final PoiType KAUPEN_POI = PoiHelper.register(Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "kaupen_poi"),
            1, 1, ModBlocks.MAGIC_BLOCK);

    public static final VillagerProfession KAUPENGER = registerVillagerProfession("kaupenger", "Kaupenger", KAUPEN_POI_KEY,
            SoundEvents.AMETHYST_CLUSTER_PLACE, Int2ObjectMap.ofEntries(
                    Int2ObjectMap.entry(1, ModTradeSets.KAUPENGER_LEVEL_1),
                    Int2ObjectMap.entry(2, ModTradeSets.KAUPENGER_LEVEL_2)
            ));


    private static VillagerProfession registerVillagerProfession(String name, String title, ResourceKey<PoiType> poi,
                                                                 SoundEvent sound, Int2ObjectMap<ResourceKey<TradeSet>> map) {
        return Registry.register(BuiltInRegistries.VILLAGER_PROFESSION, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new VillagerProfession(
                Component.literal(title), holder -> holder.is(poi), holder -> holder.is(poi),
                ImmutableSet.of(), ImmutableSet.of(), sound, map));
    }

    public static void register() {
        TutorialMod.LOGGER.info("Registering ModVillagers for " + TutorialMod.MOD_ID);
    }
}
