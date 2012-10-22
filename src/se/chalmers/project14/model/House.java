package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selld�n and Marcus Tyr�n
 * MIT is the used license. See the file license.txt for copying permission.
 */
/**
 * System version 0.3 21 oktober 2012
 */

/**
 * class that represent a house
 * 
 * @author tomassellden
 * 
 */
public class House {

	private String floor;
	private String lectureRoom;
	private int id;

	public House() {
		// empty constructor
	}

	public House(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}

	public House(int id, String lectureRoom, String floor) {
		this.id = id;
		this.floor = floor;
		this.lectureRoom = lectureRoom;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}

	public String getLectureRoom() {
		return lectureRoom;
	}

	public void SetFloor(String floor) {
		this.floor = floor;
	}

	public String getFloor() {
		return floor;
	}
}
