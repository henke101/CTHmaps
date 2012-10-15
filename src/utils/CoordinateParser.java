package utils;

import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import se.chalmers.project14.model.Door;

/*
 * Copyright (c) 2012 ICRL
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
	

	public int [] parseCoordinatesFromDoor(List<Door> doors){
			String s = doors.get(0).getDoorCoordinates();
			
			for(int i=0; i<doors.size();i++){
				s= s + "," + doors.get(i).getDoorCoordinates();
			}
			return parseCoordinatesFromString(s);
	}
		
	
	public HashSet<Integer> parseCoordinatesToSet(String s){
		int [] arr = parseCoordinatesFromString(s);
		HashSet<Integer> set = new HashSet<Integer>();
		for (int i=0; i<arr.length; i+=2){
			set.add(arr[i]);
			set.add(arr[i+1]);
		}
		Iterator<Integer> iterator = set.iterator();
		while(iterator.hasNext()){
			System.out.println(iterator.next());
		}
		
		return set;
	}
}
