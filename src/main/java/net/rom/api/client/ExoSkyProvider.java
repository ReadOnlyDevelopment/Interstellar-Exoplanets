package net.rom.api.client;

import org.lwjgl.opengl.GL11;

import asmodeuscore.core.configs.AsmodeusConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.IRenderHandler;

public class ExoSkyProvider extends IRenderHandler {

	private static final ResourceLocation sunTexture = new ResourceLocation("textures/environment/sun.png");
	private static final ResourceLocation moonTexture = new ResourceLocation("textures/environment/moon_phases.png");

	public int starList;
	public int glSkyListA;
	public int glSkyListB;
	private float sunSize;
	protected float ticks;
	public float[] quadFloatArray = new float[4];

	private float defaultStarLum = 1.0F;

	public ExoSkyProvider() {
		int displayLists = GLAllocation.generateDisplayLists(3);
		this.starList = displayLists;
		this.glSkyListA = displayLists + 1;
		this.glSkyListB = displayLists + 2;

		// Bind stars to display list
		GL11.glPushMatrix();
		GL11.glNewList(this.starList, GL11.GL_COMPILE);
		this.renderStars();
		GL11.glEndList();
		GL11.glPopMatrix();

		final Tessellator tessellator = Tessellator.getInstance();
		GL11.glNewList(this.glSkyListA, GL11.GL_COMPILE);
		final byte byte2 = 64;
		final int i = 256 / byte2 + 2;
		float f = 16F;
		BufferBuilder worldRenderer = tessellator.getBuffer();

		for (int j = -byte2 * i; j <= byte2 * i; j += byte2) {
			for (int l = -byte2 * i; l <= byte2 * i; l += byte2) {
				worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
				worldRenderer.pos(j + 0, f, l + 0).endVertex();
				worldRenderer.pos(j + byte2, f, l + 0).endVertex();
				worldRenderer.pos(j + byte2, f, l + byte2).endVertex();
				worldRenderer.pos(j + 0, f, l + byte2).endVertex();
				tessellator.draw();
			}
		}

		GL11.glEndList();
		GL11.glNewList(this.glSkyListB, GL11.GL_COMPILE);
		f = -16F;
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

		for (int k = -byte2 * i; k <= byte2 * i; k += byte2) {
			for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2) {
				worldRenderer.pos(k + byte2, f, i1).endVertex();
				worldRenderer.pos(k, f, i1).endVertex();
				worldRenderer.pos(k, f, i1 + byte2).endVertex();
				worldRenderer.pos(k + byte2, f, i1 + byte2).endVertex();
			}
		}

		tessellator.draw();
		GL11.glEndList();
	}
	
    private void renderStarMap(int count, Star_Color color, float starBrightness)
    {
    	for(int i = 0; i < count; i++) {
   		 float x = color.getColor().floatX() / 255F;
       	 float y = color.getColor().floatY() / 255F;
       	 float z = color.getColor().floatZ() / 255F;
       	 
       	GL11.glColor4f(1, 1, 1, starBrightness);
       	 
       	 if(AsmodeusConfig.enableColorStars) 
       		 GL11.glColor4f(x, y, z, starBrightness);
       	 
       	 if(AsmodeusConfig.enableRotateStars) {
	   		// if(count % 5 == 0)
	   			 GL11.glRotatef(-37.0F, 1, 0, 0);
	   		 
	   		 //if(count % 3 == 0)
	   			 GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);
       	 }
   		 GL11.glCallList(this.starList);   
   	 }
    }

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {

	}

}
