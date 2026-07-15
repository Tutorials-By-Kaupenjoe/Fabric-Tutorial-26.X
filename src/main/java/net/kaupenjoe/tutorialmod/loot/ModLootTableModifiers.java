package net.kaupenjoe.tutorialmod.loot;

import net.fabricmc.fabric.api.loot.v3.FabricLootTableBuilder;
import net.fabricmc.fabric.api.loot.v3.LootTableSource;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.BuiltInLootTables;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator;

public class ModLootTableModifiers {
    public static void modifyLootTables(ResourceKey<LootTable> key, FabricLootTableBuilder builder,
                                        LootTableSource source, HolderLookup.Provider provider) {
        if (key.identifier().equals(Identifier.withDefaultNamespace("blocks/short_grass"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1f))
                    .when(LootItemRandomChanceCondition.randomChance(0.25f))
                    .add(LootItem.lootTableItem(ModItems.STRAWBERRY_SEEDS))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());

            builder.pool(poolBuilder.build());
        }

        // This targets all ancient city chest loot tables!
        if(BuiltInLootTables.ANCIENT_CITY.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(1f)) // Drops 100% of the time
                    .add(LootItem.lootTableItem(ModItems.CHISEL))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(1f, 2f)).build());

            builder.pool(poolBuilder.build());
        }

        // This targets creeper loot table
        if(key.identifier().equals(Identifier.withDefaultNamespace("entities/creeper"))) {
            LootPool.Builder poolBuilder = LootPool.lootPool()
                    .setRolls(ConstantValue.exactly(1))
                    .when(LootItemRandomChanceCondition.randomChance(0.45f)) // Drops 45% of the time
                    .add(LootItem.lootTableItem(ModItems.RAW_FLUORITE))
                    .apply(SetItemCountFunction.setCount(UniformGenerator.between(2f, 5f)).build());

            builder.pool(poolBuilder.build());
        }
    }
}
