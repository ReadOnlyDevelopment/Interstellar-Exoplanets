  package net.rom.api.stellar;

import java.awt.Color;

import javax.annotation.Nullable;

import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
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
import micdoodle8.mods.galacticraft.core.world.gen.BiomeOrbit;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.api.stellar.impl.star.ExoStar;
import net.rom.exoplanets.conf.SConfigCore;

public class AstroBuilder {

	private String modid;

	public AstroBuilder() {
		super();
	}

	public AstroBuilder(String modid) {
		super();
		this.setModid(modid);
	}

	/**
	 * @return the modid
	 */
	public String getModid() {
		return modid;
	}

	/**
	 * @param modid set modid
	 */
	public void setModid(String modid) {
		this.modid = modid;
	}

	/**
	 * @deprecated
	 * builds solar system.
	 *
	 * @param name     the name
	 * @param galaxy   the galaxy
	 * @param pos      the pos
	 * @param starname the starname
	 * @param size     the size
	 * @return the solar system
	 */
	@Deprecated
    public SolarSystem buildSolarSystem(String name, String galaxy, Vector3 pos, String starname) {
		SolarSystem body = new SolarSystem(name, galaxy);
		body.setMapPosition(new Vector3(pos));
		Star main = new Star(starname);
		main.setParentSolarSystem(body);
		if(!SConfigCore.enableRealism) {
			main.setBodyIcon(
					new ResourceLocation(getModid(), "textures/celestialbodies/" + name + "/" + starname + ".png"));
		}
		if(SConfigCore.enableRealism) {
			main.setBodyIcon(
					new ResourceLocation(getModid(), "textures/celestialbodies/" + name + "/realism/" + starname + ".png"));
		}

		body.setMainStar(main);
		return body;
	}

	public ExoStar buildExoStar(String starName, int temp, double mass, double radius) {
		ExoStar star = new ExoStar(starName);
		star.setStarName(starName);
		star.setSurfaceTemp(temp);
		star.setStarMass(mass);
		star.setStarRadius(radius);
		star.setSpectralClass();
		return star;
	}

	public SolarSystem buildSolarSystem(String name, String galaxy, Vector3 pos, ExoStar exoStar) {
		SolarSystem body = new SolarSystem(name, galaxy);
		body.setMapPosition(new Vector3(pos));
		exoStar.setParentSolarSystem(body);
		if(!SConfigCore.enableRealism) {
			exoStar.setBodyIcon(
					new ResourceLocation(getModid(), "textures/celestialbodies/" + name + "/" + exoStar.getStarName() + ".png"));
		}
		if(SConfigCore.enableRealism) {
			exoStar.setBodyIcon(
					new ResourceLocation(getModid(), "textures/celestialbodies/" + name + "/realism/" + exoStar.getStarName() + ".png"));
		}
		body.setMainStar(exoStar);
		return body;
	}

	public ExoPlanet buildExoPlanet(SolarSystem system, String name,
			Class<? extends WorldProvider> provider, int dimID, int tier, float phase) {
		ExoPlanet body = (ExoPlanet) new ExoPlanet(name).setParentSolarSystem(system);
		body.setPhaseShift(phase);
		body.setRingColorRGB(0.1F, 0.9F, 2.6F);
		body.setRelativeSize(1.0F);
		if(!SConfigCore.enableRealism) {
			body.setBodyIcon(new ResourceLocation("exoplanets", "textures/celestialbodies/" + system.getName().toLowerCase() + "/" + name + ".png"));
		}
		if(SConfigCore.enableRealism) {
			body.setBodyIcon(
					new ResourceLocation("exoplanets", "textures/celestialbodies/" + system.getName().toLowerCase() + "/realism/" + name + ".png"));
		}

		if (provider != null) {
			body.setTierRequired(tier);
			body.setDimensionInfo(dimID, provider);
		}
		return body;
	}

