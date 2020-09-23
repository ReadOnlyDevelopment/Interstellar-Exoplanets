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

package net.romvoid95.common.astronomy.trappist1.d.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.core.ExoBlock;

public class Trap1D_Island extends WE_Biome {

	public Trap1D_Island(double min, double max) {
		super(new BiomeProperties("trappist1_d_island"), new int[] { 0x55BB44, 0x44FFAA, 0x00FF00 });

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
		biomePersistence        = 2.4D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 114;
		biomeInterpolateQuality = 35;

		//-//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(ExoBlock.TRAP1D_WETDIRT, ExoBlock.TRAP1D_STONE_2, -256, 0, -5, -1, true);
		standardBiomeLayers.add(ExoBlock.TRAP1D_WETGRASS, ExoBlock.TRAP1D_WETDIRT, -256, 0, -256, 0, false);
		standardBiomeLayers.add(ExoBlock.TRAP1D_OCEANFLOOR, 50, 0, 31, 0, true);
		standardBiomeLayers.add(ExoBlock.TRAP1D_DIAMOND, 30, 0, 2, 0, true);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
