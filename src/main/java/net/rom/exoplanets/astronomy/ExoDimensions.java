package net.rom.exoplanets.astronomy;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiDimensions;
import net.rom.exoplanets.conf.SConfigDimensionID;

public class ExoDimensions {

	public static void init() {
		YzCetiDimensions.YZCETIB = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_b);
		YzCetiDimensions.YZCETIC = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_c);
		YzCetiDimensions.YZCETID = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_d);

	}
}