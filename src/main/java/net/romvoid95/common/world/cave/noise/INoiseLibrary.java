package net.romvoid95.common.world.cave.noise;

/**
 * Tagging interface for noise libraries (FastNoise, OpenSimplex2S)
 */
public interface INoiseLibrary {
    float GetNoise(float x, float y, float z);
}
