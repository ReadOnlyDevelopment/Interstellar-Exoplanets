package com.readonlydev.space.generation.util;

import java.util.HashMap;
import java.util.Random;
import java.util.function.Consumer;

import com.readonlydev.space.generation.chunkgen.IExoChunk;
import com.readonlydev.space.generation.feature.ModStructureTemplate;

import lombok.experimental.UtilityClass;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.Rotation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraft.world.gen.structure.StructureBoundingBox;

@UtilityClass
public class GenUtility {

	/**
	 * Determines if the chunk is already generated, in which case new structures
	 * cannot be placed
	 * 
	 * @param box
	 * @param world
	 * @return
	 */
	public static boolean chunksGenerated(StructureBoundingBox box, World world) {
		return world.isChunkGeneratedAt(box.minX >> 4, box.minZ >> 4)
				|| world.isChunkGeneratedAt(box.minX >> 4, box.maxZ >> 4)
				|| world.isChunkGeneratedAt(box.maxX >> 4, box.minZ >> 4)
				|| world.isChunkGeneratedAt(box.maxX >> 4, box.maxZ >> 4);
	}

	/**
	 * Calls the function n times, passing in the ith iteration
	 * 
	 * @param n
	 * @param func
	 */
	public static void performNTimes(int n, Consumer<Integer> func) {
		for (int i = 0; i < n; i++) {
			func.accept(i);
		}
	}

	/*
	 * Generates a generator n times in a chunk
	 */
	public static void generateN(World worldIn, Random rand, BlockPos pos, int n, int baseY, int randY,
			WorldGenerator gen) {
		randY = randY > 0 ? randY : 1;
		for (int i = 0; i < n; ++i) {
			int x = rand.nextInt(16) + 8;
			int y = rand.nextInt(randY) + baseY;
			int z = rand.nextInt(16) + 8;
			gen.generate(worldIn, rand, pos.add(x, y, z));
		}
	}

	/*
	 * Generates a generator n times in a chunk
	 */
	public static void generateN(World worldIn, Random rand, BlockPos pos, int n, int baseY,
			WorldGenerator gen) {
		for (int i = 0; i < n; ++i) {
			int x = rand.nextInt(16) + 8;
			int y = rand.nextInt(baseY);
			int z = rand.nextInt(16) + 8;
			gen.generate(worldIn, rand, pos.add(x, y, z));
		}
	}

	public static BlockPos posToChunk(BlockPos pos) {
		return new BlockPos(pos.getX() / 16f, pos.getY(), pos.getZ() / 16f);
	}

	public static int calculateGenerationHeight(World world, int x, int z) {
		return world.getTopSolidOrLiquidBlock(new BlockPos(x, 0, z)).getY();
	}

	/**
	 * Returns -1 if the variation is too much
	 */
	public static int getAverageGroundHeight(World world, int x, int z, int sizeX, int sizeZ, int maxVariation) {
		sizeX = x + sizeX;
		sizeZ = z + sizeZ;
		int corner1 = calculateGenerationHeight(world, x, z);
		int corner2 = calculateGenerationHeight(world, sizeX, z);
		int corner3 = calculateGenerationHeight(world, x, sizeZ);
		int corner4 = calculateGenerationHeight(world, sizeX, sizeZ);

		int max = Math.max(Math.max(corner3, corner4), Math.max(corner1, corner2));
		int min = Math.min(Math.min(corner3, corner4), Math.min(corner1, corner2));
		if (max - min > maxVariation) {
			return -1;
		}
		return min;
	}

	public static int getTerrainVariation(World world, int x, int z, int sizeX, int sizeZ) {
		sizeX = x + sizeX;
		sizeZ = z + sizeZ;
		int corner1 = calculateGenerationHeight(world, x, z);
		int corner2 = calculateGenerationHeight(world, sizeX, z);
		int corner3 = calculateGenerationHeight(world, x, sizeZ);
		int corner4 = calculateGenerationHeight(world, sizeX, sizeZ);

		int max = Math.max(Math.max(corner3, corner4), Math.max(corner1, corner2));
		int min = Math.min(Math.min(corner3, corner4), Math.min(corner1, corner2));
		return max - min;
	}

