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

package net.rom.exoplanets.internal.world.gen;

import java.util.Random;

import net.minecraft.block.material.Material;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;

public class GenExoplanetLake extends WorldGenerator {

    private final IBlockState liquid;
    private final IBlockState stone;
    private final boolean genStone;

    public GenExoplanetLake(IBlockState liquid, IBlockState stone) {
        this(liquid, stone, false);
    }

    public GenExoplanetLake(IBlockState liquid, IBlockState stone, boolean genStone) {
        this.liquid = liquid;
        this.stone = stone;
        this.genStone = genStone;
    }

    @Override
    public boolean generate(World world, Random rand, BlockPos pos) {
        for (pos = pos.add(-8, 0, -8); pos.getY() > 5 && world.isAirBlock(pos); pos = pos.down()) {
        }

        if (pos.getY() <= 4) {
            return false;
        } else {
            pos = pos.down(4);
            boolean[] aboolean = new boolean[2048];
            int i = rand.nextInt(4) + 4;
            int j;

            for (j = 0; j < i; ++j) {
                double d0 = rand.nextDouble() * 6.0D + 3.0D;
                double d1 = rand.nextDouble() * 4.0D + 2.0D;
                double d2 = rand.nextDouble() * 6.0D + 3.0D;
                double d3 = rand.nextDouble() * (16.0D - d0 - 2.0D) + 1.0D + d0 / 2.0D;
                double d4 = rand.nextDouble() * (8.0D - d1 - 4.0D) + 2.0D + d1 / 2.0D;
                double d5 = rand.nextDouble() * (16.0D - d2 - 2.0D) + 1.0D + d2 / 2.0D;

                for (int l = 1; l < 15; ++l) {
                    for (int i1 = 1; i1 < 15; ++i1) {
                        for (int j1 = 1; j1 < 7; ++j1) {
                            double d6 = (l - d3) / (d0 / 2.0D);
                            double d7 = (j1 - d4) / (d1 / 2.0D);
                            double d8 = (i1 - d5) / (d2 / 2.0D);
                            double d9 = d6 * d6 + d7 * d7 + d8 * d8;

                            if (d9 < 1.0D) {
                                aboolean[(l * 16 + i1) * 8 + j1] = true;
                            }
                        }
                    }
                }
            }

            int k;
            int k1;
            boolean flag;

            for (j = 0; j < 16; ++j) {
                for (k1 = 0; k1 < 16; ++k1) {
                    for (k = 0; k < 8; ++k) {
                        flag = !aboolean[(j * 16 + k1) * 8 + k] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + k] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + k]
                                || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + k] || k1 > 0 && aboolean[(j * 16 + k1 - 1) * 8 + k] || k < 7 && aboolean[(j * 16 + k1) * 8 + k + 1]
                                || k > 0 && aboolean[(j * 16 + k1) * 8 + k - 1]);

                        if (flag) {
                            Material material = world.getBlockState(pos.add(j, k, k1)).getMaterial();

                            if (k >= 4 && material.isLiquid()) {
                                return false;
                            }

                            if (k < 4 && !material.isSolid() && world.getBlockState(pos.add(j, k, k1)) != this.liquid) {
                                return false;
                            }
                        }
                    }
                }
            }

            for (j = 0; j < 16; ++j) {
                for (k1 = 0; k1 < 16; ++k1) {
                    for (k = 0; k < 8; ++k) {
                        if (aboolean[(j * 16 + k1) * 8 + k]) {
                            world.setBlockState(pos.add(j, k, k1), k >= 4 ? Blocks.AIR.getDefaultState() : this.liquid, 2);
                        }
                    }
                }
            }
            if (this.liquid.getMaterial() == Material.LAVA || this.genStone) {
                for (j = 0; j < 16; ++j) {
                    for (k1 = 0; k1 < 16; ++k1) {
                        for (k = 0; k < 8; ++k) {
                            flag = !aboolean[(j * 16 + k1) * 8 + k] && (j < 15 && aboolean[((j + 1) * 16 + k1) * 8 + k] || j > 0 && aboolean[((j - 1) * 16 + k1) * 8 + k]
                                    || k1 < 15 && aboolean[(j * 16 + k1 + 1) * 8 + k] || k1 > 0 && aboolean[(j * 16 + k1 - 1) * 8 + k]
                                    || k < 7 && aboolean[(j * 16 + k1) * 8 + k + 1] || k > 0 && aboolean[(j * 16 + k1) * 8 + k - 1]);

                            if (flag && (k < 4 || rand.nextInt(2) != 0) && world.getBlockState(pos.add(j, k, k1)).getMaterial().isSolid()) {
                                world.setBlockState(pos.add(j, k, k1), this.stone, 2);
                            }
                        }
                    }
                }
            }
            return true;
        }
    }
}
