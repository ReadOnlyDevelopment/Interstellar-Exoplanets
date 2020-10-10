package net.romvoid95.common.lib.fluid;

import net.minecraft.block.*;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;

import net.romvoid95.common.utility.java.JavaUtility;

public class ExoMaterial extends MaterialLiquid {
	private Class<BlockLiquid> blockLiquidName        = BlockLiquid.class;
	private Class<BlockStaticLiquid> blockLiquidStaticName  = BlockStaticLiquid.class;
	private Class<BlockDynamicLiquid> blockLiquidDynamicName = BlockDynamicLiquid.class;

	public ExoMaterial(MapColor color) {
		super(color);
		this.setNoPushMobility();
	}

	@Override
	public boolean blocksMovement () {
		return JavaUtility.INSTANCE.isCalledBy(blockLiquidStaticName, blockLiquidName, blockLiquidDynamicName);
	}
}
