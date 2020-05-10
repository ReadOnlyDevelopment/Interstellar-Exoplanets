/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.api.stellar.calc;

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
