package se.chalmers.project14.test;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import junit.framework.Assert;
import junit.framework.TestCase;
import se.chalmers.project14.utils.CoordinateParser;

public class TestCoordinateParser extends TestCase {
	CoordinateParser parser = CoordinateParser.getInstance();

	public void testParseCoordinatesFromString() {
		String s = "12345678,87654321,11223344,44332211,10203040,40302010";
		int[] actualCoordinates = parser.parseCoordinatesFromString(s);
		int[] expectedCoordinates = { 12345678, 87654321, 11223344, 44332211,
				10203040, 40302010 };
		for (int i = 0; i < expectedCoordinates.length; i++) {
			Assert.assertEquals(actualCoordinates[i], expectedCoordinates[i]);
		}
	}

}
