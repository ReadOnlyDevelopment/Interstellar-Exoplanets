/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package com.readonlydev.space.generation.noise;

import java.util.Random;

public class NewGradient extends NewNoiseModule {
	private final NewFishyNoise	noiseGen;
	private final float			offsetX;
	private final float			offsetY;
	private final float			offsetZ;
	private final int			numOctaves;
	private final float			persistance;

	public NewGradient (long seed, int nOctaves, float p) {
		this.numOctaves = nOctaves;
		this.persistance = p;
		final Random rand = new Random(seed);
		this.offsetX = rand.nextFloat() / 2 + 0.01F;
		this.offsetY = rand.nextFloat() / 2 + 0.01F;
		this.offsetZ = rand.nextFloat() / 2 + 0.01F;
		this.noiseGen = new NewFishyNoise(seed);
	}

	@Override
	public float getNoise(float i) {
		i *= this.frequencyX;
		float val = 0;
		float curAmplitude = this.amplitude;
		for (int n = 0; n < this.numOctaves; n++) {
			val += this.noiseGen.noise2d(i + this.offsetX, this.offsetY) * curAmplitude;
			i *= 2;
			curAmplitude *= this.persistance;
		}
		return val;
	}

	@Override
	public float getNoise(float i, float j) {
		if (this.numOctaves == 1) {
			return this.noiseGen.noise2d(i * this.frequencyX + this.offsetX, j * this.frequencyY + this.offsetY)
					* this.amplitude;
		}

		i *= this.frequencyX;
		j *= this.frequencyY;
		float val = 0;
		float curAmplitude = this.amplitude;
		for (int n = 0; n < this.numOctaves; n++) {
			val += this.noiseGen.noise2d(i + this.offsetX, j + this.offsetY) * curAmplitude;
			i *= 2;
			j *= 2;
			curAmplitude *= this.persistance;
		}
		return val;
	}

	@Override
	public float getNoise(float i, float j, float k) {
		if (this.numOctaves == 1) {
			return this.noiseGen.noise3d(i * this.frequencyX + this.offsetX, j * this.frequencyY + this.offsetY,
					k * this.frequencyZ + this.offsetZ) * this.amplitude;
		}

		i *= this.frequencyX;
		j *= this.frequencyY;
		k *= this.frequencyZ;
		float val = 0;
		float curAmplitude = this.amplitude;
		for (int n = 0; n < this.numOctaves; n++) {
			val += this.noiseGen.noise3d(i + this.offsetX, j + this.offsetY, k + this.offsetZ) * curAmplitude;
			i *= 2;
			j *= 2;
			k *= 2;
			curAmplitude *= this.persistance;
		}
		return val;
	}

}
