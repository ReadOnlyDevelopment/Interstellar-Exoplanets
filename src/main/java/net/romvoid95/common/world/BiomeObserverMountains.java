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
package net.romvoid95.common.world;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.romvoid95.api.world.worldgen.terrain.ExoGenMountain;
import net.romvoid95.space.astrogeneration.biome.BiomeData.DataValues;
import net.romvoid95.space.kepler1649.KeplerBlocks;


public class BiomeObserverMountains extends Biome {
	
	protected static final WorldGenerator MOUNTAIN = new ExoGenMountain(true, KeplerBlocks.Kepler1649B.KEPLERB_STONE.getDefaultState(), 50, 0);
	public BlockPos chunkPos;

    public BiomeObserverMountains() {
        super(new DataValues("Observatory Mountains")
        		.temperature(0.8F)
        		.rainfall(0.0F)
        		.baseHeight(3.1F)
        		.heightVariation(0.4525F)
        		.finalzie());
        decorator.treesPerChunk = 0;
        decorator.extraTreeChance = 0;
        decorator.flowersPerChunk = 0;
        decorator.grassPerChunk = 8;

        spawnableCreatureList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {

        super.decorate(worldIn, rand, pos);
    }
}
