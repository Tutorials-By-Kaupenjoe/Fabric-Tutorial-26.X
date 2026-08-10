package net.kaupenjoe.tutorialmod.worldgen.tree;

import com.mojang.serialization.MapCodec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.util.RandomSource;
import net.minecraft.world.level.WorldGenLevel;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.configurations.TreeConfiguration;
import net.minecraft.world.level.levelgen.feature.foliageplacers.FoliagePlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacer;
import net.minecraft.world.level.levelgen.feature.trunkplacers.TrunkPlacerType;

import java.util.List;
import java.util.function.BiConsumer;

public class SpiralTrunkPlacer extends TrunkPlacer {
    public static final MapCodec<SpiralTrunkPlacer> CODEC = RecordCodecBuilder.mapCodec(instance ->
            trunkPlacerParts(instance).apply(instance, SpiralTrunkPlacer::new));

    Direction[] spiralDirs = { Direction.NORTH, Direction.EAST, Direction.SOUTH, Direction.WEST };

    public SpiralTrunkPlacer(int baseHeight, int heightRandA, int heightRandB) {
        super(baseHeight, heightRandA, heightRandB);
    }

    @Override
    protected TrunkPlacerType<?> type() {
        return ModTrunkPlacerTypes.SPIRAL_TRUNK_PLACER;
    }

    @Override
    public List<FoliagePlacer.FoliageAttachment> placeTrunk(WorldGenLevel level, BiConsumer<BlockPos, BlockState> trunkSetter,
                                                            RandomSource random, int treeHeight, BlockPos origin, TreeConfiguration config) {
        placeBelowTrunkBlock(level, trunkSetter, random, origin.below(), config);
        int height = treeHeight + random.nextInt(heightRandA, heightRandB + 3) + random.nextInt(heightRandA - 1, heightRandB + 2);

        for(int i = 0; i < baseHeight; i++) {
            placeLog(level, trunkSetter, random, origin.above(i), config);
        }

        BlockPos currentPos = origin.above(baseHeight - 1);

        for(int i = 0; i < height; i++) {
            Direction stepDir = spiralDirs[i % spiralDirs.length];
            currentPos = currentPos.above().relative(stepDir);
            BlockState state = config.trunkProvider.getState(level, random, origin);
            trunkSetter.accept(currentPos, state);
        }

        return List.of(new FoliagePlacer.FoliageAttachment(currentPos, 0, false));
    }
}
