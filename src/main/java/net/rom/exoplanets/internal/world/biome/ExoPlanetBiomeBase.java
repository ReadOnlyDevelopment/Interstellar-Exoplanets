/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.world.biome;

import micdoodle8.mods.galacticraft.api.world.BiomeGenBaseGC;
import net.minecraft.block.Block;
import net.minecraft.world.biome.Biome;
import net.rom.exoplanets.astronomy.ExoplanetBiomes;
import net.rom.exoplanets.internal.enums.EnumBiomeType;

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