package driver;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
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
	// this is a neat feature to implement a Map that will store currant race team data
	private static Map<Integer, Car> raceTeam;
	
	private Keyboard kb;
	Connection connection;
	
	
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
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/racing_manager_db", "root", "Ronaldo");
		}
		catch(ClassNotFoundException | SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
	
	
	
	// method to connect to the database, retrieve all data, and populate static lists
	void retrieveData() {
		try {
			
			connection = connectDB();
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
	 *  method to continuously refresh data lists every 5 seconds
	 *  to ensure any live updates to the data-set remains synced between both
	 *  database and program data.
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
	 
	// method to create new driver and add to driver database
	public void createNewDriver() {
		
		// create name, experienceLevel validation methods in keyboard class
		String name = kb.readString("enter name: ", "Invalid name, please try again");
		String experienceLevel = kb.readString("enter experience level: ", "Invalid entry, please try again;");
				
		try {
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("INSERT INTO driver (name, experienceLevel) VALUES(?, ?)");
			ps.setString(1, name.toUpperCase());
			ps.setString(2, experienceLevel.toUpperCase());
			
			ps.execute();
			ps.close();
			connection.close();
			
			System.out.println("driver: " + name + " succesfully registered!");
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// method to read driver info
	public String readDriverInfo(int driverID) throws SQLException {
		
		String driverInfo = "";
		PreparedStatement ps = null;
		ResultSet rs = null;
		
		try {
			connection = connectDB();
			ps = connection.prepareStatement("SELECT * FROM driver WHERE driverID = ?");
			ps.setInt(1, driverID);
			rs = ps.executeQuery();
			
			if(rs.next()) {
				
				String name = rs.getString("name");
				String experienceLevel = rs.getString("experienceLevel");
				
				Driver driver = new Driver();
				driver.setDriverID(driverID);
				driver.setName(name);
				driver.setExperienceLevel(experienceLevel);
				
				driverInfo = driver.toString();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		finally {
			if(rs != null) {
				rs.close();
			}
			if(ps != null) {
				ps.close();
			}
			if(connection != null) {
				connection.close();
			}
		}
		return driverInfo;	
	}
	
	
	//method to retrieve driver object from driver list
	/*
	 * this is just another way to search. the update method search using an object whereas
	 * the delete method searchs with an integer
	 */
	 Driver retrieveDriverObject(int driverID) {
		
		Driver driver = null;
		
		for(Driver d : drivers) {
			if(d.getDriverID() == driverID) {
				driver = d;
			}
		}
		return driver;
	}
	
	
	// method to update driver details
	public void updateDriverInfo(Driver driver) {
		
		// would rather have this method connect directly to database than calling retrieveDriverObject ??
		

		try {
			connection = connectDB();
			PreparedStatement ps = connection.prepareStatement("UPDATE driver SET name = ?, experienceLevel= ? WHERE driverID = ?");
			
			ps.setString(1, driver.getName());
			ps.setString(2, driver.getExperienceLevel().getLevel());
			ps.setInt(3, driver.getDriverID());
			
			ps.executeUpdate();
			ps.close();
			connection.close();
			
			System.out.println("Player data successfully updated!");
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	// method to delete driver 
	public void deleteDriver(int driverID) {
		
		boolean driverFound = false;
		
		for(Driver d : drivers) {
			if(d.getDriverID() == driverID) {
			
				connection = connectDB();
				try {
					PreparedStatement ps = connection.prepareStatement("DELETE FROM driver WHERE driverID = ?");
					ps.setInt(1, driverID);
					ps.executeUpdate();
					
					ps.close();
					connection.close();
					System.out.println("Driver removed from database");
					driverFound = true;
					break;
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
		}
		
		if(!driverFound) {
			System.out.println("no driver with id: " + driverID + " in database");
		}
	}
	

	
}
