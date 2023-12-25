package racingTeam;

/*
 * 
 * this class creates a Car object by taking in user input, validating it, and assigning data
 * to Car attributes.
 */
public class Car {
	
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
		this.model = CarModelType.fromString(stringModel);
	}
	
	
	//validation method
	private static void validateCarID(int value) {
		if(value < 1000 || value > 9999) {
			throw new IllegalArgumentException("Thats illegal feen");
		}
	}
	
	private static void validateTopSpeed(int value) {
		if(value < 0 || value > 5000) {
			throw new IllegalArgumentException("Thats illegal feen");
		}
	}
	
	private static void validateModelType(String value) {
		
	}

	
	// getters and setters
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public CarModelType getModel() {
		return model;
	}

	public void setModel(String string) {
		this.model = CarModelType.fromString(string);;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}
	
	
	// toString method
	@Override
	public String toString() {
		return "Car [carID=" + carID + ", model=" + model + ", topSpeed=" + topSpeed + "]";
	}
	
	
}
