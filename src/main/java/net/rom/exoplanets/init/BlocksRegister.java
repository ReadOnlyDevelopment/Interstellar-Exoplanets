package net.rom.exoplanets.init;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.block.BasicBlock;
import net.rom.exoplanets.block.decoration.BlockDeco;
import net.rom.exoplanets.block.decoration.BlockElectronic;
import net.rom.exoplanets.block.decoration.BlockSatelliteAntenna;
import net.rom.exoplanets.block.decoration.BlockStandConsole;
import net.rom.exoplanets.block.ore.BlockOverworldOre;
import net.rom.exoplanets.block.terrain.BlockYzCetiB;
import net.rom.exoplanets.block.terrain.BlockYzCetiC;
import net.rom.exoplanets.internal.StellarRegistry;

public class BlocksRegister {

	// TERRAIN BLOCKS
	public static final BlockYzCetiB YZB_SEDIMENTARY = new BlockYzCetiB();
	public static final BlockYzCetiB YZB_INGNEOUS = new BlockYzCetiB();
	public static final BlockYzCetiB YZB_METAMORPHIC = new BlockYzCetiB();
	public static final BlockYzCetiB YZB_SMOOTHSEDIMENTARY = new BlockYzCetiB();
	public static final BlockYzCetiC YZC_SEDIMENTARY = new BlockYzCetiC();
	public static final BlockYzCetiC YZC_INGNEOUS = new BlockYzCetiC();
	public static final BlockYzCetiC YZC_METAMORPHIC = new BlockYzCetiC();
	public static final BlockYzCetiC YZC_SMOOTHSEDIMENTARY = new BlockYzCetiC();
	
	// ORES
	public static final BlockOverworldOre OVERWORLD_ORE = new BlockOverworldOre();

	// DECORATION BLOCKS
	public static final BlockElectronic RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic LOWER_RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic RAIDCLUSTER = new BlockElectronic();
	public static final BlockElectronic DATAMONITOR = new BlockElectronic();
	public static final BlockElectronic COM = new BlockElectronic();
	public static final BlockElectronic CONTROL = new BlockElectronic();
	public static final BlockDeco DECO = new BlockDeco();
	public static final BlockSatelliteAntenna SATELLITE_ANTENNA = new BlockSatelliteAntenna();
	public static final BlockStandConsole STAND_CONSOLE = new BlockStandConsole();

	public static void registerAll(StellarRegistry registry) {

		// TERRAIN
		registry.registerBlock(YZB_SEDIMENTARY, "yzb_sedimentary", new ItemBlock(YZB_SEDIMENTARY));
		registry.registerBlock(YZB_INGNEOUS, "yzb_ingneous", new ItemBlock(YZB_INGNEOUS));
		registry.registerBlock(YZB_METAMORPHIC, "yzb_metamorphic", new ItemBlock(YZB_METAMORPHIC));
		registry.registerBlock(YZB_SMOOTHSEDIMENTARY, "yzb_smoothsedimentary", new ItemBlock(YZB_SMOOTHSEDIMENTARY));
		registry.registerBlock(YZC_SEDIMENTARY, "yzc_sedimentary", new ItemBlock(YZC_SEDIMENTARY));
		registry.registerBlock(YZC_INGNEOUS, "yzc_ingneous", new ItemBlock(YZC_INGNEOUS));
		registry.registerBlock(YZC_METAMORPHIC, "yzc_metamorphic", new ItemBlock(YZC_METAMORPHIC));
		registry.registerBlock(YZC_SMOOTHSEDIMENTARY, "yzc_smoothsedimentary", new ItemBlock(YZC_SMOOTHSEDIMENTARY));
		
		// ORE
		registry.registerBlock(OVERWORLD_ORE, "overworldore", new BlockOverworldOre.ItemBlock(OVERWORLD_ORE));

		// DECORATION
		registry.registerBlock(COM, "com_relay", new ItemBlock(COM));
		registry.registerBlock(CONTROL, "control", new ItemBlock(CONTROL));
		registry.registerBlock(RAIDCONTROLLER, "raidcontroller", new ItemBlock(RAIDCONTROLLER));
		registry.registerBlock(LOWER_RAIDCONTROLLER, "lower_raidcontroller", new ItemBlock(LOWER_RAIDCONTROLLER));
		registry.registerBlock(RAIDCLUSTER, "raidcluster", new ItemBlock(RAIDCLUSTER));
		registry.registerBlock(DATAMONITOR, "datamonitor", new ItemBlock(DATAMONITOR));
		registry.registerBlock(DECO, "deco", new BlockDeco.ItemBlock(DECO));
		registry.registerBlock(SATELLITE_ANTENNA, "satellite_antenna", new ItemBlock(SATELLITE_ANTENNA));
		registry.registerBlock(STAND_CONSOLE, "stand_console", new ItemBlock(STAND_CONSOLE));

	}
	
	

}
