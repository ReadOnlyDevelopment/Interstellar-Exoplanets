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

package net.romvoid95.common.astronomy.yzceti.d;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;

import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;

import net.romvoid95.api.world.ExoBiomes;
import net.romvoid95.api.world.worldgen.feature.ExoFeatureBoulder;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.BiomeDecoratorYzCetiD;
import net.romvoid95.common.world.chunk.ExoChunkProvider;
import net.romvoid95.common.world.mapgen.MapGenBaseMeta;
import net.romvoid95.core.ExoBlock;

public class ChunkProviderYzCetiD extends ExoChunkProvider {

	private final BiomeDecoratorYzCetiD cetiDBiomeDecorator = new BiomeDecoratorYzCetiD();

	public ChunkProviderYzCetiD(World par1World, long seed, boolean mapFeaturesEnabled) {
		super(par1World, seed, mapFeaturesEnabled);
		this.stoneBlock = ExoBlock.YZD_SEDIMENTARYROCK;
		this.waterBlock = Blocks.PACKED_ICE.getDefaultState();
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators () {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		return generators;
	}

	@Override
	public int getCraterProbability () {
		return 2000;
	}

	@Override
	public void onChunkProvide (int cX, int cZ, ChunkPrimer primer) {
	}

	@Override
	public void onPopulate (int cX, int cZ) {

	}

	@Override
	public void recreateStructures (Chunk chunk, int x, int z) {}

	@Override
	protected void decoratePlanet (World world, Random rand, int x, int z) {
	}

	@Override
	protected void populate(BlockPos pos, ChunkPos chunkpos, Biome biome, int chunkX, int chunkZ, int x, int z) {
		for (int i = 0; i < 16; ++i) {
			new ExoFeatureBoulder(ExoBiomes.yzCeti_d_cliffs, 1, 3).generate(this.worldObj, this.rand, chunkpos);
		}
		this.cetiDBiomeDecorator.decorate(this.worldObj, rand, x, z);
	}

	@Override
	public boolean generateStructures(Chunk chunkIn, int x, int z) {
		return false;
	}

	@Override
	public BlockPos getNearestStructurePos(World worldIn, String structureName, BlockPos position,
			boolean findUnexplored) {
		return null;
	}

	@Override
	public boolean isInsideStructure(World worldIn, String structureName, BlockPos pos) {
		return false;
	}
}
