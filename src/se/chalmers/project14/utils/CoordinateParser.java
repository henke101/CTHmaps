package se.chalmers.project14.utils;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * MIT is the used license.See the file license.txt for copying permission.
 */

/**
 * System version 0.3 21 oktober 2012
 */

public class CoordinateParser {
	private static final CoordinateParser parser = new CoordinateParser();

	private CoordinateParser() {

	}
	/**
	 * Returns the singleton instance of CoordinateParser
	 * @return CoordinateParser
	 */
	public static CoordinateParser getInstance() {
		return parser;
	}
	/**
	 * Parses from a string of coordinates separated by a "," into an array of coordinates
	 * @param s The string of coordinates
	 * @return int []
	 */
	public int[] parseCoordinatesFromString(String s) {
		String[] coordinatesString = s.split(",");
		int[] coordinatesInt = new int[coordinatesString.length];
		for (int i = 0; i < coordinatesString.length; i++) {
			coordinatesInt[i] = Integer.parseInt(coordinatesString[i]);
		}
		return coordinatesInt;
	}
}
