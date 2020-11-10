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

package net.romvoid95.space.kepler1649.c;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProviderSpace;
import asmodeuscore.core.utils.worldengine.WE_Biome;
import asmodeuscore.core.utils.worldengine.WE_ChunkProvider;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_CaveGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_RavineGen;
import asmodeuscore.core.utils.worldengine.standardcustomgen.WE_TerrainGenerator;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.core.util.ConfigManagerCore;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.space.prefab.WorldProviderWE_ExoPlanet;
import net.romvoid95.api.space.utility.AstronomicalConstants;
import net.romvoid95.api.world.ExoDimensions;
import net.romvoid95.core.initialization.Planets;
import net.romvoid95.space.kepler1649.c.biomes.Dunes;
import net.romvoid95.space.kepler1649.c.biomes.TestHighMountains;
import net.romvoid95.space.kepler1649.c.biomes.TestPlains;
import net.romvoid95.space.trappist1.TrappistBlocks;

public class WorldProviderKepler1649c extends WorldProviderWE_ExoPlanet {

	public static WE_ChunkProvider chunk;

	@Override
	public boolean enableAdvancedThermalLevel () {
		return true;
	}

	@Override
	public void genSettings (WE_ChunkProvider cp) {
		chunk = cp;

		cp.createChunkGen_List.clear();
		cp.createChunkGen_InXZ_List.clear();
		cp.createChunkGen_InXYZ_List.clear();
		cp.decorateChunkGen_List.clear();

		WE_Biome.setBiomeMap(cp, 5.5D, 3, 12000.0D, 0.5D);

		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator();
		terrainGenerator.worldStoneBlock  = TrappistBlocks.TrappistC.TRAP1C_TOP.getDefaultState();
		terrainGenerator.worldSeaGen      = false;
		terrainGenerator.worldSeaGenBlock = Blocks.WATER.getDefaultState();
		terrainGenerator.worldSeaGenMaxY  = 64;
		cp.createChunkGen_List.add(terrainGenerator);

		WE_CaveGen cg = new WE_CaveGen();
		cg.replaceBlocksList.clear();
		cg.addReplacingBlock(terrainGenerator.worldStoneBlock);
		cp.createChunkGen_List.add(cg);

		WE_RavineGen rg = new WE_RavineGen();
		rg.replaceBlocksList.clear();
		rg.addReplacingBlock(terrainGenerator.worldStoneBlock);
		rg.lavaBlock = Blocks.LAVA.getDefaultState();
		cp.createChunkGen_List.add(rg);

		((WE_ChunkProviderSpace) cp).worldGenerators.clear();
		cp.biomesList.clear();

		WE_Biome.addBiomeToGeneration(cp, new Dunes(0.0D, 0.0D));
		WE_Biome.addBiomeToGeneration(cp, new TestPlains(-1.2D, 1.3D));
		WE_Biome.addBiomeToGeneration(cp, new TestHighMountains(-5.4D, 5.4D));

	}

	@Override
	public CelestialBody getCelestialBody () {
		return Planets.KEPLER1649C;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass () {
		return WE_ChunkProviderSpace.class;

	}

	@Override
	public float getCloudHeight () {
		return 63.0F;
	}

	@Override
	public long getDayLength () {
		return 24000L;
	}

	@Override
	public DimensionType getDimensionType () {
		return ExoDimensions.KEPLER1649_C;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public int getDungeonSpacing () {
		return 0;
	}

	@Override
	public float getFallDamageModifier () {
		return 0;
	}

	@Override
	public Vector3 getFogColor () {
		float f = 0.6F - this.getStarBrightness(1.0F);
		return new Vector3((213f / 255F) * f, (72f / 255F) * f, (3f / 255F) * f);
	}

	@Override
	public double getFuelUsageMultiplier () {
		return 1.5;
	}

	@Override
	public float getGravity () {
		return 0.015f;
	}

	@Override
	public double getHorizon () {
		return 44.0D;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void getLightmapColors (float partialTicks, float sunBrightness, float skyLight, float blockLight, float[] colors) {
		EntityPlayer player = FMLClientHandler.instance().getClientPlayerEntity();

		if (player != null) {
			int phase = this.getMoonPhase(this.getWorldTime());
			if ((skyLight > 0) && (sunBrightness > 0.07f) && (phase != 0) && (phase != 6)) {

				colors[0] = colors[0] + skyLight + 0.3F;
				colors[1] = colors[1] + (skyLight / 6);
			}
		}
	}

	@Override
	public double getMeteorFrequency() {
		return 0;
	}

	@Override
	public int getMoonPhase (long worldTime) {
		return (int) (((worldTime / this.getDayLength()) % 8L) + 8L) % 8;
	}

	@Override
	public Block getPlanetGrassBlock() {
		return null;
	}

	@Override
	public Vector3 getSkyColor () {
		float f = 0.3F - this.getStarBrightness(1.0F);
		return new Vector3((228 / 255.0F) * f, (75 / 255.0F) * f, (1 / 255.0F) * f);

	}

	@Override
	public float getSolarSize () {
		return 0.3F / this.getCelestialBody().getRelativeDistanceFromCenter().unScaledDistance;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getStarBrightness (float partialTicks) {
		float angle = this.world.getCelestialAngle(partialTicks);
		float value = 1.0F - ((MathHelper.cos(angle * AstronomicalConstants.TWO_PI_F) * 2.0F) + 0.25F);
		value = MathHelper.clamp(value, 0.0F, 1.0F);
		return (value * value * 0.5F) + 0.3F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness (float partialTicks) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - ((MathHelper.cos(f1 * AstronomicalConstants.TWO_PI_F) * 2.0F) + 0.2F);
		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);
		f2 = 1.2F - f2;
		return f2 * 0.8F;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		return null;
	}

	@Override
	protected float getThermalValueMod () {
		return 0.2F;
	}

	@Override
	public boolean hasSunset () {
		return false;
	}

	@Override
	public void onChunkProvider (int cX, int cZ, ChunkPrimer primer) {

	}

	@Override
	public void onPopulate (int cX, int cZ) {

	}
	
	@Override
	public void recreateStructures (Chunk chunkIn, int x, int z) {

	}
	
	@Override
	public boolean shouldForceRespawn () {
		return !ConfigManagerCore.forceOverworldRespawn;
	}
}