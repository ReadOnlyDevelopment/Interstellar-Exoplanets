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

package net.rom.exoplanets.content.block.terrain;

import net.minecraft.block.SoundType;
import net.rom.exoplanets.content.block.BlockTerrain;

/**
 * Igneous rock, or magmatic rock, is one of the three main rock types, the
 * others being sedimentary and metamorphic. Igneous rock is formed through the
 * cooling and solidification of magma or lava. The magma can be derived from
 * partial melts of existing rocks in either a planet's mantle or crust.
 * <br><br>
 * (jeez sounds JUST like Obsidian DONT IT, Because Obsidian is a Igneous Rock, Thanks Wiki)
 * 
 *
 */
public class BlockIgneousRock extends BlockTerrain {

	public BlockIgneousRock() {
		super();
		this.setResistance(20.0F);
		this.setHardness(30.0F);
		this.setSoundType(SoundType.STONE);
		this.setHarvestLevel("pickaxe", 3);
	}
}
