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
