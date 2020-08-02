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

package net.rom.exoplanets.compat;

import com.mjr.planetprogression.api.research.ResearchHooksSP;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraftforge.fml.common.Optional;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

@SideOnly(Side.CLIENT)
public class PlanetProgressionCompat {

	@SideOnly(Side.CLIENT)
	@Optional.Method(modid = "planetprogression")
	public static boolean isReasearched (EntityPlayerSP player, CelestialBody body) {
		return ResearchHooksSP.hasUnlockedCelestialBody(player, body);
	}
}
