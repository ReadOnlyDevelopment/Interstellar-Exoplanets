/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.util;

import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;
import net.rom.exoplanets.internal.world.star.ExoStar;

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
