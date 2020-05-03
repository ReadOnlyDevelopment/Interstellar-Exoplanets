package net.rom.exoplanets.astronomy.yzcetisystem.b;

import org.lwjgl.opengl.GL11;

import micdoodle8.mods.galacticraft.api.prefab.world.gen.WorldProviderSpace;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.api.client.ExoSkyProvider;
import net.rom.api.stellar.enums.EnumStarColor;
import net.rom.exoplanets.ExoInfo;
import net.rom.exoplanets.conf.SConfigCore;

public class SkyProviderYzCetiB extends ExoSkyProvider {

	private static final ResourceLocation YZCETI_A_REALISTIC = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/realism/yz_ceti_starB.png");
	private static final ResourceLocation YZCETI_A = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/yz_ceti_star.png");

	private static final ResourceLocation YZCETI_C = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/yz_ceti_c.png");
	private static final ResourceLocation YZCETI_D = new ResourceLocation(ExoInfo.MODID,
			"textures/celestialbodies/yz_ceti/yz_ceti_d.png");

	@Override
	protected void render(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks) {
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		
		long daylength = ((WorldProviderSpace) this.mc.world.provider).getDayLength();
		

		f10 = 1.5F;
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);		
		GL11.glRotatef(this.getCelestialAngle(daylength / 2), 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-100F, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.YZCETI_C);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
		
		GL11.glPopMatrix();
		GL11.glPushMatrix();
			

		f10 = 1.0F;
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);		
		GL11.glRotatef(this.getCelestialAngle(daylength), 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(-95F, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.YZCETI_D);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
		
		GL11.glPopMatrix();
		GL11.glPushMatrix();
		
        float f = 0.8F;
        this.renderAtmo(tessellator, 0.0F, 0.0F, f10 - 8, new Vector3(120 / 255.0F * f, 110 / 255.0F * f, 120 / 255.0F * f));
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
		if (SConfigCore.enableRealism) {
			return YZCETI_A_REALISTIC;
		} else {
			return YZCETI_A;
		}
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
		return null;
	}

	@Override
	public boolean enableSmoothRender() {
		return true;
	}

	@Override
	public int addSizeAura() {
		return 20;
	}

}
