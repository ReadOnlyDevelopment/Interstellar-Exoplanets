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

package exoplanets;

import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.Theory;

import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.astronomy.BodiesHelper.Galaxies;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.ResourceLocation;
import net.rom.api.stellar.calc.AstronomicalConstants;
import net.rom.api.stellar.calc.Calculations;
import net.rom.api.stellar.impl.planet.ExoPlanet;
import net.rom.exoplanets.ExoInfo;

public class AstronomyMath {

	@Before
	public void setUp() {
	}

	@Test
	public void testEventHorizonCalculation() {
		double answer = Calculations.schwartzchildRadius(500.0D);
		// test answer from
		// https://www.omnicalculator.com/physics/schwarzschild-radius?c=USD&v=M:500!suns
		double rounded = Precision.round(answer, 6);
		System.out.println(answer);
		System.out.println(rounded);
	}

	@Test
	public void getStarInfo() {
		Galaxies TESTGALAXY = BodiesHelper.registerGalaxy("testgalaxy", new ResourceLocation("exoplanets", "/textures/celestialbodies/hd_219134/hd_219134_star.png"));
		SolarSystem TESTSYSTEM = BodiesHelper.registerSolarSystem("exoplanets", "testsystem", TESTGALAXY, new Vector3(0.0, 0.0, 0.0), "teststar", 2.0F);
		System.out.println(TESTGALAXY.getName());
		System.out.println(TESTSYSTEM.getUnlocalizedParentGalaxyName());
	}
	
	@Test
	public void getGravity() {
		
		//assertNotEquals("Gravity Calculated: %s", calc((float) AstronomicalConstants.GRAVITY_FIELD) + 6.5, 0.0);
		System.out.println(calcNew(1.50f));
		//System.out.println(BodiesHelper.calculateGravity((float) ((float) AstronomicalConstants.GRAVITY_FIELD + 6.5)));
	}
	
	@Theory
	public float calc(float si) {
		System.out.println("Si Value input: " + si);
		float a = 9.91F - si;
		System.out.println("And we subtract the value of earths own gravity from itself?: " + a);
		float b = 0.085F / 9.81F;
		System.out.println("Now assuming this is Overworld gravity value * earths Si value: " + b);
		float i = a * b;
		System.out.println(i);
		float k = Math.round(i * 1000);
		System.out.println(k);
		return k;
	}
	
	@Theory
	public float calcNew(float si) {
		return (float) (AstronomicalConstants.GRAVITY_FIELD * si / 1000);
	}

	public static void blockIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/" + name + ".png"));
	}

	public static void realIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/realism/" + name + ".png"));
	}

}
