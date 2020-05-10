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

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.google.common.collect.Lists;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.client.resources.I18n;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityList;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.CraftingManager;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.potion.Potion;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.registry.EntityEntry;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.rom.exoplanets.ExoplanetsMod;

public final class Data {
	private static final String REGISTRY_NAME_IS_NULL = "Registry name is null! This indicates a broken mod and is a serious problem!";
	private static final String NO_MAP_POS = "Solar System does not contain a map vector value!";
	private static final String NO_STAR = "Solar System does not have a mainStar set";
	private static final String NO_PLANETS = "Solar System does not planets registered";
	private static final String NO_MOONS = "does not have any moons registered";
	private static final String NO_SAT = "does not have any Satellites registered";
	
	private static final String SEPARATOR = "--------------------------------------------------------------------------------";

	private Data() {
		throw new IllegalAccessError("Utility class");
	}

	public static void dumpBlocks() {
		ExoplanetsMod.logger.info(SEPARATOR);
		List<String> lines = new ArrayList<>();

		for (Block block : ForgeRegistries.BLOCKS) {
			try {
				ResourceLocation name = Objects.requireNonNull(block.getRegistryName(), REGISTRY_NAME_IS_NULL);
				String translatedName = I18n.format(block.getUnlocalizedName() + ".name");
				lines.add(String.format("%-60s %-60s", name, translatedName));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on block: {} ***", block);
				ExoplanetsMod.logger.catching(ex);
			}
		}

		lines.sort(String::compareToIgnoreCase);
		lines.forEach(ExoplanetsMod.logger::info);
		ExoplanetsMod.logger.info(SEPARATOR);
	}

	public static void dumpEnchantments() {
		ExoplanetsMod.logger.info(SEPARATOR);
		for (Enchantment ench : Enchantment.REGISTRY) {
			try {
				ResourceLocation name = Objects.requireNonNull(ench.getRegistryName(), REGISTRY_NAME_IS_NULL);
				String translatedName = ench.getTranslatedName(1).replaceFirst(" I$", "");
				String type = ench.type == null ? "null" : ench.type.name();
				ExoplanetsMod.logger.info(String.format("%-30s %-40s type=%-10s", translatedName, name, type));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on enchantment: {} ***", ench);
				ExoplanetsMod.logger.catching(ex);
			}
		}
		ExoplanetsMod.logger.info(SEPARATOR);
	}

	public static void dumpEntityList() {
		ExoplanetsMod.logger.info(SEPARATOR);
		List<String> list = Lists.newArrayList();

		for (EntityEntry entry : ForgeRegistries.ENTITIES) {
			try {
				ResourceLocation name = Objects.requireNonNull(entry.getRegistryName(), REGISTRY_NAME_IS_NULL);
				Class<? extends Entity> clazz = EntityList.getClass(entry.getRegistryName());
				String oldName = EntityList.getTranslationName(name);
				int id = EntityList.getID(clazz);
				list.add(String.format("%-30s %4d   %-40s %-40s", oldName, id, name, clazz));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on entity: {} ***", entry.getEntityClass());
				ExoplanetsMod.logger.catching(ex);
			}
		}

		list.sort(String::compareToIgnoreCase);
		list.forEach(ExoplanetsMod.logger::info);
		ExoplanetsMod.logger.info(SEPARATOR);
	}

	public static void dumpItems() {
		ExoplanetsMod.logger.info(SEPARATOR);
		List<String> lines = new ArrayList<>();

		for (Item item : ForgeRegistries.ITEMS) {
			try {
				ResourceLocation name = Objects.requireNonNull(item.getRegistryName(), REGISTRY_NAME_IS_NULL);
				String translatedName = I18n.format(item.getUnlocalizedName() + ".name");
				lines.add(String.format("%-60s %-60s", name, translatedName));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on item: {} ***", item);
				ExoplanetsMod.logger.catching(ex);
			}
		}

		lines.forEach(ExoplanetsMod.logger::info);
		ExoplanetsMod.logger.info(SEPARATOR);
	}

	public static void dumpPotionEffects() {
		ExoplanetsMod.logger.info(SEPARATOR);
		for (Potion pot : Potion.REGISTRY) {
			try {
				ResourceLocation name = Objects.requireNonNull(pot.getRegistryName(), REGISTRY_NAME_IS_NULL);
				ExoplanetsMod.logger.info(String.format("%-30s %-40s", pot.getName(), name));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on potion: {} ***", pot);
				ExoplanetsMod.logger.catching(ex);
			}
		}
		ExoplanetsMod.logger.info(SEPARATOR);
	}

	public static void dumpRecipes() {
		ExoplanetsMod.logger.info(SEPARATOR);
		ExoplanetsMod.logger.info("REGISTERED RECIPES AS OF EXOPLANETS PRE-INIT");

		for (IRecipe rec : CraftingManager.REGISTRY) {
			try {
				ResourceLocation name = Objects.requireNonNull(rec.getRegistryName(), REGISTRY_NAME_IS_NULL);
				int id = CraftingManager.REGISTRY.getIDForObject(rec);
				if (id < 0)
					throw new IndexOutOfBoundsException("id < 0");
				ExoplanetsMod.logger.formatted_Info(String.format("%-6d %-40s", id, name));
			} catch (Exception ex) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on recipe: {} ***", rec);
				throw ex;
			}
		}

		ExoplanetsMod.logger.info(SEPARATOR);
	}
	
	public static void dumpSolarSystemData() {
		ExoplanetsMod.logger.info(SEPARATOR);
		ExoplanetsMod.logger.info("REGISTERED CELESTIALBODIES AS OF EXOPLANETS PRE-INIT");
		for (SolarSystem entry : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			try {
				String name = Objects.requireNonNull(entry.getUnlocalizedName(), REGISTRY_NAME_IS_NULL);
				String galaxy = Objects.requireNonNull(entry.getUnlocalizedParentGalaxyName(), REGISTRY_NAME_IS_NULL);
				Vector3 v3 = entry.getMapPosition();
				String pos = Objects.toString(v3.x + v3.y + v3.z, NO_MAP_POS);
				String mainStar = Objects.toString(entry.getMainStar().getUnlocalizedName(), NO_STAR);
				ExoplanetsMod.logger.bigDebug("SolarSystem: [ $s ] \n Galaxy: { $s } | Pos: ( $s )\n MainStar: $s", name, galaxy, pos, mainStar);
			} catch (Exception e) {
				ExoplanetsMod.logger.formatted_Warn("*** Error on CelestialBody: {} ***", entry);
			}
		}
		ExoplanetsMod.logger.info(SEPARATOR);
	}
	
}
