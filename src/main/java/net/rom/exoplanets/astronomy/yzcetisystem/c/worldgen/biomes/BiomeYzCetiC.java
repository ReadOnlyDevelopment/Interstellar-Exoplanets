package net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.biomes;

import net.rom.api.stellar.world.biome.BiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome.BiomeYzCetiBBase;


public class BiomeYzCetiC extends BiomeYzCetiBBase {

	public BiomeYzCetiC(BiomeProperties props) {
		super("yzc", props);
		props.setRainDisabled();
		props.setBaseHeight(1.0F);
		props.setHeightVariation(2.5F);
		props.setTemperature(5.0F);
		this.setTemp(5F);
		this.setBiomeHeight(72);
		this.setBiomeType(BiomeType.DARK);
		this.topBlock = YzCetiBlocks.CetiC.C_SEDIMENTARYROCK.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiC.C_METAMORPHIC.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiC.C_IGNEOUS;
	}
}