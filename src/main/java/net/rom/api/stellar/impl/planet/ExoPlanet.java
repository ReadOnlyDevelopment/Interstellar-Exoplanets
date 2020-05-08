package net.rom.api.stellar.impl.planet;

import java.util.ArrayList;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.api.space.IExBody;
import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.rom.api.stellar.calc.Calculations;
import net.rom.api.stellar.enums.EnumDiscMethod;
import net.rom.api.stellar.enums.EnumPlanetType;
import net.rom.api.stellar.enums.EnumTPHClass;
import net.rom.api.stellar.interfaces.IExoPlanet;
import net.rom.api.stellar.world.WorldProviderExoPlanet;
import net.rom.exoplanets.ExoInfo;


public class ExoPlanet extends ExPlanet implements IExoPlanet {

	private EnumTPHClass habibilityClass;
	private EnumPlanetType planetType;
	private EnumDiscMethod method;
	private String planetName;
	private Star planetHost;
	private SolarSystem planetSystem;
	private double orbitPeriod;
	private double distanceFromCenter;
	private double planetMass;
	private double planetRadius;
	private double planetTemp;
	private double gravity;
	private long dayLength;
	private boolean breathable;
	private boolean rains;
	private AtmosphereInfo atmos;
	private ArrayList<EnumAtmosphericGas> atmosGasses = new ArrayList<EnumAtmosphericGas>();
	private WorldProviderExoPlanet planetProvider = null;
	private ClassBody classBody;

	public ExoPlanet(String planetName) {
		super(planetName);
		//BodiesHelper.registerExPlanet(planetSystem, planetName, ExoInfo.MODID, (float)distanceFromCenter);
		this.setAtmos();
		this.addChecklistKeys("thermal_padding", "equip_oxygen_suit", "equip_parachute");
		this.setPlanetType();
		this.setTHPClass();
		
	}

	/**
	 * @param method the method to set
	 */
	public ExoPlanet setMethod(EnumDiscMethod method) {
		this.method = method;
		return this;
	}

	public ExoPlanet setTHPClass() {
		this.habibilityClass = Calculations.getTPHFromTemp(getPlanetTemp());
		return this;
	}
	
	public ExoPlanet setPlanetType() {
		this.planetType = Calculations.getPlanetType(getPlanetMass(), getPlanetRadius());
		return this;
	}

	/**
	 * @param planetName the planetName to set
	 */
	public ExoPlanet setPlanetName(String planetName) {
		this.planetName = planetName;
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
	 * @param planetSystem the planetSystem to set
	 */
	public ExoPlanet setPlanetSystem(SolarSystem planetSystem) {
		this.planetSystem = planetSystem;
		setParentSolarSystem(planetSystem);
		return this;
	}
	
	public ExoPlanet setDayLength(float dayLength) {
		this.dayLength = (long) (24000L * dayLength);
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
	 * @param orbitPeriod the orbitPeriod to set
	 */
	public ExoPlanet setOrbitPeriod(float orbitPeriod) {
		this.orbitPeriod = orbitPeriod;
		return this;
	}
	
	public ExoPlanet setDistanceFromCenter(float par1, float par2) {
		this.setRelativeDistanceFromCenter(new ScalableDistance(par1, par2));
		return this;
	}
	
	public ExoPlanet setDistanceFromCenter(float par1) {
		this.setDistanceFromCenter(par1, par1);
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
	 * @param planetTemp the planetTemp to set
	 */
	public Planet setPlanetTemp(double planetTemp) {
		this.planetTemp = planetTemp;
		return this;
	}

	/**
	 * @param breathable the breathable to set
	 */
	public ExoPlanet setBreathable(boolean breathable) {
		this.breathable = breathable;
		return this;
	}

	/**
	 * @param rains the rains to set
	 */
	public Planet setRains(boolean rains) {
		this.rains = rains;
		return this;
	}

	/**
	 * @param atmos the atmos to set
	 */
	public Planet setAtmos() {
		this.atmos = new AtmosphereInfo(this.isBreathable(), this.isDoesRain(), isCorrosive(this.habibilityClass), 0.0F, 0.0F, 0.0F);
		return this;
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

	/**
	 * @param atmosGasses the atmosGasses to set
	 */
	public Planet setAtmosGasses(EnumAtmosphericGas... gasses) {
		for(EnumAtmosphericGas gas : atmosGasses) {
			if(gas != null) {
				this.atmosphereComponent(gas);
				this.atmosGasses.add(gas);
			}
		}

		return this;
	}

	/**
	 * @param planetProvider the planetProvider to set
	 */
	public ExoPlanet setExoPlanetProvider(WorldProviderExoPlanet planetProvider) {
		this.planetProvider = planetProvider;
		return this;
	}
	
	public WorldProviderExoPlanet getExoPlanetProvider() {
		return this.planetProvider;
	}

	@Override
	public String getPlanetName() {
		return this.planetName;
	}

	@Override
	public SolarSystem getPlanetSystem() {
		return this.planetSystem;
	}

	@Override
	public Star getPlanetHost() {
		return this.planetHost;
	}

	@Override
	public EnumDiscMethod getDiscoveryMethod() {
		return this.method;
	}

	public EnumTPHClass getTphClass() {
		return this.habibilityClass;
	}

	@Override
	public EnumPlanetType getPlanetType() {
		return this.planetType;
	}

	@Override
	public double getOrbitPeriod() {
		return this.orbitPeriod;
	}
	
	@Override
	public float getGravity() {
		return (float)this.gravity;
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
	public double getPlanetTemp() {
		return this.planetTemp;
	}

	@Override
	public boolean isBreathable() {
		return this.breathable;
	}

	@Override
	public boolean isDoesRain() {
		return this.rains;
	}

	@Override
	public AtmosphereInfo getAtmos() {
		return null;
	}
	
	@Override
	public long getDayLength() {
		return this.dayLength;
	}

	@Override
	public ArrayList<EnumAtmosphericGas> getAtmosGasses() {
		return null;
	}

	@Override
	public WorldProviderExoPlanet getPlanetProvider() {
		return null;
	}

	@Override
	public long getDayLenght() {
		return getDayLength();
	}

	@Override
	public int getAtmosphericPressure() {
		return 0;
	}

	@Override
	public float getSolarRadiationMod() {
		return 0;
	}

	public ExoPlanet setClassBody(ClassBody classBody) {
		this.classBody = classBody;
		return this;
	}

	@Override
	public ClassBody getClassPlanet() {
		return this.classBody;
	}

	public double getDistanceFromCenter() {
		return this.distanceFromCenter;
	}

}
