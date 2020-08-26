package net.rom95.client.gui.rendering;

import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;
import net.rom95.common.CommonUtil;

public class Texture extends ResourceLocation {

	/**
	 * Instantiates a new texture.
	 *
	 * @param resource the resource
	 */
	public Texture(ResourceLocation resource) {
		this(resource.getResourceDomain(), resource.getResourcePath());
	}

	/**
	 * Instantiates a new texture.
	 *
	 * @param location the location
	 */
	public Texture(String location) {
		super(location);
	}

	/**
	 * Instantiates a new texture.
	 *
	 * @param domain the domain
	 * @param location the location
	 */
	public Texture(String domain, String location) {
		super(domain, location);
	}

	/**
	 * Bind.
	 */
	@SideOnly(Side.CLIENT)
	public void bind () {
		CommonUtil.bindTexture(this);
	}
}