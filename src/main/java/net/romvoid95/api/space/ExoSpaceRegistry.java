package net.romvoid95.api.space;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;
import net.romvoid95.api.space.objects.RelayStation;

public class ExoSpaceRegistry extends GalaxyRegistry {

	private ExoSpaceRegistry() {}

	static int                                        maxRelayID = 0;
	static TreeMap<String, RelayStation>              relays     = Maps.newTreeMap();
	static BiMap<String, Integer>                     relayIDs   = HashBiMap.create();
	static HashMap<CelestialBody, List<RelayStation>> relayList  = Maps.newHashMap();

	public static RelayStation getRelayStationFromDimensionID (int dimensionID) {
		for (RelayStation relay : ExoSpaceRegistry.relays.values()) {
			if (relay.getDimensionID() == dimensionID) {
				return relay;
			}
		}
		return null;
	}

	public static void refreshGalaxies () {
		ExoSpaceRegistry.relayList.clear();

		for (RelayStation relay : ExoSpaceRegistry.getRegisteredRelayStations().values()) {
			CelestialBody      body                = relay.getParent();
			List<RelayStation> listOfRelayStations = ExoSpaceRegistry.relayList.get(body);
			if (listOfRelayStations == null) {
				listOfRelayStations = new ArrayList<RelayStation>();
			}
			listOfRelayStations.add(relay);
			ExoSpaceRegistry.relayList.put(body, listOfRelayStations);
		}
	}

	public static List<RelayStation> getRelayStationsForCelestialBody (CelestialBody celestialBody) {
		List<RelayStation> relayStationList1 = ExoSpaceRegistry.relayList.get(celestialBody);

		if (relayStationList1 == null) {
			return new ArrayList<RelayStation>();
		}

		return ImmutableList.copyOf(relayStationList1);
	}

	public static CelestialBody getCelestialBodyFromUnlocalizedName (String unlocalizedName) {
		for (RelayStation relay : ExoSpaceRegistry.relays.values()) {
			if (relay.getUnlocalizedName().equals(unlocalizedName)) {
				return relay;
			}
		}

		return null;
	}

	/**
	 * Returns a read-only map containing Moon Names and their associated Moons.
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, RelayStation> getRegisteredRelayStations () {
		return (Map<String, RelayStation>) ExoSpaceRegistry.relays.clone();
	}

	/**
	 * Returns a read-only map containing Moon Names and their associated IDs.
	 */
	public static Map<String, Integer> getRegisteredRelayStationIDs () {
		return ImmutableMap.copyOf(ExoSpaceRegistry.relayIDs);
	}

	public static int getRelayStationID (String relayStationName) {
		return ExoSpaceRegistry.relayIDs.get(relayStationName);
	}

	public static boolean registerRelayStation (RelayStation relayStation) {
		if (ExoSpaceRegistry.relayIDs.containsKey(relayStation.getName())) {
			return false;
		}

		ExoSpaceRegistry.relays.put(relayStation.getName(), relayStation);
		ExoSpaceRegistry.relayIDs.put(relayStation.getName(), ++ExoSpaceRegistry.maxRelayID);

		MinecraftForge.EVENT_BUS
				.post(new RelayStationRegisterEvent(relayStation.getName(), ExoSpaceRegistry.maxRelayID));
		return true;
	}

	public static class RelayStationRegisterEvent extends Event {
		public final String relayStationName;
		public final int    relayStationID;

		public RelayStationRegisterEvent(String relayStationName, int relayStationID) {
			this.relayStationName = relayStationName;
			this.relayStationID   = relayStationID;
		}
	}

}
