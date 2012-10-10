package se.chalmers.project14.model;

/**
 * class that represent a house
 * 
 * @author tomassellden
 * 
 */
public class House {

	private String house;
	private int id;

	public House() {
		// empty constructor
	}

	public House(String house) {
		this.house = house;
	}

	public House(int id, String house) {
		this.id = id;
		this.house = house;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setHouse(String house) {
		this.house = house;
	}

	public String getHouse() {
		return house;
	}

}
