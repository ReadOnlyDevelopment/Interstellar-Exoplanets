package net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeAdaptive;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.BiomeDecoratorSpace;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.rom.api.stellar.world.gen.GenUtility;
import net.rom.exoplanets.astronomy.yzcetisystem.YzCetiBlocks;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.biomes.BiomeGenYzCetiD;
import net.rom.exoplanets.astronomy.yzcetisystem.d.worldgen.biomes.BiomeGenYzCetiMoltenMantleSea;
import net.rom.exoplanets.init.ExoFluids;

public class BiomeDecoratorYzCetiD extends BiomeDecoratorSpace {

    private int LakesPerChunk = 5;

    private World currentWorld;

    private boolean isDecorating = false;

    public BiomeDecoratorYzCetiD() {
    }

    @Override
    protected void setCurrentWorld(World world) {
        this.currentWorld = world;
    }

    @Override
    protected World getCurrentWorld() {
        return this.currentWorld;
    }

    @Override
    protected void decorate() {
    }
}
