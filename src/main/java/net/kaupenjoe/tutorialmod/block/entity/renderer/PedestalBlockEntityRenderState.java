package net.kaupenjoe.tutorialmod.block.entity.renderer;

import net.minecraft.client.renderer.blockentity.state.BlockEntityRenderState;
import net.minecraft.client.renderer.item.ItemStackRenderState;
import net.minecraft.world.level.Level;

public class PedestalBlockEntityRenderState extends BlockEntityRenderState {
    public Level level;
    public float rotation;

    final ItemStackRenderState itemStackRenderState = new ItemStackRenderState();
}
