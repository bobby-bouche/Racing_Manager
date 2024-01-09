package driver;

import java.sql.SQLException;

import data_classes.Car;
import data_classes.Driver;

public class Main {

	public static void main(String[] args) {
	
	
		TeamManager Manager = new TeamManager();
		//test
		//man.connectDB();
		Manager.retrieveData();
		
//		System.out.println(Manager.getDrivers());
//		System.out.println(Manager.getRaceCars());
//		System.out.println(Manager.getRaceTeam());
		
		Manager.registerNewDriver();
		
		try {
			System.out.println(Manager.readDriverInfo(1000));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
//		Driver d = new Driver(1001, "newman", "ROOKIE");
//		
//		Car c = new Car(1002, 165, "rtx_v", d.getDriverID());
//		
//		System.out.println(c.toString());
	}

}
