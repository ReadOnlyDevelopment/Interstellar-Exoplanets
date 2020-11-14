package net.romvoid95.api.world.worldgen.feature;

import java.util.Collection;
import java.util.Collections;
import java.util.Random;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.BlockPos.MutableBlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.api.math.Calculations;
import net.romvoid95.common.utility.mc.BlockUtil;
import net.romvoid95.common.utility.mc.BlockUtil.MatchType;
import net.romvoid95.space.yzceti.YzCetiBlocks;

public class ExoFeatureBoulder extends ExoGenFeature {

	private Biome generateInBiome;
	private int sizeMax, sizeMin;

	public ExoFeatureBoulder (Biome biome, int sizeMin, int sizeMax) {
		super(YzCetiBlocks.D.YZD_SEDIMENTARYROCK);
		this.generateInBiome = biome;
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.water = true;
		//this.validGroundBlocks;
	}

	@Override
	public void generate(World world, Random rand, ChunkPos chunkPos) {

		if (((BiomeAdaptive) world.getBiome(new BlockPos(chunkPos.x, 0, chunkPos.z))).isInstance(generateInBiome.getBiomeClass())) {
			for (int i = 0; i < this.strengthFactor; ++i) {

				final BlockPos pos = getOffsetPos(chunkPos).add(rand.nextInt(16), 0, rand.nextInt(16));
				int y;

				switch (this.heightType) {
					case NEXT_INT:
						y = getRangedRandom(rand, this.minY, this.maxY);
						break;
					case GET_HEIGHT_VALUE:
					default:
						y = world.getHeight(pos).getY();
						break;
				}
				if ((y >= this.minY) && (y <= this.maxY) && (rand.nextInt(this.chance) == 0)) {
					if (BlockUtil.checkAreaMaterials(MatchType.ALL_IGNORE_REPLACEABLE, world, pos.up(y), 2)) {
						int size = Calculations.getRandomNumberInRange(rand, this.sizeMin, this.sizeMax);
						new WorldGenBlob(getFeatureBlock(), size, validGroundBlocks, this.water).generate(world, rand,
								pos.up(y));
					}
				}
			}
		}
	}
	public class WorldGenBlob extends WorldGenerator {

		private final IBlockState	blobBlock;
		private final int			blobSize;
		private boolean				allowInWater;
		private Collection<Block>	validGroundBlocks;
		private boolean				enabled	= true;

		public WorldGenBlob (IBlockState block, int size, Collection<Block> validGroundBlocks, boolean allowInWater) {

			super(false);
			this.blobBlock = block;
			this.blobSize = size;
			this.validGroundBlocks = Collections.unmodifiableCollection(validGroundBlocks);
			this.allowInWater = allowInWater;
		}

		@Override
		public boolean generate(World world, Random rand, BlockPos pos) {

			if (this.enabled) {

				int x = pos.getX();
				int y = pos.getY();
				int z = pos.getZ();

				MutableBlockPos mpos = new MutableBlockPos(pos);
				IBlockState blockstate;
				while (true) {

					mpos.move(EnumFacing.DOWN);
					if (mpos.getY() > 3) {
						label63: {
						if (!world.isAirBlock(mpos)) {
							blockstate = world.getBlockState(mpos);

							// Water check.
							if (!this.allowInWater) {
								if ((blockstate.getMaterial() == Material.WATER) || BlockUtil.checkAreaMaterials(
										MatchType.ANY, world, mpos, 1, Material.WATER)) {
									return false;
								}
							}
							if (validGroundBlocks.contains(blockstate.getBlock())) {
								break label63;
							}
						}
						--y;
						continue;
					}
					}

					if (mpos.getY() <= 3) {
						return false;
					}

					int k2 = this.blobSize;

					for (int l = 0; (this.blobSize >= 0) && (l < 3); ++l) {
						int sizeX = this.blobSize + rand.nextInt(2);
						int sizeY = this.blobSize + rand.nextInt(2);
						int sizeZ = this.blobSize + rand.nextInt(2);
						float f = ((sizeX + sizeY + sizeZ) * 0.333F) + 0.5F;

						for (int bx = x - sizeX; bx <= (x + sizeX); ++bx) {
							for (int bz = z - sizeZ; bz <= (z + sizeZ); ++bz) {
								for (int by = y - sizeY; by <= (y + sizeY); ++by) {
									float f1 = (bx - x);
									float f2 = (bz - z);
									float f3 = (by - y);

									if (((f1 * f1) + (f2 * f2) + (f3 * f3)) <= (f * f)) {
										this.placeBoulderBlock(world, new BlockPos(bx, by, bz), blobBlock);
									}
								}
							}
						}

						x += -(this.blobSize + 1) + rand.nextInt(2 + (k2 * 2));
						z += -(this.blobSize + 1) + rand.nextInt(2 + (k2 * 2));
						y += 0 - rand.nextInt(2);
					}
					return true;
				}
			}
			return false;
		}

		private void placeBoulderBlock(World world, BlockPos targetPos, IBlockState boulderBlock) {

			MutableBlockPos mpos = new MutableBlockPos(targetPos);
			Block targetBlock = world.getBlockState(targetPos).getBlock();

			if (targetBlock.isReplaceable(world, targetPos)) {
				world.setBlockState(targetPos, boulderBlock, 4);

				// Double-plant check.
				if (world.getBlockState(mpos.move(EnumFacing.UP)).getBlock() == Blocks.DOUBLE_PLANT) {
					world.setBlockState(mpos, Blocks.AIR.getDefaultState(), 2);
				}
			}
		}
	}
}
