/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
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
		oreCopper = new WorldGenMinable(EnumMetal.COPPER.getOre(), 4);
		oreTin = new WorldGenMinable(EnumMetal.TIN.getOre(), 5);
		oreLead = new WorldGenMinable(EnumMetal.LEAD.getOre(), 3);
		oreNickel = new WorldGenMinable(EnumMetal.NICKEL.getOre(), 5);
		orePlatnium = new WorldGenMinable(EnumMetal.PLATINUM.getOre(), 2);
		oreAluminium = new WorldGenMinable(EnumMetal.ALUMINIUM.getOre(), 4);
		oreTitanium = new WorldGenMinable(EnumMetal.TITANIUM.getOre(), 2);
		oreTungsten = new WorldGenMinable(EnumMetal.TUNGSTEN.getOre(), 3);
	}
	
	private void runGenerator(WorldGenerator generator, World world, Random rand, int chunk_X, int chunk_Z, int chancesToSpawn, int minHeight, int maxHeight) {
	    if (minHeight < 0 || maxHeight > 256 || minHeight > maxHeight)
	        throw new IllegalArgumentException("Illegal Height Arguments for WorldGenerator");

	    int heightDiff = maxHeight - minHeight + 1;
	    for (int i = 0; i < chancesToSpawn; i ++) {
	        int x = chunk_X * 16 + rand.nextInt(16);
	        int y = minHeight + rand.nextInt(heightDiff);
	        int z = chunk_Z * 16 + rand.nextInt(16);
	        generator.generate(world, rand, new BlockPos(x, y, z));
	    }
	}

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world, IChunkGenerator chunkGenerator,
			IChunkProvider chunkProvider) {
		switch(world.provider.getDimensionType().getId()) {
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
