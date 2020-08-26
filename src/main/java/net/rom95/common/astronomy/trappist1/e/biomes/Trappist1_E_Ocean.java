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

package net.rom95.common.astronomy.trappist1.e.biomes;

import java.util.Random;

import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_BiomeLayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.rom95.api.world.worldgen.feature.ExoTreeFlatTop;
import net.rom95.common.astronomy.trappist1.TrappistBlocks;
import net.rom95.common.world.helpers.GenUtility;

public class Trappist1_E_Ocean extends WE_Biome {

	public Trappist1_E_Ocean(boolean frozen) {
		super(new BiomeProperties("trappist1_e_ocean" + (frozen ? "_frozen" : "")), new int[] { 0x55BB44, 0x11FF66,
				0x00FF00 });

		biomeMinValueOnMap      = -0.8D;
		biomeMaxValueOnMap      = -0.4D;
		biomePersistence        = 1.4D;
		biomeNumberOfOctaves    = 4;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 45;
		biomeInterpolateQuality = 65;

		//-//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		//		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), TrappistBlocks.TrappistE.trap1e_stone.getDefaultState(), -256, 0,   -5, -1,  true);
		//		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState(), TrappistBlocks.TrappistE.trap1e_dirt.getDefaultState(), -256, 0,   -1, -1,  true);
		//		standardBiomeLayers.add(TrappistBlocks.TrappistE.trap1e_grass.getDefaultState(), TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState(), -256, 0, -256,  0, false);
		//		
		if (frozen)
			standardBiomeLayers.add(Blocks.ICE.getDefaultState(), 64, 0, -1, 0, true);

		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);
	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {
		int      randPosX = x + rand.nextInt(16) + 8;
		int      randPosZ = z + rand.nextInt(16) + 8;
		BlockPos pos      = world.getHeight(new BlockPos(randPosX, 0, randPosZ));
		for (int i = 0; i < 8; i++) {
			randPosX = x + rand.nextInt(16) + 8;
			randPosZ = z + rand.nextInt(16) + 8;
			pos      = world.getHeight(new BlockPos(randPosX, 0, randPosZ));

			if (!world.isAreaLoaded(pos, 16, false))
				switch (rand.nextInt(2)) {
				case 0:
					new GenUtility().generateDome(Blocks.GLASS.getDefaultState(), 10, pos);
					break;
				case 1:
					break;

				}
		}
	}
}
