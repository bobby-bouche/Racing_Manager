package racingTeam;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TeamManager implements Serializable {

	private static final long serialVersionUID = 1L;
	
	//teamManager fields
	private static Keyboard kb;
	private Map<Integer, Car> team;
	
	
	//constructor
	public TeamManager() {
		super();
		kb = new Keyboard();
		team = new HashMap<>();
	}
	
	

}
