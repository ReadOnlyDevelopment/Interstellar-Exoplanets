package net.romvoid95.common.astronomy.yzceti.d.worldgen.biomes;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.ChunkPrimer;

import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.astronomy.yzceti.d.worldgen.YzCetiDBiomes;
import net.romvoid95.core.ExoBlock;

public class BiomeGenYzCetiDHills extends YzCetiDBiomes {

	public IBlockState subTopBlock;
	public IBlockState lowerTopBlocks;
	public IBlockState topBlock;
	public IBlockState fillerBlock;

	public BiomeGenYzCetiDHills (String name, float height, float variation) {
		super(new BiomeProperties(name)
				.setRainfall(0F)
				.setRainDisabled()
				.setBaseHeight(height)
				.setHeightVariation(variation));
		this.topBlock = YzCetiBlocks.D.YZD_STONE.getDefaultState();
		this.subTopBlock = YzCetiBlocks.D.YZD_MNT2.getDefaultState();
		this.lowerTopBlocks = YzCetiBlocks.D.YZD_SEDIMENTARYROCK.getDefaultState();
		this.fillerBlock = YzCetiBlocks.D.YZD_MNT1.getDefaultState();
	}

	@Override
	public void genTerrainBlocks (World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		this.generateYzCetiBiomeTerrain(worldIn, rand, chunkPrimerIn, x, z, noiseVal);
	}

	@Override
	public void generateYzCetiBiomeTerrain(World worldIn, Random rand, ChunkPrimer chunkPrimerIn, int x, int z, double noiseVal) {
		IBlockState iblockstate = this.topBlock;
		IBlockState iblockstate3 = this.subTopBlock;
		IBlockState iblockstate4 = this.lowerTopBlocks;
		IBlockState iblockstate1 = this.fillerBlock;
		int j = -1;
		int k = (int) ((noiseVal / 3.0D) + 3.0D + (rand.nextDouble() * 0.25D));
		int l = x & 15;
		int i1 = z & 15;

		for (int j1 = 255; j1 >= 0; --j1) {
			if (j1 <= rand.nextInt(5)) {
				chunkPrimerIn.setBlockState(i1, j1, l, Blocks.BEDROCK.getDefaultState());
			} else {
				IBlockState iblockstate2 = chunkPrimerIn.getBlockState(i1, j1, l);
				if (iblockstate2.getMaterial() == Material.AIR) {
					j = -1;
				} else if (iblockstate2.getBlock() == ExoBlock.YZD_SEDIMENTARYROCK.getBlock()) {
					if (j == -1) {
						if ((j1 >= (63 - 4)) && (j1 <= (63 + 1))) {
							iblockstate = this.topBlock;
							iblockstate1 = this.fillerBlock;
							iblockstate3 = this.subTopBlock;
							iblockstate4 = this.lowerTopBlocks;
						}

						j = k;

						if (j1 >= (65 + 18)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate);
							iblockstate1 = iblockstate;
							iblockstate3 = iblockstate;
							iblockstate4 = iblockstate;
						} else if (j1 >= (65 + 12)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate3);
							iblockstate1 = iblockstate3;
							iblockstate = iblockstate3;
							iblockstate4 = iblockstate3;
						} else if (j1 >= (65 + 4)) {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate4);
							iblockstate1 = iblockstate4;
							iblockstate = iblockstate4;
							iblockstate3 = iblockstate4;
						} else if (j1 < (63 - 7 - k)) {
							iblockstate = null;
							iblockstate3 = null;
							iblockstate1 = this.subTopBlock;
							chunkPrimerIn.setBlockState(i1, j1, l, Blocks.GRAVEL.getDefaultState());
						} else {
							chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
						}
					} else if (j > 0) {
						--j;
						chunkPrimerIn.setBlockState(i1, j1, l, iblockstate1);
					}
				}
			}
		}
	}
}
