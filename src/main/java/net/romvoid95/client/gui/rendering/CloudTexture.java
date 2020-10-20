package net.romvoid95.client.gui.rendering;

import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.client.RGB;
import net.romvoid95.common.CommonUtil;
import net.romvoid95.core.ExoInfo;

public class CloudTexture extends ResourceLocation {

	private RGB color;
	private float height;
	private int speed;
	private static String domain = ExoInfo.MODID;

	/**
	 * Instantiates a new texture.
	 *
	 * @param domain the domain
	 * @param location the location
	 */
	public CloudTexture(String location, float height, int speed, RGB color) {
		super(domain, location);
		this.height = height;
		this.speed = speed;
		this.color = color;
	}

	/**
	 * Bind.
	 */
	@SideOnly(Side.CLIENT)
	public void bind () {
		CommonUtil.bindTexture(this);
	}
	
	public float getHeight() {
		return height;
	}

	public int getSpeed() {
		return speed;
	}
	
	public RGB getColor() {
		return color;
	}
}