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

package com.readonlydev.common.entity;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import com.readonlydev.core.registries.ExoplanetItems;

import micdoodle8.mods.galacticraft.api.prefab.entity.EntityAutoRocket;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntitySpaceshipBase;
import micdoodle8.mods.galacticraft.api.prefab.entity.EntityTieredRocket;
import micdoodle8.mods.galacticraft.api.tile.IFuelDock;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3;
import micdoodle8.mods.galacticraft.api.vector.BlockVec3Dim;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.advancement.GCTriggers;
import micdoodle8.mods.galacticraft.core.entities.player.GCPlayerStats;
import micdoodle8.mods.galacticraft.core.network.PacketDynamic;
import micdoodle8.mods.galacticraft.core.tile.TileEntityLandingPad;
import micdoodle8.mods.galacticraft.core.tile.TileEntityTelemetry;
import micdoodle8.mods.galacticraft.core.util.CompatibilityManager;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.RedstoneUtil;
import net.minecraft.client.audio.ISound;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.entity.MoverType;
import net.minecraft.entity.player.EntityPlayerMP;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.ClassInheritanceMultiMap;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.RayTraceResult;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraftforge.fml.client.FMLClientHandler;

public class EntityTwoPlayerRocket extends EntityTieredRocket {

	public EntityTwoPlayerRocket(World par1World) {
		super(par1World);
		this.setSize(1.2F, 6.5F);
	}

	public EntityTwoPlayerRocket(World world, double posX, double posY, double posZ) {
		super(world, posX, posY, posZ);
		this.setSize(1.2F, 6.5F);
	}

	public EntityTwoPlayerRocket(World par1World, double par2, double par4, double par6, EnumRocketType rocketType) {
		super(par1World, par2, par4, par6);
		this.rocketType = rocketType;
		this.setSize(1.2F, 6.5F);
		this.stacks = NonNullList.withSize(this.getSizeInventory(), ItemStack.EMPTY);
	}

	public EntityTwoPlayerRocket(World par1World, double par2, double par4, double par6, boolean reversed, EnumRocketType rocketType, NonNullList<ItemStack> inv) {
		this(par1World, par2, par4, par6, rocketType);
		this.stacks = inv;
	}

	@Override
	protected void addPassenger(Entity p_addPassenger_1_) {
		super.addPassenger(p_addPassenger_1_);
	}

	@Override
	protected boolean canFitPassenger(Entity p_canFitPassenger_1_) {
		return this.getPassengers().size() <= 1;
	}

	@Override
	public double getYOffset() {
		return 1.5F;
	}

	@Override
	public ItemStack getPickedResult(RayTraceResult target) {
		return new ItemStack(ExoplanetItems.TWOPERSONROCKET, 1, this.rocketType.getIndex());
	}

	@Override
	protected void entityInit() {
		super.entityInit();
	}

	@Override
	public double getMountedYOffset() {
		return 0.75D;
	}

	@Override
	public float getRotateOffset() {
		return 2.25F;
	}

	@Override
	public double getOnPadYOffset() {
		return 0.0D;
	}

