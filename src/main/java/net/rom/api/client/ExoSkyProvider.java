package net.rom.api.client;

import java.util.Random;

import org.lwjgl.opengl.GL11;

import asmodeuscore.api.dimension.IProviderFog;
import micdoodle8.mods.galacticraft.api.vector.Vector3;
import micdoodle8.mods.galacticraft.api.world.IGalacticraftWorldProvider;
import micdoodle8.mods.galacticraft.core.Constants;
import net.minecraft.block.material.Material;
import net.minecraft.client.Minecraft;
import net.minecraft.client.multiplayer.WorldClient;
import net.minecraft.client.renderer.BufferBuilder;
import net.minecraft.client.renderer.GLAllocation;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.renderer.OpenGlHelper;
import net.minecraft.client.renderer.RenderHelper;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.vertex.DefaultVertexFormats;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.WorldProviderSurface;
import net.minecraftforge.client.IRenderHandler;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.rom.api.stellar.enums.EnumStarColor;

public abstract class ExoSkyProvider extends IRenderHandler {

	private static final ResourceLocation sunTexture = new ResourceLocation("textures/environment/sun.png");
	private static final ResourceLocation moonTexture = new ResourceLocation("textures/environment/moon_phases.png");

	private final ResourceLocation planetToRender = new ResourceLocation(Constants.ASSET_PREFIX,
			"textures/gui/celestialbodies/earth.png");

	public int starList;
	public int glSkyListA;
	public int glSkyListB;
	private float sunSize;
	protected float ticks;
	public float[] quadFloatArray = new float[4];

	private float defaultStarLum = 1.0F;

	protected Minecraft mc = Minecraft.getMinecraft();

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
		BufferBuilder buffer = tessellator.getBuffer();

