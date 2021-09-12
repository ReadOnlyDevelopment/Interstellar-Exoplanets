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

package com.readonlydev.space.trappist1.c.worldegnine;

import java.util.Random;

import com.readonlydev.space.trappist1.TrappistBlocks;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;

public class Trappist1C_River extends WE_Biome {

	public Trappist1C_River(double min, double max) {
		super(new BiomeProperties("trappist_1_c_river"), new int[] { 0x88BB44, 0x11FF66, 0x00FF00 });

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
		biomePersistence        = 1.1D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 50;
		biomeInterpolateQuality = 30;

		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(TrappistBlocks.SharedTerrain.HOT_GROUND_2.getDefaultState(), TrappistBlocks.TrappistC.TRAP1C_TOP.getDefaultState(), -256, 35, -256, 0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
