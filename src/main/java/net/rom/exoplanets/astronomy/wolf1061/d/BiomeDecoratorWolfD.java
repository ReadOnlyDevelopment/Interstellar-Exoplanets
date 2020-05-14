package net.rom.exoplanets.astronomy.wolf1061.d;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.world.World;

public class BiomeDecoratorWolfD extends BiomeDecoratorSpace {

	private World currentWorld;

	public BiomeDecoratorWolfD() {

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
