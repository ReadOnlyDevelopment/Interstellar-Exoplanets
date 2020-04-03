package net.rom.stellar.util;

import net.minecraftforge.fml.common.Loader;

public class ModCheckUtil {

	public static boolean asmodeusCoreLoaded() {
		if (Loader.isModLoaded("asmodeuscore")) {
			return true;
		} else {
			return false;
		}
	}
}
