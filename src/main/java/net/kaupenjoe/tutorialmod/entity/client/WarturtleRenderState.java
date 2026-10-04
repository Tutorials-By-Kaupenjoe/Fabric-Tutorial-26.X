package net.kaupenjoe.tutorialmod.entity.client;

import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class WarturtleRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public final AnimationState hidingAnimationState = new AnimationState();
    public final AnimationState emergeAnimationState = new AnimationState();
    public final AnimationState sittingAnimationState = new AnimationState();

    public boolean hasChestTier1 = false;
    public boolean hasChestTier2 = false;
    public boolean hasChestTier3 = false;
}
