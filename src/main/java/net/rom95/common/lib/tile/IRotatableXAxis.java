package net.rom95.common.lib.tile;

import net.minecraft.util.EnumFacing;

public interface IRotatableXAxis {

	/**
	 * Gets the rotation X axis.
	 *
	 * @return the rotation X axis
	 */
	public EnumFacing getRotationXAxis ();

	/**
	 * Sets the rotation X axis.
	 *
	 * @param facing the new rotation X axis
	 */
	public void setRotationXAxis (EnumFacing facing);
}