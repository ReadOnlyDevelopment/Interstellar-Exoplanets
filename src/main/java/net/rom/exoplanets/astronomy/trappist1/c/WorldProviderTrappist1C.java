package net.rom.exoplanets.astronomy.trappist1.c;

import java.util.List;

import asmodeuscore.api.dimension.IProviderFog;
import asmodeuscore.api.dimension.IProviderFreeze;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_Biome;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_ChunkProvider;
import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProvider;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_CaveGen;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_RavineGen;
import asmodeuscore.core.astronomy.dimension.world.worldengine.standardcustomgen.WE_TerrainGenerator;
import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.block.Block;
import net.minecraft.init.Blocks;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.DimensionType;
import net.minecraft.world.biome.BiomeProvider;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.IChunkGenerator;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom.exoplanets.astronomy.trappist1.TrappistBlocks;
import net.rom.exoplanets.astronomy.trappist1.TrappistDimensions;
import net.rom.exoplanets.astronomy.trappist1.c.biomes.Trappist_1_C_DeepOcean;
import net.rom.exoplanets.astronomy.trappist1.c.biomes.Trappist_1_C_Mountains;
import net.rom.exoplanets.init.InitPlanets;

public class WorldProviderTrappist1C extends WE_WorldProvider implements IProviderFreeze, IProviderFog {

	private final float[] colorsSunriseSunset = new float[4];
	public static WE_ChunkProvider chunk;

	@Override
	public double getFuelUsageMultiplier() {
		return 2.3f;
	}

	@Override
	public float getFallDamageModifier() {
		return 0.06F;
	}

	@Override
	public CelestialBody getCelestialBody() {
		return InitPlanets.trappistc;
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
	public float getGravity() {
		return super.getGravity();
	}

	@Override
	public float getFogDensity(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getFogColor(int x, int y, int z) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void genSettings(WE_ChunkProvider cp) {
		chunk = cp;

		cp.createChunkGen_List.clear();
		cp.createChunkGen_InXZ_List.clear();
		cp.createChunkGen_InXYZ_List.clear();
		cp.decorateChunkGen_List.clear();

		WE_Biome.setBiomeMap(cp, 1.4D, 4, 1000.0D, 1.0D);

		WE_TerrainGenerator terrainGenerator = new WE_TerrainGenerator();
		terrainGenerator.worldStoneBlock = TrappistBlocks.SharedTerrain.TE1_TOP_ALT.getDefaultState();
		terrainGenerator.worldSeaGen = true;
		terrainGenerator.worldSeaGenBlock = Blocks.WATER.getDefaultState();
		terrainGenerator.worldSeaGenMaxY = 64;
		cp.createChunkGen_List.add(terrainGenerator);

		// -//
		WE_CaveGen cg = new WE_CaveGen();
		cg.replaceBlocksList.clear();
		cg.addReplacingBlock(terrainGenerator.worldStoneBlock);
		cg.lavaMaxY = 15;
		cp.createChunkGen_List.add(cg);
		// -//

		WE_RavineGen rg = new WE_RavineGen();
		rg.replaceBlocksList.clear();
		rg.addReplacingBlock(terrainGenerator.worldStoneBlock);
		rg.lavaBlock = Blocks.LAVA.getDefaultState();
		rg.lavaMaxY = 15;
		cp.createChunkGen_List.add(rg);

		cp.worldGenerators.clear();
		cp.biomesList.clear();
		WE_Biome.addBiomeToGeneration(cp, new Trappist_1_C_DeepOcean(-4D, 4D));	
		WE_Biome.addBiomeToGeneration(cp, new Trappist_1_C_Mountains(-1.0D, 1.0D, 100, 2.8D, 4));	
		WE_Biome.addBiomeToGeneration(cp, new Trappist_1_C_Mountains(-0.8D, 0.8D, 180, 2.4D, 4));

	}

	@Override
	public BiomeDecoratorSpace getDecorator() {
		return new BiomeDecoratorT1C();
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
	@SideOnly(Side.CLIENT)
	public float getStarBrightness(float par1) {
		float f = this.world.getCelestialAngle(par1);
		float f1 = 1.0F - (MathHelper.cos(f * ((float) Math.PI * 2F)) * 2.0F + 0.25F);
		f1 = MathHelper.clamp(f1, 0.0F, 1.0F);
		return f1 * f1 * 0.5F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public float getSunBrightness(float par1) {
		float f1 = this.world.getCelestialAngle(1.0F);
		float f2 = 1.0F - (MathHelper.cos(f1 * 3.1415927F * 2.0F) * 2.0F + 0.2F);
		f2 = MathHelper.clamp(f2, 0.0F, 1.0F);

		f2 = 1.2F - f2;
		return f2 * 0.8F;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getFogColor() {
		float f = 1.0F - this.getStarBrightness(1.0F);
		return new Vector3(140 / 255.0F * f, 167 / 255.0F * f, 207 / 255.0F * f);
	}

	@Override
	@SideOnly(Side.CLIENT)
	public Vector3 getSkyColor() {

		float f = 0.5F - this.getStarBrightness(1.0F);
		if (world.isRaining()) {
			f = 1.0F;
			return new Vector3(47 / 255.0F * f, 47 / 255.0F * f, 47 / 255.0F * f);
		}
		return new Vector3(61 / 255.0F * f, 86 / 255.0F * f, 175 / 255.0F * f);

	}

	@Override
	public boolean hasSunset() {
		return true;
	}

	@Override
	public Class<? extends IChunkGenerator> getChunkProviderClass() {
		return ChunkProviderTrappist1C.class;
	}
	
    @Override 
    public Class<? extends BiomeProvider> getBiomeProviderClass() { 
    	return BiomeProviderTrappist1C.class; 
    }

	@Override
	public DimensionType getDimensionType() {
		return TrappistDimensions.TRAPPIST_1C;
	}

	@Override
	public double getHorizon() {
		return 44.0D;
	}

	@Override
	public double getMeteorFrequency() {
		return 3.0;
	}

	@Override
	public float getSoundVolReductionAmount() {
		return Float.MIN_VALUE;
	}

	@Override
	public boolean canRainOrSnow() {
		return false;
	}

	@Override
	public boolean enableAdvancedThermalLevel() {
		return true;
	}

	@Override
	protected float getThermalValueMod() {
		return 0.2F;
	}

}
