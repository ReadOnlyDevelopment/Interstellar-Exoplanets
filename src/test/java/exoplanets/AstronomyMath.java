package exoplanets;

import static org.junit.Assert.*;

import org.apache.commons.math3.util.Precision;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import net.rom.api.stellar.AstronomicalConstants;
import net.rom.api.stellar.Calculations;

public class AstronomyMath {

	@Test
	public void testEventHorizonCalculation() {
		double answer = Calculations.schwartzchildRadius(500.0D);
		// test answer from https://www.omnicalculator.com/physics/schwarzschild-radius?c=USD&v=M:500!suns
		double rounded = Precision.round(answer, 6);
		System.out.println(answer);
		System.out.println(rounded);
	}

}
