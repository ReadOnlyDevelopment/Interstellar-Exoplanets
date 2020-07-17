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
