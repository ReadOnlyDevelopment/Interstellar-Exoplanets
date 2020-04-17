package net.rom.exoplanets.astronomy.yzcetisystem.c.worldgen.biomes;

import net.rom.api.enums.EnumBiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome.BiomeYzCetiBBase;
import net.rom.exoplanets.init.BlocksRegister;


public class BiomeYzCetiC extends BiomeYzCetiBBase {
	
	public BiomeYzCetiC(BiomeProperties props) {
		super("yzc", props);
		props.setRainDisabled();
		props.setBaseHeight(1.0F);
		props.setHeightVariation(0.8F);
		props.setTemperature(5.0F);
		this.setTemp(5F);
		this.setBiomeHeight(72);
		this.setBiomeType(EnumBiomeType.DARK);
		this.topBlock = BlocksRegister.YZC_SEDIMENTARY.getDefaultState();
		this.fillerBlock = BlocksRegister.YZC_METAMORPHIC.getDefaultState();
		this.stoneBlock = BlocksRegister.YZC_INGNEOUS;
	}
}