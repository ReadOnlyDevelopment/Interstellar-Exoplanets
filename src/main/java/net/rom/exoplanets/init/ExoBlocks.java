package net.rom.exoplanets.init;

import static net.rom.exoplanets.ExoInfo.blocks;

import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.block.decoration.BlockElectronic;
import net.rom.exoplanets.block.decoration.BlockSatelliteAntenna;
import net.rom.exoplanets.block.decoration.BlockStandConsole;
import net.rom.exoplanets.block.ore.BlockOverworldOre;
import net.rom.exoplanets.internal.StellarRegistry;

public class ExoBlocks {

	public static final BlockOverworldOre OVERWORLD_ORE = new BlockOverworldOre();
	public static final BlockElectronic RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic LOWER_RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic RAIDCLUSTER = new BlockElectronic();
	public static final BlockElectronic DATAMONITOR = new BlockElectronic();
	public static final BlockElectronic COM = new BlockElectronic();
	public static final BlockElectronic CONTROL = new BlockElectronic();
	public static final BlockSatelliteAntenna SATELLITE_ANTENNA = new BlockSatelliteAntenna();
	public static final BlockStandConsole STAND_CONSOLE = new BlockStandConsole();

	public static void registerAll(StellarRegistry registry) {
		YzCetiBlocks.registerAll(registry);

		registry.registerBlock(OVERWORLD_ORE, "overworldore", new BlockOverworldOre.ItemBlock(OVERWORLD_ORE));
		registry.registerBlock(COM, "com_relay", new ItemBlock(COM));
		registry.registerBlock(CONTROL, "control", new ItemBlock(CONTROL));
		registry.registerBlock(RAIDCONTROLLER, "raidcontroller", new ItemBlock(RAIDCONTROLLER));
		registry.registerBlock(LOWER_RAIDCONTROLLER, "lower_raidcontroller", new ItemBlock(LOWER_RAIDCONTROLLER));
		registry.registerBlock(RAIDCLUSTER, "raidcluster", new ItemBlock(RAIDCLUSTER));
		registry.registerBlock(DATAMONITOR, "datamonitor", new ItemBlock(DATAMONITOR));
		registry.registerBlock(SATELLITE_ANTENNA, "satellite_antenna", new ItemBlock(SATELLITE_ANTENNA));
		registry.registerBlock(STAND_CONSOLE, "stand_console", new ItemBlock(STAND_CONSOLE));

	}
	
	public void add() {
		blocks.add(OVERWORLD_ORE);
		blocks.add(COM);
		blocks.add(CONTROL);
		blocks.add(RAIDCONTROLLER);
		blocks.add(LOWER_RAIDCONTROLLER);
		blocks.add(RAIDCLUSTER);
		blocks.add(DATAMONITOR);
		blocks.add(SATELLITE_ANTENNA);
		blocks.add(STAND_CONSOLE);
	}
}
