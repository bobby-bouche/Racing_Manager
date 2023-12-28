package racingTeam;

import java.io.Serializable;
import java.util.Objects;

/*
 * 
 * this class creates a Car object by taking in user input, validating it, and assigning data
 * to Car attributes.
 */
public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// car fields
	private int carID;
	private int topSpeed;
	private CarModelType model;
	
	
	// constructors
	public Car() {
		super();
	}
	
	public Car(int carID, int topSpeed, String stringModel) {
		
		super();
		validateCarID(carID);
		this.carID = carID;
		validateTopSpeed(topSpeed);
		this.topSpeed = topSpeed;
		validateModelType(stringModel);
		this.model = CarModelType.fromString(stringModel);
	}
	
	
	//validation methods
	private static void validateCarID(int value) {
		if(value < 1000 || value > 9999) {
			throw new IllegalArgumentException("Thats illegal in carID class feen");
		}
	}
	
	private static void validateTopSpeed(int value) {
		if(value < 0 || value > 5000) {
			throw new IllegalArgumentException("Thats illegal in carSpeed class feen");
		}
	}
	
	private static void validateModelType(String value) {
		if(value == null || value.isEmpty()) {
			throw new IllegalArgumentException("Thats illegal in carModel 1 class feen");
		}
		else if(CarModelType.fromString(value) == CarModelType.GTI_II) {
			throw new IllegalArgumentException("Thats illegal in carModel 2 class feen");
		}
	}

	
	// getters and setters
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		validateCarID(carID);
		this.carID = carID;
	}

	public CarModelType getModel() {
		return model;
	}

	public void setModel(String string) {
		validateModelType(string);
		this.model = CarModelType.fromString(string);;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		validateTopSpeed(topSpeed);
		this.topSpeed = topSpeed;
	}
	
	
	// toString method
	@Override
	public String toString() {
		return "Car [carID=" + carID + ", model=" + model + ", topSpeed=" + topSpeed + "]";
	}
	
	
	/*
	 * this method overrides the equals method to to resolve comparison failure
	 * during serialization test process
	 */
	@Override
	public boolean equals(Object obj) {
		if(this == obj) return true;
		if(obj == null || getClass() != obj.getClass()) return false;
		Car otherCar = (Car) obj;
		return this.carID == otherCar.carID 
				&& this.topSpeed == otherCar.topSpeed 
				&& Objects.equals(this.model,  otherCar.model);
	}

}
