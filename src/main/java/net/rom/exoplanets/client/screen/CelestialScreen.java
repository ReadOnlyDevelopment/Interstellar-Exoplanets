package net.rom.exoplanets.client.screen;

import java.util.Collections;
import java.util.List;

import com.google.common.collect.Lists;

import asmodeuscore.AsmodeusCore;
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
import micdoodle8.mods.galacticraft.core.util.ColorUtil;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.common.Loader;
import net.rom.api.space.ExoRegistry;
import net.rom.api.space.RelayStation;
import net.rom.exoplanets.compat.Compat;

public class CelestialScreen extends NewGuiCelestialSelection {
	protected enum EnumView {
		PREVIEW,
		PROFILE,
		GS
	}

	protected EnumView viewState = EnumView.GS;

	//GS ADDS
	private String galaxy = GalacticraftCore.planetOverworld.getParentSolarSystem().getUnlocalizedParentGalaxyName();

	public static SpaceData data;

	protected static final int LIGHTBLUE = ColorUtil.to32BitColor(255, 0, 255, 255);
	protected static final int YELLOW    = ColorUtil.to32BitColor(255, 255, 255, 0);

	public static ResourceLocation guiMain2 = new ResourceLocation(AsmodeusCore.ASSET_PREFIX, "textures/gui/celestialselection2.png");

	public static ResourceLocation guiImg = new ResourceLocation(AsmodeusCore.ASSET_PREFIX, "textures/gui/galaxymap_1.png");

	public CelestialScreen(boolean mapMode, List<CelestialBody> possibleBodies, boolean canCreateStations,
			SpaceData data) {
		super(mapMode, possibleBodies, canCreateStations, data);

	}

	@Override
	public void initGui () {
		GuiCelestialSelection.BORDER_SIZE      = this.width / 65;
		GuiCelestialSelection.BORDER_EDGE_SIZE = GuiCelestialSelection.BORDER_SIZE / 4;

		refreshBodies();

	}

	private void refreshBodies () {

		EntityPlayerSP player = Minecraft.getMinecraft().player;

		bodiesToRender.clear();

		for (SolarSystem solarSystem : GalaxyRegistry.getRegisteredSolarSystems().values()) {
			if (solarSystem.getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (Compat.hasUnlocks(player, solarSystem)) {
						bodiesToRender.add(solarSystem.getMainStar());
					}
				}
				else {
					bodiesToRender.add(solarSystem.getMainStar());
				}
			}
		}

		for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
			if (planet.getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (Compat.isReasearched(player, planet)) {
						bodiesToRender.add(planet);
					}
				}
				else {
					bodiesToRender.add(planet);
				}
			}
		}

		for (Moon moon : GalaxyRegistry.getRegisteredMoons().values()) {
			if (moon.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName().equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (Compat.isReasearched(player, moon.getParentPlanet()) && Compat.isReasearched(player, moon)) {
						bodiesToRender.add(moon);
					}
				}
				else {
					bodiesToRender.add(moon);
				}
			}
		}

		for (RelayStation relay : ExoRegistry.getRegisteredRelayStations().values()) {
			bodiesToRender.add(relay);
		}

		for (Satellite satellite : GalaxyRegistry.getRegisteredSatellites().values()) {
			if (satellite.getParentPlanet().getParentSolarSystem().getUnlocalizedParentGalaxyName()
					.equals(this.galaxy)) {
				if (Loader.isModLoaded("planetprogression")) {
					if (Compat.isReasearched(player, satellite.getParentPlanet())) {
						bodiesToRender.add(satellite);
					}
				}
				else {
					bodiesToRender.add(satellite);
				}
			}
		}
	}

	@Override
	protected List<CelestialBody> getChildren (Object object) {
		List<CelestialBody> bodyList = Lists.newArrayList();

		if (object instanceof Planet) {
			for (Planet planet : GalaxyRegistry.getRegisteredPlanets().values()) {
				if (planet.equals(object))
					if (planet.getParentSolarSystem().getUnlocalizedParentGalaxyName().equalsIgnoreCase(this.galaxy)) {
						List<Moon> moons = GalaxyRegistry.getMoonsForPlanet((Planet) object);
						if (Loader.isModLoaded("planetprogression")) {
							for (Moon moon : moons) {
								if (Compat.isReasearched(Minecraft.getMinecraft().player, moon)) {
									bodyList.add(moon);
								}
							}
						}
						else {
							bodyList.addAll(moons);
						}
					}
			}
		}
		else if (object instanceof SolarSystem) {
			for (SolarSystem solarSystems : GalaxyRegistry.getRegisteredSolarSystems().values()) {
				if (solarSystems.equals(object))
					if (solarSystems.getUnlocalizedParentGalaxyName().equalsIgnoreCase(this.galaxy)) {
						List<Planet> planets = GalaxyRegistry.getPlanetsForSolarSystem((SolarSystem) object);
						if (Loader.isModLoaded("planetprogression")) {
							for (Planet planet : planets) {
								if (Compat.isReasearched(Minecraft.getMinecraft().player, planet)) {
									bodyList.add(planet);
								}
							}
						}
						else {
							bodyList.addAll(planets);
						}
					}
			}
		}

		Collections.sort(bodyList);

		return bodyList;
	}

	public void setPossibleBodies (List<CelestialBody> possibleBodies) {
		this.possibleBodies = possibleBodies;
	}
}
