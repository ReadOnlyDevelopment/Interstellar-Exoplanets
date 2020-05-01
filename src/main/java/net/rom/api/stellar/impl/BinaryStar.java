package net.rom.api.stellar.impl;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.rom.api.stellar.enums.EnumSpectralClass;
import net.rom.api.stellar.interfaces.IBinary;
import net.rom.api.stellar.interfaces.IExoStar;



/**
 * My attempt at creating a true binary star system. Which would have two
 * Star companions orbit each other around a center-of-mass point on the
 * GuiCelestialScreen while also having 
 * 
 * @author ROMVoid
 *
 */
public abstract class BinaryStar extends CelestialBody implements IBinary {

	private EnumSpectralClass spectralClass;
	private BinaryStar companionStar;
	private String starName;
	private int surfaceTemp;
	private double radius;
	private double mass;

	public BinaryStar(String bodyName) {
		super(bodyName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getStarName() {
		return null;
	}

	@Override
	public int getSurfaceTemp() {
		return 0;
	}

	@Override
	public double getStellarRadius() {
		return 0;
	}

	@Override
	public double getMass() {
		return 0;
	}

	@Override
	public EnumSpectralClass getSpectralClassifcation() {
		return null;
	}

	@Override
	public BinaryStar getCompanionStar() {
		return null;
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public String getUnlocalizedNamePrefix() {
		return null;
	}

}
