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
package net.rom.exoplanets.client.screen;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

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
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.Loader;
import net.rom.exoplanets.compat.PlanetProgressionCompat;

public class GuiFixedCelestialScreen extends NewGuiCelestialSelection {

	private String	galaxy		= GalacticraftCore.planetOverworld.getParentSolarSystem().getUnlocalizedParentGalaxyName();
	private boolean	small_mode	= Minecraft.getMinecraft().gameSettings.guiScale == 0;

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

		bodiesToRender.clear();

		for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				bodiesToRender.add(solarSystem.getMainStar());
			}
		}

		for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
			if (planet.getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, planet)) {
						this.bodiesToRender.add(planet);
					}
				} else {
					this.bodiesToRender.add(planet);
				}
			}

		}

		for (Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
			if (moon.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, moon.getParentPlanet()) && PlanetProgressionCompat.isReasearched(player, moon)) {
						this.bodiesToRender.add(moon);
					}
				} else {
					this.bodiesToRender.add(moon);
				}
			}
		}

		for (Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
			if (satellite.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (PlanetProgressionCompat.isReasearched(player, satellite.getParentPlanet())) {
						this.bodiesToRender.add(satellite);
					}
				} else {
					this.bodiesToRender.add(satellite);
				}
			}
		}
	}

	@Override
	protected List<CelestialBody> getChildren(Object object, int start, int size) {
		List<CelestialBody> bodyList = Lists.newArrayList();
		final Minecraft minecraft = FMLClientHandler.instance().getClient();
		final EntityPlayerSP player = minecraft.player;

		if (object instanceof Planet) {
			List<Moon> moons = GalaxyRegistry.getMoonsForPlanet((Planet) object);
			if (Loader.isModLoaded("planetprogression")) {
				for (Moon moon : moons)
					if (PlanetProgressionCompat.isReasearched(player, moon)) {
						bodyList.add(moon);
					}
			} else {
				bodyList.addAll(moons);
			}
		} else if (object instanceof SolarSystem) {
			List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem((SolarSystem) object);
			if (Loader.isModLoaded("planetprogression")) {
				for (Planet planet : planets) {
					if (PlanetProgressionCompat.isReasearched(player, planet)) {
						bodyList.add(planet);
					}
				}
			} else {
				bodyList.addAll(planets);
			}
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
