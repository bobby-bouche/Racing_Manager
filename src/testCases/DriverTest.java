package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingTeam.Driver;

class DriverTest {

	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		System.out.println("Setting up test class");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		System.out.println("tearing down test class");
	}

	@BeforeEach
	void setUp() throws Exception {
		System.out.println("test started");
	}

	@AfterEach
	void tearDown() throws Exception {
		System.out.println("test finished");
	}
	
	
	@Test
	void testInitialization() {
		
		//Arrange
		int expectedDriverID = 5;
		String expectedName = "Larry";
		int expectedExperienceLevel = 90;
		
		//Act
		Driver d = new Driver(expectedDriverID,expectedName,expectedExperienceLevel);
		
		//assert
		assertEquals(expectedDriverID, d.getDriverID());
		assertEquals(expectedName, d.getName());
		assertEquals(expectedExperienceLevel, 90);
	}
	
	
	@Test
	void testSetDriverID() {
		
	}

}
