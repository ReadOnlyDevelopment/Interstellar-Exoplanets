package net.romvoid95.common.world.noise;

import net.minecraft.util.math.MathHelper;

public interface INoise {
	void setFreq (double freq);

	void setAmpl (double ampl);

	void noise2D (double[] buffer, double originX, double originY, int sizeX, int sizeY);

	void noise3D (double[] buffer, double originX, double originY, double originZ, int sizeX, int sizeY, int sizeZ);

	default double precision (double coordinate) {
		long   origin       = MathHelper.lfloor(coordinate);
		double intermediate = coordinate - (double) origin;
		return intermediate + (origin % 16777216L);
	}
}
