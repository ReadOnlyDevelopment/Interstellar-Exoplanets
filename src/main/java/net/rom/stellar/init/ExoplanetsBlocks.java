package net.rom.stellar.init;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.rom.core.autoreg.StellarRegistry;
import net.rom.stellar.block.BasicBlock;
import net.rom.stellar.block.BlockYzCetiB;
import net.rom.stellar.block.BlockYzCetiC;
import net.rom.stellar.block.decoration.BlockElectronic;
import net.rom.stellar.block.doors.SpaceDoor;

public class ExoplanetsBlocks {
	
	public static final BlockYzCetiB yzb_sedimentary = new BlockYzCetiB();
	public static final BlockYzCetiB yzb_ingneous = new BlockYzCetiB();
	public static final BlockYzCetiB yzb_metamorphic = new BlockYzCetiB();
	public static final BlockYzCetiB yzb_smoothsedimentary = new BlockYzCetiB();
	public static final BlockYzCetiC yzc_sedimentary = new BlockYzCetiC();
	public static final BlockYzCetiC yzc_ingneous = new BlockYzCetiC();
	public static final BlockYzCetiC yzc_metamorphic = new BlockYzCetiC();
	public static final BlockYzCetiC yzc_smoothsedimentary = new BlockYzCetiC();
	public static final BasicBlock dirt = new BasicBlock(Material.GROUND);
	public static final BasicBlock tiles = new BasicBlock(Material.ROCK);
	
	public static final SpaceDoor spaceDoor = new SpaceDoor();

	public static final BlockElectronic raid0 = new BlockElectronic();
	public static final BlockElectronic raid1 = new BlockElectronic();
	public static final BlockElectronic raidStack = new BlockElectronic();
	public static final BlockElectronic screen = new BlockElectronic();
	public static final BlockElectronic com = new BlockElectronic();
	public static final BlockElectronic control = new BlockElectronic();
	
	public static void registerAll(StellarRegistry registry) {
		
		
		registry.registerBlock(dirt, "basic1", new ItemBlock(dirt));
		registry.registerBlock(tiles, "basic2", new ItemBlock(tiles));
		
		registry.registerBlock(yzb_sedimentary, "yzb_sedimentary", new ItemBlock(yzb_sedimentary));
		registry.registerBlock(yzb_ingneous, "yzb_ingneous", new ItemBlock(yzb_ingneous));
		registry.registerBlock(yzb_metamorphic, "yzb_metamorphic", new ItemBlock(yzb_metamorphic));
		registry.registerBlock(yzb_smoothsedimentary, "yzb_smoothsedimentary", new ItemBlock(yzb_smoothsedimentary));
		
		registry.registerBlock(yzc_sedimentary, "yzc_sedimentary", new ItemBlock(yzc_sedimentary));
		registry.registerBlock(yzc_ingneous, "yzc_ingneous", new ItemBlock(yzc_ingneous));
		registry.registerBlock(yzc_metamorphic, "yzc_metamorphic", new ItemBlock(yzc_metamorphic));
		registry.registerBlock(yzc_smoothsedimentary, "yzc_smoothsedimentary", new ItemBlock(yzc_smoothsedimentary));
		
		registry.registerBlock(com, "com_relay", new ItemBlock(com));
		registry.registerBlock(control, "control", new ItemBlock(control));
		registry.registerBlock(raid0, "raid_0", new ItemBlock(raid0));
		registry.registerBlock(raid1, "raid_1", new ItemBlock(raid1));
		registry.registerBlock(raidStack, "raid_stack", new ItemBlock(raidStack));
		registry.registerBlock(screen, "screen", new ItemBlock(screen));
		
		registry.registerBlock(spaceDoor, "spacedoor", new ItemBlock(spaceDoor));
		
	}

}