		for (int j = -byte2 * i; j <= byte2 * i; j += byte2) {
			for (int l = -byte2 * i; l <= byte2 * i; l += byte2) {
				buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);
				buffer.pos(j + 0, f, l + 0).endVertex();
				buffer.pos(j + byte2, f, l + 0).endVertex();
				buffer.pos(j + byte2, f, l + byte2).endVertex();
				buffer.pos(j + 0, f, l + byte2).endVertex();
				tessellator.draw();
			}
		}

		GL11.glEndList();
		GL11.glNewList(this.glSkyListB, GL11.GL_COMPILE);
		f = -16F;
		buffer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

		for (int k = -byte2 * i; k <= byte2 * i; k += byte2) {
			for (int i1 = -byte2 * i; i1 <= byte2 * i; i1 += byte2) {
				buffer.pos(k + byte2, f, i1).endVertex();
				buffer.pos(k, f, i1).endVertex();
				buffer.pos(k, f, i1 + byte2).endVertex();
				buffer.pos(k + byte2, f, i1 + byte2).endVertex();
			}
		}

		tessellator.draw();
		GL11.glEndList();
	}

	private void renderStarMap(int count, EnumStarColor color, float starBrightness) {
		for (int i = 0; i < count; i++) {
			float x = color.getColor().floatX() / 255F;
			float y = color.getColor().floatY() / 255F;
			float z = color.getColor().floatZ() / 255F;

			GL11.glColor4f(1, 1, 1, starBrightness);
			GL11.glColor4f(x, y, z, starBrightness);

			//GL11.glRotatef(-37.0F, 1, 0, 0);

			//GL11.glRotatef(-15.0F, 0.0F, 1.0F, 0.0F);

			GL11.glCallList(this.starList);
		}
	}

	@Override
	public void render(float partialTicks, WorldClient world, Minecraft mc) {
		this.ticks = partialTicks;
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		Vec3d vec3 = world.getSkyColor(mc.getRenderViewEntity(), partialTicks);
		float f1 = (float) vec3.x;
		float f2 = (float) vec3.y;
		float f3 = (float) vec3.z;
		float f6;

		GL11.glColor3f(f1, f2, f3);
		Tessellator tessellator1 = Tessellator.getInstance();
		BufferBuilder worldRenderer1 = tessellator1.getBuffer();
		GL11.glDepthMask(false);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glColor3f(f1, f2, f3);
		GL11.glCallList(this.glSkyListA);
		GL11.glDisable(GL11.GL_FOG);
		GL11.glDisable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_BLEND);
		GlStateManager.tryBlendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA,
				GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA, GlStateManager.SourceFactor.ONE,
				GlStateManager.DestFactor.ZERO);

		RenderHelper.disableStandardItemLighting();
		float f7;
		float f8;
		float f9 = 0;
		float f10 = 0;

		float rain = 1.0F - world.getRainStrength(partialTicks);
		float starBrightness = world.isRaining() ? world.getStarBrightness(partialTicks) * rain
				: world.getStarBrightness(partialTicks);
		float sunBrightness = world.isRaining() ? world.getSunBrightness(partialTicks) * rain
				: world.getSunBrightness(partialTicks);

		if (starBrightness > 0.0F && !inWater(this.mc.player)) {
			if (this.mc.world.provider instanceof IProviderFog) {
				starBrightness = ((IProviderFog) this.mc.world.provider).getFogDensity(0, 0, 0) - 0.2F;
			}
			GL11.glPushMatrix();

			GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
			GL11.glRotatef(1.0F, 1.0F, 0.0F, 0.0F);
			renderStarMap(2, EnumStarColor.BRIGHT_BLUE, starBrightness);
			renderStarMap(8, EnumStarColor.WHITE, starBrightness);
			renderStarMap(1, EnumStarColor.DARK_WHTIE, starBrightness);

			GL11.glPopMatrix();
		}

		GL11.glPushMatrix();
		// TODO Sun Aura
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glShadeModel(GL11.GL_SMOOTH);

		quadFloatArray[0] = 255 / 255.0F;
		quadFloatArray[1] = 194 / 255.0F;
		quadFloatArray[2] = 180 / 255.0F;
		quadFloatArray[3] = 0.3F;
		
		if (this.colorSunAura() != null) {
			quadFloatArray[0] = this.colorSunAura().intX() / 255.0F;
			quadFloatArray[1] = this.colorSunAura().intY() / 255.0F;
			quadFloatArray[2] = this.colorSunAura().intZ() / 255.0F;
		}
		f6 = quadFloatArray[0];
		f7 = quadFloatArray[1];
		f8 = quadFloatArray[2];

		starBrightness = 1.0F - starBrightness;
		sunBrightness = starBrightness;

		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(this.mc.world.getCelestialAngle(this.ticks) * 360.0F, 1.0F, 0.0F, 0.0F);
		f10 = sunSize() + 5.5F;
		if (!inWater(this.mc.player))
			this.renderSunAura(tessellator1, f10 + addSizeAura(), sunBrightness);
		GL11.glPopMatrix();

		GL11.glShadeModel(GL11.GL_FLAT);

		GL11.glEnable(GL11.GL_TEXTURE_2D);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glPushMatrix();

		f7 = 0.0F;
		f8 = 0.0F;
		f9 = 0.0F;
		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F - Minecraft.getMinecraft().world.getRainStrength(partialTicks));
		GL11.glTranslatef(f7, f8, f9);
		GL11.glRotatef(-90.0F, 0.0F, 1.0F, 0.0F);
		GL11.glRotatef(world.getCelestialAngle(partialTicks) * 360.0F, 1.0F, 0.0F, 0.0F);
		// Render sun
		if (this.modeLight() != 2 && !inWater(this.mc.player)) {
			f10 = sunSize();
			if (this.sunImage() != null)
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.sunImage());
			else
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.sunTexture);
			if (enableSmoothRender())
				GL11.glDisable(GL11.GL_BLEND);
			worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			worldRenderer1.pos(-f10, 99.9D, -f10).tex(0, 0).endVertex();
			worldRenderer1.pos(f10, 99.9D, -f10).tex(1, 0).endVertex();
			worldRenderer1.pos(f10, 99.9D, f10).tex(1, 1).endVertex();
			worldRenderer1.pos(-f10, 99.9D, f10).tex(0, 1).endVertex();
			tessellator1.draw();
			if (enableSmoothRender())
				GL11.glEnable(GL11.GL_BLEND);
		}
		if (this.enableBaseImages() && !inWater(this.mc.player)) {
			if (Minecraft.getMinecraft().world.provider instanceof WorldProviderSurface) {
				f10 = 20.0F;
				FMLClientHandler.instance().getClient().renderEngine.bindTexture(this.moonTexture);
				int k = Minecraft.getMinecraft().world.getMoonPhase();
				int l = k % 4;
				int i1 = k / 4 % 2;
				float f14 = (float) (l + 0) / 4.0F;
				float f15 = (float) (i1 + 0) / 2.0F;
				float f16 = (float) (l + 1) / 4.0F;
				float f17 = (float) (i1 + 1) / 2.0F;
				worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
				worldRenderer1.pos(-f10, -100.0D, f10).tex(f16, f17).endVertex();
				worldRenderer1.pos(f10, -100.0D, f10).tex(f14, f17).endVertex();
				worldRenderer1.pos(f10, -100.0D, -f10).tex(f14, f15).endVertex();
				worldRenderer1.pos(-f10, -100.0D, -f10).tex(f16, f15).endVertex();
				tessellator1.draw();
			}
			float light = 0.0F;

			if (this.modeLight() == 0)
				light = FMLClientHandler.instance().getClient().world.getStarBrightness(1.0F)
						- Minecraft.getMinecraft().world.getRainStrength(partialTicks);
			if (this.modeLight() == 1)
				light = 1.0F;

			GL11.glDisable(GL11.GL_BLEND);

			this.render(tessellator1, worldRenderer1, f10, partialTicks);
		}

		GL11.glPopMatrix();
		GL11.glPushMatrix();
		if ((float) mc.player.posY > 250) {
			GL11.glEnable(GL11.GL_TEXTURE_2D);
			f10 = 120;
			float color = mc.world.getStarBrightness(ticks);
			GL11.glColor4f(1.0F - color, 1.0F - color, 1.0F - color, 1.0F);
			GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
			GL11.glRotatef(-360F, 1.0F, 0.0F, 0.0F);

			GL11.glTranslatef(0.0F, -(float) mc.player.posY / 8, 0.0F);

			if (!(mc.world.provider instanceof WorldProviderSurface))
				mc.renderEngine
						.bindTexture(((IGalacticraftWorldProvider) mc.world.provider).getCelestialBody().getBodyIcon());
			else
				mc.renderEngine.bindTexture(this.planetToRender);

			worldRenderer1.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
			worldRenderer1.pos(-f10, -100.0D, f10).tex(0D, 1.0D).endVertex();
			worldRenderer1.pos(f10, -100.0D, f10).tex(1.0D, 1.0D).endVertex();
			worldRenderer1.pos(f10, -100.0D, -f10).tex(1.0D, 0D).endVertex();
			worldRenderer1.pos(-f10, -100.0D, -f10).tex(0D, 0D).endVertex();
			tessellator1.draw();

			this.renderAtmo(tessellator1, -360.0F, 0.0F, 115, this.getAtmosphereColor());

		}
		GL11.glDisable(GL11.GL_TEXTURE_2D);

		GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
		GL11.glDisable(GL11.GL_BLEND);
		GL11.glEnable(GL11.GL_ALPHA_TEST);
		GL11.glEnable(GL11.GL_FOG);
		GL11.glPopMatrix();
		GL11.glDisable(GL11.GL_TEXTURE_2D);
		GL11.glColor3f(0.0F, 0.0F, 0.0F);
		double d0 = mc.player.getPosition().getY() - world.getHorizon();

		if (world.provider.isSkyColored()) {
			GL11.glColor3f(f1 * 0.2F + 0.04F, f2 * 0.2F + 0.04F, f3 * 0.6F + 0.1F);
		} else {
			GL11.glColor3f(f1, f2, f3);
		}

		GL11.glEnable(GL11.GL_TEXTURE_2D);

		GlStateManager.enableRescaleNormal();
		GL11.glEnable(GL11.GL_COLOR_MATERIAL);
		OpenGlHelper.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA, GL11.GL_ONE, GL11.GL_ZERO);
		GL11.glDepthMask(true);
		GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
		GL11.glDisable(GL11.GL_BLEND);
	}

	protected void renderImage(ResourceLocation image, float x, float y, float z, float f10, boolean withsun) {
		this.renderImage(image, x, y, z, f10, withsun,
				FMLClientHandler.instance().getClient().world.getStarBrightness(1.0F));
	}

	protected void renderImage(ResourceLocation image, float x, float y, float z, float f10, boolean withsun,
			float alpha) {

		GL11.glEnable(GL11.GL_BLEND);

		if (!withsun) {
			GL11.glPopMatrix();
			GL11.glPushMatrix();
		}

		Tessellator tessellator1 = Tessellator.getInstance();
		BufferBuilder worldRenderer = tessellator1.getBuffer();

		GL11.glRotatef(x, 0.0F, 1.0F, 0.0F); // X
		GL11.glRotatef(y, 1.0F, 0.0F, 0.0F); // Y
		GL11.glRotatef(z, 0.0F, 0.0F, 1.0F); // Z
		GL11.glColor4f(1.0F, 1.0F, 1.0F, alpha);
		FMLClientHandler.instance().getClient().renderEngine.bindTexture(image);

		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX);
		worldRenderer.pos(-f10, -100.0D, f10).tex(0, 1).endVertex();
		worldRenderer.pos(f10, -100.0D, f10).tex(1, 1).endVertex();
		worldRenderer.pos(f10, -100.0D, -f10).tex(1, 0).endVertex();
		worldRenderer.pos(-f10, -100.0D, -f10).tex(0, 0).endVertex();
		tessellator1.draw();

	}

	protected void renderAtmo(Tessellator tessellator1, float x, float y, float f10, Vector3 vec) {
		if (vec != null) {
			GL11.glEnable(GL11.GL_BLEND);
			GL11.glDisable(GL11.GL_TEXTURE_2D);
			GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

			GL11.glRotatef(y, 0.0F, 0.0F, 1.0F);
			GL11.glRotatef(x, 1.0F, 0.0F, 0.0F);

			double planetOrbitalDistance = 5.0D;

			double dist = (-64D - 4 * (planetOrbitalDistance) / 2D)
					- ((float) mc.player.posY / ((float) mc.player.posY));
			double scalingMult = 1D - 0.9 * (planetOrbitalDistance);

			float Xoffset = (float) ((System.currentTimeMillis() / 1000000d % 1));

			float f14 = 1f + Xoffset;
			float f15 = 0f + Xoffset;
			float f16 = f15;
			float f17 = f14;
			float[] color = new float[4];

			color[0] = vec.floatX();
			color[1] = vec.floatY();
			color[2] = vec.floatZ();
			color[3] = 0.09F;

			BufferBuilder worldRenderer = tessellator1.getBuffer();
			worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION_TEX_COLOR);
			worldRenderer.color(color[0], color[1], color[2], 0.09F);

//			for (int i = 0; i < 5; i++) {
//				renderTestWithUV(worldRenderer, color, dist + i * scalingMult, -f10, -f10, 0, 0, f14, f15, f16, f17);
//				renderTestWithUV(worldRenderer, color, dist + i * scalingMult, 0, 0, f10, f10, f14, f15, f16, f17);
//				renderTestWithUV(worldRenderer, color, dist + i * scalingMult, -f10, 0, 0, f10, f14, f15, f16, f17);
//				renderTestWithUV(worldRenderer, color, dist + i * scalingMult, 0, -f10, f10, 0, f14, f15, f16, f17);
//			}
			tessellator1.draw();
		}

	}

	public static void renderTestWithUV(BufferBuilder buff, float[] color, double yMax, double xMin, double zMin,
			double xMax, double zMax, double uMin, double uMax, double vMin, double vMax) {

		buff.pos(xMin, yMax, zMin).tex(uMin, vMin).color(color[0], color[1], color[2], color[3]).endVertex();
		buff.pos(xMin, yMax, zMax).tex(uMin, vMax).color(color[0], color[1], color[2], color[3]).endVertex();
		buff.pos(xMax, yMax, zMax).tex(uMax, vMax).color(color[0], color[1], color[2], color[3]).endVertex();
		buff.pos(xMax, yMax, zMin).tex(uMax, vMin).color(color[0], color[1], color[2], color[3]).endVertex();

	}

	protected void renderSunAura(Tessellator tessellator1, float f10, float f18) {
		BufferBuilder buffer = tessellator1.getBuffer();
		Vec3d vec3 = this.mc.world.getSkyColor(mc.getRenderViewEntity(), this.ticks);
		float f1 = (float) vec3.x;
		float f2 = (float) vec3.y;
		float f3 = (float) vec3.z;
		float f6;
		float f7;
		float f8;
		float f9 = 0;
		float f11;

		if (mc.gameSettings.anaglyph) {
			float f4 = (f1 * 30.0F + f2 * 59.0F + f3 * 11.0F) / 100.0F;
			float f5 = (f1 * 30.0F + f2 * 70.0F) / 100.0F;
			f6 = (f1 * 30.0F + f3 * 70.0F) / 100.0F;
			f1 = f4;
			f2 = f5;
			f3 = f6;
		}

		quadFloatArray[0] = 255 / 255.0F;
		quadFloatArray[1] = 194 / 255.0F;
		quadFloatArray[2] = 180 / 255.0F;
		quadFloatArray[3] = 0.3F;
		if (this.colorSunAura() != null) {
			quadFloatArray[0] = this.colorSunAura().intX() / 255.0F;
			quadFloatArray[1] = this.colorSunAura().intY() / 255.0F;
			quadFloatArray[2] = this.colorSunAura().intZ() / 255.0F;
		}

		f6 = quadFloatArray[0];
		f7 = quadFloatArray[1];
		f8 = quadFloatArray[2];

		if (mc.gameSettings.anaglyph) {
			f9 = (f6 * 30.0F + f7 * 59.0F + f8 * 11.0F) / 100.0F;
			f10 = (f6 * 30.0F + f7 * 70.0F) / 100.0F;
			f11 = (f6 * 30.0F + f8 * 70.0F) / 100.0F;
			f6 = f9;
			f7 = f10;
			f8 = f11;
		}

		float r = f6 * f18;
		float g = f7 * f18;
		float b = f8 * f18;
		float a = quadFloatArray[3] * 2 / f18 - Minecraft.getMinecraft().world.getRainStrength(this.ticks);

		if (this.modeLight() != 2) {
			buffer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);

			buffer.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();

			byte b0 = 16;
			r = quadFloatArray[0] * f18;
			g = quadFloatArray[1] * f18;
			b = quadFloatArray[2] * f18;
			a = 0.0F;

			// Render sun aura
			buffer.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
			buffer.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
			buffer.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
			buffer.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
			buffer.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
			buffer.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
			buffer.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
			buffer.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
			buffer.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

			tessellator1.draw();
		}

		if (enableSmoothRender()) {
			GL11.glLineWidth(2);
			GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_LINE);
		}

		if (enableLargeSunAura()) {
			buffer.begin(GL11.GL_TRIANGLE_FAN, DefaultVertexFormats.POSITION_COLOR);

			r = f6 * f18;
			g = f7 * f18;
			b = f8 * f18;
			a = enableSmoothRender()
					? quadFloatArray[3] * 2 / f18 - Minecraft.getMinecraft().world.getRainStrength(this.ticks)
					: quadFloatArray[3] * f18;

			buffer.pos(0.0D, 100.0D, 0.0D).color(r, g, b, a).endVertex();

			r = quadFloatArray[0] * f18;
			g = quadFloatArray[1] * f18;
			b = quadFloatArray[2] * f18;
			a = 0.0F;

			// Render larger sun aura
			f10 = f10 + 10.0F;
			int i = enableSmoothRender() ? 8 : 0;
			f10 += i;
			buffer.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();
			buffer.pos(0, 100.0D, (double) -f10 * 1.5F).color(r, g, b, a).endVertex();
			f10 -= i + i;
			buffer.pos(f10, 100.0D, -f10).color(r, g, b, a).endVertex();
			buffer.pos((double) f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
			f10 += i;
			buffer.pos(f10, 100.0D, f10).color(r, g, b, a).endVertex();
			buffer.pos(0, 100.0D, (double) f10 * 1.5F).color(r, g, b, a).endVertex();
			f10 -= i;
			buffer.pos(-f10, 100.0D, f10).color(r, g, b, a).endVertex();
			buffer.pos((double) -f10 * 1.5F, 100.0D, 0).color(r, g, b, a).endVertex();
			buffer.pos(-f10, 100.0D, -f10).color(r, g, b, a).endVertex();

			tessellator1.draw();
		}

		GL11.glPolygonMode(GL11.GL_FRONT_AND_BACK, GL11.GL_FILL);

	}

	private void renderStars() {
		final Random rand = new Random(10842L);
		final Tessellator tesselator = Tessellator.getInstance();
		BufferBuilder worldRenderer = tesselator.getBuffer();
		worldRenderer.begin(GL11.GL_QUADS, DefaultVertexFormats.POSITION);

		int star_count = 500;

		for (int starIndex = 0; starIndex < star_count; ++starIndex) {

			double var4 = rand.nextFloat() * 2.0F - 1.0F;
			double var6 = rand.nextFloat() * 2.0F - 1.0F;
			double var8 = rand.nextFloat() * 2.0F - 1.0F;
			final double var10 = 0.15F + rand.nextFloat() * 0.1F;
			double var12 = var4 * var4 + var6 * var6 + var8 * var8;

			if (var12 < 1.0D && var12 > 0.01D) {
				var12 = 1.0D / Math.sqrt(var12);
				var4 *= var12;
				var6 *= var12;
				var8 *= var12;
				final double var14 = var4 * 130D;
				final double var16 = var6 * 130D;
				final double var18 = var8 * 130D;
				final double var20 = Math.atan2(var4, var8);
				final double var22 = Math.sin(var20);
				final double var24 = Math.cos(var20);
				final double var26 = Math.atan2(Math.sqrt(var4 * var4 + var8 * var8), var6);
				final double var28 = Math.sin(var26);
				final double var30 = Math.cos(var26);
				final double var32 = rand.nextDouble() * Math.PI * 2.0D;
				final double var34 = Math.sin(var32);
				final double var36 = Math.cos(var32);

				for (int var38 = 0; var38 < 4; ++var38) {
					final double var39 = 0.0D;
					final double var41 = ((var38 & 2) - 1) * var10;
					final double var43 = ((var38 + 1 & 2) - 1) * var10;
					final double var47 = var41 * var36 - var43 * var34;
					final double var49 = var43 * var36 + var41 * var34;
					final double var53 = var47 * var28 + var39 * var30;
					final double var55 = var39 * var28 - var47 * var30;
					final double var57 = var55 * var22 - var49 * var24;
					final double var61 = var49 * var22 + var55 * var24;

					worldRenderer.pos(var14 + var57, var16 + var53, var18 + var61)
							/* .color(r, g, b, starBrightness) */.endVertex();
				}
			}

		}

		tesselator.draw();
	}

	public float getSkyBrightness(float par1) {
		final float var2 = FMLClientHandler.instance().getClient().world.getCelestialAngle(par1);
		float var3 = 1.0F - (MathHelper.sin(var2 * (float) Math.PI * 2.0F) * 2.0F + 0.25F);

		if (var3 < 0.0F) {
			var3 = 0.0F;
		}

		if (var3 > 1.0F) {
			var3 = 1.0F;
		}

		return var3 * var3 * 1F;
	}

	protected abstract void render(Tessellator tessellator, BufferBuilder buffer, float f10, float ticks);

	protected abstract int modeLight();

	protected abstract boolean enableBaseImages();

	protected abstract float sunSize();

	protected abstract ResourceLocation sunImage();

	protected abstract boolean enableStar();

	protected abstract Vector3 colorSunAura();

	protected abstract Vector3 getAtmosphereColor();

	public boolean enableLargeSunAura() {
		return true;
	}

	public boolean enableSmoothRender() {
		return false;
	}

	public boolean enableRenderPlanet() {
		return true;
	}

	public int addSizeAura() {
		return 0;
	}

	protected float getCelestialAngle(long daylength) {
		return calculateCelestialAngle(this.mc.world.getWorldTime(), this.ticks, (int) daylength) * 360.0F;
	}

	private boolean inWater(EntityPlayer player) {
		BlockPos pos = player.getPosition();

		return mc.world.getBlockState(pos.up()).getMaterial() == Material.WATER;
	}

	private void setStarBrightness(float bright) {
		defaultStarLum = bright;
	}

	public static float calculateCelestialAngle(long worldtime, float ticks, float daylenght) {
		int j = (int) (worldtime % daylenght);
		float f1 = ((float) j + ticks) / daylenght - 0.25F;

		f1 = f1 < 0.0F ? ++f1 : --f1;


		float f2 = f1;
		f1 = 1.0F - (float) ((Math.cos((double) f1 * Math.PI) + 1.0D) / 2.0D);
		f1 = f2 + (f1 - f2) / 3.0F;
		return f1;
	}

}
