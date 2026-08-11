package net.kaupenjoe.tutorialmod.block.custom;

import net.kaupenjoe.tutorialmod.block.ModBlocks;
import net.kaupenjoe.tutorialmod.worldgen.dimension.ModDimensions;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.resources.ResourceKey;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.Relative;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.Portal;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.levelgen.feature.EndPlatformFeature;
import net.minecraft.world.level.portal.TeleportTransition;
import net.minecraft.world.level.storage.LevelData;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.Vec3;
import org.jspecify.annotations.Nullable;

import java.util.Set;

public class KaupenPortalBlock extends Block implements Portal {
    public KaupenPortalBlock(Properties properties) {
        super(properties);
    }

    @Override
    protected InteractionResult useWithoutItem(BlockState state, Level level, BlockPos pos, Player player, BlockHitResult hitResult) {
        if(!level.isClientSide()) {
            player.teleport(getPortalDestination(((ServerLevel) level), player, pos));
        }

        return InteractionResult.SUCCESS;
    }

    @Override
    public @Nullable TeleportTransition getPortalDestination(ServerLevel currentLevel, Entity entity, BlockPos portalEntryPos) {
        ResourceKey<Level> currentDimension = currentLevel.dimension();
        boolean isLeavingCustomDim = currentDimension == ModDimensions.KAUPENDIM_LEVEL_KEY;

        LevelData.RespawnData respawnData = currentLevel.getRespawnData();
        ResourceKey<Level> targetDimensionKey = isLeavingCustomDim ? respawnData.dimension() : ModDimensions.KAUPENDIM_LEVEL_KEY;
        BlockPos baseSpawnPos = isLeavingCustomDim ? respawnData.pos() : ServerLevel.END_SPAWN_POINT.above(14);

        ServerLevel targetLevel = currentLevel.getServer().getLevel(targetDimensionKey);
        if (targetLevel == null) {
            return null;
        }

        Vec3 exactSpawnPos = Vec3.atBottomCenterOf(baseSpawnPos);
        float yRot, xRot;
        Set<Relative> relatives;

        if(isLeavingCustomDim) {
            // Returning from Custom Dimension
            if(entity instanceof ServerPlayer serverPlayer) {
                return serverPlayer.findRespawnPositionAndUseSpawnBlock(false, TeleportTransition.DO_NOTHING);
            }

            exactSpawnPos = Vec3.atBottomCenterOf(entity.adjustSpawnLocation(targetLevel, baseSpawnPos));
            yRot = respawnData.yaw();
            xRot = respawnData.pitch();
            relatives = Relative.union(Relative.DELTA, Relative.ROTATION);
        } else {
            // Entering our custom Dimension
            BlockPos platformCenter = BlockPos.containing(exactSpawnPos).below();
            BlockPos portalPos = platformCenter.west(2);

            if(!targetLevel.getBlockState(portalPos.above()).is(ModBlocks.KAUPEN_PORTAL)) {
                EndPlatformFeature.createEndPlatform(targetLevel, platformCenter, true);

                targetLevel.setBlockAndUpdate(portalPos, Blocks.OBSIDIAN.defaultBlockState());
                targetLevel.setBlockAndUpdate(portalPos.above(), ModBlocks.KAUPEN_PORTAL.defaultBlockState());
            }

            yRot = Direction.WEST.toYRot();
            xRot = 0f;
            relatives = Relative.union(Relative.DELTA, Set.of(Relative.X_ROT));

            if(entity instanceof ServerPlayer) {
                exactSpawnPos = exactSpawnPos.subtract(0, 1, 0);
            }
        }

        return new TeleportTransition(
                targetLevel,
                exactSpawnPos,
                Vec3.ZERO,
                yRot,
                xRot,
                relatives,
                TeleportTransition.PLAY_PORTAL_SOUND.then(TeleportTransition.PLACE_PORTAL_TICKET));
    }
}
