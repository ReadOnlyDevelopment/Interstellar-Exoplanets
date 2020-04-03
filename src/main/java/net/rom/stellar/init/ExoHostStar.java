package net.rom.stellar.init;

import net.rom.core.space.AstroBuilder;
import net.rom.core.space.implemtations.star.ExoStar;
import net.rom.stellar.Exoplanets;

public class ExoHostStar {
	
	public static ExoStar yzcetia;
		
	public static void init() {
		buildAndRegisterExoStar();
	}

	private static void buildAndRegisterExoStar() {
		
		yzcetia = new ExoStar("yzcetia");
		yzcetia.setStarRadius(0.17F); //0.17 Suns
		yzcetia.setStarMass(0.13F); //0.13 Suns
		yzcetia.setSurfaceTemp(5430); //G8 = 5430K
		yzcetia.setSpectralClass();
		yzcetia.setStarSolarSystem(ExoStarSystem.YZCETI);
		
	}


}
