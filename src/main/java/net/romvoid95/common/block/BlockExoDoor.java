package net.romvoid95.common.block;

import java.util.Random;

import javax.annotation.Nullable;

import net.minecraft.block.BlockDoor;
import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class BlockExoDoor extends BlockDoor {

	public BlockExoDoor () {
		super(Material.IRON);
		setSoundType(SoundType.METAL);
		setHardness(3.0F);
		setHarvestLevel("axe", 0);
	}
	@Override
	public SoundType getSoundType(IBlockState state, World world, BlockPos pos, @Nullable Entity entity) {
		return SoundType.WOOD;
	}

	@Override
	public SoundType getSoundType() {
		return SoundType.WOOD;
	}

	@Override
	public Item getItemDropped(IBlockState state, Random rand, int fortune) {
		return state.getValue(HALF) == BlockDoor.EnumDoorHalf.UPPER ? Items.AIR : getDoorItem().getItem();
	}

	@Override
	public ItemStack getItem(World worldIn, BlockPos pos, IBlockState state) {
		return getDoorItem();
	}

	private ItemStack getDoorItem() {
		return new ItemStack(Item.getItemFromBlock(this));
	}
}
