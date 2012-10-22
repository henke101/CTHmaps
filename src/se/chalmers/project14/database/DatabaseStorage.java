package se.chalmers.project14.database;

import java.util.List;

import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;

public interface DatabaseStorage {

	/**
	 * Adding house/lecture room to the database table "TABLE_HOUSE"
	 * 
	 * @param house
	 *            a house object containing information about the lecture name
	 * @param coordinates
	 *            a coordinate object containing information about the lectures
	 *            coordinates
	 * @param door
	 *            a door object containing information about the which door and
	 *            which floor the lecture room can be found
	 */
	public void addCthHouse(House house, Coordinates coordinates, Door door);

	/**
	 * Adding coordinates to the database table "TABLE_COORDINATE"
	 * 
	 * @param tableKey
	 *            the id the coordinates and are connected to
	 * @param coordinates
	 *            a coordinate object containing information about the lectures
	 *            coordinates
	 * @param door
	 *            a door object containing information about the which door and
	 *            which floor the lecture room can be found
	 */
	public void addCthCoordinates(int tableKey, Coordinates coordinates,
			Door door);

	/**
	 * Adding door cordinates and which floor lecture room can be found to the
	 * database table "TABLE_DOORCOORDINATES"
	 * 
	 * @param tableKey
	 *            the id the coordinates and are connected to
	 * @param door
	 *            a door object containing information about the which door and
	 *            which floor the lecture room can be found
	 */
	public void addCthDoors(int tableKey, Door door);

	/**
	 * Getter for the house name connected to the id
	 * 
	 * @param id
	 *            an int, the id which all information about one building is
	 *            connected to
	 * @return a House object containing the house/lecture room the id is
	 *         connected to
	 */
	public House getHouse(int id);

	/**
	 * Getter for the coordinates connected to the id
	 * 
	 * @param id
	 *            an int, the id which all information about one building is
	 *            connected to
	 * @return a Coordinate object, containing the coordinates the id is
	 *         connected to
	 * 
	 */
	public Coordinates getCoordinates(int id);

	/**
	 * Getter for the doorcoordinates and which floor the lecture room/house can
	 * be found connected to the id
	 * 
	 * @param id
	 *            an int, the id which all information about one building is
	 *            connected to
	 * @return a Door object, containing the lecture room/house coordinates and
	 *         the floor the id is connected to
	 */
	public Door getDoorCoordinates(int id);

	/**
	 * Getter for all floors/lecture rooms that exists in the database table
	 * TABLE_HOUSE
	 * 
	 * @return an ArrayList containing all house/lecture room that exists in the
	 *         database table TABLE_HOUSE
	 */
	public List<House> getAllLectureRoom();

	/**
	 * Getter for all houses/doorcoordinates that exists in the database table
	 * TABLE_DOORCOORDINATES
	 * 
	 * @return An arrayList containing all house/doorcoordinates that exists in
	 *         the database table TABLE_DOORCOORDINATES
	 */
	public List<Door> getAllDoorsAndBuildings();
}
