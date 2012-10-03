package se.chalmers.project14.database;

/**
 * Class that represent a coordinate.
 * 
 * @author tomassellden
 * 
 */
public class Coordinates {

	private int _id;
	private String _CTHplace;
	private String _coordinates;

	public Coordinates() {
		// empty constructor
	}

	public Coordinates(int id, String CTHplace, String coordinates) {
		this._id = id;
		this._CTHplace = CTHplace;
		this._coordinates = coordinates;
	}

	public Coordinates(String CTHplace, String coordinates) {
		this._CTHplace = CTHplace;
		this._coordinates = coordinates;
	}

	public int getID() {
		return _id;
	}

	public void setID(int id) {
		this._id = id;
	}

	public String getCTHplace() {
		return _CTHplace;
	}

	public void setCTHplace(String CTHplace) {
		this._CTHplace = CTHplace;
	}

	public String getCoordinates() {
		return _coordinates;
	}

	public void setCoordinates(String coordinates) {
		this._coordinates = coordinates;
	}
}
