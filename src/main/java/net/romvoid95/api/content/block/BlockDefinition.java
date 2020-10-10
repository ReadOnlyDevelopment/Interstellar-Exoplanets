package net.romvoid95.api.content.block;

import net.minecraft.block.Block;
import net.minecraft.item.ItemBlock;
import net.minecraft.util.ResourceLocation;

public class BlockDefinition {
	private String           modid;
	private Block            block;
	private ItemBlock        item;
	private ResourceLocation registryName;
	private boolean          noItemBlock = false;

	public BlockDefinition(String modid, Block block, ItemBlock item) {
		this.modid   = modid;
		this.block   = block;
		this.item    = item;
		registryName = block.getRegistryName();
	}

	public BlockDefinition(String modid, Block block) {
		this(modid, block, null);
	}

	public BlockDefinition(String modid, Block block, boolean noItemBlock) {
		this(modid, block, null);
		if (noItemBlock)
			this.noItemBlock = true;
	}

	public Block getBlock () {
		return block;
	}

	public BlockDefinition setBlock (Block block) {
		this.block = block;
		return this;
	}

	public ItemBlock getItem () {
		return item;
	}

	public BlockDefinition setItem (ItemBlock item) {
		this.item = item;
		return this;
	}

	public String getModid () {
		return modid;
	}

	public ResourceLocation getRegistryName () {
		return registryName;
	}

	public boolean hasItemBlock () {
		return !noItemBlock;
	}
}