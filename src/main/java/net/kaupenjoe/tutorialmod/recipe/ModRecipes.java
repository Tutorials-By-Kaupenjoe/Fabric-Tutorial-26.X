package net.kaupenjoe.tutorialmod.recipe;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;

public class ModRecipes {
    public static final RecipeSerializer<CrystallizerRecipe> CRYSTALLIZER_SERIALIZER = Registry.register(
            BuiltInRegistries.RECIPE_SERIALIZER, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizing"),
            new RecipeSerializer<>(CrystallizerRecipe.CODEC, CrystallizerRecipe.STREAM_CODEC));
    public static final RecipeType<CrystallizerRecipe> CRYSTALLIZER_TYPE = Registry.register(
            BuiltInRegistries.RECIPE_TYPE, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "crystallizing"),
            new RecipeType<CrystallizerRecipe>() {
                @Override
                public String toString() {
                    return "crystallizing";
                }
            });

    public static void registerModRecipes() {
        TutorialMod.LOGGER.info("Registering ModRecipes for " + TutorialMod.MOD_ID);
    }
}
