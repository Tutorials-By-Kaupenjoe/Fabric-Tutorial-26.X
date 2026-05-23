package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;

import java.util.concurrent.CompletableFuture;

public class ModItemTagsProvider extends FabricTagsProvider.ItemTagsProvider {
    public ModItemTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(ModTags.Items.TRANSFORMABLE_ITEMS)
                .add(ModItems.FLUORITE)
                .add(Items.IRON_INGOT)
                .add(Items.COAL)
                .add(Items.BRICK);

        valueLookupBuilder(ItemTags.SWORDS).add(ModItems.FLUORITE_SWORD);
        valueLookupBuilder(ItemTags.PICKAXES).add(ModItems.FLUORITE_PICKAXE);
        valueLookupBuilder(ItemTags.SHOVELS).add(ModItems.FLUORITE_SHOVEL);
        valueLookupBuilder(ItemTags.AXES).add(ModItems.FLUORITE_AXE);
        valueLookupBuilder(ItemTags.HOES).add(ModItems.FLUORITE_HOE);
        valueLookupBuilder(ItemTags.SPEARS).add(ModItems.FLUORITE_SPEAR);

        valueLookupBuilder(ItemTags.HEAD_ARMOR).add(ModItems.FLUORITE_HELMET);
        valueLookupBuilder(ItemTags.CHEST_ARMOR).add(ModItems.FLUORITE_CHESTPLATE);
        valueLookupBuilder(ItemTags.LEG_ARMOR).add(ModItems.FLUORITE_LEGGINGS);
        valueLookupBuilder(ItemTags.FOOT_ARMOR).add(ModItems.FLUORITE_BOOTS);

    }
}
