package se.chalmers.project14.model;

import java.util.List;

/**
 * class that represent a house
 * 
 * @author tomassellden
 * 
 */
public class House {
	private String house;
	private int id;
	private List<Coordinates> coordinateList;
	private List<House> houseList;
	private List<Door> doorList;

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

	public List<Coordinates> getCoordinateList() {
		return coordinateList;
	}

	public List<House> getHouseList() {
		return houseList;
	}

	public List<Door> getDoorList() {
		return doorList;
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
