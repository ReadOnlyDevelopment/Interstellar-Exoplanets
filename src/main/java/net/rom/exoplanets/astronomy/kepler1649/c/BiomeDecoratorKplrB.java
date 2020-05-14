package net.rom.exoplanets.astronomy.kepler1649.c;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorKplrB extends BiomeDecoratorSpace {

	private World currentWorld;

	public BiomeDecoratorKplrB() {

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
