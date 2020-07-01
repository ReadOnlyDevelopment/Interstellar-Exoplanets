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

package net.rom.exoplanets.astronomy.trappist1.e.biomes;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_BiomeLayer;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_LakeGen;
import net.minecraft.init.Blocks;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class Trappist1_E_Plains extends WE_Biome {
	
	public Trappist1_E_Plains(double min, double max) {
		super(new BiomeProperties("trappist1_e_plains"), new int[] {0x88BB44, 0x11FF66, 0x00FF00});
				
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
		//standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), -256, 0, -256,  0, false);
		//standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_grass.getDefaultState(), TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), -256, 0, 0,  0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
				
		WE_LakeGen lakes = new WE_LakeGen();
		lakes.lakeBlock = Blocks.WATER.getDefaultState();
		lakes.lakeBlock_f  = Blocks.ICE.getDefaultState();
		lakes.chunksForLake = 8;
		decorateChunkGen_List.add(lakes);
	}
}
