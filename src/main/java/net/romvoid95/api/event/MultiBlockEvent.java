package net.romvoid95.api.event;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.fml.common.eventhandler.Cancelable;

import net.romvoid95.api.content.multiblock.IMultiBlock;

@Cancelable
public class MultiBlockEvent extends PlayerEvent {
	private final IMultiBlock multiblock;
	private final BlockPos    clickedBlock;
	private final ItemStack   hammer;

	public MultiBlockEvent(EntityPlayer player, IMultiBlock multiblock, BlockPos clickedBlock, ItemStack hammer) {
		super(player);
		this.multiblock   = multiblock;
		this.clickedBlock = clickedBlock;
		this.hammer       = hammer;
	}

	public IMultiBlock getMultiblock () {
		return multiblock;
	}

	public BlockPos getClickedBlock () {
		return clickedBlock;
	}

	public ItemStack getHammer () {
		return hammer;
	}

	/**
	 * This event is fired BEFORE the multiblock is attempted to be formed.<br>
	 * No checks of the structure have been made. The event simply exists to cancel the formation of the multiblock before it ever happens.
	 */
	public static class Pre extends MultiBlockEvent {
		public Pre(EntityPlayer player, IMultiBlock multiblock, BlockPos clickedBlock, ItemStack hammer) {
			super(player, multiblock, clickedBlock, hammer);
		}
	}

	/**
	 * This event is fired AFTER the multiblock is attempted to be formed.<br>
	 * The structure has been checked positively, but not replaced with the Multiblock.
	 */
	public static class Post extends MultiBlockEvent {
		public Post(EntityPlayer player, IMultiBlock multiblock, BlockPos clickedBlock, ItemStack hammer) {
			super(player, multiblock, clickedBlock, hammer);
		}
	}

}
