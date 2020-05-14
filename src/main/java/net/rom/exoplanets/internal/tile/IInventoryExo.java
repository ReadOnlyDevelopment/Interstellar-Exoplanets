package net.rom.exoplanets.internal.tile;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.IInventory;

public interface IInventoryExo extends IInventory {

  boolean isUsable(EntityPlayer player);

  @Override
  default boolean isUsableByPlayer(EntityPlayer player) {

    return isUsable(player);
  }

  @Override
  default boolean isEmpty() {

    return false;
  }
}
