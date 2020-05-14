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

package net.rom.exoplanets.util.debug;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class BiomeDebug {
	public static boolean createFile() {
		File debug = new File("biomes.md");
		if (debug.exists()) {
			debug.delete();
		}
		try {
			debug.createNewFile();
			PrintWriter print_line = new PrintWriter(new FileWriter(debug));
			IForgeRegistry<Biome> biomeRegistry = GameRegistry.findRegistry(Biome.class);
			print_line.println("| Biome Name | Biome Registry Name | Biome ID |");
			print_line.println("|--------------------|--------------------|-----------|");
			for (Biome b : biomeRegistry.getValuesCollection()) {
				print_line.println(
						"|| " + b.getRegistryName() + " | " + Biome.getIdForBiome(b) + " |");
			}
			print_line.flush();
			print_line.close();
			return true;
		} catch (IOException localIOException) {
			return false;
		}
	}
}
