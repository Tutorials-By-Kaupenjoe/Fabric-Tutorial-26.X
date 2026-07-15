package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.creativemodetab.ModCreativeModeTabs;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.effect.ModEffects;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.loot.ModLootTableModifiers;
import net.kaupenjoe.tutorialmod.networking.ModPackets;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.kaupenjoe.tutorialmod.registries.ModCompostables;
import net.kaupenjoe.tutorialmod.registries.ModFuels;
import net.kaupenjoe.tutorialmod.registries.ModPotionRecipes;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.kaupenjoe.tutorialmod.stat.ModStats;
import net.kaupenjoe.tutorialmod.villager.ModVillagers;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

// Important Comment
public class TutorialMod implements ModInitializer {
	public static final String MOD_ID = "tutorialmod";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		ModCreativeModeTabs.registerModCreativeModeTabs();

		ModItems.registerModItems();
		ModBlocks.registerModBlocks();

		ModDataComponents.registerDataComponents();
		ModStats.registerStats();

		ModSounds.registerSounds();
		ModEffects.registerEffects();

		ModPotions.registerPotions();
		ModVillagers.register();

		ModPackets.registerPackets();
		ModBlockEntities.registerBlockEntities();


		ModFuels.registerFuels();
		ModCompostables.registerCompostables();
		ModPotionRecipes.registerPotionRecipes();

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);
	}
}