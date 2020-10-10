package net.romvoid95.common.block.terrain.decorations;

import net.minecraft.block.BlockTallGrass;
import net.minecraft.block.SoundType;
import net.minecraft.block.state.IBlockState;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.item.ItemStack;
import net.minecraft.util.NonNullList;
import net.minecraft.world.WorldProvider;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.api.space.prefab.WorldProviderWE_ExoPlanet;
import net.romvoid95.client.CreativeExoTabs;

public class BlockExoTallGrass extends BlockTallGrass {

	private WorldProvider provider;

	public BlockExoTallGrass(WorldProvider provider) {
		super();
		setCreativeTab(CreativeExoTabs.TERRAIN_TAB);
		setSoundType(SoundType.PLANT);
		useNeighborBrightness = true;
		this.provider = provider;
	}

	@Override
	public boolean canSustainBush(IBlockState state) {
		if(((WorldProviderWE_ExoPlanet)provider).getPlanetGrassBlock() != null) {
			return (state.getBlock() == ((WorldProviderWE_ExoPlanet)provider).getPlanetGrassBlock().getDefaultState());
		} else {
			return false;
		}
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getSubBlocks(CreativeTabs itemIn, NonNullList<ItemStack> items) {
		items.add(new ItemStack(this, 1, 0));
	}

	@Override
	public int damageDropped(IBlockState state) {
		return 0;
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return getDefaultState();
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return 0;
	}
}
