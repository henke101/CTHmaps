package se.chalmers.project14.test;

import java.util.ArrayList;
import java.util.List;
import junit.framework.Assert;
import junit.framework.TestCase;
import se.chalmers.project14.model.Door;
import utils.CoordinateParser;

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

	public void testParseCoordinatesFromDoors() {
		Door doorOne = new Door(
				"11111111,22222222,33333333,44444444,55555555,66666666,77777777,88888888",
				"This string is irrelevant to the test");
		Door doorTwo = new Door(
				"10101010,20202020,30303030,40404040",
				"This string is irrelevant to the test");
		Door doorThree = new Door(
				"12345678,87654321,11223344,44332211,10203040,40302010",
				"This string is irrelevant to the test");
		
		List<Door> doors = new ArrayList<Door>();
		doors.add(doorOne);
		doors.add(doorTwo);
		doors.add(doorThree);
		
		int[] expectedCoordinates = {11111111,22222222,33333333,44444444,55555555,66666666,77777777,88888888,10101010,20202020,30303030,40404040,12345678,87654321,11223344,44332211,10203040,40302010};
		int[] actualCoordinates = parser.parseCoordinatesFromDoors(doors);
		
		for (int i = 0;i<expectedCoordinates.length;i++){
			assertEquals(expectedCoordinates[i], actualCoordinates[i]);	
		}	
	}
	
	public void testParseCoordinatesToSet(){
		
	}
}
