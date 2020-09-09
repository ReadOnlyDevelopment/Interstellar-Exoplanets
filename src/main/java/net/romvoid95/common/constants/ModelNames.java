package net.romvoid95.common.constants;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import net.romvoid95.client.model.ModelTransWrapper;
import net.romvoid95.client.render.ItemModelRocket;

public enum ModelNames {

	ROCKET("twopersonrocket", ItemModelRocket.class);

	private final String                             modelName;
	private final Class<? extends ModelTransWrapper> clazz;

	ModelNames(String modelName, Class<? extends ModelTransWrapper> clazz) {
		this.modelName = modelName;
		this.clazz     = clazz;
	}

	public String modelName () {
		return modelName;
	}

	public String objFile () {
		return modelName + ".obj";
	}

	public Class<? extends ModelTransWrapper> modelClass () {
		return clazz;
	}

	public static List<ModelNames> getModels () {
		return Stream.of(ModelNames.values()).collect(Collectors.toList());
	}

}
