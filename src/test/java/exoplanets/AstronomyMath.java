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

import static org.junit.Assert.*;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import asmodeuscore.core.astronomy.BodiesHelper;
import asmodeuscore.core.astronomy.BodiesHelper.Galaxies;
import micdoodle8.mods.galacticraft.api.galaxies.SolarSystem;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.util.ResourceLocation;
import net.rom.api.stellar.calc.AstronomicalConstants;
import net.rom.api.stellar.calc.Calculations;
import net.rom.api.stellar.enums.EnumLuminosityClass;
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

	public static void blockIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/" + name + ".png"));
	}

	public static void realIcon(ExoPlanet planet, String name) {
		int i = name.length();
		planet.setBodyIcon(new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/" + name.substring(i - 2, i) + "/realism/" + name + ".png"));
	}

}
