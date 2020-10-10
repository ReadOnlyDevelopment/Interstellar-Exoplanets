package net.romvoid95.common.config.gen.cave;

import net.minecraftforge.common.config.Config;

import net.romvoid95.common.world.cave.enums.RegionSize;

public class ConfigCaves {
	@Config.Name("Cublic Caves")
	@Config.Comment("Settings used in the generation of type 1 caves, which are more worm-like.")
	public ConfigCubicCave cubicCave = new ConfigCubicCave();

	@Config.Name("Simplex Caves")
	@Config.Comment("Settings used in the generation of type 2 caves, which tend to be more open and spacious.")
	public ConfigSimplexCave simplexCave = new ConfigSimplexCave();

	@Config.Name("Surface Caves")
	@Config.Comment("Settings used in the generation of vanilla-like caves near the surface.")
	public ConfigSurfaceCave surfaceCave = new ConfigSurfaceCave();

	@Config.Name("Vanilla Caves")
	@Config.Comment("Settings controlling vanilla Minecraft cave generation.")
	public ConfigVanillaCave vanillaCave = new ConfigVanillaCave();

	@Config.Name("Cave Spawn Chance")
	@Config.Comment(
			"Percent chance of caves spawning in a given region.\n" +
			"Default: caves spawn in 100% of regions.")
	@Config.RangeDouble(min = 0, max = 100)
	@Config.RequiresWorldRestart
	public float caveSpawnChance = 100;

	@Config.Name("Cave Region Size")
	@Config.Comment(
			"Determines how large cave regions are.\n" +
					"    Controls how long a cave system of a certain cave type extends before intersecting with a cave system of another type.\n" +
					"    Larger = more cave interconnectivity for a given area, but less variation.\n" +
			"Default: Small (recommended).")
	@Config.RequiresWorldRestart
	public RegionSize caveRegionSize = RegionSize.Small;

	@Config.Name("Cave Region Size Custom Value")
	@Config.Comment(
			"Custom value for cave region size. Smaller value = larger regions. This value is very sensitive to change.\n" +
					"    ONLY WORKS IF Cave Region Size IS Custom.\n" +
					"    Provided values:\n" +
					"        Small: 0.008\n" +
					"        Medium: 0.005\n" +
					"        Large: 0.0032\n" +
					"        ExtraLarge: 0.001\n" +
			"Default: 0.008")
	@Config.RangeDouble(min = 0, max = .05)
	@Config.RequiresWorldRestart
	public float customRegionSize = 0.008f;
	
	public static class ConfigSurfaceCave {
	    @Config.Name("Enable Surface Caves")
	    @Config.Comment(
	        "Set to true to enable vanilla-like caves which provide nice, natural-looking openings at the surface.\n" +
	        "Default: true")
	    public boolean enableSurfaceCaves = true;

	    @Config.Name("Surface Cave Minimum Altitude")
	    @Config.Comment(
	        "The minimum y-coordinate at which surface caves can generate.\n" +
	            "Default: 40")
	    @Config.RangeInt(min = 0, max = 255)
	    @Config.RequiresWorldRestart
	    public int caveBottom = 40;

	    @Config.Name("Surface Cave Maximum Altitude")
	    @Config.Comment(
	        "The maximum y-coordinate at which surface caves can generate.\n" +
	            "Default: 128")
	    @Config.RangeInt(min = 0, max = 255)
	    @Config.RequiresWorldRestart
	    public int caveTop = 128;

	    @Config.Name("Surface Cave Density")
	    @Config.Comment(
	        "The density of surface caves. Higher = more caves, closer together. \n" +
	            "Default: 17")
	    @Config.RangeInt(min = 0, max = 100)
	    @Config.RequiresWorldRestart
	    public int caveDensity = 17;
	}
	
	public static class ConfigVanillaCave {
	    @Config.Name("Vanilla Cave Minimum Altitude")
	    @Config.Comment(
	        "The minimum y-coordinate at which vanilla caves can generate.\n" +
	         "Default: 8")
	    @Config.RangeInt(min = 0, max = 255)
	    @Config.RequiresWorldRestart
	    public int caveBottom = 8;

	    @Config.Name("Vanilla Cave Maximum Altitude")
	    @Config.Comment(
	        "The maximum y-coordinate at which vanilla caves can generate.\n" +
	         "Default: 128")
	    @Config.RangeInt(min = 0, max = 255)
	    @Config.RequiresWorldRestart
	    public int caveTop = 128;

	    @Config.Name("Vanilla Cave Density")
	    @Config.Comment(
	        "The density of vanilla caves. Higher = more caves, closer together. \n" +
	            "Default: 14 (value used in vanilla)")
	    @Config.RangeInt(min = 0, max = 100)
	    @Config.RequiresWorldRestart
	    public int caveDensity = 14;

	    @Config.Name("Vanilla Cave Priority")
	    @Config.Comment(
	        "Determines how frequently vanilla caves spawn. 0 = will not spawn at all.\n" +
	            "Default: 0")
	    @Config.RangeInt(min = 0, max = 10)
	    @Config.RequiresWorldRestart
	    public int cavePriority = 0;
	}
}
