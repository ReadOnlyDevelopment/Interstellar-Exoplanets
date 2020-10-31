package net.rom.api.space;

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
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.eventhandler.Event;

public class ExoRegistry {

	private ExoRegistry() {}

	static int                                        maxRelayID = 0;
	static TreeMap<String, RelayStation>              relays     = Maps.newTreeMap();
	static BiMap<String, Integer>                     relayIDs   = HashBiMap.create();
	static HashMap<CelestialBody, List<RelayStation>> relayList  = Maps.newHashMap();

	public static RelayStation getRelayStationFromDimensionID (int dimensionID) {
		for (RelayStation relay : ExoRegistry.relays.values()) {
			if (relay.getDimensionID() == dimensionID) {
				return relay;
			}
		}
		return null;
	}

	public static void refreshGalaxies () {
		ExoRegistry.relayList.clear();

		for (RelayStation relay : ExoRegistry.getRegisteredRelayStations().values()) {
			CelestialBody      body                = relay.getParent();
			List<RelayStation> listOfRelayStations = ExoRegistry.relayList.get(body);
			if (listOfRelayStations == null) {
				listOfRelayStations = new ArrayList<RelayStation>();
			}
			listOfRelayStations.add(relay);
			ExoRegistry.relayList.put(body, listOfRelayStations);
		}
	}

	public static List<RelayStation> getRelayStationsForCelestialBody (CelestialBody celestialBody) {
		List<RelayStation> relayStationList1 = ExoRegistry.relayList.get(celestialBody);

		if (relayStationList1 == null) {
			return new ArrayList<RelayStation>();
		}

		return ImmutableList.copyOf(relayStationList1);
	}

	public static CelestialBody getCelestialBodyFromUnlocalizedName (String unlocalizedName) {
		for (RelayStation relay : ExoRegistry.relays.values()) {
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
		return (Map<String, RelayStation>) ExoRegistry.relays.clone();
	}

	/**
	 * Returns a read-only map containing Moon Names and their associated IDs.
	 */
	public static Map<String, Integer> getRegisteredRelayStationIDs () {
		return ImmutableMap.copyOf(ExoRegistry.relayIDs);
	}

	public static int getRelayStationID (String relayStationName) {
		return ExoRegistry.relayIDs.get(relayStationName);
	}

	public static boolean registerRelayStation (RelayStation relayStation) {
		if (ExoRegistry.relayIDs.containsKey(relayStation.getName())) {
			return false;
		}

		ExoRegistry.relays.put(relayStation.getName(), relayStation);
		ExoRegistry.relayIDs.put(relayStation.getName(), ++ExoRegistry.maxRelayID);

		MinecraftForge.EVENT_BUS.post(new RelayStationRegisterEvent(relayStation.getName(), ExoRegistry.maxRelayID));
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
