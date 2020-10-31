package net.rom.api.space;

import micdoodle8.mods.galacticraft.api.galaxies.Star;
import net.rom.exoplanets.internal.enums.EnumLuminosityClass;
import net.rom.exoplanets.internal.enums.EnumSpectralClass;
import net.rom.exoplanets.internal.world.star.IExoStar;

public class ExoStar extends Star implements IExoStar {

	private EnumSpectralClass spectralClass;
	private EnumLuminosityClass luminosityClass;
	private String starName;
	private int surfaceTemp;
	private double radius;
	private double mass;


	public ExoStar(String starName) {
		super(starName);
		this.setSpectralClass();
	}

	public ExoStar setStarName(String starName) {
		this.starName = starName;
		return this;
	}

	public ExoStar setSurfaceTemp(int surfaceTemp) {
		this.surfaceTemp = surfaceTemp;
		return this;
	}

	public ExoStar setStarMass(double mass) {
		this.mass = mass;
		return this;
	}

	public ExoStar setStarRadius(double radius) {
		this.radius = radius;
		return this;
	}

	public ExoStar setSpectralClass() {
		this.spectralClass = EnumSpectralClass.getClass(getSurfaceTemp());
		return this;
	}
	
	public ExoStar setLuminosityClass(EnumLuminosityClass luminosityClass) {
		this.luminosityClass = luminosityClass;
		return this;
	}
	
	@Override
	public String getStarName() {
		return this.starName;
	}

	@Override
	public int getSurfaceTemp() {
		return this.surfaceTemp;
	}

	@Override
	public double getStellarRadius() {
		return this.radius;
	}

	@Override
	public EnumSpectralClass getSpectralClassifcation() {
		return this.spectralClass;
	}
	
	public EnumLuminosityClass getLuminosityClass() {
		return this.luminosityClass;
	}

	@Override
	public double getMass() {
		return this.mass;
	}


}
