package net.romvoid95.core;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.romvoid95.common.astronomy.trappist1.TrappistBlocks;
import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;

public class ExoBlock {

	public static IBlockState get (Block block) {
		return block.getDefaultState();
	}

	public static IBlockState HOT_GROUND_1 = TrappistBlocks.SharedTerrain.HOT_GROUND_1.getDefaultState();
	public static IBlockState HOT_GROUND_2 = TrappistBlocks.SharedTerrain.HOT_GROUND_2.getDefaultState();
	public static IBlockState HOT_GROUND_3 = TrappistBlocks.SharedTerrain.HOT_GROUND_3.getDefaultState();

	public static IBlockState TRAP1C_TOP    = TrappistBlocks.TrappistC.TRAP1C_TOP.getDefaultState();
	public static IBlockState TRAP1C_DIRT_1 = TrappistBlocks.TrappistC.TRAP1C_DIRT_1.getDefaultState();
	public static IBlockState TRAP1C_DIRT_2 = TrappistBlocks.TrappistC.TRAP1C_DIRT_2.getDefaultState();

	public static IBlockState TRAP1D_OCEANFLOOR = TrappistBlocks.TrappistD.TRAP1D_OCEANFLOOR.getDefaultState();
	public static IBlockState TRAP1D_STONE_1    = TrappistBlocks.TrappistD.TRAP1D_STONE_1.getDefaultState();
	public static IBlockState TRAP1D_STONE_2    = TrappistBlocks.TrappistD.TRAP1D_STONE_2.getDefaultState();
	public static IBlockState TRAP1D_SOFTSTONE  = TrappistBlocks.TrappistD.TRAP1D_SOFTSTONE.getDefaultState();
	public static IBlockState TRAP1D_DIAMOND    = TrappistBlocks.TrappistD.TRAP1D_DIAMOND.getDefaultState();
	public static IBlockState TRAP1D_WETGRASS   = TrappistBlocks.TrappistD.TRAP1D_WETGRASS.getDefaultState();
	public static IBlockState TRAP1D_WETDIRT    = TrappistBlocks.TrappistD.TRAP1D_WETDIRT.getDefaultState();

	public static IBlockState TRAP1E_GRASS       = TrappistBlocks.TrappistE.TRAP1E_GRASS.getDefaultState();
	public static IBlockState TRAP1E_DIRT        = TrappistBlocks.TrappistE.TRAP1E_DIRT.getDefaultState();
	public static IBlockState TRAP1E_COBBLESTONE = TrappistBlocks.TrappistE.TRAP1E_COBBLESTONE.getDefaultState();
	public static IBlockState TRAP1E_STONE       = TrappistBlocks.TrappistE.TRAP1E_STONE.getDefaultState();
	public static IBlockState TRAP1E_ORE         = TrappistBlocks.TrappistE.TRAP1E_ORE.getDefaultState();

	public static IBlockState YZB_LOOSE_SEDIMENT      = YzCetiBlocks.B.YZB_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZB_DARK_LOOSE_SEDIMENT = YzCetiBlocks.B.YZB_DARK_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZB_GRAVEL              = YzCetiBlocks.B.YZB_GRAVEL.getDefaultState();
	public static IBlockState YZB_IGNEOUS             = YzCetiBlocks.B.YZB_IGNEOUS.getDefaultState();
	public static IBlockState YZB_METAMORPHIC         = YzCetiBlocks.B.YZB_METAMORPHIC.getDefaultState();
	public static IBlockState YZB_SEDIMENTARYROCK     = YzCetiBlocks.B.YZB_SEDIMENTARYROCK.getDefaultState();

	public static IBlockState YZC_LOOSE_SEDIMENT      = YzCetiBlocks.C.YZC_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZC_DARK_LOOSE_SEDIMENT = YzCetiBlocks.C.YZC_DARK_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZC_GRAVEL              = YzCetiBlocks.C.YZC_GRAVEL.getDefaultState();
	public static IBlockState YZC_IGNEOUS             = YzCetiBlocks.C.YZC_IGNEOUS.getDefaultState();
	public static IBlockState YZC_METAMORPHIC         = YzCetiBlocks.C.YZC_METAMORPHIC.getDefaultState();
	public static IBlockState YZC_SEDIMENTARYROCK     = YzCetiBlocks.C.YZC_SEDIMENTARYROCK.getDefaultState();

	public static IBlockState YZD_LOOSE_SEDIMENT      = YzCetiBlocks.D.YZD_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZD_DARK_LOOSE_SEDIMENT = YzCetiBlocks.D.YZD_DARK_LOOSE_SEDIMENT.getDefaultState();
	public static IBlockState YZD_GRAVEL              = YzCetiBlocks.D.YZD_GRAVEL.getDefaultState();
	public static IBlockState YZD_IGNEOUS             = YzCetiBlocks.D.YZD_IGNEOUS.getDefaultState();
	public static IBlockState YZD_METAMORPHIC         = YzCetiBlocks.D.YZD_METAMORPHIC.getDefaultState();
	public static IBlockState YZD_SEDIMENTARYROCK     = YzCetiBlocks.D.YZD_SEDIMENTARYROCK.getDefaultState();

}