	public static int getGroundHeight(ModStructureTemplate template, IExoChunk gen, Rotation rotation) {
		StructureBoundingBox box = template.getBoundingBox();
		int corner1 = getGroundHeight(new BlockPos(box.maxX, 0, box.maxZ), gen, rotation);
		int corner2 = getGroundHeight(new BlockPos(box.minX, 0, box.maxZ), gen, rotation);
		int corner3 = getGroundHeight(new BlockPos(box.maxX, 0, box.minZ), gen, rotation);
		int corner4 = getGroundHeight(new BlockPos(box.minX, 0, box.minZ), gen, rotation);
		return Math.min(Math.min(corner3, corner4), Math.max(corner2, corner1));
	}

	/*
	 * From MapGenEndCity: determines the ground height
	 */
	public static int getGroundHeight(BlockPos pos, IExoChunk gen, Rotation rotation) {
		BlockPos chunk = posToChunk(pos);
		ChunkPrimer chunkprimer = new ChunkPrimer();
		gen.setBlocksInChunk(chunk.getX(), chunk.getZ(), chunkprimer);
		int i = 5;
		int j = 5;

		if (rotation == Rotation.CLOCKWISE_90) {
			i = -5;
		} else if (rotation == Rotation.CLOCKWISE_180) {
			i = -5;
			j = -5;
		} else if (rotation == Rotation.COUNTERCLOCKWISE_90) {
			j = -5;
		}

		int k = chunkprimer.findGroundBlockIdx(7, 7);
		int l = chunkprimer.findGroundBlockIdx(7, 7 + j);
		int i1 = chunkprimer.findGroundBlockIdx(7 + i, 7);
		int j1 = chunkprimer.findGroundBlockIdx(7 + i, 7 + j);
		int k1 = Math.min(Math.min(k, l), Math.min(i1, j1));
		return k1;
	}

	public static HashMap<BlockPos, IBlockState> generateDome(IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks = new HashMap<>();
		int halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = 0; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc = new BlockPos(xx, yy, zz);
						double dist = Math.abs(loc.getDistance(0, 0, 0));
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

	public static HashMap<BlockPos, IBlockState> generateSphere(IBlockState state1, IBlockState state2, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks = new HashMap<>();
		int halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc = new BlockPos(xx, yy, zz);
						double dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							if (i == 0) {
								blocks.put(pos.add(xx, yy, zz), state1);
							} else {
								blocks.put(pos.add(xx, yy, zz), state2);
							}
						}
					}
				}
			}
		}
		return blocks;
	}

	public static HashMap<BlockPos, IBlockState> generateSphere(IBlockState state, int size, BlockPos pos) {
		HashMap<BlockPos, IBlockState> blocks = new HashMap<>();
		int halfSize = (size / 2);
		for (int i = 0; i <= halfSize; i++) {
			for (int yy = -halfSize; yy < (halfSize + 1); yy++) {
				for (int zz = -halfSize; zz < (halfSize + 1); zz++) {
					for (int xx = -halfSize; xx < (halfSize + 1); xx++) {
						BlockPos loc = new BlockPos(xx, yy, zz);
						double dist = Math.abs(loc.getDistance(0, 0, 0));
						if (dist <= halfSize - i && dist > halfSize - (i + 1)) {
							blocks.put(pos.add(xx, yy, zz), state);
						}
					}
				}
			}
		}
		return blocks;
	}

	public static boolean checkValidSpawn(World world, BlockPos position, int checkSize, int loadedCheckSize) {
		if (!world.isAreaLoaded(position, loadedCheckSize)) {
			return false;
		}

		for (position = position.add(0, 0, 0); ((position.getY() > 5) && world.isAirBlock(position))
				|| world.getBlockState(position).getMaterial().isLiquid(); position = position.down()) {

		}

		if (position.getY() <= 4) {
			return false;
		}

		for (int i = -checkSize; i <= checkSize; ++i) {
			for (int j = -checkSize; j <= checkSize; ++j) {
				if ((world.isAirBlock(position.add(i, -1, j)) && world.isAirBlock(position.add(i, -2, j)))
						|| (world.getBlockState(position.add(i, -1, j)).getMaterial().isLiquid()
								&& world.getBlockState(position.add(i, -2, j)).getMaterial().isLiquid())) {
					return true;
				}
			}
		}
		return true;
	}

	public static boolean checkValidSpawn(World world, BlockPos position, int size) {
		return checkValidSpawn(world, position, size, size);
	}
}
