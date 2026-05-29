package net.kaupenjoe.tutorialmod.mixin;

import com.mojang.authlib.GameProfile;
import net.kaupenjoe.tutorialmod.item.ModItems;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractClientPlayer.class)
public abstract class AbstractClientPlayerMixin extends Player {
    public AbstractClientPlayerMixin(Level level, GameProfile gameProfile) {
        super(level, gameProfile);
    }

    @Inject(method = "getFieldOfViewModifier", at = @At(value = "TAIL"), cancellable = true)
    private void getFieldOfViewModifierMixin(boolean firstPerson, float effectScale, CallbackInfoReturnable<Float> info) {
        float modifier = 1f;

        if (this.isUsingItem() && this.getUseItem().is(ModItems.KAUPEN_BOW)) {
            float scale = Math.min(this.getTicksUsingItem() / 20.0F, 1.0F);
            modifier *= 1.0F - Mth.square(scale) * 0.15F;
            info.setReturnValue(Mth.lerp(effectScale, 1.0F, modifier));
        }
    }
}
