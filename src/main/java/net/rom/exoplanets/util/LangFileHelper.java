package net.rom.exoplanets.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public class LangFileHelper {
	public static boolean createFile() {
		File debug = new File("langhelper.txt");
		if (debug.exists()) {
			debug.delete();
		}
		try {
			debug.createNewFile();
			PrintWriter print_line = new PrintWriter(new FileWriter(debug));
			IForgeRegistry<Block> blocks = GameRegistry.findRegistry(Block.class);
			for (Block b : blocks.getValuesCollection()) {
				print_line.println(b.getUnlocalizedName());
			}
			IForgeRegistry<Item> items = GameRegistry.findRegistry(Item.class);
			for (Item b : items.getValuesCollection()) {
				print_line.println(b.getUnlocalizedName());
			}
			print_line.flush();
			print_line.close();
			return true;
		} catch (IOException localIOException) {
			return false;
		}
	}
}
