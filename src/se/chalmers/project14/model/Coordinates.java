package se.chalmers.project14.model;

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
