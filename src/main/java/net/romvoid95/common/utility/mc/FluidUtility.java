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
package net.romvoid95.common.utility.mc;

import javax.annotation.Nonnull;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.ModelBakery;
import net.minecraft.client.renderer.block.model.ModelResourceLocation;
import net.minecraft.client.renderer.block.statemap.StateMapperBase;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.ModelLoader;
import net.minecraftforge.fluids.*;

import micdoodle8.mods.galacticraft.api.client.IItemMeshDefinitionCustom;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class FluidUtility {

	public static @Nonnull ItemStack getBucket (@Nonnull Fluid fluid) {
		final FluidStack fluidStack = new FluidStack(fluid, Fluid.BUCKET_VOLUME);
		try {
			fluidStack.getFluid();
		}
		catch (NullPointerException e) {
			throw new RuntimeException("The fluid " + fluid + " (" + fluid.getUnlocalizedName()
					+ ") is registered in the FluidRegistry, but the FluidRegistry has no delegate for it. This is impossible.", e);
		}
		try {
			return FluidUtil.getFilledBucket(fluidStack);
		}
		catch (Exception e) {
			throw new RuntimeException("The fluid " + fluid + " (" + fluid.getUnlocalizedName()
					+ ") is registered in the FluidRegistry, but crashes when put into a bucket. This is a bug in the mod it belongs to.", e);
		}
	}

	public static void registerFluidVariant (String fluid, Block fluidBlock) {
		ModelResourceLocation location = new ModelResourceLocation(fluid, "fluid");
		Item                  item     = Item.getItemFromBlock(fluidBlock);
		ModelBakery.registerItemVariants(item, new ResourceLocation(fluid));
		ModelLoader.setCustomMeshDefinition(item, IItemMeshDefinitionCustom.create( (ItemStack stack) -> location));
		ModelLoader.setCustomStateMapper(fluidBlock, new StateMapperBase() {
			@Override
			protected ModelResourceLocation getModelResourceLocation (IBlockState state) {
				return location;
			}
		});

	}

}
