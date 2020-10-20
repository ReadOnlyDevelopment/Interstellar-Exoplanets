package net.romvoid95.common.world.biome;

import java.util.Random;

import net.minecraft.world.WorldProvider;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;

import net.romvoid95.api.space.enums.EnumTPHClass;
import net.romvoid95.api.space.prefab.ExoPlanet;
import net.romvoid95.common.world.biome.properties.BiomeData;
import net.romvoid95.common.world.helpers.EnumBiomeType;

public abstract class ExoBiome extends BiomeGenBaseGC {

	protected TempCategory					tempBiomeCtg	= TempCategory.COLD;
	protected EnumBiomeType					biomeType		= EnumBiomeType.SPACE;
	private ExoPlanet						planetForBiome;
	private Class<? extends WorldProvider>	spaceProvider;
	private BiomeData						biomeData;
	private boolean							hotBiome, coldBiome;

	public ExoBiome (BiomeData biomeData) {
		super(biomeData, true);
		this.biomeData = biomeData;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();

	}

	public EnumBiomeType getBiomeType() {
		return this.biomeType;
	}

	public ExoBiome setBiomeType(EnumBiomeType biomeType) {
		this.biomeType = biomeType;
		return this;
	}

	public ExoPlanet getPlanetForBiome() {
		return this.planetForBiome;
	}

	public ExoBiome setPlanetForBiome(ExoPlanet planetForBiome) {
		this.planetForBiome = planetForBiome;
		return this;
	}

	public Class<? extends WorldProvider> getSpaceProvider() {
		return spaceProvider;
	}

	public ExoBiome setSpaceProvider(Class<? extends WorldProvider> spaceProvider) {
		this.spaceProvider = spaceProvider;
		return this;
	}

	/**
	 * Checks if the biome is a hot biome or not.
	 * 
	 * @return True if the biome temp is >= 7.0f, otherwise false.
	 */
	public boolean determineHotCold() {
		return (this.biomeData.getTemperature() >= 7F) ? hotBiome : coldBiome;
	}

	/**
	 * Returns the actual temperature of the Planet, taking biome temp into account.
	 * 
	 * @return The current temperature of the Planet.
	 */
	public float getPlanetTemp() {
		ExoPlanet planet = this.getPlanetForBiome();

		float biomeTemp = this.getDefaultTemperature();
		@SuppressWarnings("unused")
		Random rand = new Random();

		float planetTemp = (float) planet.getPlanetTemp();
		float flucTemp = planetTemp;

		float maxTemp = planetTemp + 25;
		float minTemp = planetTemp - 25;

		if ((planet.getTphClass() == EnumTPHClass.HP) || (planet.getTphClass() == EnumTPHClass.P)) {
			flucTemp -= biomeTemp;
		} else if ((planet.getTphClass() == EnumTPHClass.T) || (planet.getTphClass() == EnumTPHClass.HT)) {
			flucTemp += biomeTemp;
		} else {
			flucTemp = planetTemp + biomeTemp;
		}

		if (planetTemp < minTemp) {
			planetTemp = minTemp;
		}

		if (planetTemp > maxTemp) {
			planetTemp = maxTemp;
		}

		return flucTemp;
	}
}
