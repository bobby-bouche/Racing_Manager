package enums;

public enum ExperienceLevel {

	ROOKIE ("rookie"),
	ESTABLISHED ("ESTABLISHED"),
	SEASONED_PRO ("seasoned pro");
	
	
	private String level;
	
	//constructor
	ExperienceLevel(String level){
		this.setLevel(level);
	}

	
	// getter and setter
	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}
	
	
	/*
	 * method that takes String input and validates for valid enum value match
	 * If no match is found, default level is assigned.
	 */
	public static ExperienceLevel fromString(String inputLevel) {
		for(ExperienceLevel level : ExperienceLevel.values()) {
			if(level.level.equalsIgnoreCase(inputLevel)) {
				return level;
			}
		}
		return ExperienceLevel.ROOKIE;
	}
	
}
