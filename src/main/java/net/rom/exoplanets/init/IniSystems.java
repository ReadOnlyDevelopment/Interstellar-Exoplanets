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

package net.rom.exoplanets.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.astronomy.BodiesHelper.Galaxies;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.ResourceLocation;
import net.rom.api.stellar.AstroBuilder;
import net.rom.api.stellar.impl.star.ExoStar;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigSystems;

public class IniSystems {

	public static ExoStar yzCetiStar;
	public static ExoStar wolf1061Star;
	public static ExoStar hd219134Star;
	public static ExoStar trappist1Star;
	public static ExoStar kepler1649star;

	public static SolarSystem yzCeti;
	public static SolarSystem wolf1061;
	public static SolarSystem hd219134;
	public static SolarSystem trappist1;
	public static SolarSystem kepler1649;
	
	//public static Galaxies TESTGALAXY;
	
	static AstroBuilder b = new AstroBuilder(ExoInfo.MODID);

	public static void init() {
		registerExoStars();
		registerSolarSystems();
		initializeSolarSystems();
	}

	private static void registerExoStars() {
		yzCetiStar = b.buildExoStar("yz_ceti_star", 3058, 0.130D, 0.168D);
		wolf1061Star = b.buildExoStar("wolf_1061_star", 3342, 0.294D, 0.307D);
		hd219134Star = b.buildExoStar("hd_219134_star", 4699, 0.81D, 0.778D);
		trappist1Star = b.buildExoStar("trappist_star", 2511, 0.089D, 0.121D);
		kepler1649star = b.buildExoStar("kepler1649_star", 2150, 0.02D, 0.230D);
	}

	private static void registerSolarSystems() {
		//TESTGALAXY = BodiesHelper.registerGalaxy("wormhole", new ResourceLocation(ExoInfo.MODID, "textures/gui/galaxy/wormhole.png"));
		
		yzCeti = b.buildSolarSystem("yz_ceti", "milky_way", yzPos(), yzCetiStar);
		BodiesData data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
		BodiesHelper.registerBodyData(yzCeti.getMainStar(), data);

		wolf1061 = b.buildSolarSystem("wolf_1061", "milky_way", wolfPos(), wolf1061Star);
		data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
		BodiesHelper.registerBodyData(wolf1061.getMainStar(), data);

		hd219134 = b.buildSolarSystem("hd_219134", "milky_way", hdPos(), hd219134Star);
		data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.YELLOW);
		BodiesHelper.registerBodyData(hd219134.getMainStar(), data);

		trappist1 = b.buildSolarSystem("trappist_1", "milky_way", trapPos(), trappist1Star);
		data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
		BodiesHelper.registerBodyData(trappist1.getMainStar(), data);

		kepler1649 = b.buildSolarSystem("kepler1649", "milky_way", k1649Pos(), kepler1649star);
		data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
		BodiesHelper.registerBodyData(kepler1649.getMainStar(), data);
	}

	private static void initializeSolarSystems() {
		b.registerSolarSystem(yzCeti);
		b.registerSolarSystem(wolf1061);
		b.registerSolarSystem(hd219134);
		b.registerSolarSystem(trappist1);
		b.registerSolarSystem(kepler1649);

	}
	
	/*
	 * Coords
	 *
	 * ZOLERN GALAXY 
	 * (Psios) 
	 * "X", -2.5 
	 * "Y", 1.2 
	 * (Praedyth) 
	 * "X", -1.2 
	 * "Y", 1.4
	 * (Sol-2) unused
	 * "X", 1.2 
	 * "Y", -1.2 
	 * (Pantheon) unused
	 * "X", 1.2 
	 * "Y", -1.2 
	 * (Olympus) unused
	 * "X", 1.2
	 * "Y", -1.2 
	 * (Asgard) unused
	 * "X", 1.2 
	 * "Y", -1.2 
	 * (Vega) unused
	 * "X", 1.2 
	 * "Y", -1.2 
	 * (Nova) unused
	 * "X", 1.2 
	 * "Y", -1.2
	 * 
	 * EXTRA PLANETS
	 * (kepler22) 
	 * "X", 0.8
	 * "Y", -0.6
	 * (kepler47) 
	 * "X", -0.40
	 * "Y", -0.8
	 * (kepler62) 
	 * "X", -0.65 
	 * "Y", 0.9
	 * (kepler69) 
	 * "X", -0.90
	 * "Y", 0.0
	 * 
	 * MORE PLANETS
	 * (LAZENDUS) 
	 * "X", 0.75
	 * "Y", 1.25
	 */

	public static Vector3 yzPos() {
		return new Vector3(SConfigSystems.yzceti_x, SConfigSystems.yzceti_y, 0.0F);
	}

	public static Vector3 wolfPos() {
		return new Vector3(SConfigSystems.wolf_x, SConfigSystems.wolf_y, 0.0F);
	}

	public static Vector3 hdPos() {
		return new Vector3(SConfigSystems.hd_x, SConfigSystems.hd_y, 0.0F);
	}

	public static Vector3 trapPos() {
		return new Vector3(SConfigSystems.trap_x, SConfigSystems.trap_y, 0.0F);
	}

	public static Vector3 k1649Pos() {
		return new Vector3(SConfigSystems.k1649_x, SConfigSystems.k1649_y, 0.0F);
	}

}