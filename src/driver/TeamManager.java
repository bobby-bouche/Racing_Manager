package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import data_classes.Car;
import data_classes.Driver;
import inputValidation.Keyboard;

public class TeamManager {
	
	//teamManager fields
	private static List<Driver> drivers;
	private static List<Car> raceCars;
	// this is a neat feature to implement that will store currant race team
	private static Map<Integer, Car> raceTeam;
	
	private Keyboard kb;

	
	//constructor
	public TeamManager() {
		super();
		kb   = new Keyboard();
		raceTeam = new HashMap<>();
	}


	// getters
	public List<Driver> getDrivers() {
		return drivers;
	}

	public List<Car> getRaceCars() {
		return raceCars;
	}

	public Map<Integer, Car> getRaceTeam() {
		return raceTeam;
	}
	
	
	//method to create database connection
	Connection connectDB() {
		
		Connection con = null;
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/racing_manager_db", "root", "ronaldo");
			System.out.println("success");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	/*
	 *  method to continuously refresh data lists every 5 seconds
	 *  to ensure an updates to the data remains synced between
	 *  database and running program data.
	 */
	public void CDCListener() {
		
		while(true) {
			try {
				retrieveData();
				Thread.sleep(5000);
			}
			catch(InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	// method to connect to the database, retrieve all data, and populate static lists
	void retrieveData() {
		try {
			
			Connection connection = connectDB();
			drivers  = retrieveDrivers(connection);
			raceCars = retrieveCars(connection, drivers);
			connection.close();
			
			populateCurrentRaceTeam(raceTeam);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/*
	 *  This method searches for raceCars currently being piloted by drivers and adds them
	 *  to the current race team line-up.
	 */
	private static Map<Integer, Car> populateCurrentRaceTeam(Map<Integer, Car> TeamLineup){
		
		for(Car car : raceCars) {
			if(!(car.getDriverID() == 0)){
				TeamLineup.put(car.getDriverID(), car);
			}
		}
		return TeamLineup;
	}
	
	
	// method to retrieve driver data from database
	private static List<Driver> retrieveDrivers(Connection con) throws SQLException {
		
		List<Driver> drivers = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM driver");
		
	    while(rs.next()) {
	    	
	    	Driver driver = new Driver();
	    	driver.setDriverID(rs.getInt("driverID"));
	    	driver.setName(rs.getString("name"));
	    	driver.setExperienceLevel(rs.getString("experienceLevel"));
	    	
	    	drivers.add(driver);
	    }
	    rs.close();
	    stmt.close();
	    
	    return drivers;
	}
	
	
	// method to retrieve car data from database
	private static List<Car> retrieveCars(Connection con, List<Driver> drivers) throws SQLException {
		
		List<Car> cars = new ArrayList<>();
		Statement stmt = con.createStatement();
		ResultSet rs = stmt.executeQuery("SELECT * FROM car");
		
		while(rs.next()) {
			
			Car car = new Car();
			car.setCarID(rs.getInt("carID"));
			car.setTopSpeed(rs.getInt("topSpeed"));
			car.setModel(rs.getString("modelType"));
			car.setDriverID(rs.getInt("driverID"));
			cars.add(car);
			
		}
		rs.close();
		stmt.close();
		
		return cars;
	}
	
	
	// CRUD methods
	
	// method to read driver info
//	public String readDriverInfo(int driverID) {
//		
//		Driver d = new Driver();
//		Statement stmt = 
//		
//		return null;
//	}
	 
	// method to register new driver to driver and add to driver database
	public void registerNewDriver() {
		
	}
	
	
	// method to update driver details
	public void updateDriverInfo(int driverID) {
		
	}
	
	
	// method to delete driver 
	public void deleteDriver(int driverID) {
		
	}
	

	
	
	
	
	
	
	
	
	
	
	
}
