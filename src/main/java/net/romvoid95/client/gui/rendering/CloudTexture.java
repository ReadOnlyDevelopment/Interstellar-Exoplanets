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
package net.romvoid95.client.gui.rendering;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.ExoInfo;
import net.romvoid95.client.RGB;
import net.romvoid95.common.CommonUtil;

public class CloudTexture extends ResourceLocation {

	private RGB color;
	private float height;
	private int speed;
	private static String domain = ExoInfo.MODID;

	/**
	 * Instantiates a new texture.
	 *
	 * @param domain the domain
	 * @param location the location
	 */
	public CloudTexture(String location, float height, int speed, RGB color) {
		super(domain, location);
		this.height = height;
		this.speed = speed;
		this.color = color;
	}

	/**
	 * Bind.
	 */
	@SideOnly(Side.CLIENT)
	public void bind () {
		CommonUtil.bindTexture(this);
	}
	
	public float getHeight() {
		return height;
	}

	public int getSpeed() {
		return speed;
	}
	
	public RGB getColor() {
		return color;
	}
}