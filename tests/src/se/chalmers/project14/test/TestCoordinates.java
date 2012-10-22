package se.chalmers.project14.test;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import se.chalmers.project14.model.Coordinates;
import junit.framework.TestCase;

/*
 * A simple test class testing the getters and setter for class Coordinates
 */

public class TestCoordinates extends TestCase {

	private Coordinates coordinate = new Coordinates();

	public void testCoordinates() {
		coordinate.setCoordinates("10101010,32323232");
		String getCoordinate = coordinate.getCoordinates();
		String expectedResult = "10101010,32323232";
		assertEquals(expectedResult, getCoordinate);
	}
}
