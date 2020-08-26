/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> parent of c40ce9e... uhg
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> parent of c40ce9e... uhg
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
<<<<<<< HEAD
 *
=======
 * 
>>>>>>> parent of c40ce9e... uhg
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package net.rom95.api.space;

import javax.annotation.Nullable;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesRegistry;
import asmodeuscore.core.prefab.celestialbody.ExPlanet;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.rom95.api.space.prefab.ExoStar;
import net.rom95.client.Assets;
import net.rom95.common.config.SConfigCore;

@Deprecated
public class AstroBuilder {

	public static final boolean REALISM = SConfigCore.enableRealism;

	/** The modid. */
	private String modid;

	/**
	 * Instantiates a new astro builder.
	 */
	public AstroBuilder() {
		super();
	}

	/**
	 * Instantiates a new astro builder.
	 *
	 * @param modid the modid
	 */
	public AstroBuilder(String modid) {
		super();
		this.setModid(modid);
	}

	/**
	 * Gets the modid.
	 *
	 * @return the modid
	 */
	public String getModid () {
		return modid;
	}

	/**
	 * Sets the modid.
	 *
	 * @param modid set modid
	 */
	public void setModid (String modid) {
		this.modid = modid;
	}

	/**
	 * Builds the exo star.
	 *
	 * @param  starName the star name
	 * @param  temp     the temp
	 * @param  mass     the mass
	 * @param  radius   the radius
	 * @return          the exo star
	 */
	public ExoStar buildExoStar (String starName, int temp, double mass, double radius) {
		ExoStar star = new ExoStar(starName);
		star.setStarName(starName);
		star.setSurfaceTemp(temp);
		star.setStarMass(mass);
		star.setStarRadius(radius);
		star.setSpectralClass();
		return star;
	}

	/**
	 * Builds the solar system.
	 *
	 * @param  name    the name
	 * @param  galaxy  the galaxy
	 * @param  pos     the pos
	 * @param  exoStar the exo star
	 * @return         the solar system
	 */
	public SolarSystem buildSolarSystem (String name, Vector3 pos, ExoStar exoStar) {
		SolarSystem body = new SolarSystem(name, "milky_way");
		body.setMapPosition(new Vector3(pos));
		exoStar.setParentSolarSystem(body);
		body.setMainStar(exoStar);
		exoStar.setBodyIcon(Assets.getCelestialTexture(exoStar.getName()));

		return body;
	}

	public ExoPlanet buildExoPlanet (SolarSystem system, String name, Class<? extends WorldProvider> provider, int dimID, int tier, float phase, float distance) {
		ExoPlanet body = (ExoPlanet) new ExoPlanet(name).setParentSolarSystem(system);
		body.setPhaseShift(phase);
		body.setRingColorRGB(0.1F, 0.9F, 2.6F);
		body.setRelativeSize(1.0F);
		body.setBodyIcon(Assets.getCelestialTexture(name));
		body.addChecklistKeys("equipOxygenSuit");
		return body;
	}

	/**
	 * Builds the exo planet.
	 *
	 * @param  system   the system
	 * @param  name     the name
	 * @param  provider the provider
	 * @param  dimID    the dim ID
	 * @param  tier     the tier
	 * @param  phase    the phase
	 * @return          the exo planet
	 */
	//	public ExoPlanet buildExoPlanet (SolarSystem system, String name, float distance) {
	//		ExoPlanet body = (ExoPlanet) new ExoPlanet(name).setParentSolarSystem(system);
	//		body.setRelativeSize(1.0F);
	//		registerExPlanet(system, name, distance);
	//		body.addChecklistKeys("equipOxygenSuit");
	//		return body;
	//	}

	public static ExPlanet registerExPlanet (SolarSystem system, String name, float distancefromcenter, ClassBody clazz) {
		ExPlanet body = new ExPlanet(name).setParentSolarSystem(system);
		body.setClassPlanet(clazz);
		body.setRingColorRGB(0.0F, 0.4F, 0.9F);
		body.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(distancefromcenter, distancefromcenter));
		body.setBodyIcon(Assets.getCelestialTexture(name));

