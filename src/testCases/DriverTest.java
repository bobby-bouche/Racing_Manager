package testCases;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingTeam.Driver;
import racingTeam.ExperienceLevel;

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
	
	
	
	/*
	 * testing for valid driver constructor initialization
	 */
	@Test
	void testInitialization() {
		
		//Arrange
		int expectedDriverID = 5;
		String expectedName = "Larry";
		String expectedExperienceLevel = "rookie";
		
		//Act
		Driver d = new Driver(expectedDriverID,expectedName,expectedExperienceLevel);
		
		//assert
		assertEquals(expectedDriverID, d.getDriverID());
		assertEquals(expectedName, d.getName());
		assertEquals(ExperienceLevel.ROOKIE, d.getExperienceLevel());
	}
	
	
	@Test
	void testSetDriverID() {
		
		//Arrange
		
		//Act
		
		//Assert
		
	}
	
	
	@Test
	void testSetDriverName() {
		
	}
	
	
	@Test
	void testSetDriverExperienceLevel() {
		
	}
	
	
	@Test
	void testInvalidInputconstructor() {
		
	}
	
	
	@Test
	void testMultipleStateChanges() {
		
	}
	
	
	@Test
	void testConsistency() {
		
	}
	
	
	@Test
	void testInvalidEnumValue() {
		// TODO create enum for experienceLEvel
	}
	
	
	@Test
	void testInvalidDriverIDChange() {
		
	}
	
	
	@Test
	void testInvalidDriverNameChange() {
		
	}
	
	
	@Test
	void testInvalidDriverExperienceLevelChange() {
		
	}
	
	
	@Test
	void testEdgeCases() {
		
	}
	
	
	@Test
	void testSerializationPerformance() {
		
	}
	

}
