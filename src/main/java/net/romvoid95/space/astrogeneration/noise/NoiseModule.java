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
package net.romvoid95.space.astrogeneration.noise;

public abstract class NoiseModule {
	public float	frequencyX	= 1;
	public float	frequencyY	= 1;
	public float	frequencyZ	= 1;
	public float	amplitude	= 1;

	public abstract float getNoise(float i);

	public abstract float getNoise(float i, float j);

	public abstract float getNoise(float i, float j, float k);

	public void setFrequency(float frequency) {
		this.frequencyX = frequency;
		this.frequencyY = frequency;
		this.frequencyZ = frequency;
	}

}
