package net.kaupenjoe.tutorialmod;

import net.fabricmc.api.ModInitializer;

import net.kaupenjoe.tutorialmod.creativemodetab.ModCreativeModeTabs;
import net.kaupenjoe.tutorialmod.item.ModItems;
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

	}
}