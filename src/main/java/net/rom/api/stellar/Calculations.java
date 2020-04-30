package net.rom.api.stellar;

import static net.rom.api.StaticConstants.GC;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.IChildBody;
import micdoodle8.mods.galacticraft.api.galaxies.Moon;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.galaxies.Star;

public class Calculations {

    public static final long yearFactor = 8640000L;
    public static final long monthFactor = 192000L;
    public static final String nameSeparator = "\\";
    // try doing this in AUs
    public static final double moonDistanceFactor = 0.00015;
    // earth<-->sun = 1 -> 149598023 => 39
    public static final double planetDistanceFactor = 1.0;
    // sun<-->ra = 1069,17 (raw value from map coords). proportional value from pixels: 12,8
    public static final double systemDistanceFactor = 12.3 / 1069.17;

    public static final float maxTemperature = 5.0F;
    public static final float maxSolarLevel = 10.0F;

    public static final double AUlength = 149597870700.0;

    public static final double maxSpeed = 299792458.0D; // this used to be an arbitary value, but
                                                        // the actual speed of light makes for a
                                                        // good maxSpeed

    /**
     * Should calculate a thermal level depending on that body's distance from the star in it's
     * system
     *
     * @param body
     * @return
     */
    public static float getThermalLevel(CelestialBody body) {
        if (body instanceof Star) {
            return maxTemperature;
        }
        body = getParentPlanet(body);
        float dist = body.getRelativeDistanceFromCenter().unScaledDistance;
        // now IRL this is most certainly a form of 1/r²
        // let's see
        // name | thermal | distance
        // OW | 0 | 1
        // mars | -1 | 1.25F
        // asteroids | -1.5 | 1.375F
        // that looks linear oO
        // eh, let's just do it linear
        // m = -4
        // t = 4

        float temperature = -4 * dist + 4;
        if (temperature < -maxTemperature) {
            temperature = -maxTemperature;
        } else if (temperature > maxTemperature) {
            temperature = maxTemperature;
        }

        return temperature;
    }

    public static CelestialBody getParentPlanet(CelestialBody body) {
        if (body == null) {
            return null;
        }
        if (body instanceof Moon) {
            return ((Moon) body).getParentPlanet();
        }
        if (body instanceof IChildBody) {
            return ((IChildBody) body).getParentPlanet();
        }

        return body;
    }

    public static int calcEscapeVelocity(double mass, double radius) {
        return (int) (((mass*GC / radius) * 2) + Math.exp(0.5D));
    }

    protected static String getSystemMainStarName(SolarSystem sys) {
        return sys.getName();
    }

    protected static String getPlanetName(Planet planet) {
        return getSystemMainStarName(planet.getParentSolarSystem()) + nameSeparator + planet.getName();
    }

    protected static String getMoonName(Moon moon) {
        return getPlanetName(moon.getParentPlanet()) + nameSeparator + moon.getName();
    }
}
