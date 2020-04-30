package net.rom.exoplanets.internal.client;

import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.EnumActionResult;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickBlock;
import net.minecraftforge.event.entity.player.PlayerInteractEvent.LeftClickEmpty;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.rom.exoplanets.ExoplanetsMod;
import net.rom.exoplanets.internal.item.ILeftClickItem;
import net.rom.exoplanets.internal.network.MLeftClick;

public final class ClientEvents {
	@SubscribeEvent
	public void onLeftClickEmpty(LeftClickEmpty event) {

		ItemStack stack = event.getItemStack();
		if (!stack.isEmpty() && stack.getItem() instanceof ILeftClickItem) {
			// Client-side call
			ActionResult<ItemStack> result = ((ILeftClickItem) stack.getItem()).onItemLeftClick(event.getWorld(),
					event.getEntityPlayer(), event.getHand());
			// Server-side call
			if (result.getType() == EnumActionResult.SUCCESS) {
				ExoplanetsMod.network.wrapper.sendToServer(new MLeftClick(MLeftClick.Type.EMPTY, event.getHand()));
			}
		}
	}

	@SubscribeEvent
	public void onLeftClickBlock(LeftClickBlock event) {

		ItemStack stack = event.getItemStack();
		if (!stack.isEmpty() && stack.getItem() instanceof ILeftClickItem) {
			// Client-side call
			ActionResult<ItemStack> result = ((ILeftClickItem) stack.getItem()).onItemLeftClickBlock(event.getWorld(),
					event.getEntityPlayer(), event.getHand());
			// Server-side call
			if (result.getType() == EnumActionResult.SUCCESS) {
				ExoplanetsMod.network.wrapper.sendToServer(new MLeftClick(MLeftClick.Type.BLOCK, event.getHand()));
			}
		}
	}
}
