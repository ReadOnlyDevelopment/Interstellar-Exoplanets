package net.rom.exoplanets.content.fluids;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public interface IFluid {

	@SideOnly(Side.CLIENT)
	public void registerModels();

}
