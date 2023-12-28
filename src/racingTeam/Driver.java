package racingTeam;

// TODO validation methods, experience level enum set

public class Driver {
	
	// driver fields
	private int driverID;
	private String name;
	private int experienceLevel;
	
	
	// constructors
	public Driver() {
		super();
	}
	
	public Driver(int driverID, String name, int experienceLevel) {
		
		this.driverID = driverID;
		this.name = name;
		this.experienceLevel = experienceLevel;
	}
	
	
	// validation methods
	
	private static void validateDriveID(int value) {
		
	}
	
	private static void validateName(String value) {
		
	}
	
	private static void validateExperienceLevel(int value) {
		
	}
	
	
	// getters and setters
	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(int experienceLevel) {
		this.experienceLevel = experienceLevel;
	}
	

	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", name=" + name + ", experienceLevel=" + experienceLevel + "]";
	}

}



