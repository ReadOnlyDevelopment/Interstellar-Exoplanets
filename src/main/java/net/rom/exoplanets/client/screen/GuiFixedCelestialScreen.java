package net.rom.exoplanets.client.screen;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;
import com.mjr.planetprogression.client.handlers.capabilities.CapabilityStatsClientHandler;
import com.mjr.planetprogression.client.handlers.capabilities.IStatsClientCapability;

import asmodeuscore.core.astronomy.SpaceData;
import asmodeuscore.core.astronomy.gui.screen.NewGuiCelestialSelection;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.core.GalacticraftCore;
import micdoodle8.mods.galacticraft.core.client.gui.screen.GuiCelestialSelection;
import micdoodle8.mods.galacticraft.core.util.PlayerUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;

public class GuiFixedCelestialScreen extends NewGuiCelestialSelection {

	private String galaxy = GalacticraftCore.planetOverworld.getParentSolarSystem().getUnlocalizedParentGalaxyName();
	private boolean small_mode = Minecraft.getMinecraft().gameSettings.guiScale == 0;

	public GuiFixedCelestialScreen(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations, SpaceData data) {
		super(mapMode, possibleBodies, canCreateStations, data);

	}

	@Override
	public void initGui() {
		GuiCelestialSelection.BORDER_SIZE = this.width / 65;
		GuiCelestialSelection.BORDER_EDGE_SIZE = GuiCelestialSelection.BORDER_SIZE / 4;

		refreshBodies();
	}

	private void refreshBodies() {

		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;
		final EntityPlayerSP playerBaseClient = PlayerUtil.getPlayerBaseClientFromPlayer(player, false);
		IStatsClientCapability stats = null;

		if (player != null) {
			stats = playerBaseClient.getCapability(CapabilityStatsClientHandler.PP_STATS_CLIENT_CAPABILITY, null);
		}

		bodiesToRender.clear();

		for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				bodiesToRender.add(solarSystem.getMainStar());
			}
		}

		for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
			if (planet.getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (stats.getUnlockedPlanets().contains(planet)) {
					bodiesToRender.add(planet);
				}
			}

		}

		for (Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
			if (moon.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (stats.getUnlockedPlanets().contains(moon.getParentPlanet()) && stats.getUnlockedPlanets().contains(moon)) {
					bodiesToRender.add(moon);
				}
			}
		}

		for (Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
			if (satellite.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (stats.getUnlockedPlanets().contains(satellite.getParentPlanet())) {
					bodiesToRender.add(satellite);
				}
			}
		}
	}

	@Override
	protected List<CelestialBody> getChildren(Object object, int start, int size) {
		List<CelestialBody> bodyList = Lists.newArrayList();
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;
		final EntityPlayerSP playerBaseClient = PlayerUtil.getPlayerBaseClientFromPlayer(player, false);

		IStatsClientCapability stats = null;

		if (player != null) {
			stats = playerBaseClient.getCapability(CapabilityStatsClientHandler.PP_STATS_CLIENT_CAPABILITY, null);
		}

		if (object instanceof Planet) {
			List<Moon> moons = GalaxyRegistry.getMoonsForPlanet((Planet) object);
			for (Moon moon : moons)
				if (stats.getUnlockedPlanets().contains(moon))
					bodyList.add(moon);
		} else if (object instanceof SolarSystem) {
			List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem((SolarSystem) object);
			for (Planet planet : planets)
				if (stats.getUnlockedPlanets().contains(planet))
					bodyList.add(planet);
		}

		Collections.sort(bodyList);

		if (small_mode) {
			List<CelestialBody> doneList = Lists.newArrayList();
			int startPos = start;
			int getSize = size;
			if (startPos > bodyList.size())
				startPos = 0;
			if (getSize > bodyList.size())
				getSize = bodyList.size();

			for (int i = 0; i < getSize; i++)
				doneList.add(i, bodyList.get(i + startPos));

			return doneList;
		}
		return bodyList;
	}

}
