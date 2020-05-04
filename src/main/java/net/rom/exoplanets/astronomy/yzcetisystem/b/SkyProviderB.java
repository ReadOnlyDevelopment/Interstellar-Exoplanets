package net.rom.exoplanets.astronomy.yzcetisystem.b;

import org.lwjgl.opengl.GL11;

import asmodeuscore.core.astronomy.sky.SkyProviderBase;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.api.stellar.enums.EnumStarColor;
import net.rom.exoplanets.ExoInfo;

public class SkyProviderB extends SkyProviderBase {

	private static final ResourceLocation cetiC = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/yz_ceti_c.png");
	private static final ResourceLocation cetiD = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/yz_ceti_d.png");

	@Override
	protected void rendererSky(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPopMatrix();
		GL11.glPushMatrix();

		GL11.glEnable(GL11.GL_BLEND);

		f10 = 0.5F;
		GL11.glScalef(0.6F, 0.6F, 0.6F);
		GL11.glRotatef(-180.0F, 50.0F, 1.0F, 0.0F);
		GL11.glRotatef(90F, 190.0F, 50.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.cetiC);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();

		f10 = 1.5F;
		GL11.glScalef(0.8F, 0.8F, 0.8F);
		GL11.glRotatef(-80.0F, 1.0F, 0.0F, 0.0F);
		GL11.glRotatef(140.0F, 0.0F, 0.0F, 1.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.cetiD);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();

		GL11.glDisable(GL11.GL_BLEND);
	}

	@Override
	protected int modeLight() {
		return 0;
	}

	@Override
	protected boolean enableBaseImages() {
		return true;
	}

	@Override
	protected float sunSize() {
		return 20.0F;
	}

	@Override
	protected ResourceLocation sunImage() {
		return new ResourceLocation(ExoInfo.MODID, "textures/celestialbodies/yz_ceti/yz_ceti_star.png");
	}

	@Override
	protected boolean enableStar() {
		return true;
	}

	@Override
	protected Vector3 colorSunAura() {
		return EnumStarColor.RED.getColor();
	}

	@Override
	protected Vector3 getAtmosphereColor() {
		return EnumStarColor.RED.getColor();
	}

	@Override
	public int addSizeAura() {
		return 10;
	}

}
