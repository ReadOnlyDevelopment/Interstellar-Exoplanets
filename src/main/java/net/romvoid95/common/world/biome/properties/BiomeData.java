package net.romvoid95.common.world.biome.properties;

import javax.annotation.Nonnull;

import net.minecraft.world.biome.Biome.BiomeProperties;

public class BiomeData extends BiomeProperties {

	private final String biomeName;
	private float baseHeight;
	private float heightVariation;
	private float temperature;
	private float rainfall;
	private int waterColor;
	private boolean snowEnabled;
	private boolean rainEnabled;

	BiomeData (@Nonnull String biomeName, float baseHeight, float heightVariation, float temperature, float rainfall, int waterColor, boolean snowEnabled, boolean rainEnabled) {
		super(biomeName);
		this.biomeName = biomeName;
		this.baseHeight = baseHeight;
		this.heightVariation = heightVariation;
		this.temperature = temperature;
		this.rainfall = rainfall;
		this.waterColor = waterColor;
		this.snowEnabled = snowEnabled;
		this.rainEnabled = rainEnabled;
	}

	public static BiomeDataBuilder builder() {
		return new BiomeDataBuilder();
	}

	public static class BiomeDataBuilder {
		private String biomeName;
		private float baseHeight;
		private float heightVariation;
		private float temperature;
		private float rainfall;
		private int waterColor;
		private boolean snowEnabled;
		private boolean rainEnabled;

		public BiomeDataBuilder biomeName(@Nonnull String biomeName) {
			this.biomeName = biomeName;
			return this;
		}

		public BiomeDataBuilder baseHeight(float baseHeight) {
			this.baseHeight = baseHeight;
			return this;
		}

		public BiomeDataBuilder heightVariation(float heightVariation) {
			this.heightVariation = heightVariation;
			return this;
		}

		public BiomeDataBuilder temperature(float temperature) {
			this.temperature = temperature;
			return this;
		}

		public BiomeDataBuilder rainfall(float rainfall) {
			this.rainfall = rainfall;
			return this;
		}

		public BiomeDataBuilder waterColor(int waterColor) {
			this.waterColor = waterColor;
			return this;
		}

		public BiomeDataBuilder snowEnabled(boolean snowEnabled) {
			this.snowEnabled = snowEnabled;
			return this;
		}

		public BiomeDataBuilder rainEnabled(boolean rainEnabled) {
			this.rainEnabled = rainEnabled;
			return this;
		}

		public BiomeData generate() {
			return new BiomeData(this.biomeName, this.baseHeight, this.heightVariation, this.temperature, this.rainfall, this.waterColor, this.snowEnabled, this.rainEnabled);
		}

		@Override
		public String toString() {
			return "BiomeSettings.BiomeDataBuilder(biomeName=" + this.biomeName + ", baseHeight=" + this.baseHeight
					+ ", heightVariation=" + this.heightVariation + ", temperature=" + this.temperature + ", rainfall="
					+ this.rainfall + ", waterColor=" + this.waterColor + ", snowEnabled=" + this.snowEnabled
					+ ", rainEnabled=" + this.rainEnabled + ")";
		}
	}


	@Override
	public boolean equals(Object o) {
		if (o == this)
			return true;
		if (!(o instanceof BiomeData))
			return false;
		BiomeData other = (BiomeData) o;
		if (!other.canEqual(this))
			return false;
		Object this$biomeName = getBiomeName(), other$biomeName = other.getBiomeName();
		return ((this$biomeName == null) ? (other$biomeName != null) : !this$biomeName.equals(other$biomeName)) ? false
				: ((Float.compare(getBaseHeight(), other.getBaseHeight()) != 0) ? false
						: ((Float.compare(getHeightVariation(), other.getHeightVariation()) != 0) ? false
								: ((Float.compare(getTemperature(), other.getTemperature()) != 0) ? false
										: ((Float.compare(getRainfall(), other.getRainfall()) != 0) ? false
												: ((getWaterColor() != other.getWaterColor()) ? false
														: ((isSnowEnabled() != other.isSnowEnabled()) ? false
																: ((isRainEnabled() == other.isRainEnabled()))))))));
	}

	protected boolean canEqual(Object other) {
		return other instanceof BiomeData;
	}

	@Override
	public String toString() {
		return "BiomeSettings(biomeName=" + getBiomeName() + ", baseHeight=" + getBaseHeight() + ", heightVariation="
				+ getHeightVariation() + ", temperature=" + getTemperature() + ", rainfall=" + getRainfall()
				+ ", waterColor=" + getWaterColor() + ", snowEnabled=" + isSnowEnabled() + ", rainEnabled="
				+ isRainEnabled() + ")";
	}

	@Nonnull
	public String getBiomeName() {
		return this.biomeName;
	}

	public float getBaseHeight() {
		return this.baseHeight;
	}

	public float getHeightVariation() {
		return this.heightVariation;
	}

	public float getTemperature() {
		return this.temperature;
	}

	public float getRainfall() {
		return this.rainfall;
	}

	public int getWaterColor() {
		return this.waterColor;
	}

	public boolean isSnowEnabled() {
		return this.snowEnabled;
	}

	public boolean isRainEnabled() {
		return this.rainEnabled;
	}

	public static BiomeData buildDefault(String nameIn) {
		return builder().biomeName(nameIn).baseHeight(0.1F).heightVariation(0.2F).temperature(0.5F).rainfall(
				0.0F).waterColor(16777215).rainEnabled(false).snowEnabled(false).generate();
	}
}
