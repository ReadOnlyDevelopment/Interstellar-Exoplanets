/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom.exoplanets.content.block.machine.container;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.entity.player.InventoryPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IContainerListener;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.inventory.SlotFurnaceFuel;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntityFurnace;
import net.rom.api.recipe.alloyrefinery.AlloyRefineryRecipe;
import net.rom.exoplanets.content.block.machine.TileAlloyRefinery;
import net.rom.exoplanets.content.block.machine.container.slot.SlotAlloyRefineryOutput;

public class ContainerAlloyRefinery extends Container {
    private final IInventory tileAlloyRefinery;
    private int cookTime;
    private int totalCookTime;
    private int furnaceBurnTime;
    private int currentItemBurnTime;

    public ContainerAlloyRefinery(InventoryPlayer playerInventory, IInventory smelterInventory) {
        this.tileAlloyRefinery = smelterInventory;

        // Input slots
        addSlotToContainer(new Slot(smelterInventory, 0, 43, 26));
        addSlotToContainer(new Slot(smelterInventory, 1, 61, 26));
        addSlotToContainer(new Slot(smelterInventory, 2, 43, 44));
        addSlotToContainer(new Slot(smelterInventory, 3, 61, 44));

        // Fuel slot
        addSlotToContainer(new SlotFurnaceFuel(smelterInventory, TileAlloyRefinery.SLOT_FUEL, 19, 44));

        // Output slot
        addSlotToContainer(new SlotAlloyRefineryOutput(playerInventory.player, smelterInventory,
                TileAlloyRefinery.SLOT_OUTPUT, 116, 35));

        // Player inventory
        int i;
        for (i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlotToContainer(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }

        for (i = 0; i < 9; ++i) {
            this.addSlotToContainer(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    @Override
    public void detectAndSendChanges() {
        super.detectAndSendChanges();

        for (int i = 0; i < this.listeners.size(); ++i) {
            IContainerListener icrafting = this.listeners.get(i);

            if (this.cookTime != this.tileAlloyRefinery.getField(2)) {
                icrafting.sendWindowProperty(this, 2, this.tileAlloyRefinery.getField(2));
            }

            if (this.furnaceBurnTime != this.tileAlloyRefinery.getField(0)) {
                icrafting.sendWindowProperty(this, 0, this.tileAlloyRefinery.getField(0));
            }

            if (this.currentItemBurnTime != this.tileAlloyRefinery.getField(1)) {
                icrafting.sendWindowProperty(this, 1, this.tileAlloyRefinery.getField(1));
            }

            if (this.totalCookTime != this.tileAlloyRefinery.getField(3)) {
                icrafting.sendWindowProperty(this, 3, this.tileAlloyRefinery.getField(3));
            }
        }

        this.cookTime = this.tileAlloyRefinery.getField(2);
        this.furnaceBurnTime = this.tileAlloyRefinery.getField(0);
        this.currentItemBurnTime = this.tileAlloyRefinery.getField(1);
        this.totalCookTime = this.tileAlloyRefinery.getField(3);
    }

    //  @Override
    public void updateProgressBar(int id, int data) {
        this.tileAlloyRefinery.setField(id, data);
    }

    @Override
    public boolean canInteractWith(EntityPlayer playerIn) {
        return this.tileAlloyRefinery.isUsableByPlayer(playerIn);
    }

    @Override
    public ItemStack transferStackInSlot(EntityPlayer playerIn, int index) {
        ItemStack itemstack = ItemStack.EMPTY;
        Slot slot = this.inventorySlots.get(index);

        if (slot != null && slot.getHasStack()) {
            final int slotFuel = TileAlloyRefinery.SLOT_FUEL;                // 4
            final int inputSlotCount = TileAlloyRefinery.SLOTS_INPUT.length; // 4
            final int slotCount = TileAlloyRefinery.NUMBER_OF_SLOTS;         // 6

            ItemStack itemstack1 = slot.getStack();
            itemstack = itemstack1.copy();

            if (index == TileAlloyRefinery.SLOT_OUTPUT) {
                if (!this.mergeItemStack(itemstack1, slotCount, slotCount + 36, true)) {
                    return ItemStack.EMPTY;
                }

                slot.onSlotChange(itemstack1, itemstack);
            } else if (index != slotFuel && index >= inputSlotCount) {
                if (TileEntityFurnace.isItemFuel(itemstack1)) {
                    // Insert fuel?
                    if (!this.mergeItemStack(itemstack1, slotFuel, slotFuel + 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (AlloyRefineryRecipe.isValidIngredient(itemstack1)) {
                    // Insert ingredients?
                    if (!this.mergeItemStack(itemstack1, 0, inputSlotCount, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 4 && index < 31) {
                    if (!this.mergeItemStack(itemstack1, 31, 40, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (index >= 31 && index < 40 && !this.mergeItemStack(itemstack1, 4, 31, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (!this.mergeItemStack(itemstack1, slotCount, slotCount + 36, false)) {
                return ItemStack.EMPTY;
            }

            if (!itemstack1.isEmpty()) {
                slot.putStack(ItemStack.EMPTY);
            } else {
                slot.onSlotChanged();
            }

            if (itemstack1.getCount() == itemstack.getCount()) {
                return ItemStack.EMPTY;
            }

            slot.onTake(playerIn, itemstack1);
        }

        return itemstack;
    }
}
