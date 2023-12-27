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
		int expectedCarID = 1005;
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
	void testSetTopSpeed() {
		
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
	void testSetModelType() {
		
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
		assertThrows(IllegalArgumentException.class, () -> new Car(101, 165, "tgi_iii"));
		assertThrows(IllegalArgumentException.class, () -> new Car(10000, 165, "tgi_iii"));
		assertThrows(IllegalArgumentException.class, () -> new Car(1001, -1, "tgi_iii"));
		assertThrows(IllegalArgumentException.class, () -> new Car(1002, 5001, "tgi_iii"));
		assertThrows(IllegalArgumentException.class, () -> new Car(1002, 401, "munya"));
	}
	
	
	@Test
	/*
	 * testing for multiple state changes
	 */
	void testMultipleStateChanges() {
		
		Car car = new Car(1001, 165, "rtx_v");
		car.setCarID(2001);
		assertEquals(2001, car.getCarID());
		car.setTopSpeed(300);
		assertEquals(300, car.getTopSpeed());
	}

	
	@Test
	/*
	 * testing for consistency
	 */
	void testConsistency() {
		
		Car car = new Car(1001, 165, "rtx_v");
		car.setTopSpeed(200);
		assertEquals(CarModelType.RTX_V, car.getModel());
	}

	
	@Test
	/*
	 * testing for invalid Enum value
	 */
	void testInvalidEnumValue() {
		
		Car car = new Car(1001, 165, "rtx_v"); 
		assertThrows(IllegalArgumentException.class, () -> car.setModel("Munya"));
	}
	
	@Test
	/*
	 * testing for invalid carID state change
	 */
	void testInvalidCarIDChange() {
		
		Car car = new Car(1001, 165, "rtx_v"); 
		assertThrows(IllegalArgumentException.class, () -> car.setCarID(999));
		assertThrows(IllegalArgumentException.class, () -> car.setCarID(10000000));
	}
	
	
	/*
	 * testing for invalid top speed state change
	 */
	@Test
	void testInvalidTopSpeedChange() {
		
		Car car = new Car(1001, 165, "rtx_v"); 
		assertThrows(IllegalArgumentException.class, () -> car.setTopSpeed(5001));
		assertThrows(IllegalArgumentException.class, () -> car.setTopSpeed(-1));
	}
	
	
	/*
	 * testing for invalid model type state change
	 */
	@Test
	void testInvalidCarModelTypeChange() {
		
		Car car = new Car(1001, 165, "rtx_v");
		assertThrows(IllegalArgumentException.class, () -> car.setModel("Munya"));
		assertThrows(IllegalArgumentException.class, () -> car.setModel(" "));
		assertThrows(IllegalArgumentException.class, () -> car.setModel(null));
		assertThrows(IllegalArgumentException.class, () -> car.setModel("1002"));
		assertThrows(IllegalArgumentException.class, () -> car.setModel("its_2"));
		assertThrows(IllegalArgumentException.class, () -> car.setModel("rtx_iv"));
	}
	
}
