package net.romvoid95.common.world;

import java.util.Random;

import net.minecraft.block.BlockDirt;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;

import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

import net.romvoid95.api.world.worldgen.feature.ExoGenPatch;


public class BiomeRockyPlateau extends Biome {

    protected static final ExoGenPatch COARSE_DIRT_PATCH_FEATURE = new ExoGenPatch(Blocks.DIRT.getDefaultState().withProperty(BlockDirt.VARIANT, BlockDirt.DirtType.COARSE_DIRT), 4);
    protected static final ExoGenPatch STONE_PATCH_FEATURE = new ExoGenPatch(Blocks.STONE.getDefaultState(), 5);
    protected static final WorldGenBlockBlob COBBLESTONE_BOULDER_FEATURE = new WorldGenBlockBlob(Blocks.COBBLESTONE, 1);

    public BiomeRockyPlateau(BiomeProperties properties) {
        super(properties);
        properties.setTemperature(0.8F);
        properties.setRainfall(0.2F);
        properties.setBaseHeight(1.6F);
        properties.setHeightVariation(0.5F);
        decorator.treesPerChunk = 0;
        decorator.extraTreeChance = 0;
        decorator.flowersPerChunk = 0;
        decorator.grassPerChunk = 8;

        spawnableCreatureList.clear();
    }

    @Override
    public void decorate(World worldIn, Random rand, BlockPos pos) {
        int coarseDirtChance = rand.nextInt(7);
        if (coarseDirtChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            COARSE_DIRT_PATCH_FEATURE.generate(worldIn, rand, blockpos);
        }

        int stoneChance = rand.nextInt(9);
        if (stoneChance == 0) {
            int k6 = rand.nextInt(16) + 8;
            int l = rand.nextInt(16) + 8;
            BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
            STONE_PATCH_FEATURE.generate(worldIn, rand, blockpos);
        }

        if ( TerrainGen.decorate(worldIn, rand, new ChunkPos(pos), DecorateBiomeEvent.Decorate.EventType.ROCK)) {
            int genChance = rand.nextInt(3);
            if (genChance == 0) {
                int k6 = rand.nextInt(16) + 8;
                int l = rand.nextInt(16) + 8;
                BlockPos blockpos = worldIn.getHeight(pos.add(k6, 0, l));
                COBBLESTONE_BOULDER_FEATURE.generate(worldIn, rand, blockpos);
            }
        }
        super.decorate(worldIn, rand, pos);
    }
}