	@Override
	public void onUpdate() {
//////////////////////////
		if (this.world.isRemote && this.addedToChunk && !CompatibilityManager.isCubicChunksLoaded) {
			Chunk chunk = this.world.getChunkFromChunkCoords(this.chunkCoordX, this.chunkCoordZ);
			int cx = MathHelper.floor(this.posX) >> 4;
			int cz = MathHelper.floor(this.posZ) >> 4;
			if (chunk.isLoaded() && this.chunkCoordX == cx && this.chunkCoordZ == cz) {
				boolean thisfound = false;
				ClassInheritanceMultiMap<Entity> mapEntities = chunk.getEntityLists()[this.chunkCoordY];
				for (Entity ent : mapEntities) {
					if (ent == this) {
						thisfound = true;
						break;
					}
				}
				if (!thisfound) {
					chunk.addEntity(this);
				}
			}
		}

		if (this.launchPhase == EnumLaunchPhase.LANDING.ordinal() && this.hasValidFuel()) {
			if (this.targetVec != null) {
				double yDiff = this.posY - this.getOnPadYOffset() - this.targetVec.getY();
				this.motionY = Math.max(-2.0, (yDiff - 0.04) / -55.0);

				// Some lateral motion in case not exactly on target (happens if rocket was
				// moving laterally during launch)
				double diff = this.posX - this.targetVec.getX() - 0.5D;
				double motX, motZ;
				if (diff > 0D) {
					motX = Math.max(-0.1, diff / -100.0D);
				} else if (diff < 0D) {
					motX = Math.min(0.1, diff / -100.0D);
				} else
					motX = 0D;
				diff = this.posZ - this.targetVec.getZ() - 0.5D;
				if (diff > 0D) {
					motZ = Math.max(-0.1, diff / -100.0D);
				} else if (diff < 0D) {
					motZ = Math.min(0.1, diff / -100.0D);
				} else
					motZ = 0D;
				if (motZ != 0D || motX != 0D) {
					double angleYaw = Math.atan(motZ / motX);
					double signed = motX < 0 ? 50D : -50D;
					double anglePitch = Math.atan(Math.sqrt(motZ * motZ + motX * motX) / signed) * 100D;
					this.rotationYaw = (float) angleYaw * Constants.RADIANS_TO_DEGREES;
					this.rotationPitch = (float) anglePitch * Constants.RADIANS_TO_DEGREES;
				} else
					this.rotationPitch = 0F;

				if (yDiff > 1D && yDiff < 4D) {
					for (Object o : this.world.getEntitiesInAABBexcluding(this, this.getEntityBoundingBox().offset(0D, -3D, 0D), EntitySpaceshipBase.rocketSelector)) {
						if (o instanceof EntitySpaceshipBase) {
							((EntitySpaceshipBase) o).dropShipAsItem();
							((EntitySpaceshipBase) o).setDead();
						}
					}
				}
				if (yDiff < 0.4) {
					int yMin = MathHelper.floor(this.getEntityBoundingBox().minY - this.getOnPadYOffset() - 0.45D) - 2;
					int yMax = MathHelper.floor(this.getEntityBoundingBox().maxY) + 1;
					int zMin = MathHelper.floor(this.posZ) - 1;
					int zMax = MathHelper.floor(this.posZ) + 1;
					for (int x = MathHelper.floor(this.posX) - 1; x <= MathHelper.floor(this.posX) + 1; x++) {
						for (int z = zMin; z <= zMax; z++) {
							// Doing y as the inner loop may help with cacheing of chunks
							for (int y = yMin; y <= yMax; y++) {
								if (this.world.getTileEntity(new BlockPos(x, y, z)) instanceof IFuelDock) {
									// Land the rocket on the pad found
									this.rotationPitch = 0F;
									this.failRocket();
								}
							}
						}
					}
				}
			}
		}

		///////////////////////////////////////////

		///////////////
		if (!this.world.isRemote) {
			this.setFlag(6, this.isGlowing());
		}

		this.onEntityUpdate();
		////////////////
		this.ticks++;

		super.onUpdate();

		if (this.getAddToTelemetry()) {
			this.setAddTotele(false);
			for (BlockVec3Dim vec : new ArrayList<>(this.getTeleList())) {
				TileEntity t1 = vec.getTileEntityNoLoad();
				if (t1 instanceof TileEntityTelemetry && !t1.isInvalid()) {
					if (((TileEntityTelemetry) t1).linkedEntity == this)
						((TileEntityTelemetry) t1).addTrackedEntity(this);
				}
			}
		}

		for (Entity e : this.getPassengers()) {
			e.fallDistance = 0.0F;
		}

		if (this.posY > (this.world.provider instanceof IExitHeight ? ((IExitHeight) this.world.provider).getYCoordinateToTeleport() : 1200)
				&& this.launchPhase != EnumLaunchPhase.LANDING.ordinal()) {
			this.onReachAtmosphere();
//            if (this.world.isRemote)
//            	this.posY = 1 + (this.world.provider instanceof IExitHeight ? ((IExitHeight) this.world.provider).getYCoordinateToTeleport() : 1200);
		}

		if (this.rollAmplitude > 0) {
			this.rollAmplitude--;
		}

		if (this.shipDamage > 0) {
			this.shipDamage--;
		}

		if (!this.world.isRemote) {
			if (this.posY < 0.0D) {
				this.setDead();
			} else if (this.posY > (this.world.provider instanceof IExitHeight ? ((IExitHeight) this.world.provider).getYCoordinateToTeleport() : 1200)
					+ (this.launchPhase == EnumLaunchPhase.LANDING.ordinal() ? 355 : 100)) {
				boolean allGood = true;
				for (Entity e : this.getPassengers()) {
					if (e instanceof EntityPlayerMP) {
						allGood = false;
						break;
					}
				}
				if (!allGood) {
					allGood = true;
					for (Entity e : this.getPassengers()) {
						if (e instanceof EntityPlayerMP) {
							GCPlayerStats stats = GCPlayerStats.get(e);
							if (stats.isUsingPlanetSelectionGui()) {
								this.setDead();
							} else {
								allGood = false;
							}
						}
					}
					if (allGood)
						this.setDead();
				} else {
					this.setDead();
				}
			}

			if (this.timeSinceLaunch > 50 && this.onGround) {
				this.failRocket();
			}
		}

		if (this.launchPhase == EnumLaunchPhase.UNIGNITED.ordinal()) {
			this.timeUntilLaunch = this.getPreLaunchWait();
		}

		if (this.launchPhase >= EnumLaunchPhase.LAUNCHED.ordinal()) {
			this.timeSinceLaunch++;
		} else {
			this.timeSinceLaunch = 0;
		}

		if (this.timeUntilLaunch > 0 && this.launchPhase == EnumLaunchPhase.IGNITED.ordinal()) {
			this.timeUntilLaunch--;
		}

		AxisAlignedBB box = this.getEntityBoundingBox().grow(0.2D);

		final List<Entity> var15 = this.world.getEntitiesWithinAABBExcludingEntity(this, box);

		if (!var15.isEmpty()) {
			for (Entity o : var15) {

				if (this.getPassengers().contains(o)) {
					o.applyEntityCollision(this);
				}
			}
		}

		if (this.timeUntilLaunch == 0 && this.launchPhase == EnumLaunchPhase.IGNITED.ordinal()) {
			this.setLaunchPhase(EnumLaunchPhase.LAUNCHED);
			this.onLaunch();
		}

		if (this.rotationPitch > 90) {
			this.rotationPitch = 90;
		}

		if (this.rotationPitch < -90) {
			this.rotationPitch = -90;
		}

		this.motionX = -(50 * Math.cos(this.rotationYaw / Constants.RADIANS_TO_DEGREES_D) * Math.sin(this.rotationPitch * 0.01 / Constants.RADIANS_TO_DEGREES_D));
		this.motionZ = -(50 * Math.sin(this.rotationYaw / Constants.RADIANS_TO_DEGREES_D) * Math.sin(this.rotationPitch * 0.01 / Constants.RADIANS_TO_DEGREES_D));

		if (this.launchPhase < EnumLaunchPhase.LAUNCHED.ordinal()) {
			this.motionX = this.motionY = this.motionZ = 0.0F;
		}

		if (this.world.isRemote) {
			this.setPosition(this.posX, this.posY, this.posZ);

			if (this.shouldMoveClientSide()) {
				this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
			}
		} else {
			this.move(MoverType.SELF, this.motionX, this.motionY, this.motionZ);
		}

		this.setRotation(this.rotationYaw, this.rotationPitch);

		if (this.world.isRemote) {
			this.setPosition(this.posX, this.posY, this.posZ);
		}

		this.prevPosX = this.posX;
		this.prevPosY = this.posY;
		this.prevPosZ = this.posZ;

		if (!this.world.isRemote && this.ticks % 3 == 0) {
			GalacticraftCore.packetPipeline.sendToDimension(new PacketDynamic(this), this.world.provider.getDimension());
			// PacketDispatcher.sendPacketToAllInDimension(GCCorePacketManager.getPacket(GalacticraftCore.CHANNELENTITIES,
			// this, this.getNetworkedData(new ArrayList())),
			// this.world.provider.getDimension());
		}

		if (this.launchPhase >= EnumLaunchPhase.LAUNCHED.ordinal()) {
			if (getPassengers().size() >= 2) // When the screen changes to the map, the player is not riding the rocket
												// anymore.
			{
				for (Entity entity : getPassengers()) {
					if (entity instanceof EntityPlayerMP) {
						GCTriggers.LAUNCH_ROCKET.trigger(((EntityPlayerMP) getPassengers().get(0)));
					}

				}
			}
		}
/////////////////////////////////////
		if (!this.world.isRemote) {
			if (this.statusMessageCooldown > 0) {
				this.statusMessageCooldown--;
			}

			if (this.statusMessageCooldown == 0 && this.lastStatusMessageCooldown > 0 && this.statusValid) {
				this.autoLaunch_r();
			}

			if (this.autoLaunchCountdown > 0 && !this.getPassengers().isEmpty()) {
				if (--this.autoLaunchCountdown <= 0) {
					this.autoLaunch_r();
				}
			}

			if (this.autoLaunchSetting == EnumAutoLaunch.ROCKET_IS_FUELED && this.fuelTank.getFluidAmount() == this.fuelTank.getCapacity() && !this.getPassengers().isEmpty()) {
				this.autoLaunch_r();
			}

			if (this.autoLaunchSetting == EnumAutoLaunch.INSTANT) {
				if (this.autoLaunchCountdown == 0 && !this.getPassengers().isEmpty()) {
					this.autoLaunch_r();
				}
			}

			if (this.autoLaunchSetting == EnumAutoLaunch.REDSTONE_SIGNAL) {
				if (this.ticks % 11 == 0 && this.getActiveLaunchController() != null) {
					if (RedstoneUtil.isBlockReceivingRedstone(this.world, this.getActiveLaunchController().toBlockPos())) {
						this.autoLaunch_r();
					}
				}
			}

			if (this.launchPhase >= EnumLaunchPhase.LAUNCHED.ordinal()) {
				this.setPad(null);
			} else {
				if (this.launchPhase == EnumLaunchPhase.UNIGNITED.ordinal() && this.getLandingPad() != null && this.ticks % 17 == 0) {
					this.updateControllerSettings(this.getLandingPad());
				}
			}

			this.lastStatusMessageCooldown = this.statusMessageCooldown;
		}

		if (this.launchPhase >= EnumLaunchPhase.IGNITED.ordinal()) {
			if (this.rocketSoundUpdater != null) {
				this.rocketSoundUpdater.update();
				this.setRocketSoundToStop(true);
			}
		} else {
			if (this.getRocketSoundToStop()) {
				this.stopRocketSound();
				if (this.rocketSoundUpdater != null) {
					FMLClientHandler.instance().getClient().getSoundHandler().stopSound((ISound) rocketSoundUpdater);
				}
				this.rocketSoundUpdater = null;
			}
		}

//////////////////////
		int i;
		if (this.timeUntilLaunch >= 100) {
			i = Math.abs(this.timeUntilLaunch / 100);
		} else {
			i = 1;
		}

		if (this.getWaitForPlayer()) {
			if (!this.getPassengers().isEmpty()) {
				List<Entity> passengers = new ArrayList<>(getPassengers());
				for (Entity passenger : passengers) {
					if (this.ticks >= 40L) {
						if (!this.world.isRemote) {
							this.removePassengers();
							passenger.startRiding(this, true);
							GCLog.debug("Remounting player in rocket.");
						}

						this.setWaitForPlayer(false);
						this.motionY = -0.5D;
					} else {
						this.motionX = this.motionY = this.motionZ = 0.0D;
						passenger.motionX = passenger.motionY = passenger.motionZ = 0.0D;
					}
				}
			} else {
				this.motionX = this.motionY = this.motionZ = 0.0D;
			}
		}

		if (this.rumble > 0.0F) {
			--this.rumble;
		} else if (this.rumble < 0.0F) {
			++this.rumble;
		}

		double rumbleAmount = (double) this.rumble / (double) (37 - 5 * Math.max(this.getRocketTier(), 5));

		Entity passenger;
		for (Iterator<Entity> var7 = this.getPassengers().iterator(); var7.hasNext(); passenger.posZ += rumbleAmount) {
			passenger = var7.next();
			passenger.posX += rumbleAmount;
		}

		if (this.launchPhase >= EnumLaunchPhase.IGNITED.ordinal()) {
			this.performHurtAnimation();
			this.rumble = (float) this.rand.nextInt(3) - 3.0F;
		}

		if (!this.world.isRemote) {
			this.lastLastMotionY = this.lastMotionY;
			this.lastMotionY = this.motionY;
		}

		//////////////////

		if ((this.getLaunched() || this.launchPhase == EnumLaunchPhase.IGNITED.ordinal() && this.rand.nextInt(i) == 0) && !ConfigManagerCore.disableSpaceshipParticles && this
				.hasValidFuel() && this.world.isRemote) {
			if (!this.isDead) {
				double sinPitch = Math.sin((double) this.rotationPitch / 57.29577951308232D);
				double x1 = 2.9D * Math.cos((double) this.rotationYaw / 57.29577951308232D) * sinPitch;
				double z1 = 2.9D * Math.sin((double) this.rotationYaw / 57.29577951308232D) * sinPitch;
				double y1 = 2.9D * Math.cos((double) (this.rotationPitch - 180.0F) / 57.29577951308232D);
				double y;
				if (this.launchPhase == EnumLaunchPhase.LANDING.ordinal() && this.targetVec != null) {
					y = this.posY - (double) this.targetVec.getY();
					y = Math.min(Math.max(y, 80.0D), 200.0D);
					x1 *= y / 100.0D;
					y1 *= y / 100.0D;
					z1 *= y / 100.0D;
				}

				y = this.prevPosY + (this.posY - this.prevPosY) + y1 - this.motionY + (!this.getLaunched() ? 2.5D : 1.0D);
				double x2 = this.posX + x1 - this.motionX;
				double z2 = this.posZ + z1 - this.motionZ;
				double x3 = x2 + x1 / 2.0D;
				double y3 = y + y1 / 2.0D;
				double z3 = z2 + z1 / 2.0D;
				Vector3 motionVec = new Vector3(x1, y1, z1);
				if (this.ticksExisted % 2 == 0 && !this.getLaunched()) {
					return;
				}

				String flame = this.getLaunched() ? "launchFlameLaunched" : "launchFlameIdle";
				//EntityLivingBase riddenByEntity = !this.getPassengers().isEmpty() && this.getPassengers().get(0) instanceof EntityLivingBase ? (EntityLivingBase) this
				//		.getPassengers().get(0) : null;
				for (Entity entity : getPassengers()) {
					if (entity instanceof EntityLivingBase) {
						Object[] rider = new Object[] { entity };
						Object[] none = new Object[0];
						Random random = this.rand;
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 + 0.4D - random.nextDouble() / 10.0D, y, z2 + 0.4D - random.nextDouble() / 10.0D), motionVec,
								rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 - 0.4D + random.nextDouble() / 10.0D, y, z2 + 0.4D - random.nextDouble() / 10.0D), motionVec,
								rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 - 0.4D + random.nextDouble() / 10.0D, y, z2 - 0.4D + random.nextDouble() / 10.0D), motionVec,
								rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 + 0.4D - random.nextDouble() / 10.0D, y, z2 - 0.4D + random.nextDouble() / 10.0D), motionVec,
								rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2, y, z2), motionVec, rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 + 0.4D, y, z2), motionVec, rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2 - 0.4D, y, z2), motionVec, rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2, y, z2 + 0.4D), motionVec, rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x2, y, z2 - 0.4D), motionVec, rider);
						double a = 8.0D;
						double bx = motionVec.x + 2.5D / a;
						double bz = motionVec.z + 2.5D / a;
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.2D - random.nextDouble() / 6.0D, y3 + 0.4D, z3 + 0.2D - random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.2D + random.nextDouble() / 6.0D, y3 + 0.4D, z3 + 0.2D - random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.2D + random.nextDouble() / 6.0D, y3 + 0.4D, z3 - 0.2D + random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.2D - random.nextDouble() / 6.0D, y3 + 0.4D, z3 - 0.2D + random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.2D - random.nextDouble() / 6.0D, y3 - 0.4D, z3 + 0.2D - random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.2D + random.nextDouble() / 6.0D, y3 - 0.4D, z3 + 0.2D - random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.2D + random.nextDouble() / 6.0D, y3 - 0.4D, z3 - 0.2D + random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.2D - random.nextDouble() / 6.0D, y3 - 0.4D, z3 - 0.2D + random.nextDouble() / 6.0D),
								new Vector3(bx - random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.7D - random.nextDouble() / 8.0D, y3, z3 + 0.7D - random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.7D + random.nextDouble() / 8.0D, y3, z3 + 0.7D - random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.7D + random.nextDouble() / 8.0D, y3, z3 - 0.7D + random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.7D - random.nextDouble() / 8.0D, y3, z3 - 0.7D + random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + 0.7D - random.nextDouble() / 8.0D, y3, z3 - random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - 0.7D + random.nextDouble() / 8.0D, y3, z3 - random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 + random.nextDouble() / 8.0D, y3, z3 + 0.7D + random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle(flame, new Vector3(x3 - random.nextDouble() / 8.0D, y3, z3 - 0.7D + random.nextDouble() / 8.0D), new Vector3(bx
								- random.nextDouble() / a, motionVec.y, bz - random.nextDouble() / a), rider);
						GalacticraftCore.proxy.spawnParticle("blueflame", new Vector3(x2 - 0.8D, y, z2), motionVec, none);
						GalacticraftCore.proxy.spawnParticle("blueflame", new Vector3(x2 + 0.8D, y, z2), motionVec, none);
						GalacticraftCore.proxy.spawnParticle("blueflame", new Vector3(x2, y, z2 - 0.8D), motionVec, none);
						GalacticraftCore.proxy.spawnParticle("blueflame", new Vector3(x2, y, z2 + 0.8D), motionVec, none);
					}
				}
			}
		}

		if (this.launchPhase >= EnumLaunchPhase.LAUNCHED.ordinal() && this.hasValidFuel()) {
			double d;
			if (this.launchPhase != EnumLaunchPhase.LAUNCHED.ordinal()) {
				this.motionY -= 0.008D;
			} else {
				d = this.timeSinceLaunch / 150.0F;
				if (this.world.provider instanceof IGalacticraftWorldProvider && ((IGalacticraftWorldProvider) this.world.provider).hasNoAtmosphere()) {
					d = Math.min(d * 1.2D, 1.8D);
				} else {
					d = Math.min(d, 1.2D);
				}

				if (d != 0.0D) {
					this.motionY = -d * 2.0D * Math.cos((double) (this.rotationPitch - 180.0F) / 57.29577951308232D);
				}
			}

			d = 1.0D;
			if (this.world.provider instanceof IGalacticraftWorldProvider) {
				d = ((IGalacticraftWorldProvider) this.world.provider).getFuelUsageMultiplier();
				if (d <= 0.0D) {
					d = 1.0D;
				}
			}

			if (this.timeSinceLaunch % (float) MathHelper.floor(2.0D * (1.0D / d)) == 0.0F) {
				this.removeFuel(1);
				if (!this.hasValidFuel()) {
					this.stopRocketSound();
				}
			}
		} else if (!this.hasValidFuel() && this.getLaunched() && !this.world.isRemote && Math.abs(Math.sin(this.timeSinceLaunch / 1000.0F)) / 10.0D != 0.0D) {
			this.motionY -= Math.abs(Math.sin(this.timeSinceLaunch / 1000.0F)) / 20.0D;
		}
	}

	private void autoLaunch_r() {
		try {
			Method method = EntityAutoRocket.class.getDeclaredMethod("autoLaunch");
			method.setAccessible(true);
			method.invoke(this);
		} catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
			e.printStackTrace();
		}
	}

	private BlockVec3 getActiveLaunchController() {
		try {
			Field field = EntityAutoRocket.class.getDeclaredField("activeLaunchController");
			field.setAccessible(true);
			return (BlockVec3) field.get(this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to get active launch controller via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	private boolean getRocketSoundToStop() {
		try {
			Field field = EntityAutoRocket.class.getDeclaredField("rocketSoundToStop");
			field.setAccessible(true);
			return (boolean) field.get(this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to get rocket sound state via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	private void setRocketSoundToStop(boolean b) {
		try {
			Field field = EntityAutoRocket.class.getDeclaredField("rocketSoundToStop");
			field.setAccessible(true);
			field.set(this, b);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to set rocket sound state via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	private boolean getAddToTelemetry() {
		try {
			Field field = EntitySpaceshipBase.class.getDeclaredField("addToTelemetry");
			field.setAccessible(true);
			return (boolean) field.get(this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to get telemetnry via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	private void setAddTotele(boolean b) {
		try {
			Field field = EntitySpaceshipBase.class.getDeclaredField("addToTelemetry");
			field.setAccessible(true);
			field.set(this, b);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to get telemetnry via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	@SuppressWarnings("unchecked")
	private List<BlockVec3Dim> getTeleList() {
		try {
			Field field = EntitySpaceshipBase.class.getDeclaredField("telemetryList");
			field.setAccessible(true);
			return (List<BlockVec3Dim>) field.get(this);
		} catch (NoSuchFieldException | IllegalAccessException e) {
			RuntimeException ex = new RuntimeException("Failed to get telemetnry via reflection!");
			ex.addSuppressed(e);
			throw ex;
		}
	}

	@Override
	public float getCameraZoom() {
		return 25.0F;
	}

	@Override
	public boolean defaultThirdPerson() {
		return true;
	}

	@Override
	public int getRocketTier() {
		return 5;
	}

	@Override
	public int getFuelTankCapacity() {
		return 2800;
	}

	@Override
	public int getPreLaunchWait() {
		return 400;
	}

	@Override
	public float getRenderOffsetY() {
		return -0.15F;
	}

	@Override
	public boolean isDockValid(IFuelDock dock) {
		return dock instanceof TileEntityLandingPad;
	}

}