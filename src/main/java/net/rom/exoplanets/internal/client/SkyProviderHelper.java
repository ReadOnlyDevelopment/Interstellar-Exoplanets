package net.rom.exoplanets.internal.client;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.FMLClientHandler;

public class SkyProviderHelper {
	
	public static void renderPlanet(Tessellator tessellator, BufferBuilder buffer, float f10, long dl, ResourceLocation planet, float rotate) {
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);		
		GL11.glRotatef(dl , 1.0F, 1.0F, 0.0F);
		GL11.glRotatef(rotate, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(planet);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
	}

	public static void renderPlanet(Tessellator tessellator, BufferBuilder buffer, float f10, float dl, ResourceLocation planet, float rotate) {
		GL11.glScalef(0.6F, 0.6F, 0.6F);			
		GL11.glRotatef(0.0F, 1.0F, 0.0F, 1.0F);		
		GL11.glRotatef(dl , 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(rotate, 1.0F, 0.0F, 0.0F);
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1F);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(planet);
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		buffer.pos(-f10, -100.0D, f10).tex(0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, f10).tex(1.0, 1.0).endVertex();
		buffer.pos(f10, -100.0D, -f10).tex(1.0, 0).endVertex();
		buffer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator.draw();
	}
}
