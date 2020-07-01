/**
 * Interstellar-Exoplanets
 * Copyright (C) 2020
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
package net.rom.exoplanets.internal.world.planet;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.gen.WorldProviderAdvancedSpace;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProvider;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProvider;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.rom.exoplanets.ExoplanetsMod;

public class WorldEngineProvider extends WE_WorldProvider {
	float rainfall = 0.1F;
	
	public WE_ChunkProvider chunk_provider;
	
	@Override
	public void init() {
		super.init();
		System.out.println("////////////////////////////////////-"                                      );
		System.out.println("//#===============================//=* Version: " + "1.12.2-1.0.0"   );
		System.out.println("//#=-------| WorldEngine |-------=//=* By Vamig Aliev (vk.com/win_vista)."  );
		System.out.println("//#===============================//=* Port by BlesseNtumble.");
		System.out.println("////////////////////////////////////-"                                      );
		//-//
		System.out.println("WorldEngine: -Registering WorldEngine..."          );
		//biomeProvider = new WorldChunkManagerHell(new WE_Biome(we_id, true), rainfall);
		System.out.println("WorldEngine: -Registration completed successfully!");
    }
	
	@Override
	public IChunkGenerator createChunkGenerator() {
		System.out.println("WorldEngine: -Starting WorldEngine..."          );
		chunk_provider = new WE_ChunkProvider((WE_WorldProvider)this);
		WE_Biome.setChunkProvider(chunk_provider);
		System.out.println("WorldEngine: -WorldEngine started successfully!");
		//-//
		return chunk_provider;
	}

	@Override
	public double getFuelUsageMultiplier() {
		return 0;
	}

	@Override
	public float getFallDamageModifier() {
		return 0;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return null;
	}

	@Override
	public int getDungeonSpacing() {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType() {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks() {
		return null;
	}

	@Override
	public void genSettings(WE_ChunkProvider cp) {
	}

	@Override
	public BiomeDecoratorSpace getDecorator() {
		return null;
	}

	@Override
	public void onChunkProvider(int cX, int cZ, ChunkPrimer primer) {
	}

	@Override
	public void onPopulate(int cX, int cZ) {
	}

	@Override
	public void recreateStructures(Chunk chunkIn, int x, int z) {
	}

	@Override
	public Vector3 getFogColor() {
		return null;
	}

	@Override
	public Vector3 getSkyColor() {
		return null;
	}

	@Override
	public boolean hasSunset() {
		return false;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return null;
	}

	@Override
	public DimensionType getDimensionType() {
		return null;
	}
}
