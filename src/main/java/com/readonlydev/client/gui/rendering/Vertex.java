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

import net.minecraft.client.renderer.Tessellator;

public class Vertex {

	public static Vertex unitX = new Vertex(1, 0, 0);

	public static Vertex unitY = new Vertex(0, 1, 0);

	public static Vertex unitZ = new Vertex(0, 0, 1);

	public static Vertex unitNX = new Vertex(-1, 0, 0);

	public static Vertex unitNY = new Vertex(0, -1, 0);

	public static Vertex unitNZ = new Vertex(0, 0, -1);

	public static Vertex unitPYNZ = new Vertex(0, 0.707, -0.707);

	public static Vertex unitPXPY = new Vertex(0.707, 0.707, 0);

	public static Vertex unitPYPZ = new Vertex(0, 0.707, 0.707);

	public static Vertex unitNXPY = new Vertex(-0.707, 0.707, 0);

	public float x, y, z;

	/**
	 * Instantiates a new vertex.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vertex(float x, float y, float z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Instantiates a new vertex.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vertex(double x, double y, double z) {
		this((float) x, (float) y, (float) z);
	}

	/**
	 * Instantiates a new vertex.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vertex(int x, int y, int z) {
		this((float) x, (float) y, (float) z);
	}

	/**
	 * Normalize.
	 *
	 * @return the vertex
	 */
	public Vertex normalize () {
		float sq = (float) Math.sqrt(x * x + y * y + z * z);
		x = x / sq;
		y = y / sq;
		z = z / sq;
		return this;
	}

	/**
	 * Tessellate.
	 *
	 * @param tessellator the tessellator
	 * @return the vertex
	 */
	public Vertex tessellate (Tessellator tessellator) {
		return this.tessellateWithUV(tessellator, null);
	}

	/**
	 * Tessellate with UV.
	 *
	 * @param tessellator the tessellator
	 * @param uv the uv
	 * @return the vertex
	 */
	public Vertex tessellateWithUV (Tessellator tessellator, UV uv) {
		if (uv == null) {
			tessellator.getBuffer().pos(x, y, z);
		}
		else {
			tessellator.getBuffer().pos(x, y, z);
			tessellator.getBuffer().tex(uv.u, uv.v);
		}
		return this;
	}

	/**
	 * Adds the.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the vertex
	 */
	public Vertex add (double x, double y, double z) {
		return new Vertex(this.x + x, this.y + y, this.z + z);
	}

	/**
	 * Adds the.
	 *
	 * @param v the v
	 * @return the vertex
	 */
	public Vertex add (Vertex v) {
		return add(v.x, v.y, v.z);
	}

	/**
	 * Mul.
	 *
	 * @param c the c
	 * @return the vertex
	 */
	public Vertex mul (double c) {
		return new Vertex(c * x, c * y, c * z);
	}

	/**
	 * To string.
	 *
	 * @return the string
	 */
	@Override
	public String toString () {
		return String.format("Vertex(%s, %s, %s)", this.x, this.y, this.z);
	}
}