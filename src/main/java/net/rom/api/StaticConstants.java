package net.rom.api;

public final class StaticConstants {

    public static final float RADIANS_TO_DEGREES = 180F / 3.1415927F;
    public static final double RADIANS_TO_DEGREES_D = 180D / Math.PI;

    public static final float floatPI = 3.1415927F;

    public static final float twoPI = floatPI * 2F;
    public static final float halfPI = floatPI / 2F;
    public static final float quarterPI = halfPI / 2F;

    /**
     * Gravitational Constant
     */
    public static final double GC = 6.674 * exp(-11);

    /**
     * Astronomical Unit (SI Unit) Metric Meter
     */
    public final static double AU = 1.495978707 * exp(-11);

    /**
     * Light-year (SI Unit) Metric Meter
     */
    public final double LY = 9.4607 * exp(15);

    public static final double MASS_OF_EARTH = 5.972E24D;
    public static final double MASS_OF_JUPITER = 1.899096E27D;
    public static final double MASS_OF_SUN = 1.9891E30F;

    private static double exp(int toPower) {
        return Math.pow(10, toPower);
    }

    public static final float _1AU = (float) AU;

}
