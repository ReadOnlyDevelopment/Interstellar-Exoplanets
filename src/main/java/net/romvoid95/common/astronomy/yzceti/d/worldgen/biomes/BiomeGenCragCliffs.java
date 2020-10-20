package net.romvoid95.common.astronomy.yzceti.d.worldgen.biomes;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.romvoid95.common.world.biome.properties.BiomeData;
import net.romvoid95.core.ExoBlock;

public class BiomeGenCragCliffs extends YzCetiDBiomes {

	protected static final WorldGenBlockBlob	BOULDER_FEATURE	= new WorldGenBlockBlob(YzCetiBlocks.D.YZD_STONE, 5);
	public static IBlockState					rock			= YzCetiBlocks.D.YZD_MNT2.getDefaultState();

	public BiomeGenCragCliffs (String name, float height, float variation) {
		super(new BiomeData.BiomeDataBuilder()
				.biomeName(name)
				.rainfall(0F)
				.rainEnabled(false)
				.baseHeight(height)
				.heightVariation(variation));
		this.topBlock = rock;
		this.fillerBlock = YzCetiBlocks.D.YZD_MNT2.getDefaultState();
	}

	@Override
	public void decorate(World worldIn, Random rand, BlockPos pos) {
		int genChance = rand.nextInt(9);
		if (genChance == 0) {
			int k6 = rand.nextInt(16) + 8;
			int l = rand.nextInt(16) + 8;
			BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
			BOULDER_FEATURE.generate(worldIn, rand, blockpos);
		}

		super.decorate(worldIn, rand, pos);
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public void generateYzCetiBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		int i = worldIn.getSeaLevel();
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.25D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, BEDROCK);
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);

				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == ExoBlock.YZD_SEDIMENTARYROCK.getBlock()) {
					if (j == -1) {
						if (k <= 0) {
							iblockstate = AIR;
							iblockstate1 = ExoBlock.YZD_SEDIMENTARYROCK;
						} else if ((j1 >= (i - 4)) && (j1 <= (i + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
						}

						j = k;

						if (j1 >= (i - 1)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
						} else if (j1 < (i - 7 - k)) {
							iblockstate = AIR;
							iblockstate1 = ExoBlock.YZD_SEDIMENTARYROCK;
							chunkPrimerIn.setBlockState(i1, j1, l, GRAVEL);
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);

						if ((j == 0) && (iblockstate1.getBlock() == ExoBlock.YZD_SEDIMENTARYROCK.getBlock()) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
						}

						if ((j == 0) && (iblockstate == rock) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate = rock;
						}

						if ((j == 0) && (iblockstate1 == rock) && (k > 1)) {
							j = rand.nextInt(4) + Math.max(0, j1 - 63);
							iblockstate1 = rock;
						}
					}
				}
			}
		}
	}
}
