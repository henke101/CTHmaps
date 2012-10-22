package se.chalmers.project14.test;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

import junit.framework.TestCase;
import se.chalmers.project14.model.Door;

/*
 * A simple test class testing the getters and setter for class Door
 */

public class TestDoor extends TestCase {

	private Door door = new Door();

	public void testGetDoors() {
		door.setDoor("121212,121212");
		String getDoor = door.getDoorCoordinates();
		String expectedResult = "121212,121212";
		assertEquals(expectedResult, getDoor);
	}

	public void testGetBuilding() {
		door.setBuilding("Maskinhuset");
		String getBuilding = door.getBuilding();
		String expectedResult = "Maskinhuset";
		assertEquals(expectedResult, getBuilding);
	}
}
