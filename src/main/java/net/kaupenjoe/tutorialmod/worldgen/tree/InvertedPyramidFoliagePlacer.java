package net.kaupenjoe.tutorialmod.worldgen.tree;

import com.mojang.serialization.Codec;
import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.util.RandomSource;
import net.minecraft.util.valueproviders.IntProvider;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacerType;

public class InvertedPyramidFoliagePlacer extends FoliagePlacer {
    public static final MapCodec<InvertedPyramidFoliagePlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            foliagePlacerParts(instance).and(
                    Codec.intRange(0, 16).fieldOf("height").forGetter(inst -> inst.height))

                    .apply(instance, InvertedPyramidFoliagePlacer::new));
    private final int height;

    public InvertedPyramidFoliagePlacer(IntProvider radius, IntProvider offset, int height) {
        super(radius, offset);
        this.height = height;
    }

    @Override
    protected FoliagePlacerType<?> type() {
        return ModFoliagePlacerTypes.INVERTED_PYRAMID_FOLIAGE_PLACER;
    }

    @Override
    protected void createFoliage(WorldGenLevel level, FoliageSetter foliageSetter, RandomSource random, TreeConfiguration config,
                                 int treeHeight, FoliageAttachment foliageAttachment, int foliageHeight, int leafRadius, int offset) {
        for(int i = 0; i < foliageHeight; i++) {
            int currentRadius = leafRadius + i - 1;
            placeLeavesRow(level, foliageSetter, random, config, foliageAttachment.pos().above(i + 1), currentRadius, 0, foliageAttachment.doubleTrunk());
        }
    }

    @Override
    public int foliageHeight(RandomSource random, int treeHeight, TreeConfiguration config) {
        return height;
    }

    @Override
    protected boolean shouldSkipLocation(RandomSource random, int dx, int y, int dz, int currentRadius, boolean doubleTrunk) {
        return false;
    }
}
