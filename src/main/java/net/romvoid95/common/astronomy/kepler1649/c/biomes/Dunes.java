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

package net.romvoid95.common.astronomy.kepler1649.c.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.core.ExoBlock;

public class Dunes extends WE_Biome {

	public Dunes(double min, double max) {
		super(new BiomeProperties("trappist1_e_dunes"), new int[] {
			0x89AC76, 0x11FF66, 0x00FF00
		});

		biomeMinValueOnMap      = min;
		biomeMaxValueOnMap      = max;
		biomePersistence        = 1.6D;
		biomeNumberOfOctaves    = 3;
		biomeScaleX             = 0.5D;
		biomeScaleY             = 0.5D;
		biomeSurfaceHeight      = 101;
		biomeInterpolateQuality = 8;

		//-//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(ExoBlock.TRAP1E_GRASS, ExoBlock.TRAP1E_STONE, -256, 0, -256, 0, false);
		standardBiomeLayers.add(ExoBlock.TRAP1E_DIRT, ExoBlock.TRAP1E_GRASS, -256, 0, -256, 0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}

}