	/**
	 * builds planet.
	 *
	 * @param system             the system
	 * @param name               the name
	 * @param prefix             the prefix
	 * @param provider           the provider
	 * @param dimID              the dim ID
	 * @param tier               the tier
	 * @param phase              the phase
	 * @param size               the size
	 * @param distancefromcenter the distancefromcenter
	 * @param relativetime       the relativetime
	 * @param biome              the biome
	 * @return the planet
	 */
	public Planet buildPlanet(SolarSystem system, String name,
			Class<? extends WorldProvider> provider, int dimID, int tier, float phase,
			float distancefromcenter, float relativetime) {
		Planet body = (new Planet(name)).setParentSolarSystem(system);
		body.setPhaseShift(phase);
		body.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(distancefromcenter, distancefromcenter));
		body.setRelativeOrbitTime(relativetime);
		body.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/" + system.getName().toLowerCase() + "/" + name + ".png"));
		if (provider != null) {
			body.setTierRequired(tier);
			body.setDimensionInfo(dimID, provider);
		}
		return body;
	}

	/**
	 * builds moon.
	 *
	 * @param parent             the parent
	 * @param name               the name
	 * @param prefix             the prefix
	 * @param provider           the provider
	 * @param dimID              the dim ID
	 * @param tier               the tier
	 * @param phase              the phase
	 * @param size               the size
	 * @param distancefromcenter the distancefromcenter
	 * @param relativetime       the relativetime
	 * @param biome              the biome
	 * @return the moon
	 */
	public Moon buildMoon(Planet parent, String name, String color, Class<? extends WorldProvider> provider,
			int dimID, int tier, float phase, float size, float distancefromcenter, float relativetime) {
		Moon body = (new Moon(name)).setParentPlanet(parent);
		float[] f = decodeColor(color).getRGBComponents(null);
		body.setRingColorRGB(f[0], f[1], f[2]);
		body.setPhaseShift(phase);
		body.setRelativeSize(size);
		body.setRelativeDistanceFromCenter(new CelestialBody.ScalableDistance(distancefromcenter, distancefromcenter));
		body.setRelativeOrbitTime(relativetime);
		body.setBodyIcon(
				new ResourceLocation(getModid(), "textures/celestialbodies/moons/" + name + ".png"));
		if (provider != null) {
			body.setTierRequired(tier);
			body.setDimensionInfo(dimID, provider);
		}
		return body;
	}

	/**
	 * builds SpaceStation.
	 *
	 * @param parent             the parent
	 * @param prefix             the prefix
	 * @param provider           the provider
	 * @param dimID              the dim ID
	 * @param dimIDStatic        the dim ID static
	 * @param phase              the phase
	 * @param size               the size
	 * @param distancefromcenter the distancefromcenter
	 * @param relativetime       the relativetime
	 * @return the satellite
	 */
	public Satellite buildSpaceStation(Planet parent, String color, Class<? extends WorldProvider> provider, int dimID,
			int dimIDStatic, float phase, float size, float distancefromcenter, float relativetime, boolean customStationIcon, @Nullable String bodyIcon) {
		Satellite body = new Satellite("spacestation." + parent.getUnlocalizedName().replace("planet.", ""));
		float[] f = decodeColor(color).getRGBComponents(null);
		body.setRingColorRGB(f[0], f[1], f[2]);
		body.setParentBody(parent);
		body.setRelativeOrbitTime(relativetime);
		body.setPhaseShift(phase);
		body.setRelativeSize(size);
		if(customStationIcon) {
			body.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/spacestations/"+ bodyIcon +".png"));
		} else {
			body.setBodyIcon(new ResourceLocation("galacticraftcore:textures/gui/celestialbodies/space_station.png"));
		}
		if (provider != null) {
			body.setTierRequired(parent.getTierRequirement());
			body.setDimensionInfo(dimID, dimIDStatic, provider);
			body.setBiomeInfo(new Biome[] { BiomeOrbit.space });
		}
		return body;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param planetName the planet name
	 * @param solarSystem the solar system
	 * @return the planet
	 */
	public ExoPlanet buildUnreachablePlanet(String planetName, SolarSystem solarSystem, float randomPhase, float au) {
		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/" + solarSystem.getName().toLowerCase() + "/" + planetName + ".png"));
		unreachable.setDistanceFromCenter(au);
		unreachable.setRelativeOrbitTime(au + 0.5F);
		unreachable.setPhaseShift(randomPhase);
		unreachable.setRelativeSize(1.0F);
		unreachable.setRingColorRGB(0.8F, 0.0F, 0.0F);
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}

	/**
	 * Builds unreachable planet.
	 *
	 * @param planetName the planet name
	 * @param solarSystem the solar system
	 * @return the planet
	 */
	public Planet buildUnreachablePlanet(String planetName, SolarSystem solarSystem) {
		Planet unreachable = new Planet(planetName).setParentSolarSystem(solarSystem);
		unreachable.setBodyIcon(new ResourceLocation(getModid(), "textures/celestialbodies/" + solarSystem.getName().toLowerCase() + "/" + planetName + ".png"));
		GalaxyRegistry.registerPlanet(unreachable);
		return unreachable;
	}

	public void setRingColor(Planet planet, double r, double g, double b) {
		float rf = (float) r;
		float gf = (float) g;
		float bf = (float) b;
		planet.setRingColorRGB(rf, gf, bf);

	}

	/**
	 * Calculate gravity.
	 *
	 * @param Si the si
	 * @return the float
	 */
	public float calculateGravity(float Si) {
		float i = (9.81F - Si) * 0.008664628F;
		float k = Math.round(i * 1000.0F);
		return k / 1000.0F;
	}

	/**
	 * Sets the atmosphere component list.
	 *
	 * @param celestial the celestial
	 * @param gasList   the gas list
	 */
	public void setAtmosphereComponentList(CelestialBody celestial, EnumAtmosphericGas... gasList) {
		for (EnumAtmosphericGas gas : gasList) {
			celestial.atmosphereComponent(gas);
		}
	}

	/**
	 * Sets the atmosphere.
	 *
	 * @param celestial           the celestial
	 * @param breathable          the breathable
	 * @param precipitation       the precipitation
	 * @param corrosive           the corrosive
	 * @param relativeTemperature the relative temperature
	 * @param windLevel           the wind level
	 * @param density             the density
	 */
	public void setAtmosphere(CelestialBody celestial, Boolean breathable, boolean precipitation, boolean corrosive,
			float relativeTemperature, float windLevel, float density) {
		celestial.setAtmosphere(
				new AtmosphereInfo(breathable, precipitation, corrosive, relativeTemperature, windLevel, density));
	}

	/**
	 * Sets the biomes.
	 *
	 * @param celestial the celestial
	 * @param bomes the bomes
	 */
	public void setBiomes(CelestialBody celestial, Biome... bomes) {
		celestial.setBiomeInfo(bomes);
	}

	/**
	 * Sets the checklist keys.
	 *
	 * @param celestial the celestial
	 * @param keys      the keys
	 */
	public void setChecklistKeys(CelestialBody celestial, String... keys) {
		celestial.addChecklistKeys(keys);
	}

	/**
	 * Register solar system.
	 *
	 * @param solarSystem the solar system
	 */
	public void registerSolarSystem(SolarSystem solarSystem) {
		GalaxyRegistry.registerSolarSystem(solarSystem);
	}

	/**
	 * Register planet.
	 *
	 * @param planet the planet
	 */
	public void registerPlanet(Planet planet) {
		GalaxyRegistry.registerPlanet(planet);
	}

	/**
	 * Register moon.
	 *
	 * @param moon the moon
	 */
	public void registerMoon(Moon moon) {
		GalaxyRegistry.registerMoon(moon);
	}

	/**
	 * Register teleport type.
	 *
	 * @param clazz the clazz
	 * @param type  the type
	 */
	public void registerTeleportType(Class<? extends WorldProvider> clazz, ITeleportType type) {
		GalacticraftRegistry.registerTeleportType(clazz, type);
	}

	/**
	 * Register rocket gui.
	 *
	 * @param clazz    the clazz
	 * @param resource the resource
	 */
	public void registerRocketGui(Class<? extends WorldProvider> clazz, String resource) {
		GalacticraftRegistry.registerRocketGui(clazz,
				new ResourceLocation(getModid() + ":textures/gui/rocket/" + resource + ".png"));
	}

	public static float[] ringColorHex(String color) {
		Color c = decodeColor(color);
		return c.getRGBColorComponents(null);
	}

	private static Color decodeColor(String color) {
	    String colorVal = "";
	    if (color.length() > 0) {
	        colorVal = color.trim();
	        if (colorVal.startsWith("#")) {
                colorVal = colorVal.substring(1);
            }
	            colorVal = new Integer(Integer.parseInt(colorVal, 16)).toString();
	            return Color.decode(colorVal.toLowerCase());
	    }
		return null;
	}

	private Color decodeColor(Color color) {
	    String colorVal = "";
	            colorVal = new Integer(Integer.parseInt(colorVal, 16)).toString();
	            return Color.decode(colorVal.toLowerCase());

	}
}
