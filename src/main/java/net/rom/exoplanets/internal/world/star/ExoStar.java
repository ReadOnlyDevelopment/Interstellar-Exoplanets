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

package net.rom.exoplanets.internal.world.star;

import micdoodle8.mods.galacticraft.api.galaxies.Star;
import net.rom.exoplanets.internal.enums.EnumLuminosityClass;
import net.rom.exoplanets.internal.enums.EnumSpectralClass;

public class ExoStar extends Star implements IExoStar {

	private EnumSpectralClass spectralClass;
	private EnumLuminosityClass luminosityClass;
	private String starName;
	private int surfaceTemp;
	private double radius;
	private double mass;


	public ExoStar(String starName) {
		super(starName);
		this.setSpectralClass();
	}

	public ExoStar setStarName(String starName) {
		this.starName = starName;
		return this;
	}

	public ExoStar setSurfaceTemp(int surfaceTemp) {
		this.surfaceTemp = surfaceTemp;
		return this;
	}

	public ExoStar setStarMass(double mass) {
		this.mass = mass;
		return this;
	}

	public ExoStar setStarRadius(double radius) {
		this.radius = radius;
		return this;
	}

	public ExoStar setSpectralClass() {
		this.spectralClass = EnumSpectralClass.getClass(getSurfaceTemp());
		return this;
	}
	
	public ExoStar setLuminosityClass(EnumLuminosityClass luminosityClass) {
		this.luminosityClass = luminosityClass;
		return this;
	}
	
	@Override
	public String getStarName() {
		return this.starName;
	}

	@Override
	public int getSurfaceTemp() {
		return this.surfaceTemp;
	}

	@Override
	public double getStellarRadius() {
		return this.radius;
	}

	@Override
	public EnumSpectralClass getSpectralClassifcation() {
		return this.spectralClass;
	}
	
	public EnumLuminosityClass getLuminosityClass() {
		return this.luminosityClass;
	}

	@Override
	public double getMass() {
		return this.mass;
	}


}
