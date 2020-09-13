package net.romvoid95.api.world;

import java.util.List;

import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;

public interface IExoProvider {

	public default Vector3 getFogColor () {
		return new Vector3();
	}

	public default Vector3 getSkyColor () {
		return new Vector3();
	}

	public default int getDungeonSpacing () {
		return 0;
	}

	public default ResourceLocation getDungeonChestType () {
		return null;
	}

	public default List<Block> getSurfaceBlocks () {
		return null;
	}

}
