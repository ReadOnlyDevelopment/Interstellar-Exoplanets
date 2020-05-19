/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.init;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
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
import net.rom.exoplanets.content.block.machine.BlockAlloyRefinery;
import net.rom.exoplanets.content.block.machine.BlockMetalFurnace;
import net.rom.exoplanets.content.block.ore.BlockAlloy;
import net.rom.exoplanets.content.block.ore.BlockMetal;
import net.rom.exoplanets.content.block.ore.BlockOreMetal;
import net.rom.exoplanets.content.block.ore.BlockOverworldOre;
import net.rom.exoplanets.content.item.ItemBlockOre;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.util.CreativeExoTabs;

public class ExoBlocks {
	
	private static StellarRegistry reg;

	// ELECTRONIC
	public static final BlockOverworldOre overworldore = new BlockOverworldOre();
	public static final BlockElectronic raidcontroller = new BlockElectronic();
	public static final BlockElectronic lower_raidcontroller = new BlockElectronic();
	public static final BlockElectronic raidcluster = new BlockElectronic();
	public static final BlockElectronic datamonitor = new BlockElectronic();
	public static final BlockElectronic com_relay = new BlockElectronic();
	public static final BlockElectronic control = new BlockElectronic();
	public static final BlockSatelliteAntenna satellite_antenna = new BlockSatelliteAntenna();
	public static final BlockStandConsole stand_console = new BlockStandConsole();
	public static final BlockMetalDiagonal metal_diagonal = new BlockMetalDiagonal();
	public static final BlockMetalDiagonal metal_slanted = new BlockMetalDiagonal();
	public static final BlockCustomHydraulic hydraulic_top = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic hydraulic_bottom = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic hydraulic_middle = new BlockCustomHydraulic();
	public static final BlockMetalDecoration metaldecoration = new BlockMetalDecoration();
	
    public static final BlockOreMetal metalOre = new BlockOreMetal();
    public static final BlockMetal metalBlock = new BlockMetal();
    public static final BlockAlloy alloyBlock = new BlockAlloy();

    public static final BlockMetalFurnace metalFurnace = new BlockMetalFurnace();
    public static final BlockAlloyRefinery alloyRefinery = new BlockAlloyRefinery();
    
	// LEVERS
	public static final BlockCustomLever lever1 = new BlockCustomLever();
	public static final BlockCustomLever lever2 = new BlockCustomLever();
	public static final BlockCustomLever lever3 = new BlockCustomLever();

	// LIGHTS
	public static final BlockAlarmLight alarm_light = new BlockAlarmLight(false);
	public static final BlockAlarmLight alarm_light_lit = new BlockAlarmLight(true);
	public static final BlockWallLamp wall_lamp = new BlockWallLamp(false);
	public static final BlockWallLamp wall_lamp_lit = new BlockWallLamp(true);
	public static final BlockMetalLamp metal_lamp = new BlockMetalLamp(false);
	public static final BlockMetalLamp metal_lamp_lit = new BlockMetalLamp(true);
	public static final BlockCellarLamp cellar_lamp = new BlockCellarLamp(false);
	public static final BlockCellarLamp cellar_lamp_lit = new BlockCellarLamp(true);
	public static final BlockInsetLamp inset_lamp = new BlockInsetLamp(false);
	public static final BlockInsetLamp inset_lamp_lit = new BlockInsetLamp(true);

	public static void registerAll(StellarRegistry reg) {
		setReg(reg);
		YzCetiBlocks.registerAll(reg);
		TrappistBlocks.registerAll(reg);

		// ORES
		reg.registerBlock(overworldore, "overworldore", new BlockOverworldOre.ItemBlock(overworldore));
        reg.registerBlock(metalOre, "metalore", new ItemBlockOre(metalOre));
        reg.registerBlock(metalBlock, "metalblock");
        reg.registerBlock(alloyBlock, "alloyblock");
        
        reg.registerBlock(metalFurnace, "metalfurnace");
        reg.registerBlock(alloyRefinery, "alloyrefinery");
        
        reg.registerBlock(metaldecoration, "metaldecoration", new BlockMetalDecoration.ItemBlock(metaldecoration));
		
		// DECORATIONS
		register(com_relay, "com_relay");
		register(control, "control");
		register(raidcontroller, "raidcontroller");
		register(lower_raidcontroller, "lower_raidcontroller");
		register(raidcluster, "raidcluster");
		register(datamonitor, "datamonitor");
		register(satellite_antenna, "satellite_antenna");
		register(stand_console, "stand_console");
		register(metal_diagonal, "metal_diagonal");
		register(metal_slanted, "metal_slanted");
		register(hydraulic_top, "hydraulic_top");
		register(hydraulic_bottom, "hydraulic_bottom");
		register(hydraulic_middle, "hydraulic_middle");

		// LEVERS
		register(lever1, "lever1");
		register(lever2, "lever2");
		register(lever3, "lever3");

		// LAMPS / LIGHTS
		registerWithDecoTab(alarm_light, "alarm_light");
		register(alarm_light_lit, "alarm_light_lit");
		registerWithDecoTab(wall_lamp, "wall_lamp");
		register(wall_lamp_lit, "wall_lamp_lit");
		registerWithDecoTab(metal_lamp, "metal_lamp");
		register(metal_lamp_lit, "metal_lamp_lit");
		registerWithDecoTab(cellar_lamp, "cellar_lamp");
		register(cellar_lamp_lit, "cellar_lamp_lit");
		registerWithDecoTab(inset_lamp, "inset_lamp");
		register(inset_lamp_lit, "inset_lamp_lit");

	}

	private static void register(Block block, String blockName) {
		reg.registerBlock(block, blockName, new ItemBlock(block));
	}

	private static void registerWithDecoTab(Block block, String blockName) {
		reg.registerBlock(block, blockName, new ItemBlock(block)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}
	
	public static void setReg(StellarRegistry reg) {
		ExoBlocks.reg = reg;
	}
}
