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
package com.readonlydev.space.trappist1.d.client;

import com.readonlydev.ExoInfo;
import com.readonlydev.api.world.weather.CloudProvider;
import com.readonlydev.client.gui.rendering.Texture;
import com.readonlydev.space.trappist1.d.WorldProviderTrappist1D;

import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;

@EventBusSubscriber
public class CloudProviderTrappist1D extends CloudProvider {

	private static final Texture CLOUDS = new Texture(ExoInfo.MODID, "textures/world/varda-clouds.png");

	@Override
	public float getMaxCloudSpeedDuringStorm () {
		return 32;
	}

	@Override
	public float getMaxNormalCloudSpeed () {
		return 12;
	}

	@Override
	public Texture getCloudTexture () {
		return CLOUDS;
	}

	@Override
	public boolean areCloudsApplicableTo (WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public double getCloudMovementX (World world, float cloudTicksPrev, float cloudTicks) {
		return -super.getCloudMovementX(world, cloudTicksPrev, cloudTicks);
	}

	@Override
	public void render (float partialTicks, WorldClient world, Minecraft mc) {
		super.render(partialTicks, world, mc);
	}
}
