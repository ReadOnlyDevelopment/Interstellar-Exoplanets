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

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.romvoid95.api.registry.ExoRegistry;
import net.romvoid95.common.astronomy.kepler1649.KeplerBlocks;
import net.romvoid95.common.astronomy.trappist1.TrappistBlocks;
import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.block.decoration.BlockElectronic;
import net.romvoid95.common.block.decoration.BlockMetalDecoration;
import net.romvoid95.common.block.decoration.BlockMetalDiagonal;
import net.romvoid95.common.block.generic.BlockAlloy;
import net.romvoid95.common.block.generic.BlockMetal;
import net.romvoid95.common.block.machine.BlockAlloyRefinery;
import net.romvoid95.common.block.machine.BlockMetalFurnace;
import net.romvoid95.common.block.ore.BlockOreMetal;
import net.romvoid95.common.block.stairs.BlockRoofStairs;
import net.romvoid95.common.block.terrain.ExoLog;

public class ExoBlocks {

	private static ExoRegistry reg;

	public static Block launch_facility;
	public static Block launch_facility_full;

	public static Block fake;

	// ELECTRONIC
	public static final Block                raidcontroller       = new BlockElectronic();
	public static final Block                lower_raidcontroller = new BlockElectronic();
	public static final Block                raidcluster          = new BlockElectronic();
	public static final Block                datamonitor          = new BlockElectronic();
	public static final Block                com_relay            = new BlockElectronic();
	public static final Block                control              = new BlockElectronic();
	public static final Block                metal_diagonal       = new BlockMetalDiagonal();
	public static final Block                metal_slanted        = new BlockMetalDiagonal();
	public static final BlockMetalDecoration metaldecoration      = new BlockMetalDecoration();

	public static final Block metalOre   = new BlockOreMetal("metalore", true);
	public static final Block metalBlock = new BlockMetal();
	public static final Block alloyBlock = new BlockAlloy();

	public static final Block metalFurnace  = new BlockMetalFurnace();
	public static final Block alloyRefinery = new BlockAlloyRefinery();
	//public static final Block advAirlockControl = new BlockAdvAirLockController();

	public static final Block roof_stairs = new BlockRoofStairs();

	// LOGS
	public static final Block log_a_dark1 = new ExoLog();
	public static final Block log_a       = new ExoLog();
	public static final Block log_a_rough = new ExoLog();
	public static final Block log_a_dark2 = new ExoLog();
	public static final Block log_b       = new ExoLog();
	public static final Block log_b_other = new ExoLog();
	public static final Block log_b_rough = new ExoLog();

	public static LinkedList<Block> blocksList = new LinkedList<>();

	public static void registerAll (ExoRegistry reg) {
		setReg(reg);
		ExoFluids.init();
		YzCetiBlocks.registerAll();
		TrappistBlocks.registerAll();
		KeplerBlocks.registerAll();

		register(metalOre, "metalore");
		register(metalBlock, "metalblock");
		register(alloyBlock, "alloyblock");
		register(metalFurnace, "metalfurnace");
		register(alloyRefinery, "alloyrefinery");
		//register(advAirlockControl, "advancedairlockcontrol");
		register(metaldecoration, "metaldecoration", new BlockMetalDecoration.ItemBlock(metaldecoration));
		register(com_relay, "com_relay");
		register(control, "control");
		register(raidcontroller, "raidcontroller");
		register(lower_raidcontroller, "lower_raidcontroller");
		register(raidcluster, "raidcluster");
		register(datamonitor, "datamonitor");
		register(metal_diagonal, "metal_diagonal");
		register(roof_stairs, "roof_stairs");
		register(log_a_dark1, "log_a_dark1");
		register(log_a, "log_a");
		register(log_a_rough, "log_a_rough");
		register(log_a_dark2, "log_a_dark2");
		register(log_b, "log_b");
		register(log_b_other, "log_b_other");
		register(log_b_rough, "log_b_rough");
	}

	public static void register (Block block, String blockName) {
		blocksList.add(block);
		reg.registerBlock(block, blockName);
	}

	public static void register (Block block, String blockName, String path) {
		blocksList.add(block);
		reg.registerBlock(block, blockName, path);
	}

	public static void register (Block block, String blockName, ItemBlock itemBlock) {
		blocksList.add(block);
		reg.registerBlock(block, blockName, new ItemBlock(block));
	}

	public static void setReg (ExoRegistry reg) {
		ExoBlocks.reg = reg;
	}
}
