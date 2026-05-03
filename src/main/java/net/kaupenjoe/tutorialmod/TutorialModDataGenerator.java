package net.kaupenjoe.tutorialmod;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.kaupenjoe.tutorialmod.datagen.ModBlockLootTableProvider;
import net.kaupenjoe.tutorialmod.datagen.ModBlockTagsProvider;
import net.kaupenjoe.tutorialmod.datagen.ModModelProvider;
import net.kaupenjoe.tutorialmod.datagen.ModRecipeProvider;

public class TutorialModDataGenerator implements DataGeneratorEntrypoint {
	@Override
	public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
		var pack = fabricDataGenerator.createPack();

		pack.addProvider(ModModelProvider::new);
		pack.addProvider(ModBlockTagsProvider::new);
		pack.addProvider(ModBlockLootTableProvider::new);
		pack.addProvider(ModRecipeProvider::new);
	}
}
