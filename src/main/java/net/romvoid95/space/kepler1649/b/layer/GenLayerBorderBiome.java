//package net.romvoid95.space.kepler1649.b.layer;
//
//import net.minecraft.world.biome.Biome;
//import net.minecraft.world.gen.layer.GenLayer;
//import net.minecraft.world.gen.layer.IntCache;
//import net.romvoid95.api.world.ExoBiomes;
//
//public class GenLayerBorderBiome extends GenLayer {
//
//	public GenLayerBorderBiome(long l, GenLayer genlayer) {
//		super(l);
//		parent = genlayer;
//	}
//
//	@Override
//	public int[] getInts(int areaX, int areaY, int areaWidth, int areaHeight) {
//		int nx = areaX - 1;
//		int nz = areaY - 1;
//		int nWidth = areaWidth + 2;
//		int nHeight = areaHeight + 2;
//		int in[] = parent.getInts(nx, nz, nWidth, nHeight);
//		int out[] = IntCache.getIntCache(areaWidth * areaHeight);
//
//		int cliff = Biome.getIdForBiome(ExoBiomes.KEPLER1649B_CLIFF);
//		int main = Biome.getIdForBiome(ExoBiomes.KEPLER1649B_MAIN);
//
//		for (int dz = 0; dz < areaHeight; dz++) {
//			for (int dx = 0; dx < areaWidth; dx++) {
//
//				int right = in[dx + 0 + (dz + 1) * nWidth];
//				int left = in[dx + 2 + (dz + 1) * nWidth];
//				int up = in[dx + 1 + (dz + 0) * nWidth];
//				int down = in[dx + 1 + (dz + 2) * nWidth];
//				int center = in[dx + 1 + (dz + 1) * nWidth];
//				
//				int ur = in[dx + 0 + (dz + 0) * nWidth];
//				int ul = in[dx + 2 + (dz + 0) * nWidth];
//				int dr = in[dx + 0 + (dz + 2) * nWidth];
//				int dl = in[dx + 2 + (dz + 2) * nWidth];
//
//				if(onBorder(cliff, center, right, left, up, down)) {
//					out[dx + dz * areaWidth] = main;
//				} else {
//					out[dx + dz * areaWidth] = center;
//				}
//			}
//		}
//		return out;
//	}
//
//	/**
//	 * Returns true if the center biome is not the specified biome and any of the surrounding biomes are the specified biomes
//	 */
//	private boolean onBorder(int biomeID, int center, int right, int left, int up, int down) {
//
//		if (center == biomeID) {
//			return false;
//		} else if (right == biomeID) {
//			return true;
//		} else if (left == biomeID) {
//			return true;
//		} else if (up == biomeID) {
//			return true;
//		} else if (down == biomeID) {
//			return true;
//		} else {
//			return false;
//		}
//	}
//
//	/**
//	 * Returns true if the center biome is the first specified biome and any of the surrounding biomes are the second bioms
//	 */
//	private boolean onBorder(int biomeID, int biomeID2, int center, int right, int left, int up, int down) {
//
//		if (center != biomeID) {
//			return false;
//		}
//		if (right == biomeID2) {
//			return true;
//		}
//		if (left == biomeID2) {
//			return true;
//		}
//		if (up == biomeID2) {
//			return true;
//		}
//		if (down == biomeID2) {
//			return true;
//		}
//
//		return false;
//	}
//}
