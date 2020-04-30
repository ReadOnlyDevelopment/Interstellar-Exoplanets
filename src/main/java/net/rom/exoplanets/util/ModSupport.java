package net.rom.exoplanets.util;

import net.minecraftforge.fml.common.Loader;

public class ModSupport {

	public static boolean asmodeusLoaded() {
		return Loader.isModLoaded("asmodeuscore");


	}

}
