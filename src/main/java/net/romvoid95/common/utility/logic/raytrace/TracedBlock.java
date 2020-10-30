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

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import net.romvoid95.common.utility.logic.Point;
import net.romvoid95.common.utility.logic.Vect;

@SuppressWarnings("unused")
public class TracedBlock extends RayTrace {
	/** World reference **/
	private World       world;
	/** Position of the block being ray traced **/
	private BlockPos    pos;
	/** Block being ray traced. */
	private IBlockState state;

	/**
	 * Sets the parameters for this {@link RaytraceBlock}.
	 *
	 * @param world the world
	 * @param ray the ray
	 * @param pos the pos
	 */
	public TracedBlock(World world, Ray ray, BlockPos pos) {
		super(ray);
		this.world = world;
		this.pos   = pos;
		this.state = world.getBlockState(pos);
	}

	/**
	 * Sets the parameters for this {@link RaytraceBlock}.
	 *
	 * @param world the world
	 * @param src the src
	 * @param v the v
	 * @param pos the pos
	 */
	public TracedBlock(World world, Point src, Vect v, BlockPos pos) {
		this(world, new Ray(src, v), pos);
	}

	/**
	 * Sets the parameters for this {@link RaytraceBlock}.
	 *
	 * @param world the world
	 * @param src the src
	 * @param dest the dest
	 * @param pos the pos
	 */
	public TracedBlock(World world, Point src, Point dest, BlockPos pos) {
		this(world, new Ray(src, new Vect(src, dest)), pos);
		this.dest = dest;
	}

	/**
	 * Sets the parameters for this {@link RaytraceBlock}.
	 *
	 * @param world the world
	 * @param src the src
	 * @param dest the dest
	 * @param pos the pos
	 */
	public TracedBlock(World world, Vec3d src, Vec3d dest, BlockPos pos) {
		this(world, new Ray(src, dest), pos);
		this.dest = new Point(dest);
	}

}
