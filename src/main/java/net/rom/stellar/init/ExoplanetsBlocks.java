package net.rom.stellar.init;

import net.minecraft.block.material.Material;
import net.minecraft.item.ItemBlock;
import net.rom.core.autoreg.StellarRegistry;
import net.rom.stellar.block.BasicBlock;
import net.rom.stellar.block.BlockYzCetiB;
import net.rom.stellar.block.BlockYzCetiC;

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
	public static final BasicBlock basic3 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic4 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic5 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic6 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic7 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic8 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic9 = new BasicBlock(Material.GROUND);
	public static final BasicBlock basic10 = new BasicBlock(Material.GROUND);
	
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
	}

}
