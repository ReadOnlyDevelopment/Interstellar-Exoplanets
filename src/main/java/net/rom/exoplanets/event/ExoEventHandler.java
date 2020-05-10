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

package net.rom.exoplanets.event;

import micdoodle8.mods.galacticraft.api.event.wgen.GCCoreEventPopulate;
import micdoodle8.mods.galacticraft.core.world.gen.WorldGenMinableMeta;
import micdoodle8.mods.galacticraft.planets.asteroids.blocks.AsteroidBlocks;
import micdoodle8.mods.galacticraft.planets.asteroids.dimension.WorldProviderAsteroids;
import micdoodle8.mods.galacticraft.planets.mars.blocks.MarsBlocks;
import micdoodle8.mods.galacticraft.planets.mars.dimension.WorldProviderMars;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.init.ExoBlocks;

public class ExoEventHandler {
//	@SubscribeEvent
//	public void onPlanetDecorated(GCCoreEventPopulate.Post event) {
//		if (event.world.provider instanceof WorldProviderMars) {
//			genOre(event.world, event.pos,
//					new WorldGenMinableMeta(ExoBlocks.overworldore, 4, 0, true, MarsBlocks.marsBlock, 9), 6, 4, 18);
//			genOre(event.world, event.pos,
//					new WorldGenMinableMeta(ExoBlocks.overworldore, 6, 1, true, MarsBlocks.marsBlock, 9), 10, 6, 30);
//			genOre(event.world, event.pos,
//					new WorldGenMinableMeta(ExoBlocks.overworldore, 16, 2, true, MarsBlocks.marsBlock, 9), 15, 6, 70);
//
//		}
//		if (event.world.provider instanceof WorldProviderAsteroids) {
//
//			genOre(event.world, event.pos,
//					new WorldGenMinableMeta(ExoBlocks.overworldore, 4, 0, true, AsteroidBlocks.blockBasic, 0), 6, 0, 255);
//		}
//	}
//
//	void genOre(World world, BlockPos pos, WorldGenerator wg, int amountPerChunk, int minY, int maxY) {
//		BlockPos pos1 = pos.add(world.rand.nextInt(16), world.rand.nextInt(maxY - minY) + minY, world.rand.nextInt(16));
//
//		for (int i = 0; i < amountPerChunk; i++)
//			wg.generate(world, world.rand, pos1);
//
//	}
}
