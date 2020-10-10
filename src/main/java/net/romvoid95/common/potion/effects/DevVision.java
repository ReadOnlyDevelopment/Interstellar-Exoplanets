package net.romvoid95.common.potion.effects;

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
