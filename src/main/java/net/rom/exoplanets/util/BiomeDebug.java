package net.rom.exoplanets.util;

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
						"| " + b.getBiomeName() + " | " + b.getRegistryName() + " | " + Biome.getIdForBiome(b) + " |");
			}
			print_line.flush();
			print_line.close();
			return true;
		} catch (IOException localIOException) {
			return false;
		}
	}
}
