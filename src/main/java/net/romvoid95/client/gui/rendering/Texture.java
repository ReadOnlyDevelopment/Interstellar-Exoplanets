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
import net.romvoid95.common.CommonUtil;

public class Texture extends ResourceLocation {

	/**
	 * Instantiates a new texture.
	 *
	 * @param resource the resource
	 */
	public Texture(ResourceLocation resource) {
		this(resource.getResourceDomain(), resource.getResourcePath());
	}

	/**
	 * Instantiates a new texture.
	 *
	 * @param location the location
	 */
	public Texture(String location) {
		super(location);
	}

	/**
	 * Instantiates a new texture.
	 *
	 * @param domain the domain
	 * @param location the location
	 */
	public Texture(String domain, String location) {
		super(domain, location);
	}

	/**
	 * Bind.
	 */
	@SideOnly(Side.CLIENT)
	public void bind () {
		CommonUtil.bindTexture(this);
	}
}