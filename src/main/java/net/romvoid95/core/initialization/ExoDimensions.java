package net.romvoid95.core.initialization;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.world.DimensionType;
import net.romvoid95.common.config.SConfigDimensionID;

public class ExoDimensions {

	public static DimensionType TRAPPIST_1B;
	public static DimensionType TRAPPIST_1C;
	public static DimensionType TRAPPIST_1D;
	public static DimensionType TRAPPIST_1E;
	public static DimensionType TRAPPIST_1F;
	public static DimensionType TRAPPIST_1G;
	public static DimensionType TRAPPIST_1H;

	public static DimensionType WOLF1061_1B;
	public static DimensionType WOLF1061_1C;
	public static DimensionType WOLF1061_1D;

	public static DimensionType YZCETIB;
	public static DimensionType YZCETIC;
	public static DimensionType YZCETID;

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
