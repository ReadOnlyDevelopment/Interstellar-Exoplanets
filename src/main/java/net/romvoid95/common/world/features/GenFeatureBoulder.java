package net.romvoid95.common.world.features;

import java.util.Arrays;
import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;

import net.romvoid95.api.space.Calculations;
import net.romvoid95.common.astronomy.yzceti.YzCetiBlocks;
import net.romvoid95.common.utility.mc.BlockUtil;
import net.romvoid95.common.utility.mc.BlockUtil.MatchType;
import net.romvoid95.core.ExoBlock;

public class GenFeatureBoulder extends GenFeatureBase {

	private Biome generateInBiome;
	private int sizeMax, sizeMin;

	public GenFeatureBoulder (Biome biome, int sizeMin, int sizeMax) {
		super(YzCetiBlocks.D.YZD_SEDIMENTARYROCK);
		this.generateInBiome = biome;
		this.sizeMin = sizeMin;
		this.sizeMax = sizeMax;
		this.water = true;
		this.validGroundBlocks = Arrays.asList(ExoBlock.YZD_SEDIMENTARYROCK.getBlock(),
				ExoBlock.YZD_IGNEOUS.getBlock());
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
}
