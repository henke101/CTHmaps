package se.chalmers.project14.utils;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

public class CoordinateParser {
	private static final CoordinateParser parser = new CoordinateParser();

	private CoordinateParser() {

	}

	public static CoordinateParser getInstance() {
		return parser;
	}

	public int[] parseCoordinatesFromString(String s) {
		String[] coordinatesString = s.split(",");
		int[] coordinatesInt = new int[coordinatesString.length];
		for (int i = 0; i < coordinatesString.length; i++) {
			coordinatesInt[i] = Integer.parseInt(coordinatesString[i]);
		}
		return coordinatesInt;
	}
}
