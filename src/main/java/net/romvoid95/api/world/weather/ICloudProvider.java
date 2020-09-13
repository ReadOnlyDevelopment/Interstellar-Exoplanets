package net.romvoid95.api.world.weather;

import net.minecraft.client.Minecraft;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.romvoid95.api.space.Calculations;
import net.romvoid95.client.gui.rendering.Texture;

public interface ICloudProvider {
	public float getCloudMovementSpeed (World world);

	public default float getMaxCloudSpeedDuringStorm () {
		return 12F;
	}

	public default float getMaxNormalCloudSpeed () {
		return 2F;
	}

	@SideOnly(Side.CLIENT)
	public Texture getCloudTexture ();

	public default double getCloudMovementX (World world, float cloudTicksPrev, float cloudTicks) {
		return Calculations
				.interpolateRotation(cloudTicksPrev, cloudTicks, Minecraft.getMinecraft().getRenderPartialTicks());
	}

	public default double getCloudMovementZ (World world, float cloudTicksPrev, float cloudTicks) {
		return 3.0;
	}

	public boolean areCloudsApplicableTo (WorldProvider provider);
}