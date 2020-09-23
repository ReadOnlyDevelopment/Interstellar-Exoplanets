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
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.romvoid95.api.world.worldgen.feature.ExoSempervirens;
import net.romvoid95.api.world.worldgen.feature.ExoTreeFlatTop;
import net.romvoid95.core.ExoBlock;
import net.romvoid95.core.initialization.ExoBlocks;

public class Trappist1_E_Plains extends WE_Biome {

	public Trappist1_E_Plains() {
		super(new BiomeProperties("trappist1e_plains"), new int[] {
			0xd14715, 0x11FF66, 0x00FF00
		});

		biomeMinValueOnMap      = -0.4D;
		biomeMaxValueOnMap      = 0.0D;
		biomePersistence        = 1.4D;
		biomeNumberOfOctaves    = 5;
		biomeScaleX             = 280.0D;
		biomeScaleY             = 1.7D;
		biomeSurfaceHeight      = 80;
		biomeInterpolateQuality = 15;

		//-//
		decorateChunkGen_List.clear();
		createChunkGen_InXZ_List.clear();

		WE_BiomeLayer standardBiomeLayers = new WE_BiomeLayer();
		standardBiomeLayers.add(ExoBlock.TRAP1E_DIRT, ExoBlock.TRAP1E_STONE, -256, 0, -4, -1, true);
		standardBiomeLayers.add(ExoBlock.TRAP1E_GRASS, ExoBlock.TRAP1E_DIRT, -256, 0, -256, 0, false);
		standardBiomeLayers.add(Blocks.BEDROCK.getDefaultState(), 0, 2, 0, 0, true);
		createChunkGen_InXZ_List.add(standardBiomeLayers);

	}

	@Override
	public void decorateBiome (World world, Random rand, int x, int z) {
		int      randPosX = x + rand.nextInt(16) + 8;
		int      randPosZ = z + rand.nextInt(16) + 8;
		BlockPos pos      = world.getHeight(new BlockPos(randPosX, 0, randPosZ));

		boolean cangen = true;
		Block   log    = ExoBlocks.log_b;
		Block   leaves = Blocks.LEAVES2;

		for (BlockPos pos1 : BlockPos.getAllInBox(pos.add(-3, -1, -3), pos.add(3, -1, 3)))
			if (world.isAirBlock(pos1))
				cangen = false;

		if (!world.isAreaLoaded(pos, 16, false))
			if (cangen && world.getBlockState(pos.down()) == ExoBlock.TRAP1E_GRASS) {
				switch (rand.nextInt(2)) {
				case 0:
					new ExoSempervirens(log, leaves).generate(world, rand, pos);
					break;
				case 1:
					new ExoTreeFlatTop(log, leaves).generate(world, rand, pos);
					break;
				}
			}

		for (int i = 0; i < 8; i++) {
			randPosX = x + rand.nextInt(16) + 8;
			randPosZ = z + rand.nextInt(16) + 8;
			pos      = world.getHeight(new BlockPos(randPosX, 0, randPosZ));

			cangen = true;
			for (BlockPos pos1 : BlockPos.getAllInBox(pos.add(-3, -1, -3), pos.add(3, -1, 3)))
				if (world.isAirBlock(pos1) || world.getBlockState(pos1) == ExoBlock.TRAP1E_GRASS)
					cangen = false;

			if (!world.isAreaLoaded(pos, 16, false))
				if (cangen && world.getBlockState(pos.down()) == ExoBlock.TRAP1E_GRASS) {
					switch (rand.nextInt(2)) {
					case 0:
						new ExoTreeFlatTop(log, leaves).generate(world, rand, pos);
						break;
					case 1:
						new ExoTreeFlatTop(log, leaves).generate(world, rand, pos);
						break;
					}
				}
		}

		for (int i = 0; i < 3; i++) {
			randPosX = x + rand.nextInt(16) + 8;
			//randPosY = this.rand.nextInt(256);
			randPosZ = z + rand.nextInt(16) + 8;
			pos      = world.getHeight(new BlockPos(randPosX, 0, randPosZ));

			if (world.getBlockState(pos.down()) == ExoBlock.TRAP1E_GRASS)
				world.setBlockState(pos, Blocks.DEADBUSH.getDefaultState());
		}
	}
}
