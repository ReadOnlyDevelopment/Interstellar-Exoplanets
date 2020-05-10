/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.api.stellar.world.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.api.stellar.world.EnumOreGen;

public abstract class BiomeDecoratorExoBase extends BiomeDecorator {

	@Override
	protected void genDecorations(Biome biome, World world, Random rand) {
		this.generate(biome, world, rand);
	}

	@Override
	protected void generateOres(World world, Random rand) {
	}

	protected void generateOre(WorldGenerator generator, EnumOreGen oreGen, World world, Random rand) {
		this.generateOre(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight(), world, rand);
	}

	public void generateOre(WorldGenerator generator, int blockCount, int minHeight, int maxHeight, World world,
			Random rand) {
		for (int j = 0; j < blockCount; ++j) {
			BlockPos blockpos = this.chunkPos.add(rand.nextInt(16), rand.nextInt(maxHeight - minHeight) + minHeight,
					rand.nextInt(16));
			generator.generate(world, rand, blockpos);
		}
	}

	protected abstract void generate(Biome biome, World world, Random rand);

}
