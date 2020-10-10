package net.romvoid95.common.config.planet;

import java.io.File;

import net.romvoid95.api.space.prefab.ExoPlanet;
import net.romvoid95.api.space.prefab.ExoSystem;
import net.romvoid95.core.ExoplanetsMod;

public class PlanetConfig {

	private PlanetFolder	planetFolder;
	private String			planetConfigFile;

	public PlanetConfig() {
	}

	/**
	 * @return the planetFolder
	 */
	public PlanetFolder getPlanetFolder() {
		return planetFolder;
	}

	/**
	 * @param planetFolder the planetFolder to set
	 */
	public PlanetConfig setPlanetFolder(PlanetFolder planetFolder) {
		this.planetFolder = planetFolder;
		return this;
	}

	/**
	 * @return the planetConfigFile
	 */
	public String getPlanetConfigFile() {
		return planetConfigFile;
	}

	/**
	 * @param planetConfigFile the planetConfigFile to set
	 */
	public PlanetConfig setPlanetConfigFile(String planetConfigFile) {
		this.planetConfigFile = planetConfigFile;
		return this;
	}

	public static class PlanetFolder extends File {

		private static final long	serialVersionUID	= 1L;
		private static String		systemFolder		= ExoplanetsMod.exoPlanetsDirectory + "/Systems/";

		public PlanetFolder (ExoSystem parent, ExoPlanet child) {
			super(systemFolder + parent.getSolarSystemName() + "/Planets/" + child.getExoPlanetName() + "/");
		}

		@Override
		public String toString() {
			return getPath();
		}
	}
}
