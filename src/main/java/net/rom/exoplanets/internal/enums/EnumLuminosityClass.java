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

import net.minecraft.util.IStringSerializable;

public enum EnumLuminosityClass implements IStringSerializable {

	EXT_LUMINOUS_SUPERGIANTS("Ia-O"),
	LUMINOUS_SUPERGIANTS("Ia"),
	LESS_LUMINOUS_SUPERGIANTS("Ib"),
	BRIGHT_GIANTS("II"),
	GIANTS("III"),
	SUB_GIANTS("IV"),
	MAIN_SEQUENCE("V"),
	SUB_DWARF("VI"),
	SUPERGIANTS_I("D");

	private String clazz;
	
	EnumLuminosityClass(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public String getName() {
		return this.clazz;
	}


}
