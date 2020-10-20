package net.romvoid95.common.config.biomes;

import java.io.File;

import net.minecraftforge.common.config.Configuration;

import net.romvoid95.common.config.biomes.property.*;

public class BiomeConfig extends Config {

    /* GLOBAL CONFIGS */
    public final ConfigPropertyString SURFACE_TOP_BLOCK;
    public final ConfigPropertyString SURFACE_FILLER_BLOCK;
    public final ConfigPropertyString SURFACE_CLIFF_STONE_BLOCK;
    public final ConfigPropertyString SURFACE_CLIFF_COBBLE_BLOCK;
    public final ConfigPropertyBoolean SURFACE_BLEED_IN;
    public final ConfigPropertyBoolean SURFACE_BLEED_OUT;
    public final ConfigPropertyFloat SURFACE_WATER_LAKE_MULT;
    public final ConfigPropertyFloat SURFACE_LAVA_LAKE_MULT;

    /* OPTIONAL CONFIGS */
    public final ConfigPropertyBoolean ALLOW_LOGS;
    public final ConfigPropertyString SURFACE_MIX_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_FILLER_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_2_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_3_BLOCK;
    public final ConfigPropertyString SURFACE_MIX_4_BLOCK;

    public BiomeConfig(File file) {
        super(file);

        /* GLOBAL CONFIGS */

        SURFACE_TOP_BLOCK = new ConfigPropertyString(
            "Top Block ID",
            "Surfaces.Top Block",
            "Set this to change this biome's top block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_TOP_BLOCK);

        SURFACE_FILLER_BLOCK = new ConfigPropertyString(
            "Filler Block ID",
            "Surfaces.Filler Block",
            "Set this to change this biome's filler block (the block underneath the top block)." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_FILLER_BLOCK);

        SURFACE_CLIFF_STONE_BLOCK = new ConfigPropertyString(
            "Cliff Stone Block ID",
            "Surfaces.Cliff Stone Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE + "Set this to change this biome's cliff stone block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_CLIFF_STONE_BLOCK);

        SURFACE_CLIFF_COBBLE_BLOCK = new ConfigPropertyString(
            "Cliff Cobble Block ID",
            "Surfaces.Cliff Cobble Block",
            "Cliff blocks are the blocks that are used on the cliffs of mountains (usually a blend of stone & cobblestone)."
                + Configuration.NEW_LINE + "Set this to change this biome's cliff cobble block." + BLOCKSTATE_HELP,
            ""
        );
        this.addProperty(SURFACE_CLIFF_COBBLE_BLOCK);

        SURFACE_BLEED_IN = new ConfigPropertyBoolean(
            "Surface Bleed In",
            "Surfaces.Surface Bleed",
            "Set to false if other biomes shouldn't bleed into this one",
            false
        );
        this.addProperty(SURFACE_BLEED_IN);

        SURFACE_BLEED_OUT = new ConfigPropertyBoolean(
            "Surface Bleed Out",
            "Surfaces.Surface Bleed",
            "Set to false if this biome shouldn't bleed into other biomes",
            false
        );
        this.addProperty(SURFACE_BLEED_OUT);

        SURFACE_WATER_LAKE_MULT = new ConfigPropertyFloat(
                "Surface Water Lake Multiplier",
                "Surfaces.Lakes",
                "This setting allows you to increase/decrease the number of water lakes that generate on the surface of this biome."
                        + Configuration.NEW_LINE + "1.0 = Default amount; 2.0 = Twice as many water lakes; 0.5 = half as many water lakes; 0 = No water lakes",
                0.4f, 0f, 10.0f
        );
        this.addProperty(SURFACE_WATER_LAKE_MULT);

        SURFACE_LAVA_LAKE_MULT = new ConfigPropertyFloat(
                "Surface Lava Lake Multiplier",
                "Surfaces.Lakes",
                "This setting allows you to increase/decrease the number of lava lakes that generate on the surface of this biome."
                        + Configuration.NEW_LINE + "1.0 = Default amount; 2.0 = Twice as many lava lakes; 0.5 = half as many lava lakes; 0 = No lava lakes",
                0f, 0f, 10.0f
        );
        this.addProperty(SURFACE_LAVA_LAKE_MULT);

        /* OPTIONAL CONFIGS - These properties get 'added' by the individual biomes when relevant, so don't 'add' them here.*/

        SURFACE_MIX_BLOCK = new ConfigPropertyString(
            "Mix Block ID",
            "Surfaces.Mix Top Block",
            "Set this to change this biome's mix block" + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_FILLER_BLOCK = new ConfigPropertyString(
            "Mix Filler Block ID",
            "Surfaces.Mix Filler Block",
            "Set this to change this biome's mix filler block (the block underneath the mix block)." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_2_BLOCK = new ConfigPropertyString(
            "Mix 2 Block ID",
            "Surfaces.Mix 2 Top Block",
            "Set this to change this biome's 2nd mix block." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_3_BLOCK = new ConfigPropertyString(
            "Mix 3 Block ID",
            "Surfaces.Mix 3 Top Block",
            "Set this to change this biome's 3rd mix block." + BLOCKSTATE_HELP,
            ""
        );

        SURFACE_MIX_4_BLOCK = new ConfigPropertyString(
            "Mix 4 Block ID",
            "Surfaces.Mix 4 Top Block",
            "Set this to change this biome's 4th mix block." + BLOCKSTATE_HELP,
            ""
        );

        ALLOW_LOGS = new ConfigPropertyBoolean("Allow Logs", "Decorations.Logs", "", true);
    }
}
