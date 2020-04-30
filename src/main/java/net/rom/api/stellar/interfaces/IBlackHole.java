package net.rom.api.stellar.interfaces;

import net.minecraft.util.math.BlockPos;

public interface IBlackHole {

    BlockPos getSingularityPos();

    double getSolarMass();

    double getEventHorizon();

    boolean isRotating();

}
