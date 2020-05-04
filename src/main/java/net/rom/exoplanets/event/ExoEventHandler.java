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
