package driver;

import data_classes.Car;
import data_classes.Driver;

public class Main {

	public static void main(String[] args) {
	
	
		TeamManager man = new TeamManager();
		//test
		man.connectDB();
		man.retrieveData();
		
		System.out.println(man.getDrivers());
		System.out.println(man.getRaceCars());
		System.out.println(man.getRaceTeam());
		
//		Driver d = new Driver(1001, "newman", "ROOKIE");
//		
//		Car c = new Car(1002, 165, "rtx_v", d.getDriverID());
//		
//		System.out.println(c.toString());
	}

}
