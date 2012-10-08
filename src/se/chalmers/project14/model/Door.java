package se.chalmers.project14.model;

public class Door {

	private String doorCoordinates;
	private String floor;

	public Door() {
		// empty constructor
	}

	public Door(String doorCoordinates, String floor) {
		this.doorCoordinates = doorCoordinates;
		this.floor = floor;
	}

	public void setDoor(String doorCoordinates) {
		this.doorCoordinates = doorCoordinates;
	}

	public String getDoor() {
		return doorCoordinates;
	}

	public void SetFloor(String floor) {
		this.floor = floor;
	}

	public String getFloor() {
		return floor;
	}

}
