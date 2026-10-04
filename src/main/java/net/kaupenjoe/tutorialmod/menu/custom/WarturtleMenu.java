package net.kaupenjoe.tutorialmod.menu.custom;

import net.kaupenjoe.tutorialmod.entity.custom.WarturtleEntity;
import net.kaupenjoe.tutorialmod.menu.ModMenuTypes;
import net.minecraft.world.Container;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;

import java.util.List;
import java.util.UUID;

public class WarturtleMenu extends AbstractContainerMenu {
    private Container warturtleContainer;
    public WarturtleEntity warturtle;


    // With Help from https://github.com/Mrbysco/ChocoCraft4/tree/arch/1.21
    // Under MIT LICENSE
    public static WarturtleMenu create(int i, Inventory inventory, UUID uuid) {
        List<WarturtleEntity> turtles = inventory.player.level().getEntitiesOfClass(WarturtleEntity.class,
                inventory.player.getBoundingBox().inflate(16), test -> test.getUUID().equals(uuid));
        WarturtleEntity warturtleEntity = turtles.isEmpty() ? null : turtles.getFirst();
        return new WarturtleMenu(i, inventory, new SimpleContainer(28), warturtleEntity, 4);
    }

    public WarturtleMenu(int containerId, Inventory inventory, Container warturtleContainer, final WarturtleEntity warturtleEntity, int columns) {
        super(ModMenuTypes.WARTURTLE_MENU, containerId);
        this.warturtleContainer = warturtleContainer;
        this.warturtle = warturtleEntity;
        warturtleContainer.startOpen(inventory.player);

        this.addSlot(new Slot(warturtleContainer, 0, 8, 63));
        this.addSlot(new Slot(warturtleContainer, 1, 44, 63));

        // Chest Slots

        // Tier 1 Chest Slot
        this.addSlot(new Slot(warturtleContainer, 2, 72, 27) {
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST);
            }

            @Override
            public boolean mayPickup(Player player) {
                return !warturtleEntity.hasTier2Chest();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        // Tier 2 Chest Slot
        this.addSlot(new Slot(warturtleContainer, 3, 72, 45){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST) && warturtleEntity.hasTier1Chest();
            }

            @Override
            public boolean mayPickup(Player player) {
                return !warturtleEntity.hasTier3Chest();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        // Tier 3 Chest Slot
        this.addSlot(new Slot(warturtleContainer, 4, 72, 63){
            @Override
            public boolean mayPlace(ItemStack itemStack) {
                return itemStack.is(Items.CHEST) && warturtleEntity.hasTier2Chest();
            }

            @Override
            public int getMaxStackSize() {
                return 1;
            }
        });

        if (columns > 0) {
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l, 98 + l * 18, 27) {
                    @Override
                    public boolean isActive() {
                        return warturtleEntity.hasTier1Chest();
                    }
                });
            }
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l + columns, 98 + l * 18, 27 + 18){
                    @Override
                    public boolean isActive() {
                        return warturtleEntity.hasTier2Chest();
                    }
                });
            }
            for (int l = 0; l < columns; l++) {
                this.addSlot(new Slot(warturtleContainer, 5 + l + 2 * columns, 98 + l * 18, 27 + 2 * 18){
                    @Override
                    public boolean isActive() {
                        return warturtleEntity.hasTier3Chest();
                    }
                });
            }
        }

        for (int i1 = 0; i1 < 3; i1++) {
            for (int k1 = 0; k1 < 9; k1++) {
                this.addSlot(new Slot(inventory, k1 + i1 * 9 + 9, 8 + k1 * 18, 102 + i1 * 18 + -18));
            }
        }

        for (int j1 = 0; j1 < 9; j1++) {
            this.addSlot(new Slot(inventory, j1, 8 + j1 * 18, 142));
        }
    }


    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.slots.get(index);
        if (slot != null && slot.hasItem()) {
            ItemStack itemstack1 = slot.getItem();
            itemstack = itemstack1.copy();
            int i = this.warturtleContainer.getContainerSize() + 1;
            if (index < i) {
                if (!this.moveItemStackTo(itemstack1, i, this.slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(1).mayPlace(itemstack1) && !this.getSlot(1).hasItem()) {
                if (!this.moveItemStackTo(itemstack1, 1, 2, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (this.getSlot(0).mayPlace(itemstack1)) {
                if (!this.moveItemStackTo(itemstack1, 0, 1, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (i <= 1 || !this.moveItemStackTo(itemstack1, 2, i, false)) {
                int j = i + 27;
                int k = j + 9;
                if (index >= j && index < k) {
                    if (!this.moveItemStackTo(itemstack1, i, j, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= i && index < j) {
                    if (!this.moveItemStackTo(itemstack1, j, k, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (!this.moveItemStackTo(itemstack1, j, j, false)) {
                    return ItemStack.EMPTY;
                }

                return ItemStack.EMPTY;
            }

            if (itemstack1.isEmpty()) {
                slot.setByPlayer(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }

        return itemstack;
    }

    @Override
    public boolean stillValid(Player player) {
        return !this.warturtle.hasInventoryChanged(this.warturtleContainer)
                && this.warturtleContainer.stillValid(player)
                && this.warturtle.isAlive()
                && player.isWithinEntityInteractionRange(this.warturtle, 4.0);
    }

    @Override
    public void removed(Player player) {
        super.removed(player);
        this.warturtleContainer.stopOpen(player);
    }
}
