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

import net.rom.exoplanets.astronomy.trappist1.e.blocks.Trappist1E_Dirt;
import net.rom.exoplanets.astronomy.trappist1.e.blocks.Trappist1E_Grass;
import net.rom.exoplanets.content.block.BlockTerrain;
import net.rom.exoplanets.content.block.terrain.BlockMetamorphicRock;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.internal.StellarRegistry;

public class TrappistBlocks {
	
	public static void registerAll(StellarRegistry registry) {
		
		registry.generateJsonFiles(false);
		
		ExoBlocks.register(SharedTerrain.HOT_GROUND_1, "trap1_dark_hot", "trappist1");
		ExoBlocks.register(SharedTerrain.HOT_GROUND_2, "trap1_solid_hot", "trappist1");
		ExoBlocks.register(SharedTerrain.HOT_GROUND_3, "trap1_bright_solid_hot", "trappist1");
		
		ExoBlocks.register(TrappistC.T1C_TOP, "trap1c_top", "trappist1");
		ExoBlocks.register(TrappistC.T1C_Dirt_1, "trap1c_dirt", "trappist1");
		ExoBlocks.register(TrappistC.T1C_Dirt_2, "trap1c_dirt_1", "trappist1");
		
		ExoBlocks.register(TrappistE.trap1e_grass, "trap1e_grass", "trappist1");
		ExoBlocks.register(TrappistE.trap1e_dirt, "trap1e_dirt", "trappist1");
		ExoBlocks.register(TrappistE.trap1e_cobblestone, "trap1e_cobblestone", "trappist1");
		ExoBlocks.register(TrappistE.trap1e_stone, "trap1e_stone", "trappist1");
		
	}
	
	public static class SharedTerrain {

		public static final BlockTerrain HOT_GROUND_1 = new BlockTerrain();
		public static final BlockTerrain HOT_GROUND_2 = new BlockTerrain();
		public static final BlockTerrain HOT_GROUND_3 = new BlockTerrain();
		
	}
	
	public static class TrappistC {
		
		public static final BlockTerrain T1C_TOP = new BlockTerrain();
		public static final BlockTerrain T1C_Dirt_1 = new BlockTerrain();
		public static final BlockTerrain T1C_Dirt_2 = new BlockTerrain();
	}
	
	public static class TrappistE {
		
		public static final Trappist1E_Grass trap1e_grass = new Trappist1E_Grass();
		public static final Trappist1E_Dirt trap1e_dirt = new Trappist1E_Dirt();
		public static final BlockMetamorphicRock trap1e_cobblestone = new BlockMetamorphicRock();
		public static final BlockMetamorphicRock trap1e_stone = new BlockMetamorphicRock();
	}

}
