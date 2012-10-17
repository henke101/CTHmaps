package se.chalmers.project14.model;

/*
 * Copyright (c) 2012 Henrik Andersson, Anton Palmqvist, Tomas Selldén and Marcus Tyrén
 * See the file license.txt for copying permission.
 */

/**
 * class that represent a house
 * 
 * @author tomassellden
 * 
 */
public class House {

	private String house;
	private String lectureRoom;
	private int id;

	public House() {
		// empty constructor
	}

	public House(String house) {
		this.house = house;
	}

	public House(int id, String house, String lectureRoom) {
		this.id = id;
		this.house = house;
		this.lectureRoom = lectureRoom;
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

	public void setLectureRoom(String lectureRoom) {
		this.lectureRoom = lectureRoom;
	}

	public String getLectureRoom() {
		return lectureRoom;
	}
}
