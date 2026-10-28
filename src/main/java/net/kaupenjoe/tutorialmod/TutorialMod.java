package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricDefaultAttributeRegistry;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.block.entity.ModBlockEntities;
import net.kaupenjoe.tutorialmod.creativemodetab.ModCreativeModeTabs;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.effect.ModEffects;
import net.kaupenjoe.tutorialmod.entity.ModEntities;
import net.kaupenjoe.tutorialmod.entity.custom.CapybaraEntity;
import net.kaupenjoe.tutorialmod.entity.custom.WarturtleEntity;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.kaupenjoe.tutorialmod.loot.ModLootTableModifiers;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.kaupenjoe.tutorialmod.networking.ModPackets;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.kaupenjoe.tutorialmod.registries.*;
import net.kaupenjoe.tutorialmod.sound.ModSounds;
import net.kaupenjoe.tutorialmod.stat.ModStats;
import net.kaupenjoe.tutorialmod.villager.ModVillagers;
import net.kaupenjoe.tutorialmod.worldgen.biome.ModBiomes;
import net.kaupenjoe.tutorialmod.worldgen.biome.ModEndBiomes;
import net.kaupenjoe.tutorialmod.worldgen.biome.ModMaterialRules;
import net.kaupenjoe.tutorialmod.worldgen.gen.ModWorldGeneration;
import net.kaupenjoe.tutorialmod.worldgen.tree.ModFoliagePlacerTypes;
import net.kaupenjoe.tutorialmod.worldgen.tree.ModTrunkPlacerTypes;
import net.minecraft.world.entity.SpawnPlacementTypes;
import net.minecraft.world.entity.SpawnPlacements;
import net.minecraft.world.entity.animal.Animal;
import net.minecraft.world.level.levelgen.Heightmap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import terrablender.api.MaterialRuleManager;
import terrablender.api.TerraBlenderApi;

// Important Comment
public class TutorialMod implements ModInitializer, TerraBlenderApi {
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

		ModMenuTypes.registerModMenuTypes();
		ModRecipes.registerModRecipes();

		ModTrunkPlacerTypes.registerModTrunkPlacerTypes();
		ModFoliagePlacerTypes.registerModFoliagePlacerTypes();

		ModEntities.registerModEntities();


		ModWorldGeneration.generateModWorldGen();


		ModFlammableBlocks.registerFlammableBlocks();
		ModStrippableBlocks.registerStrippableBlocks();

		FabricDefaultAttributeRegistry.register(ModEntities.CAPYBARA, CapybaraEntity.createAttributes());
		FabricDefaultAttributeRegistry.register(ModEntities.WARTURTLE, WarturtleEntity.createAttributes());

		LootTableEvents.MODIFY.register(ModLootTableModifiers::modifyLootTables);

		SpawnPlacements.register(ModEntities.CAPYBARA, SpawnPlacementTypes.ON_GROUND,
				Heightmap.Types.MOTION_BLOCKING_NO_LEAVES, Animal::checkAnimalSpawnRules);
	}

	@Override
	public void onTerraBlenderInitialized() {
		ModBiomes.registerBiomes();

		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.OVERWORLD, MOD_ID, ModMaterialRules::makeModOverworldRules);
		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.NETHER, MOD_ID, ModMaterialRules::makeModNetherRules);
		MaterialRuleManager.addRules(MaterialRuleManager.RuleCategory.END, MOD_ID, ModMaterialRules::makeModEndRules);
	}
}