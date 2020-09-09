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

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.romvoid95.common.astronomy.ExoplanetBiomes;
import net.romvoid95.common.world.helpers.EnumBiomeType;

public class ExoPlanetBiomeBase extends BiomeGenBaseGC {

	protected TempCategory tempBiomeCtg = TempCategory.COLD;
	protected EnumBiomeType biomeType = EnumBiomeType.SPACE;

	protected boolean hasMutation = false;
	protected boolean enableSnow = false;
	protected int skyColor = 0x0099ff;
	protected float temp = 0.0F;
	protected int BIOME_HEIGHT = 72;

	@SuppressWarnings("unused")
	private String singleName = "";

	protected Block stoneBlock;

	public ExoPlanetBiomeBase(BiomeProperties properties) {
		super(properties, true);
		this.clearAllSpawning();
		ExoplanetBiomes.biomeList.add(this);
	}

	public ExoPlanetBiomeBase(String singleName, BiomeProperties properties) {
		this(properties);
		this.singleName = singleName;
	}

	public Biome setBiomeHeight(int biomeHeight) {
		this.BIOME_HEIGHT = biomeHeight;
		return this;
	}

	public int getBiomeHeight() {
		return this.BIOME_HEIGHT;
	}

	public Biome setSkyColor(int par1SkyColor) {
		this.skyColor = par1SkyColor;
		return this;
	}

	@Override
	public int getSkyColorByTemp(float temp) {
		return this.skyColor;
	}

	public Biome setEnableSnow(boolean shouldSnow) {
		this.enableSnow = shouldSnow;
		return this;
	}

	@Override
	public boolean getEnableSnow() {
		return this.enableSnow;
	}

	@Override
	public boolean isMutation() {
		return hasMutation;
	}

	public Biome setHasMutation(boolean isMutated) {
		this.hasMutation = isMutated;
		return this;
	}

	public Biome clearAllSpawning() {
		this.spawnableCreatureList.clear();
		this.spawnableMonsterList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		return this;
	}

	public Biome clearAllNonMonsterSpawning() {
		this.spawnableCreatureList.clear();
		this.spawnableCaveCreatureList.clear();
		this.spawnableWaterCreatureList.clear();
		return this;
	}

	public Biome setNoRain(Biome.BiomeProperties props) {
		props.setRainfall(0.0F);
		props.setRainDisabled();
		return this;
	}

	public Biome setBlocks(Block block) {
		this.setBlocks(block, block);
		return this;
	}

	public Biome setBlocks(Block topBlock, Block fillerBlock) {
		this.setTopBlock(topBlock);
		this.setFillerBlock(fillerBlock);
		return this;
	}

	public Biome setFillerBlock(Block b) {
		this.fillerBlock = b.getDefaultState();
		return this;
	}

	public Block getFillerBlock() {
		return this.fillerBlock.getBlock();
	}

	public Biome setTopBlock(Block b) {
		this.topBlock = b.getDefaultState();
		return this;
	}

	public Block getTopBlock() {
		return this.topBlock.getBlock();
	}

	public Biome setStoneBlock(Block b) {
		this.stoneBlock = b;
		return this;
	}

	public Block getStoneBlock() {
		return this.stoneBlock;
	}

	public Biome setTempCategory(TempCategory tc) {
		this.tempBiomeCtg = tc;
		return this;
	}

	@Override
	public TempCategory getTempCategory() {
		return this.tempBiomeCtg;
	}

	public EnumBiomeType getBiomeType() {
		return this.biomeType;
	}

	public Biome setBiomeType(EnumBiomeType t) {
		this.biomeType = t;
		return this;
	}
}