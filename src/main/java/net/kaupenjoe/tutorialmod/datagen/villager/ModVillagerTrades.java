package net.kaupenjoe.tutorialmod.datagen.villager;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderSet;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.item.trading.TradeCost;
import net.minecraft.world.item.trading.VillagerTrade;
import net.minecraft.world.item.trading.VillagerTrades;

import java.util.List;
import java.util.Optional;

public class ModVillagerTrades {
    public static final ResourceKey<VillagerTrade> FARMER_1_EMERALD_STRAWBERRY = createKey("farmer/1/emerald_strawberry");
    public static final ResourceKey<VillagerTrade> FARMER_1_DIAMOND_STRAWBERRY_SEEDS = createKey("farmer/1/diamond_strawberry_seeds");

    public static final ResourceKey<VillagerTrade> FARMER_2_EMERALD_HONEY_BERRIES = createKey("farmer/2/emerald_honey_berries");

    public static final ResourceKey<VillagerTrade> MASON_1_FLUORITE_CHISEL = createKey("mason/1/fluorite_chisel");
    public static final ResourceKey<VillagerTrade> LIBRARIAN_1_FLUORITE_ENCHANTED_BOOK = createKey("librarian/1/fluorite_enchanted_book");


    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_FLUORITE = createKey("kaupenger/1/emerald_fluorite");
    public static final ResourceKey<VillagerTrade> KAUPENGER_1_EMERALD_RAW_FLUORITE = createKey("kaupenger/1/emerald_raw_fluorite");

    public static final ResourceKey<VillagerTrade> KAUPENGER_2_EMERALD_PEDESTAL = createKey("kaupenger/2/emerald_pedestal");
    public static final ResourceKey<VillagerTrade> KAUPENGER_2_FLUORITE_SPECTRE_STAFF = createKey("kaupenger/2/fluorite_spectre_staff");



    public static void bootstrap(BootstrapContext<VillagerTrade> context) {
        var items = context.lookup(Registries.ITEM);
        var enchantments = context.lookup(Registries.ENCHANTMENT);

        context.register(FARMER_1_EMERALD_STRAWBERRY, new VillagerTrade(
                new TradeCost(Items.EMERALD, 4),
                new ItemStackTemplate(ModItems.STRAWBERRY),
                12, 8, 0.05f,
                Optional.empty(), List.of()));
        context.register(FARMER_1_DIAMOND_STRAWBERRY_SEEDS, new VillagerTrade(
                new TradeCost(Items.DIAMOND, 12),
                new ItemStackTemplate(ModItems.STRAWBERRY_SEEDS),
                12, 10, 0.05f,
                Optional.empty(), List.of()));

        context.register(FARMER_2_EMERALD_HONEY_BERRIES, new VillagerTrade(
                new TradeCost(Items.EMERALD, 16),
                new ItemStackTemplate(ModItems.HONEY_BERRIES),
                12, 10, 0.05f,
                Optional.empty(), List.of()));


        context.register(MASON_1_FLUORITE_CHISEL, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 6),
                new ItemStackTemplate(ModItems.CHISEL),
                2, 19, 0.05f,
                Optional.empty(), List.of()));

        context.register(LIBRARIAN_1_FLUORITE_ENCHANTED_BOOK, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 32),
                new ItemStackTemplate(Items.ENCHANTED_BOOK),
                12, 6, 0.05f,
                Optional.empty(),
                VillagerTrades.enchantedBook(items,
                        HolderSet.direct(enchantments.getOrThrow(Enchantments.INFINITY),
                                enchantments.getOrThrow(Enchantments.MULTISHOT)))));


        context.register(KAUPENGER_1_EMERALD_FLUORITE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 6),
                new ItemStackTemplate(ModItems.FLUORITE, 4),
                12, 19, 0.05f,
                Optional.empty(), List.of()));
        context.register(KAUPENGER_1_EMERALD_RAW_FLUORITE, new VillagerTrade(
                new TradeCost(Items.EMERALD, 5),
                new ItemStackTemplate(ModItems.RAW_FLUORITE, 12),
                12, 23, 0.05f,
                Optional.empty(), List.of()));

        context.register(KAUPENGER_2_EMERALD_PEDESTAL, new VillagerTrade(
                new TradeCost(Items.EMERALD, 24),
                new ItemStackTemplate(ModBlocks.PEDESTAL_BLOCK.asItem()),
                12, 24, 0.05f,
                Optional.empty(), List.of()));
        context.register(KAUPENGER_2_FLUORITE_SPECTRE_STAFF, new VillagerTrade(
                new TradeCost(ModItems.FLUORITE, 19),
                new ItemStackTemplate(ModItems.SPECTRE_STAFF),
                2, 19, 0.05f,
                Optional.empty(), List.of()));
    }


    private static ResourceKey<VillagerTrade> createKey(String name) {
        return ResourceKey.create(Registries.VILLAGER_TRADE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name));
    }
}
