package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.CookingBookCategory;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.ItemLike;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends FabricRecipeProvider {
    public ModRecipeProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(HolderLookup.Provider registries, RecipeOutput output) {
        return new RecipeProvider(registries, output) {
            @Override
            public void buildRecipes() {
                List<ItemLike> FLUORITE_SMELTABLES = List.of(ModItems.RAW_FLUORITE, ModBlocks.FLUORITE_ORE, ModBlocks.FLUORITE_DEEPSLATE_ORE,
                        ModBlocks.FLUORITE_NETHER_ORE, ModBlocks.FLUORITE_END_ORE);

                oreSmelting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 200, "fluorite");
                oreBlasting(FLUORITE_SMELTABLES, RecipeCategory.MISC, CookingBookCategory.BLOCKS, ModItems.FLUORITE, 0.25f, 100, "fluorite");

                nineBlockStorageRecipes(RecipeCategory.MISC, ModItems.FLUORITE, RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_BLOCK);

                shaped(RecipeCategory.MISC, ModBlocks.RAW_FLUORITE_BLOCK)
                        .pattern("RRR")
                        .pattern("RRR")
                        .pattern("RRR")
                        .define('R', ModItems.RAW_FLUORITE)
                        .unlockedBy(getHasName(ModItems.RAW_FLUORITE), has(ModItems.RAW_FLUORITE))
                        .group("fluorite")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 9)
                        .requires(ModBlocks.RAW_FLUORITE_BLOCK)
                        .unlockedBy(getHasName(ModBlocks.RAW_FLUORITE_BLOCK), has(ModBlocks.RAW_FLUORITE_BLOCK))
                        .group("fluorite")
                        .save(output);

                shapeless(RecipeCategory.MISC, ModItems.RAW_FLUORITE, 4)
                        .requires(ModItems.FLUORITE)
                        .requires(Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output, "raw_fluorite_from_fluorite_and_stick");

                stairBuilder(ModBlocks.FLUORITE_STAIRS, Ingredient.of(ModBlocks.FLUORITE_BLOCK))
                        .unlockedBy(getHasName(ModBlocks.FLUORITE_BLOCK), has(ModBlocks.FLUORITE_BLOCK))
                        .group("fluorite")
                        .save(output);
                slab(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_SLAB, ModBlocks.FLUORITE_BLOCK);

                buttonBuilder(ModBlocks.FLUORITE_BUTTON, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                pressurePlate(ModBlocks.FLUORITE_PRESSURE_PLATE, ModItems.FLUORITE);

                fenceBuilder(ModBlocks.FLUORITE_FENCE, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                fenceGateBuilder(ModBlocks.FLUORITE_FENCE_GATE, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                wall(RecipeCategory.BUILDING_BLOCKS, ModBlocks.FLUORITE_WALL, ModBlocks.FLUORITE_BLOCK);

                doorBuilder(ModBlocks.FLUORITE_DOOR, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);
                trapdoorBuilder(ModBlocks.FLUORITE_TRAPDOOR, Ingredient.of(ModItems.FLUORITE))
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_SWORD)
                        .pattern("F")
                        .pattern("F")
                        .pattern("S")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_PICKAXE)
                        .pattern("FFF")
                        .pattern(" S ")
                        .pattern(" S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_SHOVEL)
                        .pattern("F")
                        .pattern("S")
                        .pattern("S")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_AXE)
                        .pattern("FF")
                        .pattern("SF")
                        .pattern("S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.TOOLS, ModItems.FLUORITE_HOE)
                        .pattern("FF")
                        .pattern("S ")
                        .pattern("S ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);

                shaped(RecipeCategory.COMBAT, ModItems.FLUORITE_SPEAR)
                        .pattern("  F")
                        .pattern(" S ")
                        .pattern("S  ")
                        .define('F', ModItems.FLUORITE)
                        .define('S', Items.STICK)
                        .unlockedBy(getHasName(ModItems.FLUORITE), has(ModItems.FLUORITE))
                        .unlockedBy(getHasName(Items.STICK), has(Items.STICK))
                        .group("fluorite")
                        .save(output);


            }
        };
    }

    @Override
    public String getName() {
        return "TutorialMod Recipes";
    }
}
