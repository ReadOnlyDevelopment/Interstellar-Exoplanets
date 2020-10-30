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
package net.romvoid95.space.wolf1061.c;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.space.astrogeneration.BiomeDecoratorEmpty;
import net.romvoid95.space.astrogeneration.chunkgen.ChunkProvdierNewSpace;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;

public class ChunkProviderWolf1061C extends ChunkProvdierNewSpace {
	
	private final BiomeDecoratorEmpty biomeDecorator = new BiomeDecoratorEmpty();
	
	public ChunkProviderWolf1061C(World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		return this.biomeDecorator;
	}

	@Override
	protected Biome[] getBiomesForGeneration() {
		return new Biome[] { BiomeAdaptive.biomeDefault };
	}

	@Override
	protected int getSeaLevel() {
		return 63;
	}

	@Override
	protected Block getGrassBlock() {
		return Wolf1061Blocks.Wolf1061C.WOLFC_COBBLESTONE;
	}

	@Override
	protected Block getDirtBlock() {
		return Wolf1061Blocks.Wolf1061C.WOLFC_HARDENED_SAND;
	}

	@Override
	protected Block getStoneBlock() {
		return Wolf1061Blocks.Wolf1061C.WOLFC_STONE;
	}

	@Override
	public double getHeightModifier() {
		return 12;
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 26;
	}

	@Override
	public double getMountainHeightModifier() {
		return 95;
	}

	@Override
	public double getValleyHeightModifier() {
		return 50;
	}

	@Override
	public int getCraterProbability() {
		return 2000;
	}

	@Override
	public void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {

	}
	@Override
	public void onPopulate(int cX, int cZ) {

	}

	@Override
	public void recreateStructures(Chunk chunk, int x, int z) {

	}
}
