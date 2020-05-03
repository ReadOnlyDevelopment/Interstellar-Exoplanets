package net.rom.exoplanets.astronomy.yzcetisystem.b.worldgen.biome;

import net.rom.api.stellar.enums.EnumBiomeType;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;

public class BiomeYzCetiBDirty extends BiomeYzCetiBBase {
	
	public static int grassFoilageColorMultiplier = 0x000000;
	
	public BiomeYzCetiBDirty(BiomeProperties props) {
		super("dirty", props);
		props.setRainDisabled();
		props.setBaseHeight(2.0F);
		props.setHeightVariation(0.6F);
		props.setTemperature(2.0F);
		this.setTemp(2F);
		this.setBiomeHeight(82);
		this.setBiomeType(EnumBiomeType.ABANDONED);
		this.topBlock = YzCetiBlocks.CetiB.B_LOOSE_SEDIMENT.getDefaultState();
		this.fillerBlock = YzCetiBlocks.CetiB.B_SEDIMENTARYROCK.getDefaultState();
		this.stoneBlock = YzCetiBlocks.CetiB.B_METAMORPHIC;
	}
}