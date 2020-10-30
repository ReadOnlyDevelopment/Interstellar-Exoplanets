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
