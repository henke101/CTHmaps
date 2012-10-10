package utils;

public class CoordinateParser {
	private static final CoordinateParser parser = new CoordinateParser();

	public CoordinateParser() {

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
