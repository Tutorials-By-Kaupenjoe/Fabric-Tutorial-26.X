package net.kaupenjoe.tutorialmod.entity;

import net.kaupenjoe.tutorialmod.TutorialMod;
import net.kaupenjoe.tutorialmod.entity.custom.CapybaraEntity;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;

public class ModEntities {
    public static final ResourceKey<EntityType<?>> CAPYBARA_KEY = ResourceKey.create(Registries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "capybara"));

    public static final EntityType<CapybaraEntity> CAPYBARA = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            Identifier.fromNamespaceAndPath(TutorialMod.MOD_ID, "capybara"),
            EntityType.Builder.of(CapybaraEntity::new, MobCategory.CREATURE).sized(0.75f, 0.5f).build(CAPYBARA_KEY));


    public static void registerModEntities() {
        TutorialMod.LOGGER.info("Registering Mod Entities for " + TutorialMod.MOD_ID);
    }
}