		return body;
	}

	/**
	 * Sets the biomes.
	 *
	 * @param body   the body
	 * @param biomes the biomes
	 */
	public void setBiomes (CelestialBody body, Biome... biomes) {
		body.setBiomeInfo(biomes);
	}

	/**
	 * Sets the atmos.
	 *
	 * @param body   the body
	 * @param gasses the gasses
	 */
	public void setAtmos (CelestialBody body, EnumAtmosphericGas... gasses) {
		((ExoPlanet) body).setAtmosGasses(gasses);
	}

	public static void setAtmosphere (CelestialBody body, EnumAtmosphericGas... gasses) {
		boolean canBreathe = false;
		boolean canRain    = false;
		boolean isCorr     = false;
		float   d          = 0.0f;
		for (EnumAtmosphericGas enumAtmosphericGas : gasses) {
			d++;
			body.atmosphereComponent(enumAtmosphericGas);
			if (enumAtmosphericGas == EnumAtmosphericGas.OXYGEN)
				canBreathe = true;
			if (enumAtmosphericGas == EnumAtmosphericGas.CO2)
				canRain = true;
			if (enumAtmosphericGas == EnumAtmosphericGas.METHANE)
				isCorr = true;
		}
		body.setAtmosphere(new AtmosphereInfo(canBreathe, canRain, isCorr, 0.0F, 0.0F, d));
	}

	/**
	 * Sets the data.
	 *
	 * @param body     the body
	 * @param clazz    the clazz
	 * @param distance the distance
	 * @param gravity  the gravity
	 * @param orbit    the orbit
	 * @param pressure the pressure
	 * @param day      the day
	 */
	public void setData (CelestialBody body, ClassBody clazz, float distance, float gravity, float orbit, int pressure, long day) {
		((ExoPlanet) body).setClassBody(clazz);
		body.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
		body.setRelativeOrbitTime(orbit);
		body.setRingColorRGB(0.0F, 0.4F, 0.9F);
		((ExoPlanet) body).setPlanetGravity(gravity);
		((ExoPlanet) body).setAtmosphericPressure(pressure);
		BodiesRegistry.setPlanetData(body, 0, day, BodiesRegistry.calculateGravity(gravity), false);

	}

	/**
	 * Sets the exo data.
	 *
	 * @param body   the body
	 * @param temp   the temp
	 * @param mass   the mass
	 * @param radius the radius
	 */
	public void setExoData (CelestialBody body, float temp, float mass, float radius) {
		((ExoPlanet) body).setPlanetTemp(temp);
		((ExoPlanet) body).setPlanetMass(mass);
		((ExoPlanet) body).setPlanetRadius(radius);
	}

	/**
	 * Sets the normal orbit.
	 *
	 * @param body the new normal orbit
	 */
	public void setNormalOrbit (CelestialBody body) {
		BodiesRegistry.setOrbitData(body, body.getPhaseShift(), 1.0f, body.getRelativeOrbitTime());

	}

	/**
	 * Sets the orbit.
	 *
	 * @param body          the body
	 * @param eccentricityX the eccentricity X
	 * @param eccentricityY the eccentricity Y
	 * @param orbitOffsetX  the orbit offset X
	 * @param orbitOffsetY  the orbit offset Y
	 */
	public void setOrbit (CelestialBody body, float eccentricityX, float eccentricityY, float orbitOffsetX, float orbitOffsetY) {
		((ExoPlanet) body).setOrbitEccentricity(eccentricityY, orbitOffsetX);
		((ExoPlanet) body).setOrbitOffset(orbitOffsetX, orbitOffsetY);
		BodiesRegistry.setOrbitData(body, body.getPhaseShift(), 1.0f, body
				.getRelativeOrbitTime(), eccentricityX, eccentricityY, orbitOffsetX, orbitOffsetY);

	}

	/**
	 * builds SpaceStation.
	 *
	 * @param  parent             the parent
	 * @param  color              the color
	 * @param  provider           the provider
	 * @param  dimID              the dim ID
	 * @param  dimIDStatic        the dim ID static
	 * @param  phase              the phase
	 * @param  size               the size
	 * @param  distancefromcenter the distancefromcenter
	 * @param  relativetime       the relativetime
	 * @param  customStationIcon  the custom station icon
	 * @param  bodyIcon           the body icon
	 * @return                    the satellite
	 */
	public Satellite buildSpaceStation (Planet parent, String color, Class<? extends WorldProvider> provider, int dimID, int dimIDStatic, float phase, float size, float distancefromcenter, float relativetime, boolean customStationIcon, @Nullable String bodyIcon) {
		Satellite body = new Satellite("spacestation." + parent.getUnlocalizedName().replace("planet.", ""));
		body.setParentBody(parent);
		body.setRelativeOrbitTime(relativetime);
		body.setPhaseShift(phase);
		body.setRelativeSize(size);
		if (customStationIcon) {
			body.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/spacestations/" + bodyIcon
					+ ".png"));
		}
		else {
			body.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/space_station.png"));
		}
		if (provider != null) {
			body.setTierRequired(parent.getTierRequirement());
			body.setDimensionInfo(dimID, dimIDStatic, provider);
			body.setBiomeInfo(new Biome[] { BiomeOrbit.space });
		}
		return body;
	}

	public Moon buildMoon (Planet parent, String name, int dimID, float size, float distancefromcenter, float relativetime) {
		Moon body = (new Moon(name)).setParentPlanet(parent);
		body.setRingColorRGB(0.8F, 0.0F, 0.0F);
		body.setRelativeSize(size);
		body.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(distancefromcenter, distancefromcenter));
		body.setRelativeOrbitTime(relativetime);
		body.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/moons/space_station.png"));
		GalaxyRegistry.registerMoon(body);
		return body;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param  planetName  the planet name
	 * @param  solarSystem the solar system
	 * @param  randomPhase the random phase
	 * @param  au          the au
	 * @return             the planet
	 */
	public ExoPlanet buildSpecialUnreachable (String planetName, SolarSystem solarSystem, float randomPhase, float au) {
		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setDistanceFromCenter(au);
		unreachable.setPhaseShift(randomPhase);
		unreachable.setRelativeSize(1.0F);
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param  planetName  the planet name
	 * @param  solarSystem the solar system
	 * @param  randomPhase the random phase
	 * @param  au          the au
	 * @return             the planet
	 */
	public ExoPlanet buildUnreachablePlanet (String planetName, SolarSystem solarSystem, float randomPhase) {
		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setBodyIcon(Assets.getCelestialTexture(planetName));
		unreachable.setPhaseShift(randomPhase);
		unreachable.setRelativeSize(1.0F);
		unreachable.setRingColorRGB(0.8F, 0.0F, 0.0F);
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}

	/**
	 * Register solar system.
	 *
	 * @param solarSystem the solar system
	 */
	public void registerSolarSystem (SolarSystem solarSystem) {
		GalaxyRegistry.registerSolarSystem(solarSystem);
	}

	/**
	 * Register planet.
	 *
	 * @param planet the planet
	 */
	public void registerPlanet (Planet planet) {
		GalaxyRegistry.registerPlanet(planet);
	}

	/**
	 * Register moon.
	 *
	 * @param moon the moon
	 */
	public void registerMoon (Moon moon) {
		GalaxyRegistry.registerMoon(moon);
	}

	public void register (Object val) {
		if (val instanceof SolarSystem) {
			GalaxyRegistry.registerSolarSystem((SolarSystem) val);
		}
		if (val instanceof Planet) {
			GalaxyRegistry.registerPlanet((Planet) val);
		}
		if (val instanceof Moon) {
			GalaxyRegistry.registerMoon((Moon) val);
		}
	}

	/**
	 * Register teleport type.
	 *
	 * @param clazz the clazz
	 * @param type  the type
	 */
	public void registerTeleportType (Class<? extends WorldProvider> clazz, ITeleportType type) {
		GalacticraftRegistry.registerTeleportType(clazz, type);
	}

	/**
	 * Register rocket gui.
	 *
	 * @param clazz    the clazz
	 * @param resource the resource
	 */
	public void registerRocketGui (Class<? extends WorldProvider> clazz, String resource) {
		GalacticraftRegistry.registerRocketGui(clazz, new ResourceLocation(getModid() + ":textures/gui/rocket/"
				+ resource + ".png"));
	}
}
