package net.kaupenjoe.tutorialmod.datagen;

import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.tags.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.tags.BlockTags;

import java.util.concurrent.CompletableFuture;

public class ModBlockTagsProvider extends FabricTagsProvider.BlockTagsProvider {
    public ModBlockTagsProvider(FabricPackOutput output, CompletableFuture<HolderLookup.Provider> registryLookupFuture) {
        super(output, registryLookupFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        valueLookupBuilder(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.FLUORITE_BLOCK)
                .add(ModBlocks.RAW_FLUORITE_BLOCK)
                .add(ModBlocks.FLUORITE_ORE)
                .add(ModBlocks.FLUORITE_DEEPSLATE_ORE)
                .add(ModBlocks.FLUORITE_NETHER_ORE)
                .add(ModBlocks.FLUORITE_END_ORE)
                .add(ModBlocks.MAGIC_BLOCK)
                .add(ModBlocks.FLUORITE_STAIRS)
                .add(ModBlocks.FLUORITE_SLAB)
                .add(ModBlocks.FLUORITE_FENCE)
                .add(ModBlocks.FLUORITE_FENCE_GATE)
                .add(ModBlocks.FLUORITE_WALL)
                .add(ModBlocks.FLUORITE_DOOR)
                .add(ModBlocks.FLUORITE_TRAPDOOR)
                .add(ModBlocks.PEDESTAL_BLOCK);

        valueLookupBuilder(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.FLUORITE_DEEPSLATE_ORE);

        valueLookupBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.FLUORITE_NETHER_ORE)
                .add(ModBlocks.FLUORITE_END_ORE);

        valueLookupBuilder(BlockTags.STAIRS).add(ModBlocks.FLUORITE_STAIRS);
        valueLookupBuilder(BlockTags.SLABS).add(ModBlocks.FLUORITE_SLAB);
        valueLookupBuilder(BlockTags.BUTTONS).add(ModBlocks.FLUORITE_BUTTON);
        valueLookupBuilder(BlockTags.PRESSURE_PLATES).add(ModBlocks.FLUORITE_PRESSURE_PLATE);

        valueLookupBuilder(BlockTags.FENCES).add(ModBlocks.FLUORITE_FENCE);
        valueLookupBuilder(BlockTags.FENCE_GATES).add(ModBlocks.FLUORITE_FENCE_GATE);
        valueLookupBuilder(BlockTags.WALLS).add(ModBlocks.FLUORITE_WALL);

        valueLookupBuilder(BlockTags.DOORS).add(ModBlocks.FLUORITE_DOOR);
        valueLookupBuilder(BlockTags.TRAPDOORS).add(ModBlocks.FLUORITE_TRAPDOOR);

        valueLookupBuilder(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.MAGIC_BLOCK)
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        valueLookupBuilder(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);


    }
}
