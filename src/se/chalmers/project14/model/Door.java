package se.chalmers.project14.model;


/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

/**
 * class that represent a door
 * 
 * @author tomassellden
 * 
 */
public class Door {

	private String doorCoordinates;
	private String building;

	public Door() {
		// empty constructor
	}

	public Door(String doorCoordinates, String building) {
		this.doorCoordinates = doorCoordinates;
		this.building = building;
	}

	public void setDoor(String doorCoordinates) {
		this.doorCoordinates = doorCoordinates;
	}

	public String getDoorCoordinates() {
		return doorCoordinates;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getBuilding() {
		return building;

	}

}
