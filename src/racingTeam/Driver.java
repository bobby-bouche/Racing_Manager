package racingTeam;

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



