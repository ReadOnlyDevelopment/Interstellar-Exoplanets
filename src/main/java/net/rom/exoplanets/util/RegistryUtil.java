package net.rom.exoplanets.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Comparator;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import org.apache.commons.io.IOUtils;

import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.launchwrapper.Launch;
import net.minecraft.util.text.translation.LanguageMap;
import net.minecraftforge.fml.common.registry.GameRegistry;
import net.minecraftforge.registries.IForgeRegistry;

public final class RegistryUtil {

	private RegistryUtil() {
	};

	static Object			listsDir	= null;
	private static String	lastLang	= "";
	public static boolean	langDisable;

	public static void loadLanguage(String langIdentifier, String assetPrefix, File source) {
		if (!lastLang.equals(langIdentifier)) {
			langDisable = false;
		}
		if (langDisable)
			return;
		String langFile = "assets/" + assetPrefix + "/lang/" + langIdentifier.substring(0, 3).toLowerCase() + langIdentifier.substring(3).toUpperCase() + ".lang";
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
	 * This will list all items, using their complete unlocalized names with mod id's, and write them the file lists/items.txt. This is useful for writing theme files.
	 */
	public static void listItems() {
		File itemlist = new File("items.txt");

		if (itemlist.exists())
			itemlist.delete();
		try {
			itemlist.createNewFile();
			BufferedWriter outstream = new BufferedWriter(new FileWriter(itemlist.toString()));

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
	 * This will list all blocks using their correct, unlocalized names, complete with mod id's, and write them to the file lists/blocks.txt. This is useful for editing theme files.
	 */
	public static void listBlocks() {
		File itemlist = new File("blocks.txt");

		if (itemlist.exists())
			itemlist.delete();
		try {
			itemlist.createNewFile();
			BufferedWriter outstream = new BufferedWriter(new FileWriter(itemlist.toString()));
			IForgeRegistry<Block> blocks = GameRegistry.findRegistry(Block.class);
			blocks.getValuesCollection().stream().sorted(Comparator.comparing(Block::getUnlocalizedName)).forEach(b -> {
				if (b.getUnlocalizedName().contains(".exoplanets.")) {
					String name = b.getUnlocalizedName() + ".name=";
					if (true) {
						try {
							outstream.write(name);
							outstream.newLine();
						} catch (IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}

					}
				}
			});

			if (outstream != null)
				outstream.close();
		} catch (IOException e) {
			System.err.println("[DLDUNGEONS] Error: Could not write file blocks.txt");
			e.printStackTrace();
		}
	}
}
