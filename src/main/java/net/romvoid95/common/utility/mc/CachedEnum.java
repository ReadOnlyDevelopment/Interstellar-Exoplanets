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

package net.romvoid95.common.utility.mc;

import lombok.experimental.UtilityClass;
import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.BiomeManager.BiomeType;

@UtilityClass
public class CachedEnum {

	private static EnumHand[]                   handValues       = EnumHand.values();
	private static EnumDyeColor[]               dyeColorValues   = EnumDyeColor.values();
	private static IRocketType.EnumRocketType[] rocketValues     = IRocketType.EnumRocketType.values();
	private static BiomeType[]                  biomeValues      = BiomeType.values();
	private static Axis[]                       axisValues       = Axis.values();
	private static TextFormatting[]             textFormatValues = TextFormatting.values();

	public static EnumHand[] valuesHandCached () {
		return CachedEnum.handValues;
	}

	public static EnumDyeColor[] valuesDyeCached () {
		return CachedEnum.dyeColorValues;
	}

	public static IRocketType.EnumRocketType[] valuesRocketCached () {
		return CachedEnum.rocketValues;
	}

	public static BiomeType[] valuesBiomeCached () {
		return CachedEnum.biomeValues;
	}

	public static Axis[] valuesAxisCached () {
		return CachedEnum.axisValues;
	}

	public static TextFormatting[] valuesTextFormattingCached () {
		return CachedEnum.textFormatValues;
	}
}