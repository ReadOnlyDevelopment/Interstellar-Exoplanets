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
package com.readonlydev.space.generation.chunkgen;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;

import javax.annotation.Nullable;

import com.readonlydev.space.generation.BiomeDecoratorExoBase;

import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldEntitySpawner;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.ForgeEventFactory;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;

public abstract class ChunkGeneratorVoid implements IChunkGenerator {
	protected final Random				rand;
	public final World					worldObj;
	private Biome[] biomesForGeneration = this.getBiomesForGeneration();
	private final Map<Integer, Random>	sizedRandoms	= new HashMap<>();

	public ChunkGeneratorVoid (World worldIn, long seed) {
		this.worldObj = worldIn;
		this.rand = new Random(seed);
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.biomesForGeneration = this.worldObj.getBiomeProvider().getBiomes(this.biomesForGeneration, x * 16, z * 16,
				16, 16);
		ForgeEventFactory.onReplaceBiomeBlocks(this, x, z, chunkprimer, this.worldObj);

		// Set seed on all randoms
		for (final Entry<Integer, Random> entry : this.sizedRandoms.entrySet()) {
			final int regionSize = entry.getKey();
			final int regionX = (int) Math.floor(x * 16D / regionSize);
			final int regionZ = (int) Math.floor(z * 16D / regionSize);
			entry.getValue().setSeed((regionX) * 341873128712L + (regionZ) * 132897987541L + this.worldObj.getSeed());
		}

		final Chunk chunk = new Chunk(this.worldObj, chunkprimer, x, z);
		byte[] abyte = chunk.getBiomeArray();

		for (int i = 0; i < abyte.length; ++i) {
			abyte[i] = (byte) Biome.getIdForBiome(this.biomesForGeneration[i]);
		}

		return chunk;
	}

	public void decoratePlanet(World par1World, Random par2Random, BlockPos pos) {
		this.getBiomeGenerator().decorate(par1World, par2Random, null, pos);
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		int i = x * 16;
		int j = z * 16;
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);

		ForgeEventFactory.onChunkPopulate(true, this, this.worldObj, this.rand, x, z, false);

		// Set seed on all randoms
		for (final Entry<Integer, Random> entry : this.sizedRandoms.entrySet()) {
			final int regionSize = entry.getKey();
			final int regionX = (int) Math.floor(x * 16D / regionSize);
			final int regionZ = (int) Math.floor(z * 16D / regionSize);
			entry.getValue().setSeed((regionX) * 341873128712L + (regionZ) * 132897987541L + this.worldObj.getSeed());
		}

		WorldEntitySpawner.performWorldGenSpawning(this.worldObj,
				this.worldObj.getBiome(new BlockPos(i + 16, 0, j + 16)), i + 8, j + 8, 16, 16, this.rand);

		this.decoratePlanet(this.worldObj, this.rand, new BlockPos(i, 0, j));

		ForgeEventFactory.onChunkPopulate(false, this, this.worldObj, this.rand, x, z, false);

		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<Biome.SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		if (this.worldObj.getChunkFromBlockCoords(pos).isEmpty()) {
			return null;
		}
		Biome biome = this.worldObj.getBiome(pos);

		return biome.getSpawnableList(creatureType);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {

	}

	@Override
	@Nullable
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}

	/**
	 * Do not return null
	 *
	 * @return The biome generator for this world, handles ore, flower, etc generation. See GCBiomeDecoratorBase.
	 */
	protected abstract BiomeDecoratorExoBase getBiomeGenerator();

	/**
	 * Do not return null, have at least one biome for generation
	 *
	 * @return Biome instance for generation
	 */
	protected abstract Biome[] getBiomesForGeneration();
}