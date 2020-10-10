package net.romvoid95.common.config.gen;

import net.minecraftforge.common.config.Config;

import net.romvoid95.common.config.gen.cave.ConfigCaves;
import net.romvoid95.common.config.gen.cavern.ConfigCaverns;
import net.romvoid95.common.config.gen.ravine.ConfigRavine;
import net.romvoid95.common.world.cave.enums.RegionSize;

public class ConfigUndergroundGen {
	@Config.Name("Caves")
	@Config.Comment("Settings used in the generation of caves.")
	public ConfigCaves caves = new ConfigCaves();

	@Config.Name("Caverns")
	@Config.Comment("Settings used in the generation of caverns. Caverns are spacious caves at low altitudes.")
	public ConfigCaverns caverns = new ConfigCaverns();

	@Config.Name("Ravines")
	@Config.Comment("Settings used for ravine generation.")
	public ConfigRavine ravines = new ConfigRavine();
	
	@Config.Name("Water Regions")
	@Config.Comment("Settings used in the generation of water regions.")
	public ConfigWaterRegions waterRegions = new ConfigWaterRegions();

	@Config.Name("Miscellaneous")
	@Config.Comment("Miscellaneous settings used in cave and cavern generation.")
	public ConfigMisc miscellaneous = new ConfigMisc();
	
	public static class ConfigWaterRegions {
		@Config.Name("Water Region Spawn Chance")
		@Config.Comment(
				"Percent chance of a region having water instead of lava at low altitudes." +
				"\nDefault: 40%")
		@Config.RangeDouble(min = 0, max = 100)
		@Config.RequiresWorldRestart
		public float waterRegionSpawnChance = 40;

		@Config.Comment(
				"Determines how large water regions are.\n" +
				"Default: Medium (recommended).")
		@Config.RequiresWorldRestart
		public RegionSize waterRegionSize = RegionSize.Medium;

		@Config.Name("Water Region Size Custom Value")
		@Config.Comment(
				"Custom value for water region size. Smaller value = larger regions. This value is very sensitive to change.\n" +
						"    ONLY WORKS IF Water Region Size IS Custom.\n" +
						"    Provided values:\n" +
						"        Small: 0.008\n" +
						"        Medium: 0.004\n" +
						"        Large: 0.0028\n" +
						"        ExtraLarge: 0.001\n" +
				"Default: 0.004")
		@Config.RangeDouble(min = 0, max = .05)
		@Config.RequiresWorldRestart
		public float waterRegionCustomSize = 0.004f;
	}
	
	public static class ConfigMisc {
		@Config.Name("Liquid Altitude")
		@Config.Comment(
				"Lava (or water in water regions) spawns at and below this y-coordinate.\n" +
				"Default: 10")
		@Config.RangeInt(min = 0, max = 255)
		@Config.RequiresWorldRestart
		public int liquidAltitude = 10;

		@Config.Name("Lava Block")
		@Config.Comment(
				"The block used for lava generation at and below the Liquid Altitude.\n" +
						"    Defaults to regular lava if an invalid block is given.\n" +
				"Default: minecraft:lava")
		@Config.RequiresWorldRestart
		public String lavaBlock = "minecraft:lava";

		@Config.Name("Water Block")
		@Config.Comment(
				"The block used for water generation in water caves/caverns at and below the Liquid Altitude.\n" +
						"    Defaults to regular water if an invalid block is given.\n" +
				"Default: minecraft:water")
		@Config.RequiresWorldRestart
		public String waterBlock = "minecraft:water";

		@Config.Name("Prevent Cascading Gravel")
		@Config.Comment(
				"Replace naturally generated floating gravel on the ocean floor with andesite.\n" +
						"    Can prevent lag due to cascading gravel falling into caverns under the ocean.\n" +
				"Default: true")
		@Config.RequiresWorldRestart
		public boolean replaceFloatingGravel = true;

		@Config.Name("Override Surface Detection")
		@Config.Comment(
				"Ignores surface detection for closing off caves and caverns, forcing them to spawn\n" +
						"    up until their max height. Useful for Nether-like dimensions with no real \"surface\".\n" +
				"Default: false")
		@Config.RequiresWorldRestart
		public boolean overrideSurfaceDetection = false;

		@Config.Name("Enable Flooded Underground")
		@Config.Comment(
				"Set to true to enable flooded underground in ocean biomes.\n" +
				"Default: true")
		public boolean enableFloodedUnderground = true;
	}
}
