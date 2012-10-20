package se.chalmers.project14.test;

import se.chalmers.project14.model.Coordinates;
import junit.framework.TestCase;

public class TestCoordinates extends TestCase {

	private Coordinates coordinate = new Coordinates();

	public void testCoordinates() {
		coordinate.setCoordinates("10101010,32323232");
		String getCoordinate = coordinate.getCoordinates();
		String expectedResult = "10101010,32323232";
		assertEquals(expectedResult, getCoordinate);
	}
}
