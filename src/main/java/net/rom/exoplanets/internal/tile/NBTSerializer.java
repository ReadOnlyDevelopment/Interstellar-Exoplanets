package net.rom.exoplanets.internal.tile;

import net.minecraft.nbt.NBTTagCompound;

public interface NBTSerializer<T> {
	T read(NBTTagCompound tags);

	void write(NBTTagCompound tags, T obj);
}
