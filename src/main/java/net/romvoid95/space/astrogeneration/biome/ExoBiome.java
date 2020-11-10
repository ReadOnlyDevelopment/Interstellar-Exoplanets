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
package net.romvoid95.space.astrogeneration.biome;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.romvoid95.api.enums.EnumBiomeType;
import net.romvoid95.api.space.enums.EnumTPHClass;
import net.romvoid95.api.space.prefab.ExoPlanet;

public abstract class ExoBiome extends BiomeGenBaseGC {

	protected TempCategory tempBiomeCtg = TempCategory.COLD;
	protected EnumBiomeType biomeType = EnumBiomeType.SPACE;
	private ExoPlanet planetForBiome;
	private BiomeData biomeData;
	private boolean hotBiome, coldBiome;

	public ExoBiome(BiomeData biomeData) {
		super(biomeData, true);
		this.biomeData = biomeData;
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();

	}
//	
//	public ExoBiome(BiomeData biomeData, boolean ad) {
//		super(biomeData, ad);
//		this.biomeData = biomeData;
//		this.spawnableCreatureList.clear();
//		this.spawnableMonsterList.clear();
//		this.spawnableCaveCreatureList.clear();
//		this.spawnableWaterCreatureList.clear();
//
//	}

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

	public void generateTopBlocks(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal, Block stoneBlock) {

	}
}
