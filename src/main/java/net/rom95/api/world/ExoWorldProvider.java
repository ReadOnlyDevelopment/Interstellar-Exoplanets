package net.rom95.api.world;

import java.util.List;

import asmodeuscore.core.astronomy.dimension.world.worldengine.WE_WorldProviderSpace;
import net.minecraft.block.Block;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.Vec3d;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

public abstract class ExoWorldProvider extends WE_WorldProviderSpace {

	@Override
	public int getDungeonSpacing () {
		return 0;
	}

	@Override
	public ResourceLocation getDungeonChestType () {
		return null;
	}

	@Override
	public List<Block> getSurfaceBlocks () {
		return null;
	}

	@SideOnly(Side.CLIENT)
	public float getCloudHeight () {
		return 196.0F;
	}

	@SideOnly(Side.CLIENT)
	public Vec3d getCloudColor (float partialTicks) {
		return new Vec3d(1.0D, 1.0D, 1.0D);
	}
}
