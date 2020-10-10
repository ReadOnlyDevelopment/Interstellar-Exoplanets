package net.romvoid95.common.utility.mc;

import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import lombok.experimental.UtilityClass;
import net.romvoid95.common.utility.logic.Point;

@UtilityClass
public class BlockPosUtil {
	/**
	 * Rotates the {@link BlockPos} around the Y axis around the origin (0,0,0).
	 *
	 * @param pos the pos
	 * @param rotation the rotation
	 * @return the block pos
	 */
	public static BlockPos rotate (BlockPos pos, int rotation) {
		int[] cos = {
			1, 0, -1, 0
		};
		int[] sin = {
			0, 1, 0, -1
		};

		int a = -rotation & 3;

		int newX = (pos.getX() * cos[a]) - (pos.getZ() * sin[a]);
		int newZ = (pos.getX() * sin[a]) + (pos.getZ() * cos[a]);

		return new BlockPos(newX, pos.getY(), newZ);
	}

	/**
	 * Converts the {@link BlockPos} to its position relative to the chunk it's in.
	 *
	 * @param pos the pos
	 * @return the block pos
	 */
	public static BlockPos chunkPosition (BlockPos pos) {
		return new BlockPos(pos.getX() - (pos.getX() >> 4) * 16, pos.getY() - (pos.getY() >> 4) * 16, pos.getZ()
				- (pos.getZ() >> 4) * 16);
	}

	/**
	 * Gets an iterable iterating through all the {@link BlockPos} intersecting the passed {@link AxisAlignedBB}.
	 *
	 * @param aabb the aabb
	 * @return the all in box
	 */
	public static Iterable<BlockPos> getAllInBox (AxisAlignedBB aabb) {
		return BlockPos.getAllInBox(new BlockPos(aabb.minX, aabb.minY, aabb.minZ), new BlockPos(Math.ceil(aabb.maxX)
				- 1, Math.ceil(aabb.maxY) - 1, Math.ceil(aabb.maxZ) - 1));
	}

	public static ByteBuf toBytes (BlockPos pos) {
		ByteBuf buf = Unpooled.buffer(8);
		buf.writeLong(pos.toLong());
		return buf;
	}

	public static BlockPos fromBytes (ByteBuf buf) {
		return BlockPos.fromLong(buf.readLong());
	}

	/**
	 * Compares the distance of the passed {@link BlockPos} to the origin (0,0,0).
	 *
	 * @param pos1 the pos 1
	 * @param pos2 the pos 2
	 * @return the int
	 */
	public static int compare (BlockPos pos1, BlockPos pos2) {
		return compare(new Point(0, 0, 0), pos1, pos2);
	}

	/**
	 * Compares the distance of the passed {@link BlockPos} to the <i>offset</i> {@link Point}.
	 *
	 * @param offset the offset
	 * @param pos1 the pos 1
	 * @param pos2 the pos 2
	 * @return the int
	 */
	public static int compare (Point offset, BlockPos pos1, BlockPos pos2) {
		if (pos1.equals(pos2))
			return 0;

		return Double
				.compare(pos1.distanceSq(offset.x, offset.y, offset.z), pos2.distanceSq(offset.x, offset.y, offset.z));
	}
}
