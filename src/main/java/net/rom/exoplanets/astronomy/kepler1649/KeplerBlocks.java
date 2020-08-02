package net.rom.exoplanets.astronomy.kepler1649;

import net.rom.exoplanets.content.block.terrain.BlockExoSand;
import net.rom.exoplanets.content.block.terrain.BlockMetamorphicRock;
import net.rom.exoplanets.content.block.terrain.BlockSedimentaryRock;
import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.internal.StellarRegistry;

public class KeplerBlocks {

	public static void registerAll (StellarRegistry registry) {

		ExoBlocks.register(Kepler1649C.kepler_stone, "kepler_stone");
		ExoBlocks.register(Kepler1649C.kepler_surface, "kepler_surface");
		ExoBlocks.register(Kepler1649C.kepler_surface2, "kepler_surface2");
		ExoBlocks.register(Kepler1649C.kepler_sand, "kepler_sand");

	}

	public static class Kepler1649C {

		public static final BlockMetamorphicRock kepler_stone    = new BlockMetamorphicRock();
		public static final BlockSedimentaryRock kepler_surface  = new BlockSedimentaryRock();
		public static final BlockSedimentaryRock kepler_surface2 = new BlockSedimentaryRock();
		public static final BlockExoSand         kepler_sand     = new BlockExoSand();

	}

}
