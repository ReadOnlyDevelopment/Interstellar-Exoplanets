package net.romvoid95.common.utility.mc;

import java.util.*;

import net.minecraft.entity.Entity;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.*;
import net.minecraft.world.chunk.Chunk;

import lombok.experimental.UtilityClass;

/**
 * The Class Worlds.
 */
@SuppressWarnings("all")
@UtilityClass
public class WorldUtils {
	/**
	 * Create an explosion in the specified world, at the specified coordinates,
	 * with the specified effects.
	 * 
	 * @param entity - The entity that triggered the explosion.
	 * @param world - The world that the explosion should be created in.
	 * @param data - The CoordData containing the coordinates to create an explosion
	 *        at.
	 * @param strength - The strength of the explosion
	 * @param isFlaming - Set to true if the explosion causes surrounding blocks to
	 *        catch on fire.
	 * @param isSmoking - Set to true if the explosion emits smoke particles.
	 * @param doesBlockDamage - Set to true if the explosion does physical Block
	 *        damage.
	 * @return Return the instance of the explosion that was just created.
	 */
	public static Explosion createExplosion (Entity entity, World world, BlockPos data, float strength, boolean isFlaming, boolean isSmoking, boolean doesBlockDamage) {
		Explosion explosion = new Explosion(world, entity, data.getX(), data.getY(), data
				.getZ(), strength, isFlaming, isSmoking);

		if (doesBlockDamage) {
			explosion.doExplosionA();
		}

		explosion.doExplosionB(true);

		return explosion;
	}

	/**
	 * Can see sky.
	 *
	 * @param pos the pos
	 * @param world the world
	 * @return true, if successful
	 */
	public static boolean canSeeSky (BlockPos pos, World world) {
		for (int y = (int) pos.getY(); y < world.getHeight(); y++) {
			BlockPos position = new BlockPos(pos.getX(), y + 1, pos.getZ());

			if (world.getBlockState(position) != net.minecraft.init.Blocks.AIR) {
				return false;
			}
		}

		return true;
	}

	/**
	 * Get the light intensity as an Integer at the specified coordinates in the
	 * specified world.
	 *
	 * @param world - World to check for brightness values in.
	 * @param pos the pos
	 * @return Returns light intensity of a block as an Integer.
	 */
	public static int getLightAtCoord (World world, BlockPos pos) {
		int sky   = world.getLightFor(EnumSkyBlock.BLOCK, pos);
		int block = world.getLightFor(EnumSkyBlock.SKY, pos) - world.calculateSkylightSubtracted(0F);

		return Math.max(block, sky);
	}

	/**
	 * Rand pos.
	 *
	 * @param seed the seed
	 * @param pos the pos
	 * @param width the width
	 * @param height the height
	 * @return the block pos
	 */
	public static BlockPos randPos (Random seed, BlockPos pos, int width, int height) {
		return new BlockPos(pos.getX() + seed.nextInt(width), pos.getY() + seed.nextInt(height), pos.getZ()
				+ seed.nextInt(width));
	}

	/**
	 * Rand chunk pos.
	 *
	 * @param seed the seed
	 * @param pos the pos
	 * @return the block pos
	 */
	public static BlockPos randChunkPos (Random seed, BlockPos pos) {
		return randPos(seed, pos, 16, 128);
	}

	/**
	 * Gets the entity by UUID.
	 *
	 * @param world the world
	 * @param uuid the uuid
	 * @return the entity by UUID
	 */
	public static Entity getEntityByUUID (World world, UUID uuid) {
		for (Object o : world.loadedEntityList.toArray()) {
			if (o instanceof Entity) {
				Entity entity = (Entity) o;

				if (entity.getUniqueID().equals(uuid)) {
					return entity;
				}
			}
		}

		return null;
	}

	/**
	 * Uuid from NBT.
	 *
	 * @param nbt the nbt
	 * @param key the key
	 * @return the uuid
	 */
	public static UUID uuidFromNBT (NBTTagCompound nbt, String key) {
		return uuidFromSignature(nbt.getString(key));
	}

	/**
	 * Uuid from signature.
	 *
	 * @param signature the signature
	 * @return the uuid
	 */
	public static UUID uuidFromSignature (String signature) {
		if (signature != null && signature
				.matches("[0-9a-fA-F]{8}-[0-9a-fA-F]{4}-[34][0-9a-fA-F]{3}-[89ab][0-9a-fA-F]{3}-[0-9a-fA-F]{12}")) {
			return UUID.fromString(signature);
		}

		return null;
	}

	/**
	 * Checks if is the {@link Chunk} is loaded for the specified {@link BlockPos}.
	 *
	 * @param world the world
	 * @param pos the pos
	 * @return true, if is chunk loaded
	 */
	public static boolean isChunkLoaded (World world, BlockPos pos) {
		return getLoadedChunk(world, pos).isPresent();
	}

	/**
	 * Gets an {@link Optional} for the loaded {@link Chunk} at the specified {@link BlockPos}.
	 *
	 * @param world the world
	 * @param pos the pos
	 * @return the loaded chunk
	 */
	public static Optional<Chunk> getLoadedChunk (World world, BlockPos pos) {
		if (world.getChunkProvider() == null)
			return Optional.empty();

		return Optional.ofNullable(world.getChunkProvider().getLoadedChunk(pos.getX() >> 4, pos.getZ() >> 4));
	}
}
