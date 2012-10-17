package utils;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

public class CoordinateParser {
	private static final CoordinateParser parser = new CoordinateParser();

	private CoordinateParser() {

	}
	public static CoordinateParser getInstance(){
			return parser;
	}

	public int [] parseCoordinates(String s){
		String [] coordinatesString = s.split(",");
		int [] CoordinatesInt = new int[coordinatesString.length];
		for (int i=0; i<coordinatesString.length; i++){
			CoordinatesInt[i] = Integer.parseInt(coordinatesString[i]);
		}		
		return CoordinatesInt;
	}
}
