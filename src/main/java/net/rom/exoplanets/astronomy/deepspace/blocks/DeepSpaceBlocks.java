/**
 * Exoplanets
 * Copyright (C) 2020
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
package net.rom.exoplanets.astronomy.deepspace.blocks;

import net.rom.exoplanets.init.ExoBlocks;
import net.rom.exoplanets.internal.StellarRegistry;

public class DeepSpaceBlocks {
	
	public static BlockBasicSpace spaceBasic = new BlockBasicSpace();
	public static BlockFlooring flooring = new BlockFlooring();
	public static BlockInterior interior = new BlockInterior();
	public static BlockGlassProtective glassProtective = new BlockGlassProtective();
	public static BlockSurface surface = new BlockSurface();
	public static BlockDeepStructure deepWall = new BlockDeepStructure(true);
	public static BlockDeepStructure deepStructure = new BlockDeepStructure(false);
	
	public static void registerAll(StellarRegistry registry) {
		
		ExoBlocks.register(spaceBasic, "deep_space", new BlockBasicSpace.ItemBlock(spaceBasic));
		ExoBlocks.register(flooring, "flooring");
		ExoBlocks.register(interior, "interior", new BlockInterior.ItemBlock(interior));
		ExoBlocks.register(glassProtective, "protective_glass");
		ExoBlocks.register(surface, "surface");
		ExoBlocks.register(deepWall, "deep_wall");
		ExoBlocks.register(deepStructure, "deep");
	}

}
