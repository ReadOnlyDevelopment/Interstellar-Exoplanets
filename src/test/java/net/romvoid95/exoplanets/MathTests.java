/**
 * 
 */
package net.romvoid95.exoplanets;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import net.romvoid95.api.math.Conversion;

/**
 * @author ROMVoid95
 *
 */
class MathTests {

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeAll
	static void setUpBeforeClass () throws Exception {}

	/**
	 * @throws java.lang.Exception
	 */
	@BeforeEach
	void setUp () throws Exception {}

	/**
	 * Test method for {@link net.romvoid95.api.math.Conversion#toCelciusInt(double)}.
	 */
	@Test
	final void testToCelciusInt () {
		assertEquals(0, Conversion.toCelciusInt(32.0));
	}

	/**
	 * Test method for {@link net.romvoid95.api.math.Conversion#toCelciusDouble(double)}.
	 */
	@Test
	final void testToCelciusDouble () {
		assertEquals(0.0, Conversion.toCelciusDouble(32.0));
	}

	/**
	 * Test method for {@link net.romvoid95.api.math.Conversion#toFahrenheit(double)}.
	 */
	@Test
	final void testToFahrenheitOne () {
		assertEquals(32.0, Conversion.toFahrenheit(0.0));
	}

	/**
	 * Test method for {@link net.romvoid95.api.math.Conversion#toFahrenheit(int)}.
	 */
	@Test
	final void testToFahrenheitTwo () {
		assertEquals(32.0, Conversion.toFahrenheit(0));
	}
}
