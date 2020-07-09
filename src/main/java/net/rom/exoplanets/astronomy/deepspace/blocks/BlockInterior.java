/**
 * Exoplanets
 * Copyright (C) 2020
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.rom.exoplanets.astronomy.deepspace.blocks;

import java.util.Locale;

import micdoodle8.mods.galacticraft.api.block.IPartialSealableBlock;
import micdoodle8.mods.galacticraft.core.blocks.ISortableBlock;
import micdoodle8.mods.galacticraft.core.util.EnumSortCategoryBlock;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.client.model.ModelLoader;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.content.block.decoration.BlockMetalDecoration;
import net.rom.exoplanets.internal.block.BlockMetaSubtypes;
import net.rom.exoplanets.internal.inerf.ICustomModel;
import net.rom.exoplanets.internal.inerf.item.ItemBlockMetaSubtypes;
import net.rom.exoplanets.util.CreativeExoTabs;

public class BlockInterior extends BlockMetaSubtypes implements ICustomModel, ISortableBlock, IPartialSealableBlock {
	public static final PropertyEnum<EnumBlockInterior> VARIANT = PropertyEnum.create("type", EnumBlockInterior.class);

	public enum EnumBlockInterior implements IDeepSpace {
		WHITE(0, "interior"), VARIANT_1(1, "interior_1"), OFF_WHITE(2, "interior_2"), GREY_HANDLE(3, "interior_3"),
		GREY(4, "interior_4"), VARIANT_5(5, "interior_5"), DARK_GREY(6, "interior_6");

		public final int meta;
		public final String name;

		EnumBlockInterior(int meta, String name) {
			this.meta = meta;
			this.name = name;
		}

		@Override
		public int getMeta() {
			return meta;
		}

		@Override
		public String getName() {
			return name.toLowerCase(Locale.ROOT);
		}

		@Override
		public ItemStack getBlock() {
			return new ItemStack(DeepSpaceBlocks.interior, 1, meta);
		}

		public IBlockState getBlockByState() {
			return DeepSpaceBlocks.interior.getDefaultState().withProperty(VARIANT, this);
		}
	}

	public BlockInterior() {
		super(Material.ROCK, EnumBlockInterior.values().length);
		blockHardness = 2.2F;
		blockResistance = 2.5F;
		setDefaultState(this.blockState.getBaseState().withProperty(VARIANT, EnumBlockInterior.WHITE));
		setCreativeTab(CreativeExoTabs.DECO_CREATIVE_TABS);
	}

	public ItemStack getStack(EnumBlockInterior type, int count) {
		return new ItemStack(this, count, type.ordinal());
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {

		meta = MathHelper.clamp(meta, 0, EnumBlockInterior.values().length - 1);
		return this.getDefaultState().withProperty(VARIANT, EnumBlockInterior.values()[meta]);
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return state.getValue(VARIANT).ordinal();
	}

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public int damageDropped(IBlockState state) {
		return getMetaFromState(state);
	}

	@Override
	public ItemStack getPickBlock(IBlockState state, RayTraceResult target, World world, BlockPos pos,
			EntityPlayer player) {
		return new ItemStack(Item.getItemFromBlock(this), 1, this.getMetaFromState(state));
	}

	@Override
	public void registerModels() {
		Item item = Item.getItemFromBlock(this);
		String fullName = ExoInfo.RESOURCE_PREFIX + "basicspace";
		for (EnumBlockInterior type : EnumBlockInterior.values()) {
			ModelResourceLocation model = new ModelResourceLocation(fullName, "type=" + type.getName());
			ModelLoader.setCustomModelResourceLocation(item, type.ordinal(), model);
		}
	}

	@Override
	public boolean isSealed(World world, BlockPos pos, EnumFacing direction) {
		return true;
	}

	@Override
	public boolean isOpaqueCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isFullCube(IBlockState state) {
		return true;
	}

	@Override
	public boolean isSideSolid(IBlockState base_state, IBlockAccess world, BlockPos pos, EnumFacing side) {
		return true;
	}

	public static class ItemBlock extends ItemBlockMetaSubtypes {
		public ItemBlock(BlockInterior block) {
			super(block);
		}
	}

	@Override
	public EnumSortCategoryBlock getCategory(int meta) {
		return EnumSortCategoryBlock.DECORATION;
	}

}
