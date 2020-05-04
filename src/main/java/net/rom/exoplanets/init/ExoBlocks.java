package net.rom.exoplanets.init;

import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Arrays;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.block.decoration.BlockAlarmLight;
import net.rom.exoplanets.block.decoration.BlockCellarLamp;
import net.rom.exoplanets.block.decoration.BlockCustomHydraulic;
import net.rom.exoplanets.block.decoration.BlockCustomLever;
import net.rom.exoplanets.block.decoration.BlockElectronic;
import net.rom.exoplanets.block.decoration.BlockInsetLamp;
import net.rom.exoplanets.block.decoration.BlockMetalDiagonal;
import net.rom.exoplanets.block.decoration.BlockMetalLamp;
import net.rom.exoplanets.block.decoration.BlockSatelliteAntenna;
import net.rom.exoplanets.block.decoration.BlockStandConsole;
import net.rom.exoplanets.block.decoration.BlockWallLamp;
import net.rom.exoplanets.block.ore.BlockOverworldOre;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.tabs.CreativeExoTabs;

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

		// ORES
		reg.registerBlock(overworldore, "overworldore", new BlockOverworldOre.ItemBlock(overworldore));
		
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
