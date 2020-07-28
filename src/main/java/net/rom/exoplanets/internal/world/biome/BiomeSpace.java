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

package net.rom.exoplanets.internal.world.biome;

import java.util.Random;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.minecraft.block.Block;
import net.rom.exoplanets.internal.enums.EnumTPHClass;
import net.rom.exoplanets.internal.world.WorldProviderExoPlanet;
import net.rom.exoplanets.internal.world.planet.ExoPlanet;

public class BiomeSpace extends ExoPlanetBiomeBase {

	protected Planet planetForBiome = null;
	protected WorldProviderExoPlanet spaceProvider = null;
	public static int grassFoliageColor = 0x00ff00;

	public BiomeSpace(BiomeProperties properties) {
		super(properties);
		this.init();
	}

	public BiomeSpace(String singleName, BiomeProperties props) {
		super(singleName, props);
		this.init();
	}

	private void init() {
		this.clearAllSpawning();
	}

	/**
	 * Sets the Planet to be associated with this biome.
	 * 
	 * @param planet The Planet to associate with this biome.
	 * @return The biome for the set Planet.
	 */
	public BiomeSpace setPlanetForBiome(Planet planet) {
		this.planetForBiome = planet;
		return this;
	}

	/**
	 * Gets the Planet associated with this biome.
	 * 
	 * @return The Planet associated with this biome.
	 */
	public Planet getPlanetForBiome() {
		return this.planetForBiome;
	}

	public BiomeSpace setSpaceProvider(WorldProviderExoPlanet provider) {
		this.spaceProvider = provider;
		return this;
	}

	public WorldProviderExoPlanet getSpaceProvider() {
		return this.spaceProvider;
	}

	/**
	 * Checks if the biome is a hot biome or not.
	 * 
	 * @return True if the biome temp is >= 7.0f, otherwise false.
	 */
	public boolean getIsHotBiome() {
		return (this.getBiomeTemp() >= 7F);
	}

	/**
	 * Checks if the biome is a cold biome or not.
	 * 
	 * @return True if the biome temp is <= 3.0f, otherwise false.
	 */
	public boolean getIsColdBiome() {
		return (this.getBiomeTemp() <= 3F);
	}

	@Override
	public BiomeSpace setBlocks(Block topBlock, Block fillerBlock) {
		this.topBlock = topBlock.getDefaultState();
		this.fillerBlock = fillerBlock.getDefaultState();
		return this;
	}

	/**
	 * Sets the Biome temperature.
	 * 
	 * @param biomeTemp Biome temperature.
	 * @return The Planet to apply this biome to.
	 */
	public BiomeSpace setTemp(float biomeTemp) {
		this.temp = biomeTemp;
		return this;
	}

	/**
	 * Returns the temperature of the current biome.
	 * 
	 * @return Biome temp.
	 */
	public float getBiomeTemp() {
		return this.temp;
	}

	/**
	 * Returns the actual temperature of the Planet, taking biome temp into account.
	 * 
	 * @return The current temperature of the Planet.
	 */
	public float getPlanetTemp() {
		ExoPlanet planet = (ExoPlanet) this.getPlanetForBiome();

		float biomeTemp = this.getBiomeTemp();
		@SuppressWarnings("unused")
		Random rand = new Random();

		float planetTemp = (float) planet.getPlanetTemp();
		float flucTemp = planetTemp;

		float maxTemp = planetTemp + 25;
		float minTemp = planetTemp - 25;

		if (planet.getTphClass() == EnumTPHClass.HP || planet.getTphClass() == EnumTPHClass.P) {
			flucTemp -= biomeTemp;
		} else if (planet.getTphClass() == EnumTPHClass.T || planet.getTphClass() == EnumTPHClass.HT) {
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