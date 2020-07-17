/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
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
package net.rom.exoplanets.world;

import java.util.Random;

import javax.annotation.Nonnull;

import micdoodle8.mods.galacticraft.core.GCItems;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.passive.EntityVillager;
import net.minecraft.init.Items;
import net.minecraft.item.ItemStack;
import net.minecraft.village.MerchantRecipe;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.gen.structure.MapGenStructureIO;
import net.minecraftforge.fml.common.registry.ForgeRegistries;
import net.minecraftforge.fml.common.registry.VillagerRegistry;
import net.rom.exoplanets.ExoInfo;

public class ExoVillagerHandler {
	private static final VillagerRegistry VILLAGER_REGISTRY = VillagerRegistry.instance();
	public static VillagerRegistry.VillagerProfession PROF_ASTROLOGIST;

	public static void initVillageAstronomerHouse() {
		VILLAGER_REGISTRY.registerVillageCreationHandler(new VillageAstronomerHouse.VillageManager());
		MapGenStructureIO.registerStructureComponent(VillageAstronomerHouse.class, ExoInfo.MODID + ":AstronomerHouse");
	}

	public static void initAstronomerVillagerTrades() {
		PROF_ASTROLOGIST = new VillagerRegistry.VillagerProfession(ExoInfo.MODID + ":astrologist", "exoplanets:textures/models/villager_astrologist.png",
				"exoplanets:textures/models/villager_astrologist_zombie.png");
		ForgeRegistries.VILLAGER_PROFESSIONS.register(PROF_ASTROLOGIST);

		VillagerRegistry.VillagerCareer career_astrologist = new VillagerRegistry.VillagerCareer(PROF_ASTROLOGIST, ExoInfo.MODID + ":astrologist");

		career_astrologist.addTrade(1, new EmeraldForItemstack(new ItemStack(GCItems.oxTankHeavy, 1, 0), new EntityVillager.PriceInfo(8, 16)), new ItemstackForEmerald(
				new ItemStack(GCItems.canister, 1, 1), new EntityVillager.PriceInfo(-10, -6)), new ItemstackForEmerald(new ItemStack(GCItems.emergencyKit, 1, 1),
						new EntityVillager.PriceInfo(-3, -1)));
	}

	private static class EmeraldForItemstack implements EntityVillager.ITradeList {
		public ItemStack buyingItem;
		public EntityVillager.PriceInfo buyAmounts;

		public EmeraldForItemstack(@Nonnull ItemStack item, @Nonnull EntityVillager.PriceInfo buyAmounts) {
			this.buyingItem = item;
			this.buyAmounts = buyAmounts;
		}

		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
			recipeList.add(new MerchantRecipe(copyStackWithAmount(this.buyingItem, this.buyAmounts.getPrice(random)), Items.EMERALD));
		}
	}

	private static class ItemstackForEmerald implements EntityVillager.ITradeList {
		public ItemStack sellingItem;
		public EntityVillager.PriceInfo priceInfo;

//		public ItemstackForEmerald(Item par1Item, EntityVillager.PriceInfo priceInfo) {
//			this.sellingItem = new ItemStack(par1Item);
//			this.priceInfo = priceInfo;
//		}

		public ItemstackForEmerald(ItemStack stack, EntityVillager.PriceInfo priceInfo) {
			this.sellingItem = stack;
			this.priceInfo = priceInfo;
		}

		@Override
		public void addMerchantRecipe(IMerchant merchant, MerchantRecipeList recipeList, Random random) {
			int i = 1;
			if (this.priceInfo != null)
				i = this.priceInfo.getPrice(random);
			ItemStack itemstack;
			ItemStack itemstack1;
			if (i < 0) {
				itemstack = new ItemStack(Items.EMERALD);
				itemstack1 = copyStackWithAmount(sellingItem, -i);
			} else {
				itemstack = new ItemStack(Items.EMERALD, i, 0);
				itemstack1 = copyStackWithAmount(sellingItem, 1);
			}
			recipeList.add(new MerchantRecipe(itemstack, itemstack1));
		}
	}

	public static ItemStack copyStackWithAmount(ItemStack stack, int amount) {
		if (stack.isEmpty())
			return ItemStack.EMPTY;
		ItemStack s2 = stack.copy();
		s2.setCount(amount);
		return s2;
	}
}
