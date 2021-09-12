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
package com.readonlydev.common.block.terrain;

import com.readonlydev.common.block.BlockTerrain;

import net.minecraft.block.SoundType;
import net.minecraft.util.IStringSerializable;

public class BlockCrust extends BlockTerrain {
	
	private RockType type;
	
	public BlockCrust(RockType type) {
		super();
		this.type = type;
		this.setResistance(this.type.getResistance());
		this.setHardness(this.type.getHardness());
		this.setHarvestLevel("pickaxe", this.type.getLevel());
		this.setSoundType(SoundType.STONE);
	}
	
	public enum RockType implements IStringSerializable {
		
		SEDIMENTARY(1.0F, 2.0F, 1),
		METAMORPHIC(15.0F, 20.0F, 2),
		IGNEOUS(25.0F, 30.0F, 3);
		
		private final float hardness;
		private final float resistance;
		private final int level;
		
		RockType (float hardness, float resistance, int level) {
			this.hardness = hardness;
			this.resistance = resistance;
			this.level = level;
		}

		@Override
		public String getName() {
			return toString();
		}

		/** @return hardness */
		public float getHardness() {
			return hardness;
		}

		/** @return resistance */
		public float getResistance() {
			return resistance;
		}

		/** @return level */
		public int getLevel() {
			return level;
		}
		
	}

}
