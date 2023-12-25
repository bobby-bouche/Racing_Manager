package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingTeam.Car;
import racingTeam.CarModelType;

class CarTest {

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
	 * testing for valid car constructor initialization
	 */
	@Test
	void testInitialization() {
		
		//Arrange
		int expectedID = 1001;
		int expectedTopSpeed = 165;
		String expectedModel = "rtx_v";
		
		//Act
		Car car = new Car(expectedID,expectedTopSpeed,expectedModel);
		
		//Assert
		assertEquals(expectedID, car.getCarID());
		assertEquals(expectedTopSpeed, car.getTopSpeed());
		assertEquals(CarModelType.RTX_V, car.getModel());
	}
	
	
	/*
	 * testing for valid setCarID input
	 */
	@Test
	void testSetCarID() {
		
		//Arrange
		int expectedCarID = 5;
		Car car = new Car(1001, 165, "rtx_v");
		
		//Act
		car.setCarID(expectedCarID);
		
		//Assert
		assertEquals(expectedCarID, car.getCarID());
	}
	
	
	/*
	 * testing for valid setTopSpeed input
	 */
	@Test
	void testTopSpeed() {
		
		//Arrange
		int expectedTopSpeed = 200;
		Car car = new Car(1001, 165, "rtx_v");
		
		//Act
		car.setTopSpeed(expectedTopSpeed);
		
		//Assert
		assertEquals(expectedTopSpeed, car.getTopSpeed());
	}
	
	
	/*
	 * testing for valid change in car model type
	 */
	@Test
	void testModelType() {
		
		//Arrange
		String expectedModel = "tgi_iii";
		Car car = new Car(1001, 165, "rtx_v");
		
		//Act
		car.setModel(expectedModel);
		
		//Assert
		assertEquals(CarModelType.TGI_III, car.getModel());
	}
	
	
	/*
	 * testing for input handling
	 */
	@Test
	void testInvalidInputConstructor() {
		
		//Assert
		assertThrows(IllegalArgumentException.class, () -> new Car(101, 165, "munya"));
		assertThrows(IllegalArgumentException.class, () -> new Car(10000, 165, "munya"));
		
		assertThrows(IllegalArgumentException.class, () -> new Car(1001, -1, "munya"));
		assertThrows(IllegalArgumentException.class, () -> new Car(1002, 5001, "munya"));
		
		assertThrows(IllegalArgumentException.class, () -> new Car(1002, 5001, "munya"));
		assertThrows(IllegalArgumentException.class, () -> new Car(1002, 5001, "munya"));
		
//		Car car = new Car(1001, 165, "rtx_v");
//		assertThrows(IllegalArgumentException.class, () -> car.setModel("Munya"));
	}
	
	
	

	
}
