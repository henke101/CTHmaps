package se.chalmers.project14.test;


import junit.*;
import junit.framework.Assert;
import junit.framework.TestCase;

import utils.CoordinateParser;

public class TestCoordinateParser extends TestCase{


	public void testShouldParseFromStringToIntArray(){
		CoordinateParser parser = CoordinateParser.getInstance();
		String s = "12345678,87654321,11223344,44332211,10203040,40302010";
		int [] parsedCoordinates = parser.parseCoordinatesFromString(s);
		int[] actualCoordinates = {12345678,87654321,11223344,44332211,10203040,40302010};
		for(int i = 0; i<actualCoordinates.length;i++){
			Assert.assertEquals(parsedCoordinates[i], actualCoordinates[i]);
		}				
	}


}
