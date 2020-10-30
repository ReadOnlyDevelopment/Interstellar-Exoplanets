/**
 * Copyright (C) 2020 Interstellar:  Exoplanets
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package net.romvoid95.client.gui.rendering;

import static org.lwjgl.opengl.GL11.*;

import java.nio.*;
import java.util.ArrayList;

import org.lwjgl.opengl.GL11;
import org.lwjgl.opengl.GL12;

import net.minecraft.client.renderer.*;
import net.minecraft.client.renderer.texture.*;
import net.minecraft.client.shader.Framebuffer;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.EnumFacing;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import net.romvoid95.common.CommonUtil;
import net.romvoid95.common.lib.tile.IRotateable.IRotatableXAxis;
import net.romvoid95.common.lib.tile.IRotateable.IRotatableYAxis;

public class OpenGL {

	public static ArrayList<Framebuffer> frameBuffers = new ArrayList<>();

	public static boolean lightmapTexUnitTextureEnable;

	public static int lightmapTexUnit = OpenGlHelper.lightmapTexUnit;

	public static int defaultTexUnit = OpenGlHelper.defaultTexUnit;

	public static void pushMatrix () {
		GL11.glPushMatrix();
	}

	public static void popMatrix () {
		GL11.glPopMatrix();
	}

	/**
	 * Translate.
	 *
	 * @param offsetX the offset X
	 * @param offsetY the offset Y
	 * @param offsetZ the offset Z
	 */
	public static void translate (double offsetX, double offsetY, double offsetZ) {
		GL11.glTranslated(offsetX, offsetY, offsetZ);
	}

	/**
	 * Translate.
	 *
	 * @param offsetX the offset X
	 * @param offsetY the offset Y
	 * @param offsetZ the offset Z
	 */
	public static void translate (float offsetX, float offsetY, float offsetZ) {
		GL11.glTranslatef(offsetX, offsetY, offsetZ);
	}

	/**
	 * Scale.
	 *
	 * @param scaleX the scale X
	 * @param scaleY the scale Y
	 * @param scaleZ the scale Z
	 */
	public static void scale (double scaleX, double scaleY, double scaleZ) {
		GL11.glScaled(scaleX, scaleY, scaleZ);
	}

	/**
	 * Scale.
	 *
	 * @param scaleX the scale X
	 * @param scaleY the scale Y
	 * @param scaleZ the scale Z
	 */
	public static void scale (float scaleX, float scaleY, float scaleZ) {
		GL11.glScalef(scaleX, scaleY, scaleZ);
	}

	/**
	 * Begin.
	 *
	 * @param mode the mode
	 */
	public static void begin (int mode) {
		GL11.glBegin(mode);
	}

	/**
	 * End.
	 */
	public static void end () {
		GL11.glEnd();
	}

	/**
	 * New list.
	 *
	 * @param list the list
	 * @param mode the mode
	 */
	public static void newList (int list, int mode) {
		GL11.glNewList(list, mode);
	}

	/**
	 * Call list.
	 *
	 * @param list the list
	 */
	public static void callList (int list) {
		GL11.glCallList(list);
	}

	/**
	 * End list.
	 */
	public static void endList () {
		GL11.glEndList();
	}

	/**
	 * Enable texture 2 d.
	 */
	public static void enableTexture2d () {
		GlStateManager.enableTexture2D();
	}

	/**
	 * Disable texture 2 d.
	 */
	public static void disableTexture2d () {
		GlStateManager.disableTexture2D();
	}

	/**
	 * Normal.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public static void normal (float x, float y, float z) {
		GL11.glNormal3f(x, y, z);
	}

	/**
	 * Tex coord.
	 *
	 * @param u the u
	 * @param v the v
	 */
	public static void texCoord (float u, float v) {
		GL11.glTexCoord2f(u, v);
	}

	/**
	 * Vertex.
	 *
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public static void vertex (float x, float y, float z) {
		GL11.glVertex3f(x, y, z);
	}

	/**
	 * Rotate.
	 *
	 * @param angle the angle
	 * @param x the x
	 * @param y the y
	 * @param z the z
	 */
	public static void rotate (float angle, float x, float y, float z) {
		GL11.glRotatef(angle, x, y, z);
	}

	/**
	 * Enable blend.
	 */
	public static void enableBlend () {
		GlStateManager.disableBlend();
		GlStateManager.enableBlend();
	}

	/**
	 * Disable blend.
	 */
	public static void disableBlend () {
		GlStateManager.disableBlend();
	}

	/**
	 * Color.
	 *
	 * @param r the r
	 * @param g the g
	 * @param b the b
	 */
	public static void color (float r, float g, float b) {
		GL11.glColor3f(r, g, b);
	}

	/**
	 * Color.
	 *
	 * @param r the r
	 * @param g the g
	 * @param b the b
	 * @param a the a
	 */
	public static void color (float r, float g, float b, float a) {
		GL11.glColor4f(r, g, b, a);
	}

	/**
	 * Same functionality as GlStateManager.color
	 * 
	 * @param color - Hexadecimal color value
	 */
	public static void color4i (int color) {
		float a = (color >> 24 & 255) / 255.0F;
		float r = (color >> 16 & 255) / 255.0F;
		float g = (color >> 8 & 255) / 255.0F;
		float b = (color & 255) / 255.0F;
		color(r, g, b, a);
	}

	/**
	 * Same functionality as glColor3f.
	 *
	 * @param color - Hexadecimal color value
	 */
	public static void color3i (int color) {
		float r = (color >> 16 & 255) / 255.0F;
		float g = (color >> 8 & 255) / 255.0F;
		float b = (color & 255) / 255.0F;
		color(r, g, b);
	}

	/**
	 * Gets the string.
	 *
	 * @param name the name
	 * @return the string
	 */
	public static String getString (int name) {
		return GL11.glGetString(name);
	}

	/**
	 * Enable depth test.
	 */
	public static void enableDepthTest () {
		GlStateManager.enableDepth();
	}

	/**
	 * Disable depth test.
	 */
	public static void disableDepthTest () {
		GlStateManager.disableDepth();
	}

	/**
	 * Enable.
	 *
	 * @param cap the cap
	 */
	public static void enable (int cap) {
		GL11.glEnable(cap);
	}

	/**
	 * Disable.
	 *
	 * @param cap the cap
	 */
	public static void disable (int cap) {
		GL11.glDisable(cap);
	}

	/**
	 * Blend func.
	 *
	 * @param sfactor the sfactor
	 * @param dfactor the dfactor
	 */
	public static void blendFunc (int sfactor, int dfactor) {
		GL11.glBlendFunc(sfactor, dfactor);
	}

	/**
	 * Depth mask.
	 *
	 * @param flag the flag
	 */
	public static void depthMask (boolean flag) {
		GL11.glDepthMask(flag);
	}

	/**
	 * Sets the lightmap texture coords.
	 *
	 * @param lightmapTexUnit the lightmap tex unit
	 * @param x the x
	 * @param y the y
	 */
	public static void setLightmapTextureCoords (int lightmapTexUnit, float x, float y) {
		OpenGlHelper.setLightmapTextureCoords(lightmapTexUnit, x, y);
	}

	/**
	 * 
	 *
	 * @param id 
	 */
	public static void setActiveTexture (int id) {
		OpenGlHelper.setActiveTexture(id);
	}

	/**
	 * Enable lighting.
	 */
	public static void enableLighting () {
		GlStateManager.enableLighting();
	}

	/**
	 * Disable lighting.
	 */
	public static void disableLighting () {
		GlStateManager.disableLighting();
	}

	/**
	 * Gets the boolean.
	 *
	 * @param pname the pname
	 * @return the boolean
	 */
	public static boolean getBoolean (int pname) {
		return GL11.glGetBoolean(pname);
	}

	/**
	 * Tex parameter.
	 *
	 * @param target the target
	 * @param pname the pname
	 * @param param the param
	 */
	public static void texParameter (int target, int pname, int param) {
		GL11.glTexParameteri(target, pname, param);
	}

	/**
	 * Tex parameter.
	 *
	 * @param target the target
	 * @param pname the pname
	 * @param param the param
	 */
	public static void texParameter (int target, int pname, float param) {
		GL11.glTexParameterf(target, pname, param);
	}

	/**
	 * Tex parameter.
	 *
	 * @param target the target
	 * @param pname the pname
	 * @param buffer the buffer
	 */
	public static void texParameter (int target, int pname, FloatBuffer buffer) {
		GL11.glTexParameter(target, pname, buffer);
	}

	/**
	 * Tex parameter.
	 *
	 * @param target the target
	 * @param pname the pname
	 * @param buffer the buffer
	 */
	public static void texParameter (int target, int pname, IntBuffer buffer) {
		GL11.glTexParameter(target, pname, buffer);
	}

	/**
	 * Gets the texture id.
	 *
	 * @param resource - The ResourceLocation of which to get the GL texture ID
	 *        from.
	 * @return Returns the GL texture ID of the specified ResourceLocation
	 */
	public static int getTextureId (ResourceLocation resource) {
		Object object = CommonUtil.getMinecraft().getTextureManager().getTexture(resource);
		object = object == null ? new SimpleTexture(resource) : object;
		return ((ITextureObject) object).getGlTextureId();
	}

	/**
	 * Shade smooth.
	 */
	public static void shadeSmooth () {
		GL11.glShadeModel(GL11.GL_SMOOTH);
	}

	/**
	 * Shade flat.
	 */
	public static void shadeFlat () {
		GL11.glShadeModel(GL11.GL_FLAT);
	}

	/**
	 * Enable rescale normal.
	 */
	public static void enableRescaleNormal () {
		enable(GL12.GL_RESCALE_NORMAL);
	}

	/**
	 * Disable rescale normal.
	 */
	public static void disableRescaleNormal () {
		disable(GL12.GL_RESCALE_NORMAL);
	}

	/**
	 * Enable standard item lighting.
	 */
	public static void enableStandardItemLighting () {
		RenderHelper.enableStandardItemLighting();
	}

	/**
	 * Disable standard item lighting.
	 */
	public static void disableStandardItemLighting () {
		RenderHelper.disableStandardItemLighting();
	}

	/**
	 * Enable alpha test.
	 */
	public static void enableAlphaTest () {
		GlStateManager.enableAlpha();
	}

	/**
	 * Disable alpha test.
	 */
	public static void disableAlphaTest () {
		GlStateManager.disableAlpha();
	}

	/**
	 * Read buffer.
	 *
	 * @param mode the mode
	 */
	public static void readBuffer (int mode) {
		GL11.glReadBuffer(mode);
	}

	/**
	 * Read pixels.
	 *
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 * @param format the format
	 * @param type the type
	 * @param pixels the pixels
	 */
	public static void readPixels (int x, int y, int width, int height, int format, int type, ByteBuffer pixels) {
		GL11.glReadPixels(x, y, width, height, format, type, pixels);
	}

	/**
	 * Enable cull face.
	 */
	public static void enableCullFace () {
		GlStateManager.enableCull();
	}

	/**
	 * Disable cull face.
	 */
	public static void disableCullFace () {
		GlStateManager.disableCull();
	}

	/**
	 * Disable lightmapping, enable GL_BLEND, and reset the colors to default
	 * values.
	 */
	public static void disableLightMapping () {
		char light = 61680;
		OpenGL.enableBlend();
		OpenGL.blendFunc(GL_ONE, GL_ONE);
		OpenGL.depthMask(true);
		OpenGlHelper.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) light % 65536 / 1.0F, (float) light
				/ 65536 / 1.0F);
		OpenGL.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Enable lightmapping, disable GL_BLEND, and reset the colors to default
	 * values.
	 */
	public static void enableLightMapping () {
		char light = 61680;
		OpenGL.disableBlend();
		OpenGL.depthMask(true);
		OpenGL.setLightmapTextureCoords(OpenGlHelper.lightmapTexUnit, (float) light % 65536 / 1.0F, (float) light
				/ 65536 / 1.0F);
		OpenGL.color(1.0F, 1.0F, 1.0F, 1.0F);
	}

	/**
	 * Disable lighting.
	 */
	public static void disableLight () {
		OpenGL.setActiveTexture(OpenGL.lightmapTexUnit);
		if (lightmapTexUnitTextureEnable = OpenGL.getBoolean(GL11.GL_TEXTURE_2D)) {
			OpenGL.disableTexture2d();
		}
		OpenGL.setActiveTexture(OpenGlHelper.defaultTexUnit);
		OpenGL.disableLighting();
	}

	/**
	 * Enable lighting.
	 */
	public static void enableLight () {
		OpenGL.setActiveTexture(OpenGL.lightmapTexUnit);
		if (lightmapTexUnitTextureEnable) {
			OpenGL.enableTexture2d();
		}
		OpenGL.setActiveTexture(OpenGL.defaultTexUnit);
		OpenGL.enableLighting();
	}

	/**
	 * Blend clear.
	 */
	public static void blendClear () {
		OpenGlHelper.glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA, GL_ONE, GL_ZERO);
	}

	/**
	 * Combonation of GL functions used to smooth out the rough edges of a 2D
	 * texture.
	 */
	public static void antiAlias2d () {
		OpenGL.texParameter(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
		OpenGL.texParameter(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);
		OpenGL.texParameter(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP);
		OpenGL.texParameter(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP);
	}

	/**
	 * Enable fog.
	 */
	public static void enableFog () {
		GlStateManager.enableFog();
	}

	/**
	 * Disable fog.
	 */
	public static void disableFog () {
		GlStateManager.disableFog();
	}

	/**
	 * Bind texture.
	 *
	 * @param target the target
	 * @param texture the texture
	 */
	public static void bindTexture (int target, int texture) {
		GL11.glBindTexture(target, texture);
	}

	/**
	 * Copy tex sub image.
	 *
	 * @param target the target
	 * @param level the level
	 * @param xoffset the xoffset
	 * @param yoffset the yoffset
	 * @param x the x
	 * @param y the y
	 */
	public static void copyTexSubImage (int target, int level, int xoffset, int yoffset, int x, int y) {
		GL11.glCopyTexSubImage1D(target, level, xoffset, yoffset, x, y);
	}

	/**
	 * Copy tex sub image.
	 *
	 * @param target the target
	 * @param level the level
	 * @param xoffset the xoffset
	 * @param yoffset the yoffset
	 * @param x the x
	 * @param y the y
	 * @param width the width
	 * @param height the height
	 */
	public static void copyTexSubImage (int target, int level, int xoffset, int yoffset, int x, int y, int width, int height) {
		GL11.glCopyTexSubImage2D(target, level, xoffset, yoffset, x, y, width, height);
	}

	/**
	 * Copy downsized render.
	 *
	 * @param manager the manager
	 * @param target the target
	 * @param x the x
	 * @param y the y
	 * @param w the w
	 * @param h the h
	 * @param index the index
	 */
	public static void copyDownsizedRender (TextureManager manager, ResourceLocation target, int x, int y, int w, int h, int index) {
		ITextureObject textureObject = manager.getTexture(target);

		if (textureObject != null) {
			OpenGL.bindTexture(GL11.GL_TEXTURE_2D, textureObject.getGlTextureId());
			OpenGL.copyTexSubImage(GL11.GL_TEXTURE_2D, 0, index, index, x, y, w, h);
		}
	}

	/**
	 * Creates the frame buffer.
	 *
	 * @param width the width
	 * @param height the height
	 * @param useDepth the use depth
	 * @return the framebuffer
	 */
	public static Framebuffer createFrameBuffer (int width, int height, boolean useDepth) {
		Framebuffer render = new Framebuffer(width, height, useDepth);
		frameBuffers.add(render);
		return render;
	}

	/**
	 * Destroy frame buffer.
	 *
	 * @param buffer the buffer
	 */
	public static void destroyFrameBuffer (Framebuffer buffer) {
		OpenGL.enableDepthTest();
		if (buffer.framebufferObject >= 0) {
			buffer.deleteFramebuffer();
		}
		frameBuffers.remove(buffer);
	}

	/**
	 * Rotate.
	 *
	 * @param tile the tile
	 */
	@SideOnly(Side.CLIENT)
	public static void rotate (TileEntity tile) {
		if (tile instanceof IRotatableYAxis) {
			IRotatableYAxis rotatable = (IRotatableYAxis) tile;

			if (rotatable != null && rotatable.getRotationYAxis() != null) {
				if (rotatable.getRotationYAxis() != null) {
					if (rotatable.getRotationYAxis() == EnumFacing.NORTH) {
						rotate(180F, 0F, 1F, 0F);
					}
					if (rotatable.getRotationYAxis() == EnumFacing.WEST) {
						rotate(-90F, 0F, 1F, 0F);
					}
					else if (rotatable.getRotationYAxis() == EnumFacing.EAST) {
						rotate(90F, 0F, 1F, 0F);
					}
				}
			}
		}

		if (tile instanceof IRotatableXAxis) {
			IRotatableXAxis rotatable = (IRotatableXAxis) tile;

			if (rotatable != null && rotatable.getRotationXAxis() != null) {
				if (rotatable.getRotationXAxis() != null) {
					if (rotatable.getRotationXAxis() == EnumFacing.DOWN) {
						rotate(-180F, 1F, 0F, 0F);
					}
				}
			}
		}
	}

	/**
	 * Rotate opposite.
	 *
	 * @param tile the tile
	 */
	@SideOnly(Side.CLIENT)
	public static void rotateOpposite (TileEntity tile) {
		if (tile instanceof IRotatableYAxis) {
			IRotatableYAxis rotatable = (IRotatableYAxis) tile;

			if (rotatable != null && rotatable.getRotationYAxis() != null) {
				if (rotatable.getRotationYAxis() != null) {
					if (rotatable.getRotationYAxis() == EnumFacing.SOUTH) {
						rotate(180F, 0F, 1F, 0F);
					}
					else if (rotatable.getRotationYAxis() == EnumFacing.NORTH) {
						rotate(0F, 0F, 0F, 0F);
					}
					else if (rotatable.getRotationYAxis() == EnumFacing.EAST) {
						rotate(-90F, 0F, 1F, 0F);
					}
					else if (rotatable.getRotationYAxis() == EnumFacing.WEST) {
						rotate(90F, 0F, 1F, 0F);
					}
				}
			}
		}
	}
}