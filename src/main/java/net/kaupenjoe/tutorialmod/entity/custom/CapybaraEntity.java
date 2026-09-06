package net.kaupenjoe.tutorialmod.entity.custom;

import net.minecraft.world.entity.AnimationState;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.PathfinderMob;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;

public class CapybaraEntity extends PathfinderMob {
    public final AnimationState idleAnimationState = new AnimationState();
    private int idleAnimationTimeout = 0;

    public CapybaraEntity(EntityType<? extends PathfinderMob> type, Level level) {
        super(type, level);
    }

    @Override
    protected void registerGoals() {
        this.goalSelector.addGoal(0, new FloatGoal(this));

        this.goalSelector.addGoal(1, new PanicGoal(this, 2f));

        this.goalSelector.addGoal(2, new WaterAvoidingRandomStrollGoal(this, 1f));
        this.goalSelector.addGoal(3, new LookAtPlayerGoal(this, Player.class, 6f));
        this.goalSelector.addGoal(4, new RandomLookAroundGoal(this));
    }

    // ANIMATIONS
    private void setupAnimationStates() {
        if(this.idleAnimationTimeout <= 0) {
            this.idleAnimationTimeout = 40;
            this.idleAnimationState.start(this.tickCount);
        } else {
            --this.idleAnimationTimeout;
        }
    }

    @Override
    public void tick() {
        super.tick();

        if(this.level().isClientSide()) {
            this.setupAnimationStates();
        }
    }

    public static AttributeSupplier.Builder createAttributes() {
        return PathfinderMob.createLivingAttributes()
                .add(Attributes.MAX_HEALTH, 6d)
                .add(Attributes.MOVEMENT_SPEED, 0.3D)
                .add(Attributes.TEMPT_RANGE, 16d)
                .add(Attributes.FOLLOW_RANGE, 24D);
    }
}
