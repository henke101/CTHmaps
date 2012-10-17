package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

/**
 * class that represent a door
 * 
 * @author tomassellden
 * 
 */
public class Door {

	private String doorCoordinates;
	private String floor;

	public Door() {
		// empty constructor
	}

	public Door(String doorCoordinates, String floor) {
		this.doorCoordinates = doorCoordinates;
		this.floor = floor;
	}

	public void setDoor(String doorCoordinates) {
		this.doorCoordinates = doorCoordinates;
	}

	public String getDoorCoordinates() {
		return doorCoordinates;
	}

	public void SetFloor(String floor) {
		this.floor = floor;
	}

	public String getFloor() {
		return floor;
	}

}
