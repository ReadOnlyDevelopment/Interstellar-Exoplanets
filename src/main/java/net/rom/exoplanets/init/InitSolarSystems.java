package net.rom.exoplanets.init;

import java.util.ArrayList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.rom.api.stellar.AstroBuilder;
import net.rom.api.stellar.impl.star.ExoStar;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigSystems;
import net.rom.exoplanets.util.LogHelper;
import net.rom.exoplanets.util.ModSupport;

public class InitSolarSystems {

	public static ExoStar yzCetiStar;
	public static ExoStar wolf1061Star;
	public static ExoStar hd219134Star;
	public static ExoStar trappist1Star;

	public static SolarSystem yzCeti;
	public static SolarSystem wolf1061;
	public static SolarSystem hd219134;
	public static SolarSystem trappist1;

	public static List<SolarSystem> systems = new ArrayList<>();

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
	}

	private static void registerSolarSystems() {
		yzCeti = b.buildSolarSystem("yz_ceti", "milky_way", yzPos(), yzCetiStar);
		wolf1061 = b.buildSolarSystem("wolf_1061", "milky_way", wolfPos(), wolf1061Star);
		hd219134 = b.buildSolarSystem("hd_219134", "milky_way", hdPos(), hd219134Star);
		trappist1 = b.buildSolarSystem("trappist_1", "milky_way", trapPos(), trappist1Star);

		LogHelper.info(yzCeti.getMainStar().getName());




		if (ModSupport.asmodeusLoaded()) {
			BodiesData data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBody(yzCeti.getMainStar(), data, false);

			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBody(wolf1061.getMainStar(), data, false);

			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.YELLOW);
			BodiesHelper.registerBody(hd219134.getMainStar(), data, false);

			data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
			BodiesHelper.registerBody(trappist1.getMainStar(), data, false);
		}

	}

	private static void initializeSolarSystems() {
		b.registerSolarSystem(yzCeti);
		b.registerSolarSystem(wolf1061);
		b.registerSolarSystem(hd219134);
		b.registerSolarSystem(trappist1);

	}

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

}