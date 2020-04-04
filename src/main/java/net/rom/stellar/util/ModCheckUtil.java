package net.rom.stellar.util;

import net.minecraftforge.fml.common.Loader;

public class ModCheckUtil {

	public static boolean asmodeusCoreLoaded() {
		return Loader.isModLoaded("asmodeuscore");
	}
}
