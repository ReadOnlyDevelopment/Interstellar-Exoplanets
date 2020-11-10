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

package net.romvoid95.space.trappist1;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.romvoid95.common.block.BlockTerrain;
import net.romvoid95.common.block.ore.BlockCompressedDiamond;
import net.romvoid95.common.block.ore.BlockOreMetal;
import net.romvoid95.common.block.terrain.BlockCrust;
import net.romvoid95.common.block.terrain.BlockCrust.RockType;
import net.romvoid95.common.block.terrain.BlockExoDirt;
import net.romvoid95.common.block.terrain.BlockExoGrass;
import net.romvoid95.core.initialization.ExoBlocks;

public class TrappistBlocks {

	public static void registerAll () {

		ExoBlocks.register(SharedTerrain.HOT_GROUND_1, "trap1_dark_hot");
		ExoBlocks.register(SharedTerrain.HOT_GROUND_2, "trap1_solid_hot");
		ExoBlocks.register(SharedTerrain.HOT_GROUND_3, "trap1_bright_solid_hot");

		ExoBlocks.register(TrappistC.TRAP1C_TOP, "trap1c_top");
		ExoBlocks.register(TrappistC.TRAP1C_DIRT_1, "trap1c_dirt_1");
		ExoBlocks.register(TrappistC.TRAP1C_DIRT_2, "trap1c_dirt_2");

		ExoBlocks.register(TrappistD.TRAP1D_OCEANFLOOR, "trap1d_oceanfloor");
		ExoBlocks.register(TrappistD.TRAP1D_STONE_1, "trap1d_stone1");
		ExoBlocks.register(TrappistD.TRAP1D_STONE_2, "trap1d_stone2");
		ExoBlocks.register(TrappistD.TRAP1D_SOFTSTONE, "trap1d_softstone");
		ExoBlocks.register(TrappistD.TRAP1D_DIAMOND, "compressed_diamond");
		ExoBlocks.register(TrappistD.TRAP1D_WETGRASS, "trap1d_wetgrass");
		ExoBlocks.register(TrappistD.TRAP1D_WETDIRT, "trap1d_wetdirt");

		ExoBlocks.register(TrappistE.TRAP1E_GRASS, "trap1e_grass");
		ExoBlocks.register(TrappistE.TRAP1E_DIRT, "trap1e_dirt");
		ExoBlocks.register(TrappistE.TRAP1E_COBBLESTONE, "trap1e_cobblestone");
		ExoBlocks.register(TrappistE.TRAP1E_STONE, "trap1e_stone");
		ExoBlocks.register(TrappistE.TRAP1E_ORE, "trap1eore");
		ExoBlocks.register(TrappistH.TRAP1H_FROZEN_CLOUD, "trap1h_frozen_cloud");

	}

	public static class SharedTerrain {

		public static final Block HOT_GROUND_1 = new BlockTerrain();
		public static final Block HOT_GROUND_2 = new BlockTerrain();
		public static final Block HOT_GROUND_3 = new BlockTerrain();
	}

	public static class TrappistB {

	}

	public static class TrappistC {

		public static final Block TRAP1C_TOP    = new BlockTerrain();
		public static final Block TRAP1C_DIRT_1 = new BlockTerrain();
		public static final Block TRAP1C_DIRT_2 = new BlockTerrain();
	}

	public static class TrappistD {

		public static final Block TRAP1D_OCEANFLOOR = new BlockCrust(RockType.IGNEOUS);
		public static final Block TRAP1D_STONE_1    = new BlockCrust(RockType.METAMORPHIC);
		public static final Block TRAP1D_STONE_2    = new BlockCrust(RockType.METAMORPHIC);
		public static final Block TRAP1D_SOFTSTONE  = new BlockCrust(RockType.SEDIMENTARY);
		public static final Block TRAP1D_DIAMOND    = new BlockCompressedDiamond();
		public static final Block TRAP1D_WETGRASS   = new BlockExoGrass();
		public static final Block TRAP1D_WETDIRT    = new BlockExoDirt();

	}

	public static class TrappistE {

		public static final Block TRAP1E_GRASS       = new BlockExoGrass();
		public static final Block TRAP1E_DIRT        = new BlockExoDirt();
		public static final Block TRAP1E_COBBLESTONE = new BlockCrust(RockType.METAMORPHIC);
		public static final Block TRAP1E_STONE       = new BlockCrust(RockType.METAMORPHIC);
		public static final Block TRAP1E_ORE         = new BlockOreMetal("trap1eore", false);
	}

	public static class TrappistF {

	}

	public static class TrappistG {

	}

	public static class TrappistH {
		
		public static final Block TRAP1H_FROZEN_CLOUD = new BlockTerrain();

	}
	
	public static IBlockState blockState(Block block) {
		return block.getDefaultState();
	}

}
