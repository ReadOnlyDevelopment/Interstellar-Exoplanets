package net.rom.exoplanets.internal.recipe;

import net.minecraft.inventory.InventoryCrafting;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.util.NonNullList;
import net.minecraftforge.registries.IForgeRegistryEntry;

public abstract class RBase extends IForgeRegistryEntry.Impl<IRecipe> implements IRecipe {
    @Override
    public boolean canFit(int width, int height) {
        return true;
    }

    @Override
    public boolean isDynamic() {
        return true;
    }

    @Override
    public NonNullList<ItemStack> getRemainingItems(InventoryCrafting inv) {
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty() && !stack.getItem().hasContainerItem(stack)) {
                stack.shrink(1);
                inv.setInventorySlotContents(i, stack);
            }
        }
        return NonNullList.create();
    }

    /**
     * Convenience method to make iterating a bit cleaner.
     *
     * @return A list of all non-empty stacks in the inventory
     */
    public static NonNullList<ItemStack> getNonEmptyStacks(InventoryCrafting inv) {
        NonNullList<ItemStack> list = NonNullList.create();
        for (int i = 0; i < inv.getSizeInventory(); ++i) {
            ItemStack stack = inv.getStackInSlot(i);
            if (!stack.isEmpty()) {
                list.add(stack);
            }
        }
        return list;
    }
}
