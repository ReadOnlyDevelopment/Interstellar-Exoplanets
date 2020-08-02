/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom.exoplanets.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraft.world.gen.feature.WorldGenMinable;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.IWorldGenerator;
import net.rom.exoplanets.content.EnumMetal;

public class OverworldOreGen implements IWorldGenerator {

	public WorldGenerator oreCopper, oreTin, oreLead, oreNickel, orePlatnium, oreAluminium, oreTitanium, oreTungsten;

	public OverworldOreGen() {
		oreCopper    = new WorldGenMinable(EnumMetal.COPPER.getOre(), 4);
		oreTin       = new WorldGenMinable(EnumMetal.TIN.getOre(), 5);
		oreLead      = new WorldGenMinable(EnumMetal.LEAD.getOre(), 3);
		oreNickel    = new WorldGenMinable(EnumMetal.NICKEL.getOre(), 5);
		orePlatnium  = new WorldGenMinable(EnumMetal.PLATINUM.getOre(), 2);
		oreAluminium = new WorldGenMinable(EnumMetal.ALUMINIUM.getOre(), 4);
		oreTitanium  = new WorldGenMinable(EnumMetal.TITANIUM.getOre(), 2);
		oreTungsten  = new WorldGenMinable(EnumMetal.TUNGSTEN.getOre(), 3);
		oreTungsten  = new WorldGenMinable(EnumMetal.SILVER.getOre(), 2);
		oreTungsten  = new WorldGenMinable(EnumMetal.ZINC.getOre(), 4);
	}

	private void runGenerator (WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
		if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
			throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

		int heightDiff = maxHeight - minHeight + 1;
		for (int i = 0; i < chancesToSpawn; i++) {
			int x = chunk_X * 16 + rand.nextInt(16);
			int y = minHeight + rand.nextInt(heightDiff);
			int z = chunk_Z * 16 + rand.nextInt(16);
			generator.generate(world, rand, new BlockPos(x, y, z));
		}
	}

	@Override
	public void generate (Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator, IChunkProvider chunkProvider) {
		switch (world.provider.getDimensionType().getId()) {
		case 0: //Overworld Dimension
			runGenerator(oreCopper, world, random, chunkX, chunkZ, 6, 35, 64);
			runGenerator(oreTin, world, random, chunkX, chunkZ, 4, 0, 64);
			runGenerator(oreLead, world, random, chunkX, chunkZ, 5, 0, 64);
			runGenerator(oreNickel, world, random, chunkX, chunkZ, 5, 0, 64);
			runGenerator(orePlatnium, world, random, chunkX, chunkZ, 1, 3, 25);
			runGenerator(oreAluminium, world, random, chunkX, chunkZ, 4, 45, 64);
			runGenerator(oreTitanium, world, random, chunkX, chunkZ, 2, 35, 75);
			runGenerator(oreTungsten, world, random, chunkX, chunkZ, 3, 6, 50);
		}

	}

}
