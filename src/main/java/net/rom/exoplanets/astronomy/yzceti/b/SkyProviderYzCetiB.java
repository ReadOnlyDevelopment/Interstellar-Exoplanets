package net.rom.exoplanets.astronomy.yzceti.b;

import org.lwjgl.opengl.GL11;

import asmodeuscore.api.dimension.IAdvancedSpace.StarColor;
import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.util.ResourceLocation;
import net.rom.exoplanets.Assets;

public class SkyProviderYzCetiB extends SkyProviderBase {

	@Override
	protected StarColor colorSunAura() {
		return StarColor.RED;
	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected boolean enableStar() {
		return true;
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return ((WorldProviderYzCetiB)this.mc.world.provider).getSkyColor();
	}

	@Override
	protected ModeLight modeLight() {
		return ModeLight.DEFAULT;
	}

	@Override
	protected void rendererSky(Tessellator arg0, BufferBuilder arg1, float arg2, float arg3) {
		GL11.glPushMatrix();
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glPopMatrix();
	}

	@Override
	protected ResourceLocation sunImage() {
		return Assets.getCelestialTexture("yzcetistar");
	}

	@Override
	protected float sunSize() {
		return 10;
	}

}
