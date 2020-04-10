package net.rom.exoplanets.util;

import net.minecraft.client.Minecraft;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.server.MinecraftServer;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;

public class CoreUtil {

	public static int nextID = 0;
	private static boolean deobfuscated;
	public static boolean langDisable;
	private static MinecraftServer serverCached;

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

	public static Side getEffectiveSide() {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.SERVER
				|| Thread.currentThread().getName().startsWith("Netty Epoll Server IO")) {
			return Side.SERVER;
		}

		return Side.CLIENT;
	}

	public static MinecraftServer getServer() {
		MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();
		if (server == null) {
			return serverCached;
		}
		return server;
	}
	
	public static Minecraft getClient() {
		return FMLClientHandler.instance().getClient();
	}

}
