/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2020, ROMVoid95 <rom.readonlydev@gmail.com>
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package net.rom.exoplanets.internal.client;

import java.io.IOException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.ImmutableMap;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.IReloadableResourceManager;
import net.minecraft.client.resources.IResource;
import net.minecraft.client.resources.IResourceManager;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.client.model.ICustomModelLoader;
import net.minecraftforge.client.model.IModel;
import net.minecraftforge.client.model.ModelLoaderRegistry;
import net.minecraftforge.client.model.obj.OBJModel;

public class ExoModelLoader implements ICustomModelLoader {
	public static final ExoModelLoader instance = new ExoModelLoader();
	private IResourceManager manager;
	private final Set<String> enabledDomains = new HashSet<>();
	private final Map<ResourceLocation, IModel> cache = new HashMap<>();

	static {
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(instance);
	}

	public void addDomain(String domain) {
		enabledDomains.add(domain.toLowerCase());
	}

	@Override
	public void onResourceManagerReload(IResourceManager resourceManager) {
		this.manager = resourceManager;
		cache.clear();
	}

	@Override
	public boolean accepts(ResourceLocation modelLocation) {
		return enabledDomains.contains(modelLocation.getResourceDomain())
				&& modelLocation.getResourcePath().endsWith(".obj");
	}

	@Override
	public IModel loadModel(ResourceLocation modelLocation) throws IOException {
		IModel model = null;
		if (cache.containsKey(modelLocation)) {
			model = cache.get(modelLocation);
		} else {
			try {
				String prefix = modelLocation.getResourcePath().contains("models/") ? "" : "models/";
				ResourceLocation file = new ResourceLocation(modelLocation.getResourceDomain(),
						prefix + modelLocation.getResourcePath());
				IResource resource = manager.getResource(file);
				if (resource != null) {
					OBJModel.Parser parser = new OBJModel.Parser(resource, manager);
					try {
						model = parser.parse().process(ImmutableMap.of("flip-v", "true"));
					} finally {
						resource.getInputStream().close();
						cache.put(modelLocation, model);
					}
				}
			} catch (IOException e) {
				throw e;
			}
		}
		if (model == null)
			return ModelLoaderRegistry.getMissingModel();
		return model;
	}
}
