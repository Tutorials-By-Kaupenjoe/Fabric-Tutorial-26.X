package net.kaupenjoe.tutorialmod.item.custom;

import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.data.ModDataComponents;
import net.kaupenjoe.tutorialmod.stat.ModStats;
import net.minecraft.client.Minecraft;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.item.component.TooltipDisplay;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;

import java.util.Map;
import java.util.function.Consumer;

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
    public InteractionResult use(Level level, Player player, InteractionHand hand) {
        if(player.isCrouching()) {
            player.getMainHandItem().remove(ModDataComponents.COORDINATES);
            return InteractionResult.SUCCESS;
        }

        return super.use(level, player, hand);
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

            context.getItemInHand().set(ModDataComponents.COORDINATES, context.getClickedPos());
            context.getPlayer().awardStat(ModStats.CHISEL_USED_STAT, 1);
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public void appendHoverText(ItemStack itemStack, TooltipContext context, TooltipDisplay display,
                                Consumer<Component> builder, TooltipFlag tooltipFlag) {
        if(Minecraft.getInstance().hasShiftDown()) {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel.shift_down"));
        } else {
            builder.accept(Component.translatable("tooltip.tutorialmod.chisel"));
        }

        if(itemStack.has(ModDataComponents.COORDINATES)) {
            builder.accept(Component.literal("Last Block chiseled at " + itemStack.get(ModDataComponents.COORDINATES)));
        }

        super.appendHoverText(itemStack, context, display, builder, tooltipFlag);
    }
}
