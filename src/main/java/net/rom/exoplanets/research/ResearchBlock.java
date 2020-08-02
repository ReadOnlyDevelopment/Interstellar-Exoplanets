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

package net.rom.exoplanets.research;

import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.Loader;
import net.rom.exoplanets.research.utils.JsonUtil;

public class ResearchBlock {
	IBlockState block;
	String      blockName;
	int         blockMeta;
	boolean     hasMeta;
	String      mod;

	public ResearchBlock(JsonObject object) {
		if (object.has("meta")) {
			setBlockMeta(JsonUtil.getInt(object, "meta"));
		}
		blockName = JsonUtil.getString(object, "id");
		mod       = JsonUtil.getString(object, "mod", null);
	}

	public ResearchBlock(IBlockState block) {
		this.block = block;
	}

	public ResearchBlock(String blockName, String mod) {
		this.blockName = blockName;
		this.mod       = mod;
	}

	public static ResearchBlock fromBlock (IBlockState block) {
		return new ResearchBlock(block);
	}

	public boolean isModded () {
		return mod != null && !mod.isEmpty();
	}

	public boolean isModPresent () {
		return Loader.isModLoaded(mod);
	}

	public boolean canBlockExist () {
		if (isModded()) {
			return isModPresent();
		}
		return true;
	}

	public IBlockState getBlockState () {
		if (isModded() || block == null) {
			if (hasMeta) {
				return Block.getBlockFromName(blockName).getStateFromMeta(blockMeta);
			}
			return Block.getBlockFromName(blockName).getDefaultState();

		}
		else {
			return block;
		}
	}

	public boolean isTheSame (IBlockState blockState) {
		if (hasMeta) {
			return getBlockState().equals(blockState);
		}
		else {
			return getBlockState().getBlock().equals(blockState.getBlock());
		}
	}

	public void setBlockMeta (int meta) {
		this.blockMeta = meta;
		this.hasMeta   = true;
	}
}
