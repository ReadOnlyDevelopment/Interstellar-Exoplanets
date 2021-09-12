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
package com.readonlydev.client.model;

import java.util.List;

import javax.annotation.Nullable;
import javax.vecmath.Matrix4f;

import org.apache.commons.lang3.tuple.Pair;

import net.minecraft.block.state.IBlockState;
import net.minecraft.client.renderer.block.model.BakedQuad;
import net.minecraft.client.renderer.block.model.IBakedModel;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms;
import net.minecraft.client.renderer.block.model.ItemCameraTransforms.TransformType;
import net.minecraft.client.renderer.block.model.ItemOverrideList;
import net.minecraft.client.renderer.texture.TextureAtlasSprite;
import net.minecraft.util.EnumFacing;
import net.minecraftforge.common.model.TRSRTransformation;

public abstract class ModelTransWrapper implements IBakedModel {
	private final IBakedModel parent;

	public ModelTransWrapper(IBakedModel parent) {
		this.parent = parent;
	}

	public boolean isAmbientOcclusion () {
		return parent.isAmbientOcclusion();
	}

	public boolean isGui3d () {
		return parent.isGui3d();
	}

	public boolean isBuiltInRenderer () {
		return parent.isBuiltInRenderer();
	}

	public TextureAtlasSprite getParticleTexture () {
		return parent.getParticleTexture();
	}

	@SuppressWarnings("deprecation")
	public ItemCameraTransforms getItemCameraTransforms () {
		return parent.getItemCameraTransforms();
	}

	public List<BakedQuad> getQuads (@Nullable IBlockState state, @Nullable EnumFacing side, long rand) {
		return parent.getQuads(state, side, rand);
	}

	public ItemOverrideList getOverrides () {
		return parent.getOverrides();
	}

	@Override
	public Pair<? extends IBakedModel, Matrix4f> handlePerspective (ItemCameraTransforms.TransformType cameraTransformType) {
		Matrix4f matrix4f = getTransformForPerspective(cameraTransformType);

		if (matrix4f == null) {
			return Pair.of(this, TRSRTransformation.blockCornerToCenter(TRSRTransformation.identity()).getMatrix());
		}

		return Pair.of(this, matrix4f);
	}

	abstract protected Matrix4f getTransformForPerspective (TransformType cameraTransformType);
}
