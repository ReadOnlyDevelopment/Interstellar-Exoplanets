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

package net.rom.exoplanets.astronomy.trappist1.e.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class Trappist1_E_Dunes extends WE_Biome {

	public Trappist1_E_Dunes() {
		super(new BiomeProperties("trappist1_e_dunes"), new int[] {0x89AC76, 0x11FF66, 0x00FF00});
		
		biomeMinValueOnMap      =   0.0D;
		biomeMaxValueOnMap      =   0.4D;
		biomePersistence        =   1.6D;
		biomeNumberOfOctaves    =      4;
		biomeScaleX             = 80.0D;
		biomeScaleY             =   0.7D;
		biomeSurfaceHeight      =     75;
		biomeInterpolateQuality =     15;
		
		//-//
		decorateChunkGen_List.clear();		
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_grass.getDefaultState(), TrappistBlocks.TrappistE.trap1e_stone.getDefaultState(), -256, 0, -256,  0, false);
		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), TrappistBlocks.TrappistE.trap1e_grass.getDefaultState(), -256, 0, -256,  0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}
	
	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}

}
