package com.readonlydev.core.registries;

import java.lang.reflect.Field;
import java.util.LinkedHashSet;
import java.util.Locale;
import java.util.Set;

import com.readonlydev.common.block.BlockTerrain;
import com.readonlydev.common.block.generic.BlockAlloy;
import com.readonlydev.common.block.generic.BlockMetal;
import com.readonlydev.common.block.ore.BlockCompressedDiamond;
import com.readonlydev.common.block.ore.BlockOreMetal;
import com.readonlydev.common.block.stairs.BlockRoofSlanted;
import com.readonlydev.common.block.terrain.BlockCrust;
import com.readonlydev.common.block.terrain.BlockExoDirt;
import com.readonlydev.common.block.terrain.BlockExoGrass;
import com.readonlydev.common.block.terrain.BlockGravel;
import com.readonlydev.common.block.terrain.BlockSediment;
import com.readonlydev.common.block.terrain.BlockCrust.RockType;
import com.readonlydev.lib.registry.InterstellarRegistry;
import com.readonlydev.lib.registry.impl.BlockRegistry;

import net.minecraft.block.Block;

public class ExoplanetBlocks extends BlockRegistry {

	public static final Block METALORE = new BlockOreMetal("metalore", true);
	public static final Block METALBLOCK = new BlockMetal();
	public static final Block ALLOYBLOCK = new BlockAlloy();

	public static final Block ROOF_SLANTED = new BlockRoofSlanted();

	public static final Block HOT_GROUND_2 = new BlockTerrain();
	public static final Block HOT_GROUND_3 = new BlockTerrain();

	public static final Block TRAP1B_STONE_1 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block TRAP1B_STONE_2 = new BlockCrust(RockType.IGNEOUS);

	public static final Block TRAP1C_TOP = new BlockTerrain();
	public static final Block TRAP1C_DIRT_1 = new BlockTerrain();
	public static final Block TRAP1C_DIRT_2 = new BlockTerrain();

	public static final Block TRAP1D_OCEANFLOOR = new BlockCrust(RockType.IGNEOUS);
	public static final Block TRAP1D_STONE_1 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block TRAP1D_STONE_2 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block TRAP1D_SOFTSTONE = new BlockCrust(RockType.SEDIMENTARY);
	public static final Block TRAP1D_DIAMOND = new BlockCompressedDiamond();
	public static final Block TRAP1D_WETGRASS = new BlockExoGrass();
	public static final Block TRAP1D_WETDIRT = new BlockExoDirt();

	public static final Block TRAP1E_GRASS = new BlockExoGrass();
	public static final Block TRAP1E_DIRT = new BlockExoDirt();
	public static final Block TRAP1E_COBBLESTONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block TRAP1E_STONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block TRAP1E_ORE = new BlockOreMetal("trap1e_ore", false);

	public static final Block KEPLERB_STONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block KEPLERB_CLIFF_STONE = new BlockCrust(RockType.METAMORPHIC);
	
	public static final Block KEPLERC_GRASS = new BlockExoGrass();
	public static final Block KEPLERC_DIRT = new BlockExoDirt();
	public static final Block KEPLERC_VARIANT1 = new BlockExoDirt();
	public static final Block KEPLERC_VARIANT2 = new BlockExoDirt();
	public static final Block KEPLERC_STONE = new BlockCrust(RockType.IGNEOUS);
	public static final Block KEPLERC_COBBLESTONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block KEPLERC_GRAVEL = new BlockSediment();

	public static final Block WOLFC_COBBLESTONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_CLAY = new BlockSediment();
	public static final Block WOLFC_IGNEOUS_STONE = new BlockCrust(RockType.IGNEOUS);
	public static final Block WOLFC_STONE_CLAY = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_CLAY_ORANGE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_CLAY_SILVER = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_CLAY_BROWN = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_CLAY_GRAY = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_CLAY_WHITE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFC_STONE_QUARTZ = new BlockCrust(RockType.IGNEOUS);
	public static final Block WOLFC_HARDENED_SAND = new BlockCrust(RockType.SEDIMENTARY);

	public static final Block WOLFB_COBBLESTONE = new BlockCrust(RockType.METAMORPHIC);
	public static final Block WOLFB_STONE = new BlockCrust(RockType.METAMORPHIC);

	public static final Block YZB_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZB_DARK_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZB_GRAVEL = new BlockGravel();
	public static final Block YZB_IGNEOUS = new BlockCrust(RockType.IGNEOUS);
	public static final Block YZB_METAMORPHIC = new BlockCrust(RockType.METAMORPHIC);
	public static final Block YZB_SEDIMENTARYROCK = new BlockCrust(RockType.SEDIMENTARY);

	public static final Block YZC_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZC_DARK_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZC_GRAVEL = new BlockGravel();
	public static final Block YZC_IGNEOUS = new BlockCrust(RockType.IGNEOUS);
	public static final Block YZC_METAMORPHIC = new BlockCrust(RockType.METAMORPHIC);
	public static final Block YZC_SEDIMENTARYROCK = new BlockCrust(RockType.SEDIMENTARY);

	public static final Block YZD_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZD_DARK_LOOSE_SEDIMENT = new BlockSediment();
	public static final Block YZD_GRAVEL = new BlockGravel();
	public static final Block YZD_IGNEOUS = new BlockCrust(RockType.IGNEOUS);
	public static final Block YZD_METAMORPHIC = new BlockCrust(RockType.METAMORPHIC);
	public static final Block YZD_SEDIMENTARYROCK = new BlockCrust(RockType.SEDIMENTARY);
	public static final Block YZD_MNT1 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block YZD_MNT2 = new BlockCrust(RockType.METAMORPHIC);
	public static final Block YZD_STONE = new BlockCrust(RockType.METAMORPHIC);
	
	public static final Set<Block> BLOCKS = new LinkedHashSet<>();

	@Override
	public void register(InterstellarRegistry registry) {
		try {
			for (Field field : ExoplanetBlocks.class.getDeclaredFields()) {
				Object obj = field.get(null);
				if (obj instanceof Block) {
					Block block = (Block) obj;
					String name = field.getName().toLowerCase(Locale.ROOT);
					registry.register(block, name);
					BLOCKS.add(block);
				}
			}
		} catch (IllegalAccessException e) {
			throw new RuntimeException(e);
		}
	}
}
