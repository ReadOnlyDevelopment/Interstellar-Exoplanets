package net.romvoid95.common.config.gen;

import net.minecraftforge.common.config.Config;

@Config(name = "/Exoplanets/Systems/UndergroundConfigs", modid = "exoplanets")
public class UndergroundConfigs {

	@Config.Name("Underground Generation")
	@Config.Comment("Configure settings related to caves, caverns, ravines and more.")
	public static ConfigUndergroundGen caveSettings = new ConfigUndergroundGen();

	@Config.Name("Bedrock Generation")
	@Config.Comment("Configure how bedrock generates in the overworld and nether.")
	public static ConfigBedrockGen bedrockSettings = new ConfigBedrockGen();

	@Config.Name("Debug settings")
	@Config.Comment("Don't mess with these settings for normal gameplay.")
	public static ConfigDebug debugsettings = new ConfigDebug();

	public static class ConfigDebug {
		@Config.Name("Enable DEBUG Visualizer")
		@Config.Comment(
				"The visualizer creates worlds where there are no blocks except those indicating where caves\n" +
						"    and caverns would be carved out in a regular world. This is useful for visualizing the kinds of\n" +
						"    caves and caverns your current config options will create.\n" +
						"    Type 1 Cave: Wooden Planks\n" +
						"    Type 2 Cave: Cobblestone\n" +
						"    Lava Cavern: Redstone Block\n" +
						"    Floored Cavern: Gold Block\n" +
						"    Surface Cave: Emerald Block\n" +
						"    Vanilla Cave: Bricks\n" +
				"Default: false")
		public boolean debugVisualizer = false;
	}
	
	public static class ConfigBedrockGen {

		@Config.Comment("Replaces the usual bedrock generation pattern with flat layers.\n" +
				"    Activates in all whitelisted dimension, where applicable. The End is unaffected.")
		@Config.RequiresWorldRestart
		public boolean flattenBedrock = true;

		@Config.Comment("The width of the bedrock layer. Only works if Flatten Bedrock is true.")
		@Config.RequiresWorldRestart
		@Config.RangeInt(min = 0, max = 256)
		public int bedrockWidth = 1;
	}
}
