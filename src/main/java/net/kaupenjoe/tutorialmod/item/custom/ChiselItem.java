package net.kaupenjoe.tutorialmod.item.custom;

import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;

public class ChiselItem extends Item {
    private static final Map<Block, Block> CHISEL_MAP =
            Map.of(
                    Blocks.STONE, Blocks.STONE_BRICKS,
                    Blocks.END_STONE, Blocks.END_STONE_BRICKS,
                    Blocks.OAK_LOG, ModBlocks.FLUORITE_BLOCK,
                    ModBlocks.FLUORITE_BLOCK, Blocks.NETHER_BRICKS,
                    Blocks.IRON_BLOCK, Blocks.GOLD_BLOCK
            );

    public ChiselItem(Properties properties) {
        super(properties);
    }

    @Override
    public InteractionResult useOn(UseOnContext context) {
        // Right Click Block
        // Change Block from A to B...

        Level level = context.getLevel();
        Block clickedBlock = level.getBlockState(context.getClickedPos()).getBlock();

        if(CHISEL_MAP.containsKey(clickedBlock) && !level.isClientSide()) {
            // We are on the Server!
            level.setBlockAndUpdate(context.getClickedPos(), CHISEL_MAP.get(clickedBlock).defaultBlockState());
            context.getItemInHand().hurtAndBreak(1, context.getPlayer(), context.getHand());
        }

        return InteractionResult.SUCCESS;
    }
}
