package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootSubProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.custom.HoneyBerryBushBlock;
import net.kaupenjoe.tutorialmod.block.custom.RiceCropBlock;
import net.kaupenjoe.tutorialmod.block.custom.StrawberryCropBlock;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.advancements.predicates.StatePropertiesPredicate;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.enchantment.Enchantment;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SweetBerryBushBlock;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.ApplyBonusCount;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemBlockStatePropertyCondition;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

import java.util.concurrent.CompletableFuture;

public class ModBlockLootTableProvider extends FabricBlockLootSubProvider {
    public ModBlockLootTableProvider(FabricPackOutput packOutput, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(packOutput, registriesFuture);
    }

    @Override
    public void generate() {
        var enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        dropSelf(ModBlocks.FLUORITE_BLOCK);
        dropSelf(ModBlocks.RAW_FLUORITE_BLOCK);

        add(ModBlocks.FLUORITE_ORE, createOreDrop(ModBlocks.FLUORITE_ORE, ModItems.RAW_FLUORITE));
        add(ModBlocks.FLUORITE_DEEPSLATE_ORE, createOreDrop(ModBlocks.FLUORITE_DEEPSLATE_ORE, ModItems.RAW_FLUORITE));

        add(ModBlocks.FLUORITE_NETHER_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_NETHER_ORE, ModItems.RAW_FLUORITE, 3, 6));
        add(ModBlocks.FLUORITE_END_ORE, createMultipleOreDrops(ModBlocks.FLUORITE_END_ORE, ModItems.RAW_FLUORITE, 5, 8));

        dropSelf(ModBlocks.MAGIC_BLOCK);
        dropSelf(ModBlocks.FLUORITE_STAIRS);
        add(ModBlocks.FLUORITE_SLAB, this::createSlabItemTable);

        dropSelf(ModBlocks.FLUORITE_BUTTON);
        dropSelf(ModBlocks.FLUORITE_PRESSURE_PLATE);
        dropSelf(ModBlocks.FLUORITE_FENCE);
        dropSelf(ModBlocks.FLUORITE_FENCE_GATE);
        dropSelf(ModBlocks.FLUORITE_WALL);
        dropSelf(ModBlocks.FLUORITE_TRAPDOOR);

        add(ModBlocks.FLUORITE_DOOR, this::createDoorTable);

        dropSelf(ModBlocks.FLUORITE_LAMP);
        dropSelf(ModBlocks.PEDESTAL_BLOCK);
        dropSelf(ModBlocks.CRYSTALLIZER);

        this.add(ModBlocks.STRAWBERRY_CROP, this.createCropDrops(ModBlocks.STRAWBERRY_CROP, ModItems.STRAWBERRY, ModItems.STRAWBERRY_SEEDS,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.STRAWBERRY_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(StrawberryCropBlock.AGE, StrawberryCropBlock.MAX_AGE))));

        this.add(ModBlocks.HONEY_BERRY_BUSH, block -> this.applyExplosionDecay(block,
                LootTable.lootTable().withPool(LootPool.lootPool()
                        .when(LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 3)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(2.0F, 3.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))
                ).withPool(LootPool.lootPool().when(
                                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.HONEY_BERRY_BUSH)
                                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(HoneyBerryBushBlock.AGE, 2)))
                        .add(LootItem.lootTableItem(ModItems.HONEY_BERRIES))
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(1.0F, 2.0F)))
                        .apply(ApplyBonusCount.addUniformBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))))
        );

        this.add(ModBlocks.RICE_CROP, this.createCropDrops(ModBlocks.RICE_CROP, ModItems.RICE_SHOOT, ModItems.RICE_SHOOT,
                LootItemBlockStatePropertyCondition.hasBlockStateProperties(ModBlocks.RICE_CROP)
                        .setProperties(StatePropertiesPredicate.Builder.properties().hasProperty(RiceCropBlock.AGE, RiceCropBlock.MAX_AGE))));


        dropSelf(ModBlocks.BALSA_LOG);
        dropSelf(ModBlocks.BALSA_WOOD);
        dropSelf(ModBlocks.STRIPPED_BALSA_LOG);
        dropSelf(ModBlocks.STRIPPED_BALSA_WOOD);
        dropSelf(ModBlocks.BALSA_PLANKS);
        // NOTE: This should drop the Sapling instead!
        add(ModBlocks.BALSA_LEAVES, block -> createLeavesDrops(block, ModBlocks.BALSA_SAPLING, NORMAL_LEAVES_SAPLING_CHANCES));
        dropSelf(ModBlocks.BALSA_SAPLING);
        add(ModBlocks.POTTED_BALSA_SAPLING, block -> createPotFlowerItemTable(ModBlocks.BALSA_SAPLING));

    }

    public LootTable.Builder createMultipleOreDrops(final Block block, Item item, float minDrops, float maxDrops) {
        HolderLookup.RegistryLookup<Enchantment> enchantments = this.registries.lookupOrThrow(Registries.ENCHANTMENT);

        return this.createSilkTouchDispatchTable(block, this.applyExplosionDecay(
                block, LootItem.lootTableItem(item)
                        .apply(SetItemCountFunction.setCount(UniformGenerator.between(minDrops, maxDrops)))
                        .apply(ApplyBonusCount.addOreBonusCount(enchantments.getOrThrow(Enchantments.FORTUNE)))));
    }
}
