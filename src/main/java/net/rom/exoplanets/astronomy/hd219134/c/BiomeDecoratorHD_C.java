package net.rom.exoplanets.astronomy.hd219134.c;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorHD_C extends BiomeDecoratorSpace {

	private World currentWorld;

	public BiomeDecoratorHD_C() {

	}

	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}

	@Override
	protected void decorate() {

	}

}
