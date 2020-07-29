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

package net.rom.exoplanets.init;

import java.util.LinkedList;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.astronomy.kepler1649.KeplerBlocks;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.content.block.decoration.BlockAlarmLight;
import net.rom.exoplanets.content.block.decoration.BlockCellarLamp;
import net.rom.exoplanets.content.block.decoration.BlockCustomHydraulic;
import net.rom.exoplanets.content.block.decoration.BlockCustomLever;
import net.rom.exoplanets.content.block.decoration.BlockElectronic;
import net.rom.exoplanets.content.block.decoration.BlockInsetLamp;
import net.rom.exoplanets.content.block.decoration.BlockMetalDecoration;
import net.rom.exoplanets.content.block.decoration.BlockMetalDiagonal;
import net.rom.exoplanets.content.block.decoration.BlockMetalLamp;
import net.rom.exoplanets.content.block.decoration.BlockSatelliteAntenna;
import net.rom.exoplanets.content.block.decoration.BlockStandConsole;
import net.rom.exoplanets.content.block.decoration.BlockWallLamp;
import net.rom.exoplanets.content.block.generic.BlockAlloy;
import net.rom.exoplanets.content.block.generic.BlockMetal;
import net.rom.exoplanets.content.block.machine.BlockAlloyRefinery;
import net.rom.exoplanets.content.block.machine.BlockMetalFurnace;
import net.rom.exoplanets.content.block.ore.BlockOreMetal;
import net.rom.exoplanets.content.block.stairs.BlockConcrete1Stairs;
import net.rom.exoplanets.content.block.stairs.BlockConcreteStairs;
import net.rom.exoplanets.content.block.stairs.BlockRoofStairs;
import net.rom.exoplanets.content.block.stairs.BlockSpaceQuartzMetalFrame;
import net.rom.exoplanets.content.block.stairs.BlockSpaceQuartzStair;
import net.rom.exoplanets.content.block.terrain.ExoLog;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoBlocks {
	
	private static StellarRegistry reg;
	
	public static Block launch_facility;
	public static Block launch_facility_full;
	
	public static Block fake;

	// ELECTRONIC
	public static final Block raidcontroller = new BlockElectronic();
	public static final Block lower_raidcontroller = new BlockElectronic();
	public static final Block raidcluster = new BlockElectronic();
	public static final Block datamonitor = new BlockElectronic();
	public static final Block com_relay = new BlockElectronic();
	public static final Block control = new BlockElectronic();
	public static final Block satellite_antenna = new BlockSatelliteAntenna();
	public static final Block stand_console = new BlockStandConsole();
	public static final Block metal_diagonal = new BlockMetalDiagonal();
	public static final Block metal_slanted = new BlockMetalDiagonal();
	public static final Block hydraulic_top = new BlockCustomHydraulic();
	public static final Block hydraulic_bottom = new BlockCustomHydraulic();
	public static final Block hydraulic_middle = new BlockCustomHydraulic();
	public static final BlockMetalDecoration metaldecoration = new BlockMetalDecoration();
	
    public static final Block metalOre = new BlockOreMetal("metalore", true);
    public static final Block metalBlock = new BlockMetal();
    public static final Block alloyBlock = new BlockAlloy();

    public static final Block metalFurnace = new BlockMetalFurnace();
    public static final Block alloyRefinery = new BlockAlloyRefinery();
    
	// LEVERS
	public static final Block lever1 = new BlockCustomLever();
	public static final Block lever2 = new BlockCustomLever();
	public static final Block lever3 = new BlockCustomLever();
	
	// STAIRS
	public static final Block concrete_stairs = new BlockConcreteStairs();
	public static final Block concrete1_stairs = new BlockConcrete1Stairs();
	public static final Block space_quartz_stair = new BlockSpaceQuartzStair();
	public static final Block space_quartz_metalframe = new BlockSpaceQuartzMetalFrame();
	public static final Block roof_stairs = new BlockRoofStairs();

	// LIGHTS
	public static final Block alarm_light = new BlockAlarmLight(false);
	public static final Block alarm_light_lit = new BlockAlarmLight(true);
	public static final Block wall_lamp = new BlockWallLamp(false);
	public static final Block wall_lamp_lit = new BlockWallLamp(true);
	public static final Block metal_lamp = new BlockMetalLamp(false);
	public static final Block metal_lamp_lit = new BlockMetalLamp(true);
	public static final Block cellar_lamp = new BlockCellarLamp(false);
	public static final Block cellar_lamp_lit = new BlockCellarLamp(true);
	public static final Block inset_lamp = new BlockInsetLamp(false);
	public static final Block inset_lamp_lit = new BlockInsetLamp(true);
	
	// LOGS
	public static final Block log_a_dark1 = new ExoLog();
	public static final Block log_a = new ExoLog();
	public static final Block log_a_rough = new ExoLog();
	public static final Block log_a_dark2 = new ExoLog();
	public static final Block log_b = new ExoLog();
	public static final Block log_b_other = new ExoLog();
	public static final Block log_b_rough = new ExoLog();
	
    public static LinkedList<Block> blocksList = new LinkedList<>();
    

	public static void registerAll(StellarRegistry reg) {
		setReg(reg);
		
		YzCetiBlocks.registerAll(reg);
		TrappistBlocks.registerAll(reg);
		KeplerBlocks.registerAll(reg);
		
		register(metalOre, "metalore");		
        register(metalBlock, "metalblock");
        register(alloyBlock, "alloyblock");
        register(metalFurnace, "metalfurnace");
        register(alloyRefinery, "alloyrefinery");
        register(metaldecoration, "metaldecoration", new BlockMetalDecoration.ItemBlock(metaldecoration));
		register(com_relay, "com_relay");
		register(control, "control");
		register(raidcontroller, "raidcontroller");
		register(lower_raidcontroller, "lower_raidcontroller");
		register(raidcluster, "raidcluster");
		register(datamonitor, "datamonitor");
		register(satellite_antenna, "satellite_antenna");
		register(stand_console, "stand_console");
		register(metal_diagonal, "metal_diagonal");
		register(hydraulic_top, "hydraulic_top");
		register(hydraulic_bottom, "hydraulic_bottom");
		register(hydraulic_middle, "hydraulic_middle");
		register(lever1, "lever1");
		register(lever2, "lever2");
		register(lever3, "lever3");
		register(concrete_stairs, "concrete_stairs");
		register(concrete1_stairs, "concrete1_stairs");
		register(space_quartz_stair, "space_quartz_stair");
		register(space_quartz_metalframe, "space_quartz_metalframe");
		register(roof_stairs, "roof_stairs");
		register(alarm_light, "alarm_light");
		register(alarm_light_lit, "alarm_light_lit");
		register(wall_lamp, "wall_lamp");
		register(wall_lamp_lit, "wall_lamp_lit");
		register(metal_lamp, "metal_lamp");
		register(metal_lamp_lit, "metal_lamp_lit");
		register(cellar_lamp, "cellar_lamp");
		register(cellar_lamp_lit, "cellar_lamp_lit");
		register(inset_lamp, "inset_lamp");
		register(inset_lamp_lit, "inset_lamp_lit");
		register(log_a_dark1, "log_a_dark1");
		register(log_a, "log_a");
		register(log_a_rough, "log_a_rough");
		register(log_a_dark2, "log_a_dark2");
		register(log_b, "log_b");
		register(log_b_other, "log_b_other");
		register(log_b_rough, "log_b_rough");

	}

	public static void register(Block block, String blockName) {
		blocksList.add(block);
		reg.registerBlock(block, blockName);
	}
	
	public static void register(Block block, String blockName, String path) {
		blocksList.add(block);
		reg.registerBlock(block, blockName, path);
	}
	
	public static void register(Block block, String blockName, ItemBlock itemBlock) {
		blocksList.add(block);
		reg.registerBlock(block, blockName, new ItemBlock(block));
	}
	
	public static void setReg(StellarRegistry reg) {
		ExoBlocks.reg = reg;
	}
}
