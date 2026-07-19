package net.kaupenjoe.tutorialmod.recipe.custom;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.kaupenjoe.tutorialmod.recipe.ModRecipes;
import net.minecraft.network.RegistryFriendlyByteBuf;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.ItemStackTemplate;
import net.minecraft.world.item.crafting.*;
import net.minecraft.world.level.Level;

public record CrystallizerRecipe(Ingredient inputItem, ItemStackTemplate output) implements Recipe<CrystallizerRecipeInput> {
    public static final MapCodec<CrystallizerRecipe> CODEC = RecordCodecBuilder.mapCodec(instance ->
            instance.group(
                    Ingredient.CODEC.fieldOf("ingredient").forGetter(CrystallizerRecipe::inputItem),
                    ItemStackTemplate.CODEC.fieldOf("result").forGetter(CrystallizerRecipe::output)
            ).apply(instance, CrystallizerRecipe::new));
    public static final StreamCodec<RegistryFriendlyByteBuf, CrystallizerRecipe> STREAM_CODEC =
            StreamCodec.composite(
                    Ingredient.CONTENTS_STREAM_CODEC,
                    CrystallizerRecipe::inputItem,

                    ItemStackTemplate.STREAM_CODEC,
                    CrystallizerRecipe::output,

                    CrystallizerRecipe::new);


    @Override
    public boolean matches(CrystallizerRecipeInput input, Level level) {
        if(level.isClientSide()) {
            return false;
        }

        return inputItem.test(input.getItem(0));
    }

    @Override
    public ItemStack assemble(CrystallizerRecipeInput input) {
        return output.create().copy();
    }

    @Override
    public boolean showNotification() {
        return true;
    }

    @Override
    public String group() {
        return "Crystallizing";
    }

    @Override
    public RecipeSerializer<? extends Recipe<CrystallizerRecipeInput>> getSerializer() {
        return ModRecipes.CRYSTALLIZER_SERIALIZER;
    }

    @Override
    public RecipeType<? extends Recipe<CrystallizerRecipeInput>> getType() {
        return ModRecipes.CRYSTALLIZER_TYPE;
    }

    @Override
    public PlacementInfo placementInfo() {
        return PlacementInfo.NOT_PLACEABLE;
    }

    @Override
    public RecipeBookCategory recipeBookCategory() {
        return RecipeBookCategories.CRAFTING_MISC;
    }
}
