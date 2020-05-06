package net.rom.exoplanets;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.common.ForgeVersion;

public class ExoInfo {

	public static final List<Block> blocks = new ArrayList<>();
	public static final List<Item> items = new ArrayList<>();


	public static final String MODID = "exoplanets";
	public static final String NAME = "Interstellar: Exoplanets";
	public static final String VERSION = "0.0.8";
	public static final int BUILD = 0;
	public static final String ACCEPTED_MC_VERSIONS = "[1.12.2]";
	public static final String ACCEPTED_MC_VERSION = ForgeVersion.mcVersion;
	public static final String DEPENDENCIES_MODS = "required-after:galacticraftcore@[4.0.2.261,]; required-after:galacticraftplanets; required-after:asmodeuscore@[0.0.14,)";
	public static final String RESOURCE_PREFIX = MODID + ":";

}
