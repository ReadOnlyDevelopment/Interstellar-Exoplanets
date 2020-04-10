package net.rom.exoplanets.init;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.api.dimension.IAdvancedSpace.TypeBody;
import asmodeuscore.core.astronomy.BodiesData;
import asmodeuscore.core.astronomy.BodiesHelper;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraftforge.fml.common.Loader;
import net.rom.api.AstroBuilder;
import net.rom.exoplanets.Exoplanets;
import net.rom.exoplanets.conf.SConfigSystems;

public class SystemRegister {
	
	public static SolarSystem yzCeti;
	public static SolarSystem wolf1061;
	public static SolarSystem hd219134;
	public static SolarSystem trappist1;
	
	static AstroBuilder b = new AstroBuilder(Exoplanets.MODID);

	
	public static void init() {
		registerSolarSystems();
		initializeSolarSystems();
	}

	private static void registerSolarSystems() {
		
		yzCeti = b.buildSolarSystem("yz_ceti", "milky_way", yzPos(), "yz_ceti_a");
		
		//BUILD UNIMPLEMENTED SYSTEMS
		if (!SConfigSystems.hideUnfinishedSystems) {
			wolf1061 = b.buildSolarSystem("wolf_1061", "milky_way", wolfPos(), "wolf_1061_a");
			hd219134 = b.buildSolarSystem("hd_219134", "milky_way", hdPos(), "hd_219134_a");
			trappist1 = b.buildSolarSystem("trappist_1", "milky_way", trapPos(), "trappist_1_a");
		}
		
		if(Loader.isModLoaded("asmodeuscore")) {
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