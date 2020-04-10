package net.rom.exoplanets.util;

import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;

public class WorldPosition {

	public static final WorldPosition ZERO = new WorldPosition(0, 0, 0, 0);

	public final int x, y, z, dim;

	public WorldPosition(BlockPos pos, int dim) {
		this(pos.getX(), pos.getY(), pos.getZ(), dim);

	}

	public WorldPosition(int x, int y, int z, int dim) {
		this.x = x;
		this.y = y;
		this.z = z;
		this.dim = dim;
	}

	public static WorldPosition readFromNBT(NBTTagCompound tags) {
		return new WorldPosition(tags.getInteger("posX"), tags.getInteger("posY"), tags.getInteger("posZ"),
				tags.getInteger("dim"));
	}

	public void writeToNBT(NBTTagCompound tags) {
		tags.setInteger("posX", x);
		tags.setInteger("posY", y);
		tags.setInteger("posZ", z);
		tags.setInteger("dim", dim);
	}

	public BlockPos toBlockPos() {
		return new BlockPos(x, y, z);
	}

	@Override
	public String toString() {
		return String.format("(%d, %d, %d) in dimension %d", x, y, z, dim);
	}

	@Override
	public boolean equals(Object other) {
		if (!(other instanceof WorldPosition)) {
			return false;
		}
		WorldPosition pos = (WorldPosition) other;
		return x == pos.x && y == pos.y && z == pos.z && dim == pos.dim;
	}
}
