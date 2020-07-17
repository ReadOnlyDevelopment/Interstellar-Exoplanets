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

package net.rom.exoplanets.astronomy.trappist1.c;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.block.state.IBlockState;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;

public class BiomeDecoratorTrappist1C extends BiomeDecoratorSpace {

	private World currentWorld;

	private WorldGenerator dirtGen, gravelGen;

	public BiomeDecoratorTrappist1C() {

		dirtGen = new WorldGenMinableMeta(TrappistBlocks.SharedTerrain.HOT_GROUND_2, 30, 0, true, TrappistBlocks.TrappistC.T1C_TOP, 0);
		gravelGen = new WorldGenMinableMeta(TrappistBlocks.SharedTerrain.HOT_GROUND_1, 30, 0, true, TrappistBlocks.TrappistC.T1C_TOP, 0);
	}

	@Override
	protected void setCurrentWorld(World world) {
		this.currentWorld = world;
	}

	@Override
	protected World getCurrentWorld() {
		return this.currentWorld;
	}

	@Override
	protected void decorate() {

		this.generateOre(40, dirtGen, 5, 180);
		this.generateOre(60, gravelGen, 5, 180);
	}

	static class TrappistStonePredicate implements Predicate<IBlockState> {
		List<IBlockState> states = new ArrayList<IBlockState>();

		private TrappistStonePredicate() {
			states.add(TrappistBlocks.SharedTerrain.HOT_GROUND_1.getDefaultState());
		}

		public boolean apply(IBlockState state) {
			if (state != null && states.contains(state)) {
				return true;
			}
			return false;
		}
	}

}
