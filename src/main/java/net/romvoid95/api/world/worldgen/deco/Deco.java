package net.romvoid95.api.world.worldgen.deco;

import java.util.Random;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;
import net.minecraft.world.biome.Biome;

public abstract class Deco {
	
    private boolean checkRiver;
    private float minRiver; // Minimum river value required to generate.
    private float maxRiver; // Maximum river value required to generate.

	public Deco() {
        this.checkRiver = false;
        this.minRiver = -2f;
        this.setMaxRiver(2f);
	}
	
    public abstract void generate(final Biome biome, final World world, final Random rand, final ChunkPos chunkPos, final float river, final boolean hasVillage);

	
    public boolean properlyDefined() {
        // this procedure should return true if the deco can respond properly to a generate() call
        // in particular it should not crash.
        return true;
    }
    
    public boolean isCheckRiver() {

        return checkRiver;
    }

    public Deco setCheckRiver(boolean checkRiver) {

        this.checkRiver = checkRiver;
        return this;
    }

    public float getMinRiver() {

        return minRiver;
    }

    public Deco setMinRiver(float minRiver) {

        this.minRiver = minRiver;
        return this;
    }

    public float getMaxRiver() {

        return maxRiver;
    }

    public Deco setMaxRiver(float maxRiver) {
        this.maxRiver = maxRiver;
        return this;
    }
    
    static BlockPos getOffsetPos(final ChunkPos pos) {
        return new BlockPos(pos.x * 16 + 8, 0, pos.z * 16 + 8);
    }

    public static int getRangedRandom(Random rand, int min, int max) {
        return min + rand.nextInt(max - min + 1);
    }

}
