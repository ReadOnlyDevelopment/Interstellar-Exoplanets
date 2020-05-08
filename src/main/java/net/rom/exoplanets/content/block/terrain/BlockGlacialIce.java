package net.rom.exoplanets.content.block.terrain;

import java.util.Random;

import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.EnumPushReaction;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.BlockRenderLayer;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.internal.block.BlockBreakable;
import net.rom.exoplanets.tabs.CreativeExoTabs;

public class BlockGlacialIce extends BlockBreakable implements ISortableBlock {

	public BlockGlacialIce() {
		super(Material.ICE);
		this.setDefaultSlipperiness(0.98F);
		this.setHardness(0.5F);
		this.setResistance(0.1F);
		this.setLightOpacity(3);
		this.setTickRandomly(true);
		this.setSoundType(SoundType.GLASS);
		this.setCreativeTab(CreativeExoTabs.TERRAIN_CREATIVE_TABS);

	}

	@Override
	@SideOnly(Side.CLIENT)
	public BlockRenderLayer getBlockLayer() {
		return BlockRenderLayer.TRANSLUCENT;
	}

	@Override
	public int quantityDropped(Random rand) {
		return 0;
	}

	@Override
	public EnumPushReaction getMobilityFlag(IBlockState state) {
		return EnumPushReaction.NORMAL;
	}

	@Override
	protected boolean isTranslucent() {
		return true;
	}

	@Override
	protected boolean renderSideWithState() {
		return false;
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

}
