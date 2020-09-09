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

package net.romvoid95.common.astronomy.trappist1.e;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.romvoid95.client.Assets;

public class SkyProviderTrappist1E extends SkyProviderBase {

	@Override
	protected void rendererSky (Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {

		if (!this.mc.world.isRaining()) {
			this.renderImage(Assets.getCelestialTexture("trappist1b"), 0, 0, this
					.getCelestialAngle(getDayLenght()), 5.5F);
			this.renderImage(Assets.getCelestialTexture("trappist1d"), 40, 0, this
					.getCelestialAngle((long) (getDayLenght() * 1.2)) + 80F, 1.5F);
		}

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
		return 5.0F;
	}

	@Override
	protected ResourceLocation sunImage () {
		return Assets.getCelestialTexture("trappist1star");
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
	protected Vector3 getAtmosphereColor () {
		return null;
	}

	@Override
	public int expandSizeAura () {
		return 10;
	}

}
