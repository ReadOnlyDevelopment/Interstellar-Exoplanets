package net.romvoid95.api.space;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesRegistry;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody.ScalableDistance;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.Satellite;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.romvoid95.api.space.prefab.ExoStar;
import net.romvoid95.client.Assets;
import net.romvoid95.core.ExoInfo;

public class Universe {

	/**
	 * Builds the exo star.
	 *
	 * @param starName the star name
	 * @param temp     the temp
	 * @param mass     the mass
	 * @param radius   the radius
	 * @return the exo star
	 */
	public static ExoStar buildExoStar (String starName, int temp, double mass, double radius) {
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
	 * @param name    the name
	 * @param galaxy  the galaxy
	 * @param pos     the pos
	 * @param exoStar the exo star
	 * @return the solar system
	 */
	public static SolarSystem buildSolarSystem (String name, Vector3 pos, ExoStar exoStar) {
		SolarSystem body = new SolarSystem(name, "milky_way");
		body.setMapPosition(new Vector3(pos));
		exoStar.setParentSolarSystem(body);
		body.setMainStar(exoStar);
		exoStar.setBodyIcon(Assets.getCelestialTexture(exoStar.getName()));
		return body;
	}

	/**
	 * builds solar system.
	 *
	 * @param name     the name
	 * @param galaxy   the galaxy
	 * @param pos      the pos
	 * @param starname the starname
	 * @param size     the size
	 * @return the solar system
	 */
	public static SolarSystem buildSolarSystem (String name, String galaxy, Vector3 pos, String starname) {
		SolarSystem body = new SolarSystem(name, galaxy);
		body.setMapPosition(new Vector3(pos));
		Star main = new Star(starname);
		main.setParentSolarSystem(body);
		main.setBodyIcon(Assets.getCelestialTexture(starname));

		body.setMainStar(main);
		return body;
	}

	public static ExoPlanet createPlanet (String name, SolarSystem solar, float phaseShift, float distance, float orbitTime, int tier) {
		ExoPlanet planet = (ExoPlanet) new ExoPlanet(name).setParentSolarSystem(solar);
		planet.setPhaseShift(phaseShift);
		planet.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
		planet.setRelativeOrbitTime(orbitTime);
		planet.setRelativeSize(1.0F);
		planet.setTierRequired(tier);
		planet.setBodyIcon(Assets.getCelestialTexture(name));
		planet.setTierRequired(tier);

		return planet;
	}

	public static Moon createMoon (String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier, int id, Class<? extends WorldProvider> provider) {
		Moon moon = new Moon(name).setParentPlanet(planet);
		moon.setDimensionInfo(id, provider);
		moon.setPhaseShift(phaseShift);
		moon.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
		moon.setRelativeOrbitTime(orbitTime);
		moon.setRelativeSize(size);
		moon.setTierRequired(tier);
		moon.setBodyIcon(Assets.getCelestialTexture(name));
		return moon;
	}

	public static Satellite createSpaceStation (String name, Planet planet, float phaseShift, float distance, float orbitTime, float size, int tier) {
		Satellite satellite = new Satellite(name).setParentBody(planet);
		satellite.setPhaseShift(phaseShift);
		satellite.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
		satellite.setRelativeOrbitTime(orbitTime);
		satellite.setRelativeSize(size);
		satellite.setTierRequired(tier);
		satellite.setBodyIcon(Assets.getCelestialTexture(name));
		return satellite;
	}

	public static void setBiomes (CelestialBody planet, Biome... biomes) {
		if (biomes != null) {
			planet.setBiomeInfo(biomes);
		}
	}

	public static void setProvider (ExoPlanet planet, Class<? extends WorldProvider> provider, int dimId) {
		planet.setProvider(provider);
		planet.setDimensionInfo(dimId, provider);
	}

	public static void setExoPlanetData (CelestialBody planet, float temp, float mass, float radius) {
		((ExoPlanet) planet).setPlanetTemp(temp);
		((ExoPlanet) planet).setPlanetMass(mass);
		((ExoPlanet) planet).setPlanetRadius(radius);
	}

	/**
	 * Sets the normal orbit.
	 *
	 * @param body the new normal orbit
	 */
	public static void setNormalOrbit (CelestialBody planet) {
		BodiesRegistry.setOrbitData(planet, planet.getPhaseShift(), 1.0f, planet.getRelativeOrbitTime());

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
	public static void setOrbit (CelestialBody body, float eccentricityX, float eccentricityY, float orbitOffsetX, float orbitOffsetY) {
		((ExoPlanet) body).setOrbitEccentricity(eccentricityY, orbitOffsetX);
		((ExoPlanet) body).setOrbitOffset(orbitOffsetX, orbitOffsetY);
		BodiesRegistry.setOrbitData(body, body.getPhaseShift(), 1.0f, body.getRelativeOrbitTime());

	}

	public static void setAtmosphere (CelestialBody body, double relativeTemp, double windLevel, EnumAtmosphericGas... gasses) {
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
		body.setAtmosphere(new AtmosphereInfo(canBreathe, canRain, isCorr, (float) relativeTemp, (float) windLevel, d));
	}

	public static void setSurfaceData (ExoPlanet body, double gravity, long dayLength, ClassBody clazz) {
		body.setPlanetGravity((float) gravity);
		body.setDayLength(dayLength);
		body.setClassBody(clazz);
	}

	public static void registerProvider (String name, int id, int staticId, Class<? extends WorldProvider> provider) {
		GalacticraftRegistry.registerDimension(name, "_" + name.toLowerCase(), staticId, provider, true);
	}

	public static void registerProvider (String name, int id, Class<? extends WorldProvider> provider) {
		GalacticraftRegistry.registerDimension(name, "_" + name.toLowerCase(), id, provider, false);
	}

	public static void registerSolarSystem (SolarSystem solarSystem) {
		GalaxyRegistry.registerSolarSystem(solarSystem);
	}

	public static void registerPlanet (Planet planet) {
		GalaxyRegistry.registerPlanet(planet);
	}

	public static void registerMoon (Moon moon) {
		GalaxyRegistry.registerMoon(moon);
	}

	public static void registerTeleportType (Class<? extends WorldProvider> clazz, ITeleportType type) {
		GalacticraftRegistry.registerTeleportType(clazz, type);
	}

	public static void registerRocketGui (Class<? extends WorldProvider> clazz, String planetString) {
		GalacticraftRegistry.registerRocketGui(clazz, new ResourceLocation(ExoInfo.MODID, "textures/gui/rocketgui/"
				+ planetString + ".png"));
	}
}
