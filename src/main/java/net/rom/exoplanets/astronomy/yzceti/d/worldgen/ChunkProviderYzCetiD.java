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

package net.rom.exoplanets.astronomy.yzceti.d.worldgen;

import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import net.minecraft.init.Blocks;
import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.rom.exoplanets.astronomy.yzceti.YzCetiBlocks;
import net.rom.exoplanets.internal.world.chunk.ExoChunkProviderMultiSpace;
import net.rom.exoplanets.internal.world.gen.MapGenBaseMeta;
import net.rom.exoplanets.internal.world.gen.MapGenExoCave;
import net.rom.exoplanets.internal.world.gen.MapGenExoRavinGen;

public class ChunkProviderYzCetiD extends ExoChunkProviderMultiSpace {

    private final BiomeDecoratorYzCetiD cetiDBiomeDecorator = new BiomeDecoratorYzCetiD();
    private final MapGenExoRavinGen ravineGenerator = new MapGenExoRavinGen();
    private final MapGenExoCave caveGenerator = new MapGenExoCave(YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState(), Blocks.LAVA.getDefaultState(),
            Sets.newHashSet(YzCetiBlocks.CetiD.D_SEDIMENTARYROCK, YzCetiBlocks.CetiD.D_IGNEOUS));

    public ChunkProviderYzCetiD(World par1World, long seed, boolean mapFeaturesEnabled) {
        super(par1World, seed, mapFeaturesEnabled);
        this.stoneBlock = YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState();
        this.waterBlock = Blocks.WATER.getDefaultState();
    }

    @Override
    protected List<MapGenBaseMeta> getWorldGenerators() {
        List<MapGenBaseMeta> generators = Lists.newArrayList();
        generators.add(this.caveGenerator);
        return generators;
    }

    @Override
    public int getCraterProbability() {
        return 2000;
    }

    @Override
    public void onChunkProvide(int cX, int cZ, ChunkPrimer primer) {
        this.ravineGenerator.generate(this.worldObj, cX, cZ, primer);
    }

    @Override
    public void onPopulate(int cX, int cZ) {

    }

    @Override
    public void recreateStructures(Chunk chunk, int x, int z) {
    }

    @Override
    protected void decoratePlanet(World world, Random rand, int x, int z) {
        this.cetiDBiomeDecorator.decorate(this.worldObj, rand, x, z);
    }
}
