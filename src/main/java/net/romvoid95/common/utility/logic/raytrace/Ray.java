package net.romvoid95.common.utility.logic.raytrace;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.util.EnumFacing;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.Vec3d;
import net.romvoid95.common.utility.logic.Point;
import net.romvoid95.common.utility.logic.Vect;

public class Ray {
	/** Origin {@link Point} of this {@link Ray}. */
	public Point origin;
	/** Direction of this {@link Ray}. */
	public Vect  direction;

	/**
	 * Instantiates a new {@link Ray}.
	 *
	 * @param p the origin {@link Point}
	 * @param v the direction {@link Vect}
	 */
	public Ray(Point p, Vect v) {
		origin    = p;
		direction = v;
	}

	/**
	 * Instantiates a new {@link Ray} from a specified one.
	 *
	 * @param r the ray to copy
	 */
	public Ray(Ray r) {
		origin    = new Point(r.origin);
		direction = new Vect(r.direction);
	}

	/**
	 * Instantiates a new {@link Ray} from two {@link Vec3d}.
	 *
	 * @param src the src
	 * @param dest the dest
	 */
	public Ray(Vec3d src, Vec3d dest) {
		origin    = new Point(src);
		direction = new Vect(origin, new Point(dest));
	}

	/**
	 * Gets the {@link Point} at the specified distance.
	 *
	 * @param t the distance
	 * @return the point at the distance t
	 */
	public Point getPointAt (double t) {
		if (Double.isNaN(t))
			return null;
		return new Point(origin.x + t * direction.x, origin.y + t * direction.y, origin.z + t * direction.z);
	}

	/**
	 * Gets the distance to the plane at x.
	 *
	 * @param x the x plane
	 * @return the distance
	 */
	public double intersectX (double x) {
		if (direction.x == 0)
			return Double.NaN;
		return (x - origin.x) / direction.x;
	}

	/**
	 * Gets the distance to the plane at y.
	 *
	 * @param y the y plane
	 * @return the distance
	 */
	public double intersectY (double y) {
		if (direction.y == 0)
			return Double.NaN;
		return (y - origin.y) / direction.y;
	}

	/**
	 * Gets the distance to the plane at z.
	 *
	 * @param z the z plane
	 * @return the distance
	 */
	public double intersectZ (double z) {
		if (direction.z == 0)
			return Double.NaN;
		return (z - origin.z) / direction.z;
	}

	/**
	 * Finds the points intersecting the {@link AxisAlignedBB}
	 *
	 * @param aabb the aabb
	 * @return the list
	 */
	public List<Pair<EnumFacing, Point>> intersect (AxisAlignedBB aabb) {
		double ix     = intersectX(aabb.minX);
		double iX     = intersectX(aabb.maxX);
		double iy     = intersectY(aabb.minY);
		double iY     = intersectY(aabb.maxY);
		double iz     = intersectZ(aabb.minZ);
		double iZ     = intersectZ(aabb.maxZ);
		Point  interx = ix >= 0 ? getPointAt(ix) : null;
		Point  interX = iX >= 0 ? getPointAt(iX) : null;
		Point  intery = iy >= 0 ? getPointAt(iy) : null;
		Point  interY = iY >= 0 ? getPointAt(iY) : null;
		Point  interz = iz >= 0 ? getPointAt(iz) : null;
		Point  interZ = iZ >= 0 ? getPointAt(iZ) : null;

		List<Pair<EnumFacing, Point>> list = new ArrayList<>();
		if (interx != null && interx.isInside(aabb))
			list.add(Pair.of(EnumFacing.WEST, interx));
		if (interX != null && interX.isInside(aabb))
			list.add(Pair.of(EnumFacing.EAST, interX));

		if (intery != null && intery.isInside(aabb))
			list.add(Pair.of(EnumFacing.DOWN, intery));
		if (interY != null && interY.isInside(aabb))
			list.add(Pair.of(EnumFacing.UP, interY));

		if (interz != null && interz.isInside(aabb))
			list.add(Pair.of(EnumFacing.NORTH, interz));
		if (interZ != null && interZ.isInside(aabb))
			list.add(Pair.of(EnumFacing.SOUTH, interZ));

		return list;
	}

	@Override
	public String toString () {
		return "O:" + origin + " / D:" + direction;
	}

}
