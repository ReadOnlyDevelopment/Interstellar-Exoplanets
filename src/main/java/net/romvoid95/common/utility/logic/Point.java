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

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;

public class Point {

	/** x coordinate of this {@link Point}. */
	public double x;

	/** y coordinate of this {@link Point}. */
	public double y;

	/** z coordinate of this {@link Point}. */
	public double z;

	/**
	 * Instantiates a new point.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public Point(double x, double y, double z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}

	/**
	 * Instantiates a new point.
	 *
	 * @param p the p
	 */
	public Point(Point p) {
		x = p.x;
		y = p.y;
		z = p.z;
	}

	/**
	 * Instantiates a new point.
	 *
	 * @param v the v
	 */
	public Point(Vec3d v) {
		x = v.x;
		y = v.y;
		z = v.z;
	}

	/**
	 * Sets this point to x, y and z coordinates.
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
	 * Moves this {@link Point} according to {@link Vect}.
	 *
	 * @param v the v
	 * @return the point
	 */
	public Point add (Vect v) {
		x += v.x;
		y += v.y;
		z += v.z;
		return this;
	}

	/**
	 * Adds the x, y and z coordinates to this {@link Point}.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 * @return the point
	 */
	public Point add (double x, double y, double z) {
		this.x += x;
		this.y += y;
		this.z += z;
		return this;
	}

	/**
	 * Checks if this {@link Point} is inside the {@link AxisAlignedBB}.
	 *
	 * @param aabb the aabb
	 * @return true, if it is inside
	 */
	public boolean isInside (AxisAlignedBB aabb) {
		return x >= aabb.minX && x <= aabb.maxX && y >= aabb.minY && y <= aabb.maxY && z >= aabb.minZ && z <= aabb.maxZ;
	}

	/**
	 * Creates a {@link Vec3d} from this {@link Point} coordinates.
	 *
	 * @return the Vec3d
	 */
	public Vec3d toVec3d () {
		return new Vec3d(x, y, z);
	}

	/**
	 * Checks if this {@link Point} is equal to the specified one.
	 *
	 * @param p the point to check
	 * @return true, if equal, false otherwise
	 */
	public boolean equals (Point p) {
		if (p == null)
			return false;
		return ((x == p.x) && (y == p.y) && (z == p.z));
	}

	@Override
	public String toString () {
		return "[" + x + ", " + y + ", " + z + "]";
	}

	/**
	 * Calculates the squared distance between two {@link Point points}.
	 *
	 * @param p1 fist point
	 * @param p2 second point
	 * @return the distance squared
	 */
	public static double distanceSquared (Point p1, Point p2) {
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		double z = p2.z - p1.z;
		return x * x + y * y + z * z;
	}

	/**
	 * Calculates the distance between two {@link Point points}.
	 *
	 * @param p1 fist point
	 * @param p2 second point
	 * @return the distance
	 */
	public static double distance (Point p1, Point p2) {
		double x = p2.x - p1.x;
		double y = p2.y - p1.y;
		double z = p2.z - p1.z;
		return Math.sqrt(x * x + y * y + z * z);
	}
}
