package net.romvoid95.common.world.cave.mineshaft;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.biome.BiomeMesa;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.structure.*;

import net.romvoid95.common.world.cave.ConfigHolder;
import net.romvoid95.common.world.cave.ConfigLoader;

public class MapGenBetterMineshaft extends MapGenMineshaft {
	private int liquidAltitude;

	@Override
	protected StructureStart getStructureStart(int chunkX, int chunkZ) {
		MapGenStructureIO.registerStructure(StructureBetterMineshaftStart.class, "Mineshaft");
		Biome biome = this.world.getBiome(new BlockPos((chunkX << 4) + 8, 64, (chunkZ << 4) + 8));
		MapGenMineshaft.Type mapgenmineshaft$type = biome instanceof BiomeMesa ? MapGenMineshaft.Type.MESA
				: MapGenMineshaft.Type.NORMAL;
		return new StructureBetterMineshaftStart(this.world, this.rand, chunkX, chunkZ, mapgenmineshaft$type);
	}

	@Override
	public void generate(World worldIn, int x, int z, ChunkPrimer primer) {
		if (world == null) { // First call - lazy initialization
			this.initialize(worldIn);
		}

		super.generate(worldIn, x, z, primer);
	}

	private void initialize(World worldIn) {
		this.world = worldIn;
		ConfigHolder config = ConfigLoader.loadConfigFromFileForDimension(worldIn);
		this.liquidAltitude = config.liquidAltitude.get();
	}

	private class StructureBetterMineshaftStart extends StructureMineshaftStart {
		public StructureBetterMineshaftStart (World worldIn, Random rand, int chunkX, int chunkZ, MapGenMineshaft.Type type) {
			super(worldIn, rand, chunkX, chunkZ, type);
		}

		@Override
		public void generateStructure(World worldIn, Random rand, StructureBoundingBox structurebb) {
			components.removeIf(component -> (component.getBoundingBox().minY < (liquidAltitude + 5))
					|| (component.getBoundingBox().intersectsWith(structurebb)
							&& !component.addComponentParts(worldIn, rand, structurebb)));
		}
	}
}
