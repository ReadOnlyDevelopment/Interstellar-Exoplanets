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

package net.romvoid95.common.world.biome;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.world.WorldProvider;

import micdoodle8.mods.galacticraft.api.galaxies.Planet;

import net.romvoid95.api.space.enums.EnumTPHClass;
import net.romvoid95.api.space.prefab.ExoPlanet;

public class BiomeSpace extends ExoPlanetBiomeBase {

	protected Planet planetForBiome = null;
	protected WorldProvider spaceProvider = null;
	protected Block planetGrassBlock = null;
	public static int grassFoliageColor = 0x00ff00;

	public BiomeSpace(BiomeProperties properties) {
		super(properties);
		this.init();
	}
	
	public BiomeSpace(PropsBuilder props) {
		super(props.build());
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

	public BiomeSpace setSpaceProvider(WorldProvider provider) {
		this.spaceProvider = provider;
		return this;
	}

	public WorldProvider getSpaceProvider() {
		return this.spaceProvider;
	}

	public BiomeSpace setPlanetGrassBlock(Block block) {
		this.planetGrassBlock = block;
		return this;
	}

	public Block getPlanetGrassBlock() {
		return this.planetGrassBlock;
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
	
    public static class PropsBuilder
    {
        private final String biomeName;
        private float baseHeight = 0.1F;
        private float heightVariation = 0.2F;
        private float temperature = 0.5F;
        private float rainfall = 0.5F;
        private int waterColor = 16777215;
        private boolean enableSnow = false;
        private boolean enableRain = true;
        private String baseBiomeRegName;

        public PropsBuilder(String name) { this.biomeName = name; }

        public PropsBuilder withTemperature(Float temperature) { if (temperature != null) this.temperature = temperature; return this; }
        public PropsBuilder withRainfall(Float rainfall) { if (rainfall != null) this.rainfall = rainfall; return this; }
        public PropsBuilder withBaseHeight(Float baseHeight) { if (baseHeight != null) this.baseHeight = baseHeight; return this; }
        public PropsBuilder withHeightVariation(Float heightVariation) { if (heightVariation != null) this.heightVariation = heightVariation; return this; }
        public PropsBuilder withRainDisabled() { this.enableRain = false; return this; }
        public PropsBuilder withSnowEnabled() { this.enableSnow = true; return this; }
        public PropsBuilder withWaterColor(Integer waterColor) { if (waterColor != null) this.waterColor = waterColor; return this; }
        public PropsBuilder withBaseBiome(String name) { if (name != null) this.baseBiomeRegName = name; return this; }

        public BiomeProps build()
        {
            return new BiomeProps(this.biomeName, this.temperature, this.rainfall, this.baseHeight, this.heightVariation, this.enableRain, this.enableSnow, this.waterColor, this.baseBiomeRegName);
        }
    }

    public static class BiomeProps extends BiomeProperties
    {
        private BiomeProps(String name, float temperature, float rainfall, float baseHeight, float heightVariation, boolean enableRain, boolean enableSnow, int waterColor, String baseBiomeRegName)
        {
            super(name);

            this.setTemperature(temperature);
            this.setRainfall(rainfall);
            this.setBaseHeight(baseHeight);
            this.setHeightVariation(heightVariation);
            if (!enableRain) this.setRainDisabled();
            if (enableSnow) this.setSnowEnabled();
            this.setWaterColor(waterColor);
            this.setBaseBiome(baseBiomeRegName);
        }
    }
}