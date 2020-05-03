package net.rom.exoplanets.init;

import micdoodle8.mods.galacticraft.planets.asteroids.world.gen.SpecialAsteroidBlock;
import micdoodle8.mods.galacticraft.planets.asteroids.world.gen.SpecialAsteroidBlockHandler;
import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.world.WorldProvider;
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
	public static final BlockMetalDiagonal METAL_DIAGONAL = new BlockMetalDiagonal();
	public static final BlockCustomHydraulic HYDRAULIC_TOP = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic HYDRAULIC_BOTTOM = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic HYDRAULIC_MIDDLE = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic HYDRAULIC_BOTTOM_H = new BlockCustomHydraulic();
	public static final BlockCustomHydraulic HYDRAULIC_MIDDLE_H = new BlockCustomHydraulic();

	
	//LEVERS
	public static final BlockCustomLever LEVER1 = new BlockCustomLever();
	public static final BlockCustomLever LEVER2 = new BlockCustomLever();
	public static final BlockCustomLever LEVER3 = new BlockCustomLever();

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


	public static void registerAll(StellarRegistry reg) {
		setReg(reg);
		YzCetiBlocks.registerAll(reg);
        
		// ORES
		reg.registerBlock(OVERWORLD_ORE, "overworldore", new BlockOverworldOre.ItemBlock(OVERWORLD_ORE));
		
		//DECORATIONS
		register(COM, "com_relay");
		register(CONTROL, "control");
		register(RAIDCONTROLLER, "raidcontroller");
		register(LOWER_RAIDCONTROLLER, "lower_raidcontroller");
		register(RAIDCLUSTER, "raidcluster");
		register(DATAMONITOR, "datamonitor");
		register(SATELLITE_ANTENNA, "satellite_antenna");
		register(STAND_CONSOLE, "stand_console");
		register(METAL_DIAGONAL, "metal_diagonal");
		register(HYDRAULIC_TOP, "hydraulic_top");
		register(HYDRAULIC_BOTTOM, "hydraulic_bottom");
		register(HYDRAULIC_MIDDLE, "hydraulic_middle");
		register(HYDRAULIC_BOTTOM_H, "hydraulic_bottom_horizontal");
		register(HYDRAULIC_MIDDLE_H, "hydraulic_middle_horizontal");
		
		//LEVERS
		register(LEVER1, "lever1");
		register(LEVER2, "lever2");
		register(LEVER3, "lever3");
		
		// LAMPS / LIGHTS
		registerWithDecoTab(ALARM_LIGHT, "alarm_light");
		register(ALARM_LIGHT_LIT, "alarm_light_lit");
		registerWithDecoTab(WALL_LAMP, "wall_lamp");
	    register(WALL_LAMP_LIT, "wall_lamp_lit");
	    registerWithDecoTab(METAL_LAMP, "metal_lamp");
	    register(METAL_LAMP_LIT, "metal_lamp_lit");
	    registerWithDecoTab(CELLAR_LAMP, "cellar_lamp");
	    register(CELLAR_LAMP_LIT, "cellar_lamp_lit");
	    registerWithDecoTab(INSET_LAMP, "inset_lamp");
        register(INSET_LAMP_LIT, "inset_lamp_lit");
        
	}
	
	public static void registerAstroidCores() {
		SpecialAsteroidBlockHandler newHandler = new SpecialAsteroidBlockHandler();
		SpecialAsteroidBlock block = new SpecialAsteroidBlock(OVERWORLD_ORE, (byte) 1, 32, 3);
		
		newHandler.addBlock(block);
		
		
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
