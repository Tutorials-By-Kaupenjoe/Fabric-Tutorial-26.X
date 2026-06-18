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
        tag(BlockTags.MINEABLE_WITH_PICKAXE)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.RAW_FLUORITE_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE))
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR))
                .add(ModBlocks.getRK(ModBlocks.PEDESTAL_BLOCK));

        tag(BlockTags.NEEDS_IRON_TOOL)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_DEEPSLATE_ORE));

        tag(BlockTags.NEEDS_DIAMOND_TOOL)
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_NETHER_ORE))
                .add(ModBlocks.getRK(ModBlocks.FLUORITE_END_ORE));

        tag(BlockTags.STAIRS).add(ModBlocks.getRK(ModBlocks.FLUORITE_STAIRS));
        tag(BlockTags.SLABS).add(ModBlocks.getRK(ModBlocks.FLUORITE_SLAB));
        tag(BlockTags.BUTTONS).add(ModBlocks.getRK(ModBlocks.FLUORITE_BUTTON));
        tag(BlockTags.PRESSURE_PLATES).add(ModBlocks.getRK(ModBlocks.FLUORITE_PRESSURE_PLATE));

        tag(BlockTags.FENCES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE));
        tag(BlockTags.FENCE_GATES).add(ModBlocks.getRK(ModBlocks.FLUORITE_FENCE_GATE));
        tag(BlockTags.WALLS).add(ModBlocks.getRK(ModBlocks.FLUORITE_WALL));

        tag(BlockTags.DOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_DOOR));
        tag(BlockTags.TRAPDOORS).add(ModBlocks.getRK(ModBlocks.FLUORITE_TRAPDOOR));

        tag(ModTags.Blocks.NEEDS_FLUORITE_TOOL)
                .add(ModBlocks.getRK(ModBlocks.MAGIC_BLOCK))
                .addTag(BlockTags.NEEDS_IRON_TOOL);

        tag(ModTags.Blocks.INCORRECT_FOR_FLUORITE_TOOL)
                .addTag(BlockTags.NEEDS_DIAMOND_TOOL);


    }
}
