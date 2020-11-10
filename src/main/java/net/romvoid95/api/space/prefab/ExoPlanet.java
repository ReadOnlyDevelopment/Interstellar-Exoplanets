/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.romvoid95.api.space.prefab;

import java.util.ArrayList;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.space.IExBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.romvoid95.api.space.Calculations;
import net.romvoid95.api.space.enums.EnumPlanetType;
import net.romvoid95.api.space.enums.EnumTPHClass;
import net.romvoid95.api.space.interfaces.IExoPlanet;


public class ExoPlanet extends Planet implements IExoPlanet, IExBody {

	private EnumTPHClass					habibilityClass;
	private EnumPlanetType					planetType;
	private String							exoplanetName;
	private Star							planetHost;
	private ExoSystem						planetSystem;
	private double							orbitPeriod;
	private double							distanceFromCenter;
	private double							planetMass;
	private double							planetRadius;
	private double							planetTemp;
	private double							gravity;
	private long							dayLength;
	private boolean							breathable;
	private boolean							rains;
	private AtmosphereInfo					atmos;
	private ArrayList<EnumAtmosphericGas>	atmosGasses	= new ArrayList<>();
	private ClassBody						classBody;
	private float							orbit_eccentricityX, orbit_eccentricityY;
	private float							orbit_offsetX, orbit_offsetY;

	public ExoPlanet (String planetName) {
		super(planetName.toLowerCase());
		this.setExoPlanetName(planetName);
		this.setAtmos();
		this.addChecklistKeys("thermal_padding", "equip_oxygen_suit", "equip_parachute");
		this.setPlanetType();
		this.setTHPClass();
	}
	
	@Override
	public AtmosphereInfo getAtmos() {
		return null;
	}

	@Override
	public ArrayList<EnumAtmosphericGas> getAtmosGasses() {
		return null;
	}

	public AtmosphereInfo getAtmosphereInfo() {
		return this.atmos;
	}

	@Override
	public float getAtmosphericPressure() {
		return 0.0F;
	}

	@Override
	public ClassBody getClassPlanet() {
		return this.classBody;
	}

	@Override
	public long getDayLength() {
		return this.dayLength;
	}

	public double getDistanceFromCenter() {
		return this.distanceFromCenter;
	}

	public ExoPlanet getExoPlanet() {
		return this;
	}

	@Override
	public String getExoPlanetName() {
		return this.exoplanetName;
	}

	@Override
	public float getGravity() {
		return (float) this.gravity;
	}

	@Override
	public double getOrbitPeriod() {
		return this.orbitPeriod;
	}

	@Override
	public Star getPlanetHost() {
		return this.planetHost;
	}

	@Override
	public double getPlanetMass() {
		return this.planetMass;
	}

	@Override
	public double getPlanetRadius() {
		return this.planetRadius;
	}

	@Override
	public ExoSystem getPlanetSystem() {
		return this.planetSystem;
	}

	@Override
	public double getPlanetTemp() {
		return this.planetTemp;
	}

	@Override
	public EnumPlanetType getPlanetType() {
		return this.planetType;
	}

	@Override
	public float getSolarRadiationMod() {
		return 0;
	}

	@Override
	public EnumTPHClass getTphClass() {
		return this.habibilityClass;
	}

	@Override
	public float getWaterPressure() {
		return 0;
	}

	@Override
	public float getXOrbitEccentricity() {
		return this.orbit_eccentricityX;
	}

	@Override
	public float getXOrbitOffset() {
		return this.orbit_offsetX;
	}

	@Override
	public float getYOrbitEccentricity() {
		return this.orbit_eccentricityY;
	}

	@Override
	public float getYOrbitOffset() {
		return this.orbit_offsetY;
	}

	@Override
	public boolean isBreathable() {
		return this.breathable;
	}

	public boolean isCorrosive(EnumTPHClass tphClass) {
		boolean cor = false;
		if (tphClass == EnumTPHClass.T) {
			cor = true;
			return cor;
		} else {
			return cor;
		}

	}

	@Override
	public boolean isDoesRain() {
		return this.rains;
	}

	/**
	 * @param atmos the atmos to set
	 */
	public Planet setAtmos() {
		this.atmos = setAtmos(new AtmosphereInfo(this.isBreathable(), this.isDoesRain(), isCorrosive(
				this.habibilityClass), 0.0F, 0.0F, 0.0F));
		return this;
	}

