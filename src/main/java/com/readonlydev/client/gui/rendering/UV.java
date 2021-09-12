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
package com.readonlydev.client.gui.rendering;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.Vec3d;

public class UV {

	public float u;

	public float v;

	public UV(float u, float v) {
		this.u = u;
		this.v = v;
	}

	public UV(EnumFacing facing, Vec3d vec3d) {
		switch (facing.getAxis()) {
		case X:
			this.u = Math.round(vec3d.z * 16);
			this.v = Math.round(vec3d.y * 16);
			break;
		case Y:
			this.u = Math.round(vec3d.x * 16);
			this.v = Math.round(vec3d.z * 16);
			break;
		case Z:
			this.u = Math.round(vec3d.x * 16);
			this.v = Math.round(vec3d.y * 16);
			break;
		}
	}

	public float getU () {
		return u;
	}

	public float getV () {
		return v;
	}
}