package net.rom.stellar.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.rom.core.space.AstroBuilder;
import net.rom.stellar.Exoplanets;
import net.rom.stellar.util.ModCheckUtil;

public class ExoStarSystem {
	
	public static SolarSystem YZCETI;
	static AstroBuilder b = new AstroBuilder(Exoplanets.MODID);
	
	public static void init() {
		registerSolarSystems();
		initializeSolarSystems();
	}

	private static void registerSolarSystems() {
		
		YZCETI = b.buildSolarSystem("yzceti", "milky_way", new Vector3(1.0F, 1.0F, 0.0F), ExoHostStar.yzcetia);
		
		// ######################## SET ASMODEUSCORE MAP DATA  ########################
		
				if(ModCheckUtil.asmodeusCoreLoaded()) {
					BodiesData data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
					BodiesHelper.registerBody(ExoHostStar.yzcetia, data , false);
				}
	}

	private static void initializeSolarSystems() {
		b.registerSolarSystem(YZCETI);
	}
	
}