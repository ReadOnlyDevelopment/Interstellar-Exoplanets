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

package net.romvoid95.common.astronomy.trappist1.e.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.romvoid95.core.States;

public class Trappist1_E_Mountains extends WE_Biome {

	public Trappist1_E_Mountains() {
		super(new BiomeProperties("trappist1_e_mountains"), new int[] { 0x55BB44, 0x44FFAA, 0x00FF00 });

		biomeMinValueOnMap      = 0.8D;
		biomeMaxValueOnMap      = 1.4D;
		biomePersistence        = 1.5D;
		biomeNumberOfOctaves    = 5;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 100;
		biomeInterpolateQuality = 15;

		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(States.TRAP1E_DIRT, States.TRAP1E_STONE, -256, 0, -4, -1, true);
		standardBiomeLayers.add(States.TRAP1E_GRASS, States.TRAP1E_DIRT, -256, 0, -256, 0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);

		//		WE_SnowGen snowGen = new WE_SnowGen();
		//		snowGen.snowPoint       = 120;
		//		snowGen.randomSnowPoint = 8;
		//		snowGen.snowBlock       = Blocks.SNOW.getDefaultState();
		//		snowGen.iceBlock        = Blocks.ICE.getDefaultState();
		//		snowGen.freezeMaterial  = Material.WATER;
		//		createChunkGen_InXZ_List.add(snowGen);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {

	}
}
