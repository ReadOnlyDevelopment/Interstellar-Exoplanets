package net.rom.exoplanets.init;

import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.block.decoration.BlockAlarmLight;
import net.rom.exoplanets.block.decoration.BlockCellarLamp;
import net.rom.exoplanets.block.decoration.BlockElectronic;
import net.rom.exoplanets.block.decoration.BlockInsetLamp;
import net.rom.exoplanets.block.decoration.BlockMetalLamp;
import net.rom.exoplanets.block.decoration.BlockSatelliteAntenna;
import net.rom.exoplanets.block.decoration.BlockStandConsole;
import net.rom.exoplanets.block.decoration.BlockWallLamp;
import net.rom.exoplanets.block.ore.BlockOverworldOre;
import net.rom.exoplanets.internal.StellarRegistry;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class ExoBlocks {

    //ELECTRONIC
	public static final BlockOverworldOre OVERWORLD_ORE = new BlockOverworldOre();
	public static final BlockElectronic RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic LOWER_RAIDCONTROLLER = new BlockElectronic();
	public static final BlockElectronic RAIDCLUSTER = new BlockElectronic();
	public static final BlockElectronic DATAMONITOR = new BlockElectronic();
	public static final BlockElectronic COM = new BlockElectronic();
	public static final BlockElectronic CONTROL = new BlockElectronic();
	public static final BlockSatelliteAntenna SATELLITE_ANTENNA = new BlockSatelliteAntenna();
	public static final BlockStandConsole STAND_CONSOLE = new BlockStandConsole();

	//LIGHTS
    public static final BlockAlarmLight ALARM_LIGHT = new BlockAlarmLight(false);
    public static final BlockAlarmLight ALARM_LIGHT_LIT = new BlockAlarmLight(true);
    public static final BlockWallLamp WALL_LAMP = new BlockWallLamp(false);
    public static final BlockWallLamp WALL_LAMP_LIT = new BlockWallLamp(true);
    public static final BlockMetalLamp METAL_LAMP = new BlockMetalLamp(false);
    public static final BlockMetalLamp METAL_LAMP_LIT = new BlockMetalLamp(true);
    public static final BlockCellarLamp CELLAR_LAMP = new BlockCellarLamp(false);
    public static final BlockCellarLamp CELLAR_LAMP_LIT = new BlockCellarLamp(true);
    public static final BlockInsetLamp INSET_LAMP = new BlockInsetLamp(false);
    public static final BlockInsetLamp INSET_LAMP_LIT = new BlockInsetLamp(true);


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
		registry.registerBlock(ALARM_LIGHT, "alarm_light", new ItemBlock(ALARM_LIGHT)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);;
		registry.registerBlock(ALARM_LIGHT_LIT, "alarm_light_lit", new ItemBlock(ALARM_LIGHT_LIT));
	    registry.registerBlock(WALL_LAMP, "wall_lamp", new ItemBlock(ALARM_LIGHT)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);;
	    registry.registerBlock(WALL_LAMP_LIT, "wall_lamp_lit", new ItemBlock(ALARM_LIGHT_LIT));
	    registry.registerBlock(METAL_LAMP, "metal_lamp", new ItemBlock(METAL_LAMP)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);;
	    registry.registerBlock(METAL_LAMP_LIT, "metal_lamp_lit", new ItemBlock(METAL_LAMP_LIT));
	    registry.registerBlock(CELLAR_LAMP, "cellar_lamp", new ItemBlock(CELLAR_LAMP)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);;
	    registry.registerBlock(CELLAR_LAMP_LIT, "cellar_lamp_lit", new ItemBlock(CELLAR_LAMP_LIT));
        registry.registerBlock(INSET_LAMP, "inset_lamp", new ItemBlock(INSET_LAMP)).setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);;
        registry.registerBlock(INSET_LAMP_LIT, "inset_lamp_lit", new ItemBlock(INSET_LAMP_LIT));

	}
}
