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
		this.carID    = carID;
		this.topSpeed = topSpeed;
		this.model    = CarModelType.fromString(stringModel);
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
