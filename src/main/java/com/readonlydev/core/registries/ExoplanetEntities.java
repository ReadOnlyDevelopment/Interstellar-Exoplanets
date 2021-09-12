package com.readonlydev.core.registries;

import com.readonlydev.common.entity.EntityTwoPlayerRocket;
import com.readonlydev.lib.registry.InterstellarRegistry;
import com.readonlydev.lib.registry.impl.EntityRegistry;

public class ExoplanetEntities extends EntityRegistry {

	@Override
	public void register(InterstellarRegistry registry) {
		registry.registerEntity(EntityTwoPlayerRocket.class, "twopersonrocket");
	}
}
