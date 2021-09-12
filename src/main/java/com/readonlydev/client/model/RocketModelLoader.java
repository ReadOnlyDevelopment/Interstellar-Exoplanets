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

import java.io.IOException;
import java.util.*;

import com.google.common.collect.ImmutableMap;
import com.readonlydev.Exoplanets;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.*;
import net.minecraft.util.ResourceLocation;

import net.minecraftforge.client.model.*;
import net.minecraftforge.client.model.obj.OBJModel;

public class RocketModelLoader implements ICustomModelLoader {
	public static final RocketModelLoader instance = new RocketModelLoader();
	private IResourceManager manager;
	private final Set<String> enabledDomains = new HashSet<>();
	private final Map<ResourceLocation, IModel> cache = new HashMap<>();

	static {
		((IReloadableResourceManager) Minecraft.getMinecraft().getResourceManager()).registerReloadListener(instance);
	}

	public void addDomain(String domain) {
		enabledDomains.add(domain.toLowerCase());
		Exoplanets.log.info("Added " + domain.toLowerCase() + " to OBJ domain list");
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
		if (model == null) {
			return ModelLoaderRegistry.getMissingModel();
		}
		return model;
	}
}
