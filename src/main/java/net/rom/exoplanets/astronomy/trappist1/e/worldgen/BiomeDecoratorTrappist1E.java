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

package net.rom.exoplanets.astronomy.trappist1.e.worldgen;

import java.util.ArrayList;
import java.util.List;

import com.google.common.base.Predicate;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.internal.world.gen.GenUtility;

public class BiomeDecoratorTrappist1E extends BiomeDecoratorSpace {

    private World currentWorld;
    private WorldGenerator iceGen;

    public BiomeDecoratorTrappist1E() {
    	
        this.iceGen = new WorldGenMinableMeta(Blocks.ICE, 8, 4, true, TrappistBlocks.TrappistE.trap1e_stone, 1);

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
    	
    	int randPosX = this.posX + this.rand.nextInt(16) + 8;
		int randPosZ = this.posZ + this.rand.nextInt(16) + 8;
		
		BlockPos pos = this.currentWorld.getHeight(new BlockPos(randPosX, 0, randPosZ));
    	
		this.generateOre(20, iceGen, 150, 200);

		pos = this.currentWorld.getHeight(new BlockPos(randPosX, 0, randPosZ));
			GenUtility.generateDome(Blocks.GLASS.getDefaultState(), 10, pos);
    }
    
	static class TrappistStonePredicate implements Predicate<IBlockState> {
		List<IBlockState> states = new ArrayList<IBlockState>();

		private TrappistStonePredicate() {
			states.add(TrappistBlocks.TrappistE.trap1e_cobblestone.getDefaultState());
		}

		public boolean apply(IBlockState state) {
			if (state != null && states.contains(state)) {
				return true;
			}
			return false;
		}
	}
}
