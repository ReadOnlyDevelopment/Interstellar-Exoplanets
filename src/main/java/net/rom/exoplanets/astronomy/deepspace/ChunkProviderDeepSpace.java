/**
 * Exoplanets
 * Copyright (C) 2020
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
package net.rom.exoplanets.astronomy.deepspace;

import java.util.List;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.world.ChunkProviderBase;
import micdoodle8.mods.galacticraft.core.GCBlocks;
import micdoodle8.mods.galacticraft.core.tile.IMultiBlock;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.block.BlockFalling;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.Biome.SpawnListEntry;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

public class ChunkProviderDeepSpace extends ChunkProviderBase {
	private final Random rand;

	private final World worldObj;

	public ChunkProviderDeepSpace(World par1World, long par2, boolean par4) {
		this.rand = new Random(par2);
		this.worldObj = par1World;
	}

	@Override
	public Chunk generateChunk(int x, int z) {
		ChunkPrimer chunkprimer = new ChunkPrimer();
		this.rand.setSeed(x * 341873128712L + z * 132897987541L);

		final Chunk var4 = new Chunk(this.worldObj, chunkprimer, x, z);

		final byte[] biomesArray = var4.getBiomeArray();
		for (int i = 0; i < biomesArray.length; ++i) {
			biomesArray[i] = (byte) Biome.getIdForBiome(BiomeOrbit.space);
		}

		var4.generateSkylightMap();
		return var4;
	}

	@Override
	public void populate(int x, int z) {
		BlockFalling.fallInstantly = true;
		final int k = x * 16;
		final int l = z * 16;
		this.rand.setSeed(this.worldObj.getSeed());
		final long i1 = this.rand.nextLong() / 2L * 2L + 1L;
		final long j1 = this.rand.nextLong() / 2L * 2L + 1L;
		this.rand.setSeed(x * i1 + z * j1 ^ this.worldObj.getSeed());
		if (k == 0 && l == 0) {
			BlockPos pos = new BlockPos(8, 64, 8);
			this.worldObj.setBlockState(pos, GCBlocks.spaceStationBase.getDefaultState(), 2);

			final TileEntity var8 = this.worldObj.getTileEntity(pos);

			if (var8 instanceof IMultiBlock) {
				((IMultiBlock) var8).onCreate(this.worldObj, pos);
			}
		}

		if (k == 0) {
			new WorldGenDeepSpace().generate(this.worldObj, this.rand, new BlockPos(k, 62, l));
		}
		BlockFalling.fallInstantly = false;
	}

	@Override
	public List<SpawnListEntry> getPossibleCreatures(EnumCreatureType creatureType, BlockPos pos) {
		return null;
	}

	@Override
	public void recreateStructures(Chunk p_180514_1_, int p_180514_2_, int p_180514_3_) {
	}
}
