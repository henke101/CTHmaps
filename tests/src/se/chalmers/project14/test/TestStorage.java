package se.chalmers.project14.test;

import java.util.ArrayList;
import java.util.List;

import se.chalmers.project14.database.DatabaseHandler;
import se.chalmers.project14.database.DatabaseStorage;
import se.chalmers.project14.model.Coordinates;
import se.chalmers.project14.model.Door;
import se.chalmers.project14.model.House;
import android.test.AndroidTestCase;
import android.test.RenamingDelegatingContext;

public class TestStorage extends AndroidTestCase {

	private static final String TEST_FILE_PREFIX = "test_";
	private DatabaseStorage db;
	private int databaseId = 1;

	@Override
	protected void setUp() throws Exception {
		super.setUp();
		RenamingDelegatingContext context = new RenamingDelegatingContext(
				getContext(), TEST_FILE_PREFIX);
		db = new DatabaseHandler(context);
		// Creating three door, House and Coordinates object so We can test the
		// database getters
		Door door = new Door("Edithuset", "11111,11111");
		House house = new House(databaseId, "ES51", "5");
		Coordinates coordinate = new Coordinates("2222,2222");
		Door doorTwo = new Door("MaskinHuset", "121212,121212");
		Door doorThree = new Door("Edithuset", "333333,33333");
		House houseTwo = new House(2, "Bulten", "2");
		House houseThree = new House(3, "3201", "3");
		db.addCthHouse(house, coordinate, door);
		db.addCthHouse(houseTwo, coordinate, doorTwo);
		db.addCthHouse(houseThree, coordinate, doorThree);

	}

	/*
	 * Getting the coordinate from test database and create an expected value
	 * and compare these with each other
	 */
	public void testDatabaseGetCoordinates() {
		Coordinates coordinate = db.getCoordinates(databaseId);
		String expectedResult = "2222,2222";
		String databaseCoordinate = coordinate.getCoordinates();
		assertEquals(expectedResult, databaseCoordinate);
	}

	/*
	 * Getting the doorCoordinates and building from test database and create an
	 * expected value and compare these with each other
	 */
	public void testDatabaseGetDoor() {
		Door door = db.getDoorCoordinates(databaseId);
		String expectedResult = "Edithuset,11111,11111";
		String databaseDoorCoordinates = door.getBuilding() + ","
				+ door.getDoorCoordinates();
		assertEquals(expectedResult, databaseDoorCoordinates);
	}

	/*
	 * Getting the lectureroom from test database and create an expected value
	 * and compare these with each other
	 */
	public void testDatabaseGetHouse() {
		House house = db.getHouse(databaseId);
		String expectedResult = "ES51";
		String databaseHouse = house.getLectureRoom();
		assertEquals(expectedResult, databaseHouse);
	}

	/*
	 * Getting a list with all Doors and Buildings from test database and
	 * creates an expected list and compare these with each other
	 */
	public void testDatabaseAllDorsAndBuildings() {
		List<Door> doorAndBuildingList = db.getAllDoorsAndBuildings();
		List<Door> expectedList = new ArrayList<Door>();
		expectedList.add(new Door("11111,11111", "Edithuset"));
		expectedList.add(new Door("121212,121212", "MaskinHuset"));
		expectedList.add(new Door("333333,33333", "Edithuset"));
		assertEquals(expectedList.size(), doorAndBuildingList.size());
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i).getBuilding(), doorAndBuildingList
					.get(i).getBuilding());
			assertEquals(expectedList.get(i).getDoorCoordinates(),
					doorAndBuildingList.get(i).getDoorCoordinates());
		}

	}

	/*
	 * Getting a list with all lecturerooms and floors and create an expected
	 * value and compare these with each other
	 */
	public void testDatabaseAllLectureRoom() {
		List<House> allLectureRoom = db.getAllLectureRoom();
		List<House> expectedList = new ArrayList<House>();
		expectedList.add(new House(databaseId, "ES51", "5"));
		expectedList.add(new House(2, "Bulten", "2"));
		expectedList.add(new House(3, "3201", "3"));
		assertEquals(expectedList.size(), allLectureRoom.size());
		for (int i = 0; i < expectedList.size(); i++) {
			assertEquals(expectedList.get(i).getId(), allLectureRoom.get(i)
					.getId());
			assertEquals(expectedList.get(i).getFloor(), allLectureRoom.get(i)
					.getFloor());
			assertEquals(expectedList.get(i).getLectureRoom(), allLectureRoom
					.get(i).getLectureRoom());
		}
	}
}
