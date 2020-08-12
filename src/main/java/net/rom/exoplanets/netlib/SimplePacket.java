package net.rom.exoplanets.netlib;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import asmodeuscore.core.handler.ColorBlockHandler;
import asmodeuscore.core.utils.worldengine.WE_WorldProvider;
import io.netty.buffer.ByteBuf;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.network.NetworkUtil;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import micdoodle8.mods.galacticraft.core.util.GCCoreUtil;
import micdoodle8.mods.galacticraft.core.util.GCLog;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import micdoodle8.mods.galacticraft.core.util.WorldUtil;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.network.INetHandler;
import net.minecraft.network.Packet;
import net.minecraft.network.PacketBuffer;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.FMLCommonHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.client.screen.CelestialScreen;

public class SimplePacket extends NetPacket implements Packet<INetHandler> {

	public static enum EnumExoSimplePacket {
		//CLIENT
		C_UPDATE_DIMENSION_LIST(Side.CLIENT, String.class, String.class, Boolean.class, Integer.class, Integer.class,
				Integer.class),
		C_UPDATE_OXYGEN_STATUS(Side.CLIENT, Boolean.class),
		C_UPDATE_WORLD(Side.CLIENT);

		private Side       targetSide;
		private Class<?>[] decodeAs;

		private EnumExoSimplePacket(Side targetSide, Class<?>... decodeAs) {
			this.targetSide = targetSide;
			this.decodeAs   = decodeAs;
		}

		public Side getTargetSide () {
			return this.targetSide;
		}

		public Class<?>[] getDecodeClasses () {
			return this.decodeAs;
		}
	}

	private EnumExoSimplePacket type;
	private List<Object>        data;
	static private String       spamCheckString;

	public SimplePacket() {
		super();
	}

	public SimplePacket(EnumExoSimplePacket packetType, int dimID, Object... data) {
		this(packetType, dimID, Arrays.asList(data));
	}

	public SimplePacket(EnumExoSimplePacket packetType, World world, Object[] data) {
		this(packetType, GCCoreUtil.getDimensionID(world), Arrays.asList(data));
	}

	public SimplePacket(EnumExoSimplePacket packetType, int dimID, List<Object> data) {
		super(dimID);
		if (packetType.getDecodeClasses().length != data.size()) {
			GCLog.info("Simple Packet found data length different than packet type");
			new RuntimeException().printStackTrace();
		}

		this.type = packetType;
		this.data = data;
	}

	@Override
	public void encodeInto (ByteBuf buffer) {
		super.encodeInto(buffer);
		buffer.writeInt(this.type.ordinal());

		try {
			NetworkUtil.encodeData(buffer, this.data);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void decodeInto (ByteBuf buffer) {
		super.decodeInto(buffer);
		this.type = EnumExoSimplePacket.values()[buffer.readInt()];

		try {
			if (this.type.getDecodeClasses().length > 0) {
				this.data = NetworkUtil.decodeData(this.type.getDecodeClasses(), buffer);
			}
			if (buffer.readableBytes() > 0) {
				GCLog.severe("Packet length error for packet type " + this.type.toString());
			}
		}
		catch (Exception e) {
			System.err.println("[Exoplanets] Error On Packet Type: " + this.type.toString() + " " + buffer.toString());
			e.printStackTrace();
		}
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void handleClientSide (EntityPlayer player) {
		EntityPlayerSP playerBaseClient = null;

		if (player instanceof EntityPlayerSP) {
			playerBaseClient = (EntityPlayerSP) player;
		}
		switch (this.type) {
		case C_UPDATE_DIMENSION_LIST:
			if (String.valueOf(this.data.get(0)).equals(PlayerUtil.getName(player))) {
				String dimensionList = (String) this.data.get(1);
				if (ConfigManagerCore.enableDebug) {
					if (!dimensionList.equals(SimplePacket.spamCheckString)) {
						GCLog.info("DEBUG info: " + dimensionList);
						SimplePacket.spamCheckString = dimensionList;
					}
				}
				final String[]                                            destinations            = dimensionList
						.split("\\?");
				List<CelestialBody>                                       possibleCelestialBodies = Lists
						.newArrayList();
				Map<Integer, Map<String, CelestialScreen.StationDataGUI>> spaceStationData        = Maps.newHashMap();

				for (String str : destinations) {
					System.out.println(str);
					CelestialBody celestialBody = WorldUtil.getReachableCelestialBodiesForName(str);

					if (celestialBody == null && str.contains("$")) {
						String[] values = str.split("\\$");

						int homePlanetID = Integer.parseInt(values[4]);

						for (Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
							if (satellite.getParentPlanet().getDimensionID() == homePlanetID) {
								celestialBody = satellite;
								break;
							}
						}

						if (!spaceStationData.containsKey(homePlanetID)) {
							spaceStationData.put(homePlanetID, new HashMap<String, CelestialScreen.StationDataGUI>());
						}

						spaceStationData.get(homePlanetID)
								.put(values[1], new CelestialScreen.StationDataGUI(values[2], Integer
										.parseInt(values[3])));

					}

					if (celestialBody != null) {
						possibleCelestialBodies.add(celestialBody);
					}
				}

				if (FMLClientHandler.instance().getClient().world != null) {
					if (!(FMLClientHandler.instance().getClient().currentScreen instanceof GuiCelestialSelection)) {
						GuiCelestialSelection gui = new GuiCelestialSelection(false, possibleCelestialBodies, (Boolean) this.data
								.get(2));
						gui.spaceStationMap = spaceStationData;
						FMLClientHandler.instance().getClient().displayGuiScreen(gui);
					}
					else {
						((GuiCelestialSelection) FMLClientHandler.instance()
								.getClient().currentScreen).possibleBodies                                              = possibleCelestialBodies;
						((GuiCelestialSelection) FMLClientHandler.instance()
								.getClient().currentScreen).spaceStationMap                                             = spaceStationData;
					}
				}
			}
			break;
		case C_UPDATE_WORLD:
			MinecraftServer server = FMLCommonHandler.instance().getMinecraftServerInstance();

			if (server == null)
				break;

			if (playerBaseClient == null)
				break;

			World world = server.getWorld(this.getDimensionID());

			if (world != null && world.provider != null && world.provider instanceof WE_WorldProvider) {
				ColorBlockHandler.world = world;
			}
			break;
		case C_UPDATE_OXYGEN_STATUS:
			//AsmodeusClientEvent.inOxygenBlock = (Boolean) this.data.get(0);
			break;
		default:
			break;
		}
	}

	@Override
	public void handleServerSide (EntityPlayer player) {

	}

	/*
	 * BEGIN "net.minecraft.network.Packet" IMPLEMENTATION This is for handling
	 * server->client packets before the player has joined the world
	 */

	@Override
	public void readPacketData (PacketBuffer var1) {
		this.decodeInto(var1);
	}

	@Override
	public void writePacketData (PacketBuffer var1) {
		this.encodeInto(var1);
	}

	@SideOnly(Side.CLIENT)
	@Override
	public void processPacket (INetHandler var1) {
		if (FMLCommonHandler.instance().getEffectiveSide() == Side.CLIENT) {
			this.handleClientSide(FMLClientHandler.instance().getClientPlayerEntity());
		}
	}

}
