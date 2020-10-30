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
package net.romvoid95.space.trappist1.d.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.space.trappist1.TrappistBlocks;

public class BiomeOceananic extends WE_Biome {
	
	private IBlockState TRAP1D_OCEANFLOOR = TrappistBlocks.TrappistD.TRAP1D_OCEANFLOOR.getDefaultState();
	private IBlockState TRAP1D_DIAMOND = TrappistBlocks.TrappistD.TRAP1D_DIAMOND.getDefaultState();

	public BiomeOceananic(double min, double max) {
		super(new BiomeProperties("trappist1d_deepocean"), new int[] { 0x89AC76, 0x116644, 0x985cff });

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
		biomePersistence        = 1.4D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 35;
		biomeInterpolateQuality = 65;
		biomeTemerature         = 0.0F;

		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(TRAP1D_OCEANFLOOR, 50, 0, 31, 0, true);
		standardBiomeLayers.add(TRAP1D_DIAMOND, 30, 0, 2, 0, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
