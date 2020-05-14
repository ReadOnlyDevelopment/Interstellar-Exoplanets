package net.rom.exoplanets.astronomy.wolf1061.b;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorWolfB extends BiomeDecoratorSpace {

	private World currentWorld;

	public BiomeDecoratorWolfB() {

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
