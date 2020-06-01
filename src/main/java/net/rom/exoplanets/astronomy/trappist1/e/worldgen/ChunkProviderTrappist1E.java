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

package net.rom.exoplanets.astronomy.trappist1.e.worldgen;

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
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class ChunkProviderTrappist1E extends ChunkProviderSpaceLakes {
	    
    private BlockGen STONE = new BlockGen(TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState(), true);
    private BlockGen STONE2 = new BlockGen(TrappistBlocks.TrappistE.trap1e_stone.getDefaultState(), true);
    
    private final MapGenCaves caveGenerator = new MapGenCaves(STONE, STONE2);

	public ChunkProviderTrappist1E(World world, long seed, boolean flag) {
		super(world, seed, flag);
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
		
	}

	@Override
	protected BiomeDecoratorSpace getBiomeGenerator() {
		return new BiomeDecoratorTrappist1E();
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
		return new BlockMetaPair(TrappistBlocks.TrappistE.trap1e_grass, (byte) 0);
	}

	@Override
	protected BlockMetaPair getDirtBlock() {
		return new BlockMetaPair(TrappistBlocks.TrappistE.trap1e_dirt, (byte) 0);
	}

	@Override
	protected BlockMetaPair getStoneBlock() {
		return new BlockMetaPair(TrappistBlocks.TrappistE.trap1e_stone, (byte) 0);
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
