package net.rom.api.stellar.world.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeBase extends BiomeDecorator {
	protected Random rand;
	protected BlockPos pos;

	@Override
	public void decorate(World world, Random random, Biome biome, BlockPos pos) {
		if (this.getCurrentWorld() != null) {
			throw new RuntimeException("Already decorating!!");
		} else {
			this.setCurrentWorld(world);
			this.rand = random;
			this.pos = pos;
			this.decorate();
			this.setCurrentWorld(null);
			this.rand = null;
		}
	}

	protected void generateOre(int amountPerChunk, WorldGenerator worldGenerator, int minY, int maxY) {
		World currentWorld = this.getCurrentWorld();
		for (int var5 = 0; var5 < amountPerChunk; ++var5) {
			final int var6 = this.pos.getX() + this.rand.nextInt(16);
			final int var7 = this.rand.nextInt(maxY - minY) + minY;
			final int var8 = this.pos.getZ() + this.rand.nextInt(16);
			worldGenerator.generate(currentWorld, this.rand, new BlockPos(var6, var7, var8));
		}
	}

	protected abstract void setCurrentWorld(World world);

	protected abstract World getCurrentWorld();

	protected abstract void decorate();
}
