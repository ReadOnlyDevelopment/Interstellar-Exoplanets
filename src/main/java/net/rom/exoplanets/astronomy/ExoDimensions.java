/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom.exoplanets.astronomy;

import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.rom.exoplanets.astronomy.deepspace.DeepSpaceDimension;
import net.rom.exoplanets.astronomy.trappist1.TrappistDimensions;
import net.rom.exoplanets.astronomy.yzceti.YzCetiDimensions;
import net.rom.exoplanets.conf.SConfigDimensionID;

public class ExoDimensions {

	//public static DimensionType HIGHORBIT;

	public static void init() {
		YzCetiDimensions.YZCETIB = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_b);
		YzCetiDimensions.YZCETIC = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_c);
		YzCetiDimensions.YZCETID = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_yz_d);
		
		TrappistDimensions.TRAPPIST_1B = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_b);
		TrappistDimensions.TRAPPIST_1C = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_c);
		TrappistDimensions.TRAPPIST_1D = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_d);
		TrappistDimensions.TRAPPIST_1E = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_e);
		TrappistDimensions.TRAPPIST_1F = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_f);
		TrappistDimensions.TRAPPIST_1G = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_g);
		TrappistDimensions.TRAPPIST_1H = WorldUtil.getDimensionTypeById(SConfigDimensionID.id_trap_h);
		
		DeepSpaceDimension.DEEP_SPACE = WorldUtil.getDimensionTypeById(-65);
	}
}