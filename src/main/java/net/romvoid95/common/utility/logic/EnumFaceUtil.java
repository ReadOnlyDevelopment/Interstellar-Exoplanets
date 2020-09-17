package net.romvoid95.common.utility.logic;

import lombok.experimental.UtilityClass;
import net.minecraft.util.EnumFacing;

@UtilityClass
public class EnumFaceUtil {
	/**
	 * Gets the rotation count for the facing.
	 *
	 * @param facing the facing
	 * @return the rotation count
	 */
	public static int getRotationCount (EnumFacing facing) {
		if (facing == null)
			return 0;

		switch (facing) {
		case EAST:
			return 1;
		case NORTH:
			return 2;
		case WEST:
			return 3;
		case SOUTH:
		default:
			return 0;
		}
	}

	/**
	 * Rotates facing {@code count} times.
	 *
	 * @param facing the facing
	 * @param count the count
	 * @return the enum facing
	 */
	public static EnumFacing rotateFacing (EnumFacing facing, int count) {
		if (facing == null)
			return null;

		while (count-- > 0)
			facing = facing.rotateAround(EnumFacing.Axis.Y);
		return facing;
	}

}
