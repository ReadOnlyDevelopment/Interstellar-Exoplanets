package net.rom.exoplanets.content.block.ore;

import net.minecraft.block.SoundType;
import net.minecraft.block.material.Material;
import net.rom.exoplanets.content.block.BlockGeneral;
import net.rom.exoplanets.internal.RecipeBuilder;
import net.rom.exoplanets.internal.inerf.ICustomModel;

public class DenseMeteoricIron extends BlockGeneral implements ICustomModel {


	public DenseMeteoricIron(Material materialIn) {
		super(materialIn);
		this.setSoundType(SoundType.ANVIL);
		// TODO: Auto-generated constructor stub
	}

	@Override
	public void registerModels() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addRecipes(RecipeBuilder recipes) {
		// TODO Auto-generated method stub
		
	}

}
