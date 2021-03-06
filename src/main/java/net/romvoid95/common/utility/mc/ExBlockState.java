/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
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
package net.romvoid95.common.utility.mc;

import java.lang.ref.WeakReference;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.properties.IProperty;
import net.minecraft.block.properties.PropertyDirection;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

import net.minecraftforge.common.util.Constants.NBT;

import net.romvoid95.common.utility.logic.EnumFaceUtil;

public class ExBlockState {
	private static BlockStateFunction toBlockState = new BlockStateFunction();
	private static BlockPredicate     blockFilter  = new BlockPredicate();

	protected BlockPos    pos;
	protected Block       block;
	protected IBlockState state;

	public ExBlockState(BlockPos pos, IBlockState state) {
		this.pos   = pos;
		this.block = state.getBlock();
		this.state = state;
	}

	public ExBlockState(BlockPos pos, Block block) {
		this.pos   = pos;
		this.block = block;
		this.state = block.getDefaultState();
	}

	public ExBlockState(IBlockState state) {
		this.block = state.getBlock();
		this.state = state;
	}

	public ExBlockState(Block block) {
		this.block = block;
		this.state = block.getDefaultState();
	}

	public ExBlockState(IBlockAccess world, BlockPos pos) {
		this.pos   = pos;
		this.state = world.getBlockState(pos);
		this.block = state.getBlock();
		this.state = state.getActualState(world, pos);
	}

	public ExBlockState(IBlockAccess world, long coord) {
		this(world, BlockPos.fromLong(coord));
	}

	public ExBlockState(BlockPos pos, ExBlockState state) {
		this(pos, state.getBlockState());
	}

	public BlockPos getPos () {
		return pos;
	}

	public Block getBlock () {
		return block;
	}

	public IBlockState getBlockState () {
		return state;
	}

	public int getX () {
		return pos.getX();
	}

	public int getY () {
		return pos.getY();
	}

	public int getZ () {
		return pos.getZ();
	}

	public boolean isAir () {
		return getBlockState().getMaterial() == Material.AIR;
	}

	public ExBlockState offset (BlockPos pos) {
		return new ExBlockState(this.pos.add(pos), this);
	}

	public ExBlockState rotate (int count) {
		IBlockState newState = state;
		for (IProperty<?> prop : state.getProperties().keySet()) {
			if (prop instanceof PropertyDirection) {
				EnumFacing facing = EnumFaceUtil.rotateFacing((EnumFacing) state.getValue(prop), 4 - count);
				newState = newState.withProperty((PropertyDirection) prop, facing);
			}
		}

		return new ExBlockState(BlockPosUtil.rotate(pos, count), newState);
	}

	public void placeBlock (World world) {
		world.setBlockState(pos, state);
	}

	public void placeBlock (World world, int flag) {
		world.setBlockState(pos, state, flag);
	}

	public void breakBlock (World world, int flag) {
		world.setBlockState(pos, Blocks.AIR.getDefaultState(), flag);
	}

	public boolean matchesWorld (IBlockAccess world) {
		ExBlockState mstate = new ExBlockState(world, pos);
		return mstate.getBlock() == getBlock()
				&& getBlock().getMetaFromState(mstate.getBlockState()) == getBlock().getMetaFromState(getBlockState());
	}

	public static Iterable<ExBlockState> getAllInBox (IBlockAccess world, BlockPos from, BlockPos to, Block block, boolean skipAir) {
		FluentIterable<ExBlockState> it = FluentIterable.from(BlockPos.getAllInBox(from, to))
				.transform(toBlockState.set(world));
		if (block != null || skipAir)
			it.filter(blockFilter.set(block, skipAir));

		return it;
	}

	public static IBlockState fromNBT (NBTTagCompound nbt) {
		return fromNBT(nbt, "block", "metadata");
	}

	@SuppressWarnings("deprecation")
	public static IBlockState fromNBT (NBTTagCompound nbt, String blockName, String metadataName) {
		if (nbt == null)
			return null;

		Block block = null;
		if (nbt.hasKey(blockName, NBT.TAG_INT))
			block = Block.getBlockById(nbt.getInteger(blockName));
		else if (nbt.hasKey(blockName))
			block = Block.getBlockFromName(nbt.getString(blockName));

		if (block == null)
			return null;

		int metadata = nbt.getInteger(metadataName);
		return block.getStateFromMeta(metadata);
	}

	public static NBTTagCompound toNBT (NBTTagCompound nbt, IBlockState state) {
		return toNBT(nbt, state, "block", "metadata");
	}

	public static NBTTagCompound toNBT (NBTTagCompound nbt, IBlockState state, String blockName, String metadataName) {
		if (state == null)
			return nbt;

		nbt.setString(blockName, Block.REGISTRY.getNameForObject(state.getBlock()).toString());
		nbt.setInteger(metadataName, state.getBlock().getMetaFromState(state));
		return nbt;
	}

	@Override
	public boolean equals (Object obj) {
		if (!(obj instanceof ExBlockState))
			return false;

		ExBlockState bs = (ExBlockState) obj;
		return pos.equals(bs.pos) && block == bs.block && state == bs.state;
	}

	@Override
	public String toString () {
		return "[" + pos + "] " + state;
	}

	public static class BlockStateFunction implements Function<BlockPos, ExBlockState> {
		public WeakReference<IBlockAccess> world;

		public BlockStateFunction set (IBlockAccess world) {
			this.world = new WeakReference<>(world);
			return this;
		}

		@Override
		public ExBlockState apply (BlockPos pos) {
			return new ExBlockState(world.get(), pos);
		}
	}

	public static class BlockPredicate implements Predicate<ExBlockState> {
		public Block   block;
		public boolean skipAir;

		public BlockPredicate set (Block block, boolean skipAir) {
			this.block   = block;
			this.skipAir = skipAir;
			return this;
		}

		@Override
		public boolean apply (ExBlockState state) {
			if (block == null)
				return state.getBlock() != Blocks.AIR;
			else
				return state.getBlock() == block;
		}

	}
}
