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
package net.romvoid95.common.utility.logic.raytrace;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.util.math.Vec3d;
import net.romvoid95.common.utility.logic.Point;
import net.romvoid95.common.utility.logic.Vect;

public class RayTrace {
	/** Source of the ray trace. */
	protected Point src;
	/** Destination of the ray trace. */
	protected Point dest;
	/** Ray describing the ray trace. */
	protected Ray   ray;

	/**
	 * Instanciate a new {@link Raytrace}.
	 *
	 * @param ray the ray
	 */
	public RayTrace(Ray ray) {
		this.src = ray.origin;
		this.ray = ray;
	}

	/**
	 * Instantiate a new {@link Raytrace}.
	 *
	 * @param src the src
	 * @param dest the dest
	 */
	public RayTrace(Point src, Point dest) {
		this(new Ray(src, new Vect(src, dest)));
		this.dest = dest;
	}

	/**
	 * Instantiate a new {@link Raytrace}.
	 *
	 * @param src the src
	 * @param dest the dest
	 */
	public RayTrace(Vec3d src, Vec3d dest) {
		this(new Ray(src, dest));
		this.dest = new Point(dest);
	}

	/**
	 * Gets the source of this {@link Raytrace}
	 *
	 * @return the source
	 */
	public Point getSource () {
		return src;
	}

	/**
	 * Gets the destination of this {@link Raytrace}.
	 *
	 * @return the destination
	 */
	public Point getDestination () {
		return dest;
	}

	/**
	 * Gets the direction vector of the ray.
	 *
	 * @return the direction
	 */
	public Vect direction () {
		return ray.direction;
	}

	/**
	 * Gets the length of the ray.
	 *
	 * @return the distance
	 */
	public double distance () {
		return ray.direction.length();
	}

	/**
	 * Sets the length of this {@link Raytrace}.
	 *
	 * @param length the new length
	 */
	public void setLength (double length) {
		dest = ray.getPointAt(length);
	}

	public Pair<EnumFacing, Point> trace (AxisAlignedBB... aabbs) {
		if (ArrayUtils.isEmpty(aabbs))
			return null;

		List<Pair<EnumFacing, Point>> points  = new ArrayList<>();
		double                        maxDist = dest != null ? Point.distanceSquared(src, dest) : Double.MAX_VALUE;
		for (AxisAlignedBB aabb : aabbs) {
			if (aabb == null)
				continue;

			for (Pair<EnumFacing, Point> pair : ray.intersect(aabb)) {
				if (Point.distanceSquared(src, pair.getRight()) < maxDist)
					points.add(pair);
			}
		}

		if (points.size() == 0)
			return null;

		return getClosest(points);
	}

	/**
	 * Gets the closest {@link Point} of the origin.
	 *
	 * @param points the points
	 * @return the closest point
	 */
	private Pair<EnumFacing, Point> getClosest (List<Pair<EnumFacing, Point>> points) {
		double                  distance = Double.MAX_VALUE;
		Pair<EnumFacing, Point> ret      = null;
		for (Pair<EnumFacing, Point> pair : points) {
			double d = Point.distanceSquared(src, pair.getRight());
			if (distance > d) {
				distance = d;
				ret      = pair;
			}
		}

		return ret;
	}

	/**
	 * Gets the closest {@link RayTraceResult} to the source.
	 *
	 * @param src the src
	 * @param result1 the mop1
	 * @param result2 the mop2
	 * @return the closest
	 */
	public static RayTraceResult getClosestHit (RayTraceResult.Type hitType, Point src, RayTraceResult result1, RayTraceResult result2) {
		if (result1 == null)
			return result2;
		if (result2 == null)
			return result1;

		if (result1.typeOfHit == RayTraceResult.Type.MISS && result2.typeOfHit == hitType)
			return result2;
		if (result1.typeOfHit == hitType && result2.typeOfHit == RayTraceResult.Type.MISS)
			return result1;

		if (Point.distanceSquared(src, new Point(result1.hitVec)) > Point
				.distanceSquared(src, new Point(result2.hitVec)))
			return result2;
		return result1;
	}
}
