package enums;

public enum CarModelType {
	
	GTI_II ("GTI_II"),
	RTX_V ("RTX_V"),
	TGI_III ("TGI_III");
	
	
	private String modelType;
	
	
	CarModelType(String model) {
		this.modelType = model;
	}


	public String getModel() {
		return modelType;
	}
	
	
	/*
	 * this method takes string input from user and validates for enum value match
	 */
	public static CarModelType fromString(String inputModel) {
		for(CarModelType model : CarModelType.values()) {
			if(model.modelType.equalsIgnoreCase(inputModel)) {
				return model;
			}
		}
		// default
		return CarModelType.GTI_II;
	}
		
	
}
