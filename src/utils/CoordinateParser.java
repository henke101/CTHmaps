package utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import android.util.Log;

import se.chalmers.project14.model.Door;

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

	public int [] parseCoordinatesFromString(String s){
		String [] coordinatesString = s.split(",");
		int [] coordinatesInt = new int[coordinatesString.length];
		for (int i=0; i<coordinatesString.length; i++){
			coordinatesInt[i] = Integer.parseInt(coordinatesString[i]);
		}		
		return coordinatesInt;
	}
	

	public int [] parseCoordinatesFromDoors(List<Door> doors){
			String s = doors.get(0).getDoorCoordinates();
			
			for(int i=1; i<doors.size();i++){
				s= s + "," + doors.get(i).getDoorCoordinates();
			}
			return parseCoordinatesFromString(s);
	}
		
	
	public HashSet<Integer> parseCoordinatesToSet(String s){
		int [] array = parseCoordinatesFromString(s);
		
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i=0; i<array.length; i=i+2){
			int lon = array[i];	
			int lat = array[i+1];
			set.add(lat);
			set.add(lon);	
		}
	
		return set;
	}
	
	public HashSet<Integer> parseCoordinatesToSetFromDoors(List<Door> doors){
		String s = doors.get(0).getDoorCoordinates();
		
		for(int i=1; i<doors.size();i++){
			s= s + "," + doors.get(i).getDoorCoordinates();
		}
		return parseCoordinatesToSet(s);

	}
}
