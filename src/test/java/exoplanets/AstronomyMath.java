package exoplanets;

import static org.junit.Assert.*;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.rom.api.stellar.calc.AstronomicalConstants;
import net.rom.api.stellar.calc.Calculations;
import net.rom.api.stellar.enums.EnumLuminosityClass;
import net.rom.api.stellar.impl.planet.ExoPlanet;

public class AstronomyMath {
	
	private ExoPlanet b;
	private ExoPlanet c;
	private ExoPlanet d;
	
    @Before
    public void setUp() {
    }

	@Test
	public void testEventHorizonCalculation() {
		double answer = Calculations.schwartzchildRadius(500.0D);
		// test answer from https://www.omnicalculator.com/physics/schwarzschild-radius?c=USD&v=M:500!suns
		double rounded = Precision.round(answer, 6);
		System.out.println(answer);
		System.out.println(rounded);
	}
	
	@Test
	public void getPlanetInfo() {
		
		System.out.println(5.972D * Math.pow(10, 24));
	}
	
	@Test
	public void getStarInfo() {
		
	}

}