	public AtmosphereInfo setAtmos(AtmosphereInfo atmos) {
		return this.atmos = atmos;
	}

	/**
	 * @param atmosGasses the atmosGasses to set
	 */
	public Planet setAtmosGasses(EnumAtmosphericGas... gasses) {
		for (EnumAtmosphericGas gas : atmosGasses) {
			if (gas != null) {
				this.atmosphereComponent(gas);
				this.atmosGasses.add(gas);
			}
		}

		return this;
	}

	public void setAtmosphericPressure(int pressure) {

	}

	/**
	 * @param breathable the breathable to set
	 */
	public ExoPlanet setBreathable(boolean breathable) {
		this.breathable = breathable;
		return this;
	}

	public ExoPlanet setClassBody(ClassBody classBody) {
		this.classBody = classBody;
		return this;
	}

	public ExoPlanet setDayLength(long dayLength) {
		this.dayLength = dayLength;
		return this;
	}

	public ExoPlanet setDistanceFromCenter(float par1) {
		this.setDistanceFromCenter(par1, par1);
		return this;
	}

	public ExoPlanet setDistanceFromCenter(float par1, float par2) {
		this.setRelativeDistanceFromCenter(new ScalableDistance(par1, par2));
		return this;
	}

	public ExoPlanet setExoPlanetName(String name) {
		exoplanetName = name;
		return this;
	}

	public ExoPlanet setOrbitEccentricity(float eccentricityX, float eccentricityY) {
		this.orbit_eccentricityX = eccentricityX;
		this.orbit_eccentricityY = eccentricityY;
		return this;
	}

	public ExoPlanet setOrbitOffset(float x, float y) {
		this.orbit_offsetX = x;
		this.orbit_offsetY = y;
		return this;
	}

	/**
	 * @param orbitPeriod the orbitPeriod to set
	 */
	public ExoPlanet setOrbitPeriod(float orbitPeriod) {
		this.orbitPeriod = orbitPeriod;
		return this;
	}

	/**
	 * @param gravity the gravity to set
	 */
	public ExoPlanet setPlanetGravity(float gravity) {
		this.gravity = gravity;
		return this;
	}

	/**
	 * @param planetHost the planetHost to set
	 */
	public ExoPlanet setPlanetHost(Star planetHost) {
		this.planetHost = planetHost;
		return this;
	}


	/**
	 * @param planetMass the planetMass to set
	 */
	public ExoPlanet setPlanetMass(double planetMass) {
		this.planetMass = planetMass;
		return this;
	}

	/**
	 * @param planetMass the planetMass to set
	 */
	public ExoPlanet setPlanetRadius(double planetRadius) {
		this.planetRadius = planetRadius;
		return this;
	}

	/**
	 * @param planetSystem the planetSystem to set
	 */
	public ExoPlanet setPlanetSystem(ExoSystem planetSystem) {
		this.planetSystem = planetSystem;
		setParentSolarSystem(planetSystem);
		return this;
	}

	/**
	 * @param planetTemp the planetTemp to set
	 */
	public Planet setPlanetTemp(double planetTemp) {
		this.planetTemp = planetTemp;
		return this;
	}

	public ExoPlanet setPlanetType() {
		this.planetType = Calculations.getPlanetType(getPlanetMass(), getPlanetRadius());
		return this;
	}

	/**
	 * @param rains the rains to set
	 */
	public Planet setRains(boolean rains) {
		this.rains = rains;
		return this;
	}

	public ExoPlanet setTHPClass() {
		this.habibilityClass = Calculations.getTPHFromTemp(getPlanetTemp());
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("ExoPlanet [ ");
		builder.append(getExoPlanetName());
		builder.append(" ]\n");
		builder.append("ExoPlanet is an instance of IExBody = ");
		if(getExoPlanet() instanceof IExBody) {
			builder.append("TRUE");
			builder.append("\n");
			builder.append("orbit_offsetX = " + getXOrbitOffset());
			builder.append("\n");
			builder.append("orbit_offsetY = " + getYOrbitOffset());
			builder.append("\n");
			builder.append("orbit_eccentricityX = " + getXOrbitEccentricity());
			builder.append("\n");
			builder.append("orbit_eccentricityY = " + getYOrbitEccentricity());
		}  else {
			builder.append("FALSE");
		}
		return builder.toString();
	}
}
