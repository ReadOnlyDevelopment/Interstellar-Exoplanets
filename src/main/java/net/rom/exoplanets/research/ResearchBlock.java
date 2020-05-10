/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.research;

import com.google.gson.JsonObject;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraftforge.fml.common.Loader;
import net.rom.exoplanets.research.utils.JsonUtil;

public class ResearchBlock {
    IBlockState block;
    String blockName;
    int blockMeta;
    boolean hasMeta;
    String mod;

    public ResearchBlock(JsonObject object) {
        if (object.has("meta")) {
            setBlockMeta(JsonUtil.getInt(object, "meta"));
        }
        blockName = JsonUtil.getString(object, "id");
        mod = JsonUtil.getString(object, "mod", null);
    }

    public ResearchBlock(IBlockState block) {
        this.block = block;
    }

    public ResearchBlock(String blockName, String mod) {
        this.blockName = blockName;
        this.mod = mod;
    }

    public static ResearchBlock fromBlock(IBlockState block) {
        return new ResearchBlock(block);
    }

    public boolean isModded() {
        return mod != null && !mod.isEmpty();
    }

    public boolean isModPresent() {
        return Loader.isModLoaded(mod);
    }

    public boolean canBlockExist() {
        if (isModded()) {
            return isModPresent();
        }
        return true;
    }

    public IBlockState getBlockState() {
        if (isModded() || block == null) {
            if (hasMeta) {
                return Block.getBlockFromName(blockName).getStateFromMeta(blockMeta);
            }
            return Block.getBlockFromName(blockName).getDefaultState();

        } else {
            return block;
        }
    }

    public boolean isTheSame(IBlockState blockState) {
        if (hasMeta) {
            return getBlockState().equals(blockState);
        } else {
            return getBlockState().getBlock().equals(blockState.getBlock());
        }
    }

    public void setBlockMeta(int meta) {
        this.blockMeta = meta;
        this.hasMeta = true;
    }
}
