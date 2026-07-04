package net.kaupenjoe.tutorialmod.registries;

import net.fabricmc.fabric.api.registry.FabricPotionBrewingBuilder;
import net.kaupenjoe.tutorialmod.potion.ModPotions;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.item.crafting.Ingredient;

public class ModPotionRecipes {
    public static void registerPotionRecipes() {
        FabricPotionBrewingBuilder.BUILD.register(builder -> {
            builder.registerPotionRecipe(Potions.AWKWARD, Ingredient.of(Items.DIRT), ModPotions.STINKY_POTION);

        });
    }
}
