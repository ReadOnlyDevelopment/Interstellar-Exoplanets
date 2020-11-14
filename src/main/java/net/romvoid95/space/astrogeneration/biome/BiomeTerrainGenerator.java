package net.romvoid95.space.astrogeneration.biome;

import java.util.HashMap;

import net.minecraft.block.state.IBlockState;

public class BiomeTerrainGenerator {
	
	public HashMap<IBlockState, YValues> data = new HashMap<IBlockState, YValues>();
	
	private boolean primerYat (int i, int min, int max) {
		return ((i >= min) && (i <= max));
	}

	public static class YValues {
		
		public int a, b;
		
		public YValues(int a, int b) {
			this.a = a;
			this.b = b;
		}
		
	}
}
