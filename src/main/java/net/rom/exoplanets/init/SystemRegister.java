package net.rom.exoplanets.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraftforge.fml.common.Loader;
import net.rom.core.space.AstroBuilder;
import net.rom.exoplanets.Exoplanets;

public class SystemRegister {
	
	public static SolarSystem YZCETI;
	static AstroBuilder b = new AstroBuilder(Exoplanets.MODID);
	
	public static void init() {
		registerSolarSystems();
		initializeSolarSystems();
	}

	private static void registerSolarSystems() {
		
		YZCETI = b.buildSolarSystem("yzceti", "milky_way", new Vector3(0.7F,  1.3F, 0.0F), "yzcetia");
		if(Loader.isModLoaded("asmodeuscore")) {
			BodiesData data = new BodiesData(TypeBody.STAR, ClassBody.DWARF).setStarColor(StarColor.RED);
	        BodiesHelper.registerBody(YZCETI.getMainStar(), data, false);
		}

	}

	private static void initializeSolarSystems() {
		b.registerSolarSystem(YZCETI);
	}
	
}