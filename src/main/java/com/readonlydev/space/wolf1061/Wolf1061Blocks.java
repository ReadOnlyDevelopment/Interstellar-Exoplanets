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
package com.readonlydev.space.wolf1061;

import com.readonlydev.common.block.terrain.BlockCrust;
import com.readonlydev.common.block.terrain.BlockSediment;
import com.readonlydev.common.block.terrain.BlockCrust.RockType;
import com.readonlydev.core.ExoBlocks;

import net.minecraft.block.Block;

public class Wolf1061Blocks {

	public static void registerAll() {
		ExoBlocks.register(Wolf1061B.WOLFB_COBBLESTONE, "wolfb_cobblestone");
		ExoBlocks.register(Wolf1061B.WOLFB_STONE, "wolfb_stone");

		ExoBlocks.register(Wolf1061C.WOLFC_COBBLESTONE, "wolfc_cobblestone");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE, "wolfc_stone");
		ExoBlocks.register(Wolf1061C.WOLFC_CLAY, "wolfc_clay");
		ExoBlocks.register(Wolf1061C.WOLFC_IGNEOUS_STONE, "wolfc_igneous_stone");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY, "wolfc_stone_clay");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY_ORANGE, "wolfc_stone_clay_orange");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY_SILVER, "wolfc_stone_clay_silver");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY_BROWN, "wolfc_stone_clay_brown");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY_GRAY, "wolfc_stone_clay_gray");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_CLAY_WHITE, "wolfc_stone_clay_white");
		ExoBlocks.register(Wolf1061C.WOLFC_STONE_QUARTZ, "wolfc_stone_quartz");
		ExoBlocks.register(Wolf1061C.WOLFC_HARDENED_SAND, "wolfc_hardened_sand");

	}

	public static class Wolf1061B {
		public static final Block	WOLFB_COBBLESTONE	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFB_STONE			= new BlockCrust(RockType.METAMORPHIC);
	}

	public static class Wolf1061C {
		public static final Block	WOLFC_COBBLESTONE		= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE				= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_CLAY				= new BlockSediment();
		public static final Block	WOLFC_IGNEOUS_STONE		= new BlockCrust(RockType.IGNEOUS);
		public static final Block	WOLFC_STONE_CLAY		= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_CLAY_ORANGE	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_CLAY_SILVER	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_CLAY_BROWN	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_CLAY_GRAY	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_CLAY_WHITE	= new BlockCrust(RockType.METAMORPHIC);
		public static final Block	WOLFC_STONE_QUARTZ		= new BlockCrust(RockType.IGNEOUS);
		public static final Block	WOLFC_HARDENED_SAND		= new BlockCrust(RockType.SEDIMENTARY);
	}

	public static class Wolf1061D {

	}

}
