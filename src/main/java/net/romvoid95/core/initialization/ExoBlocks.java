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

package net.romvoid95.core.initialization;

import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.item.ItemBlock;
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.common.block.decoration.BlockElectronic;
import net.romvoid95.common.block.decoration.BlockMetalDecoration;
import net.romvoid95.common.block.decoration.BlockMetalDiagonal;
import net.romvoid95.common.block.generic.BlockAlloy;
import net.romvoid95.common.block.generic.BlockMetal;
import net.romvoid95.common.block.machine.BlockAlloyRefinery;
import net.romvoid95.common.block.machine.BlockMetalFurnace;
import net.romvoid95.common.block.ore.BlockOreMetal;
import net.romvoid95.common.block.stairs.BlockRoofStairs;
import net.romvoid95.common.block.terrain.foliage.BlockDoubleFoliage;
import net.romvoid95.common.block.terrain.foliage.BlockExoFoliage;
import net.romvoid95.common.block.terrain.tree.BlockExoLeaves;
import net.romvoid95.common.block.terrain.tree.BlockExoLog;
import net.romvoid95.common.block.terrain.tree.BlockExoSapling;
import net.romvoid95.space.kepler1649.KeplerBlocks;
import net.romvoid95.space.trappist1.TrappistBlocks;
import net.romvoid95.space.wolf1061.Wolf1061Blocks;
import net.romvoid95.space.yzceti.YzCetiBlocks;

public class ExoBlocks {

	private static ExoRegistry reg;

	public static Block	launch_facility;
	public static Block	launch_facility_full;

	public static Block fake;

	// ELECTRONIC
	public static final Block	RAIDCONTROLLER			= new BlockElectronic();
	public static final Block	LOWER_RAIDCONTROLLER	= new BlockElectronic();
	public static final Block	RAIDCLUSTER				= new BlockElectronic();
	public static final Block	DATAMONITOR				= new BlockElectronic();
	public static final Block	COMRELAY				= new BlockElectronic();
	public static final Block	control					= new BlockElectronic();
	public static final Block	METAL_DIAGONAL			= new BlockMetalDiagonal();
	public static final Block	metal_slanted			= new BlockMetalDiagonal();

	public static final Block	GRATING				= new BlockMetalDecoration();
	public static final Block	GRATING_STRIPE		= new BlockMetalDecoration();
	public static final Block	HEAVY_BORDER_METAL	= new BlockMetalDecoration();
	public static final Block	LIGHT_BORDER_METAL	= new BlockMetalDecoration();
	public static final Block	PATTERN_METAL		= new BlockMetalDecoration();
	public static final Block	PATTERN_METAL_1		= new BlockMetalDecoration();
	public static final Block	RIVET_METAL			= new BlockMetalDecoration();
	public static final Block	METAL_FAN			= new BlockMetalDecoration();

	public static final Block	OVERWORLD_ORE	= new BlockOreMetal("metalore", true);
	public static final Block	METALBLOCK	= new BlockMetal();
	public static final Block	ALLOYBLOCK	= new BlockAlloy();

	public static final Block	METALFURNACE	= new BlockMetalFurnace();
	public static final Block	ALLOYREFINERY	= new BlockAlloyRefinery();
	// public static final Block advAirlockControl = new BlockAdvAirLockController();

	public static final Block ROOF_SLANTED = new BlockRoofStairs();
	
	// PLANT
	public static final Block EXO_LOG = new BlockExoLog();
	public static final Block EXO_PLANT = new BlockExoFoliage();
	public static final Block EXO_LEAVES = new BlockExoLeaves();
	public static final BlockBush EXO_SAPLING = new BlockExoSapling();
	public static final Block EXO_TALL_PLANT = new BlockDoubleFoliage();

	//public static LinkedList<Block> blocksList = new LinkedList<>();

	public static void registerAll(ExoRegistry reg) {
		setReg(reg);
		ExoFluids.init();
		YzCetiBlocks.registerAll();
		TrappistBlocks.registerAll();
		KeplerBlocks.registerAll();
		Wolf1061Blocks.registerAll();

		register(OVERWORLD_ORE, "metalore");
		register(METALBLOCK, "metalblock");
		register(ALLOYBLOCK, "alloyblock");
		register(METALFURNACE, "metalfurnace");
		register(ALLOYREFINERY, "alloyrefinery");

		register(EXO_PLANT, "exo_foliage");
		register(COMRELAY, "com_relay");
		register(control, "control");
		register(RAIDCONTROLLER, "raidcontroller");
		register(LOWER_RAIDCONTROLLER, "lower_raidcontroller");
		register(RAIDCLUSTER, "raidcluster");
		register(DATAMONITOR, "datamonitor");
		register(METAL_DIAGONAL, "metal_diagonal");
		register(ROOF_SLANTED, "roof_slanted");
		//register(EXO_PLANT, "exo_plant");
		register(EXO_LEAVES, "exo_leaves");
		register(EXO_SAPLING, "exo_sapling");
		register(EXO_LOG, "exo_log");
		register(EXO_TALL_PLANT, "exo_tallplant");
	}

	public static void register(Block block, String blockName) {
		//blocksList.add(block);
		reg.registerBlock(block, blockName);
	}

	public static void register(Block block, String blockName, String path) {
		//blocksList.add(block);
		reg.registerBlock(block, blockName, path);
	}

	public static void register(Block block, String blockName, ItemBlock itemBlock) {
		//blocksList.add(block);
		reg.registerBlock(block, blockName, new ItemBlock(block));
	}

	public static void setReg(ExoRegistry reg) {
		ExoBlocks.reg = reg;
	}
}
