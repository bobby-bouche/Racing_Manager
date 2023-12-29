package testCases;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.fail;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingTeam.Car;
import racingTeam.CarModelType;

class CarTest {
	
	private static final String FILE_NAME = "car.ser";

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
	
	/*
	 * testing edge cases: min and max values, boundary conditions, and null inputs.
	 */
	@Test
	void testEdgeCases() {
		
		//Boundary conditions for carID
		Car carMinID = new Car(1000,200,"rtx_v");
		Car carMaxID = new Car(9999,200,"rtx_v");
		
		//Boundary conditions for topSpeed
		Car carMinSpeed = new Car(1001,0,"rtx_v");
		Car carMaxSpeed = new Car(1001,5000,"rtx_v");
	
		assertEquals(1000, carMinID.getCarID());
		assertEquals(9999, carMaxID.getCarID());
		assertThrows(IllegalArgumentException.class, () -> new Car(1001, -1, "rtx_v"));
		
		assertEquals(0, carMinSpeed.getTopSpeed());
		assertEquals(5000, carMaxSpeed.getTopSpeed());
		assertThrows(IllegalArgumentException.class, () -> new Car(1001, -1, "rtx_v"));

	}
	
	
	/*
	 * testing serialization performance
	 */
	@Test
	void testSerializationPerformance() {
		
		// number of objects to serialize
		final int NUM_OBJECTS = 2000;
		
		long startTime = System.currentTimeMillis();
		
		try {
			
			for(int i = 1000; i < NUM_OBJECTS; i++) {
				Car car = new Car(i, 200, "rtx_v");
				String fileName = FILE_NAME + i + ".ser";
				FileOutputStream fileOut = new FileOutputStream(fileName);
				ObjectOutputStream out = new ObjectOutputStream(fileOut);
				out.writeObject(car);
				out.close();
				fileOut.close();
			}
			
			for(int i = 1000; i < NUM_OBJECTS; i++) {
				String fileName = FILE_NAME + i + ".ser";
				FileInputStream fileIn = new FileInputStream(fileName);
				ObjectInputStream in = new ObjectInputStream(fileIn);
				Car deserializedCar = (Car) in.readObject();
				in.close();
				fileIn.close();
				
				System.out.println("Deserialized Car: " + deserializedCar);
			}
		}
		catch(IOException | ClassNotFoundException e) {
			fail("Exception occurred during serialization/deserialiation process: " + e.getMessage());
		}
		
		
		// created files are deleted before program ends
		for(int i = 0; i< NUM_OBJECTS; i++) {
			String fileName = FILE_NAME + i + ".ser";
			File file = new File(fileName);
			if(file.exists()) {
				if(!file.delete()) {
					System.out.println("Could not delete this file: " + fileName);
				}
			}
		}
		
		long endTime = System.currentTimeMillis();
		long elapsedTime = endTime - startTime;
		
		System.out.println("Time taken for " + (NUM_OBJECTS - 1000) + " objects: " + elapsedTime + "ms");
	}
	
	
}
