package net.kaupenjoe.tutorialmod.effect;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.core.Holder;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.Identifier;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectCategory;

public class ModEffects {
    public static final Holder<MobEffect> STINKY = registerMobEffect("stinky",
            new StinkyEffect(MobEffectCategory.NEUTRAL, 0xde601d));


    private static Holder<MobEffect> registerMobEffect(String name, MobEffect effect) {
        return Registry.registerForHolder(BuiltInRegistries.MOB_EFFECT, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, name), effect);
    }

    public static void registerEffects() {
        TutorialMod.LOGGER.info("Registering effects for " + TutorialMod.MOD_ID);
    }
}
