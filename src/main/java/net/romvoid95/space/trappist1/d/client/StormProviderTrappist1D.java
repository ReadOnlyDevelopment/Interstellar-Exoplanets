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
package net.romvoid95.space.trappist1.d.client;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLivingBase;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.biome.Biome;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber;
import net.romvoid95.api.space.prefab.WorldProviderWE_ExoPlanet;
import net.romvoid95.api.world.weather.StormProvider;
import net.romvoid95.client.gui.rendering.Texture;
import net.romvoid95.common.utility.mc.WorldUtils;
import net.romvoid95.core.ExoInfo;
import net.romvoid95.space.trappist1.d.WorldProviderTrappist1D;

@EventBusSubscriber
public class StormProviderTrappist1D extends StormProvider {

	@Override
	public void updateStorm (World world) {
		if ((world != null) && this.isStormActive(world)) {
			for (Object o : world.loadedEntityList.toArray()) {
				if (o instanceof Entity) {
					Entity entity = (Entity) o;

					if (entity.world.provider instanceof WorldProviderTrappist1D) {
						if (this.apply(entity) && WorldUtils.canSeeSky(new BlockPos(entity), world)) {
							entity.fallDistance = 0F;

						}
					}
				}
			}
		}
	}

	public static final Texture STORM_TEXTURE = new Texture(ExoInfo.MODID, "textures/enviroment/heavyrain.png");

	@Override
	public boolean isStormActive (World world) {
		WorldProviderWE_ExoPlanet provider = (WorldProviderWE_ExoPlanet) world.provider;
		for (EntityLivingBase player : world.playerEntities) {
			if (player.lastTickPosY < provider.getCloudHeight()) {
				return true;
			}
		}
		return false;
	}

	@Override
	public boolean isStormVisibleInBiome (Biome biome) {
		return true;
	}

	@Override
	public float getStormDownfallSpeed () {
		return 14.0F;
	}

	@Override
	public float getStormWindSpeed () {
		return 4.0F;
	}

	@Override
	public boolean doesLightingApply () {
		return false;
	}

	@Override
	public float getStormDirection () {
		return 90F;
	}

	@Override
	public Texture getStormTexture (World world, Biome biome) {
		return STORM_TEXTURE;
	}

	@Override
	public int getStormSize () {
		return 64;
	}

	@Override
	public boolean isStormApplicableTo (WorldProvider provider) {
		return provider instanceof WorldProviderTrappist1D;
	}

	@Override
	public boolean useGroundParticle () {
		return false;
	}
}
