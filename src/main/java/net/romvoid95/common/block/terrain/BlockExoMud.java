package net.romvoid95.common.block.terrain;

import java.util.Locale;

import net.minecraft.block.Block;
import net.minecraft.block.properties.PropertyEnum;
import net.minecraft.block.state.BlockStateContainer;
import net.minecraft.block.state.IBlockState;
import net.minecraft.entity.Entity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.IStringSerializable;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.romvoid95.common.block.ISustainsPlantType;
import net.romvoid95.core.initialization.ExoMaterial;

public class BlockExoMud extends Block implements ISustainsPlantType {

	protected static final AxisAlignedBB MUD_AABB = new AxisAlignedBB(0.0D, 0.0D, 0.0D, 1.0D, 0.875D, 1.0D);

	public BlockExoMud() {
		super(ExoMaterial.SLUDGE);
		this.setHarvestLevel("shovel", 0);
		this.setHardness(0.6F);
	}

	public enum MudType implements IStringSerializable {
		MUD(0);

		private final int meta;

		MudType(int meta) {
			this.meta = meta;
		}

		public int getMeta() {
			return meta;
		}

		public static MudType getMeta(int meta) {
			if (meta < 0 || meta >= values().length) {
				meta = 0;
			}
			return values()[meta];
		}

		@Override
		public String getName() {
			return this.name().toLowerCase(Locale.ENGLISH);
		}

		@Override
		public String toString() {
			return this.getName();
		}
	}

	public static final PropertyEnum<MudType> VARIANT = PropertyEnum.create("variant", MudType.class);

	@Override
	protected BlockStateContainer createBlockState() {
		return new BlockStateContainer(this, VARIANT);
	}

	@Override
	public IBlockState getStateFromMeta(int meta) {
		return this.getDefaultState().withProperty(VARIANT, MudType.getMeta(meta));
	}

	@Override
	public int getMetaFromState(IBlockState state) {
		return ((MudType) state.getValue(VARIANT)).ordinal();
	}

	@Override
	public AxisAlignedBB getCollisionBoundingBox(IBlockState state, IBlockAccess world, BlockPos pos) {
		return MUD_AABB;
	}

	@Override
	public void onEntityCollidedWithBlock(World world, BlockPos pos, IBlockState state, Entity entity) {
		entity.motionX *= 0.4D;
		entity.motionZ *= 0.4D;
	}

	@Override
	public boolean canSustainPlantType(IBlockAccess world, BlockPos pos, EnumPlantType plantType) {
		return false;
	}

	@Override
	public boolean canSustainPlant(IBlockState state, IBlockAccess world, BlockPos pos, EnumFacing direction,
			IPlantable plantable) {
		return this.canSustainPlantType(world, pos, plantable.getPlantType(world, pos.offset(direction)));
	}

}
