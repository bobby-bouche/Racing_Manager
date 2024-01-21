package data_classes;

import java.io.Serializable;
import java.util.Objects;

import enums.CarModelType;


public class Car implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	// car fields
	private int carID;
	private int topSpeed;
	private CarModelType model;
	private int driverID;
	
	
	// symbolic constants
	private static int DEFAULT_DRIVER_ID = 0;
	
	
	// initializer
	{
		driverID = DEFAULT_DRIVER_ID;
	}
	
	
	// constructors
	public Car() {
		super();
	}
	
	public Car(int carID, int topSpeed, String model) {
		this(carID,topSpeed,model,DEFAULT_DRIVER_ID);
	}
	
	public Car(int carID, int topSpeed, String model, int driverID) {
		
		super();
		validateCarID(carID);
		this.carID = carID;
		validateTopSpeed(topSpeed);
		this.topSpeed = topSpeed;
		validateModelType(model);
		this.model = CarModelType.fromString(model);
		// TODO validation
		this.driverID = driverID;
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
	
	public int getDriverID() {
		return driverID;
	}
	
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	
	// method to add driver to car
	public void addDriver(int driverID) {
		if(!(this.driverID == 0)) {
			System.out.println("there is a driver already registered for this car");
		}
		else {
			this.driverID = driverID;
		}		
	}
	
	// method to remove driver from car
	public void removeDriver() {
		if(this.driverID == 0) {
			System.out.println("car has no registered driver");
		}
		else {
			this.driverID = 0;
			System.out.println("car is available for use");
		}
	}

	
	// toString method
	@Override
	public String toString() {
		return "Car [carID=" + carID + ", model=" + model + ", topSpeed=" + topSpeed + ", driverID="+ driverID +"]";
	}
	
	
	/*
	 * this method overrides the equals method to resolve comparison failure
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
