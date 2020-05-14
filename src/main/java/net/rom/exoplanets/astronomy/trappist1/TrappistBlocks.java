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

package net.rom.exoplanets.astronomy.trappist1;

import net.minecraft.item.ItemBlock;
import net.rom.exoplanets.content.block.BlockTerrain;
import net.rom.exoplanets.internal.StellarRegistry;

public class TrappistBlocks {
	
	public static void registerAll(StellarRegistry registry) {
		
		registry.registerBlock(SharedTerrain.TE1_TOP, "t1e_top", new ItemBlock(SharedTerrain.TE1_TOP));
		registry.registerBlock(SharedTerrain.TE1_TOP_ALT, "t1e_top_alt", new ItemBlock(SharedTerrain.TE1_TOP_ALT));
		registry.registerBlock(SharedTerrain.HOT_GROUND_1, "dark_hot", new ItemBlock(SharedTerrain.HOT_GROUND_1));
		registry.registerBlock(SharedTerrain.HOT_GROUND_2, "solid_hot", new ItemBlock(SharedTerrain.HOT_GROUND_2));
		registry.registerBlock(SharedTerrain.HOT_GROUND_3, "bright_solid_hot", new ItemBlock(SharedTerrain.HOT_GROUND_3));
		
	}
	
	public static class SharedTerrain {
		
		public static final BlockTerrain TE1_TOP = new BlockTerrain();
		public static final BlockTerrain TE1_TOP_ALT = new BlockTerrain();
		public static final BlockTerrain HOT_GROUND_1 = new BlockTerrain();
		public static final BlockTerrain HOT_GROUND_2 = new BlockTerrain();
		public static final BlockTerrain HOT_GROUND_3 = new BlockTerrain();
		
	}
	
	

}
