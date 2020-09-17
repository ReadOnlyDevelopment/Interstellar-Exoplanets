package net.romvoid95.api.multiblock;

import java.util.ArrayList;

import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraftforge.common.MinecraftForge;
import net.romvoid95.api.event.MultiBlockEvent;

public class MultiBlockHandler {

	static ArrayList<IMultiBlock> multiblocks = new ArrayList<IMultiBlock>();

	public static void registerMultiblock (IMultiBlock multiblock) {
		multiblocks.add(multiblock);
	}

	public static ArrayList<IMultiBlock> getMultiblocks () {
		return multiblocks;
	}

	public static MultiBlockEvent fireMultiblockFormationEventPre (EntityPlayer player, IMultiBlock multiblock, BlockPos clickedBlock, ItemStack hammer) {
		MultiBlockEvent event = new MultiBlockEvent.Pre(player, multiblock, clickedBlock, hammer);
		MinecraftForge.EVENT_BUS.post(event);
		return event;
	}

	/**
	 * Only fire this if the multiblock structure has been checked positive
	 */
	public static MultiBlockEvent fireMultiblockFormationEventPost (EntityPlayer player, IMultiBlock multiblock, BlockPos clickedBlock, ItemStack hammer) {
		MultiBlockEvent event = new MultiBlockEvent.Post(player, multiblock, clickedBlock, hammer);
		MinecraftForge.EVENT_BUS.post(event);
		return event;
	}

}
