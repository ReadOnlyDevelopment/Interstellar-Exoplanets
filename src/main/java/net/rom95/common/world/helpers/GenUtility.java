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

package net.rom95.common.world.helpers;

import java.util.HashMap;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom95.common.world.mapgen.GenExoplanetLake;

public class GenUtility {

	public static void generateStructure (WorldGenerator worldGen, World world, Random rand, BlockPos pos) {
		int x = pos.getX() + 8;
		int z = pos.getZ() + 8;
		int y = world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
		worldGen.generate(world, rand, new BlockPos(x, y, z));
	}

	public static void generateStructureWithRandomY (WorldGenerator worldGen, World world, Random rand, BlockPos pos) {
		int x = pos.getX() + 8;
		int z = pos.getZ() + 8;
		generateStructureWithSetY(worldGen, world, rand, new BlockPos(x, pos.getY(), z), pos.getY());
	}

	public static void generateStructureWithSetY (WorldGenerator worldGen, World world, Random rand, BlockPos pos, int y) {
		int x = pos.getX() + 8;
		int z = pos.getZ() + 8;
		worldGen.generate(world, rand, new BlockPos(x, y, z));
	}

	public static void generateStructureWithRangeY (WorldGenerator worldGen, World world, Random rand, BlockPos pos, int minY, int maxY) {
		int x = pos.getX() + 8;
		int z = pos.getZ() + 8;
		worldGen.generate(world, rand, new BlockPos(x, rand.nextInt(maxY) + minY, z));
	}

	public static void generateStructureWithRangeY (WorldGenerator worldGen, World world, Random rand, BlockPos pos, int minY, int maxY, int chance, Set<Biome> set) {
		int x = pos.getX() + 8;
		int z = pos.getZ() + 8;
		worldGen.generate(world, rand, new BlockPos(x, rand.nextInt(maxY) + minY, z));
	}

	public static void generateStructureWithRandom (WorldGenerator worldGen, World world, Random rand, BlockPos pos, int randomAmountXZ) {
		generateStructure(worldGen, world, rand, pos
				.add(rand.nextInt(randomAmountXZ), 0, rand.nextInt(randomAmountXZ)));
	}

	public static void generateStructureWithRandom (WorldGenerator worldGen, World world, Random rand, BlockPos pos, int randomAmountXZ, int randomAmountY) {
		generateStructureWithRandomY(worldGen, world, rand, pos
				.add(rand.nextInt(randomAmountXZ), rand.nextInt(randomAmountY), rand.nextInt(randomAmountXZ)));
	}

	public static void generateLakeGenStone (World world, Random rand, BlockPos pos, Block fluid, Block block) {
		IBlockState fState = fluid.getDefaultState();
		IBlockState bState = block.getDefaultState();
		new GenExoplanetLake(fState, bState, true);
	}

	public static void generateLake (World world, Random rand, BlockPos pos, Block fluid, Block block) {
		IBlockState fState = fluid.getDefaultState();
		IBlockState bState = block.getDefaultState();
		new GenExoplanetLake(fState, bState, false);
	}

	public static HashMap<BlockPos, IBlockState> generateSphereHollow (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc  = new BlockPos(xx, yy, zz);
						double   dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							if (i == 0) {
								blocks.put(pos.add(xx, yy, zz), state);
							}
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generateSphere (IBlockState state1, IBlockState state2, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc  = new BlockPos(xx, yy, zz);
						double   dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							if (i == 0) {
								blocks.put(pos.add(xx, yy, zz), state1);
							}
							else {
								blocks.put(pos.add(xx, yy, zz), state2);
							}
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generateSphere (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc  = new BlockPos(xx, yy, zz);
						double   dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							blocks.put(pos.add(xx, yy, zz), state);
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generateCube (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						blocks.put(pos.add(xx, yy, zz), state);
					}
				}
			}
		}
		return blocks;
	}

	public HashMap<BlockPos, IBlockState> generateDome (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = 0; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc  = new BlockPos(xx, yy, zz);
						double   dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							if (i == 0) {
								blocks.put(pos.add(xx, yy, zz), state);
							}
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generateCircle (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
				for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
					BlockPos loc  = new BlockPos(xx, 0, zz);
					double   dist = Math.abs(loc.getDistance(0, 0, 0));
					if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
						if (i == 0) {
							blocks.put(pos.add(xx, 0, zz), state);
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generatessSquare (IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks   = new HashMap<>();
		int                            halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
				for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
					blocks.put(pos.add(xx, 0, zz), state);
				}
			}
		}
		return blocks;
	}

	public static boolean checkValidSpawn (World world, BlockPos position, int checkSize, int loadedCheckSize) {
		if (!world.isAreaLoaded(position, loadedCheckSize)) {
			return false;
		}

		for (position = position.add(0, 0, 0); position.getY() > 5 && world.isAirBlock(position)
				|| world.getBlockState(position).getMaterial().isLiquid(); position = position.down()) {
			;
		}

		if (position.getY() <= 4) {
			return false;
		}

		for (int i = -checkSize; i <= checkSize; ++i) {
			for (int j = -checkSize; j <= checkSize; ++j) {
				if (world.isAirBlock(position.add(i, -1, j)) && world.isAirBlock(position.add(i, -2, j))
						|| world.getBlockState(position.add(i, -1, j)).getMaterial().isLiquid()
								&& world.getBlockState(position.add(i, -2, j)).getMaterial().isLiquid()) {
					return true;
				}
			}
		}
		return true;
	}

	public static boolean checkValidSpawn (World world, BlockPos position, int size) {
		return checkValidSpawn(world, position, size, size);
	}

}
