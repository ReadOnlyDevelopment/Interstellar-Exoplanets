package net.romvoid95.common.block.ore;

import java.util.Random;

import net.minecraft.block.Block;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.item.Item;
import net.minecraft.util.BlockRenderLayer;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.DimensionType;
import net.minecraft.world.IBlockAccess;
import net.romvoid95.api.world.OreHelper;
import net.romvoid95.api.world.OreType;

public class BlockDynOre extends Block {

	private final OreType oreType;

	public BlockDynOre(OreType type) {
		super(Material.ROCK);
		this.setHardness(3.0F);
		this.setResistance(5.0F);
		this.setSoundType(SoundType.STONE);
		this.oreType = type;
	}

	@Override
	public MapColor getMapColor (IBlockState state, IBlockAccess world, BlockPos pos) {
		return OreHelper.getDimensionOreBase(DimensionType.getById(world.getWorldType().getId()))
				.getMapColor(world, pos);
	}

	@Override
	public boolean canRenderInLayer (IBlockState state, BlockRenderLayer layer) {
		return layer == BlockRenderLayer.CUTOUT || layer == BlockRenderLayer.SOLID;
	}

	@Override
	public Item getItemDropped (IBlockState state, Random rand, int fortune) {

		return Item.getItemFromBlock(this.oreType.getBlock());

	}
}
