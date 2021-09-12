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

package com.readonlydev.space.yzceti.b;

import org.lwjgl.opengl.GL11;

import com.readonlydev.client.Assets;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;

public class SkyProviderYzCetiB extends SkyProviderBase {

	@Override
	protected void rendererSky (Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	@Override
	protected ModeLight modeLight () {
		return ModeLight.DEFAULT;
	}

	@Override
	protected boolean enableBaseImages () {
		return true;
	}

	@Override
	protected float sunSize () {
		return 30.0F;
	}

	@Override
	protected ResourceLocation sunImage () {
		return Assets.getCelestialTexture("yzcetistar");
	}

	@Override
	protected boolean enableStar () {
		return true;
	}

	@Override
	protected StarColor colorSunAura () {
		return StarColor.RED;
	}

	@Override
	public int expandSizeAura () {
		return 10;
	}

	@Override
	protected Vector3 getAtmosphereColor () {
		return null;
	}
}
