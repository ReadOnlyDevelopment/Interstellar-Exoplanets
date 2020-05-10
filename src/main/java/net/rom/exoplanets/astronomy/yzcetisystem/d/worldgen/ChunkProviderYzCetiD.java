/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen;

import java.util.List;
import java.util.Random;

import net.minecraft.world.World;
import net.minecraft.world.chunk.Chunk;
import net.minecraft.world.chunk.ChunkPrimer;
import net.minecraft.world.gen.MapGenBase;
import net.rom.api.stellar.world.chunk.ExoChunkProviderMultiSpace;
import net.rom.api.stellar.world.gen.MapGenBaseMeta;
import net.rom.api.stellar.world.gen.MapGenExoCave;
import net.rom.api.stellar.world.gen.MapGenExoRavinGen;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.init.ExoFluids;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

public class ChunkProviderYzCetiD extends ExoChunkProviderMultiSpace {

    private final BiomeDecoratorYzCetiD cetiDBiomeDecorator = new BiomeDecoratorYzCetiD();
    private final MapGenExoRavinGen ravineGenerator = new MapGenExoRavinGen();
    private final MapGenExoCave caveGenerator = new MapGenExoCave(YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState(), ExoFluids.fluidBlockMantle.getDefaultState(),
            Sets.newHashSet(YzCetiBlocks.CetiD.D_SEDIMENTARYROCK, YzCetiBlocks.CetiD.D_IGNEOUS));

    public ChunkProviderYzCetiD(World par1World, long seed, boolean mapFeaturesEnabled) {
        super(par1World, seed, mapFeaturesEnabled);
        this.stoneBlock = YzCetiBlocks.CetiD.D_SEDIMENTARYROCK.getDefaultState();
        this.waterBlock = ExoFluids.fluidBlockMantle.getDefaultState();
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
