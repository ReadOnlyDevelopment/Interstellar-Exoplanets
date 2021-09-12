package com.readonlydev.space.generation;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class WorldGeneratorAbstract extends WorldGenerator {

	@Override
	public final boolean generate(World world, Random random, BlockPos pos) {
		return generate(world, random, pos, false);
	}

	public boolean generate(World world, Random random, BlockPos pos, boolean forced) {
		return false;
	}
}
