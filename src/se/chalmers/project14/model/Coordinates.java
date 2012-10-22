package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license. See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

/**
 * Class that represent a coordinate.
 * 
 * @author tomassellden
 * 
 */
public class Coordinates {

	private String coordinates;

	public Coordinates() {
		// empty constuctor
	}

	public Coordinates(String coordinates) {
		this.coordinates = coordinates;
	}

	public String getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(String coordinates) {
		this.coordinates = coordinates;
	}
}
