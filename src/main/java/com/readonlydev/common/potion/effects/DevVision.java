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
package com.readonlydev.common.potion.effects;

import java.util.ArrayList;
import java.util.List;

import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;

public class DevVision extends Potion {

	public DevVision () {
		super(false, -4502242);
		this.setPotionName("dev");
	}

	public static void setPotionName(Potion potion, final String potionName) {
		potion.setRegistryName("exoplanets", potionName);
		potion.setPotionName("effect." + potion.getRegistryName().toString());
	}

	@Override
	public boolean hasStatusIcon() {
		return false;
	}

	@Override
	public List<ItemStack> getCurativeItems() {
		ArrayList<ItemStack> ret = new ArrayList<>();
		return ret;
	}

	@Override
	public void performEffect(EntityLivingBase living, int amplifier) {
		if (!(living instanceof EntityPlayer) || living.world.isRemote) {
			return;
		}
		PotionEffect effect = new PotionEffect(Potion.getPotionById(16));
		living.addPotionEffect(effect);
	}
}
