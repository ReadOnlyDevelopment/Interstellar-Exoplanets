package net.rom.api.stellar.impl.planet;

import java.util.ArrayList;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import net.rom.api.stellar.enums.EnumClass;
import net.rom.api.stellar.enums.EnumDiscMethod;
import net.rom.api.stellar.enums.EnumTPHClass;
import net.rom.api.stellar.interfaces.IExoPlanet;
import net.rom.api.stellar.world.WorldProviderExoPlanet;


public class ExoPlanet extends Planet implements IExoPlanet {

	private EnumClass exoClass;
	private EnumTPHClass habibilityClass;
	//private EnumPlanetType planetType;
	private EnumDiscMethod method;
	private String planetName;
	private Star planetHost;
	private SolarSystem planetSystem;
	private String planetLetter;
	private float orbitPeriod;
	private float distanceFromCenter;
	private int planetDensity;
	private float planetMass;
	private float planetTemp;
	private float baseRadiation;
	private float baseToxicity;
	private float gravity;
	private long dayLength;
	private boolean breathable;
	private boolean rains;
	private AtmosphereInfo atmos;
	private ArrayList<EnumAtmosphericGas> atmosGasses = new ArrayList<EnumAtmosphericGas>();
	private WorldProviderExoPlanet planetProvider = null;

	public ExoPlanet(String planetName) {
		super(planetName);
		this.setAtmos();
		this.addChecklistKeys("thermal_padding", "equip_oxygen_suit", "equip_parachute");
		
	}

	/**
	 * @param exoClass the exoClass to set
	 */
	public ExoPlanet setExoClass(EnumClass exoClass) {
		this.exoClass = exoClass;
		return this;
	}

	/**
	 * @param method the method to set
	 */
	public ExoPlanet setMethod(EnumDiscMethod method) {
		this.method = method;
		return this;
	}

	public ExoPlanet setTHPClass() {
		this.habibilityClass = EnumTPHClass.getTPHFromTemp(getPlanetTemp());
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

	/**
	 * @param planetLetter the planetLetter to set
	 */
	public ExoPlanet setPlanetLetter(String planetLetter) {
		this.planetLetter = planetLetter;
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

	/**
	 * @param planetDensity the planetDensity to set
	 */
	public ExoPlanet setPlanetDensity(int planetDensity) {
		this.planetDensity = planetDensity;
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
	public ExoPlanet setPlanetMass(float planetMass) {
		this.planetMass = planetMass;
		return this;
	}

	/**
	 * @param planetTemp the planetTemp to set
	 */
	public Planet setPlanetTemp(float planetTemp) {
		this.planetTemp = planetTemp;
		return this;
	}

	/**
	 * @param baseRadiation the baseRadiation to set
	 */
	public Planet setBaseRadiation(float baseRadiation) {
		this.baseRadiation = baseRadiation;
		return this;
	}

	/**
	 * @param baseToxicity the baseToxicity to set
	 */
	public Planet setBaseToxicity(float baseToxicity) {
		this.baseToxicity = baseToxicity;
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
		this.atmos = new AtmosphereInfo(this.isBreathable(), this.isDoesRain(), isCorrosive(this.habibilityClass), 0.0F, 0.0F, this.getBaseRadiation());
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
	public String getPlanetLetter() {
		return this.planetLetter;
	}

	@Override
	public EnumClass getExoClass() {
		return this.exoClass;
	}

	@Override
	public EnumDiscMethod getMethod() {
		return this.method;
	}

	public EnumTPHClass getTphClass() {
		return this.habibilityClass;
	}

	@Override
	public float getOrbitPeriod() {
		return this.orbitPeriod;
	}
	
	@Override
	public float getGravity() {
		return this.gravity;
	}

	@Override
	public int getPlanetDensity() {
		return this.planetDensity;
	}

	@Override
	public float getPlanetMass() {
		return this.planetMass;
	}

	@Override
	public float getPlanetTemp() {
		return this.planetTemp;
	}

	@Override
	public float getBaseRadiation() {
		return this.baseRadiation;
	}

	@Override
	public float getBaseToxicity() {
		return this.baseToxicity;
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

	public boolean getIsColdPlanet() {
		return this.planetTemp <= -25.0F;
	}

	public boolean getIsHotPlanet() {
		return this.planetTemp >= 115.0F;
	}

}
