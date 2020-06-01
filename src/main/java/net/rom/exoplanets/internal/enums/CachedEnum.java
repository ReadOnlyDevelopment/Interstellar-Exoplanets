/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.enums;

import micdoodle8.mods.galacticraft.api.entity.IRocketType;
import net.minecraft.item.EnumDyeColor;
import net.minecraft.util.EnumFacing.Axis;
import net.minecraft.util.EnumHand;
import net.minecraft.util.text.TextFormatting;
import net.minecraftforge.common.BiomeManager.BiomeType;

public class CachedEnum {
	
	private static EnumHand[] handValues = EnumHand.values();
	private static EnumDyeColor[] dyeColorValues = EnumDyeColor.values();
	private static IRocketType.EnumRocketType[] rocketValues = IRocketType.EnumRocketType.values();
	private static BiomeType[] biomeValues = BiomeType.values();
	private static Axis[] axisValues = Axis.values();
	private static TextFormatting[] textFormatValues = TextFormatting.values();
	
	public static EnumHand[] valuesHandCached() {
		return CachedEnum.handValues;
	}
	
	public static EnumDyeColor[] valuesDyeCached() {
		return CachedEnum.dyeColorValues;
	}
	
	public static IRocketType.EnumRocketType[] valuesRocketCached() {
		return CachedEnum.rocketValues;
	}
	
	public static BiomeType[] valuesBiomeCached() {
		return CachedEnum.biomeValues;
	}
	
	public static Axis[] valuesAxisCached() {
		return CachedEnum.axisValues;
	}
	
	public static TextFormatting[] valuesTextFormattingCached() {
		return CachedEnum.textFormatValues;
	}
}