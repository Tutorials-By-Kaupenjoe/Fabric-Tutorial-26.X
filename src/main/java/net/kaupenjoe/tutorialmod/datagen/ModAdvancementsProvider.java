package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementHolder;
import net.minecraft.advancements.AdvancementRequirements;
import net.minecraft.advancements.AdvancementType;
import net.minecraft.advancements.predicates.BlockPredicate;
import net.minecraft.advancements.predicates.ItemPredicate;
import net.minecraft.advancements.predicates.LocationPredicate;
import net.minecraft.advancements.triggers.InventoryChangeTrigger;
import net.minecraft.advancements.triggers.ItemUsedOnLocationTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.PackOutput;
import net.minecraft.data.advancements.AdvancementProvider;
import net.minecraft.data.advancements.AdvancementSubProvider;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.world.level.block.Blocks;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class ModAdvancementsProvider extends AdvancementProvider {
    public ModAdvancementsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> registries) {
        super(output, registries, List.of(new TutorialModAdvancements()));
    }

    public static class TutorialModAdvancements implements AdvancementSubProvider {
        @Override
        public void generate(HolderLookup.Provider registries, Consumer<AdvancementHolder> output) {
            var items = registries.lookupOrThrow(Registries.ITEM);
            var blocks = registries.lookupOrThrow(Registries.BLOCK);

            AdvancementHolder root = Advancement.Builder.advancement()
                    .display(
                            ModItems.FLUORITE,
                            Component.translatable("advancements.tutorialmod.root.title"),
                            Component.translatable("advancements.tutorialmod.root.description"),
                            Identifier.withDefaultNamespace("gui/advancements/backgrounds/adventure"),
                            AdvancementType.TASK,
                            false,
                            false,
                            false
                    )
                    .addCriterion("has_fluorite", InventoryChangeTrigger.TriggerInstance.hasItems(ItemPredicate.Builder.item().of(items, ModItems.FLUORITE)))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/root");


            AdvancementHolder plantSeed = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.RICE_SHOOT,
                            Component.translatable("advancements.tutorialmod.plant_custom.title"),
                            Component.translatable("advancements.tutorialmod.plant_custom.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .requirements(AdvancementRequirements.Strategy.OR)
                    .addCriterion("berries", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.HONEY_BERRY_BUSH))
                    .addCriterion("rice", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.RICE_CROP))
                    .addCriterion("strawberry", ItemUsedOnLocationTrigger.TriggerInstance.placedBlock(ModBlocks.STRAWBERRY_CROP))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/plant_custom");

            AdvancementHolder useChisel = Advancement.Builder.advancement()
                    .parent(root)
                    .display(
                            ModItems.CHISEL,
                            Component.translatable("advancements.tutorialmod.chisel_stone.title"),
                            Component.translatable("advancements.tutorialmod.chisel_stone.description"),
                            null,
                            AdvancementType.TASK,
                            true,
                            true,
                            false
                    )
                    .addCriterion("chisel_stone", ItemUsedOnLocationTrigger.TriggerInstance.itemUsedOnBlock(LocationPredicate.Builder.location(),
                            ItemPredicate.Builder.item().of(items, ModItems.CHISEL.asItem())))
                    .save(output, TutorialMod.MOD_ID + ":tutorialmod/chisel_stone");

        }
    }
}
