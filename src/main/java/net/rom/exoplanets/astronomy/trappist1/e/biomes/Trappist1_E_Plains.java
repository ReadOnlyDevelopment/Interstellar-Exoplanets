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

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_BiomeLayer;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_LakeGen;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class Trappist1_E_Plains extends WE_Biome {
	
	public Trappist1_E_Plains(double min, double max) {
		super(new BiomeProperties("trappist1e_plains"), new int[] {0xd14715, 0x11FF66, 0x00FF00});
				
		biomeMinValueOnMap      =  	min;
		biomeMaxValueOnMap      =   max;
		biomePersistence        =   1.4D;
		biomeNumberOfOctaves    =      4;
		biomeScaleX             = 280.0D;
		biomeScaleY             =   1.7D;
		biomeSurfaceHeight      =     75;
		biomeInterpolateQuality =     25;
		
		//-//
		decorateChunkGen_List.clear();		
		createChunkGen_InXZ_List.clear();
						
		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState(), TrappistBlocks.TrappistE.trap1e_stone.getDefaultState(), -256, 0,   -5, -1,  true);
		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_grass.getDefaultState(), TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState(), -256, 0,   -1, -1,  true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
				
		WE_LakeGen lakes = new WE_LakeGen();
		lakes.lakeBlock = Blocks.WATER.getDefaultState();
		lakes.lakeBlock_f  = Blocks.ICE.getDefaultState();
		lakes.chunksForLake = 8;
		decorateChunkGen_List.add(lakes);
	}
}
