package net.kaupenjoe.tutorialmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kaupenjoe.tutorialmod.datagen.*;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
		pack.addProvider(ModItemTagsProvider::new);
		pack.addProvider(ModEquipmentAssetProvider::new);
		pack.addProvider(ModRegistryDataProvider::new);
		pack.addProvider(ModPaintingTagsProvider::new);
	}

	@Override
	public void buildRegistry(RegistrySetBuilder registryBuilder) {
		registryBuilder.add(Registries.PAINTING_VARIANT, ModPaintings::bootstrap);
	}
}
