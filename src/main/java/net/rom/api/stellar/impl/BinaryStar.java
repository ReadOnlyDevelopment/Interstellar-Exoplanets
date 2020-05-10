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

package net.rom.api.stellar.impl;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.galaxies.Planet;
import net.rom.api.stellar.enums.EnumSpectralClass;
import net.rom.api.stellar.interfaces.IBinary;
import net.rom.api.stellar.interfaces.IExoStar;



/**
 * My attempt at creating a true binary star system. Which would have two
 * Star companions orbit each other around a center-of-mass point on the
 * GuiCelestialScreen while also having 
 * 
 * @author ROMVoid
 *
 */
public abstract class BinaryStar extends CelestialBody implements IBinary {

	private EnumSpectralClass spectralClass;
	private BinaryStar companionStar;
	private String starName;
	private int surfaceTemp;
	private double radius;
	private double mass;

	public BinaryStar(String bodyName) {
		super(bodyName);
		// TODO Auto-generated constructor stub
	}

	@Override
	public String getStarName() {
		return null;
	}

	@Override
	public int getSurfaceTemp() {
		return 0;
	}

	@Override
	public double getStellarRadius() {
		return 0;
	}

	@Override
	public double getMass() {
		return 0;
	}

	@Override
	public EnumSpectralClass getSpectralClassifcation() {
		return null;
	}

	@Override
	public BinaryStar getCompanionStar() {
		return null;
	}

	@Override
	public int getID() {
		return 0;
	}

	@Override
	public String getUnlocalizedNamePrefix() {
		return null;
	}
	
	

}
