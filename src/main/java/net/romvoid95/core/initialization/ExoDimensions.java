package net.romvoid95.core.initialization;

import static net.romvoid95.common.config.SConfigDimensionID.*;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import net.romvoid95.common.astronomy.trappist1.c.WorldProviderTrappist1C;
import net.romvoid95.common.astronomy.trappist1.d.WorldProviderTrappist1D;
import net.romvoid95.common.astronomy.trappist1.e.WorldProviderTrappist1E;
import net.romvoid95.common.astronomy.yzceti.b.WorldProviderYzCetiB;
import net.romvoid95.common.astronomy.yzceti.c.WorldProviderYzCetiC;
import net.romvoid95.common.astronomy.yzceti.d.WorldProviderYzCetiD;
import net.romvoid95.common.config.SConfigDimensionID;

public class ExoDimensions {

	public static DimensionType TRAPPIST_1B;
	public static DimensionType TRAPPIST_1C = DimensionType
			.register("Trappist 1 C", "_exo", id_trap_c, WorldProviderTrappist1C.class, false);
	public static DimensionType TRAPPIST_1D = DimensionType
			.register("Trappist 1 D", "_exo", id_trap_d, WorldProviderTrappist1D.class, false);
	public static DimensionType TRAPPIST_1E = DimensionType
			.register("Trappist 1 E", "_exo", id_trap_e, WorldProviderTrappist1E.class, false);
	public static DimensionType TRAPPIST_1F;
	public static DimensionType TRAPPIST_1G;
	public static DimensionType TRAPPIST_1H;

	public static DimensionType WOLF1061_1B;
	public static DimensionType WOLF1061_1C;
	public static DimensionType WOLF1061_1D;

	public static DimensionType YZCETIB = DimensionType
			.register("YzCeti B", "_exo", id_yz_b, WorldProviderYzCetiB.class, false);
	public static DimensionType YZCETIC = DimensionType
			.register("YzCeti C", "_exo", id_yz_c, WorldProviderYzCetiC.class, false);
	public static DimensionType YZCETID = DimensionType
			.register("YzCeti D", "_exo", id_yz_d, WorldProviderYzCetiD.class, false);

	public static DimensionType KEPLER1649_B;
	public static DimensionType KEPLER1649_C;

	public static void init () {

		ExoDimensions.YZCETIB = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_b);
		ExoDimensions.YZCETIC = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_c);
		ExoDimensions.YZCETID = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_d);

		ExoDimensions.WOLF1061_1B = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_wolf_b);
		ExoDimensions.WOLF1061_1C = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_wolf_c);
		ExoDimensions.WOLF1061_1D = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_wolf_d);

		ExoDimensions.TRAPPIST_1B = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_b);
		ExoDimensions.TRAPPIST_1C = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_c);
		ExoDimensions.TRAPPIST_1D = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_d);
		ExoDimensions.TRAPPIST_1E = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_e);
		ExoDimensions.TRAPPIST_1F = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_f);
		ExoDimensions.TRAPPIST_1G = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_g);
		ExoDimensions.TRAPPIST_1H = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_h);

		ExoDimensions.KEPLER1649_B = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_kepler_b);
		ExoDimensions.KEPLER1649_C = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_kepler_c);

	}
}
