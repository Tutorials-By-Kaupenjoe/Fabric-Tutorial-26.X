package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        dropSelf(ModBlocks.FLUORITE_BLOCK);
        dropSelf(ModBlocks.RAW_FLUORITE_BLOCK);

        add(ModBlocks.FLUORITE_ORE, createOreDrop(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        add(ModBlocks.FLUORITE_DEEPSLATE_ORE, createOreDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE));

        add(ModBlocks.FLUORITE_NETHER_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));
        add(ModBlocks.FLUORITE_END_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 5, 8));

        dropSelf(ModBlocks.MAGIC_BLOCK);
    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                        block, LootItem.lootTableItem(item)
                                .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                                .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
