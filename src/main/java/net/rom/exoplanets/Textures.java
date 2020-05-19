package net.rom.exoplanets;

import net.minecraft.util.ResourceLocation;
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.api.stellar.impl.star.ExoStar;

public class Textures {
	public static final ResourceLocation cetiB = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_b.png");
	public static final ResourceLocation cetiC = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_c.png");
	public static final ResourceLocation cetiD = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_d.png");
	
	public static final ResourceLocation trappist1b = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_b.png");
	public static final ResourceLocation trappist1c = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_c.png");
	public static final ResourceLocation trappist1d = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_d.png");
	public static final ResourceLocation trappist1e = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_e.png");
	public static final ResourceLocation trappist1f = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_f.png");
	public static final ResourceLocation trappist1g = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_g.png");
	public static final ResourceLocation trappist1h = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/trappist_1/trappist_h.png");
		
	public static final ResourceLocation redDwarfReal = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/realism/yz_ceti_star.png");
	public static final ResourceLocation redDwarf = new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_star.png");

	public static void blockIcon(ExoStar star, String name) {
		star.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.replace("_star", "") + "/" + name + ".png"));
	}

	public static void realIcon(ExoStar star, String name) {
		star.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.replace("_star", "") + "/realism/" + name + ".png"));
	}

	public static void blockIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/" + name + ".png"));
	}

	public static void realIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/realism/" + name + ".png"));
	}

}
