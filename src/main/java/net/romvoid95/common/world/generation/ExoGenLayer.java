package net.romvoid95.common.world.generation;

import net.minecraft.world.gen.layer.GenLayer;

public abstract class ExoGenLayer extends GenLayer {

	public ExoGenLayer (long seed) {
		super(seed);
	}
	
	@Override
	public int nextInt(int a) {
		return super.nextInt(a);
	}

}
