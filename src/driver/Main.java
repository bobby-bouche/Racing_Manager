package driver;


public class Main {

	public static void main(String[] args) {
	
	
		TeamManager man = new TeamManager();
		//test
		man.connectDB();
		man.retrieveData();
		
		System.out.println(man.getDrivers());
		System.out.println(man.getRaceCars());
		System.out.println(man.getRaceTeam());
	}

}
