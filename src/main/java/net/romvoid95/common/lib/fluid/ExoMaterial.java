package net.romvoid95.common.lib.fluid;

import net.minecraft.block.BlockDynamicLiquid;
import net.minecraft.block.BlockLiquid;
import net.minecraft.block.BlockStaticLiquid;
import net.minecraft.block.material.MapColor;
import net.minecraft.block.material.MaterialLiquid;
import net.romvoid95.common.utility.JavaUtility;

public class ExoMaterial extends MaterialLiquid {
	private Class blockLiquidName        = BlockLiquid.class;
	private Class blockLiquidStaticName  = BlockStaticLiquid.class;
	private Class blockLiquidDynamicName = BlockDynamicLiquid.class;

	public ExoMaterial(MapColor color) {
		super(color);
		this.setNoPushMobility();
	}

	@Override
	public boolean blocksMovement () {
		return JavaUtility.INSTANCE.isCalledBy(blockLiquidStaticName, blockLiquidName, blockLiquidDynamicName);
	}
}
