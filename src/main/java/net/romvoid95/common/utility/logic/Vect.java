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
package net.romvoid95.common.utility.logic;

import net.minecraft.util.math.Vec3d;

public class Vect {
	/** x coordinate of this {@link Vect}. */
	public double x;

	/** y coordinate of this {@link Vect}. */
	public double y;

	/** z coordinate of this {@link Vect}. */
	public double z;

	/**
	 * Instantiates a new Vect.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Vect(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Instantiates a new Vect.
	 *
	 * @param v the v
	 */
	public Vect(Vect v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/**
	 * Instantiates a new Vect.
	 *
	 * @param p the p
	 */
	public Vect(Point p) {
		x = p.x;
		y = p.y;
		z = p.z;
	}

	/**
	 * Instantiates a new Vect.
	 *
	 * @param p1 the p1
	 * @param p2 the p2
	 */
	public Vect(Point p1, Point p2) {
		x = p2.x - p1.x;
		y = p2.y - p1.y;
		z = p2.z - p1.z;
	}

	/**
	 * Instantiates a new Vect.
	 *
	 * @param vec the vec
	 */
	public Vect(Vec3d vec) {
		x = vec.x;
		y = vec.y;
		z = vec.z;
	}

	/**
	 * Sets the x, y and z coordinates for this {@link Vect}.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public void set (double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Gets the squared length of this {@link Vect}.
	 *
	 * @return the length squared
	 */
	public double lengthSquared () {
		return x * x + y * y + z * z;
	}

	/**
	 * Gets the squared length of this {@link Vect}.
	 *
	 * @return the length squared
	 */
	public double length () {
		return Math.sqrt(x * x + y * y + z * z);
	}

	/**
	 * Normalizes this {@link Vect}.
	 */
	public void normalize () {
		double d = length();
		x /= d;
		y /= d;
		z /= d;
	}

	/**
	 * Subtracts the given {@link Vect} from this <code>Vect</code>.
	 *
	 * @param v the Vect to subtract
	 */
	public void subtract (Vect v) {
		x = x - v.x;
		y = y - v.y;
		z = z - v.z;
	}

	/**
	 * Adds the given {@link Vect} from this <code>Vect</code>.
	 *
	 * @param v the Vect to add
	 */
	public void add (Vect v) {
		x += v.x;
		y += v.y;
		z += v.z;
	}

	/**
	 * Calculates the cross product of this {@link Vect} with a given <code>Vect</code>.
	 *
	 * @param v the Vect
	 */
	public void cross (Vect v) {
		x = (y * v.z) - (z * v.y);
		y = (z * v.x) - (x * v.z);
		z = (x * v.y) - (y * v.x);
	}

	/**
	 * Calculates the dot product of this {@link Vect} with a given <code>Vect</code>.
	 *
	 * @param v the Vect
	 * @return the dot product
	 */
	public double dot (Vect v) {
		return (x * v.x) + (y * v.y) + (z * v.z);
	}

	/**
	 * Calculates the dot product of this {@link Vect} with a given {@link Point}.
	 *
	 * @param p the point
	 * @return the dot product
	 */
	public double dot (Point p) {
		return (x * p.x) + (y * p.y) + (z * p.z);
	}

	/**
	 * Scales this {@link Vect} by a factor.
	 *
	 * @param factor the factor to scale
	 */
	public void scale (double factor) {
		x *= factor;
		y *= factor;
		z *= factor;
	}

	/**
	 * Inverses the Vect.
	 */
	public void negate () {
		x = -x;
		y = -y;
		z = -z;
	}

	@Override
	public String toString () {
		return "[" + x + ", " + y + ", " + z + "]";
	}
}
