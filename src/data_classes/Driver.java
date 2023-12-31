package data_classes;

import java.io.Serializable;

import enums.ExperienceLevel;


public class Driver implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	// driver fields
	private int driverID;
	private String name;
	private ExperienceLevel experienceLevel;
	
	
	// constructors
	public Driver() {
		super();
	}
	
	public Driver(int driverID, String name, String experienceLevel) {
		
		validateDriveID(driverID);
		this.driverID = driverID;
		validateName(name);
		this.name = name;
		validateExperienceLevel(experienceLevel);
		this.experienceLevel = ExperienceLevel.fromString(experienceLevel);
	}
	
	
	// validation methods
	private static void validateDriveID(int inputID) {
		if(inputID < 1000 || inputID > 1200) {
			throw new IllegalArgumentException("Invalid driver id");
		}
	}
	
	private static void validateName(String inputName) {
		if(inputName.length() > 50) {
			throw new IllegalArgumentException("Invalid name");
		}
	}
	
	private static void validateExperienceLevel(String inputLevel) {
		if(inputLevel == null || inputLevel.isEmpty()) {
			throw new IllegalArgumentException("Invalid experience level");
		}
	}
	
	
	// getters and setters
	public int getDriverID() {
		return driverID;
	}

	public void setDriverID(int driverID) {
		validateDriveID(driverID);
		this.driverID = driverID;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		validateName(name);
		this.name = name;
	}

	public ExperienceLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		validateExperienceLevel(experienceLevel);
		this.experienceLevel = ExperienceLevel.fromString(experienceLevel);
	}
	

	@Override
	public String toString() {
		return "Driver [driverID=" + driverID + ", name=" + name + ", experienceLevel=" + experienceLevel + "]";
	}

}



