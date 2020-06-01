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

package net.rom.exoplanets.internal.enums;

/**
 * Thermal Planetary Habitability Classification<br><br>
 * 
 * The proposed thermal planetary habitability classification provides a simple classification scheme based on temperature for terrestrial exoplanets<br><br>
 * 
 * See <a href="http://phl.upr.edu/library/notes/athermalplanetaryhabitabilityclassificationforexoplanets">Publication</a>
 * 
 * <br><br>
 * All Data is taken from the Planetary Habitability Labratory from the University of Puerto Rico at Arecibo 
 * @author ROMVoid
 */
public enum EnumTPHClass {

	/** 
	 * Class hP
	 * 
	 * Temp: -100 thru -50. 
	 * */
	HP("Class hP", "Hypopsychroplanet", -100.0F, -50.0F),
	
	/** 
	 * Class P
	 * 
	 * Temp: -49 thru -0. 
	 * */
	P("Class P", "Psychroplanet", -49.0F, 0.0F),
	
	/** 
	 * Class M
	 * 
	 * Temp: 1 thru 50. 
	 * */
	M("Class M", "Mesoplanet", 1.0F, 49.0F),
	
	/** 
	 * Class T
	 * 
	 * Temp: 51 thru 100. 
	 * */
	T("Class T", "Theroplanet", 50.0F, 100.0F),
	
	/** 
	 * Class hT
	 * 
	 * Temp: 101 thru 150. 
	 * */
	HT("Class hT", "hyperthermoplanet", 101.0F, 150.0F);

	/** The class name. */
	private String className;
	
	/** The name. */
	private String name;
	
	/** The temp low. */
	private float tempLow;
	
	/** The temp high. */
	private float tempHigh;

	/**
	 * Instantiates a new enum TPH class.
	 *
	 * @param className the class name
	 * @param name the name
	 * @param tempLow the temp low
	 * @param tempHigh the temp high
	 */
	EnumTPHClass(String className, String name, float tempLow, float tempHigh) {
		this.className = className;
		this.name = name;
		this.tempLow = tempLow;
		this.tempHigh = tempHigh;
	}

	/**
	 * Instantiates a new enum TPH class.
	 *
	 * @param className the class name
	 * @param name the name
	 */
	EnumTPHClass(String className, String name) {
		this.className = className;
		this.name = name;
	}

	/**
	 * Gets the temp high.
	 *
	 * @return the temp high
	 */
	public float getTempHigh() {
		return this.tempHigh;
	}

	/**
	 * Gets the temp low.
	 *
	 * @return the temp low
	 */
	public float getTempLow() {
		return this.tempLow;
	}

	/**
	 * Gets the class name.
	 *
	 * @return the className
	 */
	public String getClassName() {
		return this.className;
	}

	/**
	 * Gets the name.
	 *
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

}
