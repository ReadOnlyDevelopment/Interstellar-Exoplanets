package net.romvoid95.api.world.worldgen.feature.tree;

import net.minecraft.block.state.IBlockState;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import net.romvoid95.core.ExoplanetsMod;

public class ExoGenTreeData {
    public boolean firstLog = false;
    public boolean firstLeaves = false;

    public int minXLog = 0;
    public int maxXLog = 0;
    public int minZLog = 0;
    public int maxZLog = 0;
    public int minXLeaves = 0;
    public int maxXLeaves = 0;
    public int minZLeaves = 0;
    public int maxZLeaves = 0;
    public int logRangeX = 0;
    public int logRangeZ = 0;
    public int leavesRangeX = 0;
    public int leavesRangeZ = 0;

    public ExoGenTreeData() {

    }

    public void placeLogBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag) {

        if (firstLog) {
            minXLog = (pos.getX() < minXLog) ? pos.getX() : minXLog;
            maxXLog = (pos.getX() > maxXLog) ? pos.getX() : maxXLog;
            minZLog = (pos.getZ() < minZLog) ? pos.getZ() : minZLog;
            maxZLog = (pos.getZ() > maxZLog) ? pos.getZ() : maxZLog;
        }
        else {
            minXLog = pos.getX();
            maxXLog = pos.getX();
            minZLog = pos.getZ();
            maxZLog = pos.getZ();

            firstLog = true;
        }

        updateRanges();
    }

    public void placeLeavesBlock(World world, BlockPos pos, IBlockState logBlock, int generateFlag) {

        if (firstLeaves) {
            minXLeaves = (pos.getX() < minXLeaves) ? pos.getX() : minXLeaves;
            maxXLeaves = (pos.getX() > maxXLeaves) ? pos.getX() : maxXLeaves;
            minZLeaves = (pos.getZ() < minZLeaves) ? pos.getZ() : minZLeaves;
            maxZLeaves = (pos.getZ() > maxZLeaves) ? pos.getZ() : maxZLeaves;
        }
        else {
            minXLeaves = pos.getX();
            maxXLeaves = pos.getX();
            minZLeaves = pos.getZ();
            maxZLeaves = pos.getZ();

            firstLeaves = true;
        }

        updateRanges();
    }

    public void dumpTreeData() {

        if (logRangeX > 16) {
            ExoplanetsMod.logger.error("Log X range = {} to {} ({} blocks)", minXLog, maxXLog, logRangeX);
        }
        else {
        	ExoplanetsMod.logger.info("Log X range = {} to {} ({} blocks)", minXLog, maxXLog, logRangeX);
        }

        if (logRangeZ > 16) {
        	ExoplanetsMod.logger.error("Log Z range = {} to {} ({} blocks)", minZLog, maxZLog, logRangeZ);
        }
        else {
            ExoplanetsMod.logger.info("Log Z range = {} to {} ({} blocks)", minZLog, maxZLog, logRangeZ);
        }

        if (leavesRangeX > 16) {
        	ExoplanetsMod.logger.error("Leaves X range = {} to {} ({} blocks)", minXLeaves, maxXLeaves, leavesRangeX);
        }
        else {
        	ExoplanetsMod.logger.info("Leaves X range = {} to {} ({} blocks)", minXLeaves, maxXLeaves, leavesRangeX);
        }

        if (leavesRangeZ > 16) {
        	ExoplanetsMod.logger.error("Leaves Z range = {} to {} ({} blocks)", minZLeaves, maxZLeaves, leavesRangeZ);
        }
        else {
        	ExoplanetsMod.logger.info("Leaves Z range = {} to {} ({} blocks)", minZLeaves, maxZLeaves, leavesRangeZ);
        }
    }

    public void updateRanges() {
        logRangeX = Math.abs(maxXLog - minXLog);
        logRangeZ = Math.abs(maxZLog - minZLog);
        leavesRangeX = Math.abs(maxXLeaves - minXLeaves);
        leavesRangeZ = Math.abs(maxZLeaves - minZLeaves);
    }
}
