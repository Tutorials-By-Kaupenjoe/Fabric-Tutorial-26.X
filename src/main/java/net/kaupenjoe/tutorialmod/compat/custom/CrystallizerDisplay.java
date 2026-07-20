package net.kaupenjoe.tutorialmod.compat.custom;

import com.mojang.serialization.codecs.RecordCodecBuilder;
import me.shedaniel.rei.api.common.category.CategoryIdentifier;
import me.shedaniel.rei.api.common.display.Display;
import me.shedaniel.rei.api.common.display.DisplaySerializer;
import me.shedaniel.rei.api.common.entry.EntryIngredient;
import me.shedaniel.rei.api.common.util.EntryIngredients;
import net.kaupenjoe.tutorialmod.compat.TutorialModREICommon;
import net.kaupenjoe.tutorialmod.recipe.custom.CrystallizerRecipe;
import net.minecraft.network.codec.ByteBufCodecs;
import net.minecraft.network.codec.StreamCodec;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.crafting.RecipeHolder;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Optional;

public record CrystallizerDisplay(EntryIngredient input, EntryIngredient output,
                                  Optional<Identifier> location) implements Display {
    public static final DisplaySerializer<CrystallizerDisplay> SERIALIZER = DisplaySerializer.of(
            RecordCodecBuilder.mapCodec(instance -> instance.group(
                    EntryIngredient.codec().fieldOf("input").forGetter(CrystallizerDisplay::input),
                    EntryIngredient.codec().fieldOf("output").forGetter(CrystallizerDisplay::output),
                    Identifier.CODEC.optionalFieldOf("location").forGetter(CrystallizerDisplay::location)
            ).apply(instance, CrystallizerDisplay::new)),
            StreamCodec.composite(
                    EntryIngredient.streamCodec(),
                    CrystallizerDisplay::input,

                    EntryIngredient.streamCodec(),
                    CrystallizerDisplay::output,

                    ByteBufCodecs.optional(Identifier.STREAM_CODEC),
                    CrystallizerDisplay::location,

                    CrystallizerDisplay::new));

    public CrystallizerDisplay(RecipeHolder<CrystallizerRecipe> entry) {
        this(entry.id().identifier(), entry.value());
    }

    public CrystallizerDisplay(Identifier id, CrystallizerRecipe recipe) {
        this(EntryIngredients.ofIngredient(recipe.inputItem()), EntryIngredients.of(recipe.output().create()), Optional.of(id));
    }

    @Override
    public List<EntryIngredient> getInputEntries() {
        return List.of(input);
    }

    @Override
    public List<EntryIngredient> getOutputEntries() {
        return List.of(output);
    }

    @Override
    public CategoryIdentifier<?> getCategoryIdentifier() {
        return TutorialModREICommon.CRYSTALLIZER;
    }

    @Override
    public Optional<Identifier> getDisplayLocation() {
        return location;
    }

    @Override
    public @Nullable DisplaySerializer<? extends Display> getSerializer() {
        return SERIALIZER;
    }
}
