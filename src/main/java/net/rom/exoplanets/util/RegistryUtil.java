package net.rom.exoplanets.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Set;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.translation.LanguageMap;

public class RegistryUtil {

	private RegistryUtil() {
	};
	static Object listsDir = null;
	private static String lastLang = "";
	public static boolean langDisable;

	public static void loadLanguage(String langIdentifier, String assetPrefix, File source) {
		if (!lastLang.equals(langIdentifier)) {
			langDisable = false;
		}
		if (langDisable)
			return;
		String langFile = "assets/" + assetPrefix + "/lang/" + langIdentifier.substring(0, 3).toLowerCase()
				+ langIdentifier.substring(3).toUpperCase() + ".lang";
		InputStream stream = null;
		ZipFile zip = null;
		try {
			if (source.isDirectory() && (Boolean) Launch.blackboard.get("fml.deobfuscatedEnvironment")) {
				stream = new FileInputStream(new File(source.toURI().resolve(langFile).getPath()));
			} else {
				zip = new ZipFile(source);
				ZipEntry entry = zip.getEntry(langFile);
				if (entry == null)
					throw new FileNotFoundException();
				stream = zip.getInputStream(entry);
			}
			LanguageMap.inject(GCCoreUtil.supplementEntityKeys(stream, assetPrefix));
		} catch (FileNotFoundException fnf) {
			langDisable = true;
		} catch (Exception ignore) {
		} finally {
			if (stream != null)
				IOUtils.closeQuietly(stream);
			try {
				if (zip != null)
					zip.close();
			} catch (IOException ignore) {
			}
		}
	}

	/**
	 * This will list all items, using their complete unlocalized names with mod
	 * id's, and write them the file lists/items.txt. This is useful for writing
	 * theme files.
	 */
	public static void listItems() {
		BufferedWriter outstream = null;

		File itemlist = new File(listsDir.toString() + File.separator + "items.txt");
		if (itemlist.exists())
			itemlist.delete();
		try {
			outstream = new BufferedWriter(new FileWriter(itemlist.toString()));

			for (Object item : Item.REGISTRY) {
				String name = Item.REGISTRY.getNameForObject((Item) item).toString();
				if (true) {
					outstream.write(name);
					outstream.newLine();
				}
			}

			if (outstream != null)
				outstream.close();
		} catch (IOException e) {
			System.err.println("[DLDUNGEONS] Error: Could not write file items.txt");
			e.printStackTrace();
		}
	}

	/**
	 * This will list all blocks using their correct, unlocalized names, complete
	 * with mod id's, and write them to the file lists/blocks.txt. This is useful
	 * for editing theme files.
	 */
	public static void listBlocks() {
		BufferedWriter outstream = null;
		File itemlist = new File(listsDir.toString() + File.separator + "blocks.txt");

		if (itemlist.exists())
			itemlist.delete();
		try {
			outstream = new BufferedWriter(new FileWriter(itemlist.toString()));

			for (Object block : Block.REGISTRY) {
				String name = Block.REGISTRY.getNameForObject((Block) block).toString();
				if (true) {
					;
					outstream.write(name);
					outstream.newLine();
				}
			}

			if (outstream != null)
				outstream.close();
		} catch (IOException e) {
			System.err.println("[DLDUNGEONS] Error: Could not write file blocks.txt");
			e.printStackTrace();
		}
	}

}
