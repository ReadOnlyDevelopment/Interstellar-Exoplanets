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
