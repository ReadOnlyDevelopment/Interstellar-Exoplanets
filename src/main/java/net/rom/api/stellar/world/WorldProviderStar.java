package net.rom.api.stellar.world;

import java.util.List;

import micdoodle8.mods.galacticraft.api.galaxies.CelestialBody;
import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IExitHeight;
import micdoodle8.mods.galacticraft.api.world.ISolarLevel;
import micdoodle8.mods.galacticraft.api.world.IZeroGDimension;
import net.minecraft.block.Block;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import net.minecraft.world.DimensionType;
import net.minecraft.world.gen.IChunkGenerator;

public class WorldProviderStar extends WorldProviderSpace implements IZeroGDimension, ISolarLevel, IExitHeight {

    @Override
    public float getGravity() {
        return 0;
    }

    @Override
    public double getFuelUsageMultiplier() {
        return 0;
    }

    @Override
    public boolean canSpaceshipTierPass(int tier) {
        return false;
    }

    @Override
    public float getFallDamageModifier() {
        return 0;
    }

    @Override
    public CelestialBody getCelestialBody() {
        return null;
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
    public double getYCoordinateToTeleport() {
        return 0;
    }

    @Override
    public double getSolarEnergyMultiplier() {
        return 0;
    }

    @Override
    public boolean inFreefall(Entity entity) {
        return false;
    }

    @Override
    public void setInFreefall(Entity entity) {
    }

    @Override
    public Vector3 getFogColor() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public Vector3 getSkyColor() {
        return new Vector3(0, 0, 0);
    }

    @Override
    public boolean hasSunset() {
        return false;
    }

    @Override
    public long getDayLength() {
        return 0;
    }

    @Override
    public Class<? extends IChunkGenerator> getChunkProviderClass() {
        return null;
    }

    @Override
    public DimensionType getDimensionType() {
        return null;
    }

}
