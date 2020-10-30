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


public class BiomeRockyPlateau extends Biome {

    public BiomeRockyPlateau(BiomeProperties properties) {
        super(properties);
        properties.setTemperature(0.8F);
        properties.setRainfall(0.2F);
        properties.setBaseHeight(2.6F);
        properties.setHeightVariation(1.2F);
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
