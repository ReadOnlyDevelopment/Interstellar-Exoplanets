package net.rom.api.stellar.impl.star;

import micdoodle8.mods.galacticraft.api.galaxies.Star;
import net.rom.api.stellar.enums.SpectralClass;
import net.rom.api.stellar.interfaces.IExoStar;

public class ExoStar extends Star implements IExoStar {

	private SpectralClass spectralClass;
	private String starName;
	private int surfaceTemp;
	private double radius;
	private double mass;


	public ExoStar(String starName) {
		super(starName);
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
		this.spectralClass = SpectralClass.getClass(getSurfaceTemp());
		return this;
	}

	@Override
	public String getStarName() {
		return this.starName;
	}

//	@Override
//	public SolarSystem getStarSystem() {
//		return this.starSystem;
//	}

	@Override
	public int getSurfaceTemp() {
		return this.surfaceTemp;
	}

	@Override
	public double getStellarRadius() {
		return this.radius;
	}

	@Override
	public SpectralClass getSpectralClassifcation() {
		return this.spectralClass;
	}

	@Override
	public double getMass() {
		return this.mass;
	}


}
