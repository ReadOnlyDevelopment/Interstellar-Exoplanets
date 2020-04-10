package net.rom.api.enums;

import net.minecraft.util.IStringSerializable;

/**
 * The Enum EnumPlanetType.
 */
public enum EnumPlanetType implements IStringSerializable{

	/**
	 * <b>Asteroidan</b><br>
	 * <b>Mass </b> = 0 - 0.00001</br>
	 * <b>Radius </b> = 0 - 0.03
	 * <p>
	 * Asteroidans are small irregular bodies (below the hydrostatic equilibrium)
	 * that are not able to hold a stable atmosphere.
	 * <p>
	 * 
	 */
	ASTEROIDAN("Asteroidan"),
	/**
	 * <b>Mercurian</b><br>
	 * <b>Mass </b> = 0.00001 - 0.1</br>
	 * <b>Radius </b> = 0.03 - 0.7
	 * <p>
	 * Mercurians are only able to hold a significant atmospheres in the cold zones
	 * beyond the snow line (i.e. Titan).
	 * <p>
	 */
	MERCURIAN("Mercurian"),
	/**
	 * <b>SubTerran</b><br>
	 * <b>Mass </b> = 0.1 - 0.5</br>
	 * <b>Radius </b> = 0.5 - 1.2
	 * <p>
	 * Subterrans are able to hold a significant atmospheres after the outer edges
	 * of the habitable zone (i.e. Mars).
	 * <p>
	 */
	SUBTERRAN("Subterran"),
	/**
	 * <b>Terran</b><br>
	 * <b>Mass </b> = 0.5 - 2</br>
	 * <b>Radius </b> = 0.8 - 1.9
	 * <p>
	 * Terrans are able to hold a significant atmosphere with liquid water within
	 * the habitable zone (i.e. Earth)
	 * <p>
	 */
	TERRAN("Terran"),
	/**
	 * <b>SuperTerran</b><br>
	 * <b>Mass </b> = 2 - 10</br>
	 * <b>Radius </b> = 1.3 - 3.3
	 * <p>
	 * Superterrans are able to hold dense atmospheres with liquid water within the
	 * habitable zone.
	 * <p>
	 */
	SUPERTERRAN("Superterran"),
	/**
	 * <b>Neptunian</b><br>
	 * <b>Mass </b> = 10 - 50</br>
	 * <b>Radius </b> = 2.1 - 5.7
	 * <p>
	 * Neptunians can have dense atmospheres in the hot zone.
	 * <p>
	 */
	NEPTUNIAN("Neptunian"),
	/**
	 * <b>Jovian</b><br>
	 * <b>Mass </b> = 50 - 5000</br>
	 * <b>Radius </b> = 3.5 - 27
	 * <p>
	 * Jovians can have superdense atmospheres in the hot zone.
	 * <p>
	 */
	JOVIAN("Jovian");
	
	private String name;

	/**
	 * Instantiates a new enum planet type.
	 *
	 * @param string the string
	 */
	EnumPlanetType(String name) {
		this.name = name;
	}

	@Override
	public String getName() {
		return this.name;
	}

}
