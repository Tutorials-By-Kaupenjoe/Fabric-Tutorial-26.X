package net.kaupenjoe.tutorialmod.entity.client;

import net.kaupenjoe.tutorialmod.entity.variant.CapybaraVariant;
import net.minecraft.client.renderer.entity.state.LivingEntityRenderState;
import net.minecraft.world.entity.AnimationState;

public class CapybaraRenderState extends LivingEntityRenderState {
    public final AnimationState idleAnimationState = new AnimationState();
    public CapybaraVariant variant;

}
