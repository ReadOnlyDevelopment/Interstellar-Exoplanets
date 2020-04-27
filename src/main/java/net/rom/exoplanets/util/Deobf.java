package net.rom.exoplanets.util;

import net.minecraft.launchwrapper.Launch;

public class Deobf {

	private static boolean deobfuscated;

	static {
		try {
			deobfuscated = Launch.classLoader.getClassBytes("net.minecraft.world.World") != null;
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	public static boolean isDeobfuscated() {
		return deobfuscated;
	}

}
