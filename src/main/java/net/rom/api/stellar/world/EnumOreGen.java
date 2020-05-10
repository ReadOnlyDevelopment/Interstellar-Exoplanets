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

package net.rom.api.stellar.world;

import net.minecraft.util.IStringSerializable;

public enum EnumOreGen implements IStringSerializable {
	
	RUTILE("rutile", 13, 10, 1, 45),
	LIMONITE("limonite", 9, 20, 0, 64),
	RUTHENIUM("ruthenium", 8, 1, 0, 16);

	private final String name;
	private final int genCount;
	private final int blockCount;
	private final int minHeight;
	private final int maxHeight;

	private EnumOreGen(String name, int genCount, int blockCount, int minHeight, int maxHeight) {
		this.name = name;
		this.genCount = genCount;
		this.blockCount = blockCount;
		this.minHeight = minHeight;
		this.maxHeight = maxHeight;
	}

	public int getGenCount() {
		return this.genCount;
	}

	public int getBlockCount() {
		return this.blockCount;
	}

	public int getMinHeight() {
		return this.minHeight;
	}

	public int getMaxHeight() {
		return this.maxHeight;
	}
	
	public EnumOreGen instance() {
		return this;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
