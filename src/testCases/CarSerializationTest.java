package testCases;

import static org.junit.Assert.fail;

import java.io.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import racingTeam.Car;


public class CarSerializationTest {
	
	private static final String FILE_NAME = "car.ser";
	
	@BeforeEach
	void setUp() {
		// Ensure the file doesn't exist before each test
		deleteSerializedFile();
	}
	
	@AfterEach
	void tearDown() {
		// Clean up: Delete the file created during the test
		deleteSerializedFile();
	}
	
	@Test
	void testSerialization() {
		
		Car originalCar = new Car(1001, 200, "rtx_v");
		
		try {
			
			//Serialize car object
			FileOutputStream fileOut = new FileOutputStream(FILE_NAME);
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(originalCar);
			out.close();
			fileOut.close();
			
			//Deserialize car object
			FileInputStream fileIn = new FileInputStream(FILE_NAME);
			ObjectInputStream in = new ObjectInputStream(fileIn);
			Car deserializedCar = (Car) in.readObject();
			in.close();
			fileIn.close();
			
	        // Diagnostic logs
	        System.out.println("Original Car: " + originalCar);
	        System.out.println("Deserialized Car: " + deserializedCar);
			
			
			//Verify deserialized object matches the original
			if(!originalCar.equals(deserializedCar)) {
				fail("Deserialized object does not match the original object.");
			}
			else {
				System.out.println("Serialization test successfull");
			}
			
		}
		catch(IOException | ClassNotFoundException e) {
			fail("Exception occurred during serialization/deserialization process" + e.getMessage());
		}
		
	}
	
	private void deleteSerializedFile() {
		File file = new File(FILE_NAME);
		if(file.exists()) {
			if(!file.delete()) {
				System.out.println("Could not delete the file: " + file);
			}
		}
	}
	
	

}
