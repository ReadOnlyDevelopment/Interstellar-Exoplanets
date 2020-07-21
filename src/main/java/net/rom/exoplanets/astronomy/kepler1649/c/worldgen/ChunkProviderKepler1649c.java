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

package net.rom.exoplanets.astronomy.kepler1649.c.worldgen;

import java.util.List;

import com.google.common.collect.Lists;

import asmodeuscore.core.astronomy.dimension.world.gen.ChunkProviderSpaceLakes;
import asmodeuscore.core.astronomy.dimension.world.gen.MapGenCaves;
import asmodeuscore.core.astronomy.dimension.world.gen.MapGenCaves.BlockGen;
import micdoodle8.mods.galacticraft.api.prefab.core.BlockMetaPair;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.MapGenBaseMeta;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.exoplanets.astronomy.kepler1649.KeplerBlocks;

public class ChunkProviderKepler1649c extends ChunkProviderSpaceLakes {
	    
    private BlockGen STONE = new BlockGen(KeplerBlocks.Kepler1649C.kepler_stone.getDefaultState(), true);
    
    private final MapGenCaves caveGenerator = new MapGenCaves(STONE);

	public ChunkProviderKepler1649c(World world, long seed, boolean flag) {
		super(world, seed, flag);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		return new BiomeDecoratorKepler1649c();
	}

	@Override
	protected Biome[] getBiomesForGeneration() {
		return new Biome[] { BiomeAdaptive.biomeDefault };
	}

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {
		
	}

	@Override
	public void onPopulate(int x, int z) {
		
	}

	@Override
	protected List<MapGenBaseMeta> getWorldGenerators() {
		List<MapGenBaseMeta> generators = Lists.newArrayList();
		generators.add(this.caveGenerator);
		return generators;
	}

	@Override
	public double getHeightModifier() {
		 return 20;	
	}

	@Override
	public double getSmallFeatureHeightModifier() {
		return 1;
	}

	@Override
	public double getMountainHeightModifier() {
		return 0;
	}

	@Override
	public double getValleyHeightModifier() {
		return 0;
	}

	@Override
	public int getWaterLevel() {
		return 80;
	}

	@Override
	public boolean canGenerateWaterBlock() {
		return true;
	}

	@Override
	public boolean canGenerateIceBlock() {
		return true;
	}

	@Override
	public int getCraterProbability() {
		return 300;	
	}

	@Override
	protected BlockMetaPair getWaterBlock() {
		return null;
	}

	@Override
	protected BlockMetaPair getGrassBlock() {
		return new BlockMetaPair(KeplerBlocks.Kepler1649C.kepler_surface, (byte) 0);
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return new BlockMetaPair(KeplerBlocks.Kepler1649C.kepler_surface, (byte) 0);
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return new BlockMetaPair(KeplerBlocks.Kepler1649C.kepler_surface, (byte) 0);
	}

	@Override
	protected boolean enableBiomeGenBaseBlock() {
		return false;
	}

	@Override
	protected GenType getGenType() {
		return GenType.VANILLA;
	}


}
