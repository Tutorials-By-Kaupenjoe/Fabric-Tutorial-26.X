package net.kaupenjoe.tutorialmod.block;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.block.custom.MagicBlock;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.valueproviders.UniformInt;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;

import java.util.function.Function;

public class ModBlocks {
    public static final Block FLUORITE_BLOCK = registerBlock("fluorite_block",
            properties -> new Block(properties.strength(4f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));
    public static final Block RAW_FLUORITE_BLOCK = registerBlock("raw_fluorite_block",
            properties -> new Block(properties.strength(3f)
                    .requiresCorrectToolForDrops()));

    public static final Block FLUORITE_ORE = registerBlock("fluorite_ore",
            properties -> new DropExperienceBlock(UniformInt.of(2, 5),
                    properties.strength(3f).requiresCorrectToolForDrops().sound(SoundType.STONE)));
    public static final Block FLUORITE_DEEPSLATE_ORE = registerBlock("fluorite_deepslate_ore",
            properties -> new DropExperienceBlock(UniformInt.of(3, 6),
                    properties.strength(4f).requiresCorrectToolForDrops().sound(SoundType.DEEPSLATE)));

    public static final Block FLUORITE_NETHER_ORE = registerBlock("fluorite_nether_ore",
            properties -> new DropExperienceBlock(UniformInt.of(1, 5),
                    properties.strength(3f).requiresCorrectToolForDrops()));
    public static final Block FLUORITE_END_ORE = registerBlock("fluorite_end_ore",
            properties -> new DropExperienceBlock(UniformInt.of(4, 8),
                    properties.strength(6f).requiresCorrectToolForDrops()));

    public static final Block MAGIC_BLOCK = registerBlock("magic_block",
            properties -> new MagicBlock(properties.strength(2f)
                    .requiresCorrectToolForDrops().sound(SoundType.AMETHYST)));



    private static Block registerBlock(String name, Function<BlockBehaviour.Properties, Block> function) {
        Block toRegister = function.apply(BlockBehaviour.Properties.of().setId(ResourceKey.create(Registries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name))));
        registerBlockItem(name, toRegister);
        return Registry.register(BuiltInRegistries.BLOCK, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), toRegister);
    }

    private static void registerBlockItem(String name, Block block) {
        Registry.register(BuiltInRegistries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name),
                new BlockItem(block, new Item.Properties().useBlockDescriptionPrefix()
                        .setId(ResourceKey.create(Registries.ITEM, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name)))));
    }

    public static void registerModBlocks() {
        TutorialMod.LOGGER.info("Registering Mod Blocks for " + TutorialMod.MOD_ID);
    }
}
