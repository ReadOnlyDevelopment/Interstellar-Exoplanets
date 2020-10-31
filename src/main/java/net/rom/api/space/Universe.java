package net.rom.api.space;

import java.util.ArrayList;
import java.util.List;

import asmodeuscore.api.dimension.IAdvancedSpace.ClassBody;
import asmodeuscore.core.astronomy.BodiesRegistry;
import lombok.Getter;
import micdoodle8.mods.galacticraft.api.GalacticraftRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.GalaxyRegistry;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.AtmosphereInfo;
import micdoodle8.mods.galacticraft.api.world.EnumAtmosphericGas;
import micdoodle8.mods.galacticraft.api.world.ITeleportType;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.rom.exoplanets.Assets;
import net.rom.exoplanets.ExoInfo;

public class Universe {
	
	public static List<ExoSystem> enabledSystems = new ArrayList<>();
	
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
	public static ExoSystem buildSolarSystem (String name, ExoStar exoStar, Vector3 pos) {
		ExoSystem body = new ExoSystem(name, "milky_way");
		exoStar.setParentSolarSystem(body);
		body.setMainStar(exoStar);
		body.setMapPosition(pos);
		exoStar.setBodyIcon(Assets.getCelestialTexture(exoStar.getName()));
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
//	public static ExoPlanet unreachable (String planetName, SolarSystem solarSystem, float randomPhase, float distance, float orbitTime) {
//		ExoPlanet unreachable = (ExoPlanet) new ExoPlanet(planetName).setParentSolarSystem(solarSystem);
//		unreachable.setBodyIcon(Assets.getCelestialTexture(planetName));
//		unreachable.setRelativeDistanceFromCenter(new ScalableDistance(distance, distance));
//		unreachable.setRelativeOrbitTime(orbitTime);
//		unreachable.setPhaseShift(randomPhase);
//		unreachable.setRelativeSize(1.0F);
//		unreachable.setRingColorRGB(0.8F, 0.0F, 0.0F);
//		GalaxyRegistry.registerPlanet(unreachable);
//		return unreachable;
//	}

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
	
	public static class ExoPlanetBuilder {
		
		private static ExoPlanet planet;
		
		ExoPlanetBuilder (ExoPlanet planet) {
			ExoPlanetBuilder.planet = planet;
		}
		
		public static Builder build(ExoPlanet planet) {
			new ExoPlanetBuilder(planet);
			return new Builder();
		}
		
		public static ExoPlanet build(float phaseShift, int tier, float temp, double windLevel, EnumAtmosphericGas[] gasses, Biome[] biomes, ClassBody clazz, float eccentricityX, float eccentricityY, float orbitOffsetX, float orbitOffsetY, double gravity, long dayLength) {
			planet.setPhaseShift(phaseShift);
			planet.setRelativeSize(1.0F);
			planet.setTierRequired(tier);
			planet.setBodyIcon(Assets.getCelestialTexture(planet.getName()));
			planet.setTierRequired(tier);
			planet.setPlanetTemp(temp);
			planet.setBiomeInfo(biomes);
			planet.setClassBody(clazz);
			planet.setPlanetGravity((float) gravity);
			planet.setDayLength(dayLength);
			planet.setOrbitEccentricity(eccentricityX, eccentricityY);
			planet.setOrbitOffset(orbitOffsetX, orbitOffsetY);
			setAtmosphere(temp, windLevel, gasses);
			BodiesRegistry.setOrbitData(planet, phaseShift, 1.0f, (float) planet.getOrbitPeriod());
			return planet;
		}
		
		public static ExoPlanet build(float phaseShift, int tier, float temp, double windLevel, EnumAtmosphericGas[] gasses, Biome[] biomes, ClassBody clazz, double gravity, long dayLength) {
			planet.setPhaseShift(phaseShift);
			planet.setRelativeSize(1.0F);
			planet.setTierRequired(tier);
			planet.setBodyIcon(Assets.getCelestialTexture(planet.getName()));
			planet.setTierRequired(tier);
			planet.setBiomeInfo(biomes);
			planet.setPlanetTemp(temp);
			planet.setClassBody(clazz);
			planet.setPlanetGravity((float) gravity);
			planet.setDayLength(dayLength);
			setAtmosphere(temp, windLevel, gasses);
			BodiesRegistry.setOrbitData(planet, phaseShift, 1.0f, (float) planet.getOrbitPeriod());
			return planet;
		}
		
		public static ExoPlanet build(float phaseShift) {
			planet.setBodyIcon(Assets.getCelestialTexture(planet.getExoPlanetName()));
			planet.setPhaseShift(phaseShift);
			planet.setRelativeSize(1.0F);
			planet.setRingColorRGB(0.8F, 0.0F, 0.0F);
			planet.setUnreachable();
			return planet;
		}
		
		private static void setAtmosphere (double relativeTemp, double windLevel, EnumAtmosphericGas... gasses) {
			boolean canBreathe = false;
			boolean canRain    = false;
			boolean isCorr     = false;
			float   d          = 0.0f;
			for (EnumAtmosphericGas enumAtmosphericGas : gasses) {
				d++;
				planet.atmosphereComponent(enumAtmosphericGas);
				if (enumAtmosphericGas == EnumAtmosphericGas.OXYGEN) {
					canBreathe = true;
				}
				if (enumAtmosphericGas == EnumAtmosphericGas.CO2) {
					canRain = true;
				}
				if (enumAtmosphericGas == EnumAtmosphericGas.METHANE) {
					isCorr = true;
				}
			}
			planet.setAtmosphere(new AtmosphereInfo(canBreathe, canRain, isCorr, (float) relativeTemp, (float) windLevel, d));
		}
		
		@Getter
		public static class Builder {
			private float phaseShift;
			private int tier;
			private float temp;
			private float windLevel;
			private EnumAtmosphericGas[] gasses;
			private Biome[] biomes;
			private boolean normalOrbit = true;
			private float eccentricityX;
			private float eccentricityY; 
			private float orbitOffsetX;
			private float orbitOffsetY;
			private ClassBody clazz;
			private double gravity;
			private long dayLength;
			private boolean unreachable;
			
			public Builder () {}

			public Builder unreachable(boolean unreachable) {
				this.unreachable = unreachable;
				return this;
			}
			
			public Builder gravity(double gravity) {
				this.gravity = gravity;
				return this;
			}
			
			public Builder dayLength(long dayLength) {
				this.dayLength = dayLength;
				return this;
			}
			
			public Builder eccentricities(float eccentricityX, float eccentricityY) {
				this.eccentricityX = eccentricityX;
				this.eccentricityY = eccentricityY;
				return this;
			}
			
			public Builder orbitOffsets(float orbitOffsetX, float orbitOffsetY) {
				this.orbitOffsetX = orbitOffsetX;
				this.orbitOffsetY = orbitOffsetY;
				return this;
			}

			public Builder clazz(ClassBody clazz) {
				this.clazz = clazz;
				return this;
			}
			public Builder normalOrbit(boolean normalOrbit) {
				this.normalOrbit = normalOrbit;
				return this;
			}
			public Builder phaseShift(float phaseShift) {
				this.phaseShift = phaseShift;
				return this;
			}
			public Builder tier(int tier) {
				this.tier = tier;
				return this;
			}
			public Builder tempWind(float temp, float windLevel) {
				this.temp = temp;
				this.windLevel = windLevel;
				return this;
			}

			public Builder gasses(EnumAtmosphericGas... gasses) {
				this.gasses = gasses;
				return this;
			}
			public Builder biomes(Biome... biomes) {
				this.biomes = biomes;
				return this;
			}
			
			public ExoPlanet genUnreachable() {
				return build(this.phaseShift);
			}

			public ExoPlanet generate() {
				if(unreachable) {
					return build(this.phaseShift);
				} else {
					if(!normalOrbit) {
						return build(this.phaseShift, this.tier, this.temp, this.windLevel, this.gasses, this.biomes, this.clazz, this.eccentricityX, this.eccentricityY, this.orbitOffsetX, this.orbitOffsetY, this.gravity, this.dayLength);
					} else {
						return build(this.phaseShift, this.tier, this.temp, this.windLevel, this.gasses, this.biomes, this.clazz, this.gravity, this.dayLength);
					}
				}
			}
		}
	}
}
