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

package net.rom.exoplanets.internal.world.biome;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.gen.feature.WorldGenerator;

public abstract class BiomeDecoratorExoBase extends BiomeDecorator {

	@Override
	protected void genDecorations(Biome biome, World world, Random rand) {
		this.generate(biome, world, rand);
	}

	@Override
	protected void generateOres(World world, Random rand) {
	}

//	protected void generateOre(WorldGenerator generator, EnumOreGen oreGen, World world, Random rand) {
//		this.generateOre(generator, oreGen.getBlockCount(), oreGen.getMinHeight(), oreGen.getMaxHeight(), world, rand);
//	}

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
