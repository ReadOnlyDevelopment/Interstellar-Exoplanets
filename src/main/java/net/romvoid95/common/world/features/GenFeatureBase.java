package net.romvoid95.common.world.features;

import java.util.*;

import net.minecraft.block.Block;
import net.minecraft.block.state.IBlockState;
import net.minecraft.init.Blocks;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.ChunkPos;
import net.minecraft.world.World;

public abstract class GenFeatureBase {

	protected IBlockState block; // This can be any block.
	protected float strengthFactor; // Higher = more/bigger boulders.
	protected int minY; // Lower height restriction.
	protected int maxY; // Upper height restriction.
	protected HeightType heightType; // How we determine the Y coord.
	protected int chance; // Higher = more rare.
	protected boolean water;
	protected List<Block> validGroundBlocks;

	public GenFeatureBase(Block featureBlock) {
		this.block = featureBlock.getDefaultState();
		/*Default values*/
		this.setStrengthFactor(2f);
		this.setMinY(60); // Sensible lower height limit by default.
		this.setMaxY(255); // No upper height limit by default.
		this.setHeightType(HeightType.GET_HEIGHT_VALUE);
		this.setChance(10);
		this.water = true;
		this.validGroundBlocks = Arrays.asList(Blocks.GRASS, Blocks.DIRT, Blocks.STONE, Blocks.GRAVEL, Blocks.CLAY, Blocks.SAND);
	}

	public abstract void generate(World world, Random rand, ChunkPos chunkPos);

	public IBlockState getFeatureBlock() {
		return block;
	}

	public float getStrengthFactor() {

		return strengthFactor;
	}

	public GenFeatureBase setStrengthFactor(float strengthFactor) {

		this.strengthFactor = strengthFactor;
		return this;
	}

	public int getMinY() {

		return minY;
	}

	public GenFeatureBase setMinY(int minY) {

		this.minY = minY;
		return this;
	}

	public int getMaxY() {

		return maxY;
	}

	public GenFeatureBase setMaxY(int maxY) {

		this.maxY = maxY;
		return this;
	}

	public int getChance() {

		return chance;
	}

	public GenFeatureBase setChance(int chance) {

		this.chance = chance;
		return this;
	}

	public boolean isWater() {

		return water;
	}

	public GenFeatureBase setWater(boolean water) {

		this.water = water;
		return this;
	}

	public HeightType getHeightType() {

		return heightType;
	}

	public GenFeatureBase setHeightType(HeightType heightType) {

		this.heightType = heightType;
		return this;
	}

	public List<Block> getValidGroundBlocks() {

		return Collections.unmodifiableList(this.validGroundBlocks);
	}

	public GenFeatureBase setValidGroundBlocks(ArrayList<Block> validGroundBlocks) {

		this.validGroundBlocks = Collections.unmodifiableList(validGroundBlocks);
		return this;
	}

	public enum HeightType {
		NEXT_INT,
		GET_HEIGHT_VALUE
	}

	static BlockPos getOffsetPos(final ChunkPos pos) {
		return new BlockPos((pos.x * 16) + 8, 0, (pos.z * 16) + 8);
	}

	public static int getRangedRandom(Random rand, int min, int max) {
		return min + rand.nextInt((max - min) + 1);
	}
}
