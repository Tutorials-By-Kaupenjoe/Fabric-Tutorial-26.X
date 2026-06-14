package net.kaupenjoe.tutorialmod.datagen;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.minecraft.ChatFormatting;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstrapContext;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.decoration.painting.PaintingVariant;

import java.util.Optional;

public class ModPaintings {
    public static final ResourceKey<PaintingVariant> SAW_THEM_KEY = create("saw_them");
    public static final ResourceKey<PaintingVariant> SHRIMP_KEY = create("shrimp");
    public static final ResourceKey<PaintingVariant> WORLD_KEY = create("world");


    public static void bootstrap(BootstrapContext<PaintingVariant> context) {
        register(context, SAW_THEM_KEY, 2, 2, true);
        register(context, SHRIMP_KEY, 2, 1, true);
        register(context, WORLD_KEY, 2, 2, true);
    }

    private static ResourceKey<PaintingVariant> create(final String id) {
        return ResourceKey.create(Registries.PAINTING_VARIANT, Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, id));
    }

    private static void register(final BootstrapContext<PaintingVariant> context, final ResourceKey<PaintingVariant> key, final int width,
                                 final int height, final boolean hasAuthor) {
        context.register(key, new PaintingVariant(width, height, key.identifier(),
                Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "title")).withStyle(ChatFormatting.YELLOW)),
                hasAuthor ? Optional.of(Component.translatable(key.identifier().toLanguageKey("painting", "author")).withStyle(ChatFormatting.GRAY)) : Optional.empty()));
    }
}
